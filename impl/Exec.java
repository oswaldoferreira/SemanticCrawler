package impl;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import impl.SemanticCrawlerImpl;

public class Exec {
	
	public static final String INITIAL_URI = "http://dbpedia.org/resource/Zico";
	
	public static void main(String[] args) {
		Model model = ModelFactory.createDefaultModel();
		SemanticCrawlerImpl crawler = new SemanticCrawlerImpl();
		crawler.search(model, INITIAL_URI);
	}
}
