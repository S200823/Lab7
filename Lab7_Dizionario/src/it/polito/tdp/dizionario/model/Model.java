package it.polito.tdp.dizionario.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.dizionario.DAO.DAO;

public class Model {

	DAO dao;
	UndirectedGraph<String, DefaultEdge> grafo;

	public Model() {
		dao = new DAO();
	}

	public List<String> trovaParole(int i) {
		return dao.trovaParole(i);
	}
	
	public List<String> creaGrafo(int i) {
		List<String> parole = dao.trovaParole(i);
		grafo = new SimpleGraph<>(DefaultEdge.class);
		for (String s : parole)
			grafo.addVertex(s);
		for (String s1 : parole) {
			for (String s2 : parole) {
				if (differisceDiUnaSolaLettera(s1, s2) == 1)
					grafo.addEdge(s1, s2);
			}
		}
		return parole;
	}
	
	public List<String> trovaVicini(String parola) {
		List<String> vicini = Graphs.neighborListOf(grafo, parola);
		return vicini;
	}
	
	public List<String> trovaConnessi(String parola) {
		List<String> connessi = new ArrayList<String>();
		GraphIterator<String, DefaultEdge> dfv = new BreadthFirstIterator<String, DefaultEdge>(grafo, parola);
		dfv.next();
		while(dfv.hasNext()){
			String s = dfv.next();
			connessi.add(s);
		}
		return connessi;
	}

	private int differisceDiUnaSolaLettera(String s1, String s2) {
		int conta = 0;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i))
				conta++;
		}
		return conta;
	}	
}
