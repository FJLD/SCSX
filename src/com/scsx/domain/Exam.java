package com.scsx.domain;

import java.util.Date;

public class Exam {

	private int UNO;
	private int PNO;
	private String UNAME;
	private String NAME;
	private String PNAME;
	private String RESULT;
	private Date TIME;
	private int EXAMNO;

	public Exam() {
	}

	public int getUNO() {
		return UNO;
	}

	public void setUNO(int uNO) {
		UNO = uNO;
	}

	public int getPNO() {
		return PNO;
	}

	public void setPNO(int pNO) {
		PNO = pNO;
	}
	
	public String getUNAME() {
		return UNAME;
	}

	public void setUNAME(String uNAME) {
		UNAME = uNAME;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public String getPNAME() {
		return PNAME;
	}

	public void setPNAME(String pNAME) {
		PNAME = pNAME;
	}

	public String getRESULT() {
		return RESULT;
	}

	public void setRESULT(String rESULT) {
		RESULT = rESULT;
	}

	public Date getTIME() {
		return TIME;
	}

	public void setTIME(Date tIME) {
		TIME = tIME;
	}

	public int getEXAMNO() {
		return EXAMNO;
	}

	public void setEXAMNO(int eXAMNO) {
		EXAMNO = eXAMNO;
	}
}
