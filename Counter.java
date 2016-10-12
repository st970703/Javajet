package se254.a4.q1;
//@formatter:off
public class Counter {
	public int c;
	
	private int d;

	public void increment() { c++; }

	public void decrement() { c--; }

	public void reset() { c = 0; }
	
	private void resetP() { c = 0; }
}
//@formatter:on