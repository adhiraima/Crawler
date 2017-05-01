/**
 * 
 */
package com.crawler.buildit;

/**
 * @author adhir
 *
 */
public interface ApplicationConstants {
	public static final int BUFFER_SIZE = 1024 * 5;
	public static final String SRC_MARKER = "src=\"";
	public static final String HREF_MARKER = "href=\"";
	public static final String HASHTAG_BEGINNING = "^#[A-Za-z]+";
	public static final String TAG_BEGINNING = "^[<][A-Za-z]+";
	public static final String TAG_CLOSING = "[<][/]";
	public static final String SPL_CHARS = "['\";:]";
	public static final String HASHTAG = "#";
	public static final String COMMA = ",";
	public static final String SINGLE_QUOTE = "'";
	public static final String SEMI_COLON = ";";
	public static final String QUESTION_MARK = "?";
	public static final String LESS_THAN = "<";
	public static final String HTTP_TAG = "[h][t][t][p][:][/][/]";
	public static final String HTML_COMMENT_OPEN = "[<][!][-][-]";
	public static final String HTML_COMMENT_CLOSE = "[-][-][>]";
	public static final String EMBED_TAG = "embed";
	
}
