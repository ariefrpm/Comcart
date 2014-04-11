package id.ac.bsi.comcart.models;
import java.io.Serializable;
public class StatusDTO implements Serializable{
	private int statusId=0;
	private String name="";
	public int getStatusId(){
		return statusId;
	}
	public void setStatusId(int statusId){
		this.statusId = statusId;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}	
}