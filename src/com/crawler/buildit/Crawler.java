/**
 * 
 */
package com.crawler.buildit;

import java.io.BufferedInputStream;
import java.io.IOException;
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
			AppUtils.getLinks(page, sb.toString());
			for (WebPage sub : page.getInternalLinks()) {
				getPageData(sub);
			}
		}
	}
}
