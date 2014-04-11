package id.ac.bsi.comcart.models;
import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;

public class ProductFormBean extends ActionForm
{
  private FormFile theFile;
  private String name = "";
  private String categoryId = "";
  private String price = "";
  private String quantity = "";
  private String description = "";
  private String status = "";
  private String special = "";
  private String image = "";
  public String getImage(){
  	return image;
  }
  public void setImage(String image){
  	this.image = image;
  }
  public String getSpecial(){
  	return special;
  }
  public void setSpecial(String special){
  	this.special = special;
  }
  public String getStatus(){
  	return status;
  }
  public void setStatus(String status){
  	this.status = status;
  }
  public String getDescription(){
  	return description;
  }
  public void setDescription(String description){
  	this.description = description;
  }
  public String getQuantity(){
  	return quantity;
  }
  public void setQuantity(String quantity){
  	this.quantity = quantity;
  }
  public String getPrice(){
  	return price;
  }
  public void setPrice(String price){
  	this.price = price;
  }
  public String getCategoryId(){
  	return categoryId;
  }
  public void setCategoryId(String categoryId){
  	this.categoryId = categoryId;
  }
  public String getName(){
  	return name;
  }
  public void setName(String name){
  	this.name = name;
  }

  public FormFile getTheFile() {
    return theFile;
  }
  
  public void setTheFile(FormFile theFile) {
    this.theFile = theFile;
  }
}