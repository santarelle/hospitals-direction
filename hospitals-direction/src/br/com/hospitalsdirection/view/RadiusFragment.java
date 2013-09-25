package br.com.hospitalsdirection.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import roboguice.fragment.RoboFragment;

public class RadiusFragment extends RoboFragment {

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
			view = inflater.inflate(R.layout.fragment_radius, null);
		} catch (Exception e) {
			Log.d("erro",e.toString());
		}
			
		return view;
	}

}
