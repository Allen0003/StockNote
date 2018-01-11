package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.properties.Stock;
import com.util.Const;
import com.util.StockRetirUtil;

public class StockDao {

	Connection conn;

	public StockDao(Connection conn) {
		this.conn = conn;
	}

	public Stock getStockByNum(int num) throws Exception {
		String sql = "select * from Stock.stock where num = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		ResultSet rs = pstmt.executeQuery();
		Stock stock = null;
		while (rs.next()) {
			stock = new Stock();
			setStock(rs, stock);
		}
		return stock;
	}

	public ArrayList<Stock> getAllStocks() throws Exception {
		String sql = "select * from Stock.stock";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		ArrayList<Stock> stocks = new ArrayList<Stock>();
		Stock stock = null;
		while (rs.next()) {
			stock = new Stock();
			setStock(rs, stock);
			stocks.add(stock);
		}
		return stocks;
	}

	public boolean updStock(Stock stock) throws Exception {
		String sql = "update Stock.stock set buyDate = ?, sellDate = ?, costs = ?, "
				+ "sellPrice = ?, comment = ? where num = ?, revenue = ?, percentage = ? ";
		if (!stock.getSellDate().equals("")) {
			double revenue = (stock.getSellPrice() - stock.getCosts()) * 1000;
			double percentage = revenue / (stock.getCosts() * 1000);
			stock.setRevenue(revenue);
			stock.setPercentage(percentage);
		}
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// TODO i am lazy now
		// pstmt.setString(1, sysUser.getAUTH());
		// pstmt.setString(2, sysUser.getUid());

		return pstmt.executeUpdate() == 1;
	}

	public boolean addStock(Stock stock) throws Exception {
		String sql = "insert into Stock.stock (num, name, buyDate, sellDate, costs, sellPrice, "
				+ "revenue, percentage, comment, user) values (?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		int i = 1;
		pstmt.setInt(++i, stock.getNum());
		pstmt.setString(++i, StockRetirUtil.getNameByNum(stock.getNum()));
		pstmt.setString(++i, stock.getBuyDate());
		pstmt.setString(++i, stock.getSellDate());
		pstmt.setDouble(++i, stock.getCosts());
		pstmt.setDouble(++i, stock.getSellPrice());
		pstmt.setDouble(++i, stock.getRevenue());
		pstmt.setDouble(++i, stock.getPercentage());
		pstmt.setString(++i, stock.getComment());
		pstmt.setString(++i, Const.User);
		return pstmt.executeUpdate() == 1;
	}

	public boolean delStock(int num) throws Exception {
		String sql = "delete Stock.stock where num = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setDouble(1, num);
		return pstmt.executeUpdate() == 1;
	}

	private void setStock(ResultSet rs, Stock stock) throws Exception {
		stock.setNum(rs.getInt("num"));
		stock.setName(rs.getString("name"));
		stock.setBuyDate(rs.getString("buyDate"));
		stock.setSellDate(rs.getString("sellDate"));
		stock.setCosts(rs.getDouble("costs"));
		stock.setSellPrice(rs.getDouble("sellPrice"));
		stock.setRevenue(rs.getFloat("revenue"));
		stock.setPercentage(rs.getFloat("percentage"));
		stock.setComment(rs.getString("comment"));
		stock.setUser(rs.getString("user"));
	}

}
