package id.ac.bsi.comcart.models;

import com.ecommerce.jpa.ProductDTO;

public class ShoppingCartItem{
	private ProductDTO item;
	private int numItems;
	private String itemName;
	
	public ShoppingCartItem(ProductDTO item){
		setItem(item);
		setNumItems(1);
	}
	public String getItemName(){
		return getItem().getName();
	}
	public ProductDTO getItem(){
		return item;
	}
	public void setItem(ProductDTO item){
		this.item = item;
	}
	public String getItemId(){
		return getItem().getProductId();
	}
	public float getPrice(){
		return getItem().getPrice();
	}
	public float getSpecial(){
		return getItem().getSpecial();
	}
	public String getImage(){
		return getItem().getImage();
	}
	public int getNumItems(){
		return numItems;
	}
	public void setNumItems(int n){
		numItems = n;
	}
	public void increamentNumItems(){
		setNumItems(getNumItems()+1);
	}
	public float getTotalPrice(){
		return getNumItems()*getPrice();
	}
	public void setPrice(Float price){
		item.setPrice(price);
	}
}