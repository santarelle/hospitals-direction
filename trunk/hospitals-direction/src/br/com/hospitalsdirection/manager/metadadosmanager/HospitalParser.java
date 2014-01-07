package br.com.hospitalsdirection.manager.metadadosmanager;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

public class HospitalParser {
	private HospitalsPublic hospitalsPublic;

	public List<Hospital> parse(String resultado) {
		hospitalsPublic = new HospitalsPublic();
		List<Hospital> hospitals = new ArrayList<Hospital>();
		try {

			final JSONObject json = new JSONObject(resultado);
			final JSONArray jsonHospital = json.getJSONArray("results");
			int numberHospital = jsonHospital.length();

			for (int i = 0; i < numberHospital; i++) {
				Hospital hospital = new Hospital();
				final JSONObject hospitalObject = jsonHospital.getJSONObject(i);
				hospital.setNome(hospitalObject.getString("name"));
				final JSONObject locaction = hospitalObject.getJSONObject("geometry").getJSONObject("location");
				hospital.setLatitude(locaction.getDouble("lat"));
				hospital.setLongitude(locaction.getDouble("lng"));
				if(containsString(hospital.getNome())){
					hospitals.add(hospital);
				}
			}


		} catch (JSONException e) {
			Log.e(e.getMessage(), "Google JSON Parser - ");
		}
		return hospitals;
	}


	public boolean containsString(String hospital){
		boolean contain = false;
		List<String> hospitalsPublic = this.hospitalsPublic.getHospitaisPublic();
		for (String string : hospitalsPublic) {
			if(string.equals(hospital)){
				contain=true;
				break;
			}
		}
		return contain;
	}
}
