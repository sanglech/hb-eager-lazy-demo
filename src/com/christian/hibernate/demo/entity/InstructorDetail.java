package com.christian.hibernate.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


//annotate the class as an entity and map to db table
@Entity
@Table(name="instructor_detail")
public class InstructorDetail {
	
	//define the fields
	//annotate the fields with db column names
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@Column (name="id")
	private int id;
	
	@Column(name="youtube_channel")
	private String youtubeChannel;
	
	@Column(name="hobby")
	private String hobby;
	
	
	// add new field for instructor
	@OneToOne(mappedBy="instructorDetail",cascade= {CascadeType.DETACH,
													CascadeType.MERGE,
													CascadeType.PERSIST,
													CascadeType.REFRESH})
	private Instructor instructor;
	

	//create constructors
	
	public InstructorDetail(){
		
	}

	//generate getter/setter methods
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getYoutubeChannel() {
		return youtubeChannel;
	}


	public void setYoutubeChannel(String youtubeChannel) {
		this.youtubeChannel = youtubeChannel;
	}


	public String getHobby() {
		return hobby;
	}


	public void setHobby(String hobby) {
		this.hobby = hobby;
	}


	public InstructorDetail(String youtubeChannel, String hobby) {
		this.youtubeChannel = youtubeChannel;
		this.hobby = hobby;
	}
	
	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	//generate to string
	@Override
	public String toString() {
		
		return "InstructionDetail: [id= " + id+ ", youtube_channel: "+ youtubeChannel+ " ,hobby: "+ hobby+"]";
	}


}
