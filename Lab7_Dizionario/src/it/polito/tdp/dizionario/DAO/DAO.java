package it.polito.tdp.dizionario.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {

	public List<String> trovaParole(int i) {

		final String sql = "SELECT nome FROM parola WHERE length(nome) = ?";
		List<String> paroleTrovate = new ArrayList<String>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, i);
			ResultSet rs = st.executeQuery();
			while (rs.next())
				paroleTrovate.add(rs.getString("nome"));
			return paroleTrovate;
		} catch (SQLException e) {
			throw new RuntimeException("Errore Db");
		}
	}
}
