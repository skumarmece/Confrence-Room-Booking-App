package room.management.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@NamedQueries({
	@NamedQuery(name = "Conference.findByRoomId", query = "SELECT c FROM Conference c WHERE c.room.id = :roomId"),
	@NamedQuery(name = "Conference.findConferenceByRoomId", query = "SELECT c FROM Conference c WHERE c.room.id = :roomId and c.id != :conferenceId")
})
@Table(name = "conference")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Conference implements Serializable {

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
	private Date startTime;

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

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Conference [id=" + id + ", user=" + user + ", description=" + description + ", name=" + name
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", room=" + room + "]";
	}


}
