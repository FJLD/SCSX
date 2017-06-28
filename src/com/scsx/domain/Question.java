package com.scsx.domain;

public class Question {

	@Override
	public String toString() {
		return "Question [QNO=" + QNO + ", ANS=" + ANS + ", BANK=" + BANK + ", OPTION1=" + OPTION1 + ", OPTION2="
				+ OPTION2 + ", OPTION3=" + OPTION3 + ", OPTION4=" + OPTION4 + ", userANS=" + userANS + "]";
	}

	private int QNO;
	private int ANS;
	private String BANK;
	private String OPTION1;
	private String OPTION2;
	private String OPTION3;
	private String OPTION4;
	private int userANS;

	public int getUserANS() {
		return userANS;
	}

	public void setUserANS(int userANS) {
		this.userANS = userANS;
	}

	public Question() {
	}
	
	private Question(int QNO, String BANK, String OPTION1, String OPTION2, String OPTION3, String OPTION4) {
		this.QNO = QNO;
		this.BANK = BANK;
		this.OPTION1 = OPTION1;
		this.OPTION2 = OPTION2;
		this.OPTION3 = OPTION3;
		this.OPTION4 = OPTION4;
	}
	
	public Question(int QNO, String BANK, String OPTION1, String OPTION2, String OPTION3, String OPTION4, int ANS) {
		this(QNO, BANK, OPTION1, OPTION2, OPTION3, OPTION4);
		this.ANS = ANS;
	}
	
	public Question(int QNO, String BANK, String OPTION1, String OPTION2, String OPTION3, String OPTION4, String ans) {
		this(QNO, BANK, OPTION1, OPTION2, OPTION3, OPTION4);
		setAnswerChoices(ans);
	}

	public int getQNO() {
		return QNO;
	}

	public void setQNO(int qNO) {
		QNO = qNO;
	}

	public int getANS() {
		return ANS;
	}

	public void setANS(int aNS) {
		ANS = aNS;
	}

	public String getBANK() {
		return BANK;
	}

	public void setBANK(String bANK) {
		BANK = bANK;
	}

	public String getOPTION1() {
		return OPTION1;
	}

	public void setOPTION1(String oPTION1) {
		OPTION1 = oPTION1;
	}

	public String getOPTION2() {
		return OPTION2;
	}

	public void setOPTION2(String oPTION2) {
		OPTION2 = oPTION2;
	}

	public String getOPTION3() {
		return OPTION3;
	}

	public void setOPTION3(String oPTION3) {
		OPTION3 = oPTION3;
	}

	public String getOPTION4() {
		return OPTION4;
	}

	public void setOPTION4(String oPTION4) {
		OPTION4 = oPTION4;
	}
	
	public String getAnswerChoices() {
		boolean a = ANS / 1000 != 0? true : false;
		boolean b = ANS / 100 % 10 != 0? true : false;
		boolean c = ANS / 10 % 10 != 0? true : false;
		boolean d = ANS % 10 != 0? true : false;
		String ans = new String();
		if (a) ans += "A";
		if (b) ans += "B";
		if (c) ans += "C";
		if (d) ans += "D";
		return ans;
	}
	
	public static int answerString2Int(String ans) {
		ans = ans.toUpperCase();
		int nAns = 0;
		if (ans.contains("A")) nAns += 1000;
		if (ans.contains("B")) nAns += 100;
		if (ans.contains("C")) nAns += 10;
		if (ans.contains("D")) nAns += 1;
		return nAns;
	}
	
	public void setAnswerChoices(String ans) {
		setANS(answerString2Int(ans));
	}
}
