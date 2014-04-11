<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<div class="div1">
  <div class="div2">
  	<a href="<html:rewrite action='/home'/>">
  	<img src="<html:rewrite page='/images/logo.jpg'/>" alt="" border="0" />
  	</a>
  </div>
  <div class="div3">
  	<form action="<html:rewrite action='/actions/catalog/search'/>" id="search">
  		<img src="<html:rewrite page='/view/catalog/images/icon_search.png'/>" alt="" border="0"/>
		<input type="text" name="query" size="20"/>
		<a href="#" onclick="search.submit();" class="button">
			<span><bean:message key="text.search"/></span>
		</a>
	</form>
  </div>
</div>
<div class="div4">
  <div class="div5">
  	<a href="<html:rewrite page='/index.jsp'/>">
  		<img src="<html:rewrite page='/view/catalog/images/icon_home.png'/>" alt="" border="0" /><bean:message key="text.home"/>
  	</a>
  </div>
  <div class="div6">
  	<a href="<html:rewrite action='/actions/catalog/locale'/>?method=english">
  		<img src="<html:rewrite page='/view/catalog/images/en.png'/>" alt="English" border="0"/>
  	</a>
  	<a href="<html:rewrite action='/actions/catalog/locale'/>?method=indonesian">
  		<img src="<html:rewrite page='/view/catalog/images/id.png'/>" alt="Indonesian" border="0"/>
  	</a>
  </div>
</div>
