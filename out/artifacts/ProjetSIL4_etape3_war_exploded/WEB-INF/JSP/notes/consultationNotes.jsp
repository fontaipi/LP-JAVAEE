<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- IMPORT --%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.Integer"%>
<%@ page import="projet.data.*"%>

<%-- USEBEAN --%>
<jsp:useBean id="listeNotesEtudiants" type="java.util.Map<projet.data.Etudiant,java.lang.Integer>" scope="request"/>


<!--% ou en JAVA
Map<Etudiant,Integer> listeNotesEtudiants = (Map<Etudiant,Integer>)request.getAttribute("listeNotesEtudiants");
%-->

<div style="background-color:#D8F6CE">

<!-- AFFICHAGE d'un titre  -->
<h3>Moyennes générales</h3>

<!-- AFFICHAGE des notes des étudiants  -->
<% if (listeNotesEtudiants.size() != 0) {%>

<!-- tableau de notes  -->
<table border="1">

<%
int sommeMoyenneGenerale = 0;
for (Map.Entry<Etudiant, Integer> entry : listeNotesEtudiants.entrySet()) {
	Etudiant etudiant = entry.getKey();
	Integer note = entry.getValue();
%>
	<tr>
		<td><a href="<%= application.getContextPath()%>/do/etudiant?id=<%=etudiant.getId()%>"><%=etudiant.getPrenom()%> <%=etudiant.getNom()%></a></td>
		<td><%=etudiant.getGroupe()%></td>
		<td><%=note%></td>
	</tr>
<%

	// Calcul de la somme totale des notes pour calcul de la moyenne
	sommeMoyenneGenerale = sommeMoyenneGenerale + note;
}
%>
</table>

<p>Moyenne des étudiants : <%= sommeMoyenneGenerale/listeNotesEtudiants.size()%></p>

<% } else {%>

	<p>Aucun étudiant</p>
<%}%>

<blockquote>je suis consultationNotes.jsp</blockquote>

</div>
