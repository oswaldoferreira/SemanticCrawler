package impl;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
//import com.hp.hpl.jena.rdf.model.ModelFactory;

import crawler.SemanticCrawler;

public class SemanticCrawlerImpl implements SemanticCrawler {
		
	public void search(Model graph, String resourceURI) {
		graph.read(resourceURI);
		
		// Create a new query
		String queryString = 
			"SELECT ?url ?property ?object" +
			"WHERE {" +
			"      <"+ resourceURI +"> "+ " ?property ?object . " + 
			"      }";
	    
	    Query query = QueryFactory.create(queryString);

	    System.out.println("----------------------");

	    System.out.println("Query Result Sheet");

	    System.out.println("----------------------");
	   
	    // Execute the query and obtain results
	    QueryExecution qe = QueryExecutionFactory.create(query, graph);
	    com.hp.hpl.jena.query.ResultSet results =  qe.execSelect();

	    // Output query results    
	    ResultSetFormatter.out(System.out, results, query);

	    qe.close();
	}
}


