package br.com.hospitalsdirection.presenter;

import com.google.inject.ImplementedBy;

@ImplementedBy(MainPresenter.class)
public interface IMainPresenter {

	public void optionSelected(Integer option);
	
}
