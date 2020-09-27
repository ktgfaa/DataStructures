package com.allendowney.thinkdast;

public class SingletonExample {
	
	private String input;
	
	private SingletonExample() {};
	
	private static class InnerInstance {
		private static final SingletonExample single = new SingletonExample();
	}
	
	public static SingletonExample getInstance() {
		return InnerInstance.single;
	}
	
	public void print(String input) {
		this.input = input;
		System.out.println("ΩÃ±€≈Ê ∆–≈œ : " + input);
	}
	
	public void print() {
		System.out.println("ΩÃ±€≈Ê ∆–≈œ : " + this.input);
	}

	public static void main(String[] args) {
		SingletonExample single = SingletonExample.getInstance();
		SingletonExample single2 = SingletonExample.getInstance();
		single.print("1");
		single2.print();

	}

}
