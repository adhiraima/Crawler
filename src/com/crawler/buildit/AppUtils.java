/**
 * 
 */
package com.crawler.buildit;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author adhir
 *
 */
public class AppUtils {
	
	public static void getLinks(WebPage page, String string) {
		int currIndex = 0;
		while (-1 != currIndex || currIndex <= string.length()) {
			currIndex = string.indexOf(ApplicationConstants.HREF_MARKER, currIndex);
			if (-1 == currIndex) return;
			if (currIndex + 6 > string.length() || string.indexOf("\"", currIndex + 6) < 0) return;
			String href = string.substring(currIndex + 6, string.indexOf("\"", currIndex + 6));
			currIndex = currIndex + 6 + href.length();
			if (currIndex > string.length()) return;
			//weed out the unnecessary captures
			if (href.matches(ApplicationConstants.HASHTAG_BEGINNING) 
					|| href.matches(ApplicationConstants.TAG_BEGINNING)
					|| href.contains(ApplicationConstants.EMBED_TAG)) {
				continue;
			}
			href = fineTuneLink(href);
			//System.out.println("the href is: " + href);
			classifyLinks(page, href);
		}
	}
	
	private static String fineTuneLink(String url) {
		//this method removes the bookmark links and cleaup dirty urls
		if (url.endsWith("/")) {
			url = url.substring(0, url.lastIndexOf("/"));
		}
		if (url.contains(ApplicationConstants.HASHTAG)) {
			url = url.substring(0, url.indexOf(ApplicationConstants.HASHTAG));
		}
		if (url.contains(ApplicationConstants.QUESTION_MARK)) {
			url = url.substring(0, url.indexOf(ApplicationConstants.QUESTION_MARK));
		}
		if (url.contains(ApplicationConstants.SINGLE_QUOTE)) {
			url = url.substring(0, url.indexOf(ApplicationConstants.SINGLE_QUOTE));
		}
		if (url.contains(ApplicationConstants.SEMI_COLON)) {
			url = url.substring(0, url.indexOf(ApplicationConstants.SEMI_COLON));
		}
		if (url.contains(ApplicationConstants.COMMA)) {
			url = url.substring(0, url.indexOf(ApplicationConstants.COMMA));
		}
		if (url.contains(ApplicationConstants.LESS_THAN)) {
			url = url.substring(0, url.indexOf(ApplicationConstants.LESS_THAN));
		}
		return url;
	}
	
	private static void classifyLinks(WebPage page, String link) {
		URL url = null;
		try {
			url = new URL(link);
		} catch (MalformedURLException e) {
			return;
		}
		//check for static content before hand 
		if (link.endsWith("xml") 
				|| link.endsWith("bmp") 
				|| link.endsWith("png") 
				|| link.endsWith("css") 
				|| link.endsWith("js")) {
			page.addResources(url);
		} else if (url.getHost().equalsIgnoreCase(page.getPageURL().getHost())) {
			//internal link
			WebPage subPage = new WebPage(url, page.getDepth() + 1);
			page.addInternalLink(subPage);
		} else {
			page.addExternalLink(url);
		}
	}

}
