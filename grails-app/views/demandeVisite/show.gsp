
<%@ page import="toulousemusee.DemandeVisite" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'demandeVisite.label', default: 'DemandeVisite')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-demandeVisite" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-demandeVisite" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list demandeVisite">
			
				<g:if test="${demandeVisiteInstance?.codeVisite}">
				<li class="fieldcontain">
					<span id="codeVisite-label" class="property-label"><g:message code="demandeVisite.codeVisite.label" default="Code Visite" /></span>
					
						<span class="property-value" aria-labelledby="codeVisite-label"><g:fieldValue bean="${demandeVisiteInstance}" field="codeVisite"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${demandeVisiteInstance?.debutPeriode}">
				<li class="fieldcontain">
					<span id="debutPeriode-label" class="property-label"><g:message code="demandeVisite.debutPeriode.label" default="Debut Periode" /></span>
					
						<span class="property-value" aria-labelledby="debutPeriode-label"><g:formatDate date="${demandeVisiteInstance?.debutPeriode}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${demandeVisiteInstance?.finPeriode}">
				<li class="fieldcontain">
					<span id="finPeriode-label" class="property-label"><g:message code="demandeVisite.finPeriode.label" default="Fin Periode" /></span>
					
						<span class="property-value" aria-labelledby="finPeriode-label"><g:formatDate date="${demandeVisiteInstance?.finPeriode}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${demandeVisiteInstance?.nbPersonne}">
				<li class="fieldcontain">
					<span id="nbPersonne-label" class="property-label"><g:message code="demandeVisite.nbPersonne.label" default="Nb Personne" /></span>
					
						<span class="property-value" aria-labelledby="nbPersonne-label"><g:fieldValue bean="${demandeVisiteInstance}" field="nbPersonne"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${demandeVisiteInstance?.statut}">
				<li class="fieldcontain">
					<span id="statut-label" class="property-label"><g:message code="demandeVisite.statut.label" default="Statut" /></span>
					
						<span class="property-value" aria-labelledby="statut-label"><g:fieldValue bean="${demandeVisiteInstance}" field="statut"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:demandeVisiteInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${demandeVisiteInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
