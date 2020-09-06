package com.allendowney.thinkdast;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList<T> implements List<T> {

	int size;	// 요소의 개수를 추적합니다
	private T[] array; // 요소를 저장합니다
	
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		// 자바는 다음과 같이 타입 파라미터로 배열을 초기화할 수 없습니다. 예를 들어, 다음은 작동하지 않습니다.
		// array = new T[10];
		// 이러한 제약을 해결하려면 Object의 배열로 초기화하고 형변환을 해야합니다.
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

	// 배열에 여분의 공간이 없으면 더 큰 배열을 만들어 요소 위에 복사해야 한다
	// 그다음 배열에 요소들을 저장하고 size를 늘립니다.
	// 배열에 지정된 요소를 추가하는 메소드이다
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

	// 선택된 위치에 지정한 요소를 더하는 메소드이다.
	// 필요하다면 다른인자들을 시프트하여 공간을 만든다.(해당 위치에 있으면 요소를 오른쪽으로 이동)
	@Override
	public void add(int index, T element) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		// add the element to get the resizing
		// 먼저 요소를 더해서 사이즈를 늘립니다.
		add(element);

		// shift the elements
		// 그 후에 요소들을 오른쪽으로 이동시킵니다.
		for (int i=size-1; i>index; i--) {
			array[i] = array[i-1];
		}
		// put the new one in the right place
		// 지정한 요소를 지정한 위치에 추가 합니다.
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
		// size 만큼의 반복문을 돌립니다.
		for (int i = 0; i<size; i++) {
			// target과 array의 모든 요소들을 비교합니다.
			if (equals(target, array[i])) {
				return i;
			}
		}
		// 같으면 그 요소의 index를 반환하고 , 다르면 -1를 반환 합니다.
		return -1;
	}

	/** Checks whether an element of the array is the target.
	 *
	 * Handles the special case that the target is null.
	 *
	 * @param target
	 * @param object
	 */
	// equals 헬퍼 메소드 제공
	// 비교하는 대상과 같으면 true를 다르면 false를 반환하는 메소드
	// 클래스 내부에서만 실행하기 때문에 private이며 List 인터페이스의 일부가 아니다.
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
		// 지정한 요소를 element에 저장한다
		T element = get(index);
		// 지정한 index를 index+1로 변경한다.(지정한 위치에 다음 인덱스 요소를 추가해서 선택된 요소를 제거한다.)
		for (int i=index; i<size-1; i++) {
			array[i] = array[i+1];
		}
		// 전체 사이즈를 줄인다.
		size--;
		// 이전에 지정된 위치에 있던 요소를 반환한다.
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
		// 이전에 지정된 위치에 있던 요소 old에 저장
		T old = get(index);
		// 지정된 위치에 있는 요소를 지정된 요소로 변경
		array[index] = element;
		// 이전에 지정된 위치에 있던 요소 old 반환
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
