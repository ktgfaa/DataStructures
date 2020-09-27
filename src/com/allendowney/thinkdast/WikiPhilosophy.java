package com.allendowney.thinkdast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikiPhilosophy {

    final static List<String> visited = new ArrayList<String>();
    final static WikiFetcher wf = WikiFetcher.getInstance();

    /**
     * Tests a conjecture about Wikipedia and Philosophy.
     *
     * https://en.wikipedia.org/wiki/Wikipedia:Getting_to_Philosophy
     *
     * 1. Clicking on the first non-parenthesized, non-italicized link
     * 2. Ignoring external links, links to the current page, or red links
     * 3. Stopping when reaching "Philosophy", a page with no links or a page
     *    that does not exist, or when a loop occurs
	 *
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		String destination = "https://en.wikipedia.org/wiki/Philosophy";
		String source = "https://en.wikipedia.org/wiki/Java_(programming_language)";
		
		testConjecture(destination, source, 10);		
	}

	/**
	 * Starts from given URL and follows first link until it finds the destination or exceeds the limit.
	 * 
	 * @param destination
	 * @param source
	 * @throws IOException
	 */
	public static void testConjecture(String destination, String source, int limit) throws IOException {
		String url = source;
		for (int i=0; i<limit; i++) {
			// 방문한 리스트중에 포함되어있으면 중지
			if (visited.contains(url)) {
				System.err.println("We're in a loop, exiting.");
				return;
			// 방문한 리스트중에 없으면 리스트에 추가
			} else {
				visited.add(url);
			}
			
			Element elt = getFirstValidLink(url);
			
			// elt가 null이면 유효한 페이지가 아니므로 종료
			if (elt == null) {
				System.err.println("Got to a page with no valid links.");
				return;
			}
			
			System.out.println("**" + elt.text() + "**");
			url = elt.attr("abs:href"); // a태그의 절대주소를 반환
			
			if (url.equals(destination)) {
				System.out.println("Eureka!");
				break;
			}
		}
	}

	/**
	 * Loads and parses a URL, then extracts the first link.
	 * 
	 * @param url
	 * @return the Element of the first link, or null.
	 * @throws IOException
	 */
	public static Element getFirstValidLink(String url) throws IOException {
		print("Fetching %s...", url);
		Elements paragraphs = wf.fetchWikipedia(url);
		WikiParser wp = new WikiParser(paragraphs);
		Element elt = wp.findFirstLink();
		return elt;
	}

	/**
	 * Formats and print the arguments.
	 * 
	 * @param msg
	 * @param args
	 */
	private static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}
}