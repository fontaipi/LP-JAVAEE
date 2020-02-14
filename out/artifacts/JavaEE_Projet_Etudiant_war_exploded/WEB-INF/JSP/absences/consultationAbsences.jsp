<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- IMPORT --%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.Integer"%>
<%@ page import="projet.data.*"%>

<%-- USEBEAN --%>
<jsp:useBean id="etudiants" type="java.util.List<projet.data.Etudiant>" scope="request"/>


<div>

<!-- AFFICHAGE en fonction du groupe ou de ALL  -->
<h3>Consultation des absences</h3>

<!-- AFFICHAGE des notes des étudiants  -->
<% if (etudiants.size() != 0) {%>

<!-- tableau de notes  -->
<table >
<%
int totalAbsences = 0;
for (Etudiant etudiant : etudiants) {
	Integer absences = etudiant.getNbAbsences();
%>
	<tr>
		<td><a href="<%= application.getContextPath()%>/do/etudiant?id=<%=etudiant.getId()%>"><%=etudiant.getPrenom()%> <%=etudiant.getNom()%></a></td>
		<td><%=etudiant.getGroupe().getNom()%></td>
		<td><%=absences%></td>
		<td><a href="<%=application.getContextPath()%>/do/addAbsence?id=<%=etudiant.getId()%>" class="btn btn-success btn-circle btn-sm"><i class="fas fa-plus"></i></a></td>
		<td><a href="<%=application.getContextPath()%>/do/removeAbsence?id=<%=etudiant.getId()%>" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-minus"></i></a></td>
	</tr>
<%
totalAbsences = totalAbsences + absences;
}
%>
</table>

<p>Nombre total d'absences : <%= totalAbsences%></p>

<% } else {%>

	<p>Aucun étudiant</p>
<%}%>

<blockquote>je suis constulationAbsences.jsp</blockquote>

</div>
