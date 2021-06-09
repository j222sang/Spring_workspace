package javaLangStudy.string;

public class TestTrim {
	public static void main(String[] args) {
		String x = "sasd[dfdsfsdfsd]";
		System.out.println(""+x.indexOf('['));
		System.out.println(""+x.indexOf(']'));
		System.out.println(x.substring(x.indexOf('[') + 1, x.indexOf(']')));

	}
}
