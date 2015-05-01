package dateManager;

import java.io.Serializable;

public class DateBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String date;
	private String thing;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getThing() {
		return thing;
	}
	public void setThing(String thing) {
		this.thing = thing;
	}

}
