package com.coderdream.weibo.ui;

import java.lang.ref.WeakReference;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.coderdream.weibo.R;
import com.coderdream.weibo.util.AuthUtil;

/**
 * 新浪微博OAuth授权页面
 * 
 */
public class WebViewActivity extends Activity implements IWeiboActivity {

	// 进度条对话框
	private ProgressDialog progressDialog;

	// 进度条对话框
	private static final int CLOSE_DIALOG = 1;

	// private final String url = "http://www.sina.com";
	private String url = null;

	private WebView webView;
	
	private MyHandler myHandler = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);

		myHandler = new MyHandler(this, url, webView);

		new Thread() {
			@Override
			public void run() {
				super.run();
				url = AuthUtil.getAuthorizationURL();
				if (url == null) {
					Toast.makeText(WebViewActivity.this, R.string.auth_url_empty, Toast.LENGTH_LONG).show();
					return;
				}
				myHandler.sendEmptyMessage(0);
			}
		}.start();

		init();
		load(url, webView);
	}

	@SuppressLint("SetJavaScriptEnabled")
	public void init() {
		if (null == progressDialog) {
			progressDialog = new ProgressDialog(this);
		}
		progressDialog.setMessage("正在加载...");
		progressDialog.show();

		webView = (WebView) findViewById(R.id.wv_oauth);
		webView.getSettings().setJavaScriptEnabled(true);

		webView.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				load(url, webView);
				// RequestToken gt = null;
				// WBAuthActivity wbaa;
				return true;
			}

		});

		webView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				if (100 == newProgress) {
					myHandler.sendEmptyMessage(CLOSE_DIALOG);
				}

				super.onProgressChanged(view, newProgress);
			}
		});

	}

	public void load(final String url, final WebView view) {
		if (null == url || "".equals(url.trim())) {
			return;
		}

		new Thread() {
			public void run() {
				view.loadUrl(url);
			}
		}.start();
	}

	static class MyHandler extends Handler {
		WeakReference<WebViewActivity> mActivity;

		private String url;

		private WebView webView;

		MyHandler(WebViewActivity activity, String url, WebView webView) {
			this.url = url;
			this.webView = webView;
			mActivity = new WeakReference<WebViewActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			WebViewActivity webViewActivity = mActivity.get();
			switch (msg.what) {
			case 0:
				webViewActivity.load(url, webView);
				break;
			}
		};
	}

	@Override
	public void refresh(Object... params) {
		progressDialog.setMessage(getString(R.string.loading));
		url = (String) params[0];
		Log.i("WebViewActivity", "url" + url);
		load(url, webView);

	};

}