package br.com.hospitalsdirection.view;

import br.com.hospitalsdirection.presenter.IRadiusPresenter;

import com.google.inject.Inject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import roboguice.fragment.RoboFragment;

public class RadiusFragment extends RoboFragment implements IRadiusFragment{

	private View view;
	
	@Inject
	IRadiusPresenter radiusPresenter;
	
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
			Button btConfig = (Button) view.findViewById(R.id.config);
			btConfig.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					radiusPresenter.configure();
					getActivity().onBackPressed();
				}
			});
		} catch (Exception e) {
			Log.d("erro",e.toString());
		}
			
		return view;
	}
	
	@Override
	public void onResume() {
		radiusPresenter.populate(view);
		super.onResume();
	}
	

}
