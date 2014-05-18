package br.com.hospitalsdirection.view;

import android.view.View;

import com.google.inject.ImplementedBy;

@ImplementedBy(ConfigurationFragment.class)
public interface ConfigurationView {
	public View getView();
}
