package com.coderdream.weibo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import weibo4j.Oauth;
import weibo4j.model.WeiboException;
import weibo4j.util.BareBonesBrowserLaunch;

/**
 * <pre>
 *  4dfa566038e60947c886046201560f25
 * 
 * AccessToken [accessToken=2.00Sod8nF0v7VPWb1f07e2cedO_cV8C, expireIn=157679999, refreshToken=,uid=5311292514]
 * </pre>
 */
public class OAuth4Code {
	public static void main(String[] args) throws WeiboException, IOException {
		Oauth oauth = new Oauth();
		BareBonesBrowserLaunch.openURL(oauth.authorize("code"));
		System.out.println(oauth.authorize("code"));
		System.out.print("Hit enter when it's done.[Enter]:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String code = br.readLine();
		Log.logInfo("code: " + code);
		try {
			System.out.println(oauth.getAccessTokenByCode(code));
		} catch (WeiboException e) {
			if (401 == e.getStatusCode()) {
				Log.logInfo("Unable to get the access token.");
			} else {
				e.printStackTrace();
			}
		}
	}
}
