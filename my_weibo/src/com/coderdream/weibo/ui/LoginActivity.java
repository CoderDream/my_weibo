package com.coderdream.weibo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.coderdream.weibo.R;

public class LoginActivity extends Activity implements IWeiboActivity {

	private Button btnLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// // 取消标题栏
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		// // 取消状态栏
		// this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.login);

		// 跳转到认证页面
		Intent intent = new Intent(this, AuthActivity.class);
		startActivity(intent);

		// 启动服务
		// Intent intent = new Intent(this, MainService.class);
		// startService(intent);
		//
		// // 登录按钮
		// btnLogin = (Button) this.findViewById(R.id.btn_login);
		// btnLogin.setOnClickListener(new OnClickListener() {
		// public void onClick(View v) {
		// Task task = new Task(Task.WEIBO_LOGIN, null);
		// MainService.newTask(task);
		// }
		// });
		//
		// // 把自己放到Activity集合里面
		// MainService.addActivity(this);
	}

	public void init() {

	}

	public void refresh(Object... params) {
		TextView txtResult = (TextView) this.findViewById(R.id.txt_result);
		txtResult.setText(params[0].toString());
	}

}