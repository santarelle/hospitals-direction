package br.com.hospitalsdirection.view;

import br.com.hospitalsdirection.presenter.IMainPresenter;

import com.google.inject.Inject;

import roboguice.fragment.RoboFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainFragment extends RoboFragment {

	@Inject
	IMainPresenter mainPresenter;
	String[] vetMenu;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_main, null);
		//		Button btHospitalNear = (Button) view.findViewById(R.id.btHospitalNear);
		//btHospitalNear.setOnClickListener(new OnClickListener() {
		//@Override
		//			public void onClick(View view) {
		//				optionSelected(view);
		//			}
		//		});
		getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		ListView listView=(ListView) view.findViewById(R.id.listMenu);
		vetMenu =new String[]{view.getContext().getString(R.string.radio),view.getContext().getString(R.string.hospital_proximos)};
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1,vetMenu );
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int item, long arg3) {
				mainPresenter.optionSelected(vetMenu[item]);
			}
		});
		return view;
	}



}
