
<%@ page import="toulousemusee.Musee" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'musee.label', default: 'Musee')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
        <style type="text/css" media="screen">
        body {
            font-size:smaller;
        }
        </style>
	</head>
	<body>
		<a href="#list-musee" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="Acceuil"/></a></li>
			</ul>
		</div>
		<div id="list-musee" class="content scaffold-list" role="main">
			<h1><g:message code="Musees" args="[entityName]" /></h1>
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


                            <td><g:link action="ajouterFavoris" params="[nom: museeInstance.nom]"
                                        style="text-align: right"><input type="button" value="Ajouter à ma liste"
                                                                         class="button"/></g:link></td>


					</tr>
				</g:each>
				</tbody>
			</table>



            <g:if test="${((List) session.getAttribute("mesFavoris"))?.size() > 0}">
                <div>
                    <h1>Ma liste de musees préférés</h1>
                    <br/>
                    <table>

                        <g:each in="${session.getAttribute("mesFavoris")}" status="i" var="museeInstance">

                            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                <td>${museeInstance}</td>
                                <td><g:link url="[controller: 'demandeVisite', action: 'create']">
                                    <input type="button" value="Faire une demande"/>
                                </g:link>
                                </td>
                                <td><g:link action="supprimerFavoris"  params="[nom: museeInstance, from: 'musee/index']" style="text-align: right">
                                    <input type="button" value="Supprimer"></g:link>
                                </td>

                            </tr>

                        </g:each>

                    </table>

                    <tbody>
                    <g:each in="${demandeVisiteMuseeInstanceList}" status="i" var="demandeVisiteMuseeInstance">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                            <td><g:link action="show" id="${demandeVisiteMuseeInstance.id}">${fieldValue(bean: demandeVisiteMuseeInstance, field: "dateDemande")}</g:link></td>

                            <td>${fieldValue(bean: demandeVisiteMuseeInstance, field: "demandeVisite")}</td>

                            <td>${fieldValue(bean: demandeVisiteMuseeInstance, field: "musee")}</td>

                        </tr>
                    </g:each>
                    </tbody>
                </div>
                <div class="pagination">
                    <g:paginate total="${museeInstanceCount ?: 0}" max="5"/>
                </div>
            </g:if>
		</div>
	</body>
</html>
