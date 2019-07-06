package room.management.bean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "conference")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Conference {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToOne(fetch = FetchType.EAGER)
	private User user;

	@Column(name = "description")
	private String description;

	@Column(name = "name")
	private String name;

	@Column(name = "start_time")
	private java.sql.Date startTime;

	@Column(name = "end_time")
	private Date endTime;

	@OneToOne(fetch = FetchType.EAGER)
	private Room room;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public java.sql.Date getStartTime() {
		return startTime;
	}

	public void setStartTime(java.sql.Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "Conference [id=" + id + ", user=" + user + ", description=" + description + ", name=" + name
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", room=" + room + "]";
	}

}
