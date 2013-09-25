package br.com.hospitalsdirection.view;

import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.ContentView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;

import com.google.inject.Inject;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboFragmentActivity implements IMainActivity {

	
	@Inject
	MainFragment mainFragment;
	
	@Inject
	IHospitalFragment hospitalFragment;
	
	@Inject
	RadiusFragment radiusFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
		.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		FragmentManager manager = getSupportFragmentManager();
		manager.beginTransaction().replace(R.id.layout1, mainFragment,"mainFragment").commit();
		
		
	}
 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public Context getContext() {
		return getApplicationContext();
	}

	


	@Override
	public void direct(Class<?> direction) {
        Intent navigation = new Intent(this, direction);
        this.startActivity(navigation);
	}

	@Override
	public void rendereFragment(String viewId) {
		if(viewId == getContext().getString(R.string.hospital_proximos)){
			FragmentManager manager = getSupportFragmentManager();
			manager.beginTransaction().replace(R.id.layout1, (Fragment)hospitalFragment).addToBackStack("replaceFrag2toFrag1").commit();
		}else{
			if(viewId == getContext().getString(R.string.radio)){
				FragmentManager manager = getSupportFragmentManager();
				manager.beginTransaction().replace(R.id.layout1, radiusFragment).addToBackStack("replaceFrag2toFrag1").commit();
			}
		}
	/*	switch (viewId) {
//		case R.id.btHospitalNear:
//				FragmentManager manager = getSupportFragmentManager();
//				manager.beginTransaction().replace(R.id.layout1, hospitalFragment).addToBackStack("replaceFrag2toFrag1").commit();
//			break;

		default:
			break;
		}*/
		
	} 


}
