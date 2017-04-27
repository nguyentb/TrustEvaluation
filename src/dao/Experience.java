package dao;
import java.sql.*;

public class Experience {
	private int experience_id;
	private int source_id;
	private int destination_id;
	private double experience_value;
	private double prev_value;

	private Timestamp experience_timestamp;
	
	/* Getters */
	public int getExperience_id(){
		return this.experience_id;		
	}
	public int getSource_id(){
		return this.source_id;		
	}
	public int getDestination_id(){
		return this.destination_id;		
	}
	public double getExperience_value(){
		return this.experience_value;		
	}
	public double getPrev_value(){
		return this.prev_value;		
	}	
	public Timestamp getExperience_timestamp(){
		return this.experience_timestamp;		
	}
	
	/* Setters */
	public void setExperience_id(int experience_id){
		this.experience_id = experience_id;		
	}
	public void setSource_id(int source_id){
		this.source_id = source_id;		
	}
	public void setDestination_id(int destination_id){
		this.destination_id = destination_id;		
	}
	public void setExperience_value(double experience_value){
		this.experience_value = experience_value;		
	}
	public void setPrev_value(double prev_value){
		this.prev_value = prev_value;		
	}	
	public void setExperience_timestamp(Timestamp experience_timestamp){
		this.experience_timestamp = experience_timestamp;		
	}
}
