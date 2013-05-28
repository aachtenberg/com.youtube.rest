package com.youtube.rest.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.youtube.dao.OracleRestDataSource;
import com.youtube.util.ToJson;

@Path("/v1/inventory")
public class V1_inventory {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String returnAllPcParts() throws Exception {
	//public Response returnAllPcParts() throws Exception {

		PreparedStatement query = null;
		Connection conn = null;
		String returnString = null;
		Response rb = null;	

		try {
			conn = OracleRestDataSource.OracleRestConn().getConnection();
			query = conn.prepareStatement("select * from pc_parts");

			ResultSet rs = query.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            /*
			while (rs.next()) {
				int o = 0;
			}
*/
			ToJson converter = new ToJson();
			JSONArray json = new JSONArray();

			json = converter.toJSONArray(rsmd, rs);
			query.close(); //close connection

			returnString = json.toString();
			rb = Response.ok(returnString).build();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			 if (conn != null) conn.close();
		}
		return  returnString;
		//return rb;
	}

}

