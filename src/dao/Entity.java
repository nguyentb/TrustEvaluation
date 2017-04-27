/**
 * 
 */
package dao;

import java.sql.*;
/**
 * @author nguyentb
 * @date 27042017
 *
 */
public class Entity {
	private int entity_id;
	private String entity_name;
	private double ability;
	private double integrity;
	private double benevolence;
	private double reputation;
	private Timestamp entity_timestamp;

	/* Getters */
    public int getEntity_id()
    {
        return this.entity_id;
    }
    public String getEntity_name()
    {
        return this.entity_name;
    }
    public double getAbility()
    {
        return this.ability;
    }
    public double getIntegrity()
    {
        return this.integrity;
    }
    public double getBenevolence()
    {
        return this.benevolence;
    }
    public double getReputation()
    {
        return this.reputation;
    }
    public Timestamp getEntity_timestamp()
    {
        return this.entity_timestamp;
    }
    
    /* Setters */
    
    public void setEntity_id(int entity_id)
    {
        this.entity_id = entity_id;
    }
    public void setEntity_name(String entity_name)
    {
        this.entity_name = entity_name;
    }
    public void setAbility(double ability)
    {
        this.ability = ability;
    }
    public void setIntegrity(double integrity)
    {
        this.integrity = integrity;
    }
    public void setBenevolence(double benevolence)
    {
        this.benevolence = benevolence;
    }
    public void setReputation(double reputation)
    {
        this.reputation = reputation;
    }
    public void setEntity_timestamp(Timestamp entity_timestamp)
    {
        this.entity_timestamp = entity_timestamp;
    }
}