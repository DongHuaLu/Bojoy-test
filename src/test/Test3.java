package test;




public class Test3 {
	public static void main(String[] args) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		for(int i = 1;i<=160;i++){
			for(int j = 1;j<=160;j++){
				//+" Y = "+j+" per = "+getDressBookPer(i,j)
				System.out.println(i);
			}
		}
	}

	public static int getDressBookPer(int attactGrade,int otherGrade){
		int deletGrade = attactGrade-otherGrade;
		if (attactGrade>otherGrade){
			return Math.max(5, 30-(deletGrade));
		}else{
			return Math.min(80, (int)(30-(deletGrade)*1.5)+(deletGrade%2==0?0:1));
		}
	}

}
