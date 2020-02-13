<%@page import="projet.data.Etudiant" %>
<%@page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="etudiants" type="java.util.List<projet.data.Etudiant>" scope="request"/>
<jsp:useBean id="add" type="java.lang.String" scope="request"/>
<jsp:useBean id="edit" type="java.lang.String" scope="request"/>
<jsp:useBean id="remove" type="java.lang.String" scope="request"/>

<% if (add == "success") {%>
    <div class="alert alert-success" role="alert">
        Création réussie !
    </div>
<% } else { %>

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
<% } else { %>

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
        <td>
            <a href="<%=application.getContextPath()%>/do/editEtudiant?id=<%=etudiant.getId()%>"><i class="fas fa-edit"></i></a>
            <a href="<%=application.getContextPath()%>/do/removeEtudiant?id=<%=etudiant.getId()%>"><i class="fas fa-times"></i></a>
        </td>
    </tr>
    <% } %>
</table>