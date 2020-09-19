package com.allendowney.thinkdast;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class WikiNodeExample {
	
	public static void main(String[] args) {
		
		String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
		
		try {
			// 문서를 다운로드하고 파싱하기
			Connection conn = Jsoup.connect(url); // 웹서버 접속
			Document doc = conn.get(); // HTML을 다운로드하여 파싱하여 Document(DOM트리) 객체를 반환
			// Document 객체는 트리를 탐색하고 노드를 선택하는 메소드 제공
			
			// 내용을 선택하고 단락 추출하기
			Element content = doc.getElementById("mw-content-text"); 
			Elements paragraphs = content.select("a"); // 인자값과 일치하는 태그 요소를 모두 반환
			Element firstPara = paragraphs.get(0); // paragraphs 변수에서 첫번째 단락 선택
			
			// fetcher를 통한 구현
			WikiFetcher wf = new WikiFetcher();
			Elements Fetparagraphs = wf.fetchWikipedia(url);
			Element FfirstPara = Fetparagraphs.get(0);
			recursiveDFS(FfirstPara);
			System.out.println("1");
			
			// 반복적 DFS 구현
			recursiveDFS(firstPara);
			System.out.println("2");

			// 재귀적 DFS 구현
			iterativeDFS(firstPara);
			System.out.println("3");
			
			// 반복적 DFS하는 로직과 그 노드를 처리하는 로직을 쉽고 깔끔하게 정리한
			// WikiNodeIterable 클래스로 구현
			Iterable<Node> iter = new WikiNodeIterable(firstPara); // 선택 단락으로 Iterable<Node> 인터페이스를 구현하는 객체생성
			for(Node node: iter) {
				// instanceof는 객체타입을 확인하는데 사용하며, 형변환이 가능한지 해당여부를 boolean값으로 반환
				if(node instanceof TextNode) {
					System.out.print(node);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
	
	// 깊이 우선 탐색(DFS)
	// 1. 트리의 루트에서 시작하여 첫 번째 자식 노드를 선택 
	// 2. 그리고 자식이 자식을 가지고 있다면 첫 번째 자식을 다시 선택한다
	// 3. 자식이 없는 노드에 도착하면 부모 노드로 거슬러 올라가 부모노드의 다음 자식이 있다면 그쪽으로 이동
	// 4. 다음 자식이 없다면 다시 거슬러 올라간다
	
	// 깊이 우선탐색(DFS) 의 재귀적 구현
	private static void recursiveDFS(Node node) {
		
		// 루트에서 시작하여 모든 Node를 호출
		// Node가 TextNode라면 출력
		if( node instanceof TextNode) {
			System.out.print(node);
		}
		
		// Node에 자식이 있다면 자식 순서대로 각각 recursiveDFS 메소드를 호출
		for(Node child : node.childNodes()) {
			recursiveDFS(child);
		}
	}
	
	// 깊이 우선탐색(DFS)의 반복적 구현(스택)
	private static void iterativeDFS(Node root) {
		
		
		Deque<Node> stack = new ArrayDeque<Node>();
		Deque<Node> stack2 = new LinkedList<Node>(); // ArrayDeque 말고 LinkedList로도 구현가능
		// 생성한 스택에 루트를 push (최상단에 요소를 추가)
		stack.push(root);

		// 반복문은 스택이 빌 때까지 계속 진행한다
		while(!stack.isEmpty()) {
			// 스택에서 Node를 pop (최상단에 있는 요소를 제거하고 반환)
			Node node = stack.pop();
			// TextNode이면 출력
			if (node instanceof TextNode) {
				System.out.print(node);
			}
			
			// 스택은 LIFO 이기 때문에 역순으로 만들어야 한다
			// ArrayList로 복사하여 역순으로 만들기
			List<Node> nodes = new ArrayList<Node>(node.childNodes());
			Collections.reverse(nodes);
			
			// 자식 노드를 스택에 push (최상단에 요소를 추가)
			for(Node child: nodes) {
				stack.push(child);
			}
		}
	}
	
}
