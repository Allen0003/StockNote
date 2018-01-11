package com.action;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.bo.StockBo;
import com.google.gson.Gson;
import com.properties.Stock;

@Path("/getAllStocks")
public class GetData {

	@GET
	@Produces("application/json")
	public Response convertCtoF() {
		ArrayList<Stock> result = null;
		StockBo bo = null;
		try {
			bo = new StockBo();
			result = bo.getAllStocks();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (bo != null) {
				try {
					bo.disconnect();
				} catch (Exception e) {

				}
			}
		}
		return Response.status(200).entity(new Gson().toJson(result)).build();
	}
}
