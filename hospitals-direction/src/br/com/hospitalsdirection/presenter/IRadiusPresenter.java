package br.com.hospitalsdirection.presenter;

import android.view.View;

import com.google.inject.ImplementedBy;

@ImplementedBy(RadiusPresenter.class)
public interface IRadiusPresenter {
	public void configure();
	public void populate(View view);


}
