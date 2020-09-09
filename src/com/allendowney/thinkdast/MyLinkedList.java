
package com.allendowney.thinkdast;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLinkedList<E> implements List<E> {


	private class Node {
		public E cargo;
		public Node next;

		public Node(E cargo) {
			this.cargo = cargo;
			this.next = null;
		}
		public Node(E cargo, Node next) {
			this.cargo = cargo;
			this.next = next;
		}
		public String toString() {
			return "Node(" + cargo.toString() + ")";
		}
	}

	private int size;            
	private Node head;           

	public MyLinkedList() {
		head = null;
		size = 0;
	}


	public static void main(String[] args) {
		// run a few simple tests
		List<Integer> mll = new MyLinkedList<Integer>();
		mll.add(1);
		mll.add(2);
		mll.add(3);
		System.out.println(Arrays.toString(mll.toArray()) + " size = " + mll.size());

		mll.remove(new Integer(2));
		System.out.println(Arrays.toString(mll.toArray()) + " size = " + mll.size());
	}

	@Override
	public boolean add(E element) {
		if (head == null) {
			// head 가 null 즉 아무것도 없는 Node에 추가
			head = new Node(element);
		} else {
			Node node = head;
			// 마지막 까지 노드를 반복
			// head 즉 변수 node의 참조부분이 null이 아닐 경우 node에 다음 참조 노드값을 줘서 마지막 노드를 찾음
			for ( ; node.next != null; node = node.next) {}
			// 찾은 마지막노드에 다음 참조값에 매개변수로 받은 값으로 인스턴스 생성해 추가
			node.next = new Node(element);
		}
		size++; // null 값을 넣기위해 size 업
		return true;
	}

	@Override
	public void add(int index, E element) {

		if (index == 0) {
			// index가 0 즉 아무것도 없는 Node에 추가
			head = new Node(element, head);
		} else {
			// getNode 메소드를 이용해 입력한 노드 이전의 노드를 node 변수에 저장
			Node node = getNode(index-1);
			// 이전 Node값 참조값으로 인스턴스생성해서 추가
			node.next = new Node(element, node.next);
		}
		size++; // null 값을 넣기위해 size 업
	}

	@Override
	public boolean addAll(Collection<? extends E> collection) {
		boolean flag = true;
		for (E element: collection) {
			flag &= add(element);
		}
		return flag;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> collection) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	@Override
	public boolean contains(Object obj) {
		return indexOf(obj) != -1;
	}

	@Override
	public boolean containsAll(Collection<?> collection) {
		for (Object obj: collection) {
			if (!contains(obj)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public E get(int index) {
		Node node = getNode(index);
		return node.cargo;
	}

	/** Returns the node at the given index.
	 * @param index
	 * @return
	 */
	private Node getNode(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node node = head;
		for (int i=0; i<index; i++) {
			node = node.next;
		}
		return node;
	}

	@Override
	public int indexOf(Object target) {
		// 첫 번째 요소 node에 저장
		Node node = head;
		// equals 메소드에 타겟과 node변수의 값을 비교
		// 같으면 i를 리턴해서 index를 알려줌
		// 다르면 node 변수를 다음 참조값으로 변경해서 반복
		for (int i=0; i<size; i++) {
			if (equals(target, node.cargo)) {
				return i;
			}
			node = node.next;
		}
		
		// 없다면 -1 리턴
		return -1;
	}


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
	public Iterator<E> iterator() {
		@SuppressWarnings("unchecked")
		E[] array = (E[]) toArray();
		return Arrays.asList(array).iterator();
	}

	@Override
	public int lastIndexOf(Object target) {
		Node node = head;
		int index = -1;
		for (int i=0; i<size; i++) {
			if (equals(target, node.cargo)) {
				index = i;
			}
			node = node.next;
		}
		return index;
	}

	@Override
	public ListIterator<E> listIterator() {
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return null;
	}

	@Override
	public boolean remove(Object obj) {
		// 제거 원하는 값 indexOf 메소드를 통해 index 저장
		int index = indexOf(obj);
		// 인덱스가 -1이면 없는 값이므로 false 출력
		if (index == -1) {
			return false;
		}
		// remove 메소드로 값 제거 후 true 출력
		remove(index);
		return true;
	}

	@Override
	public E remove(int index) {
		// get메소드로 원하는 요소 찾아서 저장
		E element = get(index);
		if (index == 0) {
			// 제거할려는 요소가 첫 번째 요소라면 head 변수에 다음 참조 값을 저장
			head = head.next;
		} else {
			// 첫 번째 요소가 아니라면 node에 제거 할려는 요소 이전 값을 저장하고
			// node 다음 참조 값으로 다음 다음 값을 저장
			Node node = getNode(index-1);
			node.next = node.next.next;
		}
		size--;
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
	public E set(int index, E element) {
		Node node = getNode(index);
		E old = node.cargo;
		node.cargo = element;
		return old;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		if (fromIndex < 0 || toIndex >= size || fromIndex > toIndex) {
			throw new IndexOutOfBoundsException();
		}
		// TODO: classify this and improve it.
		int i = 0;
		MyLinkedList<E> list = new MyLinkedList<E>();
		for (Node node=head; node != null; node = node.next) {
			if (i >= fromIndex && i <= toIndex) {
				list.add(node.cargo);
			}
			i++;
		}
		return list;
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		int i = 0;
		for (Node node=head; node != null; node = node.next) {
			// System.out.println(node);
			array[i] = node.cargo;
			i++;
		}
		return array;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}
}
