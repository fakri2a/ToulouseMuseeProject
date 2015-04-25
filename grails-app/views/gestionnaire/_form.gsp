<%@ page import="toulousemusee.Gestionnaire" %>



<div class="fieldcontain ${hasErrors(bean: gestionnaireInstance, field: 'nom', 'error')} required">
	<label for="nom">
		<g:message code="gestionnaire.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nom" required="" value="${gestionnaireInstance?.nom}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: gestionnaireInstance, field: 'musee', 'error')} required">
	<label for="musee">
		<g:message code="gestionnaire.musee.label" default="Musee" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="musee" name="musee.id" from="${toulousemusee.Musee.list()}" optionKey="id" required="" value="${gestionnaireInstance?.musee?.id}" class="many-to-one"/>

</div>

