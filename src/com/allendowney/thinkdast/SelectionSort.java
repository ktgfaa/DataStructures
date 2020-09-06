package com.allendowney.thinkdast;

import java.util.Arrays;

public class SelectionSort {
	// 알고리즘 분석
	/*
	 * 상수 시간 : 실행시간이 입력 크기에 의존하지 않으면 알고리즘은 상수 시간(constant time)을 따른다고 한다 예를 들어, n개의
	 * 배열에서 브래킷 연산([])을 사용하여 요소 중 하나에 접근할 때 이 연산은 배열의 크기와 관계없이 같은 수의 동작을 수행한다.
	 * 
	 * 선형 : 실행시간이 입력의 크기에 비례하면 알고리즘은 선형(linear)이라고 한다 예를 들어, 배열에 있는 요소를 더한다면 n개 요소에
	 * 접근하여 n-1번 더하기 연산을 해야 한다 연산(요소 접근과 더하기)의 총 횟수는 2n-1이고 n에 비례한다.
	 * 
	 * 이차 : 실행시간이 n의 제곱에 비례하면 알고리즘은 이차(quadratic)라고 한다 예를 들어, 리스트에 있는 어떤 요소가 두 번이상
	 * 나타나는지를 알고 싶다고 가정하고, 간단한 알고리즘은 각 요소를 다른 모든 요소와 비교하는 것이다 n개의 요소가 있고 각각 n-1개의 다른
	 * 요소와 비교하면 총 비교 횟수는 n의제곱-n이 되어 n이 커지면서 n의 제곱에 비례하게 된다.
	 */
	
	// i 와 j의 위치에 있는 값을 바꿉니다
	// 요소를 읽고 쓰는 것은 상수 시간 연산 
	public static void swapElements(int[] array,int i,int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	// 주어진 위치인 start에서 시작하여 배열에 있는 가장 작은 요소의 인덱스를 찾는다
	// 반복문이 돌 때마다 배열의 두 요소에 접근하고 한 번의 비교연산을 한다(상수 시간 연산)
	// 1. start가 0이면 indexLowest 메소드는 전체 배열을 검색하고 전체 비교 횟수는 배열의 길이인 n이 된다
	// 2. start 인자가 1이면 비교 횟수는 n-1이 된다
	// 3. 일반적으로 비교 횟수는 n-start가 되어 indexLowest 메소드는 선형이 된다
	public static int indexLowest(int[] array,int start) {
		int lowIndex = start;
		for(int i=start;i<array.length;i++) {
			if(array[i] < array[lowIndex]) {
				lowIndex = i;
			}
		}
		
		return lowIndex;
	}
	
	// 배열을 정렬한다
	// indexLowest 메소드를 호출할 때마다 연산 횟수는 n에 비례한다
	// 이를 n번 호출하므로 결과적으로 총 연산횟수는 n의 제곱에 비례하게 된다(이차)
	public static int[] selectionSort(int[] array) {
		for(int i=0;i<array.length;i++) {
			int j = indexLowest(array,i);
			swapElements(array,i,j);
		}
		
		return array;
	}

	public static void main(String[] args) {
		int[] array = {3,6,1,7,8};
		selectionSort(array);
		System.out.println(Arrays.toString(array));
		

	}

}
