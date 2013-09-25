package br.com.hospitalsdirection.presenter;

import java.util.Iterator;
import java.util.List;

import roboguice.inject.ContextSingleton;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import br.com.hospitalsdirection.manager.communicationsmanager.ICommunicationService;
import br.com.hospitalsdirection.manager.contextmanager.LocationService;
import br.com.hospitalsdirection.manager.metadadosmanager.Hospital;
import br.com.hospitalsdirection.manager.metadadosmanager.Rota;
import br.com.hospitalsdirection.view.HospitalFragment;
import br.com.hospitalsdirection.view.IHospitalFragment;
import br.com.hospitalsdirection.view.R;

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
public class HospitalPresenter implements IHospitalPresenter {

	IHospitalFragment hospitalFragment;



	@Inject
	ICommunicationService communicationService;
	@Inject
	LocationService locationService;


	private Location location;
	private ProgressDialog progressDialog;
	private PolylineOptions rectOptions;
	private Polyline polyline;
	private Marker markerLocation;
	private boolean proximos = false;
	private Context context;	
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
			markerLocation= hospitalFragment.getMap().addMarker(new MarkerOptions()
			.position(new LatLng(location.getLatitude(), location.getLongitude()))
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
	public HospitalPresenter(IHospitalFragment fragment, Context context){
		hospitalFragment = (HospitalFragment) fragment;
		this.context = context;
		Log.d("fragmente inject", "presenter");
	}

	public void populate(){
		progressDialog = ProgressDialog.show(context, "Hospitals Direction", "Carregando Informaçoes de localizaçao");
		if(locationService.gpsAtivo()){
			location = locationService.getLocation(locationListener);
			if(location!=null){
				proximos = true;
				populateHospitaisNear();
				markerLocation= hospitalFragment.getMap().addMarker(new MarkerOptions()
				.position(new LatLng(location.getLatitude(), location.getLongitude())).title("")
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.ambulance)));
				if(progressDialog.isShowing()){
					progressDialog.dismiss();
				}
			}

		}
	}
	private void populateHospitaisNear() {
		List<Hospital> listHospital = communicationService.hospitaisProximos(location.getLatitude(), location.getLongitude());		
		this.addMarker(listHospital);
		proximos=false;
	}

	private void addMarker(List<Hospital> listHospitals){
		GoogleMap map =hospitalFragment.getMap();
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
				Rota rota = communicationService.getRota(location.getLatitude()+","+location.getLongitude(),marker.getPosition().latitude+","+marker.getPosition().longitude );
				adicionaLinhaMapa(rota.getPoints(), Color.BLUE);
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
			polyline = hospitalFragment.getMap().addPolyline(rectOptions);
			polyline.setGeodesic(true);
		};
	};

}
