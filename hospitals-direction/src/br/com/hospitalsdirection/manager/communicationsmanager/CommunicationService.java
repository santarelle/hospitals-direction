package br.com.hospitalsdirection.manager.communicationsmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import roboguice.inject.ContextSingleton;
import android.util.Log;
import br.com.hospitalsdirection.manager.contextmanager.LocationService;
import br.com.hospitalsdirection.manager.metadadosmanager.Hospital;
import br.com.hospitalsdirection.manager.metadadosmanager.HospitalParser;
import br.com.hospitalsdirection.manager.metadadosmanager.Route;
import br.com.hospitalsdirection.manager.metadadosmanager.RouteParser;

import com.google.inject.Inject;

@ContextSingleton
public class CommunicationService implements ICommunicationService {

	
	@Inject
	LocationService locationService;
	
	@Inject
	RouteParser rotaParser;
	
	@Inject
	HospitalParser hospitalParser;
	
	private URL url;
	
	@Override
	public String lugaresProximos() {
		Log.d("teste","tesassa");
		locationService.gpsAtivo(); 
		return "lugares proximos";
	}
	
	public List<Hospital> hospitaisProximos(Double latitude, Double longitude, float raioM){
		List<Hospital> hospitals=null;
		String jsonURL = "https://maps.googleapis.com/maps/api/place/search/json?";
		final StringBuffer sBuf = new StringBuffer(jsonURL);
		sBuf.append("location=");
		sBuf.append(latitude+","+longitude);
		sBuf.append("&radius=");
		sBuf.append(raioM);
		sBuf.append("&sensor=true&types=hospital&key=AIzaSyDwKjR1P5IhWok9reBBIGZQfZqpLQr3ZmE");
		try {
			url= new URL(sBuf.toString());  
			hospitals=hospitalParser.parse(converteStreamToString(url.openConnection().getInputStream()));
		} catch (IOException e) {
			Log.e(e.getMessage(), "Google JSON Parser - ");
		}
		return hospitals;
	}
	
	public Route getRota(final String start, final String dest) {
		Route rota = null;
		String jsonURL = "http://maps.googleapis.com/maps/api/directions/json?";

    	final StringBuilder sBuf = new StringBuilder(jsonURL);

		sBuf.append("origin=");
		sBuf.append(start);
		sBuf.append("&destination=");
		sBuf.append(dest);
		sBuf.append("&sensor=false&mode=driving");

		try {
			url= new URL(sBuf.toString());  
			rota =rotaParser.parse(converteStreamToString(url.openConnection().getInputStream()));
		} catch (IOException e) {
			Log.e(e.getMessage(), "Google JSON Parser - ");
		}

		return rota;
	}
	
	  private static String converteStreamToString(final InputStream input) {
	    	
	    	final BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	    	final StringBuilder sBuf = new StringBuilder();

	    	String line = null;
	    	try {
	    		while ((line = reader.readLine()) != null) {
	    			sBuf.append(line);
	    		}
	    	} catch (IOException e) {
	    		Log.e(e.getMessage(), "Google parser");
	    	} finally {
	    		try {
	    			input.close();
	    		} catch (IOException e) {
	    			Log.e(e.getMessage(), "Google parser");
	    		}
	    	}
	    	return sBuf.toString();
	    }
	
	
	

}
