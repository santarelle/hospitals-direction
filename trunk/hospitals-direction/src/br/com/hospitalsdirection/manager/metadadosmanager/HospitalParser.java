package br.com.hospitalsdirection.manager.metadadosmanager;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

public class HospitalParser {

	
	  public List<Hospital> parse(String resultado) {
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
                          hospitals.add(hospital);
                  }
                  
                  
          } catch (JSONException e) {
                  Log.e(e.getMessage(), "Google JSON Parser - ");
          }
          return hospitals;
  }

}
