package br.com.hospitalsdirection.presenter;

import com.google.inject.ImplementedBy;

@ImplementedBy(HospitalPresenterImpl.class)
public interface HospitalPresenter {
	public void populate();
}
