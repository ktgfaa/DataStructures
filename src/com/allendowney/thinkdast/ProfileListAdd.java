package com.allendowney.thinkdast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jfree.data.xy.XYSeries;

import com.allendowney.thinkdast.Profiler.Timeable;



public class ProfileListAdd {
	public static void main(String[] args) {
		System.out.print("ArrayList 시작 Add : ");profileArrayListAddBeginning();
		System.out.print("ArrayList 끝 Add : ");profileArrayListAddEnd();
		System.out.print("LinkedList 시작 Add : ");profileLinkedListAddBeginning();
		System.out.print("LinkedList 끝 Add : ");profileLinkedListAddEnd();
	}

	private static void profileArrayListAddEnd() {
		
		// 익명 클래스 방법으로 구현
		// Timeable 인터페이스를 구현하고 동시에 새로운 클래스의 인스턴스를 생성한다
		Timeable timeable = new Timeable() {
			List<String> list;
			
			// 시간측정을 시작하기 전에 필요한 일들을 수행한다
			// 이 경우에는 빈 리스트를 만든다
			public void setup(int n) {
				list = new ArrayList<String>();
			}
			
			// 측정 작업을 한다
			// 이 경우에는 리스트에 n개의 요소를 추가한다
			public void timeMe(int n) {
				for(int i=0;i<n;i++) {
					list.add("a string");
				}
			}
		};
		
		// 시간을 측정하는 n값
		// 인자의 크기가 너무 작으면 정확하게 측정하기에 실행시간이 너무 짧음
		int startN = 4000; 
		
		// 밀리초 단위로 임계치를 지정한다 timingLoop에서 문제 크기가 증가하면 이에 따라 실행시간도 늘어난다
		// 실행시간이 임계치를 넘으면 timingLoop는 중단된다
		// 인자가 너무 작으면 문제 크기와 실행시간의 명확한 관계를 보여주는 데이터를 얻기 어렵다
		int endMillis = 1000; 
		
		// profile 객체 생성
		Profiler profiler = new Profiler("ArrayList add end", timeable);
		// timingLoop 메소드는 timeMe 메소드를 n의 값 범위에서 여러번 호출한다
		XYSeries series = profiler.timingLoop(startN, endMillis);
		// 그래프 생성
		profiler.plotResults(series);
		
	}
	
	/**
	 * Characterize the run time of adding to the beginning of an ArrayList
	 */
	public static void profileArrayListAddBeginning() {

		Timeable timeable = new Timeable() {
			List<String> list;
			
			public void setup(int n) {
				list = new ArrayList<String>();
			}

			public void timeMe(int n) {
				for(int i=0;i<n;i++) {
					list.add(0,"a string");
				}
			}
		};
		int startN = 4000; 
		int endMillis = 10000; 
		runProfiler("ArrayList add end",timeable,startN,endMillis);
	}

	/**
	 * Characterize the run time of adding to the beginning of a LinkedList
	 */
	public static void profileLinkedListAddBeginning() {
		Timeable timeable = new Timeable() {
			List<String> list;
			
			public void setup(int n) {
				list = new LinkedList<String>();
			}

			public void timeMe(int n) {
				for(int i=0;i<n;i++) {
					list.add(0,"a string");
				}
			}
		};
		int startN = 128000; 
		int endMillis = 2000; 
		runProfiler("ArrayList add end",timeable,startN,endMillis);
	}

	/**
	 * Characterize the run time of adding to the end of a LinkedList
	 */
	public static void profileLinkedListAddEnd() {
		Timeable timeable = new Timeable() {
			List<String> list;
			
			public void setup(int n) {
				list = new LinkedList<String>();
			}

			public void timeMe(int n) {
				for(int i=0;i<n;i++) {
					list.add("a string");
				}
			}
		};
		int startN = 64000; 
		int endMillis = 1000; 
		runProfiler("ArrayList add end",timeable,startN,endMillis);
	}
	
	private static void runProfiler(String title, Timeable timeable, int startN, int endMillis) {
		Profiler profiler = new Profiler(title, timeable);
		// timingLoop 메소드는 timeMe 메소드를 n의 값 범위에서 여러번 호출한다
		XYSeries series = profiler.timingLoop(startN, endMillis);
		// 그래프 생성
		profiler.plotResults(series);
	}
	
	
}
