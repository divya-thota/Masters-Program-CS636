package cs636.vinylstation.dao;

import cs636.vinylstation.domain.loginObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import cs636.vinylstation.config.dbConfig;
import cs636.vinylstation.service.loginService;

public class loginDAO {

	private Connection conn;

	public loginDAO(Connection a) {

		conn = a;

	}

	public String loginCheck(loginObject u) throws SQLException {

		String username = u.getUsername();
		String password = u.getPass();
		String role = "";

		try {

			Statement stmt = conn.createStatement();
			String query = "select * from CUSTOMER where email='" + username + "'  and password= '" + password + "'";
			ResultSet rset = stmt.executeQuery(query);

			if (rset.next()) {
				System.out.println("USERNAME and PASS Exist");
				role = "customer" + rset.getString("customer_id");
			} else {
				String query_emp = "select * from EMPLOYEE where email='" + username + "'  and password= '" + password
						+ "'";
				ResultSet rset_emp = stmt.executeQuery(query_emp);

				if (rset_emp.next()) {
					System.out.println("USERNAME and PASS Exist");
					role = "employee" + rset_emp.getString("employee_id");
				} else {
					String query_band = "select * from BAND where name='" + username + "'  and password= '" + password
							+ "'";
					ResultSet rset_band = stmt.executeQuery(query_band);

					if (rset_band.next()) {
						System.out.println("USERNAME and PASS Exist");
						role = "band" + rset_band.getString("band_id");
					} else {
						System.out.println("USERNAME or PASS does not Exist");
						role = "none";
					}
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return role;

	}
}
