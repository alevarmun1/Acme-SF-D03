<%--
- form.jsp
-
- Copyright (C) 2012-2024 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form> 
	<acme:list-column code="sponsor.Invoice.form.label.code" path="code"/>
	<acme:list-column code="sponsor.Invoice.form.label.registrationTime" path="registrationTime"/>
	<acme:list-column code="sponsor.Invoice.form.label.dueDate" path="dueDate"/>
	<acme:list-column code="sponsor.Invoice.form.label.quantity" path="quantity"/>
	<acme:list-column code="sponsor.Invoice.form.label.tax" path="tax"/>
	<acme:list-column code="sponsor.Invoice.form.label.link" path="link"/>
	<acme:list-column code="sponsor.Invoice.form.label.published" path="published"/>
	<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete') && published == false}">
			<acme:submit code="sponsor.Invoice.form.button.update" action="/sponsor/invoice/update"/>
			<acme:submit code="sponsor.Invoice.form.button.delete" action="/sponsor/invoice/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="sponsor.Invoice.form.button.create" action="/sponsor/invoice/create?masterId=${masterId}"/>
		</jstl:when>		
	</jstl:choose>	
</acme:form>