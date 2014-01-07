package br.com.hospitalsdirection.manager.metadadosmanager;

import java.util.ArrayList;
import java.util.List;

public class HospitalsPublic {
	private static List<String> hospitaisPublic;
	static{
		hospitaisPublic = new ArrayList<String>();
		hospitaisPublic.add("Hospital e Maternidade Chapot Prevost");
		hospitaisPublic.add("Hospital Geral Doutor Geraldo da Rocha");
		hospitaisPublic.add("Instituto de Saúde da Criança do Amazonas - Icam");
		hospitaisPublic.add("Hospital Universitário Getúlio Vargas");
		hospitaisPublic.add("Hospital Universitário Francisca Mendes");
		hospitaisPublic.add("Hospital Infantil Doutor Fajardo");
		hospitaisPublic.add("SPA Joventina Dias");
		hospitaisPublic.add("Spa São Raimundo");
		hospitaisPublic.add("Fundação de Medicina Tropical");
		hospitaisPublic.add("Fundação Hospital Adriano Jorge - FHAJ");
		hospitaisPublic.add("Fundação Dermat Trop Vener Alfredo da Mata");
		hospitaisPublic.add("Fundação de Vigilância em Saúde-FVS");
		hospitaisPublic.add("FCECON- Fundação Centro de Controle de Oncologia");
		hospitaisPublic.add("Pronto Socorro da Criança - Zona Sul");
		hospitaisPublic.add("Pronto Socorro da Crianca - São José");
		hospitaisPublic.add("Hospital Pronto Socorro Dr João Lúcio Pereira Machado");
		hospitaisPublic.add("Pronto Socorro da Criança");
		hospitaisPublic.add("Hospital Pronto Socorro 28 de Agosto");
		hospitaisPublic.add("Hospital Dr. Aristóteles Platão Bezerra de Araújo");
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
