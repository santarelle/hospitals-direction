package br.com.hospitalsdirection.manager.metadadosmanager;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

public class RouteParser {


	public Route parse(String resultado) {
		final Route route = new Route();  
	
		try{
			// Transforma a string em JSON  
			JSONObject json = new JSONObject(resultado);  
			// Pega a primeira rota retornada  
			JSONObject jsonRoute =   
					json.getJSONArray("routes")  
					.getJSONObject(0);  
			JSONObject leg = jsonRoute  
					.getJSONArray("legs").getJSONObject(0);  

			// Obtém os passos do caminho  
			JSONArray steps = leg.getJSONArray("steps");  

			final int numSteps = steps.length();  
			/* 
			 * Itera através dos passos, decodificando  
			 * a polyline e adicionando à rota. 
			 */  
			JSONObject step;  
			for (int i = 0; i < numSteps; i++) {  
				// Obtém o passo corrente  
				step = steps.getJSONObject(i);  
				// Decodifica a polyline e adiciona à rota  
				route.addPoints(  
						decodePolyLine(  
								step.getJSONObject("polyline")  
								.getString("points")));  
			}  
		} catch (Exception e) {  
		}  
		
		return route;
	}

	private List<LatLng> decodePolyLine(final String poly) {  
		int len = poly.length();  
		int index = 0;  
		List<LatLng> decoded =   
				new ArrayList<LatLng>();  

		int lat = 0;  
		int lng = 0;  

		while (index < len) {  
			int b;  
			int shift = 0;  
			int result = 0;  
			do {  
				b = poly.charAt(index++) - 63;  
				result |= (b & 0x1f) << shift;  
				shift += 5;  
			} while (b >= 0x20);  
			int dlat =   
					((result & 1) != 0 ?   
							~(result >> 1) :   
								(result >> 1));  
			lat += dlat;  

			shift = 0;  
			result = 0;  
			do {  
				b = poly.charAt(index++) - 63;  
				result |= (b & 0x1f) << shift;  
				shift += 5;  
			} while (b >= 0x20);  

			int dlng = ((result & 1) != 0 ?   
					~(result >> 1) :   
						(result >> 1));  
			lng += dlng;  

			decoded.add(  
					new LatLng(  
							(float)(lat / 1E5),  
							(float)(lng / 1E5)));  
		}  
		return decoded;  
	}  
}
