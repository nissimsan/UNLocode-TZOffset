package io.tradelens.timezone;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UNLocode {
    @Id
    private String unLocode;
    private String unCoordinates;
    
    public String getUnLocode() {
		return unLocode;
	}
    public void setUnLocode(String unLocode) {
		this.unLocode = unLocode;
	}
	public String getUnCoordinates() {
		return unCoordinates;
	}
	public void setUnCoordinates(String unCoordinates) {
		this.unCoordinates = unCoordinates;
	}
}

