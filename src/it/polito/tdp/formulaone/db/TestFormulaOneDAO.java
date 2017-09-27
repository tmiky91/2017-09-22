package it.polito.tdp.formulaone.db;

import java.util.List;

import it.polito.tdp.formulaone.model.Season;

public class TestFormulaOneDAO {

	public static void main(String[] args) {

		FormulaOneDAO dao = new FormulaOneDAO();

		List<Season> seasons = dao.getAllSeasons();
		System.out.println(seasons);

	}

}
