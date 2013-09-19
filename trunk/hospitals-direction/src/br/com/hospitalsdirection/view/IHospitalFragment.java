package br.com.hospitalsdirection.view;

import com.google.android.gms.maps.GoogleMap;
import com.google.inject.ImplementedBy;

@ImplementedBy(HospitalFragment.class)
public interface IHospitalFragment {

	public GoogleMap getMap();
}
