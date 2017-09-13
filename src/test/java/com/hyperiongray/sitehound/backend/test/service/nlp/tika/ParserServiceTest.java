package com.hyperiongray.sitehound.backend.test.service.nlp.tika;

import com.hyperiongray.sitehound.backend.service.nlp.ParserService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by tomas on 9/26/15.
 */
@Ignore
public class ParserServiceTest{

ParserService parserService = new ParserService();

@Test
public void getDescTest(){

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
"<script>document.documentElement.className = document.documentElement.className.replace( /(^|\\s)client-nojs(\\s|$)/, \"$1client-js$2\" );</script>\n" +
  "<div id=\"mw-page-base\" class=\"noprint\"></div>\n" +
  "<div id=\"mw-head-base\" class=\"noprint\"></div>\n" +
  "<div id=\"content\" class=\"mw-body\" role=\"main\">\n" +
  "<a id=\"top\"></a>\n" +
  "\n" +
  "<div id=\"siteNotice\"><!-- CentralNotice --></div>\n" +
  "<div class=\"mw-indicators\">\n" +
  "</div>\n" +
  "<h1 id=\"firstHeading\" class=\"firstHeading\" lang=\"en\">Bad title</h1>\n" +
  "<div id=\"bodyContent\" class=\"mw-body-content\">\n" +
  "<div id=\"contentSub\"></div>\n" +
  "<div id=\"jump-to-nav\" class=\"mw-jump\">\n" +
  "Jump to:<a href=\"#mw-head\">navigation</a>, <a href=\"#p-search\">search</a>\n" +
  "</div>\n" +
  "<div id=\"mw-content-text\"><p>The requested page title contains invalid characters: \"%26\".\n" +
  "</p><p id=\"mw-returnto\">Return to <a href=\"/wiki/Main_Page\" title=\"Main Page\">Main Page</a>.</p>\n" +
  "<noscript><img src=\"//en.wikipedia.org/wiki/Special:CentralAutoLogin/getDdTrainerInputStart?type=1x1\" alt=\"\" title=\"\" width=\"1\" height=\"1\" style=\"border: none; position: absolute;\" /></noscript></div><div class=\"printfooter\">\n" +
  "Retrieved from \"<a dir=\"ltr\" href=\"https://en.wikipedia.org/wiki/Special:Badtitle\">https://en.wikipedia.org/wiki/Special:Badtitle</a>\"</div>\n" +
  "<div id='catlinks' class='catlinks catlinks-allhidden'></div><div class=\"visualClear\"></div>\n" +
  "</div>\n" +
  "</div>\n" +
  "<div id=\"mw-navigation\">\n" +
  "<h2>Navigation menu</h2>\n" +
  "\n" +
  "<div id=\"mw-head\">\n" +
  "<div id=\"p-personal\" role=\"navigation\" class=\"\" aria-labelledby=\"p-personal-label\">\n" +
  "<h3 id=\"p-personal-label\">Personal tools</h3>\n" +
  "<ul>\n" +
  "<li id=\"pt-createaccount\"><a href=\"/w/index.php?title=Special:UserLogin&amp;returnto=Special%3ABadtitle&amp;type=signup\" title=\"You are encouraged to create an account and log in; however, it is not mandatory\">Create account</a></li><li id=\"pt-login\"><a href=\"/w/index.php?title=Special:UserLogin&amp;returnto=Special%3ABadtitle\" title=\"You're encouraged to log in; however, it's not mandatory. [o]\" accesskey=\"o\">Log in</a></li></ul>\n" +
  "</div>\n" +
  "<div id=\"left-navigation\">\n" +
  "<div id=\"p-namespaces\" role=\"navigation\" class=\"vectorTabs\" aria-labelledby=\"p-namespaces-label\">\n" +
  "<h3 id=\"p-namespaces-label\">Namespaces</h3>\n" +
  "<ul>\n" +
  "<li  id=\"ca-nstab-special\" class=\"selected\"><span><a href=\"/wiki/guns_%2526_ammo\"  title=\"This is a special page which you cannot edit\">Special page</a></span></li>\n" +
  "</ul>\n" +
  "</div>\n" +
  "<div id=\"p-variants\" role=\"navigation\" class=\"vectorMenu emptyPortlet\" aria-labelledby=\"p-variants-label\">\n" +
  "<h3 id=\"p-variants-label\">\n" +
  "<span>Variants</span><a href=\"#\"></a>\n" +
  "</h3>\n" +
  "\n" +
  "<div class=\"menu\">\n" +
  "<ul>\n" +
  "</ul>\n" +
  "</div>\n" +
  "</div>\n" +
  "</div>\n" +
  "<div id=\"right-navigation\">\n" +
  "<div id=\"p-views\" role=\"navigation\" class=\"vectorTabs emptyPortlet\" aria-labelledby=\"p-views-label\">\n" +
  "<h3 id=\"p-views-label\">Views</h3>\n" +
  "<ul>\n" +
  "</ul>\n" +
  "</div>\n" +
  "<div id=\"p-cactions\" role=\"navigation\" class=\"vectorMenu emptyPortlet\" aria-labelledby=\"p-cactions-label\">\n" +
  "<h3 id=\"p-cactions-label\"><span>More</span><a href=\"#\"></a></h3>\n" +
  "\n" +
  "<div class=\"menu\">\n" +
  "<ul>\n" +
  "</ul>\n" +
  "</div>\n" +
  "</div>\n" +
  "<div id=\"p-search\" role=\"search\">\n" +
  "<h3>\n" +
  "<label for=\"searchInput\">Search</label>\n" +
  "</h3>\n" +
  "\n" +
  "<form action=\"/w/index.php\" id=\"searchform\">\n" +
  "<div id=\"simpleSearch\">\n" +
  "<input type=\"search\" name=\"search\" placeholder=\"Search\" title=\"Search Wikipedia [f]\" accesskey=\"f\" id=\"searchInput\" /><input type=\"hidden\" value=\"Special:Search\" name=\"title\" /><input type=\"submit\" name=\"fulltext\" value=\"Search\" title=\"Search Wikipedia for this text\" id=\"mw-searchButton\" class=\"searchButton mw-fallbackSearchButton\" /><input type=\"submit\" name=\"go\" value=\"Go\" title=\"Go to a page with this exact name if it exists\" id=\"searchButton\" class=\"searchButton\" /></div>\n" +
  "</form>\n" +
  "</div>\n" +
  "</div>\n" +
  "</div>\n" +
  "<div id=\"mw-panel\">\n" +
  "<div id=\"p-logo\" role=\"banner\"><a class=\"mw-wiki-logo\" href=\"/wiki/Main_Page\"  title=\"Visit the main page\"></a></div>\n" +
  "<div class=\"portal\" role=\"navigation\" id='p-navigation' aria-labelledby='p-navigation-label'>\n" +
  "<h3 id='p-navigation-label'>Navigation</h3>\n" +
  "\n" +
  "<div class=\"body\">\n" +
  "<ul>\n" +
  "<li id=\"n-mainpage-description\"><a href=\"/wiki/Main_Page\" title=\"Visit the main page [z]\" accesskey=\"z\">Main page</a></li><li id=\"n-contents\"><a href=\"/wiki/Portal:Contents\" title=\"Guides to browsing Wikipedia\">Contents</a></li><li id=\"n-featuredcontent\"><a href=\"/wiki/Portal:Featured_content\" title=\"Featured content – the best of Wikipedia\">Featured content</a></li><li id=\"n-currentevents\"><a href=\"/wiki/Portal:Current_events\" title=\"Find background information on current events\">Current events</a></li><li id=\"n-randompage\"><a href=\"/wiki/Special:Random\" title=\"Load a random article [x]\" accesskey=\"x\">Random article</a></li><li id=\"n-sitesupport\"><a href=\"https://donate.wikimedia.org/wiki/Special:FundraiserRedirector?utm_source=donate&amp;utm_medium=sidebar&amp;utm_campaign=C13_en.wikipedia.org&amp;uselang=en\" title=\"Support us\">Donate to Wikipedia</a></li><li id=\"n-shoplink\"><a href=\"//shop.wikimedia.org\" title=\"Visit the Wikipedia store\">Wikipedia store</a></li></ul>\n" +
  "</div>\n" +
  "</div>\n" +
  "<div class=\"portal\" role=\"navigation\" id='p-interaction' aria-labelledby='p-interaction-label'>\n" +
  "<h3 id='p-interaction-label'>Interaction</h3>\n" +
  "\n" +
  "<div class=\"body\">\n" +
  "<ul>\n" +
  "<li id=\"n-help\"><a href=\"/wiki/Help:Contents\" title=\"Guidance on how to use and edit Wikipedia\">Help</a></li><li id=\"n-aboutsite\"><a href=\"/wiki/Wikipedia:About\" title=\"Find out about Wikipedia\">About Wikipedia</a></li><li id=\"n-portal\"><a href=\"/wiki/Wikipedia:Community_portal\" title=\"About the project, what you can do, where to find things\">Community portal</a></li><li id=\"n-recentchanges\"><a href=\"/wiki/Special:RecentChanges\" title=\"A list of recent changes in the wiki [r]\" accesskey=\"r\">Recent changes</a></li><li id=\"n-contactpage\"><a href=\"//en.wikipedia.org/wiki/Wikipedia:Contact_us\" title=\"How to contact Wikipedia\">Contact page</a></li></ul>\n" +
  "</div>\n" +
  "</div>\n" +
  "<div class=\"portal\" role=\"navigation\" id='p-tb' aria-labelledby='p-tb-label'>\n" +
  "<h3 id='p-tb-label'>Tools</h3>\n" +
  "\n" +
  "<div class=\"body\">\n" +
  "<ul>\n" +
  "<li id=\"t-upload\"><a href=\"/wiki/Wikipedia:File_Upload_Wizard\" title=\"Upload files [u]\" accesskey=\"u\">Upload file</a></li><li id=\"t-specialpages\"><a href=\"/wiki/Special:SpecialPages\" title=\"A list of all special pages [q]\" accesskey=\"q\">Special pages</a></li><li id=\"t-print\"><a href=\"/w/index.php?title=Special:Badtitle&amp;printable=yes\" rel=\"alternate\" title=\"Printable version of this page [p]\" accesskey=\"p\">Printable version</a></li></ul>\n" +
  "</div>\n" +
  "</div>\n" +
  "<div class=\"portal\" role=\"navigation\" id='p-lang' aria-labelledby='p-lang-label'>\n" +
  "<h3 id='p-lang-label'>Languages</h3>\n" +
  "\n" +
  "<div class=\"body\">\n" +
  "<ul>\n" +
  "<li class=\"uls-p-lang-dummy\"><a href=\"#\"></a></li></ul>\n" +
  "</div>\n" +
  "</div>\n" +
  "</div>\n" +
  "</div>\n" +
  "<div id=\"footer\" role=\"contentinfo\">\n" +
  "<ul id=\"footer-places\">\n" +
  "<li id=\"footer-places-privacy\"><a href=\"//wikimediafoundation.org/wiki/Privacy_policy\" title=\"wikimedia:Privacy policy\">Privacy policy</a></li>\n" +
  "<li id=\"footer-places-about\"><a href=\"/wiki/Wikipedia:About\" title=\"Wikipedia:About\">About Wikipedia</a></li>\n" +
  "<li id=\"footer-places-disclaimer\"><a href=\"/wiki/Wikipedia:General_disclaimer\" title=\"Wikipedia:General disclaimer\">Disclaimers</a></li>\n" +
  "<li id=\"footer-places-contact\"><a href=\"//en.wikipedia.org/wiki/Wikipedia:Contact_us\">Contact Wikipedia</a></li>\n" +
  "<li id=\"footer-places-developers\"><a href=\"https://www.mediawiki.org/wiki/Special:MyLanguage/How_to_contribute\">Developers</a></li>\n" +
  "<li id=\"footer-places-mobileview\"><a href=\"//en.m.wikipedia.org/w/index.php?title=Special:Badtitle&amp;mobileaction=toggle_view_mobile\" class=\"noprint stopMobileRedirectToggle\">Mobile view</a></li>\n" +
  "</ul>\n" +
  "<ul id=\"footer-icons\" class=\"noprint\">\n" +
  "<li id=\"footer-copyrightico\">\n" +
  "<a href=\"//wikimediafoundation.org/\"><img src=\"/static/images/wikimedia-button.png\" srcset=\"/static/images/wikimedia-button-1.5x.png 1.5x, /static/images/wikimedia-button-2x.png 2x\" width=\"88\" height=\"31\" alt=\"Wikimedia Foundation\"/></a></li>\n" +
  "<li id=\"footer-poweredbyico\">\n" +
  "<a href=\"//www.mediawiki.org/\"><img src=\"/static/1.26wmf24/resources/assets/poweredby_mediawiki_88x31.png\" alt=\"Powered by MediaWiki\" srcset=\"/static/1.26wmf24/resources/assets/poweredby_mediawiki_132x47.png 1.5x, /static/1.26wmf24/resources/assets/poweredby_mediawiki_176x62.png 2x\" width=\"88\" height=\"31\" /></a></li>\n" +
  "</ul>\n" +
  "<div style=\"clear:both\"></div>\n" +
  "</div>\n" +
  "<script>window.RLQ = window.RLQ || []; window.RLQ.push( function () {\n" +
  "mw.loader.state({\"ext.globalCssJs.site\":\"ready\",\"ext.globalCssJs.user\":\"ready\",\"user\":\"ready\",\"user.groups\":\"ready\"});\n" +
  "} );</script>\n" +
  "<link rel=\"stylesheet\" href=\"/w/load.php?debug=false&amp;lang=en&amp;modules=ext.gadget.DRN-wizard%2CReferenceTooltips%2Ccharinsert%2Cfeatured-articles-links%2CrefToolbar%2Cswitcher%2Cteahouse%7Cext.wikimediaBadges&amp;only=styles&amp;skin=vector\" />\n" +
  "<script>window.RLQ = window.RLQ || []; window.RLQ.push( function () {\n" +
  "mw.loader.load([\"site\",\"mediawiki.user\",\"mediawiki.hidpi\",\"mediawiki.page.ready\",\"mediawiki.searchSuggest\",\"ext.eventLogging.subscriber\",\"ext.wikimediaEvents\",\"ext.wikimediaEvents.geoFeatures\",\"ext.navigationTiming\",\"ext.gadget.teahouse\",\"ext.gadget.ReferenceTooltips\",\"ext.gadget.DRN-wizard\",\"ext.gadget.charinsert\",\"ext.gadget.refToolbar\",\"ext.gadget.switcher\",\"ext.gadget.featured-articles-links\",\"ext.visualEditor.targetLoader\",\"schema.UniversalLanguageSelector\",\"ext.uls.eventlogger\",\"ext.uls.interlanguage\"]);\n" +
  "} );</script><script>window.RLQ = window.RLQ || []; window.RLQ.push( function () {\n" +
  "mw.config.set({\"wgBackendResponseTime\":48,\"wgHostname\":\"mw1256\"});\n" +
  "} );</script>\n" +
  "</body>\n" +
  "</html>\n";

String actual=parserService.stringify(html);

System.out.println(actual);
String expected = "Bad title Jump to:, The requested page title contains invalid characters: \"%26\". Return to . Retrieved from \"\" Navigation menu Personal tools Namespaces Views Search Navigation Interaction Tools Languages";
Assert.assertTrue(actual.equals(expected));

}

@Test
public void getDescTestAOL(){

//http://www.aol.com/article/2015/09/16/syrias-assad-blames-west-for-refugee-crisis/21236883
String html1 = "" +
  "\n" +
  "<!doctype html>\n" +
  "<!--\n" +
  "<html class=\"no-js news aol20 aol20-article cobrand-aol20-article channel-news page-article  isVerticalPage SAF adellesans-enabled responsive notResponsiveTouch wf-active\" lang=\"en\" >\n" +
  "<head >\n" +
  "<meta charset=\"utf-8\">\n" +
  "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge,chrome=1\"/>\n" +
  "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
  "<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\" />\n" +
  "<title>Syria's Assad blames West for refugee crisis - AOL</title>\n" +
  "\n" +
  "  <meta name=\"description\" content=\"In the wake of the migrant crisis in Europe, Syrian President Bashar al-Assad blamed the west for the mass migration.\" />\n" +
  "<meta name=\"author\" content=\"AOL Staff\" />\n" +
  "<meta property=\"og:type\" content=\"article\" />\n" +
  "<meta name=\"og:image\" content=\"http://www.blogcdn.com/slideshows/images/slides/356/048/6/S3560486/slug/l/mideast-syria-1.jpg\" />\n" +
  "<meta property=\"og:site_name\" content=\"AOL.com\" />\n" +
  "<meta property=\"og:url\" content=\"www.aol.com/article/2015/09/16/syrias-assad-blames-west-for-refugee-crisis/21236883/\" />\n" +
  "<meta property=\"og:app_id\" content=\"183146218394780\" />\n" +
  "<meta property=\"fb:app_id\" content=\"183146218394780\" />\n" +
  "<meta property=\"twitter:account_id\" content=\"66237835\" />\n" +
  "<meta rel=\"publisher\" content=\"115771908788438436647\" />\n" +
  "<meta name=\"robots\" content=\"index,follow\" />\n" +
  "<meta property=\"article:publisher\" content=\"https://www.facebook.com/aol\" />\n" +
  "<meta =\"category\" content=\"news\" />\n" +
  "<meta property=\"og:title\" content=\"Syria's Assad blames West for refugee crisis\" />\n" +
  "<meta name=\"twitter:card\" content=\"summary\" />\n" +
  "<meta name=\"twitter:site\" content=\"@AOL\" />\n" +
  "<meta name=\"twitter:title\" content=\"Syria's Assad blames West for refugee crisis\" />\n" +
  "<meta name=\"twitter:creator\" content=\"@AOL\" />\n" +
  "<meta name=\"twitter:image:src\" content=\"https://spthumbnails.5min.com/10381667/519083313_c_570_411.jpg\" />\n" +
  "<meta name=\"twitter:domain\" content=\"www.aol.com\" />\n" +
  "<meta name=\"original-source\" content=\"www.aol.com/article/2015/09/16/syrias-assad-blames-west-for-refugee-crisis/21236883/\" />\n" +
  "<meta name=\"syndication-source\" content=\"www.aol.com/article/2015/09/16/syrias-assad-blames-west-for-refugee-crisis/21236883/\" />\n" +
  "<meta name=\"publish-date\" content=\"September 16, 2015\" />\n" +
  "<meta http-equiv=\"last-modified\" content=\"2015-09-16@12:47:09\" />\n" +
  "<meta name=\"og:description\" content=\"In the wake of the migrant crisis in Europe, Syrian President Bashar al-Assad blamed the west for the mass migration.\" />\n" +
  "<meta name=\"twitter:description\" content=\"In the wake of the migrant crisis in Europe, Syrian President Bashar al-Assad blamed the west for the mass migration.\" />\n" +
  "<meta name=\"twitter:image\" content=\"http://www.blogcdn.com/slideshows/images/slides/356/048/6/S3560486/slug/l/mideast-syria-1.jpg\" />\n" +
  "  \n" +
  "<link rel=\"canonical\" href=\"http://www.aol.com/article/2015/09/16/syrias-assad-blames-west-for-refugee-crisis/21236883/\">\n" +
  "<link rel=\"publisher\" href=\"https://plus.google.com/115771908788438436647\" />\n" +
  "<link rel=\"shortcut icon\" href=\"http://www.aol.com/favicon.ico\" type=\"image/x-icon\" />\n" +
  "<link rel=\"icon\" href=\"http://www.aol.com/favicon.ico\" type=\"image/x-icon\" />\n" +
  "  <link rel=\"stylesheet\" href=\"http://portal.aolcdn.com/p5/_v124.15/css/responsive.css\"/>\n" +
  "<link rel=\"stylesheet\" href=\"http://www.blogsmithmedia.com/www.aol.com/assets-0ba3f0f00c1c96ceb5beceed096523ee/css/mailpreview.css\"/>\n" +
  "  <link rel=\"stylesheet\" href=\"http://www.blogsmithmedia.com/www.aol.com/assets-4f09470b0dfb0ded74ead0e25b7602f2/css/adhoc.css\"/>\n" +
  "\n" +
  "  <style id=\"adhoccss-responsive-design\">\n" +
  "\n" +
  "#recent-features {margin: 0;}\n" +
  "#recipes-of-the-day .more-link{line-height:20px;}\n" +
  ".aol20 .stock-markets #quote-form .stock-quote-input{width:66%}\n" +
  "/* center hnav menu for tablet */\n" +
  ".aol20 #aol-header.aol-global-header #quick-nav-global-tablet.quicknav {left: 10px;}\n" +
  "\n" +
  "/* change a link for breaking news to blue on hover */\n" +
  ".announcement-news-bar a.lnid-sec1_lnk1:hover {color: #008EE1 !important;}\n" +
  "\n" +
  "/* change gradient on vertical DLs per Kurt */\n" +
  "#dl_v2.dl_design_9 #dl-gradient-rl {background: none;}\n" +
  "#dl_v2.dl_design_9 #dl-gradient-lr {\n" +
  "  background: -moz-linear-gradient(left,#111 0,transparent 100%);\n" +
  "  background: -webkit-gradient(linear,left top,right top,color-stop(0,#111),color-stop(100%,transparent));\n" +
  "  background: -webkit-linear-gradient(left,#111 0,transparent 100%);\n" +
  "  background: -o-linear-gradient(left,#111 0,transparent 100%);\n" +
  "  background: -ms-linear-gradient(left,#111 0,transparent 100%);\n" +
  "  background: linear-gradient(to right,#111 0,transparent 100%);\n" +
  "  filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#111111',endColorstr='#00000000',GradientType=1);\n" +
  "}\n" +
  "\n" +
  "/* change positioning and look of vertical slideshow per Kurt \n" +
  "#verticalSlideshow .aol-twist-wrapper .aol-twist-infobar{display:none;}*/\n" +
  "#verticalSlideshow.gmod {border-bottom: none !important; padding: 3px 0px 0 !important;}\n" +
  "#verticalSlideshow .heading {display: none;}\n" +
  "#verticalSlideshow {border-top: none; margin-top: 0;}\n" +
  "#verticalSlideshow h2 {margin: 24px 0px 30px;}\n" +
  "\n" +
  "/* hide the full width line under hnav because it was appearing over ad skin 11/19. */\n" +
  ".hasAdPushdown .hnavDrawerWr {\n" +
  " padding-top: 0;\n" +
  "} \n" +
  "/* add some padding when the persistent nav scrolls down. Underline on nav was on the images  09/12 Sharon */\n" +
  "#pnaol20.fixed .hnavDrawerWr {\n" +
  "  margin-bottom: 2px;\n" +
  "}\n" +
  "\n" +
  "/* fix for ad feedback-adchoices displaying on two lines in chrome and safari 12-11 Karen */\n" +
  "@media screen and (-webkit-min-device-pixel-ratio:0) {div.feedback {padding-top: 0;}}\n" +
  "@media screen and (-webkit-min-device-pixel-ratio:0) {#adfeedback {position: relative; top: 11px; right: 72px;}}\n" +
  "/* safari mac only 12-11 Karen */\n" +
  "_:-webkit-full-screen, _::-webkit-full-page-media, _:future, :root #adchoice {padding-top: 11px;}\n" +
  "_:-webkit-full-screen, _::-webkit-full-page-media, _:future, :root #adfeedback {right: 0;}\n" +
  "\n" +
  ".page-topic .playbutton-over { display:none; }\n" +
  "@media only screen and (max-width: 767px) {.page-topic #topic .timeline .timeline-article .timeline-content .timeline-excerpt .timeline-attribution { margin-top:0px; padding-bottom:15px; }}\n" +
  "@media only screen and (min-width: 768px) { .page-topic #topic .timeline .timeline-article .timeline-content .timeline-excerpt .timeline-attribution { margin-top:6px;}}\n" +
  "\n" +
  ".adellesans-enabled #aol-scrollcurtain-query {font-size: 16px;}\n" +
  "\n" +
  "/* change spacing for article and channel pages per Christopher */\n" +
  ".isVerticalPage.aol20 #col4 .gmod {padding: 23px 0px;}\n" +
  ".isVerticalPage.aol20 #col4 .aol20Style.choose-news-headlines {padding-top: 26px; padding-bottom: 19px;}\n" +
  ".isVerticalPage.aol20 #col3 .gmod {border-bottom: none;}\n" +
  ".isVerticalPage.aol20 .aol20Style h2 {margin-bottom: 12px;}\n" +
  ".aol20 .playlistMod.carouselClass .aol20-video-nav {margin-top: -36px;}\n" +
  ".page-news .latest-thumbs.gmod {padding-bottom: 34px !important;}\n" +
  ".page-news .slot-more-thumbs-news {padding-top: 26px !important;}\n" +
  ".page-entertainment .latest-thumbs.gmod {padding-bottom: 13px !important;}\n" +
  ".page-entertainment .slot-more-thumbs-entertainment {padding-top: 26px !important;}\n" +
  ".page-lifestyle .latest-thumbs.gmod {padding-bottom: 13px !important;}\n" +
  ".page-lifestyle .specialEvents .spevt_promo {margin-top: -36px; margin-bottom: 19px;}\n" +
  ".page-finance .latest-thumbs.gmod {padding-top: 26px !important; padding-bottom: 13px !important;}\n" +
  ".page-sports .slot-more-thumbs-sports {padding-top: 27px !important;}\n" +
  ".playlistMod.carouselClass {padding-top: 41px !important; padding-bottom: 36px !important;}\n" +
  ".mnid-sports-social.gmod {border-bottom: none !important; padding-top: 16px !important;}\n" +
  ".page-sports .slideshowmod.gmod {margin-top: -30px !important;}\n" +
  ".page-entertainment .slideshowmod.gmod {margin-top: -30px !important;}\n" +
  ".page-lifestyle .slideshowmod.gmod {margin-top: -30px !important;}\n" +
  ".page-finance .slideshowmod.gmod {margin-top: -30px !important;}\n" +
  ".page-news .slideshowmod.gmod {margin-top: -30px !important;}\n" +
  ".mnid-news-social.gmod {border-bottom: none !important; padding-top: 6px !important;}\n" +
  ".ccn {margin: 0;} \n" +
  "#brightSpot {padding: 0; margin: 21px auto; border-bottom: 6px solid #E6E6E6;}\n" +
  ".ccn .list ul li {margin-top: 24px; padding-top: 27px;}\n" +
  ".ccn .list ul li .content p {padding-bottom: 2px;}\n" +
  ".slideshow {margin-bottom: -6px;}\n" +
  "#popular-videos-5min-main-wrapper.gmod.plid-531267.mnid-popular-videos-5min-main.playlistMod {margin-top: -21px;}\n" +
  ".page-lifestyle.aol20 .playlistMod.carouselClass .aol20-video-nav {margin-top: -37px;}\n" +
  ".page-lifestyle .aol20Style.choose-news-headlines {padding-top: 26px !important;}\n" +
  ".page-lifestyle #inmod_rrad.gmod {margin-top: -30px;}\n" +
  "#aol-jobs-module {padding-top: 30px !important; border-bottom: medium none !important; margin-bottom: -23px !important;}\n" +
  "@media only screen and (min-width: 297px) and (max-width: 767px) {#verticalSlideshow {margin-top: -48px;}}\n" +
  "@media only screen and (min-width: 297px) and (max-width: 479px) {.ccn {margin: 20px 0px 24px;}}\n" +
  "\n" +
  "\n" +
  ".page-topic .beta,.page-footballteams .beta {  font-size: 11px; position: relative; font-weight: bold; letter-spacing: 0; color: #999999; }\n" +
  ".page-topic .beta { margin-left: 5px; }\n" +
  "\n" +
  "@media only screen and (max-width: 479px) { .page-2014-review #aol-content.share-1 .shared-services .aol-share, .page-ces2015 #aol-content.share-1 .shared-services .aol-share, .page-2015-awards-season #aol-content.share-1 .shared-services .aol-share { padding: 0 0 0 10px; margin: 0 0 0 2px; }}\n" +
  "\n" +
  ".cobrand-hmpg .mpid-3 .enSrc {position: absolute; top: 16px; left: 127px;}\n" +
  ".cobrand-hmpg #enSrcBan {width:100%;position:relative;}\n" +
  ".cobrand-hmpg #enSrcBan .msgLnk {width:974px;margin:0 auto;}\n" +
  ".cobrand-hmpg .enSrc .srcBx {display: block; width: 539px; border: 2px solid #B2B2B2; padding: 8px 84px 8px 8px;background-color: #fff;}\n" +
  ".cobrand-hmpg .enSrc .msgLnk {position:relative;z-index:2;top:0;}\n" +
  ".cobrand-hmpg .enSrc .srcBtn{background-color:#e81d82;  font-weight:normal;position:absolute;right:0;top:0;color:#fff; padding:11px 16px 9px;}\n" +
  ".cobrand-hmpg .ie8 .enSrc .srcBtn{padding-top:10px;}\n" +
  ".cobrand-hmpg .enSrc .oly {filter: alpha(opacity=80);opacity:0.8;position:absolute;top:0;left:0;background-color:#fff;width:539px; height:36px;z-index:1;}\n" +
  "\n" +
  "/* temporary hide the trending module in hnav drawer and space out the other 2 modules: 6/24/2015 */\n" +
  ".hnavDrawerWr div.hnvdrw .hnvdrw-content .middrw .mdrwMod { width: 400px; }\n" +
  "#pgbg  .hnavDrawerWr div.hnvdrw .hnvdrw-content .middrw .mdrwMod1 { padding-left:35px; }\n" +
  ".hnavDrawerWr div.hnvdrw .hnvdrw-content .middrw .mdrwMod1 .playlistMod .videoPlaylist li.vid .vidItemDetails {width: 200px; }\n" +
  "\n" +
  "/* fix hnav highlighted link overlapping DL*/\n" +
  "@media only screen and (min-width: 768px) {.page-entertainment.aol20  #ghnav1 li.topLevel a {height: 14px;}}\n" +
  "@media only screen and (min-width: 768px) {.page-lifestyle.aol20  #ghnav1 li.topLevel a {height: 14px;}}\n" +
  "@media only screen and (min-width: 768px) {.page-finance.aol20  #ghnav1 li.topLevel a {height: 14px;}}\n" +
  "@media only screen and (min-width: 768px) {.page-sports.aol20  #ghnav1 li.topLevel a {height: 14px;}}\n" +
  "@media only screen and (min-width: 768px) {.page-news.aol20  #ghnav1 li.topLevel a {height: 14px;}}\n" +
  "\n" +
  "/* fix videobutton showing on feed items when no box to display button is checked  3-5-2015 Karen*/\n" +
  ".page-news.aol20 .latest-thumbs .thumb-items .thumb .videobutton {display: none !important;}\n" +
  ".page-finance.aol20 .latest-thumbs .thumb-items .thumb .videobutton {display: none !important;}\n" +
  "\n" +
  "\n" +
  "@media only screen and (min-width: 768px) and (max-width: 985px) {.cobrand-canada .gc #headerlogo-global a {background: url(\"http://portal.aolcdn.com/p5/forms/6464/c5dbb6a6-f31f-4173-84db-bf7081951571.png\") no-repeat scroll 0 0 transparent !important;}}\n" +
  "@media only screen and (min-width: 768px) and (max-width: 985px) {.cobrand-canada .gc #headerlogo-globallink img {display: none !important;}}\n" +
  "\n" +
  "/* 2.0 Channel DLs narrowed so remove ad padding. 13/May/2015 */\n" +
  ".isVerticalPage.aol20 #col3 .gmod.topAd {\n" +
  " margin-top: 0;\n" +
  "}\n" +
  "\n" +
  ".trend-v2 .adwrap img {width:97px;}\n" +
  "\n" +
  "/* remove ad choices as it is now served by the ads team - Karen - 8/12/15 */\n" +
  "i#adchoice-sep {display: none;}\n" +
  ".sprite-fg-ad-choices {display: none;}\n" +
  "#adchoice {display: none;}\n" +
  "div.feedback {padding-right: 8px;}\n" +
  "/* END - remove ad choices as it is now served by the ads team - Karen - 8/12/15 */\n" +
  "\n" +
  "/* Fix for horizontal scrollbar across bonanza - Vinnie - 8/24/15 - Its in Develop Branch Already */\n" +
  "#banner-centered-promo {\n" +
  "   overflow:hidden;\n" +
  "}\n" +
  "/* End fix for horizontal scrollbar */\n" +
  "\n" +
  "/* Sean 11/08/15 Skyscape adhoc css to fix gradient loading*/\n" +
  ".lftnv_wr .lftnv_bg{\n" +
  "  background: none;\n" +
  "}\n" +
  "/*end skyscape adhoc css */\n" +
  "  </style>\n" +
  "\n" +
  "  <link rel=\"stylesheet\" href=\"http://o.aolcdn.com/os_merge/?file=/aol/twist/css/twist.base.css&file=/aol/twist/css/twist.thumbs.css&file=/aol/twist/css/twist.arrows.css\"/>\n" +
  "  <link rel=\"stylesheet\" href=\"http://www.blogsmithmedia.com/www.aol.com/assets-66931423b68171396023fb2b43a7b13b/css/modules/storyOptimizer.css\"/>\n" +
  "  <link rel=\"stylesheet\" href=\"http://o.aolcdn.com/os/alura/dev/css/slick.css\"/>\n" +
  "\n" +
  "<script src=\"http://o.aolcdn.com/ads/adsWrapper.js\"></script>\n" +
  "<script>\n" +
  "adSetAdURL(\"/_uac/adpage.html\");\n" +
  "adSetOthAT('kvcmsid=bsd:21236883;kvcategory=news;kveditags=;kventryid=21236883;kvblogger=isabelle-chapman;');\n" +
  "adSetMOAT('1');\n" +
  "</script>\n" +
  "\n" +
  "  <script type=\"text/javascript\" src=\"http://portal.aolcdn.com/o.aolcdn.com/fonts/faw1kht.js\"></script>\n" +
  "<script type=\"text/javascript\">try{Typekit.load();}catch(e){}</script>\n" +
  "\n" +
  "<script type=\"text/javascript\">var pops_cb=\"0_column_rm_on\";</script>\n" +
  "<script>artEnableAdRefresh=true;artRefreshCnt=3;</script><script type=\"text/javascript\">String.prototype.trim=function(){return this.replace(/^\\s*/,\"\").replace(/\\s*$/,\"\")};function gC(a){var e=document.cookie.split(\";\");for(var b=0;b<e.length;b++){var d=e[b].trim().split(\"=\");if(d[0]==a){return d[1]}}return null}var de=\"www.aol.de\",fr=\"www.aol.fr\";var ius=new Array(de,fr);var TZs=new Array();TZs={0:{},1:{de:de,\"de-at\":de,\"de-li\":de,\"de-lu\":de,\"de-ch\":de,fr:fr,\"fr-be\":fr,\"fr-lu\":fr,\"fr-mc\":fr,\"fr-ch\":fr},2:{de:de,\"de-at\":de,\"de-li\":de,\"de-lu\":de,\"de-ch\":de,fr:fr,\"fr-be\":fr,\"fr-lu\":fr,\"fr-mc\":fr,\"fr-ch\":fr},3:{},4:{},5:{},6:{},7:{},8:{},9:{},10:{},11:{},12:{},13:{},\"-12\":{},\"-11\":{},\"-10\":{},\"-9\":{},\"-8\":{},\"-7\":{},\"-6\":{},\"-5\":{},\"-4\":{},\"-3\":{},\"-2\":{},\"-1\":{}};var offSet=String(-(new Date().getTimezoneOffset()/60));var langloc=String(nav_lang());var fqdn;if(TZs[offSet]&&TZs[offSet][langloc]){fqdn=TZs[offSet][langloc]}var doIr=true;if(typeof iro!=\"undefined\"){for(var i in iro){if(iro[i]==fqdn){doIr=false}}}if(gC(\"intlr\")==\"0\"){doIr=false}if(!gC(\"intlRedirBp\")&&doIr){var ckNm=\"l18nUrl\";var myLclUrl=gC(ckNm);if(myLclUrl!=null){for(i=0;i<ius.length;i++){if(myLclUrl.indexOf(ius[i])>=0){window.location.href=myLclUrl+\"?r=\"+document.domain;break}}}else{if(fqdn){fqdn=\"http://\"+fqdn;document.cookie=ckNm+\"=\"+fqdn+\";\";window.location.href=fqdn+\"?r=\"+document.domain}}}function nav_lang(){if(typeof(navigator.language)==\"string\"){return(navigator.language.toLowerCase())}else{if(typeof(navigator.userLanguage)==\"string\"){return(navigator.userLanguage.toLowerCase())}else{return 0}}};</script>\n" +
  "<meta data-nothing=''></meta>\n" +
  "<script type=\"text/javascript\">var tidEnabled=\"true\";var tidUrl=\"https://tacoda.at.atwola.com/atx/sync/hmpg/hp1id/default\";var tidCookieTimeout=\"259200\";var tidCookieRefreshBeforeExpiredTime=\"1\";var getTidNoRdrBsDmnLstApnd=\"www.aol.com\";var tidRdrCkiBsNm=\".aol.com\";var segEnabled=\"false\";var segGenerationUrl=\"/ids.jsp\";var segCookieFormatVersion=\"3\";var glbLocTZCookieVer=\"1\";var glbLocTZCookieExpSecs=\"43200\";</script>\n" +
  "<script type=\"text/javascript\">var osMergedJSCallbacks=[];var lazyLoadJavascripts=[];var beaconSwipeCallDelay=parseInt(\"\");var segUserId=\"gW3hcD8nJYwlF3CsL6QOEa4yDUQc/ZD9eRPl7X2y7tjXhOqsX80YHg==~~1443893949\",segSegId=\"M|N_3\",segColIds=\"usprod-5.dl\";(function(g){var d=g.getElementsByTagName(\"head\")[0]||g.documentElement,c={},e={},f={},b={},h={};function a(j,r){var o=b[j]=this._c,q=g.createElement(\"script\"),n=0,p,m=p=\"text/javascript\",k=\"c\",i=(function(s){s[s]=s+\"\";return s[s]!=s+\"\"})(new String(\"__count__\"));function l(s,t){function u(w){do{if(!c[w]){return 0}}while(w=b[w]);return 1}\n" +
  "var v=f[s];if(t===m){v&&v();l(h[s],k)}else{s&&u(s)&&!e[s]&&a(s,v)}}\n" +
  "f[j]=r;if(o&&!i){h[o]=j;p=k}\n" +
  "q.type=p;q.src=j;p===m&&(e[j]=1);q.onload=q.onreadystatechange=function(){if(!n&&(!q.readyState||q.readyState===\"loaded\"||q.readyState===\"complete\")){c[j]=n=1;l(j,p);q.onload=q.onreadystatechange=null;d.removeChild(q)}};d.insertBefore(q,d.firstChild);return{_c:j,getJS:a}}\n" +
  "window.Aol||(Aol={});Aol.getJS=a})(document);\n" +
  "\n" +
  "beaconIcidPattern=new RegExp('(.*)?video\\/|/videoid=');parStripList=['aLk','impref,itime'];rebuildUrlFuncs=new Array();rebuildUrlFuncs.aLk=function(c){if(parStripList==null||parStripList.length<=0){return c}var f=c;var a=new Array();for(i=0;i<parStripList.length;i+2){var b=parStripList[i];var e=parStripList[i+1];if(b==\"aLk\"&&e!==null&&e.length>0){a=parStripList[i+1].split(\",\");break}}for(i=0;i<a.length;i++){var g=a[i];var d=g+\"=.*?;|\"+g+\"=.*?$\";var h=f.match(d);if(h!=null&&h.length>0){f=f.replace(h,\"\")}}return f};osMergedJSCallbacks.push(function(){if(document.createEvent){var a=document.createEvent(\"HTMLEvents\");a.initEvent(\"BeaconLoaded\",true,true);document.dispatchEvent(a)}bN.extractIds=function(f,d){var g=f.className,e=g&&g.match(/[a-z]{1,3}id-[^ ]+/g),m,c,h,j,k=encodeURIComponent,n,p=g&&g.match(/d_clk+/g);rebuildMethodNm=g&&g.match(/rufnc-[^ ]+/g);var b=null;if(g){b=g.match(/src-[^ ]+/)}if(b!=null){if(e!=null){e.push(b[0])}else{e=b}}if(typeof d!=\"undefined\"){n=d}else{bN.set([[\"plid\",\"\",1],[\"mnid\",\"\",1],[\"mpid\",\"\",1],[\"mlid\",\"\",1],[\"lnid\",\"\",1],[\"icid\",\"\",1],[\"ncid\",\"\",1],[\"dtid\",\"\",1],[\"anid\",\"\",1],[\"apnIcidGlb\",\"\",1],[\"vid_series\",\"\",1],[\"vid_autoplay\",\"\",1],[\"vid_id\",\"\",1],[\"cid\",\"\",1],[\"src\",\"\",1],[\"d_clk\",\"\",1]]);n=\"\"}if(p!=null&&p.length>0){bN.set(\"d_clk\",1,1)}if(e){l=e.length;while(l--){m=e[l];h=m.indexOf(\"-\");m=[m.substring(0,h),m.substr(h+1)];if(n.indexOf(\"|\"+m[0]+\"|\")==-1){n+=\"|\"+m[0]+\"|\";bN.set(m[0],m[1],1);if(m[0].match(/[in]cid/g)!=null){var o=m[1];if(typeof(p_c_n)!=\"undefined\"&&o.indexOf(p_c_n)===-1){o=o+\"_\"+p_c_n}m=k(m[0])+\"=\"+k(o);if((c=f.href)&&c.indexOf(m)===-1&&c.substring(0,6)!=\"aol://\"){f.href=c+((c.indexOf(\"?\")===-1)?\"?\":\"&\")+m}}}}}(j=f.parentNode)&&bN.extractIds(j,n)};bN.rebuildHrefWithIcid=function(d,b){var c=\"\";var e=\"\";if(d.indexOf(\"?\")==-1){return d+\"?\"+b}if(d.indexOf(\"icid=\")>=0){urlParts=d.split(\"?\");params=new Array();if(d.indexOf(\"&\")==-1){params[0]=urlParts[1]}for(i=0;i<params.length;i++){param=params[i];if(param.indexOf(\"icid=\")>=0){e=e+b+\"&\";continue}e=e+param+\"&\"}return urlParts[0]+\"?\"+e.substr(0,e.length-1)}else{return d+\"&\"+b}};bN.swipe=function(b,d,c){b.direction=d;if(typeof(c)===\"undefined\"||isNaN(c)||c<0){bN.set(\"swpdir\",d);bN.click(b)}else{setTimeout(function(){bN.set(\"swpdir\",d);bN.click(b)},c)}};Aol.bind(document,\"mouseup\",function(j){var g=j.srcElement||j.target;bN.extractIds(g);var k=g.nodeName;if(k!=\"A\"){if(k.match(/IMG|TABLE|TD/)){var d=1;var f=g;while(d<=6){f=f.parentNode;if(f.nodeName==\"A\"){g=f;break}d++}}else{return}}methNm=bN.get(\"ruid\",1);if(methNm!=null&&methNm.length>0&&g.href!=null&&g.href.length>0){ruFunc=rebuildUrlFuncs[methNm];newUrl=ruFunc(g.href);if(newUrl!=null&&newUrl.length>0){bN.set(\"subUrl\",newUrl,1)}}if(typeof dsblApnIcid!=\"undefined\"&&dsblApnIcid==true){return}var c=g.className;var h=bN.get(\"anid\",1)==1?true:false;var b=bN.get(\"apnIcidGlb\")==\"true\"?true:false;if(h||b){evNdHref=g.href;if(typeof(beaconIcidPattern)==\"undefined\"){return}if(!evNdHref.match(beaconIcidPattern)){return}tmp=bN.get(\"template\");cob=bN.get(\"cobrand\");mid=bN.get(\"mnid\",1);lid=bN.get(\"lnid\",1);pid=bN.get(\"plid\",1);newIcidParam=\"icid=\"+encodeURIComponent(tmp+\"|\"+cob+\"|\"+mid+\"|\"+lid+\"|\"+pid);newHref=bN.rebuildHrefWithIcid(g.href,newIcidParam);g.href=newHref}})});</script>\n" +
  "<script>var lbCbr=\"videodeeplink\";</script>\n" +
  "<script type=\"text/javascript\">var assetsUrl='http://portal.aolcdn.com/p5/_v124.15';</script>\n" +
  "\n" +
  "\n" +
  "<script>\n" +
  "bN_cfg = {\n" +
  "h: [\"www.aol.com\"],\n" +
  "b: [\"b.aol.com\"],\n" +
  "p: {\n" +
  "'cms_src': 'us-aol.com',\n" +
  "'cobrand': 'amp-main5-article',\n" +
  "'uxi': segUserId,\n" +
  "'template': 'legacy.layout.twig'\n" +
  "},\n" +
  "upgradeIE: false\n" +
  "};\n" +
  "\n" +
  "Aol.getJS(\"http://o.aolcdn.com/os/aol/beacon.min.js\", function() {\n" +
  "for (i = 0; i < osMergedJSCallbacks.length; ++i) {\n" +
  "var callback = osMergedJSCallbacks[i];\n" +
  "callback();\n" +
  "}\n" +
  "});\n" +
  "</script></head>\n" +
  "<body class=\" \">\n" +
  "<!--[if lt IE 7]>\n" +
  "<p class=\"chromeframe\">You are using an <strong>outdated</strong> browser. Please <a href=\"http://browsehappy.com/\">upgrade your browser</a> or <a href=\"http://www.google.com/chromeframe/?redirect=true\">activate Google Chrome Frame</a> to improve your experience.</p>\n" +
  "<![endif]-->\n" +
  "  <noscript>\n" +
  "<meta HTTP-EQUIV=\"refresh\"\n" +
  "  content=\"0;url='http://www.aol.com/article/2015/09/16/syrias-assad-blames-west-for-refugee-crisis/21236883/?&Mo%20...%3C/p%3E%3Cp%3E%3Cstrong%3ECategories:%20%3C/strong%3Eblog%3C/p%3E%3Cp%3E%3Cstrong%3ELanguage:%20%3C/strong%3Een%3C/p%3E%3Cbutton%20data-url=&ModPagespeed=noscript'\"/>\n" +
  "<style><!--\n" +
  "table, div, span, font, p {\n" +
  "display: none\n" +
  "}\n" +
  "\n" +
  "--></style>\n" +
  "<div style=\"display:block\">Please click <a\n" +
  "href=\"http://www.aol.com/article/2015/09/16/syrias-assad-blames-west-for-refugee-crisis/21236883/?&Mo%20...%3C/p%3E%3Cp%3E%3Cstrong%3ECategories:%20%3C/strong%3Eblog%3C/p%3E%3Cp%3E%3Cstrong%3ELanguage:%20%3C/strong%3Een%3C/p%3E%3Cbutton%20data-url=&ModPagespeed=noscript\">here</a>\n" +
  "if you are not redirected within a few seconds.\n" +
  "</div>\n" +
  "</noscript>\n" +
  "<div id=\"state-indicator\" class=\"state-indicator\"></div>\n" +
  "<script type=\"text/javascript\">if (typeof AOL === \"undefined\") {\n" +
  "AOL = {};\n" +
  "}\n" +
  "if (!Array.prototype.indexOf) {\n" +
  "Array.prototype.indexOf = function (a, b) {\n" +
  "var c = this.length >>> 0;\n" +
  "b = +b || 0;\n" +
  "if (Math.abs(b) === Infinity) {\n" +
  "b = 0\n" +
  "}\n" +
  "if (b < 0) {\n" +
  "b += c;\n" +
  "if (b < 0) {\n" +
  "b = 0\n" +
  "}\n" +
  "}\n" +
  "for (; b < c; b++) {\n" +
  "if (this[b] === a) {\n" +
  "return b\n" +
  "}\n" +
  "}\n" +
  "return -1\n" +
  "}\n" +
  "}\n" +
  ";\n" +
  "function getDeviceState() {\n" +
  "var a = document.getElementById(\"state-indicator\");\n" +
  "var b;\n" +
  "if (a.currentStyle) {\n" +
  "b = a.currentStyle.zIndex;\n" +
  "} else {\n" +
  "b = parseInt(window.getComputedStyle(a).getPropertyValue(\"z-index\"), 10)\n" +
  "}\n" +
  "return b\n" +
  "}\n" +
  ";\n" +
  "function getMn(g, f, a, i, b, d, des) {\n" +
  "var e = getDeviceState();\n" +
  "var c = {};\n" +
  "c.mns = g;\n" +
  "c.mn = f;\n" +
  "c.sps = a;\n" +
  "c.div = i;\n" +
  "c.w = b;\n" +
  "c.h = d;\n" +
  "c.des = des;\n" +
  "if (a.indexOf(e) === -1) {\n" +
  "f = null\n" +
  "}\n" +
  "if (g !== null && g[e] !== null && typeof(g[e]) !== \"undefined\" && g[e] !== \"\") {\n" +
  "f = g[e]\n" +
  "}\n" +
  "c.cur = f;\n" +
  "hmpg_ads.push(c);\n" +
  "AOL.hmpg_ads.push(c);\n" +
  "return f\n" +
  "}\n" +
  ";\n" +
  "var hmpg_ads = new Array();\n" +
  "AOL.getDeviceState = getDeviceState;\n" +
  "AOL.hmpg_ads = hmpg_ads;\n" +
  "AOL.responsiveEnabled = true;\n" +
  "AOL.isTablet = false;\n" +
  "AOL.isWurflMobile = false;\n" +
  "AOL.isMobileSwipe = false;\n" +
  "AOL.pageType = \"article\";\n" +
  "AOL.cobrand = \"main5-article\";\n" +
  "AOL.hostname = \"portal-fe5-m412.websys.aol.com\";\n" +
  "AOL.timestamp = \"1435795101528\";\n" +
  "AOL.collapseModuleFeatureEnabled = \"false\";\n" +
  "AOL.enableMobileSearchFocus = false;\n" +
  "AOL.articleOverlayActive = false;</script>\n"+

		               "</div>\n" +
  "<div class=\"mlid-topbanner\">\n" +
  "</div>\n" +
  "<div class=\"mlid-mobileviewlink\">\n" +
  "<div class=\"mpid-1 \">\n" +
  "<div id=\"mobileweb\" class=\"mnid-mobileviewlink plid-0\">\n" +
  "<br><br><a name=\"om_mobileview\" href=\"http://www.aol.com/?mobile=true\" class=\" lnid-sec1_lnk1\">Back to\n" +
  "Mobile View</a></div>\n" +
  "</div>\n" +
  "</div>\n" +
  "<div class=\"mlid-netbanner\">\n" +
  "<div class=\"mpid-2 resp-show1024 resp-show768 resp-hide\">\n" +
  "<span class=\"dn\" id=\"banner-delay\">0</span>\n" +
  "<span class=\"dn\" id=\"banner-clickset\"></span>\n" +
  "<span class=\"dn\" id=\"campaign-cookie-name\">nb_cid</span>\n" +
  "<span class=\"dn\" id=\"nb-click-tracking\">nb_clickOther</span>\n" +
  "<span class=\"dn\" id=\"nb-ttid\">-tt-nb</span>\n" +


  "<span class=\"dn\" id=\"nb-large-image-onclick\">this.style.behavior='url(#default#homepage)';this.setHomePage('http://www.aol.com/?mtmhp=optinnewaol</span>\n" +
  "<span class=\"dn\" id=\"nb-ishpslot\">network-banner-promo</span>\n" +
  "<span class=\"dn\" id=\"nb-campaign-type\">mtmhpBanner</span>\n" +
  "\n" +
  "<div class=\"mpid-1 resp-show1024 resp-show768 resp-hide\">\n" +
  "<div class=\" plid-0 cid-optinnewaol\" id=\"network-banner\" style=\"display: block;\">\n" +
  "<div class=\"mnid-network-banner-promo bannerw\"\n" +
  " style=\"height:30px;background:transparent url(http://portal.aolcdn.com/p5/forms/58554/x129cf230-b410-4f78-b07f-ff724e2cb6f7.png.pagespeed.ic.7jt9P9wGlv.png) repeat-x\">\n" +
  "<div class=\"banner-grid\" style=\"height:30px;\">\n" +
  "<div class=\"banner-inner\" style=\"height:30px;\">\n" +
  "<div id=\"banner-centered-promo\">\n" +
  "<div class=\"largeimage\">\n" +
  "<a name=\"om_nb_largeimage\" onclick=\"omn.omcl('nb_clickOther');\"\n" +
  "   class=\"icid-acm50mtmhpbannertest\" href=\"/new\"><img\n" +
  "alt=\"New AOL banner\"\n" +
  "src=\"data:image/png;base64,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\"></a>\n" +
  "</div>\n" +
  "</div>\n" +
  "<div style=\"\" id=\"banner-promo\">\n" +
  "<div class=\"none promoff\"></div>\n" +
  "</div>\n" +
  "<div id=\"banner-close\"\n" +
  " style=\"background:transparent url(http://portal.aolcdn.com/p5/forms/58554/xc55a994f-8e7c-4004-a998-8ea06569f5fe.png.pagespeed.ic.VdplH_DCAw.png) 60px 0px no-repeat\">\n" +
  "<a style=\"\"></a></div>\n" +
  "</div>\n" +
  "</div>\n" +
  "</div>\n" +
  "</div>\n" +
  "</div>\n" +
  "</div>\n" +
  "</div>\n" +
  "<div class=\"mlid-topad\" id=\"topadw\">\n" +
  "</div>\n" +
  "<div id=\"themes\">\n" +
  "<div class=\"mpid-1 \">\n" +
  "<div class=\"visNum dn\">14</div>\n" +
  "<div id=\"themew\" class=\"promo\"></div>\n" +
  "</div>\n" +
  "</div>  <div id=\"pgbg\" class=\"aol-global-header-true light aolv2-true\">\n" +
  "\n" +
  "  \n" +
  "<div id=\"global-header-bg\" class=\"light\"></div>\n" +
  "<div id=\"aol-header\" class=\"aol-global-header light\">\n" +
  "  <div class=\"mpid-2 \">\n" +
  "<div id=\"headerlogo-global\" class=\"mnid-logo plid-538243 globalHeaderLogo\">\n" +
  "  <div id=\"mobilegridlogosrc\" class=\"dn\"\n" +
  " data-logodark=\"http://portal.aolcdn.com/p5/forms/26319/2b9734c1-362f-44c0-ae7a-bf6315ed2f6c.png\"\n" +
  " data-logomain=\"http://portal.aolcdn.com/p5/forms/56191/d3a8d933-16c2-4827-8663-b101a655dc40.png\">\n" +
  "http://portal.aolcdn.com/p5/forms/26319/4480a515-748d-418d-870e-064d5af6be66.png\n" +
  "  </div>\n" +
  "<script type=\"text/javascript\">var logoT = \"Back to AOL.com\";\n" +
  "var logoH = \"/?icid=aolcomlogo20p\";</script>\n" +
  "  <a id=\"headerlogo-globallink\"\n" +
  " data-mobile-canvas=\"http://portal.aolcdn.com/p5/forms/191/44a761b9-44c1-4b0a-809d-f0781b27ba0c.jpg\"\n" +
  " title=\"Back to AOL.com\" name=\"om_headerlogo-global\"\n" +
  " class=\"lnid-sec1_lnk1  globalHeaderLogoLink aol-logo\" href=\"/?icid=aolcomlogo20p\"\n" +
  " style=\"background: none;\"><img alt=\"Back to AOL.com\"\n" +
  "src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAALIAAABFCAMAAADZw0z7AAACrFBMVEUAAAAzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMnxZ6xAAAA43RSTlMAAQIDBAUGBwgJCgsMDQ4PEBESExQVFhcYGRobHB0eHyAhIiMkJSYnKCkqKy0uLzAxMzQ1Njg5Ojs8PT5AQUJDREVGR0hKTU5PUFJTVFVXWFpbXV5fYGFiY2RlZmdpamttbm9wcnN0dnh5ent8fX6AgYKDhIWGh4iJiouMjY6RkpOUlZaYmZqbnJ2foKGipKWnqKqrrK2vsLGys7S1tre4ubq7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\"></a>\n" +
  "</div>\n" +
  "  </div>\n" +
  "  <div class=\"mpid-3 \">\n" +
  "<div id=\"search-shadow\" style=\"display: none;\"></div>\n" +
  "<div class=\"mnid-header-search plid-0\">\n" +
  "  <div id=\"searchbox-container\">\n" +
  "<form id=\"aol-header-search\" action=\"http://search.aol.com/aol/search\" method=\"get\"\n" +
  "class=\"search search-3 tabs-0\">\n" +
  "  <input type=\"hidden\" id=\"header-ghostText\" value=\"\">\n" +
  "  <input type=\"hidden\" id=\"header-searchIcon\" value=\"\">\n" +
  "  <input type=\"hidden\" id=\"header-enabledTerms\" name=\"enabled_terms\" value=\"\">\n" +
  "  <input type=\"hidden\" id=\"header-s_it\" name=\"s_it\" value=\"comsearch-b\">\n" +
  "  <input type=\"hidden\" id=\"header-s_it_h\" value=\"comsearch-b\">\n" +
  "  <input type=\"hidden\" id=\"header-s_it_r\" value=\"comsearch-b\">\n" +
  "  <input type=\"hidden\" id=\"header-s_it_f\" value=\"comsearch-b\">\n" +
  "  <input type=\"hidden\" id=\"header-s_it_s\" value=\"comsearch-b\">\n" +
  "  <input type=\"hidden\" id=\"header-initSmartSearch\" value=\"false\">\n" +
  "  <input type=\"hidden\" id=\"header-apiDictionary\" value=\"\">\n" +
  "  <input type=\"hidden\" id=\"header-initFocus\" value=\"true\">\n" +
  "  <input type=\"hidden\" id=\"header-preserveGhostText\" value=\"\">\n" +
  "  <input type=\"hidden\" id=\"header-ssExtraParams\" value=\"s_qt=ac&amp;s_chn=prt_main5\">\n" +
  "  <input type=\"hidden\" id=\"header-sExtraParams\" value=\"s_chn=prt_main5\">\n" +
  "  <input type=\"hidden\" id=\"header-apiIt\" value=\"\">\n" +
  "  <input type=\"hidden\" id=\"header-apiUrl\"\n" +
  "   value=\"http://autocomplete.search.aol.com/autocomplete/get\">\n" +
  "  <input type=\"hidden\" id=\"header-apiCount\" value=\"8\">\n" +
  "  <input type=\"hidden\" id=\"header-apiQueryParam\" value=\"q\">\n" +
  "  <input type=\"hidden\" id=\"header-enableBestMatch\" value=\"true\">\n" +
  "  <input type=\"hidden\" id=\"header-bestMatchQuery\" value=\"bm_chan=2&amp;bm_max=1&amp;bm_limit=4\">\n" +
  "  <input type=\"hidden\" id=\"header-bestMatchPrefix\" value=\"in\">\n" +
  "  <input type=\"hidden\" id=\"header-bestMatchAction\" value=\"http://search.aol.com/aol/tracking\">\n" +
  "  <fieldset>\n" +
  "<input class=\"\" type=\"text\" value=\"\" id=\"aol-header-query\" name=\"q\" maxlength=\"150\"\n" +
  " title=\"Search the Web\" autocomplete=\"off\">\n" +
  "<input class=\"om_header-searchbutton lnid-sec2_lnk1\" type=\"submit\" value=\"Search\"\n" +
  " id=\"aol-header-search-button\" onclick=\"omn.omo('header-searchbutton');\"\n" +
  " title=\"Search the Web\">\n" +
  "  <div id=\"aol-header-search-results\"></div>\n" +
  "  </fieldset>\n" +
  "</form>\n" +
  "  </div>\n" +
  "</div>\n" +
  "  </div>\n" +
  "  <div id=\"user-menu-wrapper\">\n" +
  "<div class=\"mpid-4 \">\n" +
  "<div id=\"usrMnu\" class=\"mnid-user-menu plid-0 unauthenticated\" hidedelay=\"200\" showdelay=\"200\">\n" +
  "<p class=\"username unauth\">\n" +
  "  <a href=\"#\" class=\"lnid-sec1_lnk1\" id=\"amp-auth\">Sign In <span class=\"signindivider\">|</span> Sign Up</a>\n" +
  "</p>\n" +
  "<ul class=\"usrSns\">\n" +
  "</ul>\n" +
  "<div class=\"usrMnuLst mnid-user-menu plid-0\">\n" +
  "  <div class=\"topLine\"></div>\n" +
  "<ul>\n" +
  "<li>\n" +
  "<a class=\"lnid-sec2_lnk1\" href=\"https://account.aol.com/account/settings/getDdTrainerInputStart\">My Account</a>\n" +
  "</li>\n" +
  "<li>\n" +
  "<a class=\"makeHomepage url-http://www.aol.com/?mtmhp=txtlnkusaolp00000051&xicid=acm50options_mtmhp om-mtmhpGreetingIE lnid-sec2_lnk2\" href=\"http://www.aol.com/?molhp=txtlnkusaolp00000051&icid=acm50options_mtmhp\">Set AOL as Homepage</a>\n" +
  "</li>\n" +
  "</ul>\n" +
  "</div>\n" +
  "  </div>\n" +
  "  </div>\n" +
  "  </div>\n" +
  "  <div id=\"quick-nav-wrapper\">\n" +
  "<div class=\"mpid-5 \">\n" +
  "  <div id=\"quick-nav-global_w\">\n" +
  "<div id=\"quick-nav-global\" quick-nav-icons=\"1\" class=\" quicknav quick-nav1\">\n" +
  "  <a class=\"mobile-search\" title=\"\">\n" +
  "<img class=\"noion\" alt=\"Search\"\n" +
  "   src=\"http://portal.aolcdn.com/p5/forms/67023/f5603f5b-bbc6-41bb-994d-91ca4e937f69.png\"></a>\n" +
  "  <b style=\"width: 49.0%;\">\n" +
  "  <a href=\"http://mail.aol.com/?icid=aol.com-nav\" target=\"_blank\" class=\"pausedl qnpos1 qn auth-0 show-300 hide-300 thresh-500 mnid-qnav-mail_quick-nav-global plid-628891 lnid-sec1_lnk1\" name=\"om_quicknav_mail_global\" id=\"mailpreview\"><img alt=\"AOL Mail\" class=\"noion\" src=\"http://portal.aolcdn.com/p5/forms/67023/f5603f5b-bbc6-41bb-994d-91ca4e937f69.png\"><span></span></a>\n" +
  "  </b>\n" +
  "  <b style=\"width: 49.0%;\">\n" +
  "<i class=\"last\"><a href=\"#\" onclick=\"return false;\"\n" +
  "   class=\" qnpos3 mnid-qnav-quick-nav-menu_quick-nav-global plid-0 lnid-sec1_lnk3 hasdropdown dropdown-quick-nav-menu-icons\"\n" +
  "   name=\"om_quicknav_quick-nav-menu_global\" title=\"\"><img alt=\"\"\n" +
  "  class=\"noion\"\n" +
  "  src=\"http://portal.aolcdn.com/p5/forms/67023/f5603f5b-bbc6-41bb-994d-91ca4e937f69.png\"></a>\n" +
  "</i></b>\n" +
  "  <a class=\"mobile-menu mnid-user-menu plid-0\" title=\"\"><img class=\"noion\" alt=\"Menu\"\n" +
  "  src=\"http://portal.aolcdn.com/p5/forms/67023/f5603f5b-bbc6-41bb-994d-91ca4e937f69.png\"></a>\n" +
  "</div>\n" +
  "  </div>\n" +
  "</div>\n" +
  "<div id=\"preview-container\"></div>\n" +
  "  </div>\n" +
  "</div>\n" +
  "<div id=\"aol-hnav\">\n" +
  "<div class=\"mlid-topad\">\n" +
  "<div class=\"mpid-1 resp-show1024 resp-show900 resp-show768 resp-show754 resp-hide\">\n" +
  "<div id=\"topad\">\n" +
  "<div class=\"adwrap di refreshNO\" id=\"fi_1048556381\"></div>\n" +
  "<script type=\"text/javascript\">var mn = getMn({\n" +
  "1450: '93320501',\n" +
  "1265: '93320501',\n" +
  "1108: '93320501',\n" +
  "900: '93320501',\n" +
  "754: '93320501',\n" +
  "480: ''\n" +
  "}, \"93320501\", [1024, 986, 900, 768, 754], \"fi_1048556381\", \"LB\", \"LB\", true);\n" +
  "if (mn !== null) {\n" +
  "htmlAdWH(mn, 'LB', 'LB', 'F', 'fi_1048556381');\n" +
  "}</script>\n" +
  "</div>\n" +
  "</div>\n" +
  "</div>\n" +
  "<div class=\"mpid-8 \">\n" +
  "<div class=\"comment-body \">\n" +
  "<b class=\"avatar\"><img src=\"http://expapi.oscar.aol.com/expressions/get?f=native&type=buddyIcon&t=rweruss&defaultId=00050201d20472\" onerror=\"this.src='http://o.aolcdn.com/os/aolcom/media/noprofile.jpeg';\" /><b class=\"social-icon aol\"></b></b>\n" +
  "<b class=\"display-name\"> Daddy O </b>\n" +
  "<b class=\"date\">September 16 2015 at 3:04 PM</b>\n" +
  "<p>..In the late 60's early 70&quot;s we were made to read books called Brave New world by Aldous Huxley and 1984 by George Orwell.<br/>These books were supposed to enlighten us not to do what is happening now.<br/>I see people that read theses books shouting out warnings....Not many people are listening and the ones that are don't see a problem with it. I loved america the way it was to bad most will never experience freedom for all they know is control and feel scared to fend on their own. The government takes care of every situation and there is a law against everything. <br/> We no longer believe God is watching that's okay big brother is.</p>\n" +
  "\n" +
  "<b class=\"comment-meta\">\n" +
  "\n" +
  "<a id=\"aol-comments-x-CT4EMQ\" class=\"report-abuse button\" href=\"#\">Flag</a>\n" +
  "<a id=\"aol-comments-s-CT4EMQ\" class=\"share button\" href=\"#\">Share</a>\n" +
  "<div class=\"share-bar\" data-permalink=\"#aolc=CT4EMQ\">\n" +
  "<a name=\"comment-share\" class=\"comment-share\" href=\"#aolc=CT4EMQ\">Share</a>\n" +
  "<div class=\"sprite-bg sprite-up-arrow-white\"></div>\n" +
  "</div>\n" +
  "<a id=\"aol-comments-r-CT4EMQ\" class=\"reply button\" href=\"#\">Reply</a> \n" +
  "  <b class=\"rating-reply\">\n" +
  "<b class=\"rank\">+35</b>\n" +
  "<b id=\"aol-comments-t-CT4EMQ\" class=\"rate-comment\">\n" +
  "<b class=\"rate-up\">rate up</b>\n" +
  "</b>\n" +
  "</b>\n" +
  "\n" +
  "</b>\n" +
  "</div>\n" +
  "<b class=\"toggle-thread\"><span class=\"toggle-divider\"></span><a id=\"aol-comments-o-CT4EMQ\" class=\"toggle-link\" rel=\" CT421w  CT5RXQ  CT43Nw  CT5IOw  CT5Kaw  CT3-qQ  CT4xdw  CT6iXg \" href=\"#\"> 5 replies </a></b>\n" +
  "<div id=\"aol-comments-thread-CT4EMQ\" class=\"thread level-one\"></div>\n" +
  "</div>\n" +
  "<div id=\"aol-comments-CT3rpw\"  class=\"comment\">\n" +
  "<div class=\"comment-body \">\n" +
  "<b class=\"avatar\"><img src=\"http://expapi.oscar.aol.com/expressions/get?f=native&type=buddyIcon&t=royfaustjr&defaultId=00050201d20472\" onerror=\"this.src='http://o.aolcdn.com/os/aolcom/media/noprofile.jpeg';\" /><b class=\"social-icon aol\"></b></b>\n" +
  "<b class=\"display-name\"> royfaustjr </b>\n" +
  "<b class=\"date\">September 16 2015 at 3:22 PM</b>\n" +
  "<p>It is OBAMA'S fault...</p>\n" +
  "\n" +
  "<b class=\"comment-meta\">\n" +
  "\n" +
  "<a id=\"aol-comments-x-CT3rpw\" class=\"report-abuse button\" href=\"#\">Flag</a>\n" +
  "<a id=\"aol-comments-s-CT3rpw\" class=\"share button\" href=\"#\">Share</a>\n" +
  "<div class=\"share-bar\" data-permalink=\"#aolc=CT3rpw\">\n" +
  "<a name=\"comment-share\" class=\"comment-share\" href=\"#aolc=CT3rpw\">Share</a>\n" +
  "<div class=\"sprite-bg sprite-up-arrow-white\"></div>\n" +
  "</div>\n" +
  "<div class=\"footer-links-row2\" id=\"om_footer_row2_\">\n" +
  "</div>\n" +
  "<div class=\"footer-links-row3\" id=\"om_footer_row3_\">\n" +
  "</div>\n" +
  "<div id=\"aol-copyright\">\n" +
  "<span class=\"copy-text\">© 2015&nbsp; AOL Inc.</span>\n" +
  "<a href=\"?icid=acm50footerlogo\"><img class=\"footer-stamp-img\"\n" +
  " src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAC0AAAAnCAYAAACbgcH8AAAACXBIWXMAAAsTAAALEwEAmpwYAAAAIGNIUk0AAHolAACAgwAA+f8AAIDpAAB1MAAA6mAAADqYAAAXb5JfxUYAAARjSURBVHja1JltiFZVEMd/dzN3TbDNNnF7EdPUEj9kq5atQiIW9S3BQCx7oyz7YBFq0jtSEFH4RRBxi7QiipZAJdpcBdPtZdmitkwrdE3dWFJ31TRX3b19mSuzh3Pmnrv7UDlwH+69Z848/2fOnP+ZmSdJ05QLTQYBJEli6XwFnAK2Ao1AM3DuvwKcpql8hOUKIHWu48Am4Cng4v8j6Pke0Nl1qJ/fWzFQ0INydGYbY1v6CfgIsAf4EtgJNAFtSqcM6M13d1jaDE/fVxDwOKA2YKsdqFN7qAF4Ari6aHhcZwBOgeoCgGeJ93Yb9j4FRnneNwPPAZNiQD9ufMGPwMPAY8CVOYAvAX7NcUAKPAs8aYy3A0ke6I8NA6uA/XLfK0u6ArjBY2dVBOBUVuMLY3x1nqcvAo4aBl40xvYAlWJnpvyoPMDngNFAj6EzOwNdFvDyTcBlgbEeBconZ4AuYYo6IImI+RZgjjBHSBYDr1nsscL4xTuAn4zxlWLj9ciwSIE3ZSPm6R22wqPRmLhePNkFdHvGJwO35Cy1ez0IdCi7Ib2WEOghwGlj4gyl+4Aztk/CYncBwBnoPnQcuOpDMT0DKA/E1V/CFJl8CJxQz/XAC8CEAhy+F7g1Unc/gcCfY0z6xMnwTgHvqueDwLKCJ2ULcGcR0L7w+NZYnpkeQ5NlrAvY5ej/AiwERoruUOAO4DOls1HmxoTHXF9MVxm8+rPSGw/c7HjL1d8kp2FIMq7/ugDoGh/oe4wJTyu9pcAr6nmRo3sAGOZs7mmSn2vZAHQWAF3lA702oNwtq5DJduAH9TwMOKn0dVzfKHSW2VmgxmpUaOWBPhk6xvcGJryvdC5XHDxava9zuDqTBsdWp6p4ygRMDOhdvmN8DHBtIP7WOt5pBb4Hpqj369R9p7of69iqVKvWK0VBjPzuKwIeNRKgJNJwq8ypVe/WO/Z+U/YqVK7i8/Rx4G2pktb4wuOjiA2YJ0tULqEZabOA+84JnblGTJ92Ut1rMtBJmqYkSVIG/AkM92RsVwGH5Xm6Q3UA30idh8xvl5ivAv7OqRdbgInAMSdzzCqWaVa5VRPw8gfOnCaPTpOj816WsEv55JNKJ6vzefqYhyL7VOOhqnsw8JLcl4unXZkunH1Wnv+QOdWyHzbICdgBXCqn6kMOoAr1PZpGm4SVRgEvi43znv68YFaWVzYBzCuhzbNSTfWhvMoSNYDWAdvkvraEjaVDsk/QWd5UWc57gbfOZ1PF5IDDNB2SVxeRHjO7i2jWjBGeDC1Zo+TX2fPtATvDgbuAV6WJ2ZNTcQ+WA2kWcD/wvLQropo1NTlxNk6O8QZZnRgZEVNxD6QBudIw3urolkeCfsSweTSmC2u1ELLTyqpgtHRHgrZsblS0aUoI9AQ5qUJS308WaBbuHpjNQHg8YyzjvhJQ2PXAcmn39sqGHjLQpvpQ4G7gHU977I0SN/erpW4s2T8BWV/vNmkktgWK23/174vkQvx3658BAMtK+LRyGgRfAAAAAElFTkSuQmCC\"\n" +
  " alt=\"Aol\"></a>\n" +
  "<span class=\"rights-text\">All Rights Reserved.</span>\n" +
  "</div>\n" +
  "</div>\n" +
  "</div>\n" +
  "</div>\n" +
  "<div class=\"mpid-6 \">\n" +
  "</div></div>\n" +
  "  </div>\n" +
  "</div>\n" +
  "\n" +
  "<div id=\"scrollcurtain\"\n" +
  " class=\"clearfix plid-0 mnid-scrollcurtain pos-top tt-#aol-header-search tth-true ttx--5 tb-#aol-footer-search cnvs- mct- scb- nopushdown curtaintop canvasless\"\n" +
  " style=\"display: none;\">\n" +
  "<div id=\"scrollcurtain-content\">\n" +
  "<div id=\"scrollcurtain-logos\" class=\"mnid-scrollcurtain-logos plid-0 globalHeaderLogo\">\n" +
  "<div id=\"mobilegridlogosrc\" class=\"dn\"\n" +
  " data-logodark=\"http://portal.aolcdn.com/p5/forms/26319/2b9734c1-362f-44c0-ae7a-bf6315ed2f6c.png\"\n" +
  " data-logomain=\"http://portal.aolcdn.com/p5/forms/56191/d3a8d933-16c2-4827-8663-b101a655dc40.png\">\n" +
  "http://portal.aolcdn.com/p5/forms/26319/4480a515-748d-418d-870e-064d5af6be66.png\n" +
  "</div>\n" +
  "<a id=\"scrollcurtain-logoslink\"\n" +
  "   data-mobile-canvas=\"http://portal.aolcdn.com/p5/forms/191/44a761b9-44c1-4b0a-809d-f0781b27ba0c.jpg\"\n" +
  "   title=\"Back to AOL.com\" name=\"om_scrollcurtain-logos\"\n" +
  "   class=\"lnid-sec1_lnk1  globalHeaderLogoLink aol-logo\" href=\"/?icid=aolcomlogosc20p\"><img\n" +
  "alt=\"Back to AOL.com\"\n" +
  "src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAALIAAABFCAMAAADZw0z7AAACrFBMVEUAAAAzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMzMnxZ6xAAAA43RSTlMAAQIDBAUGBwgJCgsMDQ4PEBESExQVFhcYGRobHB0eHyAhIiMkJSYnKCkqKy0uLzAxMzQ1Njg5Ojs8PT5AQUJDREVGR0hKTU5PUFJTVFVXWFpbXV5fYGFiY2RlZmdpamttbm9wcnN0dnh5ent8fX6AgYKDhIWGh4iJiouMjY6RkpOUlZaYmZqbnJ2foKGipKWnqKqrrK2vsLGys7S1tre4ubq7vL2+v8DBwsPExcbHyMnLzM3Oz9DR0tPU1dbX2Nna3N7f4OHi4+Tl5ufo6err7O3u7/Dx8vP09fb3+Pn6+/z9/ruZZ3MAAAXCSURBVGjezZr7X1RFGMZZWrm5GSigFoqBSopdFAgkQ0DyEhh0gbISpSTLLqal3aQIK0K6qRgZWplKWilSEVJSkGgEiIWAGyDBzj/SCnL2zDMzhzm7Zz+f8/7GPPO+58ueubwz7/HxEVoJUds2H69ZJfUgUul2oKBuKlCb1fzIuXQgstz8yN8C8n7TI88HYjI03ezIRYhMNpkc2f9vBrnZYm7kbMLa3eZGPsJB/tjUyLM5xGQgxMzI23jIZL2JkSd0cJHrTYy8ivBtkXmRDwqQ3zUt8gyHALl3olmRNxOR5cqGsCas2VFV80vL+cbaQ28XpAZ5Gdn3TyHyd1IBrl/zWQ/td6V6443eRL6HiO0WiYSqzM7zHNq/xHvIn2og7xjPeWb5kND5cLyXkKf9p4Hc6afpa8m3aziT4dcDvIK8iY7QT/+ZqeUa8gUZxxpjvIBsaaYjbKH//FLDdc5vZFy7tNR45KV0gIuB9Kt2zBR6zrtIJGxwheHIn9ABynE2bhZmf61EygZTDEYOvYJjN49uOOcrWIwbiaT1xhiLvAH2AJvPVNi+l/GnwD4ibWdshiKfYWdbDd1UwfXLIzpsp5HIyRB8nbPtWRiLYRy/qV16kB1JBiKXQ/BIZ9sCaCvk+JUSXVZnMQw5uI93DmmB7YCzWvB26dZdT2ZnrS3+lcecaRhyPkR+eaS1GFqTGL/3WajjaWNLy+27WfW0Ycg/Q+SEkdZ0aN013sshxE5l1snnGeYkg5AXQdyO0R/K/zLd/O8k8FuPQB230h3CfsAeHxiE/B7ELbvWjmvuY+B3DDeL2zDyFEw/uicYgjwRfk2ySnDXXAs75jDo97OxFwxAnzRDkB/B66Gx02kYnl/p954F6lcytznbDUE+BVEPKsoJUIopv9dAvZO7fkLyX20EMm4ZJF+RngalizpdVNNiAz98Gd2rxwhkXH7JDEWKRSlH7QhbzRZ++OUQItRz5ABME+pU4lnQjqpv8GD2ZQjSWggR7znyA/hLvqgS30QxWjVKQYoSPOAfuluq58i4uJI4lZiGoqp0GQlSsOABzfwV1H3kGIRqV2dbfr2gqkqXsySRm4xGfgORSyl5L8quU2cIKKID7QW6W7qnyH6dyNTfrTbcvFSlS39QUvhPmCRavd1FXk102pDrXrBVqkCI02G6p8iH9CKryKB2XCO17Pd5upVEOXQju0qXb4ESy3tCIBQ/T+hADlq4LGUuXkZsJfpNGbM5IOzmIT8FnYpkkX1Xfz04kiSUzacu3dvcQFZKlxGopHMuceGSnKyURJ7jStYcxQHi/V/KBiaPudfiks6sc4EnoUtfoBzyXdS/evIGRfjcHWRX6fIZVH6PgMF4AHtUyB2kYuHlHBvbwW4adgu5XnyL3kZVGWb/yPhmSCFbf0K/564pzxP3TMlC9rDah3OVs+or/Yz6h68U8kOMo300Z/U95yayUrq8g6d+/8LK+IUZhVWDHO1RuXuMU6znBu49hbS5SpcVOtd0qxRyGMf1KPfML295Y8Gj+3T5rZC7RkzluHZdFcKZN2dv4duQuHS5UQ/xHsnL2hyeM/9p66QrgvOUuX1cnvivcEnk+wTIliammhEqQM7WKF1Ok95ABxNkCw/JHO82Z/sSplWYS9n6NUqXcT2SyDnS5R0bp2p6Vf5I8/KXNnaiZrnExXJTcK2OItoB7scKk5nzxiV/ITI7MtSly8QLEqPiQT2lSnb5bXfmJk8wre+I02ob80M6IlXyrPrxiDsX6ysIV2KAbGdjAxM2UeMowF7IU6VL/1e1s5W9YTrL7lOa2f02UeO0wbFMpjuULuO+0ai536v/44YIqgJaeh17uadV8R3Je+1aOdno+DvCBz6da3XnExJbibKBdY7Og5JytJs1D4mFTH/2e6OorVhzIWd3xglDFtDxClCP3l7nXOx6Dz9u8/GmhWe/tK+h/bLz9NHZVFX0cLSH4azBruPI/yMtEh8grClEAAAAAElFTkSuQmCC\"></a>\n" +
  "</div>\n" +
  "<div id=\"search-shadow\"></div>\n" +
  "<div class=\"mnid-scrollcurtain-search plid-0\">\n" +
  "<div id=\"searchbox-container\">\n" +
  "<form id=\"aol-scrollcurtain-search\" action=\"http://search.aol.com/aol/search\" method=\"get\"\n" +
  "  class=\"search search-3 tabs-0\">\n" +
  "<input type=\"hidden\" id=\"scrollcurtain-ghostText\" value=\"\">\n" +
  "<input type=\"hidden\" id=\"scrollcurtain-searchIcon\" value=\"\">\n" +
  "<input type=\"hidden\" id=\"scrollcurtain-enabledTerms\" name=\"enabled_terms\" value=\"\">\n" +
  "<input type=\"hidden\" id=\"scrollcurtain-s_it\" name=\"s_it\" value=\"comsearch-b\">\n" +
  "<input type=\"hidden\" id=\"scrollcurtain-s_it_h\" value=\"comsearch-b\">\n" +
  "<input type=\"hidden\" id=\"scrollcurtain-s_it_r\" value=\"comsearch-b\">\n" +
  "<input type=\"hidden\" id=\"scrollcurtain-s_it_f\" value=\"comsearch-b\">\n" +
  "<input type=\"hidden\" id=\"scrollcurtain-s_it_s\" value=\"comsearch-b\">\n" +
  "<input type=\"hidden\" id=\"scrollcurtain-initSmartSearch\" value=\"false\">\n" +
  "<input type=\"hidden\" id=\"scrollcurtain-apiDictionary\" value=\"\">\n" +
  "<input type=\"hidden\" id=\"scrollcurtain-initFocus\" value=\"true\">\n" +
  "<input type=\"hidden\" id=\"scrollcurtain-preserveGhostText\" value=\"\">\n" +
  "<input type=\"hidden\" id=\"scrollcurtain-ssExtraParams\" value=\"s_qt=sa&amp;s_chn=prt_\">\n" +
  "<input type=\"hidden\" id=\"scrollcurtain-sExtraParams\" value=\"s_qt=sh&amp;s_chn=prt_\">\n" +
  "<input type=\"hidden\" id=\"scrollcurtain-apiIt\" value=\"\">\n" +
  "<input type=\"hidden\" id=\"scrollcurtain-apiUrl\"\n" +
  "   value=\"http://autocomplete.search.aol.com/autocomplete/get\">\n" +
  "<input type=\"hidden\" id=\"scrollcurtain-apiCount\" value=\"8\">\n" +
  "<input type=\"hidden\" id=\"scrollcurtain-apiQueryParam\" value=\"q\">\n" +
  "<input type=\"hidden\" id=\"scrollcurtain-enableBestMatch\" value=\"true\">\n" +
  "<input type=\"hidden\" id=\"scrollcurtain-bestMatchQuery\"\n" +
  "   value=\"bm_chan=2&amp;bm_max=1&amp;bm_limit=4\">\n" +
  "<input type=\"hidden\" id=\"scrollcurtain-bestMatchPrefix\" value=\"in\">\n" +
  "<input type=\"hidden\" id=\"scrollcurtain-bestMatchAction\" value=\"http://search.aol.com/aol/tracking\">\n" +
  "<fieldset>\n" +
  "<input class=\"\" type=\"text\" value=\"\" id=\"aol-scrollcurtain-query\" name=\"q\" maxlength=\"150\"\n" +
  "   title=\"Search the Web\" autocomplete=\"off\">\n" +
  "<input class=\"om_scrollcurtain-searchbutton lnid-sec2_lnk1\" type=\"submit\" value=\"Search\"\n" +
  "   id=\"aol-scrollcurtain-search-button\" onclick=\"omn.omo('scrollcurtain-searchbutton');\"\n" +
  "   title=\"Search the Web\">\n" +
  "\n" +
  "<div id=\"aol-scrollcurtain-search-results\"></div>\n" +
  "</fieldset>\n" +
  "</form>\n" +
  "</div>\n" +
  "</div>\n" +
  "<div id=\"scrollcurtain-quicknav_w\">\n" +
  "<div id=\"scrollcurtain-quicknav\" quick-nav-icons=\"1\" class=\"mnid-quicknav-searchcurtain quicknav quick-nav1\"\n" +
  " style=\"right: 90px;\">\n" +
  "<a class=\"mobile-search\" title=\"\"><img class=\"noion\" alt=\"Search\"\n" +
  "   src=\"http://portal.aolcdn.com/p5/forms/67023/f5603f5b-bbc6-41bb-994d-91ca4e937f69.png\"></a>\n" +
  "<b style=\"width: 49.0%;\">\n" +
  "<a href=\"http://mail.aol.com/?icid=aol.com-nav\" target=\"_blank\" class=\"qnpos1 auth-1 mailpreview show-300 hide-300 thresh-500 mnid-qnav-scrollcurtain-quicknav-mail_scrollcurtain-quicknav plid-0 lnid-sec1_lnk1 scroll-mail\" name=\"om_quicknav_mail_global\" id=\"newmailpreview\">\n" +
  "<img alt=\"AOL Mail\" class=\"noion\" src=\"http://portal.aolcdn.com/p5/forms/67023/f5603f5b-bbc6-41bb-994d-91ca4e937f69.png\">\n" +
  "<span class=''></span>\n" +
  "</a>\n" +
  "\n" +
  "<div id=\"mailoverw\">\n" +
  "<div id=\"mailover\"></div>\n" +
  "</div>\n" +
  "<div class=\"tooltip\">\n" +
  "<div class=\"tooltip-arrow sprite-bg sprite-up-arrow-tooltip\"></div>\n" +
  "<i>AOL Mail</i></div>\n" +
  "</b>\n" +
  "<b style=\"width: 49.0%;\">\n" +
  "<i class=\"last\"><a href=\"#\" onclick=\"return false;\"\n" +
  "   class=\" qnpos3 mnid-qnav-quick-nav-menu_scrollcurtain-quicknav plid-0 lnid-sec1_lnk3 hasdropdown dropdown-quick-nav-menu-icons\"\n" +
  "   name=\"om_quicknav_quick-nav-menu_global\" title=\"\"><img alt=\"\" class=\"noion\"\n" +
  "  src=\"http://portal.aolcdn.com/p5/forms/67023/f5603f5b-bbc6-41bb-994d-91ca4e937f69.png\"></a>\n" +
  "</i></b>\n" +
  "<a class=\"mobile-menu mnid-user-menu plid-0\" title=\"\"><img class=\"noion\" alt=\"\"\n" +
  "   src=\"http://portal.aolcdn.com/p5/forms/67023/f5603f5b-bbc6-41bb-994d-91ca4e937f69.png\"></a>\n" +
  "</div>\n" +
  "</div>\n" +
  "</div>\n" +
  "</div>\n" +
  "<div id=\"mail_prev_w\">\n" +
  "<div id=\"mail-arrow\"></div>\n" +
  "<div id=\"newmail_prev\" class=\"plid-0 mnid-mailpreview\">\n" +
  "</div>\n" +
  "</div>\n" +
		              "</body>\n" +
  "</html>";

String actual=parserService.stringify(html1);

System.out.println(actual);
String expected = "14 http://portal.aolcdn.com/p5/forms/26319/4480a515-748d-418d-870e-064d5af6be66.png Daddy O September 16 2015 at 3:04 PM ..In the late 60's early 70\"s we were made to read books called Brave New world by Aldous Huxley and 1984 by George Orwell.These books were supposed to enlighten us not to do what is happening now.I see people that read theses books shouting out warnings....Not many people are listening and the ones that are don't see a problem with it. I loved america the way it was to bad most will never experience freedom for all they know is control and feel scared to fend on their own. The government takes care of every situation and there is a law against everything. We no longer believe God is watching that's okay big brother is. +35 rate up royfaustjr September 16 2015 at 3:22 PM It is OBAMA'S fault... http://portal.aolcdn.com/p5/forms/26319/4480a515-748d-418d-870e-064d5af6be66.png AOL Mail";
Assert.assertTrue(actual.equals(expected));

}
}
