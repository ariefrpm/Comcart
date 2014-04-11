package id.ac.bsi.comcart.actions.catalog;

import id.ac.bsi.comcart.models.ProductBean;
import id.ac.bsi.comcart.models.ShoppingCartItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.ecommerce.jpa.ProductDTO;

public class ShoppingCart{
	private Collection<ShoppingCartItem> shoppingCartItems = new ArrayList<ShoppingCartItem>();
	public float deliveryPrice = 0; 
	public Collection<ShoppingCartItem> getShoppingCartItems(){
		return shoppingCartItems;
	}
	public void setShoppingCartItems(Collection<ShoppingCartItem> items){
		this.shoppingCartItems = items;
	}
	public synchronized void addItem(String itemId){
		ShoppingCartItem item;
		for(Iterator<ShoppingCartItem> it=shoppingCartItems.iterator();it.hasNext();){
			Object o = it.next();
			item = (ShoppingCartItem)o;
			if(item.getItemId().equals(itemId)){
				item.increamentNumItems();
				return;				
			}
		}		
		ProductDTO product = ProductBean.getAProduct(itemId);
		ShoppingCartItem newItem = new ShoppingCartItem(product);
		shoppingCartItems.add(newItem);
	}
	public float getGrandTotal(){
		float grandTotal = 0;
		ShoppingCartItem item;
		for(Iterator<ShoppingCartItem> it=shoppingCartItems.iterator();it.hasNext();){
			Object o = it.next();
			item = (ShoppingCartItem)o;
			grandTotal += item.getTotalPrice();
		}
		grandTotal += deliveryPrice;
		return grandTotal;
	}
	public synchronized void setNumItems(String itemId, int qty){
		ShoppingCartItem item;
		for(Iterator<ShoppingCartItem> it=shoppingCartItems.iterator();it.hasNext();){
			Object o = it.next();
			item = (ShoppingCartItem)o;
			if(item.getItemId().equals(itemId)){
				if(qty<=0){
					shoppingCartItems.remove(item);
				}else{
					item.setNumItems(qty);
				}
			return;
			}
		}
	}
	public synchronized void setPrice(String itemId, float price){
		ShoppingCartItem item;
		for(Iterator<ShoppingCartItem> it=shoppingCartItems.iterator();it.hasNext();){
			Object o = it.next();
			item = (ShoppingCartItem)o;
			if(item.getItemId().equals(itemId)){
				item.setPrice(price);
				return;
			}
		}
	}
}