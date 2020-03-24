<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<jsp:useBean id="etudiant" class="projet.data.Etudiant" scope="request"/>

<div>

<h3>Fiche de <%=etudiant.getPrenom()%> <%=etudiant.getNom()%></h3>

<table >
	<tr>
		<td>Groupe</td>
		<td><%=etudiant.getGroupe().getNom()%></td>
	</tr>
	<tr>
		<td>Moyenne Generale</td>
		<td></td>
	</tr>
	<tr>
		<td>Absences</td>
		<td><%=etudiant.getNbAbsences()%></td>
	</tr>
</table>

</div>