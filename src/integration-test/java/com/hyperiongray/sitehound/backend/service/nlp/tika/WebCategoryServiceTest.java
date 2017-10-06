package com.hyperiongray.sitehound.backend.service.nlp.tika;

import com.hyperiongray.sitehound.backend.Application;
import com.hyperiongray.sitehound.backend.config.Configuration;
import com.hyperiongray.sitehound.backend.service.nlp.WebCategoryService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Created by tomas on 9/25/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@ActiveProfiles("integration-test")
public class WebCategoryServiceTest{

	@Autowired
	private WebCategoryService webCategoryService;

	@Test
	public void categorizeByFooterTest(){

		String html = "\n" +
				              "<!DOCTYPE html>\n" +
				              "<html lang=\"en\" dir=\"ltr\" class=\"client-nojs\">\n" +
				              "<head>\n" +
				              "<meta charset=\"UTF-8\" />\n" +
				              "<title>Bad title - Wikipedia, the free encyclopedia</title>\n" +
				              "<script>document.documentElement.className = document.documentElement.className.replace( /(^|\\s)client-nojs(\\s|$)/, \"$1client-js$2\" );</script>\n" +
				              "<script>window.RLQ = window.RLQ || []; window.RLQ.push( function () {\n" +
				              "mw.config.set({\"wgCanonicalNamespace\":\"Special\",\"wgCanonicalSpecialPageName\":\"Badtitle\",\"wgNamespaceNumber\":-1,\"wgPageName\":\"Special:Badtitle\",\"wgTitle\":\"Badtitle\",\"wgCurRevisionId\":0,\"wgRevisionId\":0,\"wgArticleId\":0,\"wgIsArticle\":!1,\"wgIsRedirect\":!1,\"wgAction\":\"view\",\"wgUserName\":null,\"wgUserGroups\":[\"*\"],\"wgCategories\":[],\"wgBreakFrames\":!0,\"wgPageContentLanguage\":\"en\",\"wgPageContentModel\":\"wikitext\",\"wgSeparatorTransformTable\":[\"\",\"\"],\"wgDigitTransformTable\":[\"\",\"\"],\"wgDefaultDateFormat\":\"dmy\",\"wgMonthNames\":[\"\",\"January\",\"February\",\"March\",\"April\",\"May\",\"June\",\"July\",\"August\",\"September\",\"October\",\"November\",\"December\"],\"wgMonthNamesShort\":[\"\",\"Jan\",\"Feb\",\"Mar\",\"Apr\",\"May\",\"Jun\",\"Jul\",\"Aug\",\"Sep\",\"Oct\",\"Nov\",\"Dec\"],\"wgRelevantPageName\":\"Special:Badtitle\",\"wgRelevantArticleId\":0,\"wgIsProbablyEditable\":!1,\"wgMediaViewerOnClick\":!0,\"wgMediaViewerEnabledByDefault\":!0,\"wikilove-recipient\":\"\",\"wikilove-anon\":0,\"wgWikiEditorEnabledModules\":{\"toolbar\":!0,\"dialogs\":!0,\n" +
				              "\"preview\":!1,\"publish\":!1},\"wgBetaFeaturesFeatures\":[],\"wgVisualEditor\":{\"pageLanguageCode\":\"en\",\"pageLanguageDir\":\"ltr\",\"usePageImages\":!0,\"usePageDescriptions\":!0},\"wgGatherShouldShowTutorial\":!0,\"wgGatherEnableSample\":0,\"wgULSAcceptLanguageList\":[\"en-us\",\"en\",\"es\",\"ca\"],\"wgULSCurrentAutonym\":\"English\",\"wgFlaggedRevsParams\":{\"tags\":{\"status\":{\"levels\":1,\"quality\":2,\"pristine\":3}}},\"wgStableRevisionId\":null,\"wgCategoryTreePageCategoryOptions\":\"{\\\"mode\\\":0,\\\"hideprefix\\\":20,\\\"showcount\\\":true,\\\"namespaces\\\":false}\",\"wgNoticeProject\":\"wikipedia\",\"wgVisualEditorToolbarScrollOffset\":0});mw.loader.implement(\"user.options\",function($,jQuery){mw.user.options.set({\"variant\":\"en\"});});mw.loader.implement(\"user.tokens\",function($,jQuery){mw.user.tokens.set({\"editToken\":\"+\\\\\",\"patrolToken\":\"+\\\\\",\"watchToken\":\"+\\\\\"});});mw.loader.load([\"mediawiki.page.startup\",\"mediawiki.legacy.wikibits\",\"ext.centralauth.centralautologin\",\"ext.gadget.WatchlistBase\",\"ext.gadget.WatchlistGreenIndicators\",\"ext.visualEditor.desktopArticleTarget.init\",\"ext.uls.init\",\"ext.uls.interface\",\"ext.centralNotice.bannerController\",\"skins.vector.js\"]);\n" +
				              "} );</script>\n" +
				              "<link rel=\"stylesheet\" href=\"/w/load.php?debug=false&amp;lang=en&amp;modules=ext.gadget.WatchlistBase%2CWatchlistGreenIndicators%7Cext.uls.nojs%7Cext.visualEditor.desktopArticleTarget.noscript%7Cmediawiki.legacy.commonPrint%2Cshared%7Cmediawiki.sectionAnchor%7Cmediawiki.skinning.interface%7Cskins.vector.styles&amp;only=styles&amp;skin=vector\" />\n" +
				              "<meta name=\"ResourceLoaderDynamicStyles\" content=\"\" />\n" +
				              "<link rel=\"stylesheet\" href=\"/w/load.php?debug=false&amp;lang=en&amp;modules=site&amp;only=styles&amp;skin=vector\" />\n" +
				              "<style>a:lang(ar),a:lang(kk-arab),a:lang(mzn),a:lang(ps),a:lang(ur){text-decoration:none}</style>\n" +
				              "<script async=\"\" src=\"/w/load.php?debug=false&amp;lang=en&amp;modules=startup&amp;only=scripts&amp;skin=vector\"></script>\n" +
				              "<meta name=\"generator\" content=\"MediaWiki 1.26wmf24\" />\n" +
				              "<meta name=\"robots\" content=\"noindex,nofollow\" />\n" +
				              "<link rel=\"apple-touch-icon\" href=\"/static/apple-touch/wikipedia.png\" />\n" +
				              "<link rel=\"shortcut icon\" href=\"/static/favicon/wikipedia.ico\" />\n" +
				              "<link rel=\"search\" type=\"application/opensearchdescription+xml\" href=\"/w/opensearch_desc.php\" title=\"Wikipedia (en)\" />\n" +
				              "<link rel=\"EditURI\" type=\"application/rsd+xml\" href=\"//en.wikipedia.org/w/api.php?action=rsd\" />\n" +
				              "<link rel=\"copyright\" href=\"//creativecommons.org/licenses/by-sa/3.0/\" />\n" +
				              "<link rel=\"alternate\" type=\"application/atom+xml\" title=\"Wikipedia Atom feed\" href=\"/w/index.php?title=Special:RecentChanges&amp;feed=atom\" />\n" +
				              "<link rel=\"canonical\" href=\"https://en.wikipedia.org/wiki/guns_%2526_ammo\" />\n" +
				              "<link rel=\"dns-prefetch\" href=\"//meta.wikimedia.org\" />\n" +
				              "<!--[if lt IE 7]><style type=\"text/css\">body{behavior:url(\"/w/static/1.26wmf24/skins/Vector/csshover.min.htc\")}</style><![endif]-->\n" +
				              "</head>\n" +
				              "<body class=\"mediawiki ltr sitedir-ltr ns--1 ns-special mw-special-Badtitle page-Special_Badtitle skin-vector action-view\">\n" +
				              "\t\t<div id=\"mw-page-base\" class=\"noprint\"></div>\n" +
				              "\t\t<div id=\"mw-head-base\" class=\"noprint\"></div>\n" +
				              "\t\t<div id=\"content\" class=\"mw-body\" role=\"main\">\n" +
				              "\t\t\t<a id=\"top\"></a>\n" +
				              "\n" +
				              "\t\t\t\t\t\t\t<div id=\"siteNotice\"><!-- CentralNotice --></div>\n" +
				              "\t\t\t\t\t\t<div class=\"mw-indicators\">\n" +
				              "</div>\n" +
				              "\t\t\t<h1 id=\"firstHeading\" class=\"firstHeading\" lang=\"en\">Bad title</h1>\n" +
				              "\t\t\t\t\t\t\t\t\t<div id=\"bodyContent\" class=\"mw-body-content\">\n" +
				              "\t\t\t\t\t\t\t\t<div id=\"contentSub\"></div>\n" +
				              "\t\t\t\t\t\t\t\t\t\t\t\t<div id=\"jump-to-nav\" class=\"mw-jump\">\n" +
				              "\t\t\t\t\tJump to:\t\t\t\t\t<a href=\"#mw-head\">navigation</a>, \t\t\t\t\t<a href=\"#p-search\">search</a>\n" +
				              "\t\t\t\t</div>\n" +
				              "\t\t\t\t<div id=\"mw-content-text\"><p>The requested page title contains invalid characters: \"%26\".\n" +
				              "</p><p id=\"mw-returnto\">Return to <a href=\"/wiki/Main_Page\" title=\"Main Page\">Main Page</a>.</p>\n" +
				              "<noscript><img src=\"//en.wikipedia.org/wiki/Special:CentralAutoLogin/getDdTrainerInputStart?type=1x1\" alt=\"\" title=\"\" width=\"1\" height=\"1\" style=\"border: none; position: absolute;\" /></noscript></div>\t\t\t\t\t<div class=\"printfooter\">\n" +
				              "\t\t\t\t\t\tRetrieved from \"<a dir=\"ltr\" href=\"https://en.wikipedia.org/wiki/Special:Badtitle\">https://en.wikipedia.org/wiki/Special:Badtitle</a>\"\t\t\t\t\t</div>\n" +
				              "\t\t\t\t<div id='catlinks' class='catlinks catlinks-allhidden'></div>\t\t\t\t<div class=\"visualClear\"></div>\n" +
				              "\t\t\t\t\t\t\t</div>\n" +
				              "\t\t</div>\n" +
				              "\t\t<div id=\"mw-navigation\">\n" +
				              "\t\t\t<h2>Navigation menu</h2>\n" +
				              "\n" +
				              "\t\t\t<div id=\"mw-head\">\n" +
				              "\t\t\t\t\t\t\t\t\t<div id=\"p-personal\" role=\"navigation\" class=\"\" aria-labelledby=\"p-personal-label\">\n" +
				              "\t\t\t\t\t\t<h3 id=\"p-personal-label\">Personal tools</h3>\n" +
				              "\t\t\t\t\t\t<ul>\n" +
				              "\t\t\t\t\t\t\t<li id=\"pt-createaccount\"><a href=\"/w/index.php?title=Special:UserLogin&amp;returnto=Special%3ABadtitle&amp;type=signup\" title=\"You are encouraged to create an account and log in; however, it is not mandatory\">Create account</a></li><li id=\"pt-login\"><a href=\"/w/index.php?title=Special:UserLogin&amp;returnto=Special%3ABadtitle\" title=\"You're encouraged to log in; however, it's not mandatory. [o]\" accesskey=\"o\">Log in</a></li>\t\t\t\t\t\t</ul>\n" +
				              "\t\t\t\t\t</div>\n" +
				              "\t\t\t\t\t\t\t\t\t<div id=\"left-navigation\">\n" +
				              "\t\t\t\t\t\t\t\t\t\t<div id=\"p-namespaces\" role=\"navigation\" class=\"vectorTabs\" aria-labelledby=\"p-namespaces-label\">\n" +
				              "\t\t\t\t\t\t<h3 id=\"p-namespaces-label\">Namespaces</h3>\n" +
				              "\t\t\t\t\t\t<ul>\n" +
				              "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<li  id=\"ca-nstab-special\" class=\"selected\"><span><a href=\"/wiki/guns_%2526_ammo\"  title=\"This is a special page which you cannot edit\">Special page</a></span></li>\n" +
				              "\t\t\t\t\t\t\t\t\t\t\t\t\t</ul>\n" +
				              "\t\t\t\t\t</div>\n" +
				              "\t\t\t\t\t\t\t\t\t\t<div id=\"p-variants\" role=\"navigation\" class=\"vectorMenu emptyPortlet\" aria-labelledby=\"p-variants-label\">\n" +
				              "\t\t\t\t\t\t\t\t\t\t\t\t<h3 id=\"p-variants-label\">\n" +
				              "\t\t\t\t\t\t\t<span>Variants</span><a href=\"#\"></a>\n" +
				              "\t\t\t\t\t\t</h3>\n" +
				              "\n" +
				              "\t\t\t\t\t\t<div class=\"menu\">\n" +
				              "\t\t\t\t\t\t\t<ul>\n" +
				              "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</ul>\n" +
				              "\t\t\t\t\t\t</div>\n" +
				              "\t\t\t\t\t</div>\n" +
				              "\t\t\t\t\t\t\t\t\t</div>\n" +
				              "\t\t\t\t<div id=\"right-navigation\">\n" +
				              "\t\t\t\t\t\t\t\t\t\t<div id=\"p-views\" role=\"navigation\" class=\"vectorTabs emptyPortlet\" aria-labelledby=\"p-views-label\">\n" +
				              "\t\t\t\t\t\t<h3 id=\"p-views-label\">Views</h3>\n" +
				              "\t\t\t\t\t\t<ul>\n" +
				              "\t\t\t\t\t\t\t\t\t\t\t\t\t</ul>\n" +
				              "\t\t\t\t\t</div>\n" +
				              "\t\t\t\t\t\t\t\t\t\t<div id=\"p-cactions\" role=\"navigation\" class=\"vectorMenu emptyPortlet\" aria-labelledby=\"p-cactions-label\">\n" +
				              "\t\t\t\t\t\t<h3 id=\"p-cactions-label\"><span>More</span><a href=\"#\"></a></h3>\n" +
				              "\n" +
				              "\t\t\t\t\t\t<div class=\"menu\">\n" +
				              "\t\t\t\t\t\t\t<ul>\n" +
				              "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</ul>\n" +
				              "\t\t\t\t\t\t</div>\n" +
				              "\t\t\t\t\t</div>\n" +
				              "\t\t\t\t\t\t\t\t\t\t<div id=\"p-search\" role=\"search\">\n" +
				              "\t\t\t\t\t\t<h3>\n" +
				              "\t\t\t\t\t\t\t<label for=\"searchInput\">Search</label>\n" +
				              "\t\t\t\t\t\t</h3>\n" +
				              "\n" +
				              "\t\t\t\t\t\t<form action=\"/w/index.php\" id=\"searchform\">\n" +
				              "\t\t\t\t\t\t\t<div id=\"simpleSearch\">\n" +
				              "\t\t\t\t\t\t\t<input type=\"search\" name=\"search\" placeholder=\"Search\" title=\"Search Wikipedia [f]\" accesskey=\"f\" id=\"searchInput\" /><input type=\"hidden\" value=\"Special:Search\" name=\"title\" /><input type=\"submit\" name=\"fulltext\" value=\"Search\" title=\"Search Wikipedia for this text\" id=\"mw-searchButton\" class=\"searchButton mw-fallbackSearchButton\" /><input type=\"submit\" name=\"go\" value=\"Go\" title=\"Go to a page with this exact name if it exists\" id=\"searchButton\" class=\"searchButton\" />\t\t\t\t\t\t\t</div>\n" +
				              "\t\t\t\t\t\t</form>\n" +
				              "\t\t\t\t\t</div>\n" +
				              "\t\t\t\t\t\t\t\t\t</div>\n" +
				              "\t\t\t</div>\n" +
				              "\t\t\t<div id=\"mw-panel\">\n" +
				              "\t\t\t\t<div id=\"p-logo\" role=\"banner\"><a class=\"mw-wiki-logo\" href=\"/wiki/Main_Page\"  title=\"Visit the main page\"></a></div>\n" +
				              "\t\t\t\t\t\t<div class=\"portal\" role=\"navigation\" id='p-navigation' aria-labelledby='p-navigation-label'>\n" +
				              "\t\t\t<h3 id='p-navigation-label'>Navigation</h3>\n" +
				              "\n" +
				              "\t\t\t<div class=\"body\">\n" +
				              "\t\t\t\t\t\t\t\t\t<ul>\n" +
				              "\t\t\t\t\t\t<li id=\"n-mainpage-description\"><a href=\"/wiki/Main_Page\" title=\"Visit the main page [z]\" accesskey=\"z\">Main page</a></li><li id=\"n-contents\"><a href=\"/wiki/Portal:Contents\" title=\"Guides to browsing Wikipedia\">Contents</a></li><li id=\"n-featuredcontent\"><a href=\"/wiki/Portal:Featured_content\" title=\"Featured content – the best of Wikipedia\">Featured content</a></li><li id=\"n-currentevents\"><a href=\"/wiki/Portal:Current_events\" title=\"Find background information on current events\">Current events</a></li><li id=\"n-randompage\"><a href=\"/wiki/Special:Random\" title=\"Load a random article [x]\" accesskey=\"x\">Random article</a></li><li id=\"n-sitesupport\"><a href=\"https://donate.wikimedia.org/wiki/Special:FundraiserRedirector?utm_source=donate&amp;utm_medium=sidebar&amp;utm_campaign=C13_en.wikipedia.org&amp;uselang=en\" title=\"Support us\">Donate to Wikipedia</a></li><li id=\"n-shoplink\"><a href=\"//shop.wikimedia.org\" title=\"Visit the Wikipedia store\">Wikipedia store</a></li>\t\t\t\t\t</ul>\n" +
				              "\t\t\t\t\t\t\t</div>\n" +
				              "\t\t</div>\n" +
				              "\t\t\t<div class=\"portal\" role=\"navigation\" id='p-interaction' aria-labelledby='p-interaction-label'>\n" +
				              "\t\t\t<h3 id='p-interaction-label'>Interaction</h3>\n" +
				              "\n" +
				              "\t\t\t<div class=\"body\">\n" +
				              "\t\t\t\t\t\t\t\t\t<ul>\n" +
				              "\t\t\t\t\t\t<li id=\"n-help\"><a href=\"/wiki/Help:Contents\" title=\"Guidance on how to use and edit Wikipedia\">Help</a></li><li id=\"n-aboutsite\"><a href=\"/wiki/Wikipedia:About\" title=\"Find out about Wikipedia\">About Wikipedia</a></li><li id=\"n-portal\"><a href=\"/wiki/Wikipedia:Community_portal\" title=\"About the project, what you can do, where to find things\">Community portal</a></li><li id=\"n-recentchanges\"><a href=\"/wiki/Special:RecentChanges\" title=\"A list of recent changes in the wiki [r]\" accesskey=\"r\">Recent changes</a></li><li id=\"n-contactpage\"><a href=\"//en.wikipedia.org/wiki/Wikipedia:Contact_us\" title=\"How to contact Wikipedia\">Contact page</a></li>\t\t\t\t\t</ul>\n" +
				              "\t\t\t\t\t\t\t</div>\n" +
				              "\t\t</div>\n" +
				              "\t\t\t<div class=\"portal\" role=\"navigation\" id='p-tb' aria-labelledby='p-tb-label'>\n" +
				              "\t\t\t<h3 id='p-tb-label'>Tools</h3>\n" +
				              "\n" +
				              "\t\t\t<div class=\"body\">\n" +
				              "\t\t\t\t\t\t\t\t\t<ul>\n" +
				              "\t\t\t\t\t\t<li id=\"t-upload\"><a href=\"/wiki/Wikipedia:File_Upload_Wizard\" title=\"Upload files [u]\" accesskey=\"u\">Upload file</a></li><li id=\"t-specialpages\"><a href=\"/wiki/Special:SpecialPages\" title=\"A list of all special pages [q]\" accesskey=\"q\">Special pages</a></li><li id=\"t-print\"><a href=\"/w/index.php?title=Special:Badtitle&amp;printable=yes\" rel=\"alternate\" title=\"Printable version of this page [p]\" accesskey=\"p\">Printable version</a></li>\t\t\t\t\t</ul>\n" +
				              "\t\t\t\t\t\t\t</div>\n" +
				              "\t\t</div>\n" +
				              "\t\t\t<div class=\"portal\" role=\"navigation\" id='p-lang' aria-labelledby='p-lang-label'>\n" +
				              "\t\t\t<h3 id='p-lang-label'>Languages</h3>\n" +
				              "\n" +
				              "\t\t\t<div class=\"body\">\n" +
				              "\t\t\t\t\t\t\t\t\t<ul>\n" +
				              "\t\t\t\t\t\t<li class=\"uls-p-lang-dummy\"><a href=\"#\"></a></li>\t\t\t\t\t</ul>\n" +
				              "\t\t\t\t\t\t\t</div>\n" +
				              "\t\t</div>\n" +
				              "\t\t\t\t</div>\n" +
				              "\t\t</div>\n" +
				              "\t\t<div id=\"footer\" role=\"contentinfo\">\n" +
				              "\t\t\t\t\t\t\t<ul id=\"footer-places\">\n" +
				              "\t\t\t\t\t\t\t\t\t\t\t<li id=\"footer-places-privacy\"><a href=\"//wikimediafoundation.org/wiki/Privacy_policy\" title=\"wikimedia:Privacy policy\">Privacy policy</a></li>\n" +
				              "\t\t\t\t\t\t\t\t\t\t\t<li id=\"footer-places-about\"><a href=\"/wiki/Wikipedia:About\" title=\"Wikipedia:About\">About Wikipedia</a></li>\n" +
				              "\t\t\t\t\t\t\t\t\t\t\t<li id=\"footer-places-disclaimer\"><a href=\"/wiki/Wikipedia:General_disclaimer\" title=\"Wikipedia:General disclaimer\">Disclaimers</a></li>\n" +
				              "\t\t\t\t\t\t\t\t\t\t\t<li id=\"footer-places-contact\"><a href=\"//en.wikipedia.org/wiki/Wikipedia:Contact_us\">Contact Wikipedia</a></li>\n" +
				              "\t\t\t\t\t\t\t\t\t\t\t<li id=\"footer-places-developers\"><a href=\"https://www.mediawiki.org/wiki/Special:MyLanguage/How_to_contribute\">Developers</a></li>\n" +
				              "\t\t\t\t\t\t\t\t\t\t\t<li id=\"footer-places-mobileview\"><a href=\"//en.m.wikipedia.org/w/index.php?title=Special:Badtitle&amp;mobileaction=toggle_view_mobile\" class=\"noprint stopMobileRedirectToggle\">Mobile view</a></li>\n" +
				              "\t\t\t\t\t\t\t\t\t</ul>\n" +
				              "\t\t\t\t\t\t\t\t\t\t<ul id=\"footer-icons\" class=\"noprint\">\n" +
				              "\t\t\t\t\t\t\t\t\t\t\t<li id=\"footer-copyrightico\">\n" +
				              "\t\t\t\t\t\t\t<a href=\"//wikimediafoundation.org/\"><img src=\"/static/images/wikimedia-button.png\" srcset=\"/static/images/wikimedia-button-1.5x.png 1.5x, /static/images/wikimedia-button-2x.png 2x\" width=\"88\" height=\"31\" alt=\"Wikimedia Foundation\"/></a>\t\t\t\t\t\t</li>\n" +
				              "\t\t\t\t\t\t\t\t\t\t\t<li id=\"footer-poweredbyico\">\n" +
				              "\t\t\t\t\t\t\t<a href=\"//www.mediawiki.org/\"><img src=\"/static/1.26wmf24/resources/assets/poweredby_mediawiki_88x31.png\" alt=\"Powered by MediaWiki\" srcset=\"/static/1.26wmf24/resources/assets/poweredby_mediawiki_132x47.png 1.5x, /static/1.26wmf24/resources/assets/poweredby_mediawiki_176x62.png 2x\" width=\"88\" height=\"31\" /></a>\t\t\t\t\t\t</li>\n" +
				              "\t\t\t\t\t\t\t\t\t</ul>\n" +
				              "\t\t\t\t\t\t<div style=\"clear:both\"></div>\n" +
				              "\t\t</div>\n" +
				              "\t\t<script>window.RLQ = window.RLQ || []; window.RLQ.push( function () {\n" +
				              "mw.loader.state({\"ext.globalCssJs.site\":\"ready\",\"ext.globalCssJs.user\":\"ready\",\"user\":\"ready\",\"user.groups\":\"ready\"});\n" +
				              "} );</script>\n" +
				              "<link rel=\"stylesheet\" href=\"/w/load.php?debug=false&amp;lang=en&amp;modules=ext.gadget.DRN-wizard%2CReferenceTooltips%2Ccharinsert%2Cfeatured-articles-links%2CrefToolbar%2Cswitcher%2Cteahouse%7Cext.wikimediaBadges&amp;only=styles&amp;skin=vector\" />\n" +
				              "<script>window.RLQ = window.RLQ || []; window.RLQ.push( function () {\n" +
				              "mw.loader.load([\"site\",\"mediawiki.user\",\"mediawiki.hidpi\",\"mediawiki.page.ready\",\"mediawiki.searchSuggest\",\"ext.eventLogging.subscriber\",\"ext.wikimediaEvents\",\"ext.wikimediaEvents.geoFeatures\",\"ext.navigationTiming\",\"ext.gadget.teahouse\",\"ext.gadget.ReferenceTooltips\",\"ext.gadget.DRN-wizard\",\"ext.gadget.charinsert\",\"ext.gadget.refToolbar\",\"ext.gadget.switcher\",\"ext.gadget.featured-articles-links\",\"ext.visualEditor.targetLoader\",\"schema.UniversalLanguageSelector\",\"ext.uls.eventlogger\",\"ext.uls.interlanguage\"]);\n" +
				              "} );</script><script>window.RLQ = window.RLQ || []; window.RLQ.push( function () {\n" +
				              "mw.config.set({\"wgBackendResponseTime\":48,\"wgHostname\":\"mw1256\"});\n" +
				              "} );</script>\n" +
				              "\t</body>\n" +
				              "</html>\n";

		Document htmlDocument = Jsoup.parse(html);

		Set<String> categories = webCategoryService.categorizeByFooter(htmlDocument);


		assertTrue(categories.size()==1);
		assertTrue(categories.contains("wiki"));
//		assertThat(categories.contains(), contains("wiki"));
//		System.out.println(categories);
	}

	@Test
	public void categorizeByTextTest(){
		String text = "";
		webCategoryService.categorizeByText(text);
	}

}


