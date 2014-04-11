package id.ac.bsi.comcart.models;
import java.io.Serializable;
public class AdminDTO implements Serializable{
	private int adminId=0;
	private String name="";
	private String password="";
	private String status="";
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public int getAdminId(){
		return adminId;
	}
	public void setAdminId(int adminId){
		this.adminId = adminId;
	}	
}
