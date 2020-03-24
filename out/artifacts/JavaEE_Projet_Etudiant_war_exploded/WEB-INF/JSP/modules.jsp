<%@page import="projet.data.Groupe"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="modules" type="java.util.List<projet.data.Module>" scope="request"/>

<jsp:useBean id="add" type="java.lang.String" scope="request"/>
<jsp:useBean id="edit" type="java.lang.String" scope="request"/>
<jsp:useBean id="remove" type="java.lang.String" scope="request"/>

<% if (add == "success") {%>
<div class="alert alert-success" role="alert">
    Création réussie !
</div>
<% } else if (add == "failed") { %>
<div class="alert alert-danger" role="alert">
    Échec lors de la création !
</div>
<% } %>

<% if (edit == "success") {%>
<div class="alert alert-success" role="alert">
    Modification réussie !
</div>
<% } else { %>

<% } %>

<% if (remove == "success") {%>
<div class="alert alert-success" role="alert">
    Suppression réussie !
</div>
<% } else if (remove == "failed"){ %>
<div class="alert alert-danger" role="alert">
    Vous devez retirer les étudiants du module avant de le supprimer !
</div>
<% } %>

<html>
<head>
    <title><%=getServletContext().getInitParameter("title")%></title>
</head>
<body>

<h1>Liste des groupes présents en BD</h1>
<p><a href="<%= getServletContext().getContextPath()%>/do/listEtudiants">voir les étudiants</a></p>

<!-- tableau de groupes  -->
<table>

    <tr>
        <th>Nom du module</th>
        <th>Nombre de groupes ou le module est présent</th>
    </tr>

    <% for (projet.data.Module module : modules) {%>

    <tr>
        <td><%=module.getNom()%></td>
        <td><%=module.getGroupes().size()%></td>
        <td>
            <a href="<%=application.getContextPath()%>/do/editModule?id=<%=module.getId()%>"><i class="fas fa-edit"></i></a>
            <a href="<%=application.getContextPath()%>/do/removeModule?id=<%=module.getId()%>"><i class="fas fa-times"></i></a>
        </td>
    </tr>
    <% } %>
</table>


</body>
</html>