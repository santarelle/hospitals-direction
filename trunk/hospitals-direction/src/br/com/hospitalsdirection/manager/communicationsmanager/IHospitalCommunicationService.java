package br.com.hospitalsdirection.manager.communicationsmanager;

import com.google.inject.ImplementedBy;

@ImplementedBy(HospitalCommunicationService.class)
public interface IHospitalCommunicationService {
	
	
	public void hospitaisProximos(Double latitude, Double longitude, float raioM, String nextToken);

}
