package br.com.hospitalsdirection.presenter;

import com.google.inject.ImplementedBy;

@ImplementedBy(HospitalPresenter.class)
public interface IHospitalPresenter {
	public void populate();
}
