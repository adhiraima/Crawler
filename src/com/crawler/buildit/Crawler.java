/**
 * 
 */
package com.crawler.buildit;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author adhir
 *
 */
public class Crawler {
	/**
	 * @param args
	 * @throws IOException 
	 */
	private static Map<String, Boolean> pages = new HashMap<String, Boolean>();

	
	public static void main(String[] args) throws IOException {
		URL root = new URL("http://wiprodigital.com");
		WebPage rootPage = new WebPage(root, 0);
		getPageData(rootPage);
		System.out.println(rootPage);
		
	}
	
	private static void getPageData(WebPage page) {
		System.out.println("Processing Page: " + page.getPageURL().toString());
		if (pages.containsKey(page.getPageURL().toString()) && pages.get(page.getPageURL().toString())) {
			return;
		} else {
			if (page.getPageURL() == null) return;
			pages.put(page.getPageURL().toString(), Boolean.TRUE);
			byte[] dataBuffer = new byte[ApplicationConstants.BUFFER_SIZE];
			int bytesRead = 0;
			StringBuilder sb = new StringBuilder();
			try {
				URLConnection connection = page.getPageURL().openConnection();
				BufferedInputStream bStream = new BufferedInputStream(connection.getInputStream());
				
				do {
					bytesRead = bStream.read(dataBuffer, 0,ApplicationConstants. BUFFER_SIZE);
					String inter = new String(dataBuffer);
					sb.append(inter);
					inter = null;
				} while(bytesRead > 0);
			} catch (IOException e) {
				//do nothing; absorb
			} catch (IllegalArgumentException iae) {
				//do nothing; absorb
			}
			getLinks(page, sb.toString());
			for (WebPage sub : page.getInternalLinks()) {
				getPageData(sub);
			}
		}
	}
	
	private static void getLinks(WebPage page, String string) {
		int currIndex = 0;
		while (currIndex != -1 || currIndex <= string.length()) {
			currIndex = string.indexOf(ApplicationConstants.HREF_MARKER, currIndex);
			if (currIndex == -1) return;
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
		//this method removes the bookmark links and cleans up dirty urls
		if (url.endsWith("/")) {
			url = url.substring(0, url.length() - 1);
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
