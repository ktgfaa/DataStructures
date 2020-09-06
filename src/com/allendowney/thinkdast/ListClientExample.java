package com.allendowney.thinkdast;

import java.util.ArrayList;
import java.util.List;

public class ListClientExample {
	
	// interface 기반 프로그래밍 or 인터페이스 프로그래밍
	// 여기서 인터페이스는 자바 인터페이스가 아닌 일반적인 개념의 인터페이스를 말함
	// Java는 인터페이스의 구현 클래스로 ArrayList 와 LinkedList 클래스를 제공한다
	@SuppressWarnings("rawtypes")
	private List list;
	
	@SuppressWarnings("rawtypes")
	public ListClientExample() {
		list = new ArrayList();
	}
	
	@SuppressWarnings("rawtypes")
	public List getList() {
		return list;
	}
	
	public static void main(String[] args) {
		ListClientExample lce = new ListClientExample();
		@SuppressWarnings("rawtypes")
		List list = lce.getList();
		System.out.println("ListClientExample 실행");
		System.out.println(list);
	}
}
