package parsers.solr;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

public class FakeSolrVersionParser extends SolrVersionParser {
    private String documentOrigin;

    public FakeSolrVersionParser(String documentOrigin) {
        this.documentOrigin = documentOrigin;
    }

    @Override
    public Document loadDocument() throws IOException {
        File input = new File(documentOrigin);
        return Jsoup.parse(input, "UTF-8", url);
    }
}