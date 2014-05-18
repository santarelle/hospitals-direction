package br.com.hospitalsdirection.presenter;

import roboguice.inject.ContextSingleton;
import android.app.Activity;
import android.util.Log;
import br.com.hospitalsdirection.view.MainView;

import com.google.inject.Inject;

@ContextSingleton
public class MainPresenterImpl implements MainPresenter{


	private MainView mainActivity;



	@Inject
	public MainPresenterImpl(final Activity activity) {
		mainActivity = (MainView) activity;
		Log.d("Activity injec", "testee");
	}


	@Override
	public void optionSelected(String option) {
		mainActivity .rendereFragment(option);
		//mainActivity.direct(Radio.class);

	}

}
 