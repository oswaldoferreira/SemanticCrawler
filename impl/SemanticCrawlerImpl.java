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

public class SemanticCrawlerImpl implements SemanticCrawler {
	public void search(Model graph, String resourceURI) {
//		CharsetEncoder enc = Charset.forName("ISO‐8859‐1").newEncoder();
		
		graph.read(resourceURI);
		
		System.out.println("Following instances with owl:sameAs property");
		StmtIterator statements = graph.listStatements((Resource) null, OWL.sameAs, (RDFNode) null);
		
		while ( statements.hasNext() ) {
			Statement statement = statements.nextStatement();
			Resource subject = statement.getSubject();
			
			if ( subject.isAnon() ) {
				System.out.print(" ("+ subject.getId() +" )");
			} else {
				String subjectURI = subject.getURI();
				
//				if (enc.canEncode(subjectURI)) {
				System.out.println(" ("+ subjectURI +") ");
//				}
			}
			
			System.out.print(" OWL.sameAs ");
			
			Resource object = (Resource) (statement.getObject());
			
			if (object.isAnon()) {
				System.out.print(" ("+ object +") ");
			} else if (object.isLiteral()) {
				System.out.print(" ("+ object.toString() +") ");
			} else if (object.isResource() ) {
				System.out.print(" ("+ object.getURI() +") ");
			}
			
		}
	}
}


