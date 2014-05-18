package br.com.hospitalsdirection.manager.communicationsmanager;

import com.google.inject.ImplementedBy;

@ImplementedBy(HospitalCommunicationServiceImpl.class)
public interface HospitalCommunicationService {
	
	
	public void hospitaisProximos(Double latitude, Double longitude, float raioM, String nextToken);

}
