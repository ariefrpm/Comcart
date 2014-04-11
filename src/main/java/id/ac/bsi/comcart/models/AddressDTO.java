package id.ac.bsi.comcart.models;
import java.io.Serializable;
public class AddressDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int addressId=0;
	private int customerId=0;
	private String firstName="";
	private String lastName="";
	private String address="";
	private String city="";
	private int zip=0;
	private String country="";
	private String region="";
	
	public String getFirstName(){
		return firstName;
	}
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	public String getLastName(){
		return lastName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	public int getAddressId(){
		return addressId;
	}
	public void setAddressId(int addressId){
		this.addressId = addressId;
	}
	public int getCustomerId(){
		return customerId;
	}
	public void setCustomerId(int customerId){
		this.customerId = customerId;
	}
	public String getAddress(){
		return address;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public String getCity(){
		return city;
	}
	public void setCity(String city){
		this.city = city;
	}
	public int getZip(){
		return zip;
	}
	public void setZip(int zip){
		this.zip = zip;
	}
	public String getCountry(){
		return country;
	}
	public void setCountry(String country){
		this.country = country;
	}
	public String getRegion(){
		return region;
	}
	public void setRegion(String region){
		this.region = region;
	}
}
