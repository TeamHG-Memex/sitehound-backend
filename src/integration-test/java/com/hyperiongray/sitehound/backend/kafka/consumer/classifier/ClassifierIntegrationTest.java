package com.hyperiongray.sitehound.backend.kafka.consumer.classifier;

import com.hyperiongray.sitehound.backend.Application;
import com.hyperiongray.sitehound.backend.service.nlp.classifier.ClassifierSyncClient;
import com.hyperiongray.sitehound.backend.config.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.net.URI;
import java.util.Set;

import static junit.framework.TestCase.fail;

/**
 * Created by tomas on 1/03/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@ActiveProfiles("integration-test")
public class ClassifierIntegrationTest {

//    @Autowired ClassifierService classifierService;
    @Autowired private ClassifierSyncClient classifierSyncClient;


    private String rawHtml =
            "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
            "<head>\n" +
            "<title>PunkSPIDER</title>\n" +
            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
            "<!-- <link rel=\"stylesheet\" href=\"http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css\" /> -->\n" +
            "<!-- <link rel=\"stylesheet\" href=\"http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css\" /> -->\n" +
            "<link rel=\"stylesheet\" href=\"//code.jquery.com/ui/1.10.3/themes/eggplant/jquery-ui.css\" />\n" +
            "<script src=\"//code.jquery.com/jquery-1.8.0.js\"></script>\n" +
            "<script src=\"//code.jquery.com/ui/1.10.3/jquery-ui.js\"></script>\n" +
            "<script type=\"text/javascript\" src=\"/static/js/JSON.js\"></script>\n" +
            "<script type=\"text/javascript\" src=\"/static/js/jquery.atooltip.min.js\"></script>\n" +
            "<script type=\"text/javascript\" src=\"/static/js/aqPaging.js\"></script>\n" +
            "<script type=\"text/javascript\" src=\"/static/js/URLhelper.js\"></script>\n" +
            "<script type=\"text/javascript\" src=\"/static/js/search.js\"></script>\n" +
            "<link type=\"text/css\" href=\"/static/css/default.css\" rel=\"stylesheet\" />\n" +
            "<link type=\"text/css\" href=\"/static/css/atooltip.css\" rel=\"stylesheet\" />\n" +
            "<link rel='icon' href=\"/static/img/favicon.ico\" type='image/png' />\n" +
            "<link rel=\"stylesheet\" href=\"/static/css/odometer-theme-car.css\" />\n" +
            "<script src=\"/static/js/odometer.js\"></script>\n" +
            "\n" +
            "</head>\n" +
            "<body>\n" +
            "\t<div id=\"mainWrapper\">\n" +
            "      <script>\n" +
            "      function isIE() { \n" +
            "        return ((navigator.appName == 'Microsoft Internet Explorer') || ((navigator.appName == 'Netscape') && (new RegExp(\"Trident/.*rv:([0-9]{1,}[\\.0-9]{0,})\").exec(navigator.userAgent) != null))); }\n" +
            "        isie = isIE();\n" +
            "        if (isie) {\n" +
            "          document.write('<div id=\"iedetect-dialog\" class=\"dialog\" title=\" Unsupported Browser\">Warning! You are on a browser unsupported by PunkSPIDER, you will most likely see issues with the site. Please use any browser besides Internet Explorer. The world will be better for it.</div>');\n" +
            "        }\n" +
            "      </script>\n" +
            "\n" +
            "\t\t<div id=\"main\">\n" +
            "\t\t\t<!-- Define the page header -->\n" +
            "\t\t\t<div name=\"page_header\" class=\"page_header\">\n" +
            "\t\t\t\t<div class=\"text_header\">\n" +
            "\t\t\t\t\t<a id=\"home\" class=\"top-button\" href=\"#\">Home</a>\n" +
            "\t\t\t\t\t<a href=\"https://hyperiongray.atlassian.net/wiki/display/PUB/PunkSPIDER+Search+Help\" target=\"_blank\" class=\"top-button\">Search Tips</a>\n" +
            "\t\t\t\t\t<!-- <a id=\"discourse\" class=\"top-button\" href=\"https://discourse.hyperiongray.com/\" target=\"_blank\">Forum/Discussion</a>-->\n" +
            "\t\t\t\t\t<!--<a id=\"plugins\" class=\"top-button\" href=\"plugins\">Plugins</a>\n" +
            "\t\t\t\t\t<a id=\"lists\" class=\"top-button\" href=\"lists\">Black/Whitelists</a>-->\n" +
            "\t\t\t\t\t<!--<a id=\"contributors\" class=\"top-button\" href=\"contributors.html\">Contributors</a>-->\n" +
            "\t\t\t\t\t<a href=\"/register\" class=\"top-button\">Join PunkSPIDER Community</a>\n" +
            "\t\t\t\t\t<a href=\"/login\" class=\"top-button\">Login To PunkSPIDER Community</a>\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t</div>\n" +
            "\t\t\t<div id=\"band\" class=\"band welcome\">\n" +
            "\t\t\t\t<img class=\"bandimg\" src=\"/static/img/spiderweb.png\" alt=\"bandimg\" />\n" +
            "\t\t\t\t<div class=\"title\">Welcome to PunkSPIDER</div>\n" +
            "\t\t\t\t<div class=\"subtitle\">a global web application vulnerability search engine.</div>\n" +
            "                <div class=\"subtitle\">Deeper, faster, harder scans: <a id=\"comlink\" href=\"/register\">Join PunkSPIDER Community Edition (it's free and takes 5 seconds)!</a>\n" +
            "                </div>\n" +
            "\n" +
            "                <!-- Odometr includes -->\n" +
            "\n" +
            "                <!-- Extra styles for this example -->\n" +
            "                <style>\n" +
            "                .odometer {\n" +
            "                    font-size: 30px;\n" +
            "                }\n" +
            "                </style>\n" +
            "\n" +
            "                <script>\n" +
            "                    setTimeout(function(){\n" +
            "                    ods.innerHTML = 89560699;\n" +
            "                    odv.innerHTML = 3377131;\n" +
            "                    }, 1000);\n" +
            "                </script>\n" +
            "\n" +
            "\n" +
            "\t\t\t\t<!-- <div id=\"maintenance\" style=\"font-size:10px\">You may experience a slight service interruption due to scheduled maintenance - apologies for the inconvenience</div>-->\n" +
            "\t\t\t\t<!-- \t\t\t\t\t</div> -->\n" +
            "\t\t\t\t<!-- \t\t\t\t</div> -->\n" +
            "\t\t\t</div>\n" +
            "\t\t\t<div id=\"wrapperContainer\">\n" +
            "\t\t\t\t<div id=\"container\" class=\"container\">\n" +
            "\t\t\t\t\t<div class=\"mainContent\">\n" +
            "\t\t\t\t\t\t<div class=\"disclaimer\"></div>\n" +
            "\t\t\t\t\t\t<div id=\"mainSearch\" class=\"mainSearch\">\n" +
            "\t\t\t\t\t\t\t<div id=\"img-above\" class=\"borderable\">\n" +
            "\t\t\t\t\t\t\t\t<img class=\"mainlogo\" src=\"/static/img/hg-logo.png\" alt=\"logo\" />\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t<div id=\"search\" align=\"center\">\n" +
            "\t\t\t\t\t\t\t\t<div class=\"mainSearch\" align=\"center\">\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"searchByRadio\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<input type=\"radio\" id=\"searchBy_Url\" name=\"searchBy\" value=\"url\" checked=\"checked\" />\n" +
            "\t\t\t\t\t\t\t\t\t\t<label for=\"searchBy_Url\">URL</label>\n" +
            "\t\t\t\t\t\t\t\t\t\t<!--<input type=\"radio\" id=\"searchBy_Title\" name=\"searchBy\" value=\"title\" />\n" +
            "\t\t\t\t\t\t\t\t\t\t<label for=\"searchBy_Title\">Title</label>-->\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"omnibox borderable\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<input id=\"txtOmnibox\" placeholder=\"Please enter a url or search term...\" />\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"searchButton\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<input id=\"searchBTN\" type=\"button\" value=\"Search!\" />\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t<div class=\"advancedSearch borderable\">\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"vulCheckboxSet\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<input id=\"chkbsqli\" type=\"checkbox\" class=\"vulcheckbox\" value=\"bsqli\" title=\"Blind SQL Injection\" />\n" +
            "\t\t\t\t\t\t\t\t\t\t<label for=\"chkbsqli\">BSQLI</label>\n" +
            "\t\t\t\t\t\t\t\t\t\t<input id=\"chksqli\" type=\"checkbox\" class=\"vulcheckbox\" value=\"sqli\" title=\"SQL Injection\"/>\n" +
            "\t\t\t\t\t\t\t\t\t\t<label for=\"chksqli\">SQLI</label>\n" +
            "\t\t\t\t\t\t\t\t\t\t<input id=\"chkxss\" type=\"checkbox\" class=\"vulcheckbox\" value=\"xss\"  title=\"Cross-site scripting\"/>\n" +
            "\t\t\t\t\t\t\t\t\t\t<label for=\"chkxss\">XSS</label>\n" +
            "\t\t\t\t\t\t\t\t\t\t<input id=\"chktrav\" type=\"checkbox\" class=\"vulcheckbox\" value=\"trav\" title=\"Path traversal\"/>\n" +
            "\t\t\t\t\t\t\t\t\t\t<label for=\"chktrav\">TRAV</label>\n" +
            "\t\t\t\t\t\t\t\t\t\t<input id=\"chkmxi\" type=\"checkbox\" class=\"vulcheckbox\" value=\"mxi\" title=\"Mail header injection\"/>\n" +
            "\t\t\t\t\t\t\t\t\t\t<label for=\"chkmxi\">MXI</label>\n" +
            "\t\t\t\t\t\t\t\t\t\t<input id=\"chkosci\" type=\"checkbox\" class=\"vulcheckbox\" value=\"osci\" title=\"Operating system command injection\"/>\n" +
            "\t\t\t\t\t\t\t\t\t\t<label for=\"chkosci\">OSCI</label>\n" +
            "\t\t\t\t\t\t\t\t\t\t<input id=\"chkxpathi\" type=\"checkbox\" class=\"vulcheckbox\" value=\"xpathi\" title=\"Xpath injection\"/>\n" +
            "\t\t\t\t\t\t\t\t\t\t<label for=\"chkxpathi\">XPATHI</label>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"vulCheckOptSet\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<input type=\"radio\" id=\"filterType_OR\" title=\"Any of the selected\" name=\"filterType\" value=\"or\" checked=\"checked\" />\n" +
            "\t\t\t\t\t\t\t\t\t\t<label for=\"filterType_OR\">OR</label>\n" +
            "\t\t\t\t\t\t\t\t\t\t<input type=\"radio\" id=\"filterType_AND\" title=\"All of the selected\" name=\"filterType\" value=\"and\" />\n" +
            "\t\t\t\t\t\t\t\t\t\t<label for=\"filterType_AND\">AND</label>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t<div style=\"clear: both\"></div>\n" +
            "\t\t\t\t\t\t<div class=\"tableSorter\">\n" +
            "\t\t\t\t\t\t\t<ol class=\"resultTable\">\n" +
            "\t\t\t\t\t\t\t\t<!--<li></li>-->\n" +
            "\t\t\t\t\t\t\t</ol>\n" +
            "\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t<div class=\"paginatorContainer\"></div>\n" +
            "                        <div id=\"resultsTrimmedBox\" style=\"display:none;\">Results have been trimmed, we only return the first 10,000 hits</div>\n" +
            "\t\t\t\t\t\t<br />\n" +
            "\t\t\t\t\t</div>\n" +
            "                    <div id=\"count-wrapper\">\n" +
            "                        <div id=\"ods\" class=\"odometer\"></div>\n" +
            "                        <span>Sites Scanned</span>\n" +
            "    \t\t\t\t    <div id=\"odv\" class=\"odometer\"></div>\n" +
            "                        <span>Vulnerabilities found</span>\n" +
            "                    </div>\n" +
            "\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t\t<div class=\"contribsContainer\"></div>\n" +
            "\t\t\t</div>\n" +
            "\t\t\t<div id=\"dialog\" class=\"dialog\" title=\" Please do not use this site for malicious purposes\">\n" +
            "\t\t\t\t<p>\n" +
            "\t\t\t\t\t<br />\n" +
            "                    PunkSPIDER 3.0 is now more powerful than ever, but with great power comes great responsibility.\n" +
            "\t\t\t\t\t<br />\n" +
            "                    <br />\n" +
            "\t\t\t\t\tThe goal is to provide free information to website users and owners regarding website security status.\n" +
            "                    <br />\n" +
            "                    <br />\n" +
            "\t\t\t\t\tWe take this very seriously, use it wisely or we'll have to take it away.\n" +
            "\t\t\t\t</p>\n" +
            "\t\t\t</div>\n" +
            "\t\t</div>\n" +
            "\t</div>\n" +
            "\t<div class=\"footer\">\n" +
            "\t\t<span class=\"footspan\">\n" +
            "\t\t\t<form action=\"https://www.paypal.com/cgi-bin/webscr\" method=\"post\" target=\"_top\">\n" +
            "\t\t\t\t<input type=\"hidden\" name=\"cmd\" value=\"_s-xclick\" />\n" +
            "\t\t\t\t<input type=\"hidden\" name=\"hosted_button_id\" value=\"JF2GUHZMEDW8C\" />\n" +
            "\t\t\t\t<input type=\"image\" src=\"https://www.paypalobjects.com/en_US/i/btn/btn_donate_LG.gif\" border=\"0\" name=\"submit\"\n" +
            "\t\t\t\t\talt=\"PayPal - The safer, easier way to pay online!\" />\n" +
            "\t\t\t\t<img alt=\"\" border=\"0\" src=\"https://www.paypalobjects.com/en_US/i/scr/pixel.gif\" width=\"1\" height=\"1\" />\n" +
            "\t\t\t</form>\n" +
            "\t\t</span>\n" +
            "\t\t<span class=\"footspan\">\n" +
            "\t\t\t<a class=\"footlink\" href=\"https://hyperiongray.atlassian.net/wiki/display/PUB/New+To+Web+Security+Help+Page\" target=\"_blank\">New To\n" +
            "\t\t\t\tWeb Security? | </a>\n" +
            "\t\t</span>\n" +
            "\t\t<span class=\"footspan\">\n" +
            "\t\t\t<a class=\"footlink\" href=\"http://hyperiongray.tumblr.com/\" target=\"_blank\">Official Blog</a>\n" +
            "\t\t\t|\n" +
            "\t\t</span>\n" +
            "        <span class=\"footspan\">\n" +
            "            <a href=\"http://www.hyperiongray.com/#works\" target=\"_blank\" class=\"top-button\">About PunkSpider</a>\n" +
            "            |\n" +
            "        </span>\n" +
            "        <span class=\"footspan\">\n" +
            "            <a href=\"http://www.hyperiongray.com/\" target=\"_blank\" class=\"top-button\">About Hyperion Gray</a>\n" +
            "            |\n" +
            "        </span>\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\t\t<span id=\"twitlink\" class=\"footspan\">\n" +
            "\t\t\t<a href=\"https://twitter.com/HyperionGray\" class=\"twitter-follow-button\" data-show-count=\"false\" data-dnt=\"true\">Follow @HyperionGray</a>\n" +
            "\t\t\t<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';fjs.parentNode.insertBefore(js,fjs);}}(document, 'script', 'twitter-wjs');\n" +
            "        </script>\n" +
            "\t\t</span>\n" +
            "\t\t<span id=\"twitlink\" class=\"footspan\">\n" +
            "\t\t\t<a href=\"https://twitter.com/DotSlashPunk\" class=\"twitter-follow-button\" data-show-count=\"false\" data-dnt=\"true\">Follow @DotSlashPunk</a>\n" +
            "\t\t\t<!--<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?$\n" +
            "        </script>-->\n" +
            "\t\t</span>\n" +
            "\t</div>\n" +
            "\n" +
            "        <script src=\"//static.getclicky.com/js\" type=\"text/javascript\"></script>\n" +
            "        <script type=\"text/javascript\">try{ clicky.init(100841437); }catch(e){}</script>\n" +
            "        <noscript><p><img alt=\"Clicky\" width=\"1\" height=\"1\" src=\"//in.getclicky.com/100841437ns.gif\" /></p></noscript>\n" +
            "\n" +
            "</body>\n" +
            "</html>";

    private String helloWorld = "<html><body>hello world!</body></html>";

    private String smallPs  = "<html><body>hello world!</body></html>";
    private String mediumPs =

//            "<!DOCTYPE HTML PUBLIC>"// +
            "<!DOCTYPE HTML PUBLIC \\\"-//W3C//DTD XHTML 1.0 Transitional//EN\\\" \\\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\\\">" //dont+
//            "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">"// +


//            "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"// +
//            "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
//            "<head>\n" +
//            "<title>PunkSPIDER</title>\n" +
//            "<script src=\"/static/js/odometer.js\"></script>\n" +
//            "\n" +
//            "</head>\n" +
//            "<body>\n" +
//            "\t<div id=\"mainWrapper\">\n" //+
//            "      <script>\n" +
//            "      function isIE() { \n" +
//            "        return ((navigator.appName == 'Microsoft Internet Explorer') || ((navigator.appName == 'Netscape') && (new RegExp(\"Trident/.*rv:([0-9]{1,}[\\.0-9]{0,})\").exec(navigator.userAgent) != null))); }\n" +
//            "        isie = isIE();\n" +
//            "        if (isie) {\n" +
//            "          document.write('<div id=\"iedetect-dialog\" class=\"dialog\" title=\" Unsupported Browser\">Warning! You are on a browser unsupported by PunkSPIDER, you will most likely see issues with the site. Please use any browser besides Internet Explorer. The world will be better for it.</div>');\n" +
//            "        }\n" +
//            "      </script>\n" +
//            "\n" +
//            "\t\t<div id=\"main\">\n" +
//            "\t\t\t<!-- Define the page header -->\n" +
//            "\t\t\t<div name=\"page_header\" class=\"page_header\">\n" +
//            "            \"\\n\" +\n" +
//            "            \"        <script src=\\\"//static.getclicky.com/js\\\" type=\\\"text/javascript\\\"></script>\\n\" +\n" +
//            "            \"        <script type=\\\"text/javascript\\\">try{ clicky.init(100841437); }catch(e){}</script>\\n\" +\n" +
//            "            \"        <noscript><p><img alt=\\\"Clicky\\\" width=\\\"1\\\" height=\\\"1\\\" src=\\\"//in.getclicky.com/100841437ns.gif\\\" /></p></noscript>\\n\" +\n" +
//            "            \"\\n\" +\n" +
//            "            \"</body>\\n\" +\n"
            ;


    String cochesnet = "" +
            "\n" +
            "\n" +
            "\n" +
            "<!DOCTYPE html>\n" +
            "\n" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
            "<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><link rel=\"dns-prefetch\" href=\"//c.ccdn.es\"><link rel=\"dns-prefetch\" href=\"//a.ccdn.es\"><meta content=\"es\" name=\"Content-Language\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><title>\n" +
            "\tCoches.net: Coches nuevos, coches de ocasión, seminuevos, Km0.\n" +
            "</title><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" +
            "<body>\n" +
            "    <form method=\"post\" action=\"/default.aspx\" id=\"form1\">\n" +
            "<div class=\"aspNetHidden\">\n" +
            "<input type=\"hidden\" name=\"__EVENTTARGET\" id=\"__EVENTTARGET\" value=\"\" />\n" +
            "<input type=\"hidden\" name=\"__EVENTARGUMENT\" id=\"__EVENTARGUMENT\" value=\"\" />\n" +
            "<input type=\"hidden\" name=\"__VIEWSTATE\" id=\"__VIEWSTATE\" value=\"/wEPDwULLTE5MTIxMjg3NjRkZIU46RZFPgEGDaI65I4e4RtVM+hR\" />\n" +
            "</div>\n" +
            "\n" +
            "<script type=\"text/javascript\">\n" +
            "<!--\n" +
            "var theForm = document.forms['form1'];\n" +
            "if (!theForm) {\n" +
            "    theForm = document.form1;\n" +
            "}\n" +
            "function __doPostBack(eventTarget, eventArgument) {\n" +
            "    if (!theForm.onsubmit || (theForm.onsubmit() != false)) {\n" +
            "        theForm.__EVENTTARGET.value = eventTarget;\n" +
            "        theForm.__EVENTARGUMENT.value = eventArgument;\n" +
            "        theForm.submit();\n" +
            "    }\n" +
            "}\n" +
            "// -->\n" +
            "</script>\n" +
            "\n" +
            "\n" +
            "<div class=\"aspNetHidden\">\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "        <div class=\"content\">\n" +
            "            <div class=\"page\">\n" +
            "                <div id=\"banner_top\" style=\"min-height: 90px\" class=\"publi-top\">\n" +
            "                    <div id=\"PubTop1\"></div>\n" +
            "                </div>\n" +
            "\n" +
            "                <header>\n" +
            "                    <!-- control menu slide (MOVIL) -->\n" +
            "                    \n" +
            "\n" +
            "<div class=\"content hide--in-desktop\">\n" +
            "    <div class=\"topbar topbar_logo-wrap\">\n" +
            "        <!-- SLIDE MENU BUTTON getDdTrainerInputStart -->\n" +
            "        <a\n" +
            "            href=\"#\"\n" +
            "            class=\"nav-slide_open-btn\"\n" +
            "            data-navslide=\"toggle\">\n" +
            "            <span class=\"icon-menu\"></span>\n" +
            "        </a>\n" +
            "        <!-- SLIDE MENU BUTTON end -->\n" +
            "\n" +
            "        <!-- LOGO getDdTrainerInputStart -->\n" +
            "        <a class=\"topbar_logo logo\" href=\"/\"><b>coches.net</b></a>\n" +
            "        <!-- LOGO end -->\n" +
            "    </div>\n" +
            "\n" +
            "    <div class=\"topbar nav-slide clearfix\">\n" +
            "        <div class=\"nav-slide_mask\" data-navslide=\"close\"></div>\n" +
            "\n" +
            "        <div class=\"nav-slide_content\">\n" +
            "            <div class=\"topbar_menus\">\n" +
            "                <nav class=\"topbar_main-nav\">\n" +
            "                    <ul>\n" +
            "                        <!-- NO LOGGED USER getDdTrainerInputStart -->\n" +
            "                        <li class=\"hide--when-logged\">\n" +
            "                            <a href=\"/signupin.aspx?p=l\" class=\"user-thumb nav_btn clearfix\">\n" +
            "                                <img src=\"/Images/ico-nouser.jpg\" data-login=\"avatar\" class=\"user-thumb_avatar\" />\n" +
            "                                <span class=\"user-thumb_name\"><b>Entrar o registrarme</b></span>\n" +
            "                            </a>\n" +
            "                        </li>\n" +
            "                        <!-- NO LOGGED USER end -->\n" +
            "\n" +
            "                        <!-- LOGGED USER getDdTrainerInputStart -->\n" +
            "                        <li class=\"hide--when-no-logged\">\n" +
            "                            <a href=\"/GTA_new/default.aspx\" class=\"user-thumb nav_btn clearfix\">\n" +
            "                                <img src=\"/Images/ico-user.jpg\" data-login=\"avatar\" class=\"user-thumb_avatar\" />\n" +
            "                                <span class=\"user-thumb_name\" data-login=\"name\"></span>\n" +
            "                            </a>\n" +
            "                        </li>\n" +
            "                        <!-- LOGGED USER end -->\n" +
            "\n" +
            "                        <li>\n" +
            "                            <a href=\"/GTA_new/favoritos.aspx\" class=\"nav_btn has--extra-padding\"><span class=\"icon-estrella2\"></span>Mis favoritos</a>\n" +
            "                        </li>\n" +
            "                        <li>\n" +
            "                            <a href=\"/GTA_new/alertas.aspx\" class=\"nav_btn\"><span class=\"icon-bell\"></span>Mis alertas</a>\n" +
            "                        </li>\n" +
            "                        <li>\n" +
            "                            <a href=\"/GTA_new/anuncios.aspx\" class=\"nav_btn\"><span class=\"icon-coche\"></span>Mis anuncios</a>\n" +
            "                        </li>\n" +
            "\n" +
            "                        <li>\n" +
            "                            <a href=\"/\" class=\"nav_btn has--border-top\"><span class=\"icon-lupa\"></span>Buscar</a>\n" +
            "                        </li>\n" +
            "\n" +
            "                        <li>\n" +
            "                            <a href=\"/noticias/\" class=\"nav_btn\"><span class=\"icon-noticies\"></span>Pruebas y novedades</a>\n" +
            "                        </li>\n" +
            "\n" +
            "                        <li>\n" +
            "                            <a href=\"/fichas_tecnicas/\" class=\"nav_btn\"><span class=\"icon-servicios\"></span>Fichas técnicas</a>\n" +
            "                        </li>\n" +
            "\n" +
            "                        <li>\n" +
            "                            <a href=\"/Vender-mi-coche.aspx\" class=\"nav_btn nav_btn--blue\"><span class=\"icon-anunci\"></span>Publica tu anuncio</a>\n" +
            "                        </li>\n" +
            "                        \n" +
            "\n" +
            "                        \n" +
            "\n" +
            "                        <li class=\"hide--when-no-logged\">\n" +
            "                            <a href=\"/logout.aspx\" class=\"nav_btn has--border-top\"><span class=\"icon-link\"></span>Cerrar sesión</a>\n" +
            "                        </li>\n" +
            "                    </ul>\n" +
            "                </nav>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</div>\n" +
            "\n" +
            "                    <!-- fi control menu slide (MOVIL) -->\n" +
            "\n" +
            "                    <!-- control menu (DESKTOP) -->\n" +
            "                    \n" +
            "\n" +
            "<div class=\"content hide--in-mobile\">\n" +
            "    <div class=\"topbar nav-slide clearfix\" data-tooltip=\"front\">\n" +
            "        <div class=\"topbar_logo-wrap\">\n" +
            "            <a class=\"topbar_logo logo\" href=\"/\"><b>coches.net</b></a>\n" +
            "        </div>\n" +
            "\n" +
            "        <div class=\"nav-slide_content\">\n" +
            "            <div class=\"topbar_menus clearfix\">\n" +
            "                <div class=\"topbar_pta\">\n" +
            "                    <a class=\"btn btn-s btn-secondary\" href=\"/Vender-mi-coche.aspx\"><span class=\"icon-anunci\"></span>Publica tu anuncio</a>\n" +
            "                </div>\n" +
            "\n" +
            "                <div class=\"topbar_user drop-bubble_wrap mt-tooltip__wrap\" data-dropbubble-nav=\"open\" data-tooltip=\"wrap\">\n" +
            "                    <a href=\"/signupin.aspx?p=l\" class=\"user-thumb clearfix\">\n" +
            "                        <img src=\"/Images/ico-nouser.jpg\" data-login=\"avatar\" class=\"user-thumb_avatar\" />\n" +
            "                        <span class=\"user-thumb_name\" data-login=\"name\">Mi cuenta</span>\n" +
            "                    </a>\n" +
            "\n" +
            "                    <ul id=\"menu_gta\" class=\"drop-bubble\">\n" +
            "                        <!-- NO LOGGED USER getDdTrainerInputStart -->\n" +
            "                        <li class=\"hide--when-logged\">\n" +
            "                            <a  href=\"/signupin.aspx?p=l\" class=\"user-thumb nav_btn clearfix\">\n" +
            "                                <span class=\"icon-user\"></span>\n" +
            "                                <span class=\"user-thumb_name\"><b>Entrar o registrarme</b></span>\n" +
            "                            </a>\n" +
            "                        </li>\n" +
            "                        <!-- NO LOGGED USER end -->\n" +
            "\n" +
            "                        <!-- LOGGED USER getDdTrainerInputStart -->\n" +
            "                        <li class=\"hide--when-no-logged\">\n" +
            "                            <a href=\"/GTA_new/Default.aspx\" class=\"nav_btn has--extra-padding has--border-bottom js_menu_actions\"><span class=\"icon-editar-perfil\"></span>Editar mi cuenta</a>\n" +
            "                        </li>\n" +
            "                        <!-- LOGGED USER end -->\n" +
            "\n" +
            "                        <li id=\"favorites\">\n" +
            "                            <a href=\"/GTA_new/favoritos.aspx\" class=\"nav_btn js_menu_actions\"><span class=\"icon-estrella2\"></span>Mis favoritos</a>\n" +
            "                        </li>\n" +
            "                        <li class=\"active\">\n" +
            "                            <a href=\"/GTA_new/alertas.aspx\" class=\"nav_btn js_menu_actions\"><span class=\"icon-bell\"></span>Mis alertas</a>\n" +
            "                        </li>\n" +
            "                        <li>\n" +
            "                            <a href=\"/GTA_new/anuncios.aspx\" class=\"nav_btn js_menu_actions\"><span class=\"icon-coche\"></span>Mis anuncios</a>\n" +
            "                        </li>\n" +
            "                        <li class=\"hide--when-logged\">\n" +
            "                            <a href=\"http://ev.coches.net/\" class=\"nav_btn js_menu_actions has--border-top\"><span class=\"icon-share\"></span>Soy profesional</a>\n" +
            "                        </li>\n" +
            "\n" +
            "                        <li class=\"hide--when-no-logged\">\n" +
            "                            <a href=\"/logout.aspx\" class=\"nav_btn has--border-top\"><span class=\"icon-link\"></span>Cerrar sesión</a>\n" +
            "                        </li>\n" +
            "                    </ul>\n" +
            "                </div>\n" +
            "\n" +
            "                <nav class=\"topbar_main-nav\">\n" +
            "                    <ul class=\"clearfix\">\n" +
            "                        <li class=\"drop-bubble_wrap\" data-dropbubble-nav=\"open\">\n" +
            "                            <a href=\"#\" class=\"nav_btn has--border-top\"><span class=\"icon-lupa\"></span>Buscar</a>\n" +
            "                            <div class=\"drop-bubble clearfix\">\n" +
            "                                <div class=\"drop-bubble_section\">\n" +
            "                                    <div class=\"drop-bubble_title\">Coches</div>\n" +
            "                                    <ul class=\"drop-bubble_nav\">\n" +
            "                                        <li>\n" +
            "                                            <a href=\"/coches-de-ocasion.aspx\"\n" +
            "                                            title=\"Coches de segunda mano\"\n" +
            "                                            class=\"nav_btn\"\n" +
            "                                               data-xtclib=\"menu_ocasion_coches\">Coches de segunda mano</a>\n" +
            "                                        </li>\n" +
            "                                        <li>\n" +
            "                                            <a href=\"/ofertas_especiales/\"\n" +
            "                                            title=\"Coches km 0\"\n" +
            "                                            class=\"nav_btn\"\n" +
            "                                               data-xtclib=\"menu_km0_buscar\">Coches km 0</a>\n" +
            "                                        </li>\n" +
            "                                        <li>\n" +
            "                                            <a href=\"/nuevos/\"\n" +
            "                                            class=\"nav_btn\"\n" +
            "                                               data-xtclib=\"Menu_nuevos_buscar\">Coches nuevos</a>\n" +
            "                                        </li>\n" +
            "                                    </ul>\n" +
            "                                </div>\n" +
            "\n" +
            "                                <div class=\"drop-bubble_section\">\n" +
            "                                    <div class=\"drop-bubble_title\">Más vehículos</div>\n" +
            "                                    <ul class=\"drop-bubble_nav\">\n" +
            "                                        <li>\n" +
            "                                            <a href=\"/vehiculos-industriales.aspx\"\n" +
            "                                            class=\"nav_btn\"\n" +
            "                                               data-xtclib=\"Menu_ocasion_industriales\">Furgonetas e industriales</a>\n" +
            "                                        </li>\n" +
            "                                        <li>\n" +
            "                                            <a href=\"/coches-clasicos-competicion.aspx\"\n" +
            "                                            class=\"nav_btn\"\n" +
            "                                               data-xtclib=\"Menu_ocasion_clasicos\">Clásicos y competición</a>\n" +
            "                                        </li>\n" +
            "                                        <li>\n" +
            "                                            <a href=\"/autocaravanas-y-remolques.aspx\"\n" +
            "                                            class=\"nav_btn\"\n" +
            "                                               data-xtclib=\"Menu_ocasion_caravanas\">Autocaravanas y remolques</a>\n" +
            "                                        </li>\n" +
            "                                        <li>\n" +
            "                                            <a href=\"/coches-sin-carnet.aspx\"\n" +
            "                                            class=\"nav_btn\"\n" +
            "                                               data-xtclib=\"Menu_ocasion_sincarnet\">Coches sin carnet</a>\n" +
            "                                        </li>\n" +
            "                                    </ul>\n" +
            "                                </div>\n" +
            "\n" +
            "\n" +
            "                                <div class=\"drop-bubble_section\">\n" +
            "                                    <div class=\"drop-bubble_title\">Otros</div>\n" +
            "                                    <ul class=\"nb drop-bubble_nav\">\n" +
            "                                        <li>\n" +
            "                                            <a href=\"/accesorios-de-coches.aspx\"\n" +
            "                                            class=\"nav_btn\"\n" +
            "                                               data-xtclib=\"Menu_accesorios\">Accesorios</a>\n" +
            "                                        </li>\n" +
            "                                    </ul>\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "                        </li>\n" +

            "</div>\n" +
            "\n" +
            "                    <!-- fi control menu (DESKTOP) -->\n" +
            "                </header>\n" +
            "                <div class=\"alert alert-ok\" style=\"display: none\" id=\"divMsgCuentaCreada\">\n" +
            "                    <span class=\"icon-ok\"></span>Se ha creado una cuenta en Coches.net con tus datos. <a id=\"msgCuentaCreada\" href=\"#\">entendido</a>\n" +
            "                </div>\n" +
            "                <!-- control content -->\n" +
            "                \n" +
            "    \n" +
            "\n" +
            "<a id=\"showdialog\" class=\"layer\" style=\"display: none;\" href=\"#layer-provincias\" rel=\"leanModal\"></a>\n" +
            "\n" +
            "<div id=\"layer-provincias\" style=\"display:none\">\n" +
            "    <p>Ofertas disponibles en las siguientes provincias:<a href=\"#\" class=\"modal_close\" data-xtclib=\"layer_provincias_close\"></a></p>\n" +
            "    <div id=\"div_provincias\"></div>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"bg-buscador\" xtcz=\"Buscador1\" xtdztype=\"CZSE\">\n" +
            "    <div class=\"buscador\">\n" +
            "        <ul class=\"tabs-search\">\n" +
            "            <li class=\"oldfilter filter ani active searcher\" data-tipo=\"1\" data-suggest=\"2\"><span data-xtclib=\"Buscador_Segundamano\" xtcltype=\"A\" onclick=\"javascript:void(0)\">coches de segunda mano</span></li>\n" +
            "            <li class=\"oldfilter filter ani searcher\" data-tipo=\"3\" data-suggest=\"3\"><span data-xtclib=\"Buscador_km0\" xtcltype=\"A\" onclick=\"javascript:void(0)\">km0, gerencia...</span></li>\n" +
            "            <li class=\"oldfilter filter ani searcher\" data-tipo=\"2\" data-suggest=\"3\"><span data-xtclib=\"Buscador_Nuevo\" xtcltype=\"A\" onclick=\"javascript:void(0)\">nuevo</span></li>\n" +
            "            <li class=\"oldfilter filter ani todo searcher\" data-tipo=\"0\" data-suggest=\"4\"><span data-xtclib=\"Buscador_TodoCochesnet\" xtcltype=\"A\" onclick=\"javascript:void(0)\">en todo coches.net</span></li>\n" +
            "            <li class=\"newfilter filter ani searcher\" style=\"display:none\" data-tipo=\"1\" data-suggest=\"2\"><span data-xtclib=\"Buscador_Coche\" xtcltype=\"A\" onclick=\"javascript:void(0)\">Buscar un coche</span></li>\n" +
            "            <li class=\"newfilter filter ani searcher\" style=\"display:none\" data-tipo=\"2\" data-suggest=\"3\"><span data-xtclib=\"Buscador_Configurar_Nuevo\" xtcltype=\"A\" onclick=\"javascript:void(0)\">Configurar un coche nuevo</span></li>\n" +
            "            <li class=\"newfilter filter ani todo searcher\" style=\"display:none\" data-tipo=\"0\" data-suggest=\"4\"><span data-xtclib=\"Buscador_Todo\" xtcltype=\"A\" onclick=\"javascript:void(0)\">Todo sobre...</span></li>\n" +
            "        </ul>\n" +
            "        <div class=\"content-search ani\">\n" +
            "            <div class=\"filtres-col1\">\n" +
            "                <input name=\"_ctl0:ContentPlaceHolder1:WDYL1:txt_buscar\" type=\"text\" id=\"txt_buscar\" class=\"make-model txt_buscar\" placeholder=\"escribe aquí marca y modelo\" maxlength=\"150\" autocomplete=\"off\" />\n" +
            "                <select name=\"_ctl0:ContentPlaceHolder1:WDYL1:ddl_Price_To\" id=\"ddl_Price_To\" class=\"sel-price\">\n" +
            "\t<option selected=\"selected\" value=\"0\">precio hasta</option>\n" +
            "\t<option value=\"1000\">hasta 1.000 €</option>\n" +
            "\t<option value=\"2000\">hasta 2.000 €</option>\n" +
            "\t<option value=\"3000\">hasta 3.000 €</option>\n" +
            "\t<option value=\"4000\">hasta 4.000 €</option>\n" +
            "\t<option value=\"5000\">hasta 5.000 €</option>\n" +
            "\t<option value=\"6000\">hasta 6.000 €</option>\n" +
            "\t<option value=\"8000\">hasta 8.000 €</option>\n" +
            "\t<option value=\"10000\">hasta 10.000 €</option>\n" +
            "\t<option value=\"15000\">hasta 15.000 €</option>\n" +
            "\t<option value=\"20000\">hasta 20.000 €</option>\n" +
            "\t<option value=\"25000\">hasta 25.000 €</option>\n" +
            "\t<option value=\"30000\">hasta 30.000 €</option>\n" +
            "\t<option value=\"40000\">hasta 40.000 €</option>\n" +
            "\t<option value=\"50000\">hasta 50.000 €</option>\n" +
            "\t<option value=\"70000\">hasta 70.000 €</option>\n" +
            "\t<option value=\"999999\">+ de 70.000 €</option>\n" +
            "</select>\n" +
            "                <select name=\"_ctl0:ContentPlaceHolder1:WDYL1:ddl_Province\" id=\"ddl_Province\" class=\"sel-prov\">\n" +
            "\t<option selected=\"selected\" value=\"0\">provincia</option>\n" +
            "\t<option value=\"15\">A Coru&#241;a</option>\n" +
            "\t<option value=\"1\">Alava</option>\n" +
            "\t<option value=\"2\">Albacete</option>\n" +
            "\t<option value=\"3\">Alicante</option>\n" +
            "\t<option value=\"4\">Almer&#237;a</option>\n" +
            "\t<option value=\"33\">Asturias</option>\n" +
            "\t<option value=\"5\">&#193;vila</option>\n" +
            "\t<option value=\"6\">Badajoz</option>\n" +
            "\t<option value=\"7\">Baleares</option>\n" +
            "\t<option value=\"8\">Barcelona</option>\n" +
            "\t<option value=\"9\">Burgos</option>\n" +
            "\t<option value=\"10\">C&#225;ceres</option>\n" +
            "\t<option value=\"11\">C&#225;diz</option>\n" +
            "\t<option value=\"39\">Cantabria</option>\n" +
            "\t<option value=\"12\">Castell&#243;n</option>\n" +
            "\t<option value=\"51\">Ceuta</option>\n" +
            "\t<option value=\"13\">Ciudad Real</option>\n" +
            "\t<option value=\"14\">C&#243;rdoba</option>\n" +
            "\t<option value=\"16\">Cuenca</option>\n" +
            "\t<option value=\"17\">Girona</option>\n" +
            "\t<option value=\"18\">Granada</option>\n" +
            "\t<option value=\"19\">Guadalajara</option>\n" +
            "\t<option value=\"20\">Guip&#250;zcoa</option>\n" +
            "\t<option value=\"21\">Huelva</option>\n" +
            "\t<option value=\"22\">Huesca</option>\n" +
            "\t<option value=\"23\">Ja&#233;n</option>\n" +
            "\t<option value=\"26\">La Rioja</option>\n" +
            "\t<option value=\"35\">Las Palmas</option>\n" +
            "\t<option value=\"24\">Le&#243;n</option>\n" +
            "\t<option value=\"25\">Lleida</option>\n" +
            "\t<option value=\"27\">Lugo</option>\n" +
            "\t<option value=\"28\">Madrid</option>\n" +
            "\t<option value=\"29\">M&#225;laga</option>\n" +
            "\t<option value=\"52\">Melilla</option>\n" +
            "\t<option value=\"30\">Murcia</option>\n" +
            "\t<option value=\"31\">Navarra</option>\n" +
            "\t<option value=\"32\">Orense</option>\n" +
            "\t<option value=\"34\">Palencia</option>\n" +
            "\t<option value=\"36\">Pontevedra</option>\n" +
            "\t<option value=\"37\">Salamanca</option>\n" +
            "\t<option value=\"40\">Segovia</option>\n" +
            "\t<option value=\"41\">Sevilla</option>\n" +
            "\t<option value=\"42\">Soria</option>\n" +
            "\t<option value=\"38\">Sta. C. Tenerife</option>\n" +
            "\t<option value=\"43\">Tarragona</option>\n" +
            "\t<option value=\"44\">Teruel</option>\n" +
            "\t<option value=\"45\">Toledo</option>\n" +
            "\t<option value=\"46\">Valencia</option>\n" +
            "\t<option value=\"47\">Valladolid</option>\n" +
            "\t<option value=\"48\">Vizcaya</option>\n" +
            "\t<option value=\"49\">Zamora</option>\n" +
            "\t<option value=\"50\">Zaragoza</option>\n" +
            "</select>\n" +
            "                <input name=\"_ctl0:ContentPlaceHolder1:WDYL1:txt_cp\" type=\"text\" id=\"txt_cp\" class=\"txt_cp\" style=\"display:none;width:82px\" placeholder=\"cp\" maxlength=\"5\" autocomplete=\"off\" />\n" +
            "                <select name=\"_ctl0:ContentPlaceHolder1:WDYL1:ddl_FuelType\" id=\"ddl_FuelType\" class=\"sel-fuel\">\n" +
            "\t<option selected=\"selected\" value=\"0\">combustible</option>\n" +
            "\t<option value=\"1\">Diesel</option>\n" +
            "\t<option value=\"3\">El&#233;ctrico / H&#237;brido</option>\n" +
            "\t<option value=\"2\">Gasolina</option>\n" +
            "\t<option value=\"4\">Otros</option>\n" +
            "</select>\n" +
            "                <div id=\"slider-km\" class=\"slider km\"></div>\n" +
            "                <span id=\"km-min\" class=\"km\"></span>\n" +
            "                <span id=\"km-max\" class=\"km\"></span>\n" +
            "            </div>                \n" +
            "            <div class=\"filtres-col2\">\n" +
            "                <input name=\"_ctl0:ContentPlaceHolder1:WDYL1:btn_buscar\" type=\"submit\" id=\"btn_buscar\" value=\"¡Encuéntralo!\" class=\"ani btn_buscar\" />\n" +
            "                <div id=\"slider-any\" class=\"slider any\"></div>\n" +
            "                <span id=\"any-min\" class=\"any\"></span>\n" +
            "                <span id=\"any-max\" class=\"any\"></span>\n" +
            "            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "            \n" +
            "            <div class=\"bodies\">\n" +
            "                <div class=\"body ani\" data-bt=\"1\"><div class=\"cr1\"></div><p>Berlina y compacto</p></div>\n" +
            "                <div class=\"body ani\" data-bt=\"4\"><div class=\"cr2\"></div><p>Familiar</p></div>\n" +
            "                <div class=\"body ani\" data-bt=\"2\"><div class=\"cr3\"></div><p>Coupe</p></div>\n" +
            "                <div class=\"body ani\" data-bt=\"5\"><div class=\"cr4\"></div><p>Monovolumen</p></div>\n" +
            "                <div class=\"body ani\" data-bt=\"6\"><div class=\"cr5\"></div><p>4x4 y SUV</p></div>\n" +
            "                <div class=\"body ani\" data-bt=\"3\"><div class=\"cr6\"></div><p>Cabrio</p></div>\n" +
            "                <div class=\"clear\"></div>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "        <ul id=\"divSuggest\" class=\"texto-sugerido\"></ul>\n" +
            "        <div class=\"body-type ani\" data-xtclib=\"Buscador_MasOpionesBusqueda\" xtcltype=\"A\">opciones de búsqueda</div>\n" +
            "    </div>\n" +
            "    \n" +
            "    <div class=\"segments\" style=\"display:none\">\n" +
            "        <h3>¿Te ayudamos a buscar tu coche?</h3>\n" +
            "        <div class=\"segment seg1\"><a href=\"coches-de-ocasion.aspx?ArrBodyType=1&PowerHpTo=90&maxprice=6000\" title=\"Coches baratos para la ciudad\"><b>Baratos</b>para la ciudad</a></div>\n" +
            "        <div class=\"segment seg2\"><a href=\"coches-de-ocasion.aspx?ArrBodyType=1|4|5|6&maxprice=10000&minyear=2009\" title=\"Coches familiares económicos con pocos años\"><b>Familiares</b>económicos</a></div>\n" +
            "        <div class=\"segment seg3\"><a href=\"coches-de-ocasion.aspx?wwa=1&st=1&maxkms=50000\" title=\"Coches con garantía y pocos km\"><b>Con garantía</b>y pocos km</a></div>\n" +
            "        <div class=\"segment seg4\"><a href=\"coches-de-ocasion.aspx?FuelTypeId=1&MaxKms=30000&MinYear=2012&ArrOfferType=0,8&OfferTypeGroup=1\" title=\"Coches diesel seminuevos\"><b>Diesel</b>Seminuevos</a></div>\n" +
            "        <div class=\"clear\"></div>\n" +
            "    </div>\n" +
            "\n" +
            "</div>\n" +
            "<input name=\"_ctl0:ContentPlaceHolder1:WDYL1:hidQType\" type=\"hidden\" id=\"hidQType\" clienidmode=\"static\" />\n" +
            "<input name=\"_ctl0:ContentPlaceHolder1:WDYL1:hFilter\" type=\"hidden\" id=\"hFilter\" clientdidmode=\"static\" />\n" +
            "<input name=\"_ctl0:ContentPlaceHolder1:WDYL1:tipobusqueda\" type=\"hidden\" id=\"tipobusqueda\" value=\"1\" class=\"tipobusqueda\" />\n" +
            "<input name=\"_ctl0:ContentPlaceHolder1:WDYL1:hSuggestType\" type=\"hidden\" id=\"hSuggestType\" value=\"2\" class=\"hSuggestType\" />\n" +
            "    \n" +
            "\n" +
            "    <div id=\"content\" style=\"padding:0\">\n" +
            "    <section>\n" +
            "            <div id=\"home-related\">\n" +
            "            </div>\n" +
            "        </section>\n" +
            "        <section>\n" +
            "            <div id=\"home-news\" class=\"clearfix\">\n" +
            "                <h2 class=\"center\">Pruebas y novedades</h2>\n" +
            "                <div class=\"editorial-1\">\n" +
            "                    <div id=\"noticias\" class=\"portada la_2365\">\n" +
            "\n" +
            "<article>\n" +
            "    <div class=\"card\">\n" +
            "        <a href=\"/prueba-jaguar-ftype\" title=\"Jaguar F-Type S AWD: Ahora con tracci&#243;n total\" data_xtclib=\"New_col_0_row_1\">\n" +
            "            <img alt=\"Jaguar F-Type S AWD: Ahora con tracci&#243;n total\" src=\"http://a.ccdn.es/cnet/contents/media/jaguar/ftype/1138375.jpg/380x218cut/0_51_1280_771/\"  />\n" +
            "        </a>\n" +
            "        <div class=\"content-card\">\n" +
            "            <a href=\"/pruebas/\" class=\"pruebas title\" data_xtclib=\"New_col_0_row_1_label\">\n" +
            "                <span class=\"label-news label1 fleft\">Pruebas</span>\n" +
            "            </a>\n" +
            "          \n" +
            "            <div id=\"divComments\" style=\"display:block\">\n" +
            "                <a href=\"/prueba-jaguar-ftype#ctrl_comments\" data_xtclib=\"New_col_0_row_1_comments\"><span class=\"icon-comentaris fright\">9</span></a>    \n" +
            "            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "            <a href=\"/prueba-jaguar-ftype\" title=\"Jaguar F-Type S AWD: Ahora con tracci&#243;n total\" data_xtclib=\"New_col_0_row_1\">\n" +
            "                <h3>Jaguar F-Type S AWD: Ahora con tracci&#243;n total</h3>\n" +
            "\n" +
            "                <p>Dispone del motor V6 de 380 CV y cuesta 98.600 €</p>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</article>\n" +
            "\n" +
            "<article>\n" +
            "    <div class=\"card\">\n" +
            "        <a href=\"/nuevo-porsche-718-boxster-presentacion\" title=\"Porsche 718 Boxster: conocemos todos sus secretos\" data_xtclib=\"New_col_0_row_2\">\n" +
            "            <img alt=\"Porsche 718 Boxster: conocemos todos sus secretos\" src=\"\" data-iurl2=http://a.ccdn.es/cnet/contents/media/porsche/boxster/1138159.jpg/380x218cut/77_224_1126_814/ />\n" +
            "        </a>\n" +
            "        <div class=\"content-card\">\n" +
            "            <a href=\"/presentacion/\" class=\"presentacion title\" data_xtclib=\"New_col_0_row_2_label\">\n" +
            "                <span class=\"label-news label1 fleft\">Presentaci&#243;n</span>\n" +
            "            </a>\n" +
            "          \n" +
            "            <div id=\"divComments\" style=\"display:block\">\n" +
            "                <a href=\"/nuevo-porsche-718-boxster-presentacion#ctrl_comments\" data_xtclib=\"New_col_0_row_2_comments\"><span class=\"icon-comentaris fright\">304</span></a>    \n" +
            "            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "            <a href=\"/nuevo-porsche-718-boxster-presentacion\" title=\"Porsche 718 Boxster: conocemos todos sus secretos\" data_xtclib=\"New_col_0_row_2\">\n" +
            "                <h3>Porsche 718 Boxster: conocemos todos sus secretos</h3>\n" +
            "\n" +
            "                <p>Presentaci&#243;n previa a su puesta de largo en Ginebra</p>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</article>\n" +
            "\n" +
            "<article>\n" +
            "    <div class=\"card\">\n" +
            "        <a href=\"/nuevo-porsche-718-boxster-presentacion\" title=\"Porsche 718 Boxster: conocemos todos sus secretos\" data_xtclib=\"New_col_0_row_3\">\n" +
            "            <img alt=\"Porsche 718 Boxster: conocemos todos sus secretos\" src=\"\" data-iurl2=http://a.ccdn.es/cnet/contents/media/porsche/boxster/1138159.jpg/380x218cut/77_224_1126_814/ />\n" +
            "        </a>\n" +
            "        <div class=\"content-card\">\n" +
            "            <a href=\"/presentacion/\" class=\"presentacion title\" data_xtclib=\"New_col_0_row_3_label\">\n" +
            "                <span class=\"label-news label1 fleft\">Presentaci&#243;n</span>\n" +
            "            </a>\n" +
            "          \n" +
            "            <div id=\"divComments\" style=\"display:block\">\n" +
            "                <a href=\"/nuevo-porsche-718-boxster-presentacion#ctrl_comments\" data_xtclib=\"New_col_0_row_3_comments\"><span class=\"icon-comentaris fright\">305</span></a>    \n" +
            "            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "            <a href=\"/nuevo-porsche-718-boxster-presentacion\" title=\"Porsche 718 Boxster: conocemos todos sus secretos\" data_xtclib=\"New_col_0_row_3\">\n" +
            "                <h3>Porsche 718 Boxster: conocemos todos sus secretos</h3>\n" +
            "\n" +
            "                <p>Presentaci&#243;n previa a su puesta de largo en Ginebra</p>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</article>\n" +
            "\n" +
            "<article>\n" +
            "    <div class=\"card\">\n" +
            "        <a href=\"/videos/bmw-x3-land_rover-discovery_sport-mercedes_benz-clase_glc-audi-q5\" title=\"Prueba comparativa: Audi Q5 - BMW X3 - Mercedes GLC - LR Discovery Sport\" data_xtclib=\"New_col_0_row_4\">\n" +
            "<span class=\"icon-play\"></span>            <img alt=\"Prueba comparativa: Audi Q5 - BMW X3 - Mercedes GLC - LR Discovery Sport\" src=\"\" data-iurl2=http://a.ccdn.es/cnet/contents/media/mercedes__benz/1137450.jpg/380x218cut/0_104_1280_824/ />\n" +
            "        </a>\n" +
            "        <div class=\"content-card\">\n" +
            "            <a href=\"/pruebas/\" class=\"pruebas title\" data_xtclib=\"New_col_0_row_4_label\">\n" +
            "                <span class=\"label-news label1 fleft\">Pruebas</span>\n" +
            "            </a>\n" +
            "          \n" +
            "            <div id=\"divComments\" style=\"display:block\">\n" +
            "                <a href=\"/videos/bmw-x3-land_rover-discovery_sport-mercedes_benz-clase_glc-audi-q5#ctrl_comments\" data_xtclib=\"New_col_0_row_4_comments\"><span class=\"icon-comentaris fright\">106</span></a>    \n" +
            "            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "            <a href=\"/videos/bmw-x3-land_rover-discovery_sport-mercedes_benz-clase_glc-audi-q5\" title=\"Prueba comparativa: Audi Q5 - BMW X3 - Mercedes GLC - LR Discovery Sport\" data_xtclib=\"New_col_0_row_4\">\n" +
            "                <h3>Prueba comparativa: Audi Q5 - BMW X3 - Mercedes GLC - LR Discovery Sport</h3>\n" +
            "\n" +
            "                <p>Cuatro SUV de lujo que saben combinar asfalto y pistas</p>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</article>\n" +
            "\n" +
            "<article>\n" +
            "    <div class=\"card\">\n" +
            "        <a href=\"/nuevo-volkswagen-tiguan-2016-presentacion-artico\" title=\"Volkswagen Tiguan. Primer contacto en el &#193;rtico\" data_xtclib=\"New_col_0_row_5\">\n" +
            "            <img alt=\"Volkswagen Tiguan. Primer contacto en el &#193;rtico\" src=\"\" data-iurl2=http://a.ccdn.es/cnet/contents/media/volkswagen/tiguan/1138019.jpg/380x218cut/ />\n" +
            "        </a>\n" +
            "        <div class=\"content-card\">\n" +
            "            <a href=\"/presentacion/\" class=\"presentacion title\" data_xtclib=\"New_col_0_row_5_label\">\n" +
            "                <span class=\"label-news label1 fleft\">Presentaci&#243;n</span>\n" +
            "            </a>\n" +
            "          \n" +
            "            <div id=\"divComments\" style=\"display:block\">\n" +
            "                <a href=\"/nuevo-volkswagen-tiguan-2016-presentacion-artico#ctrl_comments\" data_xtclib=\"New_col_0_row_5_comments\"><span class=\"icon-comentaris fright\">121</span></a>    \n" +
            "            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "            <a href=\"/nuevo-volkswagen-tiguan-2016-presentacion-artico\" title=\"Volkswagen Tiguan. Primer contacto en el &#193;rtico\" data_xtclib=\"New_col_0_row_5\">\n" +
            "                <h3>Volkswagen Tiguan. Primer contacto en el &#193;rtico</h3>\n" +
            "\n" +
            "                <p>Se lanzar&#225; con dos motores: Diesel 150 CV y gasolina 180 CV</p>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</article>\n" +
            "\n" +
            "<article>\n" +
            "    <div class=\"card\">\n" +
            "        <a href=\"/nuevo-citroen-celysee-bluehdi\" title=\"Citro&#235;n C-Elys&#233;e BlueHDi. Espacio y confort por 17.300 euros\" data_xtclib=\"New_col_0_row_6\">\n" +
            "            <img alt=\"Citro&#235;n C-Elys&#233;e BlueHDi. Espacio y confort por 17.300 euros\" src=\"\" data-iurl2=http://a.ccdn.es/cnet/contents/media/citroen/celysee/1133477.jpg/380x218cut/ />\n" +
            "        </a>\n" +
            "        <div class=\"content-card\">\n" +
            "            <a href=\"/pruebas/\" class=\"pruebas title\" data_xtclib=\"New_col_0_row_6_label\">\n" +
            "                <span class=\"label-news label1 fleft\">Pruebas</span>\n" +
            "            </a>\n" +
            "          \n" +
            "            <div id=\"divComments\" style=\"display:block\">\n" +
            "                <a href=\"/nuevo-citroen-celysee-bluehdi#ctrl_comments\" data_xtclib=\"New_col_0_row_6_comments\"><span class=\"icon-comentaris fright\">89</span></a>    \n" +
            "            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "            <a href=\"/nuevo-citroen-celysee-bluehdi\" title=\"Citro&#235;n C-Elys&#233;e BlueHDi. Espacio y confort por 17.300 euros\" data_xtclib=\"New_col_0_row_6\">\n" +
            "                <h3>Citro&#235;n C-Elys&#233;e BlueHDi. Espacio y confort por 17.300 euros</h3>\n" +
            "\n" +
            "                <p>Probamos la versi&#243;n con el nuevo motor di&#233;sel de 100 CV</p>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</article>\n" +
            "\n" +
            "<article>\n" +
            "    <div class=\"card\">\n" +
            "        <a href=\"/videos/seat-leon-cupra-290-cv-presentacion-febrero-2016\" title=\"Seat Le&#243;n Cupra 290 CV: A fondo en v&#237;deo\" data_xtclib=\"New_col_0_row_7\">\n" +
            "<span class=\"icon-play\"></span>            <img alt=\"Seat Le&#243;n Cupra 290 CV: A fondo en v&#237;deo\" src=\"\" data-iurl2=http://a.ccdn.es/cnet/contents/media/seat/leon/1137891.jpg/380x218cut/ />\n" +
            "        </a>\n" +
            "        <div class=\"content-card\">\n" +
            "            <a href=\"/presentacion/\" class=\"presentacion title\" data_xtclib=\"New_col_0_row_7_label\">\n" +
            "                <span class=\"label-news label1 fleft\">Presentaci&#243;n</span>\n" +
            "            </a>\n" +
            "          \n" +
            "            <div id=\"divComments\" style=\"display:block\">\n" +
            "                <a href=\"/videos/seat-leon-cupra-290-cv-presentacion-febrero-2016#ctrl_comments\" data_xtclib=\"New_col_0_row_7_comments\"><span class=\"icon-comentaris fright\">227</span></a>    \n" +
            "            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "            <a href=\"/videos/seat-leon-cupra-290-cv-presentacion-febrero-2016\" title=\"Seat Le&#243;n Cupra 290 CV: A fondo en v&#237;deo\" data_xtclib=\"New_col_0_row_7\">\n" +
            "                <h3>Seat Le&#243;n Cupra 290 CV: A fondo en v&#237;deo</h3>\n" +
            "\n" +
            "                <p>Est&#225; disponible desde 34.050 euros (28.362 con descuentos)</p>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</article>\n" +
            "\n" +
            "<article>\n" +
            "    <div class=\"card\">\n" +
            "        <a href=\"/nuevo-ford-kuga-2016\" title=\"Nuevo Ford Kuga, presentado en el Mobile World Congress 2016\" data_xtclib=\"New_col_0_row_8\">\n" +
            "<span class=\"icon-play\"></span>            <img alt=\"Nuevo Ford Kuga, presentado en el Mobile World Congress 2016\" src=\"\" data-iurl2=http://a.ccdn.es/cnet/contents/media/resources/2016/2/1137892.jpg/380x218cut/ />\n" +
            "        </a>\n" +
            "        <div class=\"content-card\">\n" +
            "            <a href=\"/presentacion/\" class=\"presentacion title\" data_xtclib=\"New_col_0_row_8_label\">\n" +
            "                <span class=\"label-news label1 fleft\">Presentaci&#243;n</span>\n" +
            "            </a>\n" +
            "          \n" +
            "            <div id=\"divComments\" style=\"display:block\">\n" +
            "                <a href=\"/nuevo-ford-kuga-2016#ctrl_comments\" data_xtclib=\"New_col_0_row_8_comments\"><span class=\"icon-comentaris fright\">37</span></a>    \n" +
            "            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "            <a href=\"/nuevo-ford-kuga-2016\" title=\"Nuevo Ford Kuga, presentado en el Mobile World Congress 2016\" data_xtclib=\"New_col_0_row_8\">\n" +
            "                <h3>Nuevo Ford Kuga, presentado en el Mobile World Congress 2016</h3>\n" +
            "\n" +
            "                <p>Imagen actualizada, SYNC 3 y nuevo motor di&#233;sel</p>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</article>\n" +
            "</div>\n" +
            "\n" +
            "<script>window.grid_ids =[21318,21381,21381]</script>\n" +
            "                    <div class=\"clear\"></div>\n" +
            "                        <a href=\"/pruebas/\" class=\"btn btn-border-dark block mgtop0 mgbot50 center\" data-xtclib=\"Noticias_1_todas\">Pruebas</a>\n" +
            "                </div>\n" +
            "                <div class=\"editorial-2\">\n" +
            "                    <div id=\"noticias\" class=\"portada la_2366\">\n" +
            "\n" +
            "<article>\n" +
            "    <div class=\"card\">\n" +
            "        <a href=\"/nuevo-honda-civic-hatchback-prototype\" title=\"Honda Civic Hatchback Prototype: Adelanto de la 10&#170; generaci&#243;n\" data_xtclib=\"New_col_0_row_1\">\n" +
            "            <img alt=\"Honda Civic Hatchback Prototype: Adelanto de la 10&#170; generaci&#243;n\" src=\"http://a.ccdn.es/cnet/contents/media/honda/civic/1139011.jpg/243x137cut/78_262_1128_853/\"  />\n" +
            "        </a>\n" +
            "        <div class=\"content-card\">\n" +
            "            <a href=\"/novedades/\" class=\"novedades title\" data_xtclib=\"New_col_0_row_1_label\">\n" +
            "                <span class=\"label-news label1 fleft\">Novedades</span>\n" +
            "            </a>\n" +
            "          \n" +
            "            <div id=\"divComments\" style=\"display:block\">\n" +
            "                <a href=\"/nuevo-honda-civic-hatchback-prototype#ctrl_comments\" data_xtclib=\"New_col_0_row_1_comments\"><span class=\"icon-comentaris fright\">16</span></a>    \n" +
            "            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "            <a href=\"/nuevo-honda-civic-hatchback-prototype\" title=\"Honda Civic Hatchback Prototype: Adelanto de la 10&#170; generaci&#243;n\" data_xtclib=\"New_col_0_row_1\">\n" +
            "                <h3>Honda Civic Hatchback Prototype: Adelanto de la 10&#170; generaci&#243;n</h3>\n" +
            "\n" +
            "                <p>Podr&#237;a revelarse su aspecto final en el Sal&#243;n de Par&#237;s</p>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</article>\n" +
            "\n" +
            "<article>\n" +
            "    <div class=\"card\">\n" +
            "        <a href=\"/nuevo-fiat-tipo-5-puertas-station-wagon\" title=\"Fiat Tipo 5 puertas y Station Wagon: Debut en Ginebra\" data_xtclib=\"New_col_0_row_2\">\n" +
            "            <img alt=\"Fiat Tipo 5 puertas y Station Wagon: Debut en Ginebra\" src=\"\" data-iurl2=http://a.ccdn.es/cnet/contents/media/fiat/tipo/1138969.jpg/243x137cut/18_105_1253_800/ />\n" +
            "        </a>\n" +
            "        <div class=\"content-card\">\n" +
            "            <a href=\"/novedades/\" class=\"novedades title\" data_xtclib=\"New_col_0_row_2_label\">\n" +
            "                <span class=\"label-news label1 fleft\">Novedades</span>\n" +
            "            </a>\n" +
            "          \n" +
            "            <div id=\"divComments\" style=\"display:block\">\n" +
            "                <a href=\"/nuevo-fiat-tipo-5-puertas-station-wagon#ctrl_comments\" data_xtclib=\"New_col_0_row_2_comments\"><span class=\"icon-comentaris fright\">26</span></a>    \n" +
            "            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "            <a href=\"/nuevo-fiat-tipo-5-puertas-station-wagon\" title=\"Fiat Tipo 5 puertas y Station Wagon: Debut en Ginebra\" data_xtclib=\"New_col_0_row_2\">\n" +
            "                <h3>Fiat Tipo 5 puertas y Station Wagon: Debut en Ginebra</h3>\n" +
            "\n" +
            "                <p>Ya conocemos las tres variantes del Fiat Tipo</p>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</article>\n" +
            "\n" +
            "<article>\n" +
            "    <div class=\"card\">\n" +
            "        <a href=\"/nuevo-ford-ka\" title=\"Ford Ka+ con cinco puertas\" data_xtclib=\"New_col_0_row_3\">\n" +
            "            <img alt=\"Ford Ka+ con cinco puertas\" src=\"\" data-iurl2=http://a.ccdn.es/cnet/contents/media/ford/ka/1138669.jpg/243x137cut/0_229_1280_949/ />\n" +
            "        </a>\n" +
            "        <div class=\"content-card\">\n" +
            "            <a href=\"/novedades/\" class=\"novedades title\" data_xtclib=\"New_col_0_row_3_label\">\n" +
            "                <span class=\"label-news label1 fleft\">Novedades</span>\n" +
            "            </a>\n" +
            "          \n" +
            "            <div id=\"divComments\" style=\"display:block\">\n" +
            "                <a href=\"/nuevo-ford-ka#ctrl_comments\" data_xtclib=\"New_col_0_row_3_comments\"><span class=\"icon-comentaris fright\">13</span></a>    \n" +
            "            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "            <a href=\"/nuevo-ford-ka\" title=\"Ford Ka+ con cinco puertas\" data_xtclib=\"New_col_0_row_3\">\n" +
            "                <h3>Ford Ka+ con cinco puertas</h3>\n" +
            "\n" +
            "                <p>Est&#225; basado en un modelo que ya se vende en Brasil</p>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</article>\n" +
            "\n" +
            "<article>\n" +
            "    <div class=\"card\">\n" +
            "        <a href=\"/nuevo-abarth-124-spider\" title=\"Abarth 124 Spider: M&#225;s picante para el roadster\" data_xtclib=\"New_col_0_row_4\">\n" +
            "            <img alt=\"Abarth 124 Spider: M&#225;s picante para el roadster\" src=\"\" data-iurl2=http://a.ccdn.es/cnet/contents/media/abarth/1138663.jpg/243x137cut/70_203_1120_794/ />\n" +
            "        </a>\n" +
            "        <div class=\"content-card\">\n" +
            "            <a href=\"/novedades/\" class=\"novedades title\" data_xtclib=\"New_col_0_row_4_label\">\n" +
            "                <span class=\"label-news label1 fleft\">Novedades</span>\n" +
            "            </a>\n" +
            "          \n" +
            "            <div id=\"divComments\" style=\"display:block\">\n" +
            "                <a href=\"/nuevo-abarth-124-spider#ctrl_comments\" data_xtclib=\"New_col_0_row_4_comments\"><span class=\"icon-comentaris fright\">27</span></a>    \n" +
            "            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "            <a href=\"/nuevo-abarth-124-spider\" title=\"Abarth 124 Spider: M&#225;s picante para el roadster\" data_xtclib=\"New_col_0_row_4\">\n" +
            "                <h3>Abarth 124 Spider: M&#225;s picante para el roadster</h3>\n" +
            "\n" +
            "                <p>Se aumenta su potencia hasta los 170 CV y 250 Nm de par</p>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</article>\n" +
            "\n" +
            "<article>\n" +
            "    <div class=\"card\">\n" +
            "        <a href=\"/nuevo-toyota-c-hr\" title=\"Toyota C-HR: As&#237; ser&#225; el SUV compacto\" data_xtclib=\"New_col_0_row_5\">\n" +
            "            <img alt=\"Toyota C-HR: As&#237; ser&#225; el SUV compacto\" src=\"\" data-iurl2=http://a.ccdn.es/cnet/contents/media/toyota/1138654.jpg/243x137cut/82_62_1131_653/ />\n" +
            "        </a>\n" +
            "        <div class=\"content-card\">\n" +
            "            <a href=\"/novedades/\" class=\"novedades title\" data_xtclib=\"New_col_0_row_5_label\">\n" +
            "                <span class=\"label-news label1 fleft\">Novedades</span>\n" +
            "            </a>\n" +
            "          \n" +
            "            <div id=\"divComments\" style=\"display:block\">\n" +
            "                <a href=\"/nuevo-toyota-c-hr#ctrl_comments\" data_xtclib=\"New_col_0_row_5_comments\"><span class=\"icon-comentaris fright\">31</span></a>    \n" +
            "            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "            <a href=\"/nuevo-toyota-c-hr\" title=\"Toyota C-HR: As&#237; ser&#225; el SUV compacto\" data_xtclib=\"New_col_0_row_5\">\n" +
            "                <h3>Toyota C-HR: As&#237; ser&#225; el SUV compacto</h3>\n" +
            "\n" +
            "                <p>Rival directo del Nissan Qashqai</p>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</article>\n" +
            "\n" +
            "<article>\n" +
            "    <div class=\"card\">\n" +
            "        <a href=\"/nuevo-bugatti-chiron\" title=\"Bugatti Chiron: 1.500 CV y 1.600 Nm\" data_xtclib=\"New_col_0_row_6\">\n" +
            "            <img alt=\"Bugatti Chiron: 1.500 CV y 1.600 Nm\" src=\"\" data-iurl2=http://a.ccdn.es/cnet/contents/media/bugatti/1138628.jpg/243x137cut/230_122_1280_712/ />\n" +
            "        </a>\n" +
            "        <div class=\"content-card\">\n" +
            "            <a href=\"/novedades/\" class=\"novedades title\" data_xtclib=\"New_col_0_row_6_label\">\n" +
            "                <span class=\"label-news label1 fleft\">Novedades</span>\n" +
            "            </a>\n" +
            "          \n" +
            "            <div id=\"divComments\" style=\"display:block\">\n" +
            "                <a href=\"/nuevo-bugatti-chiron#ctrl_comments\" data_xtclib=\"New_col_0_row_6_comments\"><span class=\"icon-comentaris fright\">52</span></a>    \n" +
            "            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "            <a href=\"/nuevo-bugatti-chiron\" title=\"Bugatti Chiron: 1.500 CV y 1.600 Nm\" data_xtclib=\"New_col_0_row_6\">\n" +
            "                <h3>Bugatti Chiron: 1.500 CV y 1.600 Nm</h3>\n" +
            "\n" +
            "                <p>Acelera de 0 a 100 en 2,5 segundos y alcanza los 420 km/h</p>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</article>\n" +
            "\n" +
            "<article>\n" +
            "    <div class=\"card\">\n" +
            "        <a href=\"/nuevo-volkswagen-t-cross-breeze-concept\" title=\"Volkswagen T-Cross Breeze: El futuro &quot;Polo SUV&quot;\" data_xtclib=\"New_col_0_row_7\">\n" +
            "            <img alt=\"Volkswagen T-Cross Breeze: El futuro &quot;Polo SUV&quot;\" src=\"\" data-iurl2=http://a.ccdn.es/cnet/contents/media/volkswagen/1138619.jpg/243x137cut/230_157_1280_747/ />\n" +
            "        </a>\n" +
            "        <div class=\"content-card\">\n" +
            "            <a href=\"/concept_car/\" class=\"concept_car title\" data_xtclib=\"New_col_0_row_7_label\">\n" +
            "                <span class=\"label-news label1 fleft\">Concept Car</span>\n" +
            "            </a>\n" +
            "          \n" +
            "            <div id=\"divComments\" style=\"display:block\">\n" +
            "                <a href=\"/nuevo-volkswagen-t-cross-breeze-concept#ctrl_comments\" data_xtclib=\"New_col_0_row_7_comments\"><span class=\"icon-comentaris fright\">8</span></a>    \n" +
            "            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "            <a href=\"/nuevo-volkswagen-t-cross-breeze-concept\" title=\"Volkswagen T-Cross Breeze: El futuro &quot;Polo SUV&quot;\" data_xtclib=\"New_col_0_row_7\">\n" +
            "                <h3>Volkswagen T-Cross Breeze: El futuro &quot;Polo SUV&quot;</h3>\n" +
            "\n" +
            "                <p>Adelanto del todocamino urbano que llegar&#225; en unos a&#241;os</p>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</article>\n" +
            "\n" +
            "<article>\n" +
            "    <div class=\"card\">\n" +
            "        <a href=\"/nuevo-audi-q2\" title=\"Audi Q2: Llega el SUV urbano\" data_xtclib=\"New_col_0_row_8\">\n" +
            "            <img alt=\"Audi Q2: Llega el SUV urbano\" src=\"\" data-iurl2=http://a.ccdn.es/cnet/contents/media/audi/1138510.jpg/243x137cut/19_298_1069_888/ />\n" +
            "        </a>\n" +
            "        <div class=\"content-card\">\n" +
            "            <a href=\"/novedades/\" class=\"novedades title\" data_xtclib=\"New_col_0_row_8_label\">\n" +
            "                <span class=\"label-news label1 fleft\">Novedades</span>\n" +
            "            </a>\n" +
            "          \n" +
            "            <div id=\"divComments\" style=\"display:block\">\n" +
            "                <a href=\"/nuevo-audi-q2#ctrl_comments\" data_xtclib=\"New_col_0_row_8_comments\"><span class=\"icon-comentaris fright\">46</span></a>    \n" +
            "            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "            <a href=\"/nuevo-audi-q2\" title=\"Audi Q2: Llega el SUV urbano\" data_xtclib=\"New_col_0_row_8\">\n" +
            "                <h3>Audi Q2: Llega el SUV urbano</h3>\n" +
            "\n" +
            "                <p>A la venta en oto&#241;o</p>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</article>\n" +
            "\n" +
            "<article>\n" +
            "    <div class=\"card\">\n" +
            "        <a href=\"/nuevo-porsche-911-r\" title=\"Porsche 911 R: Atmosf&#233;rico y manual\" data_xtclib=\"New_col_0_row_9\">\n" +
            "            <img alt=\"Porsche 911 R: Atmosf&#233;rico y manual\" src=\"\" data-iurl2=http://a.ccdn.es/cnet/contents/media/porsche/911/1138592.jpg/243x137cut/101_115_1150_706/ />\n" +
            "        </a>\n" +
            "        <div class=\"content-card\">\n" +
            "            <a href=\"/novedades/\" class=\"novedades title\" data_xtclib=\"New_col_0_row_9_label\">\n" +
            "                <span class=\"label-news label1 fleft\">Novedades</span>\n" +
            "            </a>\n" +
            "          \n" +
            "            <div id=\"divComments\" style=\"display:block\">\n" +
            "                <a href=\"/nuevo-porsche-911-r#ctrl_comments\" data_xtclib=\"New_col_0_row_9_comments\"><span class=\"icon-comentaris fright\">21</span></a>    \n" +
            "            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "            <a href=\"/nuevo-porsche-911-r\" title=\"Porsche 911 R: Atmosf&#233;rico y manual\" data_xtclib=\"New_col_0_row_9\">\n" +
            "                <h3>Porsche 911 R: Atmosf&#233;rico y manual</h3>\n" +
            "\n" +
            "                <p>Edici&#243;n limitada a 911 unidades</p>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</article>\n" +
            "\n" +
            "<article>\n" +
            "    <div class=\"card\">\n" +
            "        <a href=\"/nuevo-mercedes-benz-clase-c-cabrio\" title=\"Mercedes-Benz Clase C Cabrio: El primer &quot;C&quot; descapotable\" data_xtclib=\"New_col_0_row_10\">\n" +
            "            <img alt=\"Mercedes-Benz Clase C Cabrio: El primer &quot;C&quot; descapotable\" src=\"\" data-iurl2=http://a.ccdn.es/cnet/contents/media/mercedes__benz/clase_c/1138506.jpg/243x137cut/192_259_1242_850/ />\n" +
            "        </a>\n" +
            "        <div class=\"content-card\">\n" +
            "            <a href=\"/novedades/\" class=\"novedades title\" data_xtclib=\"New_col_0_row_10_label\">\n" +
            "                <span class=\"label-news label1 fleft\">Novedades</span>\n" +
            "            </a>\n" +
            "          \n" +
            "            <div id=\"divComments\" style=\"display:block\">\n" +
            "                <a href=\"/nuevo-mercedes-benz-clase-c-cabrio#ctrl_comments\" data_xtclib=\"New_col_0_row_10_comments\"><span class=\"icon-comentaris fright\">6</span></a>    \n" +
            "            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "            <a href=\"/nuevo-mercedes-benz-clase-c-cabrio\" title=\"Mercedes-Benz Clase C Cabrio: El primer &quot;C&quot; descapotable\" data_xtclib=\"New_col_0_row_10\">\n" +
            "                <h3>Mercedes-Benz Clase C Cabrio: El primer &quot;C&quot; descapotable</h3>\n" +
            "\n" +
            "                <p>Se acaba de presentar en el Sal&#243;n de Ginebra</p>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</article>\n" +
            "</div>\n" +
            "\n" +
            "<script>window.grid_ids =[21400,21399,21396]</script>\n" +
            "                    <div class=\"clear\"></div>\n" +
            "                        <a href=\"/novedades/\" class=\"btn btn-border-dark block mgtop0 mgbot50 center\" data-xtclib=\"Noticias_2_todas\">Novedades</a>\n" +
            "                </div>\n" +
            "                <div class=\"editorial-3\">\n" +
            "                    <div id=\"noticias\" class=\"portada la_2367\">\n" +
            "\n" +
            "<article>\n" +
            "    <div class=\"card\">\n" +
            "        <a href=\"http://www.coches.net/salon_ginebra_2016/\" title=\"Sal&#243;n de Ginebra: Todas las novedades\" data_xtclib=\"New_col_0_row_1\">\n" +
            "            <img alt=\"Sal&#243;n de Ginebra: Todas las novedades\" src=\"http://a.ccdn.es/cnet/contents/media/resources/2016/3/1138985.jpg/301x169cut/0_131_1280_851/\"  />\n" +
            "        </a>\n" +
            "        <div class=\"content-card\">\n" +
            "            <a href=\"//\" class=\" title\" data_xtclib=\"New_col_0_row_1_label\">\n" +
            "                <span class=\"label-news label1 fleft\"></span>\n" +
            "            </a>\n" +
            "          \n" +
            "            <div id=\"divComments\" style=\"display:block\">\n" +
            "                <a href=\"http://www.coches.net/salon_ginebra_2016/#ctrl_comments\" data_xtclib=\"New_col_0_row_1_comments\"><span class=\"icon-comentaris fright\">0</span></a>    \n" +
            "            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "            <a href=\"http://www.coches.net/salon_ginebra_2016/\" title=\"Sal&#243;n de Ginebra: Todas las novedades\" data_xtclib=\"New_col_0_row_1\">\n" +
            "                <h3>Sal&#243;n de Ginebra: Todas las novedades</h3>\n" +
            "\n" +
            "                <p>Arranca el sal&#243;n suizo, cargado de primicias</p>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</article>\n" +
            "\n" +
            "<article>\n" +
            "    <div class=\"card\">\n" +
            "        <a href=\"http://www.coches.net/noticias/salon_ginebra_2016_fotos\" title=\"Sal&#243;n de Ginebra 2016: Todas las fotos\" data_xtclib=\"New_col_0_row_2\">\n" +
            "            <img alt=\"Sal&#243;n de Ginebra 2016: Todas las fotos\" src=\"\" data-iurl2=http://a.ccdn.es/cnet/contents/media/infiniti/q60/1138781.jpg/301x169cut/0_118_1280_838/ />\n" +
            "        </a>\n" +
            "        <div class=\"content-card\">\n" +
            "            <a href=\"/actualidad/\" class=\"actualidad title\" data_xtclib=\"New_col_0_row_2_label\">\n" +
            "                <span class=\"label-news label1 fleft\">Actualidad</span>\n" +
            "            </a>\n" +
            "          \n" +
            "            <div id=\"divComments\" style=\"display:block\">\n" +
            "                <a href=\"http://www.coches.net/noticias/salon_ginebra_2016_fotos#ctrl_comments\" data_xtclib=\"New_col_0_row_2_comments\"><span class=\"icon-comentaris fright\">0</span></a>    \n" +
            "            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "            <a href=\"http://www.coches.net/noticias/salon_ginebra_2016_fotos\" title=\"Sal&#243;n de Ginebra 2016: Todas las fotos\" data_xtclib=\"New_col_0_row_2\">\n" +
            "                <h3>Sal&#243;n de Ginebra 2016: Todas las fotos</h3>\n" +
            "\n" +
            "                <p>Echa un vistazo a todas las novedades presentadas</p>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</article>\n" +
            "\n" +
            "<article>\n" +
            "    <div class=\"card\">\n" +
            "        <a href=\"/noticias/schibsted-motor-awards-2016-ganadores-premios-enero\" title=\"Schibsted Motor Awards 2016\" data_xtclib=\"New_col_0_row_3\">\n" +
            "            <img alt=\"Schibsted Motor Awards 2016\" src=\"\" data-iurl2=http://a.ccdn.es/cnet/contents/media/resources/2016/2/1136560.jpg/301x169cut/39_137_1162_769/ />\n" +
            "        </a>\n" +
            "        <div class=\"content-card\">\n" +
            "            <a href=\"/actualidad/\" class=\"actualidad title\" data_xtclib=\"New_col_0_row_3_label\">\n" +
            "                <span class=\"label-news label1 fleft\">Actualidad</span>\n" +
            "            </a>\n" +
            "          \n" +
            "            <div id=\"divComments\" style=\"display:none\">\n" +
            "                <a href=\"/noticias/schibsted-motor-awards-2016-ganadores-premios-enero#ctrl_comments\" data_xtclib=\"New_col_0_row_3_comments\"><span class=\"icon-comentaris fright\">6</span></a>    \n" +
            "            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "            <a href=\"/noticias/schibsted-motor-awards-2016-ganadores-premios-enero\" title=\"Schibsted Motor Awards 2016\" data_xtclib=\"New_col_0_row_3\">\n" +
            "                <h3>Schibsted Motor Awards 2016</h3>\n" +
            "\n" +
            "                <p>&#161;Ya tenemos los ganadores de enero!</p>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</article>\n" +
            "\n" +
            "<article>\n" +
            "    <div class=\"card publi-card\">\n" +
            "        <a href=\"/noticias/ahorra-en-tu-itv-comprandola-online-tumejoritv-publirreportaje\" title=\"Ahorra en tu ITV compr&#225;ndola a trav&#233;s de Internet\" data_xtclib=\"New_col_0_row_4\">\n" +
            "            <img alt=\"Ahorra en tu ITV compr&#225;ndola a trav&#233;s de Internet\" src=\"\" data-iurl2=http://a.ccdn.es/cnet/contents/media/resources/2016/2/1137097.jpg/301x169cut/ />\n" +
            "        </a>\n" +
            "        <div class=\"content-card\">\n" +
            "            <a href=\"/publirreportaje/\" class=\"publirreportaje title\" data_xtclib=\"New_col_0_row_4_label\">\n" +
            "                <span class=\"label-news label1 fleft\">publirreportaje</span>\n" +
            "            </a>\n" +
            "          \n" +
            "            <div id=\"divComments\" style=\"display:none\">\n" +
            "                <a href=\"/noticias/ahorra-en-tu-itv-comprandola-online-tumejoritv-publirreportaje#ctrl_comments\" data_xtclib=\"New_col_0_row_4_comments\"><span class=\"icon-comentaris fright\">0</span></a>    \n" +
            "            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "            <a href=\"/noticias/ahorra-en-tu-itv-comprandola-online-tumejoritv-publirreportaje\" title=\"Ahorra en tu ITV compr&#225;ndola a trav&#233;s de Internet\" data_xtclib=\"New_col_0_row_4\">\n" +
            "                <h3>Ahorra en tu ITV compr&#225;ndola a trav&#233;s de Internet</h3>\n" +
            "\n" +
            "                <p></p>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</article>\n" +
            "\n" +
            "<article>\n" +
            "    <div class=\"card\">\n" +
            "        <a href=\"/videos/cronica-del-salon-de-ginebra-2016\" title=\"Cr&#243;nica del Sal&#243;n de Ginebra 2016\" data_xtclib=\"New_col_0_row_5\">\n" +
            "<span class=\"icon-play\"></span>            <img alt=\"Cr&#243;nica del Sal&#243;n de Ginebra 2016\" src=\"\" data-iurl2=http://a.ccdn.es/cnet/contents/media/resources/2016/3/1138590.jpg/301x169cut/0_0_1280_720/ />\n" +
            "        </a>\n" +
            "        <div class=\"content-card\">\n" +
            "            <a href=\"/videos/\" class=\"videos title\" data_xtclib=\"New_col_0_row_5_label\">\n" +
            "                <span class=\"label-news label1 fleft\">V&#237;deos</span>\n" +
            "            </a>\n" +
            "          \n" +
            "            <div id=\"divComments\" style=\"display:block\">\n" +
            "                <a href=\"/videos/cronica-del-salon-de-ginebra-2016#ctrl_comments\" data_xtclib=\"New_col_0_row_5_comments\"><span class=\"icon-comentaris fright\">8</span></a>    \n" +
            "            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "            <a href=\"/videos/cronica-del-salon-de-ginebra-2016\" title=\"Cr&#243;nica del Sal&#243;n de Ginebra 2016\" data_xtclib=\"New_col_0_row_5\">\n" +
            "                <h3>Cr&#243;nica del Sal&#243;n de Ginebra 2016</h3>\n" +
            "\n" +
            "                <p>Repaso a las principales novedades presentadas</p>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</article>\n" +
            "\n" +
            "<article>\n" +
            "    <div class=\"card\">\n" +
            "        <a href=\"/noticias/ventas-febrero\" title=\"Febrero cierra con 97.650 turismos vendidos\" data_xtclib=\"New_col_0_row_6\">\n" +
            "            <img alt=\"Febrero cierra con 97.650 turismos vendidos\" src=\"\" data-iurl2=http://a.ccdn.es/cnet/contents/media/seat/leon/1131990.jpg/301x169cut/408_505_2776_1837/ />\n" +
            "        </a>\n" +
            "        <div class=\"content-card\">\n" +
            "            <a href=\"/actualidad/\" class=\"actualidad title\" data_xtclib=\"New_col_0_row_6_label\">\n" +
            "                <span class=\"label-news label1 fleft\">Actualidad</span>\n" +
            "            </a>\n" +
            "          \n" +
            "            <div id=\"divComments\" style=\"display:block\">\n" +
            "                <a href=\"/noticias/ventas-febrero#ctrl_comments\" data_xtclib=\"New_col_0_row_6_comments\"><span class=\"icon-comentaris fright\">0</span></a>    \n" +
            "            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "            <a href=\"/noticias/ventas-febrero\" title=\"Febrero cierra con 97.650 turismos vendidos\" data_xtclib=\"New_col_0_row_6\">\n" +
            "                <h3>Febrero cierra con 97.650 turismos vendidos</h3>\n" +
            "\n" +
            "                <p>Opel y el Seat Le&#243;n, l&#237;deres en ventas</p>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</article>\n" +
            "\n" +
            "<article>\n" +
            "    <div class=\"card\">\n" +
            "        <a href=\"/noticias/iluminacion-reportaje\" title=\"Ver y ser vistos\" data_xtclib=\"New_col_0_row_7\">\n" +
            "            <img alt=\"Ver y ser vistos\" src=\"\" data-iurl2=http://a.ccdn.es/cnet/contents/media/resources/2016/1/1133958.jpg/301x169cut/ />\n" +
            "        </a>\n" +
            "        <div class=\"content-card\">\n" +
            "            <a href=\"/actualidad/\" class=\"actualidad title\" data_xtclib=\"New_col_0_row_7_label\">\n" +
            "                <span class=\"label-news label1 fleft\">Actualidad</span>\n" +
            "            </a>\n" +
            "          \n" +
            "            <div id=\"divComments\" style=\"display:block\">\n" +
            "                <a href=\"/noticias/iluminacion-reportaje#ctrl_comments\" data_xtclib=\"New_col_0_row_7_comments\"><span class=\"icon-comentaris fright\">19</span></a>    \n" +
            "            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "            <a href=\"/noticias/iluminacion-reportaje\" title=\"Ver y ser vistos\" data_xtclib=\"New_col_0_row_7\">\n" +
            "                <h3>Ver y ser vistos</h3>\n" +
            "\n" +
            "                <p>La revoluci&#243;n de los sistemas de iluminaci&#243;n</p>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</article>\n" +
            "\n" +
            "<article>\n" +
            "    <div class=\"card publi-card\">\n" +
            "        <a href=\"/noticias/autogas-todas-las-ventajas\" title=\"Autogas: Todas las ventajas\" data_xtclib=\"New_col_0_row_8\">\n" +
            "            <img alt=\"Autogas: Todas las ventajas\" src=\"\" data-iurl2=http://a.ccdn.es/cnet/contents/media/resources/2016/2/1136589.jpg/301x169cut/9_10_996_565/ />\n" +
            "        </a>\n" +
            "        <div class=\"content-card\">\n" +
            "            <a href=\"/publirreportaje/\" class=\"publirreportaje title\" data_xtclib=\"New_col_0_row_8_label\">\n" +
            "                <span class=\"label-news label1 fleft\">publirreportaje</span>\n" +
            "            </a>\n" +
            "          \n" +
            "            <div id=\"divComments\" style=\"display:none\">\n" +
            "                <a href=\"/noticias/autogas-todas-las-ventajas#ctrl_comments\" data_xtclib=\"New_col_0_row_8_comments\"><span class=\"icon-comentaris fright\">2</span></a>    \n" +
            "            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "            <a href=\"/noticias/autogas-todas-las-ventajas\" title=\"Autogas: Todas las ventajas\" data_xtclib=\"New_col_0_row_8\">\n" +
            "                <h3>Autogas: Todas las ventajas</h3>\n" +
            "\n" +
            "                <p></p>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</article>\n" +
            "\n" +
            "<article>\n" +
            "    <div class=\"card\">\n" +
            "        <a href=\"http://espacio-toyota.coches.net/\" title=\"Toda la informaci&#243;n de la marca\" data_xtclib=\"New_col_0_row_9\">\n" +
            "            <img alt=\"Toda la informaci&#243;n de la marca\" src=\"\" data-iurl2=http://a.ccdn.es/cnet/contents/media/resources/2015/6/1111783.jpg/301x169cut/0_3_1063_601/ />\n" +
            "        </a>\n" +
            "        <div class=\"content-card\">\n" +
            "            <a href=\"/toyota/\" class=\"toyota title\" data_xtclib=\"New_col_0_row_9_label\">\n" +
            "                <span class=\"label-news label1 fleft\">TOYOTA</span>\n" +
            "            </a>\n" +
            "          \n" +
            "            <div id=\"divComments\" style=\"display:block\">\n" +
            "                <a href=\"http://espacio-toyota.coches.net/#ctrl_comments\" data_xtclib=\"New_col_0_row_9_comments\"><span class=\"icon-comentaris fright\">0</span></a>    \n" +
            "            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "            <a href=\"http://espacio-toyota.coches.net/\" title=\"Toda la informaci&#243;n de la marca\" data_xtclib=\"New_col_0_row_9\">\n" +
            "                <h3>Toda la informaci&#243;n de la marca</h3>\n" +
            "\n" +
            "                <p>El espacio exclusivo de Toyota en Coches.net</p>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</article>\n" +
            "</div>\n" +
            "\n" +
            "<script>window.grid_ids =[21398,21391,21389]</script>\n" +
            "                    <div class=\"clear\"></div>\n" +
            "                        <a href=\"/videos/\" class=\"btn btn-border-dark block mgtop0 mgbot50 center\" data-xtclib=\"Noticias_3_todas\">Vídeos</a>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </section>\n" +
            "        <div class=\"clear\"></div>\n" +
            "\n" +
            "        <section>\n" +
            "            <div id=\"tramites\">\n" +
            "                \n" +
            "<h1>Trámites y Servicios</h1>\n" +
            "<ul>\n" +
            "    <li>\n" +
            "        <div id=\"iComprar\" class=\"ilustracion\" data-iurl=\"/images/ilus-comprar.jpg\"></div>\n" +
            "        <h2>Datos y opiniones</h2>\n" +
            "        <ul>\n" +
            "            <li class=\"enlace\"><a href=\"/fichas_tecnicas/\" data-xtclib=\"Servicios_FichasTecnicas\">Fichas técnicas</a></li>\n" +
            "            <li class=\"enlace\"><a href=\"/fichas_tecnicas/comparar.aspx\" data-xtclib=\"Servicios_Comparador\">Comparador de coches</a></li>\n" +
            "            <li class=\"enlace\"><a href=\"//debates.coches.net\" title=\"Foros de coches\" data-xtclib=\"Servicios_Foros\">Foros</a></li>\n" +
            "        </ul>\n" +
            "    </li>\n" +
            "    <li>\n" +
            "        <div id=\"iVender\"class=\"ilustracion\" data-iurl=\"/images/ilus-vender.jpg\"></div>\n" +
            "        <h2>Compra y venta</h2>\n" +
            "        <ul>\n" +
            "            <li class=\"enlace\"><a href=\"/tasacion-de-coches.aspx\" data-xtclib=\"Servicios_Tasacion\">Tasación</a></li>\n" +
            "            <li class=\"enlace\"><a href=\"/informe-vehiculos/\" data-xtclib=\"Servicios_InformeVehiculos\">Informe de vehículos</a></li>\n" +
            "            <li class=\"enlace\"><a href=\"/tramites-compra-venta-coches.aspx\" data-xtclib=\"Servicios_CompraVenta\">Trámites de compra-venta</a></li>\n" +
            "            <li class=\"enlace\"><a href=\"/Vender-mi-coche.aspx\" title=\"Vender coche gratis\" data-xtclib=\"Servicios_VenderCoche\">Vender coche</a></li>\n" +
            "        </ul>\n" +
            "    </li>\n" +
            "    <li>\n" +
            "        <div id=\"iNoticias\" class=\"ilustracion\" data-iurl=\"/images/ilus-noticias.jpg\"></div>\n" +
            "        <h2>Seguros y financiación</h2>\n" +
            "        <ul>\n" +
            "            <li class=\"enlace\"><a href=\"//seguros.coches.net\" data-xtclib=\"Servicios_Seguros\">Seguros</a></li>\n" +
            "            <li class=\"enlace\"><a href=\"//financiacion.coches.net\" data-xtclib=\"Servicios_Financiacion\">Financiación</a></li>\n" +
            "        </ul>\n" +
            "    </li>\n" +
            "    <li class=\"last\">\n" +
            "        <div id=\"iServicios\" class=\"ilustracion\" data-iurl=\"/images/ilus-servicios.jpg\"></div>\n" +
            "        <h2>Mantenimiento</h2>\n" +
            "        <ul>\n" +
            "            <li class=\"enlace\"><a href=\"/talleres/\" title=\"Talleres de coches\" data-xtclib=\"Servicios_Talleres\">Talleres</a></li>\n" +
            "            <li class=\"enlace\"><a href=\"/accesorios-de-coches.aspx\" data-xtclib=\"Servicios_Accesorios\">Accesorios</a></li>\n" +
            "            <li class=\"enlace\"><a href=\"/servicios/costes-mantenimiento/?coche=talleres\" data-xtclib=\"Servicios_CostesMantenimiento\">Costes de mantenimiento</a></li>\n" +
            "        </ul>\n" +
            "    </li>\n" +
            "</ul>\n" +
            "            </div>\n" +
            "        </section>\n" +
            "\n" +
            "</body>\n" +
            "</html>\n";


    String wikipedia = "" +
            "\n" +
            "<!DOCTYPE html>\n" +
            "<html lang=\"mul\" dir=\"ltr\">\n" +
            "<head>\n" +
            "<meta charset=\"utf-8\">\n" +
            "<title>Wikipedia</title>\n" +
            "<!--[if lt IE 7]><meta http-equiv=\"imagetoolbar\" content=\"no\"><![endif]-->\n" +
            "<meta name=\"viewport\" content=\"initial-scale=1,user-scalable=yes\">\n" +
            "<link rel=\"apple-touch-icon\" href=\"/static/apple-touch/wikipedia.png\">\n" +
            "<link rel=\"shortcut icon\" href=\"/static/favicon/wikipedia.ico\">\n" +
            "<link rel=\"license\" href=\"//creativecommons.org/licenses/by-sa/3.0/\">\n" +
            "<style>legend,td,th{padding:0}hr,img{border:none}html{font-family:sans-serif;-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%}article,aside,details,figcaption,figure,footer,header,hgroup,main,menu,nav,section,summary{display:block}audio,canvas,progress,video{display:inline-block;vertical-align:baseline}audio:not([controls]){display:none;height:0}[hidden],template{display:none}a{background-color:transparent}a:active,a:hover{outline:0}abbr[title]{border-bottom:1px dotted}b,optgroup,strong{font-weight:700}dfn{font-style:italic}h1{font-size:2em;margin:.67em 0}mark{background:#ff0;color:#000}small{font-size:85%}sub,sup{font-size:75%;line-height:0;position:relative;vertical-align:baseline}.central-featured,.search-container,img{vertical-align:middle}sup{top:-.5em}sub{bottom:-.25em}svg:not(:root){overflow:hidden}figure{margin:1em 40px}pre,textarea{overflow:auto}code,kbd,pre,samp{font-family:monospace,monospace;font-size:1em}button,input,optgroup,select,textarea{color:inherit;font:inherit;margin:0}button{overflow:visible}button,select{text-transform:none}button,html input[type=button],input[type=reset],input[type=submit]{-webkit-appearance:button;cursor:pointer}button[disabled],html input[disabled]{cursor:default}button::-moz-focus-inner,input::-moz-focus-inner{border:0;padding:0}input{line-height:normal}input[type=checkbox],input[type=radio]{box-sizing:border-box;padding:0}input[type=number]::-webkit-inner-spin-button,input[type=number]::-webkit-outer-spin-button{height:auto}input[type=search]{-webkit-appearance:textfield;box-sizing:content-box}input[type=search]::-webkit-search-cancel-button,input[type=search]::-webkit-search-decoration{-webkit-appearance:none}input[type=search]:focus{outline-offset:-2px}fieldset{border:1px solid silver;margin:0 2px;padding:.35em .625em .75em}legend{border:0}table{border-collapse:collapse;border-spacing:0}.hidden,[hidden]{display:none!important}.pure-img{max-width:100%;height:auto;display:block}body{background-color:#fff;font:13px/1.5 sans-serif;margin:.3em 0}a,a:active,a:focus{unicode-bidi:embed;white-space:nowrap;outline:0;color:#165c91;text-decoration:none}a:focus{outline:#165c91 solid 1px}a:hover{text-decoration:underline}hr{box-sizing:content-box;clear:both;height:0;border-bottom:1px solid #aaa;margin:.2em 1em}.central-textlogo{margin:1em 0 .3em;text-align:center;font-size:30px;line-height:110%;font-family:'Linux Libertine','Hoefler Text',Georgia,'Times New Roman',Times,serif;font-weight:400;padding:10px 0;-webkit-font-feature-settings:\"ss05\";-ms-font-feature-settings:\"ss05\";font-feature-settings:\"ss05\"}.central-featured{position:relative;text-align:center;max-width:100%;width:42em;margin:0 auto;height:26em}.central-featured-logo-wrapper{vertical-align:middle;line-height:26em}.central-featured-lang{position:absolute;width:12em}.central-featured-lang .link-box{display:block;padding:0;text-decoration:none}.central-featured-lang .link-box:hover strong{text-decoration:underline}.central-featured-lang .link-box em,.central-featured-lang .link-box small{color:#000;text-decoration:none}.central-featured-lang strong{font-size:larger}.central-featured-lang em{font-style:italic}.central-featured-lang .emNonItalicLang{font-style:normal}.lang1{top:0;right:60%}.lang2{top:0;left:60%}.lang3{top:20%;right:70%}.lang4{top:20%;left:70%}.lang5{top:40%;right:72%}.lang6{top:40%;left:72%}.lang7{top:60%;right:70%}.lang8{top:60%;left:70%}.lang9{top:80%;right:60%}.lang10{top:80%;left:60%}.search-container{float:none;margin:1em auto 1.5em;max-width:95%;padding:.3em;text-align:center;width:540px}.search-form fieldset{background-color:#f9f9f9;border:1px solid #aaa;margin-top:.5em;padding:.7em;width:auto}.search-form #searchInput{font-size:1.2em;margin:0;padding:0;vertical-align:top}.search-form select{margin:0 .4em;padding:0;vertical-align:middle;max-width:12em;width:12em}.bookshelf-container{width:100%;overflow:hidden;margin:2em 0 .5em;font-size:13px;font-weight:700;line-height:20px}.bookshelf-container .text{padding:0 .5em}.bookshelf{display:block;width:300%;overflow:hidden;position:relative;left:-100%;text-align:center;white-space:nowrap}.bookshelf-container .bookend{display:inline-block;vertical-align:top}.langlist{text-align:center;margin:.5em 3em 2.5em}.langlist-large{font-size:larger}.langlist-tiny{margin:.5em 3em 3em}.langlist-others{margin:0 3em 2em;font-weight:700}.otherprojects{font-weight:700;margin:1.5em auto;text-align:left;vertical-align:middle;width:90%}.otherprojects-item{float:left;height:60px;min-width:12em;width:24.75%;line-height:60px}.otherprojects-item a{display:block;width:10em;margin:0 auto}.sprite-project-logos-wrapper{width:47px;display:-moz-inline-stack;display:inline-block;text-align:center;vertical-align:middle}.otherprojects-item .sprite-project-logos{display:-moz-inline-stack;display:inline-block;text-align:center;vertical-align:middle}.otherprojects-item .otherprojects-item-name{vertical-align:middle}.sprite-project-logos-Wikispecies-logo_sister{background-position:0 -324px!important;width:35px!important;height:40px!important}.wm-site-info{clear:both;margin:2.5em 0 2em;text-align:center;padding-top:1.5em}.hlist ul{margin:0;padding:0}.hlist li,.hlist ul ul{display:inline}.hlist li:before{content:\" · \";font-weight:700}.hlist li:first-child:before{content:none}.hlist li+ul:before{content:\"\\00a0(\"}.hlist li+ul:after{content:\") \"}@media print{body{background-color:transparent}a{color:#000!important;background:0 0!important;padding:0!important}a:link,a:visited{color:#520;background:0 0}img{border:none}}@media all and (max-width:480px){.central-textlogo{line-height:normal;padding:0 0 0 84px;margin:0;height:70px;text-align:left;position:relative}.central-textlogo img{height:35px;width:auto;margin-top:22.5px}.central-featured{width:auto;height:auto;padding-top:2em;text-align:left;font-size:.8em;margin-top:4em}.central-featured-logo{right:0;left:.8em;top:-70px;margin-top:-4em;width:70px;position:absolute}.central-featured-lang{right:auto;top:auto;position:relative;left:auto;text-align:left;margin-left:1em;display:block;float:left;width:40%;height:5em}.language-search,.search-container label{display:none}.search-container{margin-top:0;height:6em;position:absolute;top:5em;left:0;right:0;max-width:100%;width:auto;padding:0;text-align:left}.search-form #searchInput{max-width:40%;vertical-align:middle}.search-form #searchLanguage,.search-form .formBtn{max-width:25%;vertical-align:middle}form fieldset{margin:0;border-left:none;border-right:none}.bookshelf{width:auto;left:auto;overflow:hidden;text-align:left}.bookshelf-container .bookend{width:40px!important}.bookshelf-container .bookend:last-child{display:none}.langlist a{word-wrap:break-word;white-space:normal}.langlist{font-size:.7em}.otherprojects-item{width:50%;text-align:left}.bookshelf-container,.langlist{margin:1em 1em 0;text-align:left;width:auto}hr{margin-top:.5em}}@media all and (max-width:240px){.central-featured-lang,.otherprojects-item{width:100%}.central-textlogo img{width:100px;height:auto}}@media all and (max-width:45em){.otherprojects{width:100%}.otherprojects-item{width:33.33%}}@media all and (max-width:30em){.otherprojects-item{width:50%}}@media all and (max-width:20em){.otherprojects-item{width:100%}}@media (-webkit-min-device-pixel-ratio:1.5),(min--moz-device-pixel-ratio:1.5),(min-resolution:1.5dppx),(min-resolution:144dpi){.bookshelf-container .bookend{background-size:40px auto}}@media (-webkit-min-device-pixel-ratio:2),(min--moz-device-pixel-ratio:2),(min-resolution:2dppx),(min-resolution:192dpi){.bookshelf-container .bookend{background-size:40px auto}}@media (min--moz-device-pixel-ratio:2),(-webkit-min-device-pixel-ratio:2),(min-resolution:2dppx),(min-resolution:192dpi){#footer,.search-form fieldset{border-width:.5px}hr{border-bottom-width:.5px}}@supports (-webkit-transform:rotate(30deg)){#footer,.search-form fieldset{border-width:1px}hr{border-bottom-width:1px}}.formBtn{background-color:#fff;border:1px solid #E4E4E4;border-radius:4px;border-top:1px solid #e8e8e8;border-bottom:1px solid #CECECE;font-size:11px;padding:1px 8px;margin-left:.3em;vertical-align:top}.formBtn .sprite-bookshelf_icons-search_icon{display:inline-block;vertical-align:middle}.formBtn:focus{background-color:silver;outline:0}.formBtn:active{background-color:#b0b0b0}.search-container .search-form #searchInput{font-size:13px;padding:1px 3px;width:15em}#langsearch-input{padding:1px 2px;font-size:11px;vertical-align:top}</style>\n" +
            "<style>.sprite-bookshelf_icons{background-image:url(portal/wikipedia.org/assets/img/sprite-bookshelf_icons.png)}@media (-webkit-min-device-pixel-ratio:1.5),(min-resolution:144dpi){.sprite-bookshelf_icons{background-image:url(portal/wikipedia.org/assets/img/sprite-bookshelf_icons@1.5x.png);background-size:40px 57px}}@media (-webkit-min-device-pixel-ratio:2),(min-resolution:192dpi){.sprite-bookshelf_icons{background-image:url(portal/wikipedia.org/assets/img/sprite-bookshelf_icons@2x.png);background-size:40px 57px}}.sprite-bookshelf_icons-caretDown{background-position:0 0;width:12px;height:12px}.sprite-bookshelf_icons-search-ltr-invert{background-position:0 -12px;width:12px;height:12px}.sprite-bookshelf_icons-search_icon{background-position:0 -24px;width:12px;height:13px}.sprite-bookshelf_icons-Bookshelf{background-position:0 -37px;width:40px;height:20px}.sprite-project-logos{background-image:url(portal/wikipedia.org/assets/img/sprite-project-logos.png)}@media (-webkit-min-device-pixel-ratio:1.5),(min-resolution:144dpi){.sprite-project-logos{background-image:url(portal/wikipedia.org/assets/img/sprite-project-logos@1.5x.png);background-size:88px 452px}}@media (-webkit-min-device-pixel-ratio:2),(min-resolution:192dpi){.sprite-project-logos{background-image:url(portal/wikipedia.org/assets/img/sprite-project-logos@2x.png);background-size:88px 453px}}.sprite-project-logos-Wikinews-logo_sister{background-position:0 0;width:47px;height:26px}.sprite-project-logos-Wikidata-logo_sister{background-position:0 -26px;width:47px;height:27px}.sprite-project-logos-Wikiversity-logo_sister{background-position:0 -52px;width:35px;height:28px}.sprite-project-logos-wikimedia-button{background-position:0 -80px;width:88px;height:31px}.sprite-project-logos-Wikibooks-logo_sister{background-position:0 -111px;width:35px;height:35px}.sprite-project-logos-Meta-logo_sister{background-position:0 -146px;width:35px;height:35px}.sprite-project-logos-Wikivoyage-logo_sister{background-position:0 -181px;width:35px;height:35px}.sprite-project-logos-Wiktionary-logo_sister{background-position:0 -216px;width:35px;height:35px}.sprite-project-logos-MediaWiki-logo_sister{background-position:0 -251px;width:47px;height:36px}.sprite-project-logos-Wikisource-logo_sister{background-position:0 -287px;width:35px;height:37px}.sprite-project-logos-Wikispecies-logo_sister{background-position:0 -323px;width:35px;height:41px}.sprite-project-logos-Wikiquote-logo_sister{background-position:0 -364px;width:35px;height:42px}.sprite-project-logos-Commons-logo_sister{background-position:0 -406px;width:35px;height:47px}</style>\n" +
            "<!--[if lt IE 8]><style type=\"text/css\">\n" +
            ".bookshelf-container .bookend,.otherprojects-icon{zoom:1;display:inline}\n" +
            "</style><![endif]-->\n" +
            "</head>\n" +
            "<body id=\"www-wikipedia-org\">\n" +
            "<h1 class=\"central-textlogo\" style=\"font-variant: small-caps\" alt=\"WikipediA\" title=\"Wikipedia\">\n" +
            "<img src=\"portal/wikipedia.org/assets/img/Wikipedia_wordmark.png\" srcset=\"portal/wikipedia.org/assets/img/Wikipedia_wordmark@1.5x.png 1.5x, portal/wikipedia.org/assets/img/Wikipedia_wordmark@2x.png 2x\" width=\"174\" height=\"30\" alt=\"WikipediA\" title=\"Wikipedia\">\n" +
            "</h1><!-- container div for the central logo and the links to the most viewed language editions -->\n" +
            "<div class=\"central-featured\" data-el-section=\"primary links\">\n" +
            "<!-- logo -->\n" +
            "<div class=\"central-featured-logo-wrapper\">\n" +
            "<img class=\"central-featured-logo\" src=\"portal/wikipedia.org/assets/img/Wikipedia-logo-v2.png\" srcset=\"portal/wikipedia.org/assets/img/Wikipedia-logo-v2@1.5x.png 1.5x, portal/wikipedia.org/assets/img/Wikipedia-logo-v2@2x.png 2x\" width=\"200\" alt=\"Wikipedia Logo\">\n" +
            "</div>\n" +
            "<!-- Rankings from http://stats.wikimedia.org/EN/Sitemap.htm -->\n" +
            "<!-- Article counts from http://meta.wikimedia.org/wiki/List_of_Wikipedias/Table -->\n" +
            "<!-- #1. en.wikipedia.org - 1 596 246 000 views/day -->\n" +
            "<div class=\"central-featured-lang lang1\" lang=\"en\">\n" +
            "<a href=\"//en.wikipedia.org/\" title=\"English — Wikipedia — The Free Encyclopedia\" class=\"link-box\">\n" +
            "<strong>English</strong><br>\n" +
            "<em>The Free Encyclopedia</em><br>\n" +
            "<small>5&nbsp;077&nbsp;000+ articles</small>\n" +
            "</a>\n" +
            "</div>\n" +
            "<!-- #2. es.wikipedia.org - 256 145 000 views/day -->\n" +
            "<div class=\"central-featured-lang lang2\" lang=\"es\">\n" +
            "<a href=\"//es.wikipedia.org/\" title=\"Español — Wikipedia — La enciclopedia libre\" class=\"link-box\">\n" +
            "<strong>Español</strong><br>\n" +
            "<em>La enciclopedia libre</em><br>\n" +
            "<small>1&nbsp;233&nbsp;000+ artículos</small>\n" +
            "</a>\n" +
            "</div>\n" +
            "<!-- #3. de.wikipedia.org - 223 337 000 views/day -->\n" +
            "<div class=\"central-featured-lang lang3\" lang=\"de\">\n" +
            "<a href=\"//de.wikipedia.org/\" title=\"Deutsch — Wikipedia — Die freie Enzyklopädie\" class=\"link-box\">\n" +
            "<strong>Deutsch</strong><br>\n" +
            "<em>Die freie Enzyklopädie</em><br>\n" +
            "<small>1&nbsp;907&nbsp;000+ Artikel</small>\n" +
            "</a>\n" +
            "</div>\n" +
            "<!-- #4. ja.wikipedia.org - 222 307 000 views/day -->\n" +
            "<div class=\"central-featured-lang lang4\" lang=\"ja\">\n" +
            "<a href=\"//ja.wikipedia.org/\" title=\"Nihongo — ウィキペディア — フリー百科事典\" class=\"link-box\">\n" +
            "<strong>日本語</strong><br>\n" +
            "<em class=\"emNonItalicLang\">フリー百科事典</em><br>\n" +
            "<small>1&nbsp;001&nbsp;000+ 記事</small>\n" +
            "</a>\n" +
            "</div>\n" +
            "<!-- #5. ru.wikipedia.org - 208 151 000 views/day -->\n" +
            "<div class=\"central-featured-lang lang5\" lang=\"ru\">\n" +
            "<a href=\"//ru.wikipedia.org/\" title=\"Russkiy — Википедия — Свободная энциклопедия\" class=\"link-box\">\n" +
            "<strong>Русский</strong><br>\n" +
            "<em>Свободная энциклопедия</em><br>\n" +
            "<small>1&nbsp;289&nbsp;000+ статей</small>\n" +
            "</a>\n" +
            "</div>\n" +
            "<!-- #6. fr.wikipedia.org - 160 177 000 views/day -->\n" +
            "<div class=\"central-featured-lang lang6\" lang=\"fr\">\n" +
            "<a href=\"//fr.wikipedia.org/\" title=\"Français — Wikipédia — L’encyclopédie libre\" class=\"link-box\">\n" +
            "<strong>Français</strong><br>\n" +
            "<em>L’encyclopédie libre</em><br>\n" +
            "<small>1&nbsp;723&nbsp;000+ articles</small>\n" +
            "</a>\n" +
            "</div>\n" +
            "<!-- #7. it.wikipedia.org - 119 093 000 views/day -->\n" +
            "<div class=\"central-featured-lang lang7\" lang=\"it\">\n" +
            "<a href=\"//it.wikipedia.org/\" title=\"Italiano — Wikipedia — L’enciclopedia libera\" class=\"link-box\">\n" +
            "<strong>Italiano</strong><br>\n" +
            "<em>L’enciclopedia libera</em><br>\n" +
            "<small>1&nbsp;252&nbsp;000+ voci</small>\n" +
            "</a>\n" +
            "</div>\n" +
            "<!-- #8. pt.wikipedia.org - 74 718 000 views/day -->\n" +
            "<div class=\"central-featured-lang lang8\" lang=\"pt\">\n" +
            "<a href=\"//pt.wikipedia.org/\" title=\"Português — Wikipédia — A enciclopédia livre\" class=\"link-box\">\n" +
            "<strong>Português</strong><br>\n" +
            "<em>A enciclopédia livre</em><br>\n" +
            "<small>909&nbsp;000+ artigos</small>\n" +
            "</a>\n" +
            "</div>\n" +
            "<!-- #9. zh.wikipedia.org - 64 303 000 views/day -->\n" +
            "<div class=\"central-featured-lang lang9\" lang=\"zh\">\n" +
            "<a href=\"//zh.wikipedia.org/\" title=\"Zhōngwén — 維基百科 — 自由的百科全書\" class=\"link-box\" data-converttitle-hans=\"Zhōngwén — 维基百科 — 自由的百科全书\" id=\"zh_top10\">\n" +
            "<strong>中文</strong><br>\n" +
            "<em data-convert-hans=\"自由的百科全书\" id=\"zh_tag\" class=\"emNonItalicLang\">自由的百科全書</em><br>\n" +
            "<small>863&nbsp;000+ <span data-convert-hans=\"条目\" id=\"zh_art\">條目</span></small>\n" +
            "</a>\n" +
            "</div>\n" +
            "<!-- #10. pl.wikipedia.org - 55 726 000 views/day -->\n" +
            "<div class=\"central-featured-lang lang10\" lang=\"pl\">\n" +
            "<a href=\"//pl.wikipedia.org/\" title=\"Polski — Wikipedia — Wolna encyklopedia\" class=\"link-box\">\n" +
            "<strong>Polski</strong><br>\n" +
            "<em>Wolna encyklopedia</em><br>\n" +
            "<small>1&nbsp;154&nbsp;000+ haseł</small>\n" +
            "</a>\n" +
            "</div>\n" +
            "</div>\n" +
            "<!-- Search form -->\n" +
            "<div id=\"search-container\" class=\"search-container\">\n" +
            "<form class=\"search-form\" action=\"//www.wikipedia.org/search-redirect.php\" data-el-section=\"search\">\n" +
            "<fieldset>\n" +
            "<!-- search-redirect.php is project-independent, requires a family -->\n" +
            "<input type=\"hidden\" name=\"family\" value=\"wikipedia\">\n" +
            "<select id=\"searchLanguage\" name=\"language\">\n" +
            "<!-- 100,000+ content pages, sorted by romanization -->\n" +
            "<option value=\"ar\" lang=\"ar\">العربية</option><!-- Al-ʿArabīyah -->\n" +
            "<option value=\"az\" lang=\"az\">Azərbaycanca</option>\n" +
            "<option value=\"bg\" lang=\"bg\">Български</option><!-- Bǎlgarski -->\n" +
            "<option value=\"zh-min-nan\" lang=\"nan\">Bân-lâm-gú / Hō-ló-oē</option>\n" +
            "<option value=\"be\" lang=\"be\">Беларуская (Акадэмічная)</option><!-- Belaruskaya (Akademichnaya) -->\n" +
            "<option value=\"ca\" lang=\"ca\">Català</option>\n" +
            "<option value=\"cs\" lang=\"cs\">Čeština</option>\n" +
            "<option value=\"da\" lang=\"da\">Dansk</option>\n" +
            "<option value=\"de\" lang=\"de\">Deutsch</option>\n" +
            "<option value=\"et\" lang=\"et\">Eesti</option>\n" +
            "<option value=\"el\" lang=\"el\">Ελληνικά</option><!-- Ellīniká -->\n" +
            "<option value=\"en\" lang=\"en\" selected=selected>English</option>\n" +
            "<option value=\"es\" lang=\"es\">Español</option>\n" +
            "<option value=\"eo\" lang=\"eo\">Esperanto</option>\n" +
            "<option value=\"eu\" lang=\"eu\">Euskara</option>\n" +
            "<option value=\"fa\" lang=\"fa\">فارسی</option><!-- Fārsi -->\n" +
            "<option value=\"fr\" lang=\"fr\">Français</option>\n" +
            "<option value=\"gl\" lang=\"gl\">Galego</option>\n" +
            "<option value=\"ko\" lang=\"ko\">한국어</option><!-- Hangugeo -->\n" +
            "<option value=\"hy\" lang=\"hy\">Հայերեն</option><!-- Hayeren -->\n" +
            "<option value=\"hi\" lang=\"hi\">हिन्दी</option><!-- Hindī -->\n" +
            "<option value=\"hr\" lang=\"hr\">Hrvatski</option>\n" +
            "<option value=\"id\" lang=\"id\">Bahasa Indonesia</option>\n" +
            "<option value=\"it\" lang=\"it\">Italiano</option>\n" +
            "<option value=\"he\" lang=\"he\">עברית</option><!-- ʿIvrit -->\n" +
            "<option value=\"ka\" lang=\"ka\">ქართული</option><!-- Kartuli -->\n" +
            "<option value=\"la\" lang=\"la\">Latina</option>\n" +
            "<option value=\"lt\" lang=\"lt\">Lietuvių</option>\n" +
            "<option value=\"hu\" lang=\"hu\">Magyar</option>\n" +
            "<option value=\"ms\" lang=\"ms\">Bahasa Melayu</option>\n" +
            "<option value=\"min\" lang=\"min\">Bahaso Minangkabau</option>\n" +
            "<option value=\"nl\" lang=\"nl\">Nederlands</option>\n" +
            "<option value=\"ja\" lang=\"ja\">日本語</option><!-- Nihongo -->\n" +
            "<option value=\"no\" lang=\"nb\">Norsk (Bokmål)</option>\n" +
            "<option value=\"nn\" lang=\"nn\">Norsk (Nynorsk)</option>\n" +
            "<option value=\"ce\" lang=\"ce\">Нохчийн</option><!-- Noxçiyn -->\n" +
            "<option value=\"uz\" lang=\"uz\">Oʻzbekcha / Ўзбекча</option>\n" +
            "<option value=\"pl\" lang=\"pl\">Polski</option>\n" +
            "<option value=\"pt\" lang=\"pt\">Português</option>\n" +
            "<option value=\"kk\" lang=\"kk\">Қазақша / Qazaqşa / قازاقشا</option>\n" +
            "<option value=\"ro\" lang=\"ro\">Română</option>\n" +
            "<option value=\"ru\" lang=\"ru\">Русский</option><!-- Russkiy -->\n" +
            "<option value=\"simple\" lang=\"en\">Simple English</option>\n" +
            "<option value=\"ceb\" lang=\"ceb\">Sinugboanong Binisaya</option>\n" +
            "<option value=\"sk\" lang=\"sk\">Slovenčina</option>\n" +
            "<option value=\"sl\" lang=\"sl\">Slovenščina</option>\n" +
            "<option value=\"sr\" lang=\"sr\">Српски / Srpski</option>\n" +
            "<option value=\"sh\" lang=\"sh\">Srpskohrvatski / Српскохрватски</option>\n" +
            "<option value=\"fi\" lang=\"fi\">Suomi</option>\n" +
            "<option value=\"sv\" lang=\"sv\">Svenska</option>\n" +
            "<option value=\"th\" lang=\"th\">ภาษาไทย</option><!-- Phasa Thai -->\n" +
            "<option value=\"tr\" lang=\"tr\">Türkçe</option>\n" +
            "<option value=\"uk\" lang=\"uk\">Українська</option><!-- Ukrayins’ka -->\n" +
            "<option value=\"ur\" lang=\"ur\">اردو</option><!-- Urdu -->\n" +
            "<option value=\"vi\" lang=\"vi\">Tiếng Việt</option>\n" +
            "<option value=\"vo\" lang=\"vo\">Volapük</option>\n" +
            "<option value=\"war\" lang=\"war\">Winaray</option>\n" +
            "<option value=\"zh\" lang=\"zh\">中文</option><!-- Zhōngwén -->\n" +
            "</select>\n" +
            "<input id=\"searchInput\" name=\"search\" type=\"search\" size=\"20\" autofocus=\"autofocus\" accesskey=\"F\" dir=\"auto\">\n" +
            "<button class=\"formBtn\" type=\"submit\" name=\"go\">\n" +
            "<div class=\"sprite-bookshelf_icons sprite-bookshelf_icons-search_icon\"></div>\n" +
            "<!--<img width='12' height='13' src=\"portal/wikipedia.org/assets/img/12px-Vector_search_icon.svg.png\" srcset=\"portal/wikipedia.org/assets/img/18px-Vector_search_icon.svg.png 1.5x, portal/wikipedia.org/assets/img/24px-Vector_search_icon.svg.png 2x\" alt=\"Search\">-->\n" +
            "</button>\n" +
            "<input type=\"hidden\" value=\"Go\" name=\"go\">\n" +
            "</fieldset>\n" +
            "</form>\n" +
            "</div>\n" +
            "<hr>\n" +
            "<div class=\"search-container language-search\" data-el-section=\"language search\">\n" +
            "<form action=\"//incubator.wikimedia.org/w/index.php\" method=\"get\" name=\"searchwiki\">\n" +
            "<input type=\"hidden\" name=\"title\" value=\"Special:SearchWiki\">\n" +
            "<input type=\"hidden\" name=\"uselang\" value=\"en\">\n" +
            "<input type=\"hidden\" name=\"searchproject\" value=\"p\">\n" +
            "<label for=\"langsearch-input\">Find a language:</label>\n" +
            "<input type=\"search\" name=\"searchlanguage\" id=\"langsearch-input\" value=\"\" dir=\"auto\">\n" +
            "<input class=\"formBtn\" type=\"submit\" value=\"  →  \">\n" +
            "</form>\n" +
            "</div>\n" +
            "<!-- Bookshelves -->\n" +
            "<h2 class=\"bookshelf-container\">\n" +
            "<span class=\"bookshelf\">\n" +
            "<span class=\"sprite-bookshelf_icons sprite-bookshelf_icons-Bookshelf bookend\" style=\"width: 360px /* 9 * 40 */\"></span><span class=\"text\">1&nbsp;000&nbsp;000+</span><span class=\"sprite-bookshelf_icons sprite-bookshelf_icons-Bookshelf bookend\" style=\"width: 360px\"></span>\n" +
            "</span>\n" +
            "</h2>\n" +
            "<!-- 1,000,000+ content pages -->\n" +
            "<div class=\"langlist langlist-large hlist\" data-el-section=\"secondary links\">\n" +
            "<ul>\n" +
            "<li><a href=\"//de.wikipedia.org/\" lang=\"de\">Deutsch</a></li>\n" +
            "<li><a href=\"//en.wikipedia.org/\" lang=\"en\">English</a></li>\n" +
            "<li><a href=\"//es.wikipedia.org/\" lang=\"es\">Español</a></li>\n" +
            "<li><a href=\"//fr.wikipedia.org/\" lang=\"fr\">Français</a></li>\n" +
            "<li><a href=\"//it.wikipedia.org/\" lang=\"it\">Italiano</a></li>\n" +
            "<li><a href=\"//nl.wikipedia.org/\" lang=\"nl\">Nederlands</a></li>\n" +
            "<li><a href=\"//ja.wikipedia.org/\" lang=\"ja\" title=\"Nihongo\">日本語</a></li>\n" +
            "<li><a href=\"//pl.wikipedia.org/\" lang=\"pl\">Polski</a></li>\n" +
            "<li><a href=\"//ru.wikipedia.org/\" lang=\"ru\" title=\"Russkiy\">Русский</a></li>\n" +
            "<li><a href=\"//ceb.wikipedia.org/\" lang=\"ceb\">Sinugboanong Binisaya</a></li>\n" +
            "<li><a href=\"//sv.wikipedia.org/\" lang=\"sv\">Svenska</a></li>\n" +
            "<li><a href=\"//vi.wikipedia.org/\" lang=\"vi\">Tiếng Việt</a></li>\n" +
            "<li><a href=\"//war.wikipedia.org/\" lang=\"war\">Winaray</a></li>\n" +
            "</ul>\n" +
            "</div>\n" +
            "<!-- Bookshelves -->\n" +
            "<h2 class=\"bookshelf-container\">\n" +
            "<span class=\"bookshelf\">\n" +
            "<span class=\"sprite-bookshelf_icons sprite-bookshelf_icons-Bookshelf bookend\" style=\"width: 280px /* 7 * 40 */\"></span><span class=\"text\">100&nbsp;000+</span><span class=\"sprite-bookshelf_icons sprite-bookshelf_icons-Bookshelf bookend\" style=\"width: 280px\"></span>\n" +
            "</span>\n" +
            "</h2>\n" +
            "<!-- 100,000+ content pages -->\n" +
            "<div class=\"langlist langlist-large hlist\" data-el-section=\"secondary links\">\n" +
            "<ul>\n" +
            "<li><a href=\"//ar.wikipedia.org/\" lang=\"ar\" title=\"Al-ʿArabīyah\"><bdi dir=\"rtl\">العربية</bdi></a></li>\n" +
            "<li><a href=\"//az.wikipedia.org/\" lang=\"az\">Azərbaycanca</a></li>\n" +
            "<li><a href=\"//bg.wikipedia.org/\" lang=\"bg\" title=\"Bǎlgarski\">Български</a></li>\n" +
            "<li><a href=\"//zh-min-nan.wikipedia.org/\" lang=\"nan\">Bân-lâm-gú / Hō-ló-oē</a></li>\n" +
            "<li><a href=\"//be.wikipedia.org/\" lang=\"be\" title=\"Belaruskaya (Akademichnaya)\">Беларуская (Акадэмічная)</a></li>\n" +
            "<li><a href=\"//ca.wikipedia.org/\" lang=\"ca\">Català</a></li>\n" +
            "<li><a href=\"//cs.wikipedia.org/\" lang=\"cs\">Čeština</a></li>\n" +
            "<li><a href=\"//da.wikipedia.org/\" lang=\"da\">Dansk</a></li>\n" +
            "<li><a href=\"//et.wikipedia.org/\" lang=\"et\">Eesti</a></li>\n" +
            "<li><a href=\"//el.wikipedia.org/\" lang=\"el\" title=\"Ellīniká\">Ελληνικά</a></li>\n" +
            "<li><a href=\"//eo.wikipedia.org/\" lang=\"eo\">Esperanto</a></li>\n" +
            "<li><a href=\"//eu.wikipedia.org/\" lang=\"eu\">Euskara</a></li>\n" +
            "<li><a href=\"//fa.wikipedia.org/\" lang=\"fa\" title=\"Fārsi\"><bdi dir=\"rtl\">فارسی</bdi></a></li>\n" +
            "<li><a href=\"//gl.wikipedia.org/\" lang=\"gl\">Galego</a></li>\n" +
            "<li><a href=\"//ko.wikipedia.org/\" lang=\"ko\" title=\"Hangugeo\">한국어</a></li>\n" +
            "<li><a href=\"//hy.wikipedia.org/\" lang=\"hy\" title=\"Hayeren\">Հայերեն</a></li>\n" +
            "<li><a href=\"//hi.wikipedia.org/\" lang=\"hi\" title=\"Hindī\">हिन्दी</a></li>\n" +
            "<li><a href=\"//hr.wikipedia.org/\" lang=\"hr\">Hrvatski</a></li>\n" +
            "<li><a href=\"//id.wikipedia.org/\" lang=\"id\">Bahasa Indonesia</a></li>\n" +
            "<li><a href=\"//he.wikipedia.org/\" lang=\"he\" title=\"ʿIvrit\"><bdi dir=\"rtl\">עברית</bdi></a></li>\n" +
            "<li><a href=\"//ka.wikipedia.org/\" lang=\"ka\" title=\"Kartuli\">ქართული</a></li>\n" +
            "<li><a href=\"//la.wikipedia.org/\" lang=\"la\">Latina</a></li>\n" +
            "<li><a href=\"//lt.wikipedia.org/\" lang=\"lt\">Lietuvių</a></li>\n" +
            "<li><a href=\"//hu.wikipedia.org/\" lang=\"hu\">Magyar</a></li>\n" +
            "<li><a href=\"//ms.wikipedia.org/\" lang=\"ms\">Bahasa Melayu</a></li>\n" +
            "<li><a href=\"//min.wikipedia.org/\" lang=\"min\">Bahaso Minangkabau</a></li>\n" +
            "<li lang=\"no\">Norsk</li><ul><li><a href=\"//no.wikipedia.org/\" lang=\"nb\">Bokmål</a></li><li><a href=\"//nn.wikipedia.org/\" lang=\"nn\">Nynorsk</a></li></ul>\n" +
            "<li><a href=\"//ce.wikipedia.org/\" lang=\"ce\" title=\"Noxçiyn\">Нохчийн</a></li>\n" +
            "<li><a href=\"//uz.wikipedia.org/\" lang=\"uz\"><span lang=\"uz-Latn\">Oʻzbekcha</span> / <span lang=\"uz-Cyrl\">Ўзбекча</span></a></li>\n" +
            "<li><a href=\"//pt.wikipedia.org/\" lang=\"pt\">Português</a></li>\n" +
            "<li><a href=\"//kk.wikipedia.org/\" lang=\"kk\"><span lang=\"kk-Cyrl\">Қазақша</span> / <span lang=\"kk-Latn\">Qazaqşa</span> / <bdi lang=\"kk-Arab\" dir=\"rtl\">قازاقشا</bdi></a></li>\n" +
            "<li><a href=\"//ro.wikipedia.org/\" lang=\"ro\">Română</a></li>\n" +
            "<li><a href=\"//simple.wikipedia.org/\" lang=\"en\">Simple English</a></li>\n" +
            "<li><a href=\"//sk.wikipedia.org/\" lang=\"sk\">Slovenčina</a></li>\n" +
            "<li><a href=\"//sl.wikipedia.org/\" lang=\"sl\">Slovenščina</a></li>\n" +
            "<li><a href=\"//sr.wikipedia.org/\" lang=\"sr\"><span lang=\"sr-Cyrl\">Српски</span> / <span lang=\"sr-Latn\">Srpski</span></a></li>\n" +
            "<li><a href=\"//sh.wikipedia.org/\" lang=\"sh\"><span lang=\"sh-Latn\">Srpskohrvatski</span> / <span lang=\"sh-Cyrl\">Српскохрватски</span></a></li>\n" +
            "<li><a href=\"//fi.wikipedia.org/\" lang=\"fi\">Suomi</a></li>\n" +
            "<li><a href=\"//th.wikipedia.org/\" lang=\"th\" title=\"Phasa Thai\">ภาษาไทย</a></li>\n" +
            "<li><a href=\"//tr.wikipedia.org/\" lang=\"tr\">Türkçe</a></li>\n" +
            "<li><a href=\"//uk.wikipedia.org/\" lang=\"uk\" title=\"Ukrayins’ka\">Українська</a></li>\n" +
            "<li><a href=\"//ur.wikipedia.org/\" lang=\"ur\" title=\"Urdu\"><bdi dir=\"rtl\">اردو</bdi></a></li>\n" +
            "<li><a href=\"//vo.wikipedia.org/\" lang=\"vo\">Volapük</a></li>\n" +
            "<li><a href=\"//zh.wikipedia.org/\" lang=\"zh\" title=\"Zhōngwén\">中文</a></li>\n" +
            "</ul>\n" +
            "</div>\n" +
            "<!-- Bookshelves -->\n" +
            "<h2 class=\"bookshelf-container\">\n" +
            "<span class=\"bookshelf\"><span class=\"sprite-bookshelf_icons sprite-bookshelf_icons-Bookshelf bookend\" style=\"width: 200px /* 5 * 40 */\"></span><span class=\"text\">10&nbsp;000+</span><span class=\"sprite-bookshelf_icons sprite-bookshelf_icons-Bookshelf bookend\" style=\"width: 200px\"></span></span>\n" +
            "</h2>\n" +
            "<!-- 10,000+ content pages -->\n" +
            "<div class=\"langlist hlist\" data-el-section=\"secondary links\">\n" +
            "<ul>\n" +
            "<li><a href=\"//af.wikipedia.org/\" lang=\"af\">Afrikaans</a></li>\n" +
            "<li><a href=\"//als.wikipedia.org/\" lang=\"gsw\">Alemannisch</a></li>\n" +
            "<li><a href=\"//am.wikipedia.org/\" lang=\"am\" title=\"Āmariññā\">አማርኛ</a></li>\n" +
            "<li><a href=\"//an.wikipedia.org/\" lang=\"an\">Aragonés</a></li>\n" +
            "<li><a href=\"//ast.wikipedia.org/\" lang=\"ast\">Asturianu</a></li>\n" +
            "<li><a href=\"//bn.wikipedia.org/\" lang=\"bn\" title=\"Bangla\">বাংলা</a></li>\n" +
            "<li><a href=\"//map-bms.wikipedia.org/\" lang=\"map-x-bms\">Basa Banyumasan</a></li>\n" +
            "<li><a href=\"//ba.wikipedia.org/\" lang=\"ba\" title=\"Başqortsa\">Башҡортса</a></li>\n" +
            "<li><a href=\"//be-tarask.wikipedia.org/\" lang=\"be\" title=\"Belaruskaya (Taraškievica)\">Беларуская (Тарашкевіца)</a></li>\n" +
            "<li><a href=\"//bpy.wikipedia.org/\" lang=\"bpy\" title=\"Bishnupriya Manipuri\">বিষ্ণুপ্রিয়া মণিপুরী</a></li>\n" +
            "<li><a href=\"//bar.wikipedia.org/\" lang=\"bar\">Boarisch</a></li>\n" +
            "<li><a href=\"//bs.wikipedia.org/\" lang=\"bs\">Bosanski</a></li>\n" +
            "<li><a href=\"//br.wikipedia.org/\" lang=\"br\">Brezhoneg</a></li>\n" +
            "<li><a href=\"//cv.wikipedia.org/\" lang=\"cv\" title=\"Čăvašla\">Чӑвашла</a></li>\n" +
            "<li><a href=\"//cy.wikipedia.org/\" lang=\"cy\">Cymraeg</a></li>\n" +
            "<li><a href=\"//fo.wikipedia.org/\" lang=\"fo\">Føroyskt</a></li>\n" +
            "<li><a href=\"//fy.wikipedia.org/\" lang=\"fy\">Frysk</a></li>\n" +
            "<li><a href=\"//ga.wikipedia.org/\" lang=\"ga\">Gaeilge</a></li>\n" +
            "<li><a href=\"//gd.wikipedia.org/\" lang=\"gd\">Gàidhlig</a></li>\n" +
            "<li><a href=\"//gu.wikipedia.org/\" lang=\"gu\" title=\"Gujarati\">ગુજરાતી</a></li>\n" +
            "<li><a href=\"//hsb.wikipedia.org/\" lang=\"hsb\">Hornjoserbsce</a></li>\n" +
            "<li><a href=\"//io.wikipedia.org/\" lang=\"io\">Ido</a></li>\n" +
            "<li><a href=\"//ia.wikipedia.org/\" lang=\"ia\">Interlingua</a></li>\n" +
            "<li><a href=\"//os.wikipedia.org/\" lang=\"os\" title=\"Iron Ævzag\">Ирон æвзаг</a></li>\n" +
            "<li><a href=\"//is.wikipedia.org/\" lang=\"is\">Íslenska</a></li>\n" +
            "<li><a href=\"//jv.wikipedia.org/\" lang=\"jv\">Basa Jawa</a></li>\n" +
            "<li><a href=\"//kn.wikipedia.org/\" lang=\"kn\" title=\"Kannada\">ಕನ್ನಡ</a></li>\n" +
            "<li><a href=\"//ht.wikipedia.org/\" lang=\"ht\">Kreyòl Ayisyen</a></li>\n" +
            "<li><a href=\"//ku.wikipedia.org/\" lang=\"ku\"><span lang=\"ku-Latn\">Kurdî</span> / <bdi lang=\"ku-Arab\" dir=\"rtl\">كوردی</bdi></a></li>\n" +
            "<li><a href=\"//ckb.wikipedia.org/\" lang=\"ckb\" title=\"Kurdîy Nawendî\"><bdi dir=\"rtl\">کوردیی ناوەندی</bdi></a></li>\n" +
            "<li><a href=\"//ky.wikipedia.org/\" lang=\"ky\" title=\"Kyrgyzča\">Кыргызча</a></li>\n" +
            "<li><a href=\"//mrj.wikipedia.org/\" lang=\"mjr\" title=\"Kyryk Mary\">Кырык Мары</a></li>\n" +
            "<li><a href=\"//lv.wikipedia.org/\" lang=\"lv\">Latviešu</a></li>\n" +
            "<li><a href=\"//lb.wikipedia.org/\" lang=\"lb\">Lëtzebuergesch</a></li>\n" +
            "<li><a href=\"//li.wikipedia.org/\" lang=\"li\">Limburgs</a></li>\n" +
            "<li><a href=\"//lmo.wikipedia.org/\" lang=\"lmo\">Lumbaart</a></li>\n" +
            "<li><a href=\"//mk.wikipedia.org/\" lang=\"mk\" title=\"Makedonski\">Македонски</a></li>\n" +
            "<li><a href=\"//mg.wikipedia.org/\" lang=\"mg\">Malagasy</a></li>\n" +
            "<li><a href=\"//ml.wikipedia.org/\" lang=\"ml\" title=\"Malayalam\">മലയാളം</a></li>\n" +
            "<li><a href=\"//mr.wikipedia.org/\" lang=\"mr\" title=\"Marathi\">मराठी</a></li>\n" +
            "<li><a href=\"//arz.wikipedia.org/\" lang=\"arz\" title=\"Maṣrī\"><bdi dir=\"rtl\">مصرى</bdi></a></li>\n" +
            "<li><a href=\"//mzn.wikipedia.org/\" lang=\"mzn\" title=\"Mäzeruni\"><bdi dir=\"rtl\">مازِرونی</bdi></a></li>\n" +
            "<li><a href=\"//mn.wikipedia.org/\" lang=\"mn\" title=\"Mongol\">Монгол</a></li>\n" +
            "<li><a href=\"//my.wikipedia.org/\" lang=\"my\" title=\"Myanmarsar\">မြန်မာဘာသာ</a></li>\n" +
            "<li><a href=\"//nah.wikipedia.org/\" lang=\"nah\">Nāhuatlahtōlli</a></li>\n" +
            "<li><a href=\"//new.wikipedia.org/\" lang=\"new\" title=\"Nepal Bhasa\">नेपाल भाषा</a></li>\n" +
            "<li><a href=\"//ne.wikipedia.org/\" lang=\"ne\" title=\"Nepālī\">नेपाली</a></li>\n" +
            "<li><a href=\"//nap.wikipedia.org/\" lang=\"nap\">Nnapulitano</a></li>\n" +
            "<li><a href=\"//oc.wikipedia.org/\" lang=\"oc\">Occitan</a></li>\n" +
            "<li><a href=\"//or.wikipedia.org/\" lang=\"or\" title=\"Oṛiā\">ଓଡି଼ଆ</a></li>\n" +
            "<li><a href=\"//pa.wikipedia.org/\" lang=\"pa\" title=\"Pañjābī (Gurmukhī)\">ਪੰਜਾਬੀ (ਗੁਰਮੁਖੀ)</a></li>\n" +
            "<li><a href=\"//pnb.wikipedia.org/\" lang=\"pnb\" title=\"Pañjābī (Shāhmukhī)\"><bdi dir=\"rtl\">پنجابی (شاہ مکھی)</bdi></a></li>\n" +
            "<li><a href=\"//pms.wikipedia.org/\" lang=\"pms\">Piemontèis</a></li>\n" +
            "<li><a href=\"//nds.wikipedia.org/\" lang=\"nds\">Plattdüütsch</a></li>\n" +
            "<li><a href=\"//qu.wikipedia.org/\" lang=\"qu\">Runa Simi</a></li>\n" +
            "<li><a href=\"//sa.wikipedia.org/\" lang=\"sa\" title=\"Saṃskṛtam\">संस्कृतम्</a></li>\n" +
            "<li><a href=\"//sah.wikipedia.org/\" lang=\"sah\" title=\"Saxa Tyla\">Саха Тыла</a></li>\n" +
            "<li><a href=\"//sco.wikipedia.org/\" lang=\"sco\">Scots</a></li>\n" +
            "<li><a href=\"//sq.wikipedia.org/\" lang=\"sq\">Shqip</a></li>\n" +
            "<li><a href=\"//scn.wikipedia.org/\" lang=\"scn\">Sicilianu</a></li>\n" +
            "<li><a href=\"//si.wikipedia.org/\" lang=\"si\" title=\"Siṃhala\">සිංහල</a></li>\n" +
            "<li><a href=\"//su.wikipedia.org/\" lang=\"su\">Basa Sunda</a></li>\n" +
            "<li><a href=\"//sw.wikipedia.org/\" lang=\"sw\">Kiswahili</a></li>\n" +
            "<li><a href=\"//tl.wikipedia.org/\" lang=\"tl\">Tagalog</a></li>\n" +
            "<li><a href=\"//ta.wikipedia.org/\" lang=\"ta\" title=\"Tamiḻ\">தமிழ்</a></li>\n" +
            "<li><a href=\"//tt.wikipedia.org/\" lang=\"tt\"><span lang=\"tt-Cyrl\">Татарча</span> / <span lang=\"tt-Latn\">Tatarça</span></a></li>\n" +
            "<li><a href=\"//te.wikipedia.org/\" lang=\"te\" title=\"Telugu\">తెలుగు</a></li>\n" +
            "<li><a href=\"//tg.wikipedia.org/\" lang=\"tg\" title=\"Tojikī\">Тоҷикӣ</a></li>\n" +
            "<li><a href=\"//bug.wikipedia.org/\" lang=\"bug\"><span lang=\"bug-Bugi\">ᨅᨔ ᨕᨙᨁᨗ</span> / <span lang=\"bug-Latn\">Basa Ugi</span></a></li>\n" +
            "<li><a href=\"//vec.wikipedia.org/\" lang=\"vec\">Vèneto</a></li>\n" +
            "<li><a href=\"//wa.wikipedia.org/\" lang=\"wa\">Walon</a></li>\n" +
            "<li><a href=\"//yi.wikipedia.org/\" lang=\"yi\" title=\"Yidiš\"><bdi dir=\"rtl\">ייִדיש</bdi></a></li>\n" +
            "<li><a href=\"//yo.wikipedia.org/\" lang=\"yo\">Yorùbá</a></li>\n" +
            "<li><a href=\"//zh-yue.wikipedia.org/\" lang=\"yue\" title=\"Yuht Yúh / Jyut6 jyu5\" data-convert-hans=\"粤语\" id=\"zh-yue_wiki\">粵語</a></li>\n" +
            "<li><a href=\"//bat-smg.wikipedia.org/\" lang=\"sgs\">Žemaitėška</a></li>\n" +
            "</ul>\n" +
            "</div>\n" +
            "<!-- Bookshelves -->\n" +
            "<h2 class=\"bookshelf-container\">\n" +
            "<span class=\"bookshelf\"><span class=\"sprite-bookshelf_icons sprite-bookshelf_icons-Bookshelf bookend\" style=\"width: 120px /* 3 * 40 */\"></span><span class=\"text\">1&nbsp;000+</span><span class=\"sprite-bookshelf_icons sprite-bookshelf_icons-Bookshelf bookend\" style=\"width: 120px\"></span></span>\n" +
            "</h2>\n" +
            "<!-- 1,000+ content pages -->\n" +
            "<div class=\"langlist hlist\" data-el-section=\"secondary links\">\n" +
            "<ul>\n" +
            "<li><a href=\"//ace.wikipedia.org/\" lang=\"ace\">Bahsa Acèh</a></li>\n" +
            "<li><a href=\"//kbd.wikipedia.org/\" lang=\"kbd\" title=\"Adighabze\">Адыгэбзэ</a></li>\n" +
            "<li><a href=\"//ang.wikipedia.org/\" lang=\"ang\">Ænglisc</a></li>\n" +
            "<li><a href=\"//roa-rup.wikipedia.org/\" lang=\"roa-rup\">Armãneashce</a></li>\n" +
            "<li><a href=\"//frp.wikipedia.org/\" lang=\"frp\">Arpitan</a></li>\n" +
            "<li><a href=\"//arc.wikipedia.org/\" lang=\"arc\" title=\"Ātûrāyâ\"><bdi dir=\"rtl\">ܐܬܘܪܝܐ</bdi></a></li>\n" +
            "<li><a href=\"//gn.wikipedia.org/\" lang=\"gn\">Avañe’ẽ</a></li>\n" +
            "<li><a href=\"//av.wikipedia.org/\" lang=\"av\" title=\"Avar\">Авар</a></li>\n" +
            "<li><a href=\"//ay.wikipedia.org/\" lang=\"ay\">Aymar</a></li>\n" +
            "<li><a href=\"//bjn.wikipedia.org/\" lang=\"bjn\">Bahasa Banjar</a></li>\n" +
            "<li><a href=\"//bh.wikipedia.org/\" lang=\"bh\" title=\"Bhōjapurī\">भोजपुरी</a></li>\n" +
            "<li><a href=\"//bcl.wikipedia.org/\" lang=\"bcl\">Bikol Central</a></li>\n" +
            "<li><a href=\"//bo.wikipedia.org/\" lang=\"bo\" title=\"Bod Skad\">བོད་ཡིག</a></li>\n" +
            "<li><a href=\"//bxr.wikipedia.org/\" lang=\"bxr\" title=\"Buryad\">Буряад</a></li>\n" +
            "<li><a href=\"//cbk-zam.wikipedia.org/\" lang=\"cbk-x-zam\">Chavacano de Zamboanga</a></li>\n" +
            "<li><a href=\"//co.wikipedia.org/\" lang=\"co\">Corsu</a></li>\n" +
            "<li><a href=\"//za.wikipedia.org/\" lang=\"za\">Cuengh</a></li>\n" +
            "<li><a href=\"//pdc.wikipedia.org/\" lang=\"pdc\">Deitsch</a></li>\n" +
            "<li><a href=\"//dv.wikipedia.org/\" lang=\"dv\" title=\"Dhivehi\"><bdi dir=\"rtl\">ދިވެހިބަސް</bdi></a></li>\n" +
            "<li><a href=\"//nv.wikipedia.org/\" lang=\"nv\">Diné Bizaad</a></li>\n" +
            "<li><a href=\"//dsb.wikipedia.org/\" lang=\"dsb\">Dolnoserbski</a></li>\n" +
            "<li><a href=\"//eml.wikipedia.org/\" lang=\"roa-x-eml\">Emigliàn–Rumagnòl</a></li>\n" +
            "<li><a href=\"//myv.wikipedia.org/\" lang=\"myv\" title=\"Erzjanj\">Эрзянь</a></li>\n" +
            "<li><a href=\"//ext.wikipedia.org/\" lang=\"ext\">Estremeñu</a></li>\n" +
            "<li><a href=\"//hif.wikipedia.org/\" lang=\"hif\">Fiji Hindi</a></li>\n" +
            "<li><a href=\"//fur.wikipedia.org/\" lang=\"fur\">Furlan</a></li>\n" +
            "<li><a href=\"//gv.wikipedia.org/\" lang=\"gv\">Gaelg</a></li>\n" +
            "<li><a href=\"//gag.wikipedia.org/\" lang=\"gag\">Gagauz</a></li>\n" +
            "<li><a href=\"//glk.wikipedia.org/\" lang=\"glk\" title=\"Giləki\"><bdi dir=\"rtl\">گیلکی</bdi></a></li>\n" +
            "<li><a href=\"//gan.wikipedia.org/\" lang=\"gan\" title=\"Gon ua\" data-convert-hans=\"赣语\" id=\"gan_wiki\">贛語</a></li>\n" +
            "<li><a href=\"//hak.wikipedia.org/\" lang=\"hak\"><span lang=\"hak-Latn\">Hak-kâ-fa</span> / <span id=\"hak_wiki\" lang=\"hak-Hani\" data-convert-hans=\"客家话\">客家話</span></a></li>\n" +
            "<li><a href=\"//xal.wikipedia.org/\" lang=\"xal\" title=\"Halʹmg\">Хальмг</a></li>\n" +
            "<li><a href=\"//ha.wikipedia.org/\" lang=\"ha\"><span lang=\"ha-Latn\">Hausa</span> / <bdi lang=\"ha-Arab\" dir=\"rtl\">هَوُسَا</bdi></a></li>\n" +
            "<li><a href=\"//haw.wikipedia.org/\" lang=\"haw\">ʻŌlelo Hawaiʻi</a></li>\n" +
            "<li><a href=\"//ig.wikipedia.org/\" lang=\"ig\">Igbo</a></li>\n" +
            "<li><a href=\"//ilo.wikipedia.org/\" lang=\"ilo\">Ilokano</a></li>\n" +
            "<li><a href=\"//ie.wikipedia.org/\" lang=\"ie\">Interlingue</a></li>\n" +
            "<li><a href=\"//kl.wikipedia.org/\" lang=\"kl\">Kalaallisut</a></li>\n" +
            "<li><a href=\"//pam.wikipedia.org/\" lang=\"pam\">Kapampangan</a></li>\n" +
            "<li><a href=\"//csb.wikipedia.org/\" lang=\"csb\">Kaszëbsczi</a></li>\n" +
            "<li><a href=\"//kw.wikipedia.org/\" lang=\"kw\">Kernewek</a></li>\n" +
            "<li><a href=\"//km.wikipedia.org/\" lang=\"km\" title=\"Phéasa Khmér\">ភាសាខ្មែរ</a></li>\n" +
            "<li><a href=\"//rw.wikipedia.org/\" lang=\"rw\">Kinyarwanda</a></li>\n" +
            "<li><a href=\"//kv.wikipedia.org/\" lang=\"kv\" title=\"Komi\">Коми</a></li>\n" +
            "<li><a href=\"//kg.wikipedia.org/\" lang=\"kg\">Kongo</a></li>\n" +
            "<li><a href=\"//gom.wikipedia.org/\" lang=\"gom\"><span lang=\"gom-Deva\">कोंकणी</span> / <span lang=\"gom-Latn\">Konknni</span></a></li>\n" +
            "<li><a href=\"//lo.wikipedia.org/\" lang=\"lo\" title=\"Phaasaa Laao\">ພາສາລາວ</a></li>\n" +
            "<li><a href=\"//lad.wikipedia.org/\" lang=\"lad\" title=\"Ladino\"><span lang=\"lad-Latn\">Dzhudezmo</span> / <bdi lang=\"lad-Hebr\" dir=\"rtl\">לאדינו</bdi></a></li>\n" +
            "<li><a href=\"//lbe.wikipedia.org/\" lang=\"lbe\" title=\"Lakːu\">Лакку</a></li>\n" +
            "<li><a href=\"//lez.wikipedia.org/\" lang=\"lez\" title=\"Lezgi\">Лезги</a></li>\n" +
            "<li><a href=\"//lij.wikipedia.org/\" lang=\"lij\">Líguru</a></li>\n" +
            "<li><a href=\"//ln.wikipedia.org/\" lang=\"ln\">Lingála</a></li>\n" +
            "<li><a href=\"//jbo.wikipedia.org/\" lang=\"jbo\">lojban</a></li>\n" +
            "<li><a href=\"//lrc.wikipedia.org/\" lang=\"lrc\" title=\"Löriyé-Šomālī\"><bdi dir=\"rtl\">لۊری شومالی</bdi></a></li>\n" +
            "<li><a href=\"//mai.wikipedia.org/\" lang=\"mai\" title=\"Maithilī\">मैथिली</a></li>\n" +
            "<li><a href=\"//mt.wikipedia.org/\" lang=\"mt\">Malti</a></li>\n" +
            "<li><a href=\"//zh-classical.wikipedia.org/\" lang=\"lzh\" title=\"Man4jin4 / Wényán\">文言</a></li>\n" +
            "<li><a href=\"//ty.wikipedia.org/\" lang=\"ty\">Reo Mā’ohi</a></li>\n" +
            "<li><a href=\"//mi.wikipedia.org/\" lang=\"mi\">Māori</a></li>\n" +
            "<li><a href=\"//xmf.wikipedia.org/\" lang=\"xmf\" title=\"Margaluri\">მარგალური</a></li>\n" +
            "<li><a href=\"//cdo.wikipedia.org/\" lang=\"cdo\">Mìng-dĕ̤ng-ngṳ̄</a></li>\n" +
            "<li><a href=\"//mwl.wikipedia.org/\" lang=\"mwl\">Mirandés</a></li>\n" +
            "<li><a href=\"//mdf.wikipedia.org/\" lang=\"mdf\" title=\"Mokšenj\">Мокшень</a></li>\n" +
            "<li><a href=\"//na.wikipedia.org/\" lang=\"na\">Dorerin Naoero</a></li>\n" +
            "<li><a href=\"//nds-nl.wikipedia.org/\" lang=\"nds-nl\">Nedersaksisch</a></li>\n" +
            "<li><a href=\"//frr.wikipedia.org/\" lang=\"frr\">Nordfriisk</a></li>\n" +
            "<li><a href=\"//nrm.wikipedia.org/\" lang=\"roa-x-nrm\">Nouormand / Normaund</a></li>\n" +
            "<li><a href=\"//nov.wikipedia.org/\" lang=\"nov\">Novial</a></li>\n" +
            "<li><a href=\"//mhr.wikipedia.org/\" lang=\"mhr\" title=\"Olyk Marij\">Олык Марий</a></li>\n" +
            "<li><a href=\"//as.wikipedia.org/\" lang=\"as\" title=\"Ôxômiya\">অসমীযা়</a></li>\n" +
            "<li><a href=\"//pi.wikipedia.org/\" lang=\"pi\" title=\"Pāḷi\">पाऴि</a></li>\n" +
            "<li><a href=\"//pag.wikipedia.org/\" lang=\"pag\">Pangasinán</a></li>\n" +
            "<li><a href=\"//pap.wikipedia.org/\" lang=\"pap\">Papiamentu</a></li>\n" +
            "<li><a href=\"//ps.wikipedia.org/\" lang=\"ps\" title=\"Paʂto\"><bdi dir=\"rtl\">پښتو</bdi></a></li>\n" +
            "<li><a href=\"//koi.wikipedia.org/\" lang=\"koi\" title=\"Perem Komi\">Перем Коми</a></li>\n" +
            "<li><a href=\"//pfl.wikipedia.org/\" lang=\"pfl\">Pfälzisch</a></li>\n" +
            "<li><a href=\"//pcd.wikipedia.org/\" lang=\"pcd\">Picard</a></li>\n" +
            "<li><a href=\"//krc.wikipedia.org/\" lang=\"krc\" title=\"Qaraçay–Malqar\">Къарачай–Малкъар</a></li>\n" +
            "<li><a href=\"//kaa.wikipedia.org/\" lang=\"kaa\">Qaraqalpaqsha</a></li>\n" +
            "<li><a href=\"//crh.wikipedia.org/\" lang=\"crh\">Qırımtatarca</a></li>\n" +
            "<li><a href=\"//ksh.wikipedia.org/\" lang=\"ksh\">Ripoarisch</a></li>\n" +
            "<li><a href=\"//rm.wikipedia.org/\" lang=\"rm\">Rumantsch</a></li>\n" +
            "<li><a href=\"//rue.wikipedia.org/\" lang=\"rue\" title=\"Rusin’skyj Yazyk\">Русиньскый Язык</a></li>\n" +
            "<li><a href=\"//se.wikipedia.org/\" lang=\"se\">Sámegiella</a></li>\n" +
            "<li><a href=\"//sc.wikipedia.org/\" lang=\"sc\">Sardu</a></li>\n" +
            "<li><a href=\"//stq.wikipedia.org/\" lang=\"stq\">Seeltersk</a></li>\n" +
            "<li><a href=\"//nso.wikipedia.org/\" lang=\"nso\">Sesotho sa Leboa</a></li>\n" +
            "<li><a href=\"//sn.wikipedia.org/\" lang=\"sn\">ChiShona</a></li>\n" +
            "<li><a href=\"//sd.wikipedia.org/\" lang=\"sd\" title=\"Sindhī\"><bdi dir=\"rtl\">سنڌي</bdi></a></li>\n" +
            "<li><a href=\"//szl.wikipedia.org/\" lang=\"szl\">Ślůnski</a></li>\n" +
            "<li><a href=\"//so.wikipedia.org/\" lang=\"so\">Soomaaliga</a></li>\n" +
            "<li><a href=\"//srn.wikipedia.org/\" lang=\"srn\">Sranantongo</a></li>\n" +
            "<li><a href=\"//kab.wikipedia.org/\" lang=\"kab\">Taqbaylit</a></li>\n" +
            "<li><a href=\"//roa-tara.wikipedia.org/\" lang=\"roa\">Tarandíne</a></li>\n" +
            "<li><a href=\"//tet.wikipedia.org/\" lang=\"tet\">Tetun</a></li>\n" +
            "<li><a href=\"//tpi.wikipedia.org/\" lang=\"tpi\">Tok Pisin</a></li>\n" +
            "<li><a href=\"//to.wikipedia.org/\" lang=\"to\">faka Tonga</a></li>\n" +
            "<li><a href=\"//azb.wikipedia.org/\" lang=\"azb\" title=\"Türkce\"><bdi dir=\"rtl\">تۆرکجه</bdi></a></li>\n" +
            "<li><a href=\"//tk.wikipedia.org/\" lang=\"tk\">Türkmençe</a></li>\n" +
            "<li><a href=\"//tyv.wikipedia.org/\" lang=\"tyv\" title=\"Tyva dyl\">Тыва дыл</a></li>\n" +
            "<li><a href=\"//udm.wikipedia.org/\" lang=\"udm\" title=\"Udmurt\">Удмурт</a></li>\n" +
            "<li><a href=\"//ug.wikipedia.org/\" lang=\"ug\"><bdi dir=\"rtl\">ئۇيغۇرچه</bdi></a></li>\n" +
            "<li><a href=\"//vep.wikipedia.org/\" lang=\"vep\">Vepsän</a></li>\n" +
            "<li><a href=\"//fiu-vro.wikipedia.org/\" lang=\"fiu-vro\">Võro</a></li>\n" +
            "<li><a href=\"//vls.wikipedia.org/\" lang=\"vls\">West-Vlams</a></li>\n" +
            "<li><a href=\"//wo.wikipedia.org/\" lang=\"wo\">Wolof</a></li>\n" +
            "<li><a href=\"//wuu.wikipedia.org/\" lang=\"wuu\" title=\"Wú Yǔ\" data-convert-hans=\"吴语\" id=\"wuu_wiki\">吳語</a></li>\n" +
            "<li><a href=\"//diq.wikipedia.org/\" lang=\"diq\">Zazaki</a></li>\n" +
            "<li><a href=\"//zea.wikipedia.org/\" lang=\"zea\">Zeêuws</a></li>\n" +
            "</ul>\n" +
            "</div>\n" +
            "<!-- Bookshelves -->\n" +
            "<h2 class=\"bookshelf-container\">\n" +
            "<span class=\"bookshelf\"><span class=\"sprite-bookshelf_icons sprite-bookshelf_icons-Bookshelf bookend\" style=\"width: 40px /* 1 * 40 */\"></span><span class=\"text\">100+</span><span class=\"sprite-bookshelf_icons sprite-bookshelf_icons-Bookshelf bookend\" style=\"width: 40px\"></span></span>\n" +
            "</h2>\n" +
            "<!-- 100+ content pages -->\n" +
            "<div class=\"langlist langlist-tiny hlist\" data-el-section=\"secondary links\">\n" +
            "<ul>\n" +
            "<li><a href=\"//ak.wikipedia.org/\" lang=\"ak\">Akan</a></li>\n" +
            "<li><a href=\"//ab.wikipedia.org/\" lang=\"ab\" title=\"Aṗsua\">Аҧсуа</a></li>\n" +
            "<li><a href=\"//bm.wikipedia.org/\" lang=\"bm\">Bamanankan</a></li>\n" +
            "<li><a href=\"//bi.wikipedia.org/\" lang=\"bi\">Bislama</a></li>\n" +
            "<li><a href=\"//ch.wikipedia.org/\" lang=\"ch\">Chamoru</a></li>\n" +
            "<li><a href=\"//ny.wikipedia.org/\" lang=\"ny\">Chichewa</a></li>\n" +
            "<li><a href=\"//ee.wikipedia.org/\" lang=\"ee\">Eʋegbe</a></li>\n" +
            "<li><a href=\"//ff.wikipedia.org/\" lang=\"ff\">Fulfulde</a></li>\n" +
            "<li><a href=\"//ki.wikipedia.org/\" lang=\"ki\">Gĩkũyũ</a></li>\n" +
            "<li><a href=\"//got.wikipedia.org/\" lang=\"got\" title=\"Gutisk\">\uD800\uDF32\uD800\uDF3F\uD800\uDF44\uD800\uDF39\uD800\uDF43\uD800\uDF3A</a></li>\n" +
            "<li><a href=\"//iu.wikipedia.org/\" lang=\"iu\"><span lang=\"iu-Cans\">ᐃᓄᒃᑎᑐᑦ</span> / <span lang=\"iu-Latn\">Inuktitut</span></a></li>\n" +
            "<li><a href=\"//ik.wikipedia.org/\" lang=\"ik\">Iñupiak</a></li>\n" +
            "<li><a href=\"//ks.wikipedia.org/\" lang=\"ks\" title=\"Kashmiri\"><bdi dir=\"rtl\">كشميري</bdi></a></li>\n" +
            "<li><a href=\"//ltg.wikipedia.org/\" lang=\"ltg\">Latgaļu</a></li>\n" +
            "<li><a href=\"//lg.wikipedia.org/\" lang=\"lg\">Luganda</a></li>\n" +
            "<li><a href=\"//mo.wikipedia.org/\" lang=\"ro-Cyrl\" title=\"Moldovenească\">Молдовеняскэ</a></li>\n" +
            "<li><a href=\"//fj.wikipedia.org/\" lang=\"fj\">Na Vosa Vaka-Viti</a></li>\n" +
            "<li><a href=\"//pih.wikipedia.org/\" lang=\"pih\">Norfuk / Pitkern</a></li>\n" +
            "<li><a href=\"//om.wikipedia.org/\" lang=\"om\">Afaan Oromoo</a></li>\n" +
            "<li><a href=\"//pnt.wikipedia.org/\" lang=\"pnt\" title=\"Pontiaká\">Ποντιακά</a></li>\n" +
            "<li><a href=\"//dz.wikipedia.org/\" lang=\"dz\" title=\"Rdzong-Kha\">རྫོང་ཁ</a></li>\n" +
            "<li><a href=\"//rmy.wikipedia.org/\" lang=\"rmy\">Romani</a></li>\n" +
            "<li><a href=\"//rn.wikipedia.org/\" lang=\"rn\">Kirundi</a></li>\n" +
            "<li><a href=\"//sm.wikipedia.org/\" lang=\"sm\">Gagana Sāmoa</a></li>\n" +
            "<li><a href=\"//sg.wikipedia.org/\" lang=\"sg\">Sängö</a></li>\n" +
            "<li><a href=\"//st.wikipedia.org/\" lang=\"st\">Sesotho</a></li>\n" +
            "<li><a href=\"//tn.wikipedia.org/\" lang=\"tn\">Setswana</a></li>\n" +
            "<li><a href=\"//cu.wikipedia.org/\" lang=\"cu\" title=\"Slověnĭskŭ\"><span lang=\"cu-Cyrl\">Словѣ́ньскъ</span> / <span lang=\"cu-Glag\">ⰔⰎⰑⰂⰡⰐⰠⰔⰍⰟ</span></a></li>\n" +
            "<li><a href=\"//ss.wikipedia.org/\" lang=\"ss\">SiSwati</a></li>\n" +
            "<li><a href=\"//ti.wikipedia.org/\" lang=\"ti\" title=\"Tigriññā\">ትግርኛ</a></li>\n" +
            "<li><a href=\"//chr.wikipedia.org/\" lang=\"chr\" title=\"Tsalagi\">ᏣᎳᎩ</a></li>\n" +
            "<li><a href=\"//chy.wikipedia.org/\" lang=\"chy\">Tsėhesenėstsestotse</a></li>\n" +
            "<li><a href=\"//ve.wikipedia.org/\" lang=\"ve\">Tshivenḓa</a></li>\n" +
            "<li><a href=\"//ts.wikipedia.org/\" lang=\"ts\">Xitsonga</a></li>\n" +
            "<li><a href=\"//tum.wikipedia.org/\" lang=\"tum\">chiTumbuka</a></li>\n" +
            "<li><a href=\"//tw.wikipedia.org/\" lang=\"tw\">Twi</a></li>\n" +
            "<li><a href=\"//xh.wikipedia.org/\" lang=\"xh\">isiXhosa</a></li>\n" +
            "<li><a href=\"//zu.wikipedia.org/\" lang=\"zu\">isiZulu</a></li>\n" +
            "</ul>\n" +
            "</div>\n" +
            "<!-- Other languages -->\n" +
            "<div class=\"langlist langlist-others hlist\" data-el-section=\"other languages\">\n" +
            "<ul>\n" +
            "<li><a href=\"//meta.wikimedia.org/wiki/List_of_Wikipedias\" lang=\"en\">Other languages</a></li>\n" +
            "<li><a href=\"//de.wikipedia.org/wiki/Wikipedia:Sprachen\" lang=\"de\">Weitere Sprachen</a></li>\n" +
            "<li><a href=\"//meta.wikimedia.org/wiki/Liste_des_Wikip%C3%A9dias\" lang=\"fr\">Autres langues</a></li>\n" +
            "<li><a href=\"//pl.wikipedia.org/wiki/Wikipedia:Lista_wersji_j%C4%99zykowych\" lang=\"pl\">Kompletna lista języków</a></li>\n" +
            "<li><a href=\"//ja.wikipedia.org/wiki/Wikipedia:%E5%A4%9A%E8%A8%80%E8%AA%9E%E3%83%97%E3%83%AD%E3%82%B8%E3%82%A7%E3%82%AF%E3%83%88%E3%81%A8%E3%81%97%E3%81%A6%E3%81%AE%E3%82%A6%E3%82%A3%E3%82%AD%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2\" lang=\"ja\">他の言語</a></li>\n" +
            "<li><a href=\"//meta.wikimedia.org/wiki/Lista_de_Wikipedias\" lang=\"es\">Otros idiomas</a></li>\n" +
            "<li><a href=\"//zh.wikipedia.org/wiki/Wikipedia:%E7%BB%B4%E5%9F%BA%E7%99%BE%E7%A7%91%E8%AF%AD%E8%A8%80%E5%88%97%E8%A1%A8\" lang=\"zh\" data-convert-hans=\"其他语言\" id=\"zh_others\">其他語言</a></li>\n" +
            "<li><a href=\"//ru.wikipedia.org/wiki/%D0%92%D0%B8%D0%BA%D0%B8%D0%BF%D0%B5%D0%B4%D0%B8%D1%8F:%D0%A1%D0%92\" lang=\"ru\">Другие языки</a></li>\n" +
            "<li><a href=\"//eo.wikipedia.org/wiki/Vikipedio:Internacia_Vikipedio\" lang=\"eo\">Aliaj lingvoj</a></li>\n" +
            "<li><a href=\"//meta.wikimedia.org/wiki/%EC%9C%84%ED%82%A4%EB%B0%B1%EA%B3%BC%EC%9D%98_%EB%AA%A9%EB%A1%9D\" lang=\"ko\">다른 언어</a></li>\n" +
            "<li><a href=\"//vi.wikipedia.org/wiki/Wikipedia:Phi%C3%AAn_b%E1%BA%A3n_ng%C3%B4n_ng%E1%BB%AF\" lang=\"vi\">Ngôn ngữ khác</a></li>\n" +
            "</ul>\n" +
            "</div>\n" +
            "<hr>\n" +
            "<!-- Other projects -->\n" +
            "<div class=\"otherprojects\" data-el-section=\"other projects\">\n" +
            "<div class=\"otherprojects-item\">\n" +
            "<a href=\"//www.wiktionary.org/\">\n" +
            "<span class=\"sprite-project-logos-wrapper\">\n" +
            "<span class=\"sprite-project-logos sprite-project-logos-Wiktionary-logo_sister\"></span>\n" +
            "</span>\n" +
            "<span class=\"otherprojects-item-name\">Wiktionary</span>\n" +
            "<!--\n" +
            "                    <img src=\"portal/wikipedia.org/assets/img/Wiktionary-logo_sister\" srcset=\"portal/wikipedia.org/assets/img/ 1.5x, portal/wikipedia.org/assets/img/ 2x\" width=\"35\" height=\"35\" alt=\"\"></span> Wiktionary</a>\n" +
            "                -->\n" +
            "</a></div>\n" +
            "<div class=\"otherprojects-item\">\n" +
            "<a href=\"//www.wikinews.org/\">\n" +
            "<span class=\"sprite-project-logos-wrapper\">\n" +
            "<span class=\"sprite-project-logos sprite-project-logos-Wikinews-logo_sister\"></span>\n" +
            "</span>\n" +
            "<span class=\"otherprojects-item-name\">Wikinews</span>\n" +
            "<!--\n" +
            "                    <img src=\"portal/wikipedia.org/assets/img/Wikinews-logo_sister\" srcset=\"portal/wikipedia.org/assets/img/ 1.5x, portal/wikipedia.org/assets/img/ 2x\" width=\"47\" height=\"24\" alt=\"\"></span> Wikinews</a>\n" +
            "                -->\n" +
            "</a></div>\n" +
            "<div class=\"otherprojects-item\">\n" +
            "<a href=\"//www.wikiquote.org/\">\n" +
            "<span class=\"sprite-project-logos-wrapper\">\n" +
            "<span class=\"sprite-project-logos sprite-project-logos-Wikiquote-logo_sister\"></span>\n" +
            "</span>\n" +
            "<span class=\"otherprojects-item-name\">Wikiquote</span>\n" +
            "<!--\n" +
            "                    <img src=\"portal/wikipedia.org/assets/img/Wikiquote-logo_sister\" srcset=\"portal/wikipedia.org/assets/img/ 1.5x, portal/wikipedia.org/assets/img/ 2x\" width=\"35\" height=\"41\" alt=\"\"></span> Wikiquote</a>\n" +
            "                -->\n" +
            "</a></div>\n" +
            "<div class=\"otherprojects-item\">\n" +
            "<a href=\"//www.wikibooks.org/\">\n" +
            "<span class=\"sprite-project-logos-wrapper\">\n" +
            "<span class=\"sprite-project-logos sprite-project-logos-Wikibooks-logo_sister\"></span>\n" +
            "</span>\n" +
            "<span class=\"otherprojects-item-name\">Wikibooks</span>\n" +
            "<!--\n" +
            "                    <img src=\"portal/wikipedia.org/assets/img/Wikibooks-logo_sister\" srcset=\"portal/wikipedia.org/assets/img/ 1.5x, portal/wikipedia.org/assets/img/ 2x\" width=\"35\" height=\"35\" alt=\"\"></span> Wikibooks</a>\n" +
            "                -->\n" +
            "</a></div>\n" +
            "<div class=\"otherprojects-item\">\n" +
            "<a href=\"//www.wikidata.org/\">\n" +
            "<span class=\"sprite-project-logos-wrapper\">\n" +
            "<span class=\"sprite-project-logos sprite-project-logos-Wikidata-logo_sister\"></span>\n" +
            "</span>\n" +
            "<span class=\"otherprojects-item-name\">Wikidata</span>\n" +
            "<!--\n" +
            "                    <img src=\"portal/wikipedia.org/assets/img/Wikidata-logo_sister\" srcset=\"portal/wikipedia.org/assets/img/ 1.5x, portal/wikipedia.org/assets/img/ 2x\" width=\"47\" height=\"26\" alt=\"\"></span> Wikidata</a>\n" +
            "                -->\n" +
            "</a></div>\n" +
            "<div class=\"otherprojects-item\">\n" +
            "<a href=\"//species.wikimedia.org/\">\n" +
            "<span class=\"sprite-project-logos-wrapper\">\n" +
            "<span class=\"sprite-project-logos sprite-project-logos-Wikispecies-logo_sister\"></span>\n" +
            "</span>\n" +
            "<span class=\"otherprojects-item-name\">Wikispecies</span>\n" +
            "<!--\n" +
            "                    <img src=\"portal/wikipedia.org/assets/img/Wikispecies-logo_sister\" srcset=\"portal/wikipedia.org/assets/img/ 1.5x, portal/wikipedia.org/assets/img/ 2x\" width=\"35\" height=\"41\" alt=\"\"></span> Wikispecies</a>\n" +
            "                -->\n" +
            "</a></div>\n" +
            "<div class=\"otherprojects-item\">\n" +
            "<a href=\"//www.wikisource.org/\">\n" +
            "<span class=\"sprite-project-logos-wrapper\">\n" +
            "<span class=\"sprite-project-logos sprite-project-logos-Wikisource-logo_sister\"></span>\n" +
            "</span>\n" +
            "<span class=\"otherprojects-item-name\">Wikisource</span>\n" +
            "<!--\n" +
            "                    <img src=\"portal/wikipedia.org/assets/img/Wikisource-logo_sister\" srcset=\"portal/wikipedia.org/assets/img/ 1.5x, portal/wikipedia.org/assets/img/ 2x\" width=\"35\" height=\"37\" alt=\"\"></span> Wikisource</a>\n" +
            "                -->\n" +
            "</a></div>\n" +
            "<div class=\"otherprojects-item\">\n" +
            "<a href=\"//www.wikiversity.org/\">\n" +
            "<span class=\"sprite-project-logos-wrapper\">\n" +
            "<span class=\"sprite-project-logos sprite-project-logos-Wikiversity-logo_sister\"></span>\n" +
            "</span>\n" +
            "<span class=\"otherprojects-item-name\">Wikiversity</span>\n" +
            "<!--\n" +
            "                    <img src=\"portal/wikipedia.org/assets/img/Wikiversity-logo_sister\" srcset=\"portal/wikipedia.org/assets/img/ 1.5x, portal/wikipedia.org/assets/img/ 2x\" width=\"35\" height=\"28\" alt=\"\"></span> Wikiversity</a>\n" +
            "                -->\n" +
            "</a></div>\n" +
            "<div class=\"otherprojects-item\">\n" +
            "<a href=\"//www.wikivoyage.org/\">\n" +
            "<span class=\"sprite-project-logos-wrapper\">\n" +
            "<span class=\"sprite-project-logos sprite-project-logos-Wikivoyage-logo_sister\"></span>\n" +
            "</span>\n" +
            "<span class=\"otherprojects-item-name\">Wikivoyage</span>\n" +
            "<!--\n" +
            "                    <img src=\"portal/wikipedia.org/assets/img/Wikivoyage-logo_sister\" srcset=\"portal/wikipedia.org/assets/img/ 1.5x, portal/wikipedia.org/assets/img/ 2x\" width=\"35\" height=\"35\" alt=\"\"></span> Wikivoyage</a>\n" +
            "                -->\n" +
            "</a></div>\n" +
            "<div class=\"otherprojects-item\">\n" +
            "<a href=\"//commons.wikimedia.org/\">\n" +
            "<span class=\"sprite-project-logos-wrapper\">\n" +
            "<span class=\"sprite-project-logos sprite-project-logos-Commons-logo_sister\" style=\"vertical-align: top\"></span>\n" +
            "</span>\n" +
            "<span class=\"otherprojects-item-name\">Commons</span>\n" +
            "<!--\n" +
            "                    <img src=\"portal/wikipedia.org/assets/img/Commons-logo_sister\" srcset=\"portal/wikipedia.org/assets/img/ 1.5x, portal/wikipedia.org/assets/img/ 2x\" width=\"35\" height=\"47\" alt=\"\" style=\"vertical-align: top;\"></span> Commons</a>\n" +
            "                -->\n" +
            "</a></div>\n" +
            "<div class=\"otherprojects-item\">\n" +
            "<a href=\"//www.mediawiki.org/\">\n" +
            "<span class=\"sprite-project-logos-wrapper\">\n" +
            "<span class=\"sprite-project-logos sprite-project-logos-MediaWiki-logo_sister\"></span>\n" +
            "</span>\n" +
            "<span class=\"otherprojects-item-name\">MediaWiki</span>\n" +
            "<!--\n" +
            "                    <img src=\"portal/wikipedia.org/assets/img/MediaWiki-logo_sister\" srcset=\"portal/wikipedia.org/assets/img/ 1.5x, portal/wikipedia.org/assets/img/ 2x\" width=\"47\" height=\"36\" alt=\"\"></span> MediaWiki</a>\n" +
            "                -->\n" +
            "</a></div>\n" +
            "<div class=\"otherprojects-item\">\n" +
            "<a href=\"//meta.wikimedia.org/\">\n" +
            "<span class=\"sprite-project-logos-wrapper\">\n" +
            "<span class=\"sprite-project-logos sprite-project-logos-Meta-logo_sister\"></span>\n" +
            "</span>\n" +
            "<span class=\"otherprojects-item-name\">Meta-Wiki</span>\n" +
            "<!--\n" +
            "                    <img src=\"portal/wikipedia.org/assets/img/Meta-logo_sister\" srcset=\"portal/wikipedia.org/assets/img/ 1.5x, portal/wikipedia.org/assets/img/ 2x\" width=\"35\" height=\"35\" alt=\"\"></span> Meta-Wiki</a>\n" +
            "                -->\n" +
            "</a></div>\n" +
            "</div>\n" +
            "<!-- Site info -->\n" +
            "<div class=\"wm-site-info\">\n" +
            "<a href=\"//wikimediafoundation.org/\">\n" +
            "<span class=\"sprite-project-logos sprite-project-logos-wikimedia-button\" style=\"display:inline-block;vertical-align:middle\"></span>\n" +
            "<!--<img src=\"portal/wikipedia.org/assets/img/wikimedia-button.png\" srcset=\"portal/wikipedia.org/assets/img/A_Wikimedia_project_1.5x.png 1.5x, portal/wikipedia.org/assets/img/A_Wikimedia_project_2x.png 2x\" width=\"88\" height=\"31\" alt=\"A Wikimedia Project\"> -->\n" +
            "</a>\n" +
            "</div>\n" +
            "<div style=\"text-align:center\"><a href=\"//wikimediafoundation.org/wiki/Terms_of_Use\">Terms of Use</a> | <a href=\"//wikimediafoundation.org/wiki/Privacy_policy\">Privacy Policy</a></div>\n" +
            "<script src=\"portal/wikipedia.org/assets/js/index-7203873ab7.js\"></script>\n" +
            "</body>\n" +
            "</html>";

    @Test
//    @Repeat(value = 10)
    public void classifierSimpleTest() {
        Set<String> webCategories = null;
        try {
            webCategories = classifierSyncClient.get(new URI("https://www.punkspider.org"), helloWorld);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        Assert.isTrue(webCategories.isEmpty());
        System.out.println(webCategories);
    }

    @Test
    public void classifiersmallPsTest() {
        Set<String> webCategories = null;
        try {
            webCategories = classifierSyncClient.get(new URI("https://www.punkspider.org"), mediumPs);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        Assert.isTrue(webCategories.isEmpty());
        System.out.println(webCategories);
    }

    @Test
    public void classifierPsTest() {
        Set<String> webCategories = null;
        try {
            webCategories = classifierSyncClient.get(new URI("https://www.punkspider.org"), rawHtml);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        Assert.isTrue(webCategories.isEmpty());
        System.out.println(webCategories);
    }

    @Test
    public void classifierCochesNetTest() {
        Set<String> webCategories = null;
        try {
            webCategories = classifierSyncClient.get(new URI("https://www.punkspider.org"), cochesnet);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        Assert.isTrue(webCategories.contains("SHOPPING"));
        System.out.println(webCategories);
    }

    @Test
    public void classifierWikipediaTest() {
        Set<String> webCategories = null;
        try {
            webCategories = classifierSyncClient.get(new URI("https://www.punkspider.org"), wikipedia);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        Assert.isTrue(webCategories.contains("WIKI"));
        System.out.println(webCategories);
    }


    @Test
    public void classifierEbayTest() {
        Set<String> webCategories = null;
        try {
            webCategories = classifierSyncClient.get(new URI("https://www.punkspider.org"), ClassifierHelper.EBAY_HTML_PART_1);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        Assert.isTrue(webCategories.contains("news"));
        System.out.println(webCategories);
    }


}