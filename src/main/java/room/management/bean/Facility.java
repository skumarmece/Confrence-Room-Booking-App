package room.management.bean;

import javax.persistence.*;

@Entity
@Table(name = "facility")
public class Facility {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "facility_id")
	private long id;
	
	@Column(name = "facility_name")
	private String facilityName;

	public Facility() {
	}

	public Facility(int id, String facilityName) {
		this.id = id;
		this.facilityName = facilityName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	@Override
	public String toString() {
		return "Facility [id=" + id + ", facilityName=" + facilityName + "]";
	}

}
