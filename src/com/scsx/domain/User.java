package com.scsx.domain;

public class User {
	private int UNO;
	private String UNAME;
	private String PW;
	private String NAME;
	private String ID;
	private String UPHONE;
	private String POWER;
	private String HEADIMAGE;

	public User() {
	}

	public User(String UNAME, String PW, String POWER) {
		this.UNAME = UNAME;
		this.PW = PW;
		this.POWER = POWER;

	}

	public User(String UNAME, String PW, String NAME, String ID, String PHONE, String POWER) {
		this.UNAME = UNAME;
		this.PW = PW;
		this.NAME = NAME;
		this.ID = ID;
		this.UPHONE = PHONE;
		this.POWER = POWER;
	}

	public int getUNO() {
		return UNO;
	}

	public void setUNO(int uNO) {
		UNO = uNO;
	}

	public String getUNAME() {
		return UNAME;
	}

	public void setUNAME(String uNAME) {
		UNAME = uNAME;
	}

	public String getPW() {
		return PW;
	}

	public void setPW(String pW) {
		PW = pW;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getUPHONE() {
		return UPHONE;
	}

	public void setUPHONE(String uPHONE) {
		UPHONE = uPHONE;
	}

	public String getPOWER() {
		return POWER;
	}

	public void setPOWER(String pOWER) {
		POWER = pOWER;
	}

	public String getHEADIMAGE() {
		return HEADIMAGE;
	}

	public void setHEADIMAGE(String hEADIMAGE) {
		HEADIMAGE = hEADIMAGE;
	}

	public String toString() {
		return "NAME=" + NAME;
	}

}
