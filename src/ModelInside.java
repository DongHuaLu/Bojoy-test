public class ModelInside {
	private String strI = null;

	public ModelInside(String strI) {
		super();
		this.strI = strI;
	}

	public String getStrI() {
		return strI;
	}

	public void setStrI(String strI) {
		this.strI = strI;
	}

	public void appendStr(String appStr) {
		strI += appStr;
	}

}
