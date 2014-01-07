package br.com.hospitalsdirection.manager.communicationsmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import roboguice.inject.ContextSingleton;
import android.content.Context;
import android.util.Log;
import br.com.hospitalsdirection.manager.contextmanager.LocationService;
import br.com.hospitalsdirection.manager.metadadosmanager.Hospital;
import br.com.hospitalsdirection.manager.metadadosmanager.HospitalParser;
import br.com.hospitalsdirection.manager.metadadosmanager.Route;
import br.com.hospitalsdirection.manager.metadadosmanager.RouteParser;

import com.google.inject.Inject;

@ContextSingleton
public abstract class HospitalCommunicationService extends ServiceRequest<Hospital> implements IHospitalCommunicationService {


	@Inject
	LocationService locationService;




	public HospitalCommunicationService(final Context context) {
		setContext(context);
		setEntryTime(0);
	}

	public void hospitaisProximos(Double latitude, Double longitude, float raioM){
		String jsonURL = "https://maps.googleapis.com/maps/api/place/search/json?";
		final StringBuffer sBuf = new StringBuffer(jsonURL);
		sBuf.append("location=");
		sBuf.append(latitude+","+longitude);
		sBuf.append("&radius=");  
		sBuf.append(raioM);
		sBuf.append("&sensor=true&types=hospital&key=AIzaSyDwKjR1P5IhWok9reBBIGZQfZqpLQr3ZmE");
		setNameService(sBuf.toString());
		request(getContext(), "");
	}

	
	@Override
	protected List<Hospital> getResult(JSONObject response) throws JSONException {
		List<Hospital> result = null;
		if(response!=null){
			result = new HospitalParser().parse(response.toString());
		}
		return result;
	}

}