package br.com.hospitalsdirection.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import br.com.hospitalsdirection.view.ConfigurationView;
import br.com.hospitalsdirection.view.R;
import br.com.hospitalsdirection.view.ConfigurationFragment;

import com.google.inject.Inject;

public class ConfigurationPresenterImpl implements ConfigurationPresenter {

	
	private SharedPreferences shrPreference;
	private EditText edtRadius;
	private CheckBox checkBox;
	private Context context;
	private Activity activity;
	@Inject
	public ConfigurationPresenterImpl(Activity activity ,Context context){
		this.context = context;
		this.activity = activity;
		Log.d("fragmente inject", "presenter");
	} 
	
	public void populate(View view){
		shrPreference = PreferenceManager.getDefaultSharedPreferences(context);
		edtRadius = (EditText) view.findViewById(R.id.edtRadiusInput);
		checkBox = (CheckBox) view.findViewById(R.id.checkPublico);
		checkBox.setChecked(shrPreference.getBoolean("public", false));
		edtRadius.setText(shrPreference.getFloat("radius",5)+"");
	}
	@Override
	public void configure() {
		Float km = Float.parseFloat(edtRadius.getText().toString());
		if(km<=50){
			shrPreference.edit().putFloat("radius",km).commit();
			shrPreference.edit().putBoolean("public",checkBox.isChecked()).commit();
			activity.onBackPressed();
		}else{
			Toast.makeText(context, "Distância não pode ser acima de 50 km", 5).show();
		}
		
	}

}
