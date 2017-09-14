package com.hyperiongray.sitehound.backend.test.service.nlp.tika;

import com.hyperiongray.sitehound.backend.config.Configuration;
import com.hyperiongray.sitehound.backend.service.nlp.tika.TikaOutput;
import com.hyperiongray.sitehound.backend.service.nlp.tika.TikaService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tomas on 9/24/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Configuration.class)
@Ignore
public class TikaServiceTest{

	@Autowired
	private TikaService tikaService;

//	private TikaService tikaService = new TikaService();

	private static final Logger LOGGER = LoggerFactory.getLogger(TikaServiceTest.class);

	private String text_en=
					"<!doctype html>\n"+
					"<!--[if lt IE 7]> <html class=\"no-js ie6 oldie\" lang=\"en\"> <![endif]-->\n"+
					"<!--[if IE 7]>    <html class=\"no-js ie7 oldie\" lang=\"en\"> <![endif]-->\n"+
					"<!--[if IE 8]>    <html class=\"no-js ie8 oldie\" lang=\"en\"> <![endif]-->\n"+
					"<!--[if IE 9]>    <html class=\"no-js ie9\" lang=\"en\"> <![endif]-->\n"+
					"<!--[if gt IE 9]><!--> <html class=\"no-js\" lang=\"en\" itemscope itemtype=\"http://schema.org/Product\"> <!--<![endif]-->\n"+
					"<head>\n"+
					"\n"+
					"  <!-- Meta tags & title /-->\n"+
					"  <meta charset=\"utf-8\">\n"+
					"  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n"+
					"  <title>Hyperion Gray, LLC</title>\n"+
					"  <meta name=\"description\" content=\".\" />\n"+
					"  <meta name=\"keywords\" content=\"\" />\n"+
					"  <meta name=\"author\" content=\"humans.txt\">\n"+
					"\n"+
					"  <!-- Favicon /-->\n"+
					"  <link rel=\"shortcut icon\" href=\"favicon.ico\" type=\"image/x-icon\" /> <!-- Favicon /-->\n"+
					"\n"+
					"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1\">\n"+
					"\n"+
					"  <!-- Stylesheets /-->\n"+
					"  <link rel=\"stylesheet\" href=\"css/gumby.css\">   <!-- Gumby Framework /-->\n"+
					"  <link rel=\"stylesheet\" href=\"css/style.css\">   <!-- Main stylesheet /-->\n"+
					"  <link rel=\"stylesheet\" href=\"css/component.css\"> <!-- Header Slider /-->\n"+
					"  <link rel=\"stylesheet\" href=\"css/owl.carousel.css\" > <!-- Owl Carousel Assets -->\n"+
					"  <link rel=\"stylesheet\" href=\"css/animate.css\"> <!-- Animate for Process Slider -->\n"+
					"  <link rel=\"stylesheet\" href=\"css/liquid-slider.css\"> <!-- Liquid Slider ( Process Section ) -->\n"+
					"\n"+
					"\t<script src=\"//static.getclicky.com/js\" type=\"text/javascript\"></script>\n"+
					"\t<script type=\"text/javascript\">try{ clicky.init(100568453); }catch(e){}</script>\n"+
					"  <!-- Change UA-XXXXX-X to be your site's ID -->\n"+
					"  <!--<script>\n"+
					"  window._gaq = [['_setAccount','UAXXXXXXXX1'],['_trackPageview'],['_trackPageLoadTime']];\n"+
					"  Modernizr.load({\n"+
					"    load: ('https:' == location.protocol ? '//ssl' : '//www') + '.google-analytics.com/ga.js'\n"+
					"  });\n"+
					"  </script>-->\n"+
					"\n"+
					"  <!-- Prompt IE 6 users to install Chrome Frame. Remove this if you want to support IE 6.\n"+
					"     chromium.org/developers/how-tos/chrome-frame-getting-started -->\n"+
					"  <!--[if lt IE 7 ]>\n"+
					"  <script src=\"//ajax.googleapis.com/ajax/libs/chrome-frame/1.0.3/CFInstall.min.js\"></script>\n"+
					"  <script>window.attachEvent('onload',function(){CFInstall.check({mode:'overlay'})})</script>\n"+
					"  <![endif]-->\n"+
					"\n"+
					"  </body>\n"+
					"\n"+
					"</html>\n";


	@Test
	public void languageDetectionTest() throws Exception{

		String text_en="The results aren't very good for short" +
				               " runs of text. Ted Dunning's paper (attached) indicates that a log-likelihood ratio (LLR) test works much better, which would then make language detection faster due to less text needing to be processed.";
		text_en=text_en + "The Getting Started document describes how to build Apache Tika from sources and how to getDdTrainerInputStart using Tika in an application. Pay close attention and " +
				        "follow the instructions in the \"Getting and building the sources\" section.";

		text_en=text_en + "Now, you need to create your new parser. This is a class that must implement the Parser interface offered by Tika. Instead of implementing the Parser interface " +
				        "directly, it is recommended that you extend the abstract class AbstractParser if possible. AbstractParser handles translating between API changes for you.";

		TikaOutput tikaOutput = tikaService.parseHtml(text_en);
		System.out.println(tikaOutput);
	}

	@Test
	public void parseTest() throws Exception{
		String html="<html><head><title>The Big Brown Shoe</title></head><body><p>The best pizza place " + "in the US is <a href=\"http://antoniospizzas.com/\">Antonio's Pizza</a>.</p>" + "<p>It is located in Amherst, MA.</p></body></html>";
		TikaOutput tikaOutput = tikaService.parseHtml(html);
		System.out.println(tikaOutput);
	}


	@Test
	public void parseTest2() throws Exception{
		String en = tikaService.identifyLanguage(text_en);
		System.out.println(en);
		Assert.assertTrue("en".equals(en));
	}

	@Test
	public void parseFooterTest() throws Exception{
		String inpupt = text_en + text_en + text_en +text_en + text_en +text_en + text_en + text_en +text_en + text_en;
		TikaOutput res = tikaService.parseHtml(inpupt);
		System.out.println(inpupt.length());
//		System.out.println(res);
		System.out.println(inpupt.length());
	}



}
