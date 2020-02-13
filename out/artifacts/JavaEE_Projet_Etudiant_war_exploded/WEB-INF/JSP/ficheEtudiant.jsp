<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<jsp:useBean id="etudiant" class="projet.data.Etudiant" scope="request"/>
<jsp:useBean id="nbAbsences" type="java.lang.Integer" scope="request"/>
<jsp:useBean id="note" type="java.lang.Integer" scope="request"/>

<div style="background-color:#D8F6CE">

<h3>Fiche de <%=etudiant.getPrenom()%> <%=etudiant.getNom()%></h3>

<table border="1">
	<tr>
		<td>Groupe</td>
		<td><%=etudiant.getGroupe()%></td>
	</tr>
	<tr>
		<td>Moyenne Generale</td>
		<td><%=note%></td>
	</tr>
	<tr>
		<td>Absences</td>
		<td><%=nbAbsences%></td>
	</tr>
</table>

<blockquote>je suis ficheEtudiant.jsp</blockquote>

</div>