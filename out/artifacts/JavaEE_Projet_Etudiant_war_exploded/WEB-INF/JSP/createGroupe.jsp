<%@page import="projet.data.Etudiant"%>
<%@page import="java.util.List"%>
<%@ page import="projet.data.Groupe" %>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="etudiants" type="java.util.List<projet.data.Etudiant>" scope="request"/>


<form action="<%=request.getContextPath()%>/do/createGroupe" method="post">
    <input name="nom" type="text" placeholder="Nom du groupe">

    <h2>Ajouter des étudiants au groupe : </h2>
    <br>
    <% for (Etudiant etudiant : etudiants) {%>
        <input type="checkbox" name="etu_<%=etudiant.getId()%>" value="<%=etudiant.getId()%>" %>
        <label for="etu_<%=etudiant.getId()%>"%><%=etudiant.getNom()%> <%=etudiant.getPrenom()%></label>
        <br>
    <% } %>
    <input type="submit" name="submit">

</form>