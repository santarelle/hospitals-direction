package br.com.hospitalsdirection.presenter;

import roboguice.inject.ContextSingleton;
import android.app.Activity;
import android.util.Log;
import br.com.hospitalsdirection.view.IMainActivity;

import com.google.inject.Inject;

@ContextSingleton
public class MainPresenter implements IMainPresenter{


	private IMainActivity mainActivity;



	@Inject
	public MainPresenter(final Activity activity) {
		mainActivity = (IMainActivity) activity;
		Log.d("Activity injec", "testee");
	}


	@Override
	public void optionSelected(String option) {
		mainActivity .rendereFragment(option);
		//mainActivity.direct(Radio.class);

	}

}
 