package com.example.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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

		Button btnBegin = (Button) dialogView.findViewById(R.id.btn_auth_begin);
		btnBegin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AuthActivity.this,
						WebViewActivity.class);
				startActivity(intent);
			}
		});

	}
}