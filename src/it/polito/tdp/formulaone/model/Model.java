package it.polito.tdp.formulaone.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.formulaone.db.FormulaOneDAO;

public class Model {
	
	private Map<Integer, Race> idMap;
	private SimpleWeightedGraph<Race, DefaultWeightedEdge> grafo;
	
	public Model() {
		idMap = new HashMap<>();
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	}

	public List<Season> getAllSeasons() {
		FormulaOneDAO dao = new FormulaOneDAO();
		return dao.getAllSeasons();
	}

	public String getArcoPesoMax(Season s) {
		String risultato="";
		FormulaOneDAO dao = new FormulaOneDAO();
		dao.getAllRaces(idMap);
		List<PartecipazioneGare> partecipazione = dao.getPartecipazioneGare(idMap, s);
		for(PartecipazioneGare pg: partecipazione) {
			if(!grafo.containsVertex(pg.getR1())) {
				grafo.addVertex(pg.getR1());
			}
			if(!grafo.containsVertex(pg.getR2())) {
				grafo.addVertex(pg.getR2());
			}
			DefaultWeightedEdge edge = grafo.getEdge(pg.getR1(), pg.getR2());
			if(edge==null) {
				Graphs.addEdgeWithVertices(grafo, pg.getR1(), pg.getR2(), pg.getPeso());
			}
		}
		double max=0;
		for(DefaultWeightedEdge edge: grafo.edgeSet()) {
			double pesoArco = grafo.getEdgeWeight(edge);
			if(pesoArco>max) {
				max=pesoArco;
				risultato="Peso arco massimo: "+max+" Gara1: "+grafo.getEdgeSource(edge).getName()+" Gara2: "+grafo.getEdgeTarget(edge).getName()+"\n";
			}
		}
		return risultato;
	}


}
