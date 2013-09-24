package br.com.hospitalsdirection.view;

import android.content.Context;

import com.google.inject.ImplementedBy;

@ImplementedBy(MainActivity.class)
public interface IMainActivity {

	public Context getContext();
	
	public void direct (Class<?> direction);
	public void rendereFragment(String viewId);
}
