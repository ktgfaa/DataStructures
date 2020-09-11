package com.allendowney.thinkdast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jfree.data.xy.XYSeries;

import com.allendowney.thinkdast.Profiler.Timeable;



public class ProfileListAdd {
	public static void main(String[] args) {
		System.out.print("ArrayList ���� Add : ");profileArrayListAddBeginning();
		System.out.print("ArrayList �� Add : ");profileArrayListAddEnd();
		System.out.print("LinkedList ���� Add : ");profileLinkedListAddBeginning();
		System.out.print("LinkedList �� Add : ");profileLinkedListAddEnd();
	}

	private static void profileArrayListAddEnd() {
		
		// �͸� Ŭ���� ������� ����
		// Timeable �������̽��� �����ϰ� ���ÿ� ���ο� Ŭ������ �ν��Ͻ��� �����Ѵ�
		Timeable timeable = new Timeable() {
			List<String> list;
			
			// �ð������� �����ϱ� ���� �ʿ��� �ϵ��� �����Ѵ�
			// �� ��쿡�� �� ����Ʈ�� �����
			public void setup(int n) {
				list = new ArrayList<String>();
			}
			
			// ���� �۾��� �Ѵ�
			// �� ��쿡�� ����Ʈ�� n���� ��Ҹ� �߰��Ѵ�
			public void timeMe(int n) {
				for(int i=0;i<n;i++) {
					list.add("a string");
				}
			}
		};
		
		// �ð��� �����ϴ� n��
		// ������ ũ�Ⱑ �ʹ� ������ ��Ȯ�ϰ� �����ϱ⿡ ����ð��� �ʹ� ª��
		int startN = 4000; 
		
		// �и��� ������ �Ӱ�ġ�� �����Ѵ� timingLoop���� ���� ũ�Ⱑ �����ϸ� �̿� ���� ����ð��� �þ��
		// ����ð��� �Ӱ�ġ�� ������ timingLoop�� �ߴܵȴ�
		// ���ڰ� �ʹ� ������ ���� ũ��� ����ð��� ��Ȯ�� ���踦 �����ִ� �����͸� ��� ��ƴ�
		int endMillis = 1000; 
		
		// profile ��ü ����
		Profiler profiler = new Profiler("ArrayList add end", timeable);
		// timingLoop �޼ҵ�� timeMe �޼ҵ带 n�� �� �������� ������ ȣ���Ѵ�
		XYSeries series = profiler.timingLoop(startN, endMillis);
		// �׷��� ����
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
		// timingLoop �޼ҵ�� timeMe �޼ҵ带 n�� �� �������� ������ ȣ���Ѵ�
		XYSeries series = profiler.timingLoop(startN, endMillis);
		// �׷��� ����
		profiler.plotResults(series);
	}
	
	
}
