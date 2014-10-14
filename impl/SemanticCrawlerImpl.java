package impl;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.OWL;

import crawler.SemanticCrawler;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.HashSet;
import java.util.Set;

public class SemanticCrawlerImpl implements SemanticCrawler {
	Set<RDFNode> visited = new HashSet<RDFNode>();
	
	public void search(Model graph, String resourceURI) {
		graph.read(resourceURI);
		
		System.out.println("Following instances with owl:sameAs property");
		StmtIterator statements = graph.listStatements((Resource) null, OWL.sameAs, (RDFNode) null);
		
		while ( statements.hasNext() ) {
			rdfDFS( statements.next().getObject(), visited, "");	
		}
		
		System.out.println("------ VISITADOS ("+ visited.size() +") ------");
		
		for (RDFNode node : visited) {
			System.out.println(node);
		}
	}
	
	public static void rdfDFS( RDFNode node, Set<RDFNode> visited, String prefix) {
        if ( !visited.contains( node )) {
            visited.add( node );
            
			if ( node.isResource() ) {
	            StmtIterator stmts = node.asResource().listProperties( OWL.sameAs );
	            while ( stmts.hasNext() ) {
	                Statement stmt = stmts.next();
	                rdfDFS( stmt.getObject(), visited, node + " =[" + stmt.getPredicate() + "]=> " );
	            }
            }
        }
    }
}


