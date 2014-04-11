package id.ac.bsi.comcart.models;
import java.io.Serializable;
public class CountryDTO implements Serializable{
	private int countryId=0;
	private String name="";
	public int getCountryId(){
		return countryId;
	}
	public void setCountryId(int countryId){
		this.countryId = countryId;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
}