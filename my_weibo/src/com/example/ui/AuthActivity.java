package com.example.ui;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import com.example.R;

public class AuthActivity extends Activity {

	private Dialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.auth);

		View dialogView = View.inflate(this, R.layout.authorize_dialog, null);

		dialog = new Dialog(this, R.style.auth_dialog);
		dialog.setContentView(dialogView);
		dialog.show();
	}

}