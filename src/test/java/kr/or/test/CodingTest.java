package kr.or.test;

public class CodingTest {

	public static void main(String[] args) {
		String w = ")(";
		String u = w.substring(1,2);
		System.out.println("w를 u와 v로 분리할 때 u는 " + u);
		CodingTest cd = new CodingTest();
		boolean balanceCheck = cd.isvalidString(")(");
		System.out.println("균형잡힌 문자열인지 체크 " + balanceCheck);
	}

	private boolean isvalidString(String u) {
		int balanceCount = 0;
		for (int i = 0; i < u.length(); i++) {
			String tmpChar = u.substring(i, i+1);
			if ("(".equals(tmpChar)) {
				balanceCount++;
			} else if (")".equals(tmpChar)) {
				balanceCount--;
			}
			if (balanceCount < 0) {
				return false;
			}
		}
		return true;
	}

}
