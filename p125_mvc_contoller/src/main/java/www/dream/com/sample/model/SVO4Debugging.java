package www.dream.com.sample.model;

import lombok.Setter;

public class SVO4Debugging {
	private String name;
	private int age;
	
	public SVO4Debugging () {
		
	}
	
	public void setName(String name) {
		this.name = name;
	}



	public void setAge(int age) {
		this.age = age;
	}



	@Override
	public String toString() {
		return "SampleVO [name=" + name + ", age=" + age + "]";
	}
}
