package br.com.hospitalsdirection.presenter;

import com.google.inject.ImplementedBy;

@ImplementedBy(MainPresenterImpl.class)
public interface MainPresenter {

	public void optionSelected(String option);
	
}
