package com.hyperiongray.sitehound.backend.service.nlp.tika;

import com.beust.jcommander.internal.Sets;
import org.apache.tika.language.LanguageIdentifier;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.Link;
import org.apache.tika.sax.LinkContentHandler;
import org.apache.tika.sax.TeeContentHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xml.sax.ContentHandler;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Set;

/**
 * Created by tomas on 9/24/15.
 */

@Service
public class TikaService{

	@Value( "${tika.text-min-length}" ) private int textMinLength;

	public TikaOutput parseHtml(String html) throws Exception {
		InputStream input=new ByteArrayInputStream(html.getBytes(Charset.forName("UTF-8")));
		ContentHandler bodyContentHandler=new BodyContentHandler(1*1000*1000);
//		ToHTMLContentHandler toHTMLContentHandler = new ToHTMLContentHandler();
		LinkContentHandler linkContentHandler=new LinkContentHandler();
		ContentHandler handler=new TeeContentHandler(bodyContentHandler, linkContentHandler);
		Metadata metadata=new Metadata();
		Parser parser=new HtmlParser();
		ParseContext context=new ParseContext();
		parser.parse(input, handler, metadata, context);

		TikaOutput tikaOutput = new TikaOutput();
		tikaOutput.setTitle(metadata.get(Metadata.TITLE));

		///links
		Set<String> strlinks = Sets.newHashSet();
		for(Link link:linkContentHandler.getLinks())
		{
			if(link.isAnchor())
				strlinks.add(link.getUri());
		}
		tikaOutput.setLinks(strlinks);


		//metadata
		tikaOutput.setPageMetadata(metadata.toString());

		return tikaOutput;
	}

	@Deprecated
	public String identifyLanguage(String text){
		String language;
		if(text != null && text.length() > textMinLength)
		{
			LanguageIdentifier identifier = new LanguageIdentifier(text);
			language = identifier.getLanguage();
		}
		else{
			language = "unidentified";
		}
		return language;
	}
}