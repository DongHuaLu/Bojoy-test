public class Model {
	private boolean[] canUse = {true,false,false};
	

	public void setCanUse(int idx){
		canUse[idx] = true;
	}
	public boolean canUse(int idx){
		return canUse[idx];
	}

}
