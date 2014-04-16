package test;

public class Model {
	private int a;
	private int b;
	
	public Model(int a) {
		this.a = a;
	}
	@Override
	public String toString() {
		return a+"_"+b;
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	
}
