package room.management.room.bean;

import javax.persistence.*;

@Entity
@Table(name = "facility")
public class Facility {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "facility_id")
	private int id;

	@Column(name = "facility_name")
	private String facilityName;

	public Facility() {
		
	}

	public Facility(int id, String facilityName) {
		this.id = id;
		this.facilityName = facilityName;
	}


}
