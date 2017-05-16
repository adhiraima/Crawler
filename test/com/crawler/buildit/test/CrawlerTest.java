/**
 * 
 */
package com.crawler.buildit.test;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.crawler.buildit.AppUtils;
import com.crawler.buildit.WebPage;

/**
 * @author adhir
 *
 */
public class CrawlerTest {

	/**
	 * @throws java.lang.Exception
	 */
	
	private String testHTML = "<html>lang=\"en-US\"id=\"arve\"prefix=\"og:http://ogp.me/ns#\"class=\"no-js\">"
			+ "<head><bodyclass=\"homepagepage-id-17page-templatepage-template-page-wdhomepage-template-page-wdhome-php_masterslider_msp_version_2.29.0\">"
			+ "<navclass=\"wd-navbar-containernavbarnavbar-defaultnavbar-fixed-top\"><divclass=\"container\">"
			+ "<divclass=\"wd-navbar-headernavbar-header\"><buttontype=\"button\"class=\"navbar-toggle\"data-toggle=\"collapse\"data-target=\"#wdNavbar\">"
			+ "<spanclass=\"icon-bar\"></span><spanclass=\"icon-bar\"></span><spanclass=\"icon-bar\"></span></button>"
			+ "<aclass=\"wd-navbar-brandnavbar-brand\"href=\"http://wiprodigital.com\"><imgclass=\"wd-navlogo-digital\""
			+ "src=\"http://17776-presscdn-0-6.pagely.netdna-cdn.com/wp-content/themes/wiprodigital/images/wdlogo.png\""
			+ "alt=\"wiprodigital\"></a></div><divclass=\"collapsenavbar-collapse\"id=\"wdNavbar\">"
			+ "<ulclass=\"navnavbar-navnavbar-rightwd-navbar-nav\"><liclass=\"wd-navbar-nav-elemdropdown\">"
			+ "<aclass=\"wd-navbar-nav-elem-linkwd-nav-elem-link\"href=\"http://wiprodigital.com/who-we-are\">Whoweare</a>"
			+ "<ulclass=\"wd-navbar-nav-elem-ddmenudropdown-menu\"><li><aclass=\"wd-navbar-nav-elem-linkwd-nav-elem-link\""
			+ "href=\"http://wiprodigital.com/who-we-are\">Ourstory</a></li><li><aclass=\"wd-navbar-nav-elem-linkwd-nav-elem-link\""
			+ "href=\"http://wiprodigital.com/who-we-are#wdteam_meetus\">Meetus</a></li><li><aclass=\"wd-navbar-nav-elem-linkwd-nav-elem-link\""
			+ "href=\"http://wiprodigital.com/who-we-are#wdteam_leaders\">Leaders</a></li></ul></li><liclass=\"wd-navbar-nav-elem\">"
			+ "<aclass=\"wd-navbar-nav-elem-linkwd-nav-elem-link\"href=\"http://wiprodigital.com/what-we-do\">Whatwedo</a>"
			+ "<ulclass=\"wd-navbar-nav-elem-ddmenudropdown-menu\"><li><aclass=\"wd-navbar-nav-elem-linkwd-nav-elem-link\"href=\"http://wiprodigital.com/what-we-do#work-three-circles-row\">Services</a>"
			+ "</li><li><aclass=\"wd-navbar-nav-elem-linkwd-nav-elem-link\"href=\"http://wiprodigital.com/what-we-do#wdwork_cases\">Casestudies</a>"
			+ "</li><li><aclass=\"wd-navbar-nav-elem-linkwd-nav-elem-link\"href=\"http://wiprodigital.com/what-we-do#wdwork_partners\">Partners</a>"
			+ "</li></ul></li><liclass=\"wd-navbar-nav-elem\"><aclass=\"wd-navbar-nav-elem-linkwd-nav-elem-link\"href=\"http://wiprodigital.com/what-we-think\">Whatwethink</a>"
			+ "<ulclass=\"wd-navbar-nav-elem-ddmenudropdown-menu\"><li><aclass=\"wd-navbar-nav-elem-linkwd-nav-elem-link\"href=\"http://www.linkedin.com/wiprodigital\">Insights</a>"
			+ "</li><li><aclass=\"wd-navbar-nav-elem-linkwd-nav-elem-link\"href=\"http://wiprodigital.com/?s=&post_type[]=cases\">Cases</a>"
			+ "</li><li><aclass=\"wd-navbar-nav-elem-linkwd-nav-elem-link\"href=\"http://wiprodigital.com/?s=&post_type[]=events\">Events</a>"
			+ "</li><li><aclass=\"wd-navbar-nav-elem-linkwd-nav-elem-link\"href=\"http://wiprodigital.com/?s=&post_type[]=news\">News</a>"
			+ "</li></ul></li><liclass=\"wd-navbar-nav-elem\"><aclass=\"wd-navbar-nav-elem-linkwd-nav-elem-link\"href=\"http://wiprodigital.com/join-our-team\">Joinourteam</a>"
			+ "<ulclass=\"wd-navbar-nav-elem-ddmenudropdown-menu\"><li><aclass=\"wd-navbar-nav-elem-linkwd-nav-elem-link\"href=\"http://wiprodigital.com/join-our-team\">Bettertogether</a>"
			+ "</li><li><aclass=\"wd-navbar-nav-elem-linkwd-nav-elem-link\"href=\"http://wiprodigital.com/join-our-team#wdcareers_team\">Ourteams</a></li><li><aclass=\"wd-navbar-nav-elem-linkwd-nav-elem-link\"href=\"http://wiprodigital.com/join-our-team#wdcareers_jobs\">Openpositions</a>"
			+ "</li></ul></li><liclass=\"wd-navbar-nav-elem\"><aclass=\"wd-navbar-nav-elem-linkwd-nav-elem-link\"href=\"http://wiprodigital.com/get-in-touch\">Getintouch</a><ulclass=\"wd-navbar-nav-elem-ddmenudropdown-menu\"><li>"
			+ "<aclass=\"wd-navbar-nav-elem-linkwd-nav-elem-link\"href=\"http://wiprodigital.com/get-in-touch#wddi-locations\">Locations</a></li><li><aclass=\"wd-navbar-nav-elem-linkwd-nav-elem-link\"href=\"http://wiprodigital.com/get-in-touch#wddi-contact\">Contactus"
			+ "</a></li></ul></li></ul></div></div></nav></body></html>";
	private WebPage page;
	
	@Before
	public void setUp() throws Exception {
		page = new WebPage(new URL("http://wiprodigital.com"), 0);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getLinksTest() {
		AppUtils.getLinks(page, testHTML);
		assertEquals(1, page.getExternalLinks().size());
		assertEquals(6, page.getInternalLinks().size());
	}

}
