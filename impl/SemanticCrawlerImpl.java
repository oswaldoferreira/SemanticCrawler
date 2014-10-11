package impl;

import com.github.jsonldjava.core.RDFDataset.Literal;
import com.hp.hpl.jena.query.ParameterizedSparqlString;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSetFactory;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
//import com.hp.hpl.jena.rdf.model.ModelFactory;


import com.hp.hpl.jena.rdf.model.ResourceFactory;

import crawler.SemanticCrawler;

public class SemanticCrawlerImpl implements SemanticCrawler {
		
	public void search(Model graph, String resourceURI) {
		graph.read(resourceURI);
		
		ParameterizedSparqlString queryString = new ParameterizedSparqlString( "" +
                "select ?uri ?property ?resource where {\n" +
                "  <"+resourceURI+"> ?property ?resource\n" +
                "}" );

        System.out.println( queryString );

        QueryExecution exec = QueryExecutionFactory.sparqlService( "http://dbpedia.org/sparql", queryString.asQuery() );
        
        com.hp.hpl.jena.query.ResultSet results = ResultSetFactory.copyResults( exec.execSelect() );

	    System.out.println("----------------------");

	    System.out.println("Query Result Sheet");

	    System.out.println("----------------------");
	   
	    while ( results.hasNext() ) {
            // As RobV pointed out, don't use the `?` in the variable
            // name here. Use *just* the name of the variable.
            System.out.println( results.next().get( "resource" ));
        }
	    
	    // A simpler way of printing the results.
        ResultSetFormatter.out( results );
	}
}


