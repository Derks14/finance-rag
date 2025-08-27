package sika.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.document.DocumentReader;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.ParagraphPdfDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

//Command line runner so this runs after the application context gets created
@Component
@Slf4j
public class IngestionService implements CommandLineRunner {

    private final VectorStore store;

    @Value("classpath:/docs/article_thebeataugust2025-msim.pdf")
    private Resource marketFile;

    public IngestionService (VectorStore vectorStore) {
        this.store = vectorStore;
    }

    @Override
    public void run(String... args) throws Exception {
//        get a pdf reader to read the document you have uploaded
        DocumentReader pdfReader = new ParagraphPdfDocumentReader(marketFile);

//        get a textsplitter to read the text as tokens
        TextSplitter textSplitter = new TokenTextSplitter();

//        let's use what we get from our document reader into the text splitter
        List<Document> paragraphs = textSplitter.apply(pdfReader.get());

//        put what we have from out token into the vector store, which is our database
        store.accept(paragraphs);

        log.info("Vector Store loaded with data");
    }
}
