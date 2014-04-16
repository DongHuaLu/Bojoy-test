package test;

public class ModelRank implements Comparable<ModelRank> {
	private int roleid;
	private int start;
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ModelRank){
			ModelRank o = (ModelRank) obj;
			return this.roleid == o.getRoleid();
		}
		return false;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}


	@Override
	public int compareTo(ModelRank o) {
		return o.getStart()-this.start;
	}
}
