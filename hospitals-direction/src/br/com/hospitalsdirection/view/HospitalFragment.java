package br.com.hospitalsdirection.view;

import roboguice.fragment.RoboFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.hospitalsdirection.presenter.IHospitalPresenter;
import br.com.hospitalsdirection.utils.GoogleMapsUtils;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.inject.Inject;


public class HospitalFragment extends RoboFragment implements IHospitalFragment{

	@Inject
	IHospitalPresenter hospitalPresenter;
	private GoogleMap map;
	
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(view!=null){
			ViewGroup parent = (ViewGroup) view.getParent();
	        if (parent != null)
	            parent.removeView(view);
		}
		try {
			if(map!=null){
				hospitalPresenter.populate();
			}
			view = inflater.inflate(R.layout.fragment_hospital, null);
		} catch (Exception e) {
			Log.d("erro",e.toString());
		}
		return view;
	}

	@Override
	public void onResume() {
		configuraMapa();
		super.onResume();
		
	}
	
	public GoogleMap getMap() {
		return map;
	}
	
	private void configuraMapa(){
		if(map == null) {
			FragmentManager fragment = getFragmentManager();
			SupportMapFragment supportMapFragment =((SupportMapFragment)fragment.findFragmentById(R.id.map));
			map =  supportMapFragment.getMap();
			if(map != null){ 
				GoogleMapsUtils	mapaUtils = new GoogleMapsUtils(supportMapFragment.getActivity(), map);
				mapaUtils.setVisualizacao(GoogleMap.MAP_TYPE_NORMAL);
				mapaUtils.configuraPosicionamento(new LatLng(-3.11056, -60.03593), 0, 0, 17);
				hospitalPresenter.populate();
			}		

		}
	}
	
	
	@Override
	public void onSaveInstanceState(final Bundle outState) {
		super.onSaveInstanceState(outState);
		final float cameraZoom = map.getCameraPosition().zoom;
		outState.putInt("map_type", GoogleMap.MAP_TYPE_NORMAL);
		outState.putFloat("zoom", cameraZoom);
	}


}
