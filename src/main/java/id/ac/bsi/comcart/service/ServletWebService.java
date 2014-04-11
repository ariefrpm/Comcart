package id.ac.bsi.comcart.service;

import id.ac.bsi.comcart.models.AddressBean;
import id.ac.bsi.comcart.models.CategoryBean;
import id.ac.bsi.comcart.models.CategoryDTO;
import id.ac.bsi.comcart.models.ConfirmedOrderBean;
import id.ac.bsi.comcart.models.CustomerBean;
import id.ac.bsi.comcart.models.OrderBean;
import id.ac.bsi.comcart.models.ProductBean;
import id.ac.bsi.comcart.models.RekeningBean;
import id.ac.bsi.comcart.models.ShoppingCartItem;
import id.ac.bsi.comcart.models.StatusBean;
import id.ac.bsi.comcart.utils.ImageUtil;
import id.ac.bsi.comcart.utils.Utils;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.jpa.ConfirmedOrderDTO;
import com.ecommerce.jpa.CustomerDTO;
import com.ecommerce.jpa.EnumDeliveryService;
import com.ecommerce.jpa.EnumOrderStatus;
import com.ecommerce.jpa.EnumRequestCode;
import com.ecommerce.jpa.OrderDTO;
import com.ecommerce.jpa.OrderProductDTO;
import com.ecommerce.jpa.ProductDTO;
import com.ecommerce.jpa.RekeningDTO;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.Part;

public class ServletWebService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String requestCode = request.getParameter(EnumRequestCode.getRequestKey());
        
        response.setContentType("application/json");
        
        PrintWriter out = response.getWriter();
        
        handleRequest(out, requestCode, request);
        
        out.close();        

    }

	private void handleRequest(PrintWriter out, String code, HttpServletRequest request) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
        JsonFactory jsonFactory = new JsonFactory();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
		if(code.equalsIgnoreCase(EnumRequestCode.PRODUCTLIST.getText())){
			String categoryId = request.getParameter("categoryId");
	        String from = request.getParameter("from");
	        String to = request.getParameter("to");
	        
			ArrayList<ProductDTO> products = (ArrayList<ProductDTO>) ProductBean.getProductByCategory(categoryId, Utils.convertInt(from), Utils.convertInt(to));
			
//			System.out.println("ProductListLog:"+products.size());
			
            mapper.writeValue(out, products);
		}else if(code.equalsIgnoreCase(EnumRequestCode.SENDNEWORDER.getText())){
			String order = request.getParameter("order");
	        String orderProduct = request.getParameter("orderProduct");
	        
	        JsonParser jp = jsonFactory.createJsonParser(order);
			OrderDTO orderDTO = mapper.readValue(jp, OrderDTO.class);
			
			jp = jsonFactory.createJsonParser(orderProduct);
			JavaType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, OrderProductDTO.class);
			ArrayList<OrderProductDTO> orderProductDTO = mapper.readValue(jp, type);
			
			String message = "Failed";
			try {
				if(orderDTO.getOrderId() != null){
					if(orderDTO.getOrderId().trim().length() > 0){
						message = OrderBean.updatePrescriptionConfirm(orderDTO, orderProductDTO);
					}else{
						message = OrderBean.addOrderComplete(orderDTO, orderProductDTO);
					}
				}else{
					message = OrderBean.addOrderComplete(orderDTO, orderProductDTO);
				}
				
			} catch (SQLException e) {
				message = e.getMessage();
				e.printStackTrace();
			}
			mapper.writeValue(out, message);
			
//			String regId = CustomerBean.findCustomerRegIdByEmail(orderDTO.getCustomerEmail());
			
//			Utils.sendGCMMessage(orderDTO.getCustomerEmail(), regId, orderDTO.getOrderId(), EnumOrderStatus.NEW.getCategoryCode(), null, 2);
			
		}else if(code.equalsIgnoreCase(EnumRequestCode.SENDPRESCRIPTIONORDER.getText())){
			String email = request.getParameter("email");
	        
			MultipartParser parser = new MultipartParser(request, 1024 * 1024 * 1024);
			
			String filePath = getServletContext().getRealPath("/") +"images";
			List<Image> img = new ArrayList<Image>();
			
    		if (parser != null) {
    			Part part;
    			while ((part = (Part) parser.readNextPart()) != null) {
    				if (part instanceof FilePart) {
    					File fileToCreate = new File(filePath, part.getName());        
    	        		if(!fileToCreate.exists()){
    	        			InputStream is = ((FilePart) part).getInputStream();
    			        	Image imageF = ImageIO.read(is);
    			        	img.add(imageF);
    	        		}
    				}
    			}
    		}
    		
    		String message = "Failed";
    		try {
				message = OrderBean.addOrderPrescription(email, img, filePath);
			} catch (SQLException e) {
				message = e.getMessage();
				e.printStackTrace();
			}
			mapper.writeValue(out, message);
		}else if(code.equalsIgnoreCase(EnumRequestCode.USERLOGIN.getText())){
			String regId = request.getParameter("regId");
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");
	        
			CustomerDTO cust = CustomerBean.validateCustomer(email, password, regId);
			mapper.writeValue(out, cust);
		}else if(code.equalsIgnoreCase(EnumRequestCode.USERREGISTRATION.getText())){
			String customer = request.getParameter("customer");
			
			JsonParser jp = jsonFactory.createJsonParser(customer);
			CustomerDTO customerDTO = mapper.readValue(jp, CustomerDTO.class);
			
			String message = CustomerBean.createCustomerMobile(customerDTO);
			
			mapper.writeValue(out, message);
		}else if(code.equalsIgnoreCase(EnumRequestCode.EDITPROFILE.getText())){
			String customer = request.getParameter("customer");
			
			JsonParser jp = jsonFactory.createJsonParser(customer);
			CustomerDTO customerDTO = mapper.readValue(jp, CustomerDTO.class);
			
			String message = "Failed";
			if(customerDTO.getPassword() == null){
				message = CustomerBean.updateCustomerMobileWithoutPass(customerDTO);
			}else{
				message = CustomerBean.updateCustomerMobileWithPass(customerDTO);
			}
			
			mapper.writeValue(out, message);
		}else if(code.equalsIgnoreCase(EnumRequestCode.CITYLIST.getText())){
			List<String> cities = AddressBean.getCityList();
			mapper.writeValue(out, cities);
		}else if(code.equalsIgnoreCase(EnumRequestCode.INBOXDETAIL.getText())){
			String inboxId = request.getParameter("inboxId");
			String inbox = StatusBean.getInboxDetail(Integer.parseInt(inboxId));
			mapper.writeValue(out, inbox);
		}else if(code.equalsIgnoreCase(EnumRequestCode.REJECT.getText())){
			String orderId = request.getParameter("orderId");
			String comment = request.getParameter("comment");
			
			JsonParser jp = jsonFactory.createJsonParser(orderId);
			
			JavaType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, String.class);
			ArrayList<String> orders = mapper.readValue(jp, type);
			
			
			String message = "Failed";
			try {
				message = OrderBean.updateStatusNew(orders, EnumOrderStatus.REJECT.getCategoryCode(), comment);
			} catch (SQLException e) {
				message = e.getMessage();
				e.printStackTrace();
			}
			mapper.writeValue(out, message);
		}else if(code.equalsIgnoreCase(EnumRequestCode.REGIONLIST.getText())){
			String city = request.getParameter("city");
			List<String> region = AddressBean.getRegionList(city);
			mapper.writeValue(out, region);
		}else if(code.equalsIgnoreCase(EnumRequestCode.SUBREGIONLIST.getText())){
			String region = request.getParameter("region");
			List<String> subRegion = AddressBean.getSubRegionList(region);
			mapper.writeValue(out, subRegion);
		}else if(code.equalsIgnoreCase(EnumRequestCode.DELIVERYPRICE.getText())){
			String ds = request.getParameter("deliveryService");
			float price = 0;
			if(ds.equalsIgnoreCase(EnumDeliveryService.JNEONS.getText())){
				price = 30000;
			}else if(ds.equalsIgnoreCase(EnumDeliveryService.JNEREGULER.getText())){
				price = 20000;
			}else if(ds.equalsIgnoreCase(EnumDeliveryService.TIKI.getText())){
				price = 15000;
			}else if(ds.equalsIgnoreCase(EnumDeliveryService.POS.getText())){
				price = 10000;
			}else if(ds.equalsIgnoreCase(EnumDeliveryService.INOKURINDO.getText())){
				price = 5000;
			}else{
				price = 1000;
			}
			mapper.writeValue(out, price);
		}else if(code.equalsIgnoreCase(EnumRequestCode.BANKLIST.getText())){
			ArrayList<RekeningDTO> banks = (ArrayList<RekeningDTO>) RekeningBean.getRekenings();
			mapper.writeValue(out, banks);
		}else if(code.equalsIgnoreCase(EnumRequestCode.ALLBANKLIST.getText())){
			ArrayList<String> banks = (ArrayList<String>) RekeningBean.getAllBank();
			mapper.writeValue(out, banks);
		}else if(code.equalsIgnoreCase(EnumRequestCode.CONFIRMPAYMENT.getText())){
			String confirmedOrder = request.getParameter("confirmedOrder");
	        JsonParser jp = jsonFactory.createJsonParser(confirmedOrder);
	        ConfirmedOrderDTO order = mapper.readValue(jp, ConfirmedOrderDTO.class);
			
	        String message = "Failed";
			try {
				message = ConfirmedOrderBean.addConfirmedOrder(order);
			} catch (SQLException e) {
				message = e.getMessage();
				e.printStackTrace();
			}
			mapper.writeValue(out, message);
		}else if(code.equalsIgnoreCase(EnumRequestCode.PRODUCTCATEGORY.getText())){
			List<String> categories = (ArrayList<String>) CategoryBean.getTitleCategories();
			mapper.writeValue(out, categories);
		}else{
			
		}
	}
}
