package com.youtube.rest.status;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.*;
import com.youtube.dao.*;

@Path("/v1/status") //route to java class
public class V1_status {

	// version of api
	private static final String apiVersion = "00.02.00";
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>Java Web Service</p> ";
	}
	
	@Path("/version") //route to specific method
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>Version</p>" + apiVersion;
	}
	
	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws Exception {
		PreparedStatement ps = null;
		String resultString = null;
		String retString = null;
		Connection conn = null;
		
		try {
			conn = OracleRestDataSource.OracleRestConn().getConnection();
			ps = conn.prepareStatement("select to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS') DATETIME from sys.dual");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				resultString = rs.getString(1);
			}
			ps.close();
			rs.close();
			retString = "<p> Database Status</p>" + 
						"<p> Database Date/Time returns: " + resultString + "</p>";
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) conn.close();
		}
		return retString;
	}
	
	
	
}
