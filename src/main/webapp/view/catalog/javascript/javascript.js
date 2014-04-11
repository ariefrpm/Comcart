function getRequestObject(){
	if(window.ActiveXObject){
		return new ActiveXObject("Microsoft.XMLHTTP");
	}else if(window.XMLHttpRequest){
		return new XMLHttpRequest();
	}else{
		return null;
	}
}
function getRegion(query){	
	var request = getRequestObject();
	var address = "/ComCart.co.cc/GetRegion?id="+query;	
	request.onreadystatechange = function() { handleResponse(request);};
	request.open("GET",address,true);
	request.send(null);	
}
function handleResponse(request){
	if((request.readyState==4)&&(request.status==200)){		
		document.getElementById("region").innerHTML=request.responseText;
	}
}