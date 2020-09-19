package com.allendowney.thinkdast;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikiFetcher {

	private long lastRequestTime = -1;
	private long minInterval = 1000;
	
	public Elements fetchWikipedia(String url) throws IOException {
		sleepIfNeeded(); // 바로 앞 요청이후 경과 시간을 검사하여 그 간격이 minInterval(단위는 밀리초) 미만이면 동작을 지연한다
		
		Connection conn = Jsoup.connect(url);
		Document doc = conn.get();
		
		Element content = doc.getElementById("mw-content-text");
		
		Elements paras = content.select("a");
		return paras;
	}

	private void sleepIfNeeded() {
		if(lastRequestTime != -1) {
			long currentTime = System.currentTimeMillis();
			long nextRequestTime = lastRequestTime + minInterval;
			if(currentTime < nextRequestTime) {
				try {
					System.out.println("Sleeping until : " + nextRequestTime);
					Thread.sleep(nextRequestTime - currentTime);
				} catch(InterruptedException e) {
					System.err.println("Warning: sleep interrupted in fetchWikipedia.");
				}
			}
		}
		lastRequestTime = System.currentTimeMillis();
	}
}
