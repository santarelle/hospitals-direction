package br.com.hospitalsdirection.manager.communicationsmanager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.hospitalsdirection.manager.metadadosmanager.Hospital;
import br.com.hospitalsdirection.manager.metadadosmanager.HospitalParser;
import br.com.hospitalsdirection.manager.metadadosmanager.Route;
import br.com.hospitalsdirection.manager.metadadosmanager.RouteParser;
import android.content.Context;
import android.util.Log;

public abstract  class RouteCommunicationServiceImpl extends ServiceRequest<Route> implements RouteCommunicationService{

	
	public RouteCommunicationServiceImpl(final Context context) {
		setContext(context);
		setEntryTime(0);
	}

	

	@Override
	protected List<Route> getResult(JSONObject response) throws JSONException {
		List<Route> result = null;
		if(response!=null){
			result = new ArrayList<Route>();
			result.add( new RouteParser().parse(response.toString()));
		}
		return result;
	}
	
	public void getRota(final String start, final String dest) {
		String jsonURL = "http://maps.googleapis.com/maps/api/directions/json?";
		final StringBuilder sBuf = new StringBuilder(jsonURL);
		sBuf.append("origin=");
		sBuf.append(start);
		sBuf.append("&destination=");
		sBuf.append(dest);
		sBuf.append("&sensor=false&mode=driving");
		setNameService(sBuf.toString());
		request(getContext(), "");
	}

}
