package com.scsx.domain;

import java.util.Date;

public class Discussion {
	public static int paperInv = 3;
	private Date TIME;
	private String UNAME;
	private String HEADIMAGE;
	private String DATA;
	private int PNO;
	private int UNO;
	private int DNO;
	
	@Override
	public String toString() {
		return "Discussion [TIME=" + TIME + ", UNAME=" + UNAME + ", HEADIMAGE=" + HEADIMAGE + ", Data=" + DATA
				+ ", PNO=" + PNO + ", UNO=" + UNO + ", DNO=" + DNO + "]";
	}
	public Date getTIME() {
		return TIME;
	}
	public void setTIME(Date tIME) {
		TIME = tIME;
	}
	public String getUNAME() {
		return UNAME;
	}
	public void setUNAME(String uNAME) {
		UNAME = uNAME;
	}
	public String getHEADIMAGE() {
		return HEADIMAGE;
	}
	public void setHEADIMAGE(String hEADIMAGE) {
		HEADIMAGE = hEADIMAGE;
	}
	public String getDATA() {
		return DATA;
	}
	public void setDATA(String dATA) {
		DATA = dATA;
	}
	public int getPNO() {
		return PNO;
	}
	public void setPNO(int pNO) {
		PNO = pNO;
	}
	public int getUNO() {
		return UNO;
	}
	public void setUNO(int uNO) {
		UNO = uNO;
	}
	public int getDNO() {
		return DNO;
	}
	public void setDNO(int dNO) {
		DNO = dNO;
	}
	
}
