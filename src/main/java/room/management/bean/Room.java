package room.management.bean;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "room")
public class Room implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "room_id")
	private long id;

	@Column(name = "name")
	private String name;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "room_category", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "room_id"))
	private Category category;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "room_facility", joinColumns = @JoinColumn(name = "facility_id"), inverseJoinColumns = @JoinColumn(name = "room_id"))
	private Set<Facility> facilities;

	public Room(long id, String name, Category category) {
		this.id = id;
		this.name = name;
		this.category = category;
	}

	public Room() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Facility> getFacilities() {
		return facilities;
	}

	public void setFacilities(Set<Facility> facilities) {
		this.facilities = facilities;
	}

}
