package br.com.hospitalsdirection.presenter;

import java.util.Iterator;
import java.util.List;

import roboguice.inject.ContextSingleton;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import br.com.hospitalsdirection.manager.communicationsmanager.HospitalCommunicationServiceImpl;
import br.com.hospitalsdirection.manager.communicationsmanager.HospitalCommunicationService;
import br.com.hospitalsdirection.manager.communicationsmanager.RouteCommunicationService;
import br.com.hospitalsdirection.manager.communicationsmanager.RouteCommunicationServiceImpl;
import br.com.hospitalsdirection.manager.contextmanager.LocationService;
import br.com.hospitalsdirection.manager.metadadosmanager.Hospital;
import br.com.hospitalsdirection.manager.metadadosmanager.Route;
import br.com.hospitalsdirection.view.HospitalFragment;
import br.com.hospitalsdirection.view.HospitalView;
import br.com.hospitalsdirection.view.R;

import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.inject.Inject;

@ContextSingleton
public class HospitalPresenterImpl implements HospitalPresenter {

	HospitalView hospitalFragment;

	HospitalCommunicationService communicationService;
	RouteCommunicationService routeCommunicationService;

	@Inject
	LocationService locationService;

	private Location location;
	private ProgressDialog progressDialog;
	private PolylineOptions rectOptions;
	private Polyline polyline;
	private Marker markerLocation;
	private boolean proximos = false;
	private Context context;	
	private GoogleMap map;
	private LocationListener locationListener = new LocationListener() {
		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {

		}

		@Override
		public void onProviderEnabled(String arg0) {

		}

		@Override
		public void onProviderDisabled(String arg0) {

		}

		@Override
		public void onLocationChanged(Location loc) {
			if(markerLocation!=null){
				markerLocation.remove();
			}
			markerLocation= map.addMarker(new MarkerOptions()
			.position(new LatLng(loc.getLatitude(), loc.getLongitude()))
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.ambulance)));
			location =loc;
			if(proximos){
				populateHospitaisNear();  
				proximos = false;
				if(progressDialog.isShowing()){
					progressDialog.dismiss();
				}

			}
		}
	}; 


	@Inject
	public HospitalPresenterImpl(HospitalView fragment, Context context){
		hospitalFragment = (HospitalFragment) fragment;
		this.context = context;
		Log.d("fragmente inject", "presenter");
	}

	


	public void populate(){
		this.map = hospitalFragment.getMap();
		map.clear();
		progressDialog = ProgressDialog.show(context, "", context.getString(R.string.carregando_informacoes));
		if(locationService.gpsAtivo()){
			location = locationService.getLocation(locationListener);
			if(location!=null){
				populateHospitaisNear();
				markerLocation= map.addMarker(new MarkerOptions()
				.position(new LatLng(location.getLatitude(), location.getLongitude())).title("")
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.ambulance)));

			}else{  
				proximos = true;
			}

		}
	}
	private void populateHospitaisNear() {
		SharedPreferences 	shrPreference = PreferenceManager.getDefaultSharedPreferences(context);
		float raioM = (shrPreference.getFloat("radius",5)*1000);
		communicationService = getHospitalCommunicationService();
		communicationService.hospitaisProximos(location.getLatitude(), location.getLongitude(),raioM,null);		
		proximos=false;
	}

	public HospitalCommunicationServiceImpl getHospitalCommunicationService(){
		HospitalCommunicationServiceImpl communicationService = new HospitalCommunicationServiceImpl(context) {

			@Override
			protected void executeResult(List<Hospital> resultList) {
				addMarker(resultList);
				if(!resultList.isEmpty()){
					if(resultList.get(0).getNextToken()!=null ){
						SharedPreferences 	shrPreference = PreferenceManager.getDefaultSharedPreferences(context);
						float raioM = (shrPreference.getFloat("radius",5)*1000);
						HospitalPresenterImpl.this.communicationService.hospitaisProximos(location.getLatitude(), location.getLongitude(),raioM,resultList.get(0).getNextToken());	
					}else{
						if(progressDialog.isShowing()&& resultList.get(0).getNextToken()==null ){
							progressDialog.dismiss();
						}	
					}
				}else{
					if(progressDialog.isShowing() ){
						progressDialog.dismiss();
					}
				}
			
				
			}

			@Override
			protected ErrorListener errorListener() {
				
				return new Response.ErrorListener() {
					@Override
					public void onErrorResponse(final VolleyError error) {
						if (error != null) {
							progressDialog.dismiss();
							AlertDialog alertDialog = new AlertDialog.Builder(context).create();
							alertDialog.setMessage(getContext().getString(R.string.erro_conexao));
							alertDialog.show();
						}
					}
				};
			}
		};
		return communicationService;
	}


	public RouteCommunicationServiceImpl getRouteCommunicationService(){
		RouteCommunicationServiceImpl communicationService = new RouteCommunicationServiceImpl(context) {

			@Override
			protected void executeResult(List<Route> resultList) {
				if(resultList!=null){
					adicionaLinhaMapa(resultList.get(0).getPoints(), Color.BLUE);
				}
				if(progressDialog.isShowing()){
					progressDialog.dismiss();
				}

			}

			@Override
			protected ErrorListener errorListener() {
				{
					
					return new Response.ErrorListener() {
						@Override
						public void onErrorResponse(final VolleyError error) {
							if (error != null) {
								progressDialog.dismiss();
								AlertDialog alertDialog = new AlertDialog.Builder(context).create();
								alertDialog.setMessage(getContext().getString(R.string.erro_conexao));
								alertDialog.show();
							}
						}
					};
				}
			}
		};
		return communicationService;
	}

	private void addMarker(List<Hospital> listHospitals){
		for (Hospital hospital : listHospitals) {
			map.addMarker(new MarkerOptions()
			.position(new LatLng(hospital.getLatitude(), hospital.getLongitude()))
			.title(hospital.getNome())
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital_building))).showInfoWindow();
			Log.d("hospital", ""+hospital.getNome());
		}

		map.setOnMarkerClickListener(new OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(Marker marker) {
				progressDialog = ProgressDialog.show(context, "", "Carregando Rota");
				routeCommunicationService = getRouteCommunicationService();
				routeCommunicationService.getRota(location.getLatitude()+","+location.getLongitude(),marker.getPosition().latitude+","+marker.getPosition().longitude );
				return false;
			}
		});
	}

	public void adicionaLinhaMapa(List<LatLng> listaCoordenadas, int cor){
		rectOptions = new PolylineOptions();


		if(listaCoordenadas != null && listaCoordenadas.size() > 0){
			Iterator<LatLng> it = listaCoordenadas.iterator();
			while (it.hasNext()) {
				LatLng latLng = (LatLng) it.next();
				rectOptions.add(latLng);
			}
		}

		rectOptions.color(cor);

		if(rectOptions != null && rectOptions.getPoints().size() > 0){
			mHandlerAddLinha.sendEmptyMessage(0);

		}

	}

	Handler mHandlerAddLinha = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if(polyline!=null){
				polyline.remove();
			}
			polyline =map.addPolyline(rectOptions);
			polyline.setGeodesic(true);
			if(progressDialog.isShowing()){
				progressDialog.dismiss();
			}
		};
	};

}
