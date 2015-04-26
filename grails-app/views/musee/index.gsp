
<%@ page import="toulousemusee.Musee" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'musee.label', default: 'Musee')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-musee" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-musee" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="nom" title="${message(code: 'musee.nom.label', default: 'Nom')}" />
					
						<g:sortableColumn property="horairesOuverture" title="${message(code: 'musee.horairesOuverture.label', default: 'Horaires Ouverture')}" />
					
						<g:sortableColumn property="telephone" title="${message(code: 'musee.telephone.label', default: 'Telephone')}" />
					
						<g:sortableColumn property="accessMetro" title="${message(code: 'musee.accessMetro.label', default: 'Access Metro')}" />
					
						<g:sortableColumn property="accessBus" title="${message(code: 'musee.accessBus.label', default: 'Access Bus')}" />
					
						<th><g:message code="musee.adresse.label" default="Adresse" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${museeInstanceList}" status="i" var="museeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td><g:link action="show" id="${museeInstance.id}">${fieldValue(bean: museeInstance, field: "nom")}</g:link></td>

						<td>${fieldValue(bean: museeInstance, field: "horairesOuverture")}</td>

						<td>${fieldValue(bean: museeInstance, field: "telephone")}</td>

						<td>${fieldValue(bean: museeInstance, field: "accessMetro")}</td>

						<td>${fieldValue(bean: museeInstance, field: "accessBus")}</td>

						<td>${fieldValue(bean: museeInstance, field: "adresse")}</td>

                        <g:if test="${!(((List) session.getAttribute("mesFavoris"))?.contains(museeInstance.nom))}">
                            <td><g:link action="ajouterFavoris" params="[nom: museeInstance.nom]"
                                        style="text-align: right"><input type="button" value="Ajouter à ma liste"
                                                                         class="button"/></g:link></td>
                        </g:if>

					</tr>
				</g:each>
				</tbody>
			</table>

            <div class="pagination">
                <g:paginate total="${museeInstanceCount ?: 0}" max="5"/>
            </div>

            <g:if test="${((List) session.getAttribute("mesFavoris"))?.size() > 0}">
                <div>
                    <h1>Ma liste de musees préférés</h1>
                    <br/>
                    <table>

                        <g:each in="${session.getAttribute("mesFavoris")}" status="i" var="museeInstance">

                            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                <td>${museeInstance}</td>
                                <td><g:link action="demandePref" controller="demandevisitemusee/index"  params="[nom: museeInstance]"
                                            style="text-align: right"><input type="button" value="Demande de viste"></g:link>
                                </td>
                                <td><g:link action="supprimerFavoris"  params="[nom: museeInstance, from: 'musee/index']" style="text-align: right">
                                    <input type="button" value="Supprimer"></g:link>
                                </td>

                            </tr>

                        </g:each>

                    </table>
                </div>
            </g:if>
		</div>
	</body>
</html>
