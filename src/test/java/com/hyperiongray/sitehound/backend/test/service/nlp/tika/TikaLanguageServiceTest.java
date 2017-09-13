package com.hyperiongray.sitehound.backend.test.service.nlp.tika;

import com.hyperiongray.sitehound.backend.service.nlp.tika.TikaService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tomas on 9/24/15.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = ApplicationSpringConfiguration.class)
public class TikaLanguageServiceTest{

//	@Autowired
//	private TikaService tikaService;

	private TikaService tikaService = new TikaService();

	private static final Logger LOGGER = LoggerFactory.getLogger(TikaLanguageServiceTest.class);

	private String text_en=	"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
			  "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\n" +
			  "<head>\n" +
			  "\n" +
			  "<title>Ammo To Go : Ammunition | Bulk and Cheap Ammo for Sale</title>\n" +
			  "<body class=\" cms-index-index cms-home cms-home\"  itemscope itemtype=\"http://schema.org/WebPage\">\n" +
			  "  <div class=\"noscript\">\n" +
			  "<div class=\"noscript-inner\">\n" +
			  " <p><strong>JavaScript seem to be disabled in your browser.</strong></p>\n" +
			  " <p>You must have JavaScript enabled in your browser to utilize the functionality of this website.</p>\n" +
			  "\n" +
			  "  \n" +
			  " </noscript>\n" +
			  " <div class=\"page\">\n" +
			  "  <div class=\"header-container\">\n" +
			  " <div class=\"header\">\n" +
			  "  <div class=\"col2-set\">\n" +
			  "<div class=\"col-1 top-nav\">\n" +
			  " <ul>\n" +
			  "  <li><a href=\"http://www.ammunitiontogo.com/brands.php\"><span>Brands</span></a></li>\n" +
			  "  <li><a href=\"http://www.ammunitiontogo.com/bragboard\"><span>Brag Board</span></a></li>\n" +
			  "  <li><a href=\"http://www.ammunitiontogo.com/aboutus.php\"><span>About Us</span></a></li>\n" +
			  "  <li><a href=\"http://www.ammunitiontogo.com/contactus.php\"><span>Contact Us</span></a></li>\n" +
			  "  <li><a href=\"http://www.ammunitiontogo.com/account.php\" class=\"my-account\"><span>My account</span></a></li>\n" +
			  "  <li><a href=\"http://www.ammunitiontogo.com/shopping_cart.php\" class=\"cart\"><span><span>Cart</span></span></a></li>\n" +
			  " </ul>\n" +
			  "\n" +
			  "<div class=\"col-2 top-search\">\n" +
			  " <form id=\"search_mini_form\" action=\"http://www.ammunitiontogo.com/search_result.php\" method=\"get\">\n" +
			  " <div class=\"top-mini-search\">\n" +
			  "  <label for=\"search\">Search:</label>\n" +
			  "  <input id=\"search\" type=\"text\" name=\"keywords\" value=\"\" class=\"input-text\" autocomplete=\"off\" spellcheck=\"false\" autocorrect=\"off\" autocapitalize=\"off\" />\n" +
			  "  <button type=\"submit\" title=\"Search\" class=\"button btn-search\"><span><span>Search</span></span></button>\n" +
			  "  <div id=\"search_autocomplete\" class=\"algoliasearch-autocomplete\" style=\"display: none;\">\n" +
			  "<div id=\"search_autocomplete_categories\">\n" +
			  "<div id=\"search_autocomplete_products\">\n" +
			  "  \n" +
			  " \n" +
			  "</form>\n" +
			  "  \n" +
			  "  <div class=\"col3-set logo-row\">\n" +
			  "<div class=\"col-1 atg-logo\">\n" +
			  " <a href=\"http://www.ammunitiontogo.com/\">\n" +
			  "  <img src=\"http://static-cdn.ammunitiontogo.com/skin/frontend/ammotogo/default/images/logo-atg.gif\" alt=\"Gun and Rifle Ammunition\" width=\"193\" height=\"136\" border=\"0\">\n" +
			  " </a>\n" +
			  "\n" +
			  "<div class=\"col-2 header-diamond-plate\">\n" +
			  " <a href=\"http://www.ammunitiontogo.com/\">\n" +
			  "  <img src=\"http://static-cdn.ammunitiontogo.com/skin/frontend/ammotogo/default/images/bkg_header_diamond_plate_atg.jpg\" alt=\"Ammunition To Go\" width=\"546\" height=\"136\" border=\"0\">\n" +
			  " </a>\n" +
			  "\n" +
			  "<div class=\"col-3 animals-slider\">\n" +
			  " <img src=\"http://static-cdn.ammunitiontogo.com/skin/frontend/ammotogo/default/images/homepage/slider/atg-pheasant.jpg\" width=\"220\" height=\"136\" alt=\"Ammunition To Go\" />\n" +
			  "\n" +
			  "  \n" +
			  "\n" +
			  "\n" +
			  "  <div class=\"main-container col3-layout\">\n" +
			  "<div class=\"main\">\n" +
			  " <div class=\"breadcrumbs\">\n" +
			  "  <ul xmlns:v=\"http://rdf.data-vocabulary.org/#\">\n" +
			  "  <li class=\"home\" typeof=\"v:Breadcrumb\">\n" +
			  "Home \n" +
			  " </li>\n" +
			  "</ul>\n" +
			  "  <div class=\"col-wrapper\">\n" +
			  "  <div class=\"col-left sidebar\"><div class=\"block block-side-nav\">\n" +
			  " <div class=\"block-title\"><a href=\"http://www.ammunitiontogo.com/categories.php\">Categories / Products</a>\n" +
			  " <div class=\"block-content\">\n" +
			  "  <ul class=\"nav\">\n" +
			  "<li class=\"clearance-items\">\n" +
			  " <a href=\"http://www.ammunitiontogo.com/specials.php\"><span>Clearance Items</span></a>\n" +
			  "</li>\n" +
			  "<li class=\"level-top cat-6\"><span class=\"level-top-header parent open-content\">Pistol Ammo</span><ul class=\"content\"><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo\" title=\"Pistol Ammo\">All Pistol Ammo</a></li><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-10mm\" title=\"10mm\">10mm</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-22-tcm\" title=\"22 TCM\">22 TCM</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-25-auto\" title=\"25 AUTO\">25 AUTO</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-30-luger\" title=\"30 LUGER\">30 LUGER</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-32-auto\" title=\"32 AUTO\">32 AUTO</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-32-hr-mag\" title=\"32 H&R MAG\">32 H&R MAG</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-32-naa\" title=\"32 NAA\">32 NAA</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-32-short-colt\" title=\"32 SHORT COLT\">32 SHORT COLT</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-32-sw-long\" title=\"32 S&W LONG\">32 S&W LONG</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-32-sw-short\" title=\"32 S&W (Short)\">32 S&W (Short)</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-327-federal-mag\" title=\"327 FEDERAL MAG\">327 FEDERAL MAG</a></li><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-357-magnum\" title=\"357 MAGNUM\">357 MAGNUM</a></li><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-357-sig\" title=\"357 SIG\">357 SIG</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-38-long-colt\" title=\"38 LONG COLT\">38 LONG COLT</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-38-short-colt\" title=\"38 SHORT COLT\">38 SHORT COLT</a></li><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-38-special\" title=\"38 SPECIAL\">38 SPECIAL</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-38-super-auto\" title=\"38 SUPER AUTO\">38 SUPER AUTO</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-38-sw\" title=\"38 S&W\">38 S&W</a></li><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-380-auto\" title=\"380 AUTO\">380 AUTO</a></li><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-40-sw\" title=\"40 S&W\">40 S&W</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-400-corbon\" title=\"400 CORBON\">400 CORBON</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-41-colt\" title=\"41 COLT\">41 COLT</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-41-magnum\" title=\"41 MAGNUM\">41 MAGNUM</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-44-long-colt\" title=\"44 LONG COLT\">44 LONG COLT</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-44-magnum\" title=\"44 MAGNUM\">44 MAGNUM</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-44-russian\" title=\"44 RUSSIAN\">44 RUSSIAN</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-44-special\" title=\"44 SPECIAL\">44 SPECIAL</a></li><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-45-acp\" title=\"45 ACP\">45 ACP</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-45-auto-rim\" title=\"45 AUTO RIM\">45 AUTO RIM</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-45-gap\" title=\"45 GAP\">45 GAP</a></li><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-45-long-colt\" title=\"45 LONG COLT\">45 LONG COLT</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-45-schofield\" title=\"45 SCHOFIELD\">45 SCHOFIELD</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-45-win-mag\" title=\"45 WIN MAG\">45 WIN MAG</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-454-casull\" title=\"454 CASULL\">454 CASULL</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-455-webley\" title=\"455 WEBLEY\">455 WEBLEY</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-460-rowland\" title=\"460 ROWLAND\">460 ROWLAND</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-460-sw\" title=\"460 S&W\">460 S&W</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-475-linebaugh\" title=\"475 LINEBAUGH\">475 LINEBAUGH</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-480-ruger\" title=\"480 RUGER\">480 RUGER</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-50-ae\" title=\"50 AE\">50 AE</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-50-gi\" title=\"50 GI\">50 GI</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-500-sw\" title=\"500 S&W\">500 S&W</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-fn-57\" title=\"FN 5.7\">FN 5.7</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-762-nagant\" title=\"7.62 NAGANT\">7.62 NAGANT</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-762x25-tokarev\" title=\"7.62x25 TOKAREV\">7.62x25 TOKAREV</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-763-mauser\" title=\"7.63 MAUSER\">7.63 MAUSER</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-8mm-lebel\" title=\"8mm LEBEL\">8mm LEBEL</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-8mm-steyr\" title=\"8mm STEYR\">8mm STEYR</a></li><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-9mm\" title=\"9mm Ammo\">9mm Ammo</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-9mm-flobert\" title=\"9mm FLOBERT\">9mm FLOBERT</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-9mm-largo\" title=\"9mm LARGO\">9mm LARGO</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-9mm-steyr\" title=\"9mm STEYR\">9mm STEYR</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-9x18-makarov\" title=\"9x18 MAKAROV\">9x18 MAKAROV</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/9x18-ultra-ammo\" title=\"9x18 Ultra Ammo\">9x18 Ultra Ammo</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-9x21mm\" title=\"9x21mm\">9x21mm</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-9x23-winchester\" title=\"9x23 WINCHESTER\">9x23 WINCHESTER</a></li><li><a href=\"javascript:\" id=\"more-6\" class=\"show-more-calibers show-more\">See all pistol calibers</a></li></ul></li><li class=\"level-top cat-7\"><span class=\"level-top-header parent open-content\">Rifle Ammo</span><ul class=\"content\"><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo\" title=\"Rifle Ammo\">All Rifle Ammo</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-17-fireball\" title=\"17 FIREBALL\">17 FIREBALL</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-17-hornet\" title=\"17 HORNET\">17 HORNET</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-17-remington\" title=\"17 REMINGTON\">17 REMINGTON</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-17-win-super-mag\" title=\"17 WIN SUPER MAG\">17 WIN SUPER MAG</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-204-ruger\" title=\"204 RUGER\">204 RUGER</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-218-bee\" title=\"218 BEE\">218 BEE</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-22-hornet\" title=\"22 HORNET\">22 HORNET</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-22-savage-hipower\" title=\"22 SAVAGE HI-POWER\">22 SAVAGE HI-POWER</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-220-swift\" title=\"220 SWIFT\">220 SWIFT</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-221-fireball\" title=\"221 FIREBAll\">221 FIREBAll</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-222-rem\" title=\".222 REM.\">.222 REM.</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-222-rem-mag\" title=\".222 REM. MAG.\">.222 REM. MAG.</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-22250-remington\" title=\"22-250 REMINGTON\">22-250 REMINGTON</a></li><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-223-556\" title=\".223 / 5.56\">.223 / 5.56</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-223-wssm\" title=\".223 WSSM\">.223 WSSM</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-240-weatherby\" title=\"240 WEATHERBY\">240 WEATHERBY</a></li><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-243\" title=\".243\">.243</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-243-wssm\" title=\".243 WSSM\">.243 WSSM</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-25-wssm\" title=\"25 WSSM\">25 WSSM</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-250-savage\" title=\"250 SAVAGE\">250 SAVAGE</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-2506\" title=\".25-06\">.25-06</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-2520-winchester\" title=\".25-20 WINCHESTER\">.25-20 WINCHESTER</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-2535-winchester\" title=\"25-35 WINCHESTER\">25-35 WINCHESTER</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-257-roberts\" title=\"257 ROBERTS\">257 ROBERTS</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-257-weatherby-magnum\" title=\"257 WEATHERBY MAGNUM\">257 WEATHERBY MAGNUM</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-260-remington\" title=\"260 REMINGTON\">260 REMINGTON</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-264-win-mag\" title=\"264 WIN. MAG\">264 WIN. MAG</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-270-weatherby\" title=\".270 WEATHERBY\">.270 WEATHERBY</a></li><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-270-winchester\" title=\"270 WINCHESTER\">270 WINCHESTER</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-270-wsm\" title=\".270 WSM\">.270 WSM</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-280-ackley-improved\" title=\"280 ACKLEY IMPROVED\">280 ACKLEY IMPROVED</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-280-remington\" title=\"280 REMINGTON\">280 REMINGTON</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-284-win\" title=\"284 WIN.\">284 WIN.</a></li><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-30-carbine\" title=\"30 CARBINE\">30 CARBINE</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-30-remington-ar\" title=\"30 REMINGTON AR\">30 REMINGTON AR</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-30-thompson-center\" title=\"30 THOMPSON CENTER\">30 THOMPSON CENTER</a></li><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-300-aac-blackout\" title=\"300 AAC BLACKOUT\">300 AAC BLACKOUT</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-300-hh-mag\" title=\"300 H&H MAG\">300 H&H MAG</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-300-rcm\" title=\"300 RCM\">300 RCM</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-300-rem-sa-ultra-mag\" title=\"300 REM SA ULTRA MAG\">300 REM SA ULTRA MAG</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-300-savage\" title=\"300 SAVAGE\">300 SAVAGE</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-300-ultra-mag\" title=\"300 ULTRA MAG.\">300 ULTRA MAG.</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-300-weatherby-mag\" title=\"300 WEATHERBY MAG\">300 WEATHERBY MAG</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-300-whisper\" title=\"300 WHISPER\">300 WHISPER</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-300-win-mag\" title=\"300 WIN. MAG\">300 WIN. MAG</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-300-wsm\" title=\"300 WSM\">300 WSM</a></li><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-3006\" title=\"30-06\">30-06</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-303-british\" title=\"303 BRITISH\">303 BRITISH</a></li><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-3030-winchester\" title=\"30-30 WINCHESTER\">30-30 WINCHESTER</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-30378-weatherby\" title=\"30-378 WEATHERBY\">30-378 WEATHERBY</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-3040-krag\" title=\"30-40 KRAG\">30-40 KRAG</a></li><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-308-762-nato\" title=\".308 / 7.62 NATO\">.308 / 7.62 NATO</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-308-marlin\" title=\"308 MARLIN\">308 MARLIN</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-308-norma-magnum\" title=\"308 NORMA MAGNUM\">308 NORMA MAGNUM</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-32-special\" title=\"32 SPECIAL\">32 SPECIAL</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-3220-win\" title=\"32-20 WIN.\">32-20 WIN.</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-325-wsm\" title=\"325 WSM\">325 WSM</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-338-federal\" title=\"338 FEDERAL\">338 FEDERAL</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-338-lapua\" title=\"338 LAPUA\">338 LAPUA</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-338-marlin-express\" title=\"338 MARLIN EXPRESS\">338 MARLIN EXPRESS</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-338-norma-mag\" title=\"338 NORMA MAG\">338 NORMA MAG</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-338-rcm\" title=\"338 RCM\">338 RCM</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-338-rum\" title=\"338 RUM\">338 RUM</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-338-win-mag\" title=\"338 WIN. MAG\">338 WIN. MAG</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-338378-weatherby\" title=\"338-378 WEATHERBY\">338-378 WEATHERBY</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-340-weatherby\" title=\"340 WEATHERBY\">340 WEATHERBY</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-348-winchester\" title=\"348 WINCHESTER\">348 WINCHESTER</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-35-remington\" title=\"35 REMINGTON\">35 REMINGTON</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-35-whelen\" title=\"35 WHELEN\">35 WHELEN</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-356-win\" title=\"356 WIN.\">356 WIN.</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-358-win\" title=\"358 WIN.\">358 WIN.</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-370-sako-magnum\" title=\"370 SAKO MAGNUM\">370 SAKO MAGNUM</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-375-hh-mag\" title=\"375 H&H MAG\">375 H&H MAG</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-375-jdj\" title=\"375 JDJ\">375 JDJ</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-375-ruger\" title=\"375 RUGER\">375 RUGER</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-375-rum\" title=\"375 RUM\">375 RUM</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-375-win\" title=\"375 WIN\">375 WIN</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-376-steyr\" title=\"376 STEYR\">376 STEYR</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-378-weatherby-mag\" title=\"378 WEATHERBY MAG\">378 WEATHERBY MAG</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-3840\" title=\"38-40\">38-40</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-3855-winchester\" title=\"38-55 WINCHESTER\">38-55 WINCHESTER</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-404-jeffery\" title=\"404 JEFFERY\">404 JEFFERY</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-405-win\" title=\"405 Winchester\">405 Winchester</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-416-barrett\" title=\"416 BARRETT\">416 BARRETT</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-416-remington-magnum\" title=\"416 REMINGTON MAGNUM\">416 REMINGTON MAGNUM</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-416-rigby\" title=\"416 RIGBY\">416 RIGBY</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-416-ruger\" title=\"416 RUGER\">416 RUGER</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-444-marlin\" title=\".444 MARLIN\">.444 MARLIN</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-4440\" title=\"44-40 WINCHESTER\">44-40 WINCHESTER</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-450-bushmaster\" title=\"450 BUSHMASTER\">450 BUSHMASTER</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-450-marlin\" title=\"450 MARLIN\">450 MARLIN</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-450-nitro-express\" title=\"450 NITRO EXPRESS\">450 NITRO EXPRESS</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-450400\" title=\"450-400\">450-400</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-4570-govt\" title=\"45-70 GOVT.\">45-70 GOVT.</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-458-lott\" title=\"458 LOTT\">458 LOTT</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-458-socom\" title=\"458 SOCOM\">458 SOCOM</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-458-winchester-mag\" title=\"458 WINCHESTER MAG\">458 WINCHESTER MAG</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-4590\" title=\"45-90\">45-90</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-460-weatherby\" title=\"460 WEATHERBY\">460 WEATHERBY</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-46x30mm-hk\" title=\"4.6X30mm HK\">4.6X30mm HK</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-470-ne\" title=\"470 NE\">470 NE</a></li><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-50-cal-bmg\" title=\"50 CAL. BMG\">50 CAL. BMG</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-500-nitro-express\" title=\"500 NITRO EXPRESS\">500 NITRO EXPRESS</a></li><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-545x39\" title=\"5.45X39\">5.45X39</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/5.6x52mm-rimmed-ammo\" title=\"5.6x52R Ammo\">5.6x52R Ammo</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-577450-martinihenry\" title=\".577/.450 MARTINI-HENRY\">.577/.450 MARTINI-HENRY</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-6mm-br-norma\" title=\"6MM B.R. NORMA\">6MM B.R. NORMA</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-6mm-remington\" title=\"6MM REMINGTON\">6MM REMINGTON</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-6xc\" title=\"6XC\">6XC</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-65-carcano\" title=\"6.5 CARCANO\">6.5 CARCANO</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-65-creedmoor\" title=\"6.5 CREEDMOOR\">6.5 CREEDMOOR</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-65-grendel\" title=\"6.5 GRENDEL\">6.5 GRENDEL</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-65mm-284-norma\" title=\"6.5MM - 284 NORMA\">6.5MM - 284 NORMA</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-65-jap\" title=\"6.5 JAP\">6.5 JAP</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-65x54-mannlicher-sch\" title=\"6.5X54 MANNLICHER SCH\">6.5X54 MANNLICHER SCH</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-65x55-swedish-mauser\" title=\"6.5X55 SWEDISH MAUSER\">6.5X55 SWEDISH MAUSER</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-65x57-mauser\" title=\"6.5X57 MAUSER\">6.5X57 MAUSER</a></li><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-68-spc\" title=\"6.8 SPC\">6.8 SPC</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-7mm-mauser\" title=\"7mm MAUSER\">7mm MAUSER</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-7mm-rem-mag\" title=\"7mm REM MAG\">7mm REM MAG</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-7mm-sa-ultra-mag\" title=\"7mm SA ULTRA MAG\">7mm SA ULTRA MAG</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-7mm-stw\" title=\"7mm STW\">7mm STW</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-7mm-ultra-mag\" title=\"7mm ULTRA MAG\">7mm ULTRA MAG</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-7mm-weatherby\" title=\"7mm WEATHERBY\">7mm WEATHERBY</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-7mm-wsm\" title=\"7mm WSM\">7mm WSM</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-7mm08-remington\" title=\"7mm-08 REMINGTON\">7mm-08 REMINGTON</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-7x30-waters\" title=\"7x30 WATERS\">7x30 WATERS</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-7x57r\" title=\"7X57R\">7X57R</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-7x64\" title=\"7X64\">7X64</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-7x65r\" title=\"7X65R\">7X65R</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-75x54-french\" title=\"7.5X54 FRENCH\">7.5X54 FRENCH</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-75x55-swiss\" title=\"7.5X55 SWISS\">7.5X55 SWISS</a></li><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-762x39\" title=\"7.62X39\">7.62X39</a></li><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-762x54r\" title=\"7.62X54R\">7.62X54R</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-765-argentine\" title=\"7.65 ARGENTINE\">7.65 ARGENTINE</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-77-jap\" title=\"7.7 JAP\">7.7 JAP</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-792x33-kurz\" title=\"7.92X33 KURZ\">7.92X33 KURZ</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-8mm-mauser\" title=\"8mm MAUSER\">8mm MAUSER</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-8-x-50r-lebel\" title=\"8 x 50R LEBEL\">8 x 50R LEBEL</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-8x56r-mannlicher\" title=\"8x56R MANNLICHER\">8x56R MANNLICHER</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-8x57-jr\" title=\"8X57 JR\">8X57 JR</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/8x57mm-jrs-rimmed-mauser-ammo\" title=\"8x57 JRS Mauser\">8x57 JRS Mauser</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/8x57mm-js-mauser-ammo\" title=\"8x57mm JS Mauser\">8x57mm JS Mauser</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-93-x-62mm-mauser\" title=\"9.3 X 62mm MAUSER\">9.3 X 62mm MAUSER</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-93x64mm-brenneke\" title=\"9.3X64mm BRENNEKE\">9.3X64mm BRENNEKE</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-93x72r\" title=\"9.3x72R\">9.3x72R</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-93x74r\" title=\"9.3X74R\">9.3X74R</a></li><li><a href=\"javascript:\" id=\"more-7\" class=\"show-more-calibers show-more\">See all rifle calibers</a></li></ul></li><li class=\"level-top cat-36\"><span class=\"level-top-header parent open-content\">22LR & All Rimfire Ammo</span><ul class=\"content\"><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/22-rimfirelr-mag\" title=\"22LR & All Rimfire Ammo\">All 22LR & All Rimfire Ammo</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-17-mach-2\" title=\"17 MACH 2\">17 MACH 2</a></li><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-17-hmr\" title=\"17 HMR\">17 HMR</a></li><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/22-rimfirelr-mag-22-long-rifle\" title=\"22 Long Rifle\">22 Long Rifle</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/22-rimfirelr-mag-22-short\" title=\"22 Short\">22 Short</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/22-rimfirelr-mag-22-winchester-automatic\" title=\"22 Winchester Automatic\">22 Winchester Automatic</a></li><li class=\"out-of-stock\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/22-rimfirelr-mag-22-magnum-wrf\" title=\"22 WMR (22 Magnum) \">22 WMR (22 Magnum) </a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/22-wrf-winchester-rim-fire\" title=\"22 WRF\">22 WRF</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-5mm-rimfire-magnum\" title=\"5mm RIMFIRE MAGNUM\">5mm RIMFIRE MAGNUM</a></li><li><a href=\"javascript:\" id=\"more-36\" class=\"show-more-calibers show-more\">See all 22lr & all rimfire calibers</a></li></ul></li><li class=\"level-top cat-37\"><span class=\"level-top-header parent open-content\">Shotgun Ammo</span><ul class=\"content\"><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/shotgun-ammo\" title=\"Shotgun Ammo\">All Shotgun Ammo</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/shotgun-ammo-10-gauge\" title=\"10 Gauge\">10 Gauge</a></li><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/shotgun-ammo-12-gauge\" title=\"12 Gauge\">12 Gauge</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/shotgun-ammo-16-gauge\" title=\"16 Gauge\">16 Gauge</a></li><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/shotgun-ammo-20-gauge\" title=\"20 Gauge\">20 Gauge</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/shotgun-ammo-28-gauge\" title=\"28 Gauge\">28 Gauge</a></li><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/shotgun-ammo-410-gauge\" title=\"410 Gauge\">410 Gauge</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/32-gauge-ammo-shells\" title=\"32 Gauge\">32 Gauge</a></li><li><a href=\"javascript:\" id=\"more-37\" class=\"show-more-calibers show-more\">See all shotgun calibers</a></li></ul></li><li class=\"level-top cat-883\"><span class=\"level-top-header parent\">Specialty Ammo</span><ul class=\"content hidden-content\"><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/specialty-ammo\" title=\"Specialty Ammo\">All Specialty Ammo</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/subsonic-ammo\" title=\"Subsonic Ammo\">Subsonic Ammo</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/military-surplus\" title=\"Military Surplus Ammo\">Military Surplus Ammo</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/less-lethal-ammo\" title=\"Less Lethal Ammo\">Less Lethal Ammo</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/tracer-ammo\" title=\"Tracer Ammo\">Tracer Ammo</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/air-rifles\" title=\"Air Rifle Pellets\">Air Rifle Pellets</a></li><li><a href=\"javascript:\" id=\"more-883\" class=\"show-more-calibers show-more\">See all specialty</a></li></ul></li><li class=\"level-top cat-802\"><span class=\"level-top-header parent\">Ammo Cans / Desiccant</span><ul class=\"content hidden-content\"><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/ammo-cans\" title=\"Ammo Cans / Desiccant\">All Ammo Cans / Desiccant</a></li><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/ammo-cans-for-sale\" title=\"Ammo Cans\">Ammo Cans</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/desiccant\" title=\"Desiccant\">Desiccant</a></li><li><a href=\"javascript:\" id=\"more-802\" class=\"show-more-calibers show-more\">See all cans / desiccant</a></li></ul></li><li class=\"level-top cat-99\"><span class=\"level-top-header parent\">AR-15 Parts & Accessories</span><ul class=\"content hidden-content\"><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/accessories\" title=\"AR-15 Parts & Accessories\">All AR-15 Parts & Accessories</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/accessories-ar-parts\" title=\"AR Parts\">AR Parts</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/accessories-stocks\" title=\"Stocks\">Stocks</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/accessories-quad-rails-handguard\" title=\"Quad Rails / Handguard\">Quad Rails / Handguard</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/accessories-forend-grips\" title=\"Forend Grips\">Forend Grips</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/accessories-grips\" title=\"Grips\">Grips</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/accessories-trigger-guards\" title=\"Trigger Guards\">Trigger Guards</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/accessories-furniture-kits\" title=\"Furniture Kits\">Furniture Kits</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/accessories-tools\" title=\"Gun Tools\">Gun Tools</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/accessories-bipods\" title=\"Bipods\">Bipods</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/accessories-slings\" title=\"Slings\">Slings</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/accessories-firearm-conversion-kits\" title=\"Firearm Conversion Kits\">Firearm Conversion Kits</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/complete-uppers\" title=\"Complete Uppers\">Complete Uppers</a></li><li><a href=\"javascript:\" id=\"more-99\" class=\"show-more-calibers show-more\">See all ar-15 parts & accessories</a></li></ul></li><li class=\"level-top cat-823\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/choke-tubes\" title=\"Choke Tubes\"><span class=\"level-top-header\">Choke Tubes</span></a></li><li class=\"level-top cat-819\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/cleaning-supplies\" title=\"Gun Cleaning Supplies\"><span class=\"level-top-header\">Gun Cleaning Supplies</span></a></li><li class=\"level-top cat-50\"><span class=\"level-top-header parent\">Gift Cards</span><ul class=\"content hidden-content\"><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/gift-cards\" title=\"Gift Cards\">All Gift Cards</a></li></ul></li><li class=\"level-top cat-801\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/gun-cases\" title=\"Gun Cases and Storage\"><span class=\"level-top-header\">Gun Cases and Storage</span></a></li><li class=\"level-top cat-815\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/hearing-protection\" title=\"Ear / Eye Protection\"><span class=\"level-top-header\">Ear / Eye Protection</span></a></li><li class=\"level-top cat-828\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/holsters\" title=\"Holsters\"><span class=\"level-top-header\">Holsters</span></a></li><li class=\"level-top cat-797\"><span class=\"level-top-header parent\">Magazines & Accessories</span><ul class=\"content hidden-content\"><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/magazines\" title=\"Magazines & Accessories\">All Magazines & Accessories</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/magazines-pistol-magazines\" title=\"Pistol Magazines\">Pistol Magazines</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/magazines-rifle-magazines\" title=\"Rifle Magazines\">Rifle Magazines</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/magazines-shotgun-magazines\" title=\"Shotgun Magazines\">Shotgun Magazines</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/magazines-magazine-loaders\" title=\"Magazine Loaders\">Magazine Loaders</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/magazines-magazine-accessories\" title=\"Magazine Accessories\">Magazine Accessories</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/stripper-clips\" title=\"Stripper Clips\">Stripper Clips</a></li><li><a href=\"javascript:\" id=\"more-797\" class=\"show-more-calibers show-more\">See all magazines & accessories</a></li></ul></li><li class=\"level-top cat-148\"><span class=\"level-top-header parent\">Optics & Sights</span><ul class=\"content hidden-content\"><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/optics\" title=\"Optics & Sights\">All Optics & Sights</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/night-vision\" title=\"Night Vision\">Night Vision</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/optics-rifle-scopes\" title=\"Rifle Scopes\">Rifle Scopes</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/optics-red-dot-sights\" title=\"Red Dot Sights\">Red Dot Sights</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/optics-binoculars\" title=\"Binoculars\">Binoculars</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/optics-range-finders\" title=\"Range Finders\">Range Finders</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/optics-optic-and-scope-accessories\" title=\"Optic and Scope Accessories\">Optic and Scope Accessories</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/scope-mounts\" title=\"Scope Mounts\">Scope Mounts</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/sights-laser-sights\" title=\"Laser Sights\">Laser Sights</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/sights-flipup-sights\" title=\"Flip-Up Sights\">Flip-Up Sights</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/sights-iron-sights\" title=\"Iron Sights\">Iron Sights</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/sights-sight-accessories\" title=\"Sight Accessories\">Sight Accessories</a></li><li><a href=\"javascript:\" id=\"more-148\" class=\"show-more-calibers show-more\">See all optics & sights</a></li></ul></li><li class=\"level-top cat-46\"><span class=\"level-top-header parent\">Reloading Supplies</span><ul class=\"content hidden-content\"><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/reloading-supplies\" title=\"Reloading Supplies\">All Reloading Supplies</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/brass-casings\" title=\"Brass Casings\">Brass Casings</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/reloading-supplies-bullets\" title=\"Bullets\">Bullets</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/reloading-supplies-primers\" title=\"Primers\">Primers</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/reloading-supplies-reloading-presses\" title=\"Reloading Presses\">Reloading Presses</a></li><li><a href=\"javascript:\" id=\"more-46\" class=\"show-more-calibers show-more\">See all reloading supplies</a></li></ul></li><li class=\"level-top cat-818\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/snap-caps\" title=\"Snap Caps\"><span class=\"level-top-header\">Snap Caps</span></a></li><li class=\"level-top cat-820\"><span class=\"level-top-header parent\">Tactical Gear</span><ul class=\"content hidden-content\"><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/tactical-gear\" title=\"Tactical Gear\">All Tactical Gear</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/ammo-to-go-gear\" title=\"Ammo To Go Gear\">Ammo To Go Gear</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/backpacksbags\" title=\"Backpacks/Bags\">Backpacks/Bags</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/phone-cases\" title=\"Phone Cases\">Phone Cases</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/paracord\" title=\"Paracord\">Paracord</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/tactical-lights\" title=\"Tactical Lights\">Tactical Lights</a></li><li class=\"out-of-stock category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/watches\" title=\"Watches\">Watches</a></li><li><a href=\"javascript:\" id=\"more-820\" class=\"show-more-calibers show-more\">See all tactical gear</a></li></ul></li><li class=\"level-top cat-821\"><span class=\"level-top-header parent\">Targets</span><ul class=\"content hidden-content\"><li><a href=\"http://www.ammunitiontogo.com/index.php/cName/targets\" title=\"Targets\">All Targets</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/targets-targets\" title=\"Paper Targets\">Paper Targets</a></li><li class=\"category-hidden\"><a href=\"http://www.ammunitiontogo.com/index.php/cName/targets-exploding-targets\" title=\"Exploding/Reactive Targets\">Exploding/Reactive Targets</a></li><li><a href=\"javascript:\" id=\"more-821\" class=\"show-more-calibers show-more\">See all targets</a></li></ul></li>  </ul>\n" +
			  " \n" +
			  "\n" +
			  " <script type=\"text/javascript\">\n" +
			  "  var categoryNames = {\"6\":\"pistol\",\"7\":\"rifle\",\"36\":\"22lr & all rimfire\",\"37\":\"shotgun\",\"883\":\"specialty\",\"802\":\"cans \\/ desiccant\",\"99\":\"ar-15 parts & accessories\",\"823\":\"choke tubes\",\"819\":\"gun cleaning supplies\",\"50\":\"gift cards\",\"801\":\"gun cases and storage\",\"815\":\"ear \\/ eye protection\",\"828\":\"holsters\",\"797\":\"magazines & accessories\",\"148\":\"optics & sights\",\"46\":\"reloading supplies\",\"818\":\"snap caps\",\"820\":\"tactical gear\",\"821\":\"targets\"};\n" +
			  "  var ammoCategories = [6,7,36,37];\n" +
			  " </script>\n" +
			  "<div class=\"block block-more-information\">\n" +
			  " <div class=\"block-title\">\n" +
			  "  <strong><span>More Information</span></strong>\n" +
			  " \n" +
			  " <div class=\"block-content\">\n" +
			  "<a href=\"http://www.ammunitiontogo.com/privacy.php\" rel=\"nofollow\">Privacy Notice</a><br>\n" +
			  "<a href=\"http://www.ammunitiontogo.com/conditions.php\" rel=\"nofollow\">Conditions of Use</a><br>\n" +
			  "<a href=\"http://www.ammunitiontogo.com/contactus.php\">Contact Us</a><br>\n" +
			  "<a href=\"http://www.ammunitiontogo.com/sitemap.php\">Site Map</a>\n" +
			  " \n" +
			  "\n" +
			  "  <div class=\"col-main\">\n" +
			  "<div class=\"std\"><div class=\"promo\">\n" +
			  "<a class=\"item\" href=\"http://www.ammunitiontogo.com/product_info.php/pName/1000rds-556-nato-pmc-xtac-62gr-green-tip-light-armor-piercing-ammo\"><img src=\"http://ammotogo-689018.c.cdn77.org/media/wysiwyg/AmmoToGo/ATG-PMC-M855-39850.jpg\" border=\"0\" alt=\"PMC 5.56x45mm M855 Penetrator Ammo On Sale!\" width=\"545\" height=\"300\" /></a>\n" +
			  "\n" +
			  "\n" +
			  "\n" +
			  "<div class=\"box-main\">\n" +
			  "  <p style=\"text-align: left; font-style: normal; line-height: 12px;\">Founded in the heart of Texas, Ammo To Go is an online ammunition store that stocks factory new ammunition, bulk ammo, and surplus ammunition.</p>\n" +
			  "  <p style=\"text-align: left; font-style: normal; line-height: 12px;\">With <a href=\"http://www.ammunitiontogo.com/index.php/cName/rifle-ammo-223-556\">bulk .223 ammo</a>, <a href=\"http://www.ammunitiontogo.com/index.php/cName/pistol-ammo-9mm\">9mm handgun cartridges</a>, and other great rounds, Ammo To Go is the primary online source for all your cheap, bulk rounds for range training as well as specialty/self-defense ammunition needs! Ammo To Go supplies sportsmen, hunters, and law enforcement throughout the United States with quality rounds, fast shipping and top-notch service. Contact us at 979-277-9676 or <a href=\"mailto:sales@ammotogo.com\">email sales@ammotogo.com</a>.</p>\n" +
			  "\n" +
			  "\n" +
			  "<div class=\"block block-specials\">\n" +
			  "  <div class=\"block-title\"><strong><span>Specials For September</span></strong>\n" +
			  " <div class=\"block-content\"><div class=\"featured\">\n" +
			  " <table class=\"products-grid\" id=\"products-grid-table\">\n" +
			  "<tr>\n" +
			  "<td class=\"item\">\n" +
			  " <a href=\"http://www.ammunitiontogo.com/product_info.php/pName/20rds-762x39-wpa-polyformance-123gr-fmj-ammo\" title=\"20rds - 7.62x39 WPA Polyformance 123gr. FMJ Ammo\" class=\"product-image\"><img src=\"http://ammotogo-689018.c.cdn77.org/pimages/small_image/160x/9df78eab33525d08d6e5fb8d27136e95/8/c/8cdc15bbbe88b1a35a36cd60d6f59b3b.jpg\" width=\"160\" height=\"160\" alt=\"Image of 20rds - 7.62x39 WPA Polyformance 123gr. FMJ Ammo\" /></a>\n" +
			  " <h3 class=\"product-name\"><a href=\"http://www.ammunitiontogo.com/product_info.php/pName/20rds-762x39-wpa-polyformance-123gr-fmj-ammo\" title=\"Check out this 20rds - 7.62x39 WPA Polyformance 123gr. FMJ Ammo\">20rds - 7.62x39 WPA Polyformance 123gr. FMJ Ammo</a></h3>\n" +
			  " \n" +
			  "  \n" +
			  "\n" +
			  "  \n" +
			  " <div class=\"price-box\">\n" +
			  "  \n" +
			  "  <p class=\"old-price\">\n" +
			  " <span class=\"price\" id=\"old-price-2605\">\n" +
			  "  $6.49 </span>\n" +
			  "</p>\n" +
			  "\n" +
			  "<p class=\"special-price\">\n" +
			  " <span class=\"price\" id=\"product-price-2605\">\n" +
			  "  $4.70 </span>\n" +
			  "</p>\n" +
			  "  \n" +
			  " \n" +
			  "  \n" +
			  "\n" +
			  " \n" +
			  "</td>\n" +
			  "  <td class=\"item\">\n" +
			  " <a href=\"http://www.ammunitiontogo.com/product_info.php/pName/1-primos-trail-camera-truth-cam-el-blackout\" title=\"1 - Primos Trail Camera - Truth Cam EL Blackout\" class=\"product-image\"><img src=\"http://ammotogo-689018.c.cdn77.org/pimages/small_image/160x/9df78eab33525d08d6e5fb8d27136e95/6/3/63038.jpg\" width=\"160\" height=\"160\" alt=\"Image of 1 - Primos Trail Camera - Truth Cam EL Blackout\" /></a>\n" +
			  " <h3 class=\"product-name\"><a href=\"http://www.ammunitiontogo.com/product_info.php/pName/1-primos-trail-camera-truth-cam-el-blackout\" title=\"Check out this 1 - Primos Trail Camera - Truth Cam EL Blackout\">1 - Primos Trail Camera - Truth Cam EL Blackout</a></h3>\n" +
			  " \n" +
			  "  \n" +
			  "\n" +
			  "  \n" +
			  " <div class=\"price-box\">\n" +
			  "<span class=\"regular-price\" id=\"product-price-10095\">\n" +
			  "  <span class=\"price\">$130.00</span> </span>\n" +
			  "\n" +
			  "  \n" +
			  "\n" +
			  " \n" +
			  "</td>\n" +
			  "  <td class=\"item\">\n" +
			  " <a href=\"http://www.ammunitiontogo.com/product_info.php/pName/500rds-45-acp-prvi-partizan-230gr-fmj-ammo\" title=\"500rds - 45 ACP PRVI Partizan 230gr. FMJ Ammo\" class=\"product-image\"><img src=\"http://ammotogo-689018.c.cdn77.org/pimages/small_image/160x/9df78eab33525d08d6e5fb8d27136e95/0/1/011e5be24bf92380dfb95a5810d72505.jpg\" width=\"160\" height=\"160\" alt=\"Image of 500rds - 45 ACP PRVI Partizan 230gr. FMJ Ammo\" /></a>\n" +
			  " <h3 class=\"product-name\"><a href=\"http://www.ammunitiontogo.com/product_info.php/pName/500rds-45-acp-prvi-partizan-230gr-fmj-ammo\" title=\"Check out this 500rds - 45 ACP PRVI Partizan 230gr. FMJ Ammo\">500rds - 45 ACP PRVI Partizan 230gr. FMJ Ammo</a></h3>\n" +
			  " \n" +
			  "  \n" +
			  "\n" +
			  "  \n" +
			  " <div class=\"price-box\">\n" +
			  "<span class=\"regular-price\" id=\"product-price-4944\">\n" +
			  "  <span class=\"price\">$184.95</span> </span>\n" +
			  "\n" +
			  "  \n" +
			  "\n" +
			  " \n" +
			  "</td>\n" +
			  " </tr>\n" +
			  "<tr>\n" +
			  "<td class=\"item\">\n" +
			  " <a href=\"http://www.ammunitiontogo.com/product_info.php/pName/1000rds-9mm-drs-115gr-fmj-ammo-once-fired-brass\" title=\"1000rds - 9mm DRS 115gr. FMJ Ammo (Once Fired Brass)\" class=\"product-image\"><img src=\"http://ammotogo-689018.c.cdn77.org/pimages/small_image/160x/9df78eab33525d08d6e5fb8d27136e95/3/0/3013a4154a1d04d98e159a1cc66211fc.jpg\" width=\"160\" height=\"160\" alt=\"Image of 1000rds - 9mm DRS 115gr. FMJ Ammo (Once Fired Brass)\" /></a>\n" +
			  " <h3 class=\"product-name\"><a href=\"http://www.ammunitiontogo.com/product_info.php/pName/1000rds-9mm-drs-115gr-fmj-ammo-once-fired-brass\" title=\"Check out this 1000rds - 9mm DRS 115gr. FMJ Ammo (Once Fired Brass)\">1000rds - 9mm DRS 115gr. FMJ Ammo (Once Fired Brass)</a></h3>\n" +
			  " \n" +
			  "  \n" +
			  "\n" +
			  "  \n" +
			  " <div class=\"price-box\">\n" +
			  "  \n" +
			  "  <p class=\"old-price\">\n" +
			  " <span class=\"price\" id=\"old-price-11079\">\n" +
			  "  $215.00 </span>\n" +
			  "</p>\n" +
			  "\n" +
			  "<p class=\"special-price\">\n" +
			  " <span class=\"price\" id=\"product-price-11079\">\n" +
			  "  $194.95 </span>\n" +
			  "</p>\n" +
			  "  \n" +
			  " \n" +
			  "  \n" +
			  "\n" +
			  " \n" +
			  "</td>\n" +
			  "  <td class=\"item\">\n" +
			  " <a href=\"http://www.ammunitiontogo.com/product_info.php/pName/100rds-50-bmg-federal-american-eagle-xm33c-660gr-fmj-ammo\" title=\"100rds - 50 BMG Federal American Eagle XM33C 660gr. FMJ Ammo\" class=\"product-image\"><img src=\"http://ammotogo-689018.c.cdn77.org/pimages/small_image/160x/9df78eab33525d08d6e5fb8d27136e95/X/M/XM33C.jpg\" width=\"160\" height=\"160\" alt=\"Image of 100rds - 50 BMG Federal American Eagle XM33C 660gr. FMJ Ammo\" /></a>\n" +
			  " <h3 class=\"product-name\"><a href=\"http://www.ammunitiontogo.com/product_info.php/pName/100rds-50-bmg-federal-american-eagle-xm33c-660gr-fmj-ammo\" title=\"Check out this 100rds - 50 BMG Federal American Eagle XM33C 660gr. FMJ Ammo\">100rds - 50 BMG Federal American Eagle XM33C 660gr. FMJ Ammo</a></h3>\n" +
			  " \n" +
			  "  \n" +
			  "\n" +
			  "  \n" +
			  " <div class=\"price-box\">\n" +
			  "  \n" +
			  "  <p class=\"old-price\">\n" +
			  " <span class=\"price\" id=\"old-price-4455\">\n" +
			  "  $289.95 </span>\n" +
			  "</p>\n" +
			  "\n" +
			  "<p class=\"special-price\">\n" +
			  " <span class=\"price\" id=\"product-price-4455\">\n" +
			  "  $219.49 </span>\n" +
			  "</p>\n" +
			  "  \n" +
			  " \n" +
			  "  \n" +
			  "\n" +
			  " \n" +
			  "</td>\n" +
			  "  <td class=\"item\">\n" +
			  " <a href=\"http://www.ammunitiontogo.com/product_info.php/pName/1000rds-223-pmc-bronze-55gr-fmj-ammo\" title=\"1000rds - .223 PMC Bronze 55gr. FMJ Ammo\" class=\"product-image\"><img src=\"http://ammotogo-689018.c.cdn77.org/pimages/small_image/160x/9df78eab33525d08d6e5fb8d27136e95/e/c/ec7d5ae45b84e8026fd2af5719e4e453.jpg\" width=\"160\" height=\"160\" alt=\"Image of 1000rds - .223 PMC Bronze 55gr. FMJ Ammo\" /></a>\n" +
			  " <h3 class=\"product-name\"><a href=\"http://www.ammunitiontogo.com/product_info.php/pName/1000rds-223-pmc-bronze-55gr-fmj-ammo\" title=\"Check out this 1000rds - .223 PMC Bronze 55gr. FMJ Ammo\">1000rds - .223 PMC Bronze 55gr. FMJ Ammo</a></h3>\n" +
			  " \n" +
			  "  \n" +
			  "\n" +
			  "  \n" +
			  " <div class=\"price-box\">\n" +
			  "  \n" +
			  "  <p class=\"old-price\">\n" +
			  " <span class=\"price\" id=\"old-price-31\">\n" +
			  "  $330.95 </span>\n" +
			  "</p>\n" +
			  "\n" +
			  "<p class=\"special-price\">\n" +
			  " <span class=\"price\" id=\"product-price-31\">\n" +
			  "  $319.95 </span>\n" +
			  "</p>\n" +
			  "  \n" +
			  " \n" +
			  "  \n" +
			  "\n" +
			  " \n" +
			  "</td>\n" +
			  " </tr>\n" +
			  "</table>\n" +
			  " <script type=\"text/javascript\">decorateTable('products-grid-table')</script>\n" +
			  "\n" +
			  "\n" +
			  "  \n" +
			  " \n" +
			  " <div class=\"col-right sidebar\"><div class=\"block block-cart\">\n" +
			  "  <div class=\"block-title\">\n" +
			  "  <a href=\"http://www.ammunitiontogo.com/shopping_cart.php\"><strong><span>Cart</span></strong></a>\n" +
			  " \n" +
			  " <div class=\"block-content\">\n" +
			  " <p class=\"empty\">Your Cart is currently empty.</p>\n" +
			  "  \n" +
			  " <div class=\"block-footer\">\n" +
			  "  <a href=\"http://www.ammunitiontogo.com/login.php\" class=\"login\">Login</a>\n" +
			  " <a href=\"http://www.ammunitiontogo.com/shopping_cart.php\" class=\"go-to-cart\">Go to cart</a>\n" +
			  " \n" +
			  "\n" +
			  "<div class=\"block block-manufacturers\">\n" +
			  " <div class=\"block-title\">\n" +
			  "  <a href=\"http://www.ammunitiontogo.com/browse-by-manufacturer.php\"><strong><span>Ammo Manufacturers</span></strong></a>\n" +
			  " \n" +
			  " <div class=\"block-content\">\n" +
			  "  <select id=\"manufacturers\" name=\"manufacturers_id\" onchange=\"getBrowseByManufacturerUrl(this.value, { baseUrl: 'http://www.ammunitiontogo.com/' });\">\n" +
			  "<option value=\"-1\">Please Select</option>\n" +
			  " <option value=\"101\">ASYM Precision Ammunition</option>\n" +
			  " <option value=\"13\">Aguila Ammunition</option>\n" +
			  " <option value=\"44\">Armscor Precision Ammunition</option>\n" +
			  " <option value=\"287\">Australian Outback Ammunition</option>\n" +
			  " <option value=\"99\">Barnes Bullets</option>\n" +
			  " <option value=\"66\">Black Hills Ammunition</option>\n" +
			  " <option value=\"10001166\">Blazer</option>\n" +
			  " <option value=\"10009\">Blazer Brass</option>\n" +
			  " <option value=\"37\">Brenneke Slugs</option>\n" +
			  " <option value=\"20\">Brown Bear Ammunition</option>\n" +
			  " <option value=\"182\">Buffalo Bore</option>\n" +
			  " <option value=\"10001608\">CBC</option>\n" +
			  " <option value=\"181\">CCI Ammunition</option>\n" +
			  " <option value=\"95\">Colt</option>\n" +
			  " <option value=\"17\">Corbon</option>\n" +
			  " <option value=\"16\">DPX Ammunition</option>\n" +
			  " <option value=\"113\">DRS</option>\n" +
			  " <option value=\"217\">Dynamic Research Technologies</option>\n" +
			  " <option value=\"63\">Dynamit Nobel (Geco)</option>\n" +
			  " <option value=\"33\">ELEY Ammunition</option>\n" +
			  " <option value=\"64\">Estate Cartridge</option>\n" +
			  " <option value=\"29\">FN Ammunition</option>\n" +
			  " <option value=\"25\">Federal Ammunition</option>\n" +
			  " <option value=\"10\">Fiocchi Ammunition</option>\n" +
			  " <option value=\"405\">G2 Research</option>\n" +
			  " <option value=\"31\">Glaser Safety Slug</option>\n" +
			  " <option value=\"21\">Golden Bear Ammunition</option>\n" +
			  " <option value=\"60\">Guncrafter Industries</option>\n" +
			  " <option value=\"186\">HPR Ammo</option>\n" +
			  " <option value=\"149\">HSM Ammunition</option>\n" +
			  " <option value=\"61\">Hevi-Shot</option>\n" +
			  " <option value=\"18\">Hornady Ammunition</option>\n" +
			  " <option value=\"10002086\">Hotshot Ammunition</option>\n" +
			  " <option value=\"40\">Igman Ammunition</option>\n" +
			  " <option value=\"41\">Independence Ammunition</option>\n" +
			  " <option value=\"10002012\">Jamison Ammo</option>\n" +
			  " <option value=\"62\">Kent</option>\n" +
			  " <option value=\"1000180\">Lake City</option>\n" +
			  " <option value=\"172\">Lapua</option>\n" +
			  " <option value=\"302\">Liberty Ammunition</option>\n" +
			  " <option value=\"47\">Lightfield Ammo</option>\n" +
			  " <option value=\"151\">Magnum Research</option>\n" +
			  " <option value=\"10002099\">Magsafe Ammo</option>\n" +
			  " <option value=\"32\">Magtech</option>\n" +
			  " <option value=\"12\">Military Surplus</option>\n" +
			  " <option value=\"43\">NobelSport Shotshells</option>\n" +
			  " <option value=\"55\">Norma</option>\n" +
			  " <option value=\"81\">Nosler Ammunition</option>\n" +
			  " <option value=\"34\">PMC Ammunition</option>\n" +
			  " <option value=\"209\">PNW Arms</option>\n" +
			  " <option value=\"183\">Pierce Performance Ammunition</option>\n" +
			  " <option value=\"10002156\">Pobjeda Technology</option>\n" +
			  " <option value=\"15\">PowRBall Ammunition</option>\n" +
			  " <option value=\"200\">Precision Gun Works</option>\n" +
			  " <option value=\"26\">Prvi Partizan Ammunition</option>\n" +
			  " <option value=\"201\">RBCD Performance Plus</option>\n" +
			  " <option value=\"42\">RIO Ammunition</option>\n" +
			  " <option value=\"79\">RWS</option>\n" +
			  " <option value=\"27\">Remington Ammunition</option>\n" +
			  " <option value=\"136\">Right To Bear Ammo</option>\n" +
			  " <option value=\"1000976\">Romanian Surplus</option>\n" +
			  " <option value=\"10001205\">Russian Surplus</option>\n" +
			  " <option value=\"10001193\">SK</option>\n" +
			  " <option value=\"14\">Sellier & Bellot Ammunition</option>\n" +
			  " <option value=\"410\">Sig Sauer</option>\n" +
			  " <option value=\"19\">Silver Bear Ammunition</option>\n" +
			  " <option value=\"10001252\">Spartan</option>\n" +
			  " <option value=\"180\">Speer</option>\n" +
			  " <option value=\"158\">Summit Ammunition</option>\n" +
			  " <option value=\"223\">Ted Nugent Ammo</option>\n" +
			  " <option value=\"82\">Tula Ammo</option>\n" +
			  " <option value=\"50\">Ultramax Ammunition</option>\n" +
			  " <option value=\"56\">Weatherby Ammunition</option>\n" +
			  " <option value=\"23\">Winchester Ammunition</option>\n" +
			  " <option value=\"22\">Wolf Ammunition</option>\n" +
			  " <option value=\"1000266\">Yugoslavian Surplus</option>\n" +
			  "  </select>\n" +
			  " \n" +
			  "<div class=\"block block-social\">\n" +
			  " <div class=\"block-title\">\n" +
			  "  <strong><span>Connect with Us</span></strong>\n" +
			  " \n" +
			  " <div class=\"block-content\">\n" +
			  "  <a href=\"https://www.facebook.com/Ammotogo\" target=\"_blank\"><img src=\"http://static-cdn.ammunitiontogo.com/skin/frontend/ammotogo/default/images/icon_Facebook_.png\" border=\"0\"></a>\n" +
			  "  <a href=\"http://twitter.com/AmmoToGo\" target=\"_blank\"><img src=\"http://static-cdn.ammunitiontogo.com/skin/frontend/ammotogo/default/images/icon_twitter.png\" border=\"0\"></a>\n" +
			  "\n" +
			  "  <div class=\"block block-subscribe\">\n" +
			  " <div class=\"block-title\">\n" +
			  "  <strong><span>Newsletter</span></strong>\n" +
			  " \n" +
			  " <form action=\"https://www.ammunitiontogo.com/newsletter/subscriber/new\" method=\"post\" id=\"newsletter-signup\">\n" +
			  "  <div class=\"block-content\">\n" +
			  "<div class=\"form-subscribe-header\">\n" +
			  " <span><img src=\"http://static-cdn.ammunitiontogo.com/skin/frontend/ammotogo/default/images/email1_trans.gif\" alt=\"Email Newsletter icon, E-mail Newsletter icon, Email List icon, E-mail List icon\" border=\"0\"></span>\n" +
			  " Sign up for our Email Newsletter\n" +
			  "\n" +
			  "<div class=\"field-row\">\n" +
			  " <div class=\"input-box\">\n" +
			  "  <input type=\"text\" name=\"email\" id=\"email\" title=\"Sign up for our newsletter\" class=\"input-text required-entry validate-email\" />\n" +
			  " \n" +
			  " <div class=\"actions\">\n" +
			  "  <input type=\"submit\" name=\"go\" value=\"GO\" class=\"submit\" style=\"font-size:10px; padding: 1px 6px;\">\n" +
			  " \n" +
			  "\n" +
			  "  \n" +
			  " </form>\n" +
			  " <script type=\"text/javascript\">\n" +
			  " //<![CDATA[\n" +
			  "  var newsletterSignup = new VarienForm('newsletter-signup');\n" +
			  " //]]>\n" +
			  " </script>\n" +
			  "\n" +
			  "\n" +
			  " \n" +
			  "<a href=\"https://membership.nrahq.org/forms/signup.asp?CampaignID=XP010072\" style=\"display: block; margin-bottom: 3px; text-align: center;\">\n" +
			  "P.O. Box 3109</br> \n" +
			  "Houston, TX 77253<br>\n" +
			  "Ph: <a href=\"tel:979.277.9676\">979-277-9676</a> |\n" +
			  "Fax: 979-277-9959 <br>\n" +
			  "Email - <a href=\"mailto:sales@ammotogo.com\">sales@ammotogo.com</a>\n" +
			  "\n" +
			  "  <p class=\"copy\">&copy; 2015. All Rights Reserved.</p>\n" +
			  " \n" +
			  "\n" +
			  "  <span style=\"position:absolute; left:-10000px;top:-100px;\"  itemprop=\"provider\" itemscope itemtype=\"http://schema.org/Organization\"><meta  itemprop=\"name\" content=\"Ammo To Go\"/><meta  itemprop=\"telephone\" content=\"979-277-9676\"/><meta  itemprop=\"faxNumber\" content=\"979-277-9959 \"/><span  itemprop=\"address\" itemscope itemtype=\"http://schema.org/PostalAddress\">PO Box <span  itemprop=\"postOfficeBoxNumber\">3109</span> <span  itemprop=\"addressRegion\">Houston, TX</span> <span  itemprop=\"postalCode\">77253</span></span><meta  itemprop=\"url\" content=\"http://www.ammunitiontogo.com/\"/></span><meta  itemprop=\"name\" content=\"Ammo To Go : Ammunition | Bulk and Cheap Ammo for Sale\"/>" +
			  "\n" +
			  "\n" +
			  "</body>\n" +
			  "</html>\n";


	@Test
	public void languageDetectionTest() throws Exception{
//
//		TikaOutput tikaOutput = tikaService.parseHtml(text_en);
//		System.out.println(tikaOutput);
		Document htmlDocument = Jsoup.parse(text_en);

		String input = htmlDocument.title();// + htmlDocument.body().text();
		String language=tikaService.identifyLanguage(input);
		System.out.println(language);
	}


}
