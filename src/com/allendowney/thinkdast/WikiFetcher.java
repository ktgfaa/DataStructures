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
	
	// 싱글톤 패턴 이용
	private WikiFetcher() {};
	
    private static class InnerInstanceClazz {
        private static final WikiFetcher instance = new WikiFetcher();
    }
    
    public static WikiFetcher getInstance() {
        return InnerInstanceClazz.instance;
    }
	
	public Elements fetchWikipedia(String url) throws IOException {
		sleepIfNeeded(); // 바로 앞 요청이후 경과 시간을 검사하여 그 간격이 minInterval(단위는 밀리초) 미만이면 동작을 지연한다
		
		Connection conn = Jsoup.connect(url); // 웹서버 접속
		Document doc = conn.get(); // HTML을 다운로드하여 파싱하여 Document(DOM트리) 객체를 반환		
		// Document 객체는 트리를 탐색하고 노드를 선택하는 메소드 제공
		
		// 내용을 선택하고 단락 추출하기
		Element content = doc.getElementById("mw-content-text");
		Elements paras = content.select("p");
		
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
