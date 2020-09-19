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
			// ������ �ٿ�ε��ϰ� �Ľ��ϱ�
			Connection conn = Jsoup.connect(url); // ������ ����
			Document doc = conn.get(); // HTML�� �ٿ�ε��Ͽ� �Ľ��Ͽ� Document(DOMƮ��) ��ü�� ��ȯ
			// Document ��ü�� Ʈ���� Ž���ϰ� ��带 �����ϴ� �޼ҵ� ����
			
			// ������ �����ϰ� �ܶ� �����ϱ�
			Element content = doc.getElementById("mw-content-text"); 
			Elements paragraphs = content.select("a"); // ���ڰ��� ��ġ�ϴ� �±� ��Ҹ� ��� ��ȯ
			Element firstPara = paragraphs.get(0); // paragraphs �������� ù��° �ܶ� ����
			
			// fetcher�� ���� ����
			WikiFetcher wf = new WikiFetcher();
			Elements Fetparagraphs = wf.fetchWikipedia(url);
			Element FfirstPara = Fetparagraphs.get(0);
			recursiveDFS(FfirstPara);
			System.out.println("1");
			
			// �ݺ��� DFS ����
			recursiveDFS(firstPara);
			System.out.println("2");

			// ����� DFS ����
			iterativeDFS(firstPara);
			System.out.println("3");
			
			// �ݺ��� DFS�ϴ� ������ �� ��带 ó���ϴ� ������ ���� ����ϰ� ������
			// WikiNodeIterable Ŭ������ ����
			Iterable<Node> iter = new WikiNodeIterable(firstPara); // ���� �ܶ����� Iterable<Node> �������̽��� �����ϴ� ��ü����
			for(Node node: iter) {
				// instanceof�� ��üŸ���� Ȯ���ϴµ� ����ϸ�, ����ȯ�� �������� �ش翩�θ� boolean������ ��ȯ
				if(node instanceof TextNode) {
					System.out.print(node);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
	
	// ���� �켱 Ž��(DFS)
	// 1. Ʈ���� ��Ʈ���� �����Ͽ� ù ��° �ڽ� ��带 ���� 
	// 2. �׸��� �ڽ��� �ڽ��� ������ �ִٸ� ù ��° �ڽ��� �ٽ� �����Ѵ�
	// 3. �ڽ��� ���� ��忡 �����ϸ� �θ� ���� �Ž��� �ö� �θ����� ���� �ڽ��� �ִٸ� �������� �̵�
	// 4. ���� �ڽ��� ���ٸ� �ٽ� �Ž��� �ö󰣴�
	
	// ���� �켱Ž��(DFS) �� ����� ����
	private static void recursiveDFS(Node node) {
		
		// ��Ʈ���� �����Ͽ� ��� Node�� ȣ��
		// Node�� TextNode��� ���
		if( node instanceof TextNode) {
			System.out.print(node);
		}
		
		// Node�� �ڽ��� �ִٸ� �ڽ� ������� ���� recursiveDFS �޼ҵ带 ȣ��
		for(Node child : node.childNodes()) {
			recursiveDFS(child);
		}
	}
	
	// ���� �켱Ž��(DFS)�� �ݺ��� ����(����)
	private static void iterativeDFS(Node root) {
		
		
		Deque<Node> stack = new ArrayDeque<Node>();
		Deque<Node> stack2 = new LinkedList<Node>(); // ArrayDeque ���� LinkedList�ε� ��������
		// ������ ���ÿ� ��Ʈ�� push (�ֻ�ܿ� ��Ҹ� �߰�)
		stack.push(root);

		// �ݺ����� ������ �� ������ ��� �����Ѵ�
		while(!stack.isEmpty()) {
			// ���ÿ��� Node�� pop (�ֻ�ܿ� �ִ� ��Ҹ� �����ϰ� ��ȯ)
			Node node = stack.pop();
			// TextNode�̸� ���
			if (node instanceof TextNode) {
				System.out.print(node);
			}
			
			// ������ LIFO �̱� ������ �������� ������ �Ѵ�
			// ArrayList�� �����Ͽ� �������� �����
			List<Node> nodes = new ArrayList<Node>(node.childNodes());
			Collections.reverse(nodes);
			
			// �ڽ� ��带 ���ÿ� push (�ֻ�ܿ� ��Ҹ� �߰�)
			for(Node child: nodes) {
				stack.push(child);
			}
		}
	}
	
}
