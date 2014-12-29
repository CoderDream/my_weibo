package com.coderdream.weibo.ui;

/***
 * Activity 接口
 */
public interface IWeiboActivity {

	/**
	 * 初始化数据
	 */
	void init();

	/**
	 * 刷新UI
	 */
	void refresh(Object... params);

}