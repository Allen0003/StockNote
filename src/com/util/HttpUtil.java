package com.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class HttpUtil {
	static public String getDataFromUrl(String url_i) throws Exception {
		String result = "";
		URL url = new URL(url_i);
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
		String line = "";
		while ((line = reader.readLine()) != null) {
			result += line;
		}
		return result;
	}
}
