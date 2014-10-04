package com.example.ui;

import java.lang.ref.WeakReference;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.R;

/**
 * 新浪微博OAuth授权页面
 * 
 */
public class WebViewActivity extends Activity {

	private WebView webView;

	// 进度条对话框
	private ProgressDialog progressDialog;

	// 进度条对话框
	private static final int CLOSE_DIALOG = 1;

	private final String url = "http://www.sina.com";

	MyHandler myHandler = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);

		init();
		load(url, webView);
		myHandler = new MyHandler(this);
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void init() {
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

		MyHandler(WebViewActivity activity) {
			mActivity = new WeakReference<WebViewActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			WebViewActivity webViewActivity = mActivity.get();
			if (CLOSE_DIALOG == msg.what) {
				webViewActivity.progressDialog.dismiss();
			}
		}
	};

}