
<%@ page import="org.agilereview.Review" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'review.label', default: 'Review')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'review.id.label', default: 'Id')}" />
                        
                            <th><g:message code="review.reviewee.label" default="Person" /></th>
                            <th><g:message code="review.complete.label" default="complete" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${reviewInstanceList}" status="i" var="reviewInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link controller="evaluation" action="create" params="[reviewID:reviewInstance.id]">${fieldValue(bean: reviewInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: reviewInstance, field: "reviewee")}</td>
                            <td>${fieldValue(bean: reviewInstance, field: "complete")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${reviewInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
