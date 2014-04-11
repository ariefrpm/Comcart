package id.ac.bsi.comcart.models;
import java.io.Serializable;
public class OrderHistoryDTO implements Serializable{
	private int orderHistoryId=0;
	private String orderId;
	private int statusId=0;
	private String dateAdded="";
	private String comment="";
	private String status="";
	
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public String getComment(){
		return comment;
	}
	public void setComment(String comment){
		this.comment = comment;
	}
	public String getDateAdded(){
		return dateAdded;
	}
	public void setDateAdded(String dateAdded){
		this.dateAdded = dateAdded;
	}
	public int getStatusId(){
		return statusId;
	}
	public void setStatusId(int statusId){
		this.statusId = statusId;
	}
	public int getOrderHistoryId(){
		return orderHistoryId;
	}
	public void setOrderHistoryId(int orderHistoryId){
		this.orderHistoryId = orderHistoryId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}	
}