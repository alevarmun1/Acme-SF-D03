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
	<acme:input-textbox code="developer.trainingModule.form.label.code" path="code"/>
	<acme:input-moment code="developer.trainingModule.form.label.creationMoment" path="creationMoment"/>
	<acme:input-textbox code="developer.trainingModule.form.label.details" path="details"/>
	<acme:input-select code="developer.trainingModule.form.label.difficultyLevel" path="difficultyLevel" choices="${difficulties}"/>
	<acme:input-moment code="developer.trainingModule.form.label.updateMoment" path="updateMoment"/>
	<acme:input-textbox code="developer.trainingModule.form.label.link" path="link"/>
	<acme:input-double code="developer.trainingModule.form.label.totalTime" path="totalTime"/>
	<acme:input-checkbox code="developer.trainingModule.form.label.published" path="published"/>
	

	<jstl:choose>	 
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="developer.trainingModule.form.button.create" action="/developer/trainingModule/create"/>
		</jstl:when>		
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete') && published == false}">
			<acme:submit code="developer.trainingModule.form.button.delete" action="/developer/trainingModule/delete"/>
			<acme:submit code="developer.trainingModule.form.button.update" action="/developer/trainingModule/update"/>
			
		</jstl:when>
	</jstl:choose>
</acme:form>