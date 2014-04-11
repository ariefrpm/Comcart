package id.ac.bsi.comcart.models;
import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;

public class CategoryFormBean extends ActionForm{	
		    
  private FormFile theFile;
  private String name = "";
  private String parent = "";
  private String description = "";
  private String image = "";
  public String getParent(){
  	return parent;
  }
  public void setParent(String parent){
  	this.parent = parent;
  }
  public String getImage(){
  	return image;
  }
  public void setImage(String image){
  	this.image = image;
  }
  public String getDescription(){
  	return description;
  }
  public void setDescription(String description){
  	this.description = description;
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