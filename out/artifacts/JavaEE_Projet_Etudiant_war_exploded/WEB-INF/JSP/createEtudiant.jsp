<%@page import="projet.data.Etudiant"%>
<%@page import="java.util.List"%>
<%@ page import="projet.data.Groupe" %>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="groupes" type="java.util.List<projet.data.Groupe>" scope="request"/>


<form action="<%=request.getContextPath()%>/do/createEtudiant" method="post">
    <input name="nom" type="text" placeholder="Nom">
    <input name="prenom" type="text" placeholder="Prénom">
    <select name="groupe">
        <% for (Groupe groupe : groupes) {%>
        <option value="<%=groupe.getId()%>"><%=groupe.getNom()%></option>
        <% } %>
    </select>
    <input type="submit" name="submit">

</form>