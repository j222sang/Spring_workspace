package www.dream.com.party.model;

import lombok.Data;

@Data
public class ContactPoint {
	private ContactPointType contactPointType;
	private String value;
}
