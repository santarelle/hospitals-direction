package br.com.hospitalsdirection.view;

import android.view.View;

import com.google.inject.ImplementedBy;

@ImplementedBy(RadiusFragment.class)
public interface IRadiusFragment {
	public View getView();
}
