package impl;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.OWL;

import crawler.SemanticCrawler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SemanticCrawlerImpl implements SemanticCrawler {
	ArrayList<String> visited = new ArrayList<String>();
	
	public void search(Model graph, String resourceURI) {
		graph.read(resourceURI);
		
		System.out.println("Following instances with owl:sameAs property");
		StmtIterator statements = graph.listStatements((Resource) null, OWL.sameAs, (RDFNode) null);
		
		while ( statements.hasNext() ) {
			System.out.println("OUTRO STATEMENT");
			rdfDFS(graph, statements.nextStatement().getObject(), visited);
		}
		
		System.out.println("------ VISITADOS ("+ visited.size() +") ------");
		
		for (String uri : visited) {
			System.out.println(uri);
		}
	}
	
	public static void rdfDFS(Model graph, RDFNode node, ArrayList<String> visited) {	
		String newURI = node.asResource().getURI();
		
        if ( visited.contains( newURI ) ) {
        	return;
        } else {
			if ( node.isResource() ) {
				visited.add(newURI);
				
				try {
					Model model = ModelFactory.createDefaultModel();
					model.read(newURI);
					
					StmtIterator stmts = model.listStatements((Resource) node, OWL.sameAs, (RDFNode) null);
					
					while ( stmts.hasNext() ) {
						Statement statement = stmts.nextStatement();
						Resource subject = statement.getSubject();
						
						Resource object  = (Resource) statement.getObject();
						
						System.out.println("SEGUINDO -> "+ object);
							
						rdfDFS(model, subject, visited);
						rdfDFS(model, object, visited);
					}
				} catch (Exception e) {
					return;
				}
				
            }
        }
    }
}


