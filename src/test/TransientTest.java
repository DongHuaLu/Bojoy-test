package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TransientTest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8209042900126557889L;
	private String a;
	private transient String b;
	private String c;
	
	public TransientTest(String a,String b,String c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	@Override
	public String toString() {
		return a+","+b+","+c;
	}
	
	public static void main(String[] args) throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("c:/a.dat")));
		TransientTest tt = new TransientTest("a","b","c");
		oos.writeObject(tt);
		oos.flush();
		System.out.println(tt);
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("c:/a.dat")));
		TransientTest tt2 = (TransientTest) ois.readObject();
		System.out.println(tt2);
	}

}
