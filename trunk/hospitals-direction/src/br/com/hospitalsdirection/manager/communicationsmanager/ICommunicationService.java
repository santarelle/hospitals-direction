package br.com.hospitalsdirection.manager.communicationsmanager;

import java.util.List;

import br.com.hospitalsdirection.manager.metadadosmanager.Hospital;
import br.com.hospitalsdirection.manager.metadadosmanager.Rota;

import com.google.inject.ImplementedBy;

@ImplementedBy(CommunicationService.class)
public interface ICommunicationService {
	
	
	public String lugaresProximos();
	public List<Hospital> hospitaisProximos(Double latitude, Double longitude);
	public Rota getRota(final String start, final String dest);
}
