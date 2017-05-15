/**
 * 
 */
package com.crawler.buildit;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * @author adhir
 *
 */
public class WebPage {
	
	private URL pageURL;
	private Set<WebPage> internalLinks;
	private Set<URL> externalLinks;
	private Set<URL> resources;
	private int depth;
	
	public int getDepth() {
		return depth;
	}
	public WebPage(URL pageURL, int depth) {
		super();
		this.pageURL = pageURL;
		this.depth = depth;
		this.externalLinks = new HashSet<URL>();
		this.internalLinks = new HashSet<WebPage>();
		this.resources = new HashSet<URL>();
	}
	public URL getPageURL() {
		return pageURL;
	}
	public void setPageURL(URL pageURL) {
		this.pageURL = pageURL;
	}
	public Set<WebPage> getInternalLinks() {
		return internalLinks;
	}
	public Set<URL> getExternalLinks() {
		return externalLinks;
	}
	
	public Set<URL> getResources() {
		return resources;
	}
	
	public void addExternalLink(URL url) {
		this.externalLinks.add(url);
	}
	
	public void addInternalLink(WebPage page) {
		this.internalLinks.add(page);
	}
	
	public void addResources(URL url) {
		this.resources.add(url);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pageURL == null) ? 0 : pageURL.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WebPage other = (WebPage) obj;
		if (pageURL == null) {
			if (other.getPageURL() != null)
				return false;
		} else if (!pageURL.equals(other.getPageURL()))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(padSpaces(this.depth)).append("START OF DATA for ").append(this.getPageURL().toString()).append("\n");

		sb.append(padSpaces(this.depth)).append("External Links\n");
		sb.append(padSpaces(this.depth)).append("==============\n");
		
		for (URL url : externalLinks) {
			sb.append(padSpaces(this.depth)).append(url.toString()).append("\n");
		}
		sb.append(padSpaces(this.depth)).append("Resources\n");
		sb.append(padSpaces(this.depth)).append("=========\n");
		for (URL url : resources) {
			sb.append(padSpaces(this.depth)).append(url.toString()).append("\n");
		}
		sb.append(padSpaces(this.depth)).append("Internal Links\n");
		sb.append(padSpaces(this.depth)).append("==============\n");
		for (WebPage page : internalLinks) {
			sb.append(page.toString()).append("\n");
		}
		sb.append(padSpaces(this.depth)).append("END OF DATA for " + this.pageURL.toString());
		return sb.toString();
	}
	
	private static String padSpaces(int depth) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < depth * 4; i++) {
			sb.append(" ");
		}
		return sb.toString();
	}

}
