package www.dream.com.restStudy.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class MyVO {
	private int aa;
	private String bb;

	public MyVO() {
	}

	private List<DetailVO> df = new ArrayList<>();
	
	public void add(DetailVO dd) {
		df.add(dd);
	}
	
	public String getKey() {
		return "ff"+aa;
	}
	
	public MyVO getObj() {
		try {
			return (MyVO)this.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		MyVO clone = new MyVO();
		clone.aa = aa;
		clone.bb = bb;
		return clone;
	}

}
