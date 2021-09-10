package com.logique.desafio.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class EncurtadorURL {

	public static String convertToShortUrl(String urlToShort) {

		URL url;
		HttpURLConnection conn;
		BufferedReader br = null;
		String line = "";
		try {
			url = new URL("https://is.gd/create.php?format=simple&url=" + urlToShort);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			if ((line = br.readLine()) != null) {
				return line;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return line;
	}
	
	public static boolean urlValida(String url) {
		try {
			(new java.net.URL(url)).openStream().close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}
