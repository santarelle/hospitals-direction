package br.com.hospitalsdirection.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.com.hospitalsdirection.view.IRadiusFragment;
import br.com.hospitalsdirection.view.R;
import br.com.hospitalsdirection.view.RadiusFragment;

import com.google.inject.Inject;

public class RadiusPresenter implements IRadiusPresenter {

	
	private SharedPreferences shrPreference;
	private EditText edtRadius;
	private Context context;
	private Activity activity;
	@Inject
	public RadiusPresenter(Activity activity ,Context context){
		this.context = context;
		this.activity = activity;
		Log.d("fragmente inject", "presenter");
	} 
	
	public void populate(View view){
		shrPreference = PreferenceManager.getDefaultSharedPreferences(context);
		edtRadius = (EditText) view.findViewById(R.id.edtRadiusInput);
		edtRadius.setText(shrPreference.getFloat("radius",5)+"");
	}
	@Override
	public void configure() {
		Float km = Float.parseFloat(edtRadius.getText().toString());
		if(km<=50){
			shrPreference.edit().putFloat("radius",km).commit();
			activity.onBackPressed();
		}else{
			Toast.makeText(context, "Distância não pode ser acima de 50 km", 5).show();
		}
		
	}

}
