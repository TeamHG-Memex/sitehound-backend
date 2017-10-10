package com.hyperiongray.sitehound.backend.service.nlp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Service;

/**
 * Created by tomas on 9/26/15.
 */
@Service
public class ParserService{

	/*
	public String getDesc(final String html){
		Document htmlDocument = Jsoup.parse(html);
		clean(htmlDocument);
		htmlDocument.body().select("img").remove();
		htmlDocument.body().select("a").remove();
		htmlDocument.body().select("span").remove();
		String cleanBodyNoImage = Jsoup.clean(htmlDocument.body().toJson(), Whitelist.simpleText().removeAttributes("a", "span"));

		cleanBodyNoImage = Jsoup.parseBodyFragment(cleanBodyNoImage).body().ownText();
//		Document htmlDocument2 = Jsoup.parseBodyFragment(cleanBodyNoImage);

//		htmlDocument2.text();
		return cleanBodyNoImage.length() > 500 ? cleanBodyNoImage.substring(0, 500) + " ..." : cleanBodyNoImage;
	}
*/
	/*
	public String getDesc(final String html){
		String cleanBody = Jsoup.clean(html, Whitelist.none());

		Document htmlDocument = Jsoup.parse(cleanBody);
		clean(htmlDocument);
		htmlDocument.body().select("img").remove();
		String cleanBodyNoImage = htmlDocument.body().text();
		return cleanBodyNoImage.length() > 140 ? cleanBodyNoImage.substring(0, 140) + " ..." : cleanBodyNoImage;
	}
	*/

	public String stringify(final String html){
		Document htmlDocument = Jsoup.parse(html);
		htmlDocument.select("head").remove();
		htmlDocument.select("script").remove();
		htmlDocument.select("noscript").remove();
		htmlDocument.body().select("iframe").remove();
		htmlDocument.body().select("img").remove();
		htmlDocument.body().select("a").remove();
//		htmlDocument.body().select("span").remove();
		String cleanBody = Jsoup.clean(htmlDocument.body().toString(), Whitelist.simpleText().removeAttributes("a", "span"));

		cleanBody = Jsoup.parseBodyFragment(cleanBody).body().text();
		return cleanBody;

	}

	public Document clean (Document htmlDocument)
	{
		htmlDocument.select("head").remove();
		htmlDocument.select("script").remove();
		htmlDocument.select("noscript").remove();
		htmlDocument.body().select("iframe").remove();
		return htmlDocument;
	}
}
