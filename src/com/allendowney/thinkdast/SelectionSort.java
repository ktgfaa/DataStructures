package com.allendowney.thinkdast;

import java.util.Arrays;

public class SelectionSort {
	// �˰��� �м�
	/*
	 * ��� �ð� : ����ð��� �Է� ũ�⿡ �������� ������ �˰����� ��� �ð�(constant time)�� �����ٰ� �Ѵ� ���� ���, n����
	 * �迭���� �귡Ŷ ����([])�� ����Ͽ� ��� �� �ϳ��� ������ �� �� ������ �迭�� ũ��� ������� ���� ���� ������ �����Ѵ�.
	 * 
	 * ���� : ����ð��� �Է��� ũ�⿡ ����ϸ� �˰����� ����(linear)�̶�� �Ѵ� ���� ���, �迭�� �ִ� ��Ҹ� ���Ѵٸ� n�� ��ҿ�
	 * �����Ͽ� n-1�� ���ϱ� ������ �ؾ� �Ѵ� ����(��� ���ٰ� ���ϱ�)�� �� Ƚ���� 2n-1�̰� n�� ����Ѵ�.
	 * 
	 * ���� : ����ð��� n�� ������ ����ϸ� �˰����� ����(quadratic)��� �Ѵ� ���� ���, ����Ʈ�� �ִ� � ��Ұ� �� ���̻�
	 * ��Ÿ�������� �˰� �ʹٰ� �����ϰ�, ������ �˰����� �� ��Ҹ� �ٸ� ��� ��ҿ� ���ϴ� ���̴� n���� ��Ұ� �ְ� ���� n-1���� �ٸ�
	 * ��ҿ� ���ϸ� �� �� Ƚ���� n������-n�� �Ǿ� n�� Ŀ���鼭 n�� ������ ����ϰ� �ȴ�.
	 */
	
	// i �� j�� ��ġ�� �ִ� ���� �ٲߴϴ�
	// ��Ҹ� �а� ���� ���� ��� �ð� ���� 
	public static void swapElements(int[] array,int i,int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	// �־��� ��ġ�� start���� �����Ͽ� �迭�� �ִ� ���� ���� ����� �ε����� ã�´�
	// �ݺ����� �� ������ �迭�� �� ��ҿ� �����ϰ� �� ���� �񱳿����� �Ѵ�(��� �ð� ����)
	// 1. start�� 0�̸� indexLowest �޼ҵ�� ��ü �迭�� �˻��ϰ� ��ü �� Ƚ���� �迭�� ������ n�� �ȴ�
	// 2. start ���ڰ� 1�̸� �� Ƚ���� n-1�� �ȴ�
	// 3. �Ϲ������� �� Ƚ���� n-start�� �Ǿ� indexLowest �޼ҵ�� ������ �ȴ�
	public static int indexLowest(int[] array,int start) {
		int lowIndex = start;
		for(int i=start;i<array.length;i++) {
			if(array[i] < array[lowIndex]) {
				lowIndex = i;
			}
		}
		
		return lowIndex;
	}
	
	// �迭�� �����Ѵ�
	// indexLowest �޼ҵ带 ȣ���� ������ ���� Ƚ���� n�� ����Ѵ�
	// �̸� n�� ȣ���ϹǷ� ��������� �� ����Ƚ���� n�� ������ ����ϰ� �ȴ�(����)
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
