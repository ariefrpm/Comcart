package id.ac.bsi.comcart.models;
import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;

public class RekeningFormBean extends ActionForm{	
		    
  private FormFile theFile;
  private String bank = "";
  private String rekening = "";
  private String account = "";
  private String image = "";
  public String getRekening(){
  	return rekening;
  }
  public void setRekening(String rekening){
  	this.rekening = rekening;
  }
  public String getImage(){
  	return image;
  }
  public void setImage(String image){
  	this.image = image;
  }
  public String getAccount(){
  	return account;
  }
  public void setAccount(String account){
  	this.account = account;
  }  
  public String getBank(){
  	return bank;
  }
  public void setBank(String bank){
  	this.bank = bank;
  }

  public FormFile getTheFile() {
    return theFile;
  }
  
  public void setTheFile(FormFile theFile) {
    this.theFile = theFile;
  }
}