<%--
- list.jsp
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
	<acme:input-textbox code="auditor.auditRecord.form.label.code" path="code"/>
	<acme:input-moment code="auditor.auditRecord.form.label.auditPeriodStart" path="auditPeriodStart"/>
	<acme:input-moment code="auditor.auditRecord.form.label.auditPeriodEnd" path="auditPeriodEnd"/>
	<acme:input-select code="auditor.auditRecord.form.label.mark" path="mark" choices="${marks}"/>
	<acme:input-url code="auditor.auditRecord.form.label.link" path="link"/>
	<acme:input-checkbox code="auditor.auditRecord.form.label.published" path="published"/>

	<jstl:choose>	 
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="auditor.auditRecord.form.button.create" action="/auditor/audit-record/create?masterId=${masterId}"/>
		</jstl:when>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete') && published == false}">
			<acme:submit code="auditor.auditRecord.form.button.delete" action="/auditor/audit-record/delete"/>
			<acme:submit code="auditor.auditRecord.form.button.update" action="/auditor/audit-record/update?masterId=${masterId}"/>
			<acme:submit code="auditor.auditRecord.form.button.publish" action="/auditor/audit-record/publish"/>
		</jstl:when>
	</jstl:choose>
</acme:form>