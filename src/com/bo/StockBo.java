package com.bo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import com.dao.StockDao;
import com.properties.Stock;
import com.util.Const;

public class StockBo {
	public Connection conn;

	StockDao stockDao = null;

	public StockBo() throws Exception {
		Class.forName(Const.sqlDriver);
		conn = DriverManager.getConnection(Const.sqlUrl, Const.sqlUsername, Const.sqlPassword);
	}

	public ArrayList<Stock> getAllStocks() throws Exception {
		if (stockDao == null) {
			stockDao = new StockDao(conn);
		}
		return stockDao.getAllStocks();
	}

	public void disconnect() throws Exception {
		if (conn != null) {
			conn.close();
		}
	}

	public void finalize() throws Exception {
		disconnect();
	}

}
