<%@page import="projet.data.Etudiant"%>
<%@page import="java.util.List"%>
<%@ page import="projet.data.Groupe" %>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="groupes" type="java.util.List<projet.data.Groupe>" scope="request"/>


<form action="<%=request.getContextPath()%>/do/createModule" method="post">
    <input name="nom" type="text" placeholder="Nom du module">

    <h2>Ajouter des groupes au module : </h2>
    <br>
    <% for (Groupe groupe : groupes) {%>
    <input type="checkbox" name="groupe_<%=groupe.getId()%>" value="<%=groupe.getId()%>" %>
    <label for="etu_<%=groupe.getId()%>"%><%=groupe.getNom()%></label>
    <br>
    <% } %>
    <input type="submit" name="submit">

</form>