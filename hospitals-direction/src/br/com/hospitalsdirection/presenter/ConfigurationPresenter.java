package br.com.hospitalsdirection.presenter;

import android.view.View;

import com.google.inject.ImplementedBy;

@ImplementedBy(ConfigurationPresenterImpl.class)
public interface ConfigurationPresenter {
	public void configure();
	public void populate(View view);


}
