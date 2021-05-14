package www.dream.com.party.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import www.dream.com.sample.model.SampleVO;

public class Party {
	private long id;
	private String name;
	private Date birthDate;
	private boolean sex;
	private String job;
	
	private List<ContactPoint> listContactPoint = new ArrayList<>();;
}
