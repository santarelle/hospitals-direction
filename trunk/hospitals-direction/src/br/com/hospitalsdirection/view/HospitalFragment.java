package br.com.hospitalsdirection.view;

import roboguice.fragment.RoboFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.hospitalsdirection.presenter.IHospitalPresenter;

import com.google.android.gms.maps.GoogleMap;
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
		hospitalPresenter.populate();	
		return view;
	}

	@Override
	public void onResume() {
	
		super.onResume();
	}
	
	public GoogleMap getMap() {
		return map;
	}


}
