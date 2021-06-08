package study.java.lang.sort;

public class MyObj implements Comparable<MyObj>{
	@Override
	public String toString() {
		return "MyObj [a=" + a + ", b=" + b + "]";
	}
	
	private int a;
	private String b;
	
	public MyObj(int a, String b) {
		super();
		this.a = a;
		this.b = b;
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	@Override
	public int compareTo(MyObj o) {
		return a - o.a;
	}
	
}
