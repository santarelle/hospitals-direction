package br.com.hospitalsdirection.view;

import br.com.hospitalsdirection.presenter.IMainPresenter;

import com.google.inject.Inject;

import roboguice.fragment.RoboFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends RoboFragment {

	@Inject
	IMainPresenter mainPresenter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_main, null);
		Button btHospitalNear = (Button) view.findViewById(R.id.btHospitalNear);
		btHospitalNear.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				optionSelected(view);
			}
		});
		return view;
	}

	public void optionSelected(View view){
		mainPresenter.optionSelected(view.getId());
	}
}
