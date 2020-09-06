package com.allendowney.thinkdast;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList<T> implements List<T> {

	int size;	// ����� ������ �����մϴ�
	private T[] array; // ��Ҹ� �����մϴ�
	
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		// �ڹٴ� ������ ���� Ÿ�� �Ķ���ͷ� �迭�� �ʱ�ȭ�� �� �����ϴ�. ���� ���, ������ �۵����� �ʽ��ϴ�.
		// array = new T[10];
		// �̷��� ������ �ذ��Ϸ��� Object�� �迭�� �ʱ�ȭ�ϰ� ����ȯ�� �ؾ��մϴ�.
		array = (T[]) new Object[10];
		size = 0;
	}
	
	public static void main(String[] args) {
		// run a few simple tests
		MyArrayList<Integer> mal = new MyArrayList<Integer>();
		mal.add(1);
		mal.add(2);
		mal.add(3);
		System.out.println(Arrays.toString(mal.toArray()) + " size = " + mal.size);

		mal.remove(new Integer(2));
		System.out.println(Arrays.toString(mal.toArray()) + " size = " + mal.size);
	}

	// �迭�� ������ ������ ������ �� ū �迭�� ����� ��� ���� �����ؾ� �Ѵ�
	// �״��� �迭�� ��ҵ��� �����ϰ� size�� �ø��ϴ�.
	// �迭�� ������ ��Ҹ� �߰��ϴ� �޼ҵ��̴�
	@Override
	public boolean add(T e) {
		if(size >= array.length) {
			@SuppressWarnings("unchecked")
			T[] bigger = (T[]) new Object[array.length * 2];
			System.arraycopy(array, 0, bigger, 0, array.length);
			array = bigger;
		}
		array[size] = e;
		size++;
		return true;
	}

	// ���õ� ��ġ�� ������ ��Ҹ� ���ϴ� �޼ҵ��̴�.
	// �ʿ��ϴٸ� �ٸ����ڵ��� ����Ʈ�Ͽ� ������ �����.(�ش� ��ġ�� ������ ��Ҹ� ���������� �̵�)
	@Override
	public void add(int index, T element) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		// add the element to get the resizing
		// ���� ��Ҹ� ���ؼ� ����� �ø��ϴ�.
		add(element);

		// shift the elements
		// �� �Ŀ� ��ҵ��� ���������� �̵���ŵ�ϴ�.
		for (int i=size-1; i>index; i--) {
			array[i] = array[i-1];
		}
		// put the new one in the right place
		// ������ ��Ҹ� ������ ��ġ�� �߰� �մϴ�.
		array[index] = element;
	}

	@Override
	public boolean addAll(Collection<? extends T> collection) {
		boolean flag = true;
		for (T element: collection) {
			flag &= add(element);
		}
		return flag;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> collection) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		// note: this version does not actually null out the references
		// in the array, so it might delay garbage collection.
		size = 0;
	}

	@Override
	public boolean contains(Object obj) {
		return indexOf(obj) != -1;
	}

	@Override
	public boolean containsAll(Collection<?> collection) {
		for (Object element: collection) {
			if (!contains(element)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return array[index];
	}

	@Override
	public int indexOf(Object target) {
		// size ��ŭ�� �ݺ����� �����ϴ�.
		for (int i = 0; i<size; i++) {
			// target�� array�� ��� ��ҵ��� ���մϴ�.
			if (equals(target, array[i])) {
				return i;
			}
		}
		// ������ �� ����� index�� ��ȯ�ϰ� , �ٸ��� -1�� ��ȯ �մϴ�.
		return -1;
	}

	/** Checks whether an element of the array is the target.
	 *
	 * Handles the special case that the target is null.
	 *
	 * @param target
	 * @param object
	 */
	// equals ���� �޼ҵ� ����
	// ���ϴ� ���� ������ true�� �ٸ��� false�� ��ȯ�ϴ� �޼ҵ�
	// Ŭ���� ���ο����� �����ϱ� ������ private�̸� List �������̽��� �Ϻΰ� �ƴϴ�.
	private boolean equals(Object target, Object element) {
		if (target == null) {
			return element == null;
		} else {
			return target.equals(element);
		}
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<T> iterator() {
		// make a copy of the array
		T[] copy = Arrays.copyOf(array, size);
		// make a list and return an iterator
		return Arrays.asList(copy).iterator();
	}

	@Override
	public int lastIndexOf(Object target) {
		// see notes on indexOf
		for (int i = size-1; i>=0; i--) {
			if (equals(target, array[i])) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public ListIterator<T> listIterator() {
		// make a copy of the array
		T[] copy = Arrays.copyOf(array, size);
		// make a list and return an iterator
		return Arrays.asList(copy).listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		// make a copy of the array
		T[] copy = Arrays.copyOf(array, size);
		// make a list and return an iterator
		return Arrays.asList(copy).listIterator(index);
	}

	@Override
	public boolean remove(Object obj) {
		int index = indexOf(obj);
		if (index == -1) {
			return false;
		}
		remove(index);
		return true;
	}

	@Override
	public T remove(int index) {
		// ������ ��Ҹ� element�� �����Ѵ�
		T element = get(index);
		// ������ index�� index+1�� �����Ѵ�.(������ ��ġ�� ���� �ε��� ��Ҹ� �߰��ؼ� ���õ� ��Ҹ� �����Ѵ�.)
		for (int i=index; i<size-1; i++) {
			array[i] = array[i+1];
		}
		// ��ü ����� ���δ�.
		size--;
		// ������ ������ ��ġ�� �ִ� ��Ҹ� ��ȯ�Ѵ�.
		return element;
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		boolean flag = true;
		for (Object obj: collection) {
			flag &= remove(obj);
		}
		return flag;
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T set(int index, T element) {
		// no need to check index; get will do it for us
		// ������ ������ ��ġ�� �ִ� ��� old�� ����
		T old = get(index);
		// ������ ��ġ�� �ִ� ��Ҹ� ������ ��ҷ� ����
		array[index] = element;
		// ������ ������ ��ġ�� �ִ� ��� old ��ȯ
		return old;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		if (fromIndex < 0 || toIndex >= size || fromIndex > toIndex) {
			throw new IndexOutOfBoundsException();
		}
		T[] copy = Arrays.copyOfRange(array, fromIndex, toIndex);
		return Arrays.asList(copy);
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(array, size);
	}

	@Override
	public <U> U[] toArray(U[] array) {
		throw new UnsupportedOperationException();
	}

}
