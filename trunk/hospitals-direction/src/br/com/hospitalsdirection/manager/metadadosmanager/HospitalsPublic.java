package br.com.hospitalsdirection.manager.metadadosmanager;

import java.util.ArrayList;
import java.util.List;

public class HospitalsPublic {
	private static List<String> hospitaisPublic;
	static{
		hospitaisPublic = new ArrayList<String>();
		hospitaisPublic.add("Hospital e Maternidade Chapot Prevost");
		hospitaisPublic.add("Hospital Geral Doutor Geraldo da Rocha");
		hospitaisPublic.add("Instituto de Sa�de da Crian�a do Amazonas - Icam");
		hospitaisPublic.add("Hospital Universit�rio Get�lio Vargas");
		hospitaisPublic.add("Hospital Universit�rio Francisca Mendes");
		hospitaisPublic.add("Hospital Infantil Doutor Fajardo");
		hospitaisPublic.add("SPA Joventina Dias");
		hospitaisPublic.add("Spa S�o Raimundo");
		hospitaisPublic.add("Funda��o de Medicina Tropical");
		hospitaisPublic.add("Funda��o Hospital Adriano Jorge - FHAJ");
		hospitaisPublic.add("Funda��o Dermat Trop Vener Alfredo da Mata");
		hospitaisPublic.add("Funda��o de Vigil�ncia em Sa�de-FVS");
		hospitaisPublic.add("FCECON- Funda��o Centro de Controle de Oncologia");
		hospitaisPublic.add("Pronto Socorro da Crian�a - Zona Sul");
		hospitaisPublic.add("Pronto Socorro da Crianca - S�o Jos�");
		hospitaisPublic.add("Hospital Pronto Socorro Dr Jo�o L�cio Pereira Machado");
		hospitaisPublic.add("Pronto Socorro da Crian�a");
		hospitaisPublic.add("Hospital Pronto Socorro 28 de Agosto");
		hospitaisPublic.add("Hospital Dr. Arist�teles Plat�o Bezerra de Ara�jo");
		hospitaisPublic.add("Maternidade Ana Braga");
		hospitaisPublic.add("Maternidade Nazira Daou");
	}
	public static List<String> getHospitaisPublic() {
		return hospitaisPublic;
	}
	public static void setHospitaisPublic(List<String> hospitaisPublic) {
		HospitalsPublic.hospitaisPublic = hospitaisPublic;
	}
	
}
