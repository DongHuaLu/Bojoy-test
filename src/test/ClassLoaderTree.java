package test;

public class ClassLoaderTree {

	public static void main(String[] args) {
		ClassLoader loader=ClassLoaderTree.class.getClassLoader();
		while(loader!=null){
			String s = "-";
			System.out.println(s+loader.toString());
			s+="-";
			loader = loader.getParent();
		}
	}
}
