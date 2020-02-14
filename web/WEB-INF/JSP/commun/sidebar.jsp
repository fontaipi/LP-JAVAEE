<%@ page contentType="text/script; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="<%=application.getContextPath()%>">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Etud'Admin</div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item active">
        <a class="nav-link" href="<%= application.getContextPath()%>">
            <i class="fas fa-home"></i>
            <span>Accueil</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Admin
    </div>

    <!-- Nav Item - Pages Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true"
           aria-controls="collapseTwo">
            <i class="fas fa-user"></i>
            <span>Étudiants</span>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Gérer les étudiants :</h6>
                <a class="collapse-item" href="<%= application.getContextPath()%>/do/listeEtudiants">Liste des étudiants</a>
                <a class="collapse-item" href="<%= application.getContextPath()%>/do/createEtudiant">Ajouter un étudiant</a>
            </div>
        </div>
    </li>

    <!-- Nav Item - Utilities Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseGroupes"
           aria-expanded="true" aria-controls="collapseGroupes">
            <i class="fas fa-users"></i>
            <span>Groupes</span>
        </a>
        <div id="collapseGroupes" class="collapse" aria-labelledby="headingGroupes" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Gérer les groupes :</h6>
                <a class="collapse-item" href="<%=request.getContextPath()%>/do/groupes">Liste des groupes</a>
                <a class="collapse-item" href="<%=request.getContextPath()%>/do/createGroupe">Ajouter un groupe</a>
            </div>
        </div>
    </li>

    <!-- Nav Item - Utilities Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseNotes"
           aria-expanded="true" aria-controls="collapseNotes">
            <i class="fas fa-graduation-cap"></i>
            <span>Notes</span>
        </a>
        <div id="collapseNotes" class="collapse" aria-labelledby="headingNotes" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Custom Utilities:</h6>
                <a class="collapse-item" href="<%= application.getContextPath()%>/do/consultationNotes">Liste des notes</a>
            </div>
        </div>
    </li>

    <!-- Nav Item - Utilities Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseAbsences"
           aria-expanded="true" aria-controls="collapseAbsences">
            <i class="fas fa-sticky-note"></i>
            <span>Absences</span>
        </a>
        <div id="collapseAbsences" class="collapse" aria-labelledby="headingAbsences" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Custom Utilities:</h6>
                <a class="collapse-item" href="<%=request.getContextPath()%>/do/consultationAbsences">Liste des abscences</a>
            </div>
        </div>
    </li>

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

</ul>
<!-- End of Sidebar -->