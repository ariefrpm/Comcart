package id.ac.bsi.comcart.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.ecommerce.jpa.EnumInboxCategory;
import com.ecommerce.jpa.GCMOrder;
import com.ecommerce.jpa.GCMOrderItem;
import com.ecommerce.jpa.OrderProductDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class Utils {
	public static int convertInt(String value){
		int result = 0;
		try{
			result = Integer.parseInt(value);
		}catch(NumberFormatException nfe){
			nfe.printStackTrace();
		}
		return result;
	}
	
	public static boolean sendGCMMessage(String email, String regId, String orderId, int orderStatus, List<OrderProductDTO> orderItems, int attemps){
		boolean ret = true;
		
		Sender sender = new Sender("AIzaSyBG-F4K6j8q_D5vUSNjtr7mrcTNkY0V7P0");
		String registrationId = regId;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		
		GCMOrder ord = new GCMOrder();
		ord.setOrder_id(orderId);
		ord.setOrder_status(orderStatus);
		
		List<GCMOrderItem> items = new ArrayList<GCMOrderItem>();
		if(orderItems != null){
			for(OrderProductDTO det : orderItems){
				GCMOrderItem item = new GCMOrderItem();
				item.setPrice(det.getPrice());
				item.setProduct_id(det.getProductId());
				item.setQty(det.getQuantity());
				items.add(item);
			}
		}
		
		ord.setItem((ArrayList<GCMOrderItem>) items);
		
        ObjectMapper mapper = new ObjectMapper();
		String order = "";
		try {
			order = mapper.writeValueAsString(ord);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		String user_id = email;
		String description = "";
		String category = EnumInboxCategory.ORDERSTATUS.getCategory();
		String date = format.format(new Date());
		
        Message message = new Message.Builder()
        						.addData("order", order)
        						.addData("user_id", user_id)
        						.addData("description", description)
        						.addData("category", category)
        						.addData("date", date)
        						.build();
        Result result = null;
		try {
			result = sender.send(message, registrationId, attemps);
		} catch (IOException e) {
			ret = false;
			e.printStackTrace();
		}
		
        System.out.println("Sent message to one device: " + result + "  "+message.toString());
        return ret;
	}
}
