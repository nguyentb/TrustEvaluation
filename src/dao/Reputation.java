package dao;
import java.sql.*;

public class Reputation {
	private int reputation_id;
	private float reputation_value;
	private Timestamp reputation_timestamp;
	
	/* Getters */
	public int getReputation_id(){
		return this.reputation_id;		
	}
	public float getReputation_value(){
		return this.reputation_value;		
	}
	public Timestamp getReputation_timestamp(){
		return this.reputation_timestamp;		
	}
	
	/* Setters */
	public void setReputation_id(int reputation_id){
		this.reputation_id = reputation_id;		
	}
	public void setReputation_value(float reputation_value){
		this.reputation_value = reputation_value;		
	}
	public void setReputation_id(Timestamp reputation_timestamp){
		this.reputation_timestamp = reputation_timestamp;		
	}
}
