<%@page import="projet.data.Etudiant"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="etudiants" type="java.util.List<projet.data.Etudiant>" scope="request"/>


<form action="<%=request.getContextPath()%>/do/createEtu" method="post">
    <input name="nom" type="text">
    <input name="nom" type="text">
    <list>
        <option value=""></option>
    </list>

</form>