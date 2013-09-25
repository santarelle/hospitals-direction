package br.com.hospitalsdirection.view;

import roboguice.fragment.RoboFragment;
import android.os.Bundle;
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
	private void configuraMapa(){

		if(map == null) {

			map =  ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

			if(map != null){
				GoogleMapsUtils	mapaUtils = new GoogleMapsUtils(this.getActivity(), map);

				mapaUtils.setVisualizacao(GoogleMap.MAP_TYPE_NORMAL);
				mapaUtils.configuraPosicionamento(new LatLng(-3.11056, -60.03593), 0, 0, 17);

				

			}
		}
		hospitalPresenter.populate();
	}

	@Override
	public GoogleMap getMap() {
		return map;
	}


}
