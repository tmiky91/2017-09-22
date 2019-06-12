package it.polito.tdp.formulaone.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.formulaone.model.PartecipazioneGare;
import it.polito.tdp.formulaone.model.Race;
import it.polito.tdp.formulaone.model.Season;

public class FormulaOneDAO {

	public List<Season> getAllSeasons() {
		String sql = "SELECT year, url FROM seasons ORDER BY year";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			List<Season> list = new ArrayList<>();
			while (rs.next()) {
				list.add(new Season(rs.getInt("year"), rs.getString("url")));
			}
			conn.close();
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Race> getAllRaces(Map<Integer, Race> idMap) {
		final String sql=	"select * " + 
							"from races";
		List<Race> result = new LinkedList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				if(!idMap.containsKey(rs.getInt("raceId"))) {
					Race race = new Race(rs.getInt("raceId"), rs.getString("name"));
					result.add(race);
					idMap.put(rs.getInt("raceId"), race);
				}else {
					result.add(idMap.get(rs.getInt("raceId")));
				}
				
			}
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<PartecipazioneGare> getPartecipazioneGare(Map<Integer, Race> idMap, Season s) {
		final String sql=	"select r1.raceId as id1, r2.raceId as id2, count(*) as cnt " + 
							"from results as r1, results as r2, races as rr1, races as rr2 " + 
							"where r1.raceId > r2.raceId " + 
							"and r1.statusId=1 " + 
							"and r2.statusId=1 " + 
							"and rr1.raceId=r1.raceId " + 
							"and rr2.raceId=r2.raceId " + 
							"and r1.driverId=r2.driverId " + 
							"and rr1.year=? " + 
							"and rr1.year=rr2.year " + 
							"group by id1, id2";
		List<PartecipazioneGare> result = new LinkedList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, s.getYear());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Race r1 = idMap.get(rs.getInt("id1"));
				Race r2 = idMap.get(rs.getInt("id2"));
				if(r1!=null && r2!=null) {
					PartecipazioneGare pg = new PartecipazioneGare(r1, r2, rs.getDouble("cnt"));
					result.add(pg);
				}
				
			}
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
