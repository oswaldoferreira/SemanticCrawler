package crawler;

import com.hp.hpl.jena.rdf.model.Model;

public interface SemanticCrawler {
	public void search(Model graph, String resourceURI);
}
