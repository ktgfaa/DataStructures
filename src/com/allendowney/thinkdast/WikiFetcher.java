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
	
	// �̱��� ���� �̿�
	private WikiFetcher() {};
	
    private static class InnerInstanceClazz {
        private static final WikiFetcher instance = new WikiFetcher();
    }
    
    public static WikiFetcher getInstance() {
        return InnerInstanceClazz.instance;
    }
	
	public Elements fetchWikipedia(String url) throws IOException {
		sleepIfNeeded(); // �ٷ� �� ��û���� ��� �ð��� �˻��Ͽ� �� ������ minInterval(������ �и���) �̸��̸� ������ �����Ѵ�
		
		Connection conn = Jsoup.connect(url); // ������ ����
		Document doc = conn.get(); // HTML�� �ٿ�ε��Ͽ� �Ľ��Ͽ� Document(DOMƮ��) ��ü�� ��ȯ		
		// Document ��ü�� Ʈ���� Ž���ϰ� ��带 �����ϴ� �޼ҵ� ����
		
		// ������ �����ϰ� �ܶ� �����ϱ�
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
