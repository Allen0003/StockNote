package com.util;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class StockRetirUtil {

	public static void main(String[] args) {

		ArrayList<Integer> inputs = new ArrayList<Integer>();
		inputs.add(2884);
		inputs.add(2324);

		try {

			for (int num : inputs) {
				System.out.println(StockRetirUtil.getNameByNum(num));
			}

			// System.out.println(StockRetirUtil.getNameByNum(2324));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static public String getNameByNum(int id) throws Exception {
		Document doc = Jsoup.parse(HttpUtil.getDataFromUrl(Const.StockAPIUrl + String.valueOf(id)));
		String title = doc.title();
		int lastIndex = title.indexOf("(" + String.valueOf(id));
		return title.subSequence(0, lastIndex).toString().replace(Const.UnwantedCharactersTitle, "");
	}

}
