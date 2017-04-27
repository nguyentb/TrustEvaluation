package dao;
import java.sql.*;

public class Interaction {
	private int interaction_id;
	private int source_id;
	private int destination_id;
	private double interaction_value;
	private Timestamp interaction_timestamp;
	
	/* Getters */
	public int getInteraction_id(){
		return this.interaction_id;		
	}
	public int getSource_id(){
		return this.source_id;		
	}
	public int getDestination_id(){
		return this.destination_id;		
	}
	public double getInteraction_value(){
		return this.interaction_value;		
	}
	public Timestamp getInteraction_timestamp(){
		return this.interaction_timestamp;		
	}
	
	/* Setters */
	public void setInteraction_id(int interaction_id){
		this.interaction_id = interaction_id;		
	}
	public void setSource_id(int source_id){
		this.source_id = source_id;		
	}
	public void setDestination_id(int destination_id){
		this.destination_id = destination_id;		
	}
	public void setInteraction_value(double interaction_value){
		this.interaction_value = interaction_value;		
	}
	public void setInteraction_timestamp(Timestamp interaction_timestamp){
		this.interaction_timestamp = interaction_timestamp;		
	}
}
