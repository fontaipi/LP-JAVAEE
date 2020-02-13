<%@page import="projet.data.Etudiant" %>
<%@page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="etudiants" type="java.util.List<projet.data.Etudiant>" scope="request"/>
<jsp:useBean id="success" type="java.lang.Boolean" scope="request"/>

<% if (success) {%>
<div class="alert alert-success" role="alert">
	Création réussie !
</div>
<% } %>

<h1>Liste des étudiants présents en BD</h1>
<p><a href="<%= getServletContext().getContextPath()%>/do/groupes">voir les groupes</a></p>

<!-- tableau d'étudiants -->
<table>

    <tr>
        <th>Prénom/nom de l'étudiant</th>
        <th>Son groupe</th>
    <tr>


            <% for (Etudiant etudiant : etudiants) {%>

    <tr>
        <td><%=etudiant.getPrenom()%> <%=etudiant.getNom()%>
        </td>
        <td><%=etudiant.getGroupe().getNom()%>
        </td>
    </tr>
    <% } %>
</table>