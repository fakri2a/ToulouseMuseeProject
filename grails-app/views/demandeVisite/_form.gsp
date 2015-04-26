<%@ page import="toulousemusee.DemandeVisite" %>



<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'codeVisite', 'error')} required">
	<label for="codeVisite">
		<g:message code="demandeVisite.codeVisite.label" default="Code Visite" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="codeVisite" required="" value="${demandeVisiteInstance?.codeVisite}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'debutPeriode', 'error')} required">
	<label for="debutPeriode">
		<g:message code="demandeVisite.debutPeriode.label" default="Debut Periode" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="debutPeriode" precision="day"  value="${demandeVisiteInstance?.debutPeriode}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'finPeriode', 'error')} required">
	<label for="finPeriode">
		<g:message code="demandeVisite.finPeriode.label" default="Fin Periode" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="finPeriode" precision="day"  value="${demandeVisiteInstance?.finPeriode}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'nbPersonne', 'error')} required">
	<label for="nbPersonne">
		<g:message code="demandeVisite.nbPersonne.label" default="Nb Personne" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="nbPersonne" type="number" min="1" max="6" value="${demandeVisiteInstance.nbPersonne}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'statut', 'error')} required">
	<label for="statut">
		<g:message code="demandeVisite.statut.label" default="Statut" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="statut" required="" value="${demandeVisiteInstance?.statut}"/>

</div>

