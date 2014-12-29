package com.coderdream.weibo.util;

import java.util.List;

import weibo4j.Timeline;
import weibo4j.Users;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.User;
import weibo4j.model.WeiboException;

public class WbTest {

	private static final String access_token = "2.00Sod8nF0v7VPWb1f07e2cedO_cV8C";
	private static final String uid = "5311292514";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		showUser();
		// updateStatus();
		// getFriendsTimeline();
	}

	/**
	 * 获取用户最新的20条微博 GetFriendsTimeline.java
	 */
	public static void getFriendsTimeline() {
		Timeline tm = new Timeline(access_token);
		try {
			StatusWapper statusList = tm.getFriendsTimeline();
			// Log.logInfo(statusList.toString());
			List<Status> statuses = statusList.getStatuses();
			System.out.println("#####");
			for (Status status : statuses) {
				System.out.println(status.getUser().getName() + ":"
						+ status.getText());
			}
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取当前登录用户信息 ShowUser.java
	 */
	public static void showUser() {
		Users um = new Users(access_token);
		try {
			User user = um.showUserById(uid);

			System.out.println(user.getId());
			System.out.println(user.getName());
			System.out.println(user.getFriendsCount());
			System.out.println(user.getProfileImageURL().toString());
			// Log.logInfo(user.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 写一条微博 UpdateStatus
	 */
	public static void updateStatus() {
		String statuses = "再一次发布一条通过代码发送的微博！";
		Timeline tm = new Timeline(access_token);
		try {
			Status status = tm.updateStatus(statuses);
			// Log.logInfo(status.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}

	}

}