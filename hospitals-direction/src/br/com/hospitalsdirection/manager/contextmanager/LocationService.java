package br.com.hospitalsdirection.manager.contextmanager;

import roboguice.inject.ContextSingleton;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import com.google.inject.Inject;

@ContextSingleton
public class LocationService  {

	private LocationManager locationManager;
	boolean isGPSEnabled = false;

	// flag for network status
	boolean isNetworkEnabled = false;

	boolean canGetLocation = false;
	// The minimum distance to change Updates in meters
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0; // 10 meters

	// The minimum time between updates in milliseconds
	private static final long MIN_TIME_BW_UPDATES = 0; // 1 minute


	@Inject
	public LocationService(Context context) {
		locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
	}

	public Boolean gpsAtivo(){
		return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
	}

	public Location getLocation(LocationListener locationListener){
		Location location = null;
		String provider = null;
		if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
			provider = LocationManager.GPS_PROVIDER;
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0, locationListener);
		} 
		else{
			if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
				locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0,0, locationListener);
				provider = LocationManager.NETWORK_PROVIDER;
			}
		}
		return locationManager.getLastKnownLocation(provider);
	}


}