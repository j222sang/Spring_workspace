package www.dream.com.party.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Party {
	private long id;
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;
	private boolean sex;
	private String job;
	
	private List<ContactPoint> listContactPoint = new ArrayList<>();

	
	public void addCP(ContactPoint cp) {
		listContactPoint.add(cp);
	}
	
}