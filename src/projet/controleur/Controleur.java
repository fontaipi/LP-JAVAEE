/**
 *
 */
package projet.controleur;

/**
 * @author hb
 *
 */

import projet.data.*;
import projet.data.Module;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("serial")
public class Controleur extends HttpServlet {

    // private String urlTest;
    private String urlGestionTemplate;
    private String urlAccueil;
    private String urlListeEtudiants;
    private String urlGroupes;
    private String urlCreateGroupe;
    private String urlEditGroupe;
    private String urlEtudiant;
    private String urlCreateEtudiant;
    private String urlEditEtudiant;
    private String urlModules;
    private String urlCreateModule;
    private String urlEditModule;
    private String urlConsultationAbsences;
    private String urlConsultationNotes;


    // INIT
    public void init() throws ServletException {
        // Récupération des URLs en paramètre du web.xml
        urlGestionTemplate = getServletConfig().getInitParameter("urlGestionTemplate");
        urlAccueil = getServletConfig().getInitParameter("urlAccueil");
        urlListeEtudiants = getServletConfig().getInitParameter("urlListeEtudiants");
        urlGroupes = getServletConfig().getInitParameter("urlGroupes");
        urlCreateGroupe = getServletConfig().getInitParameter("urlCreateGroupe");
        urlEditGroupe = getServletConfig().getInitParameter("urlEditGroupe");
        urlEtudiant = getServletConfig().getInitParameter("urlEtudiant");
        urlCreateEtudiant = getServletConfig().getInitParameter("urlCreateEtudiant");
        urlEditEtudiant = getServletConfig().getInitParameter("urlEditEtudiant");
        urlModules = getServletConfig().getInitParameter("urlModules");
        urlCreateModule = getServletConfig().getInitParameter("urlCreateModule");
        urlEditModule = getServletConfig().getInitParameter("urlEditModule");
        urlConsultationAbsences = getServletConfig().getInitParameter("urlConsultationAbsences");
        urlConsultationNotes = getServletConfig().getInitParameter("urlConsultationNotes");

        // Création de la factory permettant la création d'EntityManager
        // (gestion des transactions)
        GestionFactory.open();

        ///// INITIALISATION DE LA BD
        // Normalement l'initialisation se fait directement dans la base de données
        if ((GroupeDAO.getAll().size() == 0) && (EtudiantDAO.getAll().size() == 0)) {

            // Creation des groupes
            Groupe MIAM = GroupeDAO.create("miam");
            Groupe SIMO = GroupeDAO.create("SIMO");
            Groupe MESSI = GroupeDAO.create("MESSI");

            // Creation des étudiants
            EtudiantDAO.create("Francis", "Brunet-Manquat", MIAM);
            EtudiantDAO.create("Philippe", "Martin", MIAM);
            EtudiantDAO.create("Mario", "Cortes-Cornax", MIAM);
            EtudiantDAO.create("Françoise", "Coat", SIMO);
            EtudiantDAO.create("Laurent", "Bonnaud", MESSI);
            EtudiantDAO.create("Sébastien", "Bourdon", MESSI);
            EtudiantDAO.create("Mathieu", "Gatumel", SIMO);

            // Creation des groupes
            projet.data.Module MI1 = ModuleDAO.create("MI1");
            Module MI4 = ModuleDAO.create("MI4");

            // Liés groupe et module
            //MIAM.addModule(MI1);
            //MIAM.addModule(MI4);
            //SIMO.addModule(MI1);

            MI1.addGroupe(MIAM);
            MI4.addGroupe(MIAM);
            MI1.addGroupe(SIMO);

            //GroupeDAO.update(MIAM);
            //GroupeDAO.update(SIMO);

            ModuleDAO.update(MI1);
            ModuleDAO.update(MI4);

        }
    }

    @Override
    public void destroy() {
        super.destroy();

        // Fermeture de la factory
        GestionFactory.close();
    }

    // POST
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // on passe la main au GET
        doGet(request, response);
    }

    // GET
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // On récupère le path
        String action = request.getPathInfo();
        if (action == null) {
            action = "/accueil";
        }

        // Exécution action
        if (action.equals("/accueil")) {
            doAcceuil(request, response);
        } else if (action.equals("/listeEtudiants")) {
            doListeEtudiants(request, response);

        } else if (action.equals("/etudiant")) {
            doEtudiant(request, response);

        } else if (action.equals("/createEtudiant")) {
            doCreateEtudiant(request, response);

        } else if (action.equals("/editEtudiant")) {
            doEditEtudiant(request, response);

        } else if (action.equals("/removeEtudiant")) {
            doRemoveEtudiant(request, response);

        } else if (action.equals("/consultationAbsences")) {
            doConsultationAbsences(request, response);

        } else if (action.equals("/addAbsence")) {
            doAddAbsence(request, response);

        } else if (action.equals("/removeAbsence")) {
            doRemoveAbsence(request, response);

        } else if (action.equals("/consultationNotes")) {
            doConsultationNotes(request, response);

        } else if (action.equals("/groupes")) {
            doGroupes(request, response);

        } else if (action.equals("/createGroupe")) {
            doCreateGroupe(request, response);

        } else if (action.equals("/editGroupe")) {
            doEditGroupe(request, response);

        } else if (action.equals("/removeGroupe")) {
            doRemoveGroupe(request, response);

        } else if (action.equals("/modules")) {
            doModules(request, response);

        } else if (action.equals("/createModule")) {
            doCreateModule(request, response);

        } else if (action.equals("/editModule")) {
            doEditModule(request, response);

        } else if (action.equals("/removeModule")) {
            doRemoveModule(request, response);

        } else {
            // Autres cas
            doAcceuil(request, response);
        }
    }


    // /////////////////////// ACCUEIL
    //
    private void doAcceuil(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {

        // Inclusion du content dans le template
        request.setAttribute("content", urlAccueil);
        loadJSP(urlGestionTemplate, request, response);
    }

    private void doEditEtudiant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("submit") != null) {
            Integer etu_id = Integer.parseInt(request.getParameter("id"));
            Etudiant etudiant = EtudiantDAO.retrieveById(etu_id);

            String prenom = request.getParameter("prenom");
            String nom = request.getParameter("nom");
            int id_groupe = Integer.parseInt(request.getParameter("groupe"));
            Groupe groupe = GroupeDAO.retrieveById(id_groupe);

            etudiant.setPrenom(prenom);
            etudiant.setNom(nom);
            etudiant.setGroupe(groupe);

            EtudiantDAO.update(etudiant);

            // Récupérer les étudiants
            List<Etudiant> etudiants = EtudiantDAO.getAll();

            // Ajouter les étudiants à la requête pour affichage
            request.setAttribute("etudiants", etudiants);
            request.setAttribute("add", "");
            request.setAttribute("edit", "success");
            request.setAttribute("remove", "");

            request.setAttribute("content", urlListeEtudiants);
            loadJSP(urlGestionTemplate, request, response);
        } else {
            Integer etu_id = Integer.parseInt(request.getParameter("id"));
            Etudiant etudiant = EtudiantDAO.retrieveById(etu_id);
            List<Groupe> groupes = GroupeDAO.getAll();

            // Ajouter les groupes à la requête pour affichage
            request.setAttribute("groupes", groupes);

            request.setAttribute("etudiant", etudiant);

            request.setAttribute("content", urlEditEtudiant);
            loadJSP(urlGestionTemplate, request, response);
        }
    }

    private void doRemoveEtudiant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer etu_id = Integer.parseInt(request.getParameter("id"));

        EtudiantDAO.remove(etu_id);

        // Récupérer les étudiants
        List<Etudiant> etudiants = EtudiantDAO.getAll();

        // Ajouter les étudiants à la requête pour affichage
        request.setAttribute("etudiants", etudiants);
        request.setAttribute("add", "");
        request.setAttribute("edit", "");
        request.setAttribute("remove", "success");

        request.setAttribute("content", urlListeEtudiants);
        loadJSP(urlGestionTemplate, request, response);
    }

    private void doCreateEtudiant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("submit") != null) {
            String prenom = request.getParameter("prenom");
            String nom = request.getParameter("nom");

            Groupe groupe;
            int id_groupe = Integer.parseInt(request.getParameter("groupe"));
            groupe = GroupeDAO.retrieveById(id_groupe);

            EtudiantDAO.create(prenom, nom, groupe);

            // Récupérer les étudiants
            List<Etudiant> etudiants = EtudiantDAO.getAll();

            // Ajouter les étudiants à la requête pour affichage
            request.setAttribute("etudiants", etudiants);
            request.setAttribute("add", "success");
            request.setAttribute("edit", "");
            request.setAttribute("remove", "");

            request.setAttribute("content", urlListeEtudiants);
            loadJSP(urlGestionTemplate, request, response);
        } else {
            // Récupérer les étudiants
            List<Groupe> groupes = GroupeDAO.getAll();

            // Ajouter les groupes à la requête pour affichage
            request.setAttribute("groupes", groupes);

            request.setAttribute("content", urlCreateEtudiant);
            loadJSP(urlGestionTemplate, request, response);
        }
    }


    // /////////////////////// Liste des étudiants
    //
    private void doListeEtudiants(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {

        // Récupérer les étudiants
        List<Etudiant> etudiants = EtudiantDAO.getAll();

        // Ajouter les étudiants à la requête pour affichage
        request.setAttribute("etudiants", etudiants);
        request.setAttribute("add", "");
        request.setAttribute("edit", "");
        request.setAttribute("remove", "");

        request.setAttribute("content", urlListeEtudiants);
        loadJSP(urlGestionTemplate, request, response);
    }

    // /////////////////////// Liste des groupes
    //
    private void doGroupes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer les groupes
        List<Groupe> groupes = GroupeDAO.getAll();

        // Ajouter les étudiants à la requête pour affichage
        request.setAttribute("groupes", groupes);
        request.setAttribute("add", "");
        request.setAttribute("edit", "");
        request.setAttribute("remove", "");

        //
        request.setAttribute("content", urlGroupes);
        loadJSP(urlGestionTemplate, request, response);
    }

    private void doCreateGroupe(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("submit") != null) {
            String nom = request.getParameter("nom");

            try {
                Groupe groupe = GroupeDAO.create(nom);
                List<Etudiant> etudiants = EtudiantDAO.getAll();
                for (Etudiant etudiant : etudiants) {
                    if (request.getParameter("etu_" + etudiant.getId()) != null) {
                        etudiant.setGroupe(groupe);
                        EtudiantDAO.update(etudiant);
                    }
                }
                // Récupérer les étudiants
                List<Groupe> groupes = GroupeDAO.getAll();

                // Ajouter les étudiants à la requête pour affichage
                request.setAttribute("groupes", groupes);
                request.setAttribute("add", "success");
                request.setAttribute("edit", "");
                request.setAttribute("remove", "");

                request.setAttribute("content", urlGroupes);
                loadJSP(urlGestionTemplate, request, response);
            } catch (Exception e) {
                // Récupérer les étudiants
                List<Groupe> groupes = GroupeDAO.getAll();

                // Ajouter les étudiants à la requête pour affichage
                request.setAttribute("groupes", groupes);
                request.setAttribute("add", "failed");
                request.setAttribute("edit", "");
                request.setAttribute("remove", "");

                request.setAttribute("content", urlGroupes);
                loadJSP(urlGestionTemplate, request, response);
            }

        } else {
            // Récupérer les étudiants
            List<Etudiant> etudiants = EtudiantDAO.getAll();

            // Ajouter les groupes à la requête pour affichage
            request.setAttribute("etudiants", etudiants);

            request.setAttribute("content", urlCreateGroupe);
            loadJSP(urlGestionTemplate, request, response);
        }
    }

    private void doEditGroupe(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("submit") != null) {
            Integer id_groupe = Integer.parseInt(request.getParameter("id"));
            Groupe groupe = GroupeDAO.retrieveById(id_groupe);

            String nom = request.getParameter("nom");
            groupe.setNom(nom);

            GroupeDAO.update(groupe);

            List<Etudiant> etudiants = EtudiantDAO.getAll();
            for (Etudiant etudiant : etudiants) {
                if (request.getParameter("etu_" + etudiant.getId()) != null) {
                    etudiant.setGroupe(groupe);
                    EtudiantDAO.update(etudiant);
                }
            }

            // Récupérer les étudiants
            List<Groupe> groupes = GroupeDAO.getAll();

            // Ajouter les étudiants à la requête pour affichage
            request.setAttribute("groupes", groupes);
            request.setAttribute("add", "");
            request.setAttribute("edit", "success");
            request.setAttribute("remove", "");

            request.setAttribute("content", urlGroupes);
            loadJSP(urlGestionTemplate, request, response);
        } else {
            Integer id_groupe = Integer.parseInt(request.getParameter("id"));
            Groupe groupe = GroupeDAO.retrieveById(id_groupe);

            // Récupérer les étudiants
            List<Etudiant> etudiants = EtudiantDAO.getAll();

            // Ajouter les groupes à la requête pour affichage
            request.setAttribute("etudiants", etudiants);
            request.setAttribute("groupe", groupe);

            request.setAttribute("content", urlEditGroupe);
            loadJSP(urlGestionTemplate, request, response);
        }
    }

    private void doRemoveGroupe(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer groupe_id = Integer.parseInt(request.getParameter("id"));
        // Récupérer les étudiants
        List<Etudiant> etudiants = EtudiantDAO.getAll();

        int count = 0;
        for (Etudiant etudiant : etudiants) {
            if (etudiant.getGroupe().getId() == groupe_id) {
                count++;
            }
        }
        if (count == 0) {
            GroupeDAO.remove(groupe_id);
            request.setAttribute("remove", "success");
        } else {
            request.setAttribute("remove", "failed");
        }

        // Récupérer les groupes
        List<Groupe> groupes = GroupeDAO.getAll();

        // Ajouter les étudiants à la requête pour affichage
        request.setAttribute("groupes", groupes);
        request.setAttribute("add", "");
        request.setAttribute("edit", "");

        request.setAttribute("content", urlGroupes);
        loadJSP(urlGestionTemplate, request, response);
    }

    ///////////////////////// Détails étudiant
    //
    private void doEtudiant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer id_etu = Integer.parseInt(request.getParameter("id"));
        // Récupérer le parametre id, l'objet Etudiant associé, le nombre d'absences et la moyenne
        Etudiant etudiant = EtudiantDAO.retrieveById(id_etu);

        // Mettre l'objet étudiant en attribut pour affichage par la vue
        // correspondant
        request.setAttribute("etudiant", etudiant);

        //
        request.setAttribute("content", urlEtudiant);
        loadJSP(urlGestionTemplate, request, response);
    }

    private void doModules(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer les groupes
        List<Module> modules = ModuleDAO.getAll();

        // Ajouter les étudiants à la requête pour affichage
        request.setAttribute("modules", modules);
        request.setAttribute("add", "");
        request.setAttribute("edit", "");
        request.setAttribute("remove", "");

        //
        request.setAttribute("content", urlModules);
        loadJSP(urlGestionTemplate, request, response);

    }

    private void doCreateModule(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("submit") != null) {
            String nom = request.getParameter("nom");

            try {
                Module module = ModuleDAO.create(nom);

                List<Groupe> groupes = GroupeDAO.getAll();
                for (Groupe groupe : groupes) {
                    if (request.getParameter("groupe_" + groupe.getId()) != null) {
                        groupe.addModule(module);
                        GroupeDAO.update(groupe);
                    } else {
                        groupe.removeModule(module);
                        GroupeDAO.update(groupe);
                    }
                }
                // Récupérer les modules
                List<Module> modules = ModuleDAO.getAll();

                // Ajouter les étudiants à la requête pour affichage
                request.setAttribute("modules", modules);
                request.setAttribute("add", "success");
                request.setAttribute("edit", "");
                request.setAttribute("remove", "");

                request.setAttribute("content", urlModules);
                loadJSP(urlGestionTemplate, request, response);
            } catch (Exception e) {
                // Récupérer les modules
                List<Module> modules = ModuleDAO.getAll();

                // Ajouter les étudiants à la requête pour affichage
                request.setAttribute("modules", modules);
                request.setAttribute("add", "failed");
                request.setAttribute("edit", "");
                request.setAttribute("remove", "");

                request.setAttribute("content", urlModules);
                loadJSP(urlGestionTemplate, request, response);
            }
        } else {
            // Récupérer les étudiants
            List<Groupe> groupes = GroupeDAO.getAll();

            // Ajouter les groupes à la requête pour affichage
            request.setAttribute("groupes", groupes);

            request.setAttribute("content", urlCreateModule);
            loadJSP(urlGestionTemplate, request, response);
        }
    }

    private void doEditModule(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("submit") != null) {
            Integer id_module = Integer.parseInt(request.getParameter("id"));
            Module module = ModuleDAO.retrieveById(id_module);

            String nom = request.getParameter("nom");
            module.setNom(nom);

            ModuleDAO.update(module);

            List<Groupe> groupes = GroupeDAO.getAll();

            for (Groupe groupe : groupes) {
                module.removeGroupe(groupe);
                ModuleDAO.update(module);
                System.out.println(request.getParameter("groupe_" + groupe.getId()));
                if (request.getParameter("groupe_" + groupe.getId()) != null) {
                    groupe.addModule(module);
                    GroupeDAO.update(groupe);
                }
            }

            // Récupérer les étudiants
            List<Module> modules = ModuleDAO.getAll();

            // Ajouter les étudiants à la requête pour affichage
            request.setAttribute("modules", modules);
            request.setAttribute("add", "");
            request.setAttribute("edit", "success");
            request.setAttribute("remove", "");

            request.setAttribute("content", urlModules);
            loadJSP(urlGestionTemplate, request, response);
        } else {
            Integer id_module = Integer.parseInt(request.getParameter("id"));
            Module module = ModuleDAO.retrieveById(id_module);

            // Récupérer les étudiants
            List<Groupe> groupes = GroupeDAO.getAll();

            // Ajouter les groupes à la requête pour affichage
            request.setAttribute("groupes", groupes);
            request.setAttribute("module", module);

            request.setAttribute("content", urlEditModule);
            loadJSP(urlGestionTemplate, request, response);
        }
    }

    private void doRemoveModule(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer module_id = Integer.parseInt(request.getParameter("id"));
        Module module = ModuleDAO.retrieveById(module_id);

        int count = module.getGroupes().size();
        if (count == 0) {
            ModuleDAO.remove(module_id);
            request.setAttribute("remove", "success");
        } else {
            request.setAttribute("remove", "failed");
        }

        // Récupérer les groupes
        List<Module> modules = ModuleDAO.getAll();

        // Ajouter les étudiants à la requête pour affichage
        request.setAttribute("modules", modules);
        request.setAttribute("add", "");
        request.setAttribute("edit", "");

        request.setAttribute("content", urlModules);
        loadJSP(urlGestionTemplate, request, response);
    }


    // /////////////////////// CONSULTATION NOTES
    //
    private void doConsultationNotes(HttpServletRequest request,
                                     HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("content", urlConsultationNotes);
        loadJSP(urlGestionTemplate, request, response);
    }

    // /////////////////////// CONSULTATION ABSENCES
    //
    private void doConsultationAbsences(HttpServletRequest request,
                                        HttpServletResponse response) throws ServletException, IOException {

        // Récupérer les étudiants
        List<Etudiant> etudiants = EtudiantDAO.getAll();

        // Ajouter les étudiants à la requête pour affichage
        request.setAttribute("etudiants", etudiants);

        request.setAttribute("content", urlConsultationAbsences);
        loadJSP(urlGestionTemplate, request, response);
    }

    private void doAddAbsence(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer etu_id = Integer.parseInt(request.getParameter("id"));
        Etudiant etudiant = EtudiantDAO.retrieveById(etu_id);

        etudiant.setNbAbsences(etudiant.getNbAbsences() + 1);

        EtudiantDAO.update(etudiant);

        doConsultationAbsences(request, response);
    }

    private void doRemoveAbsence(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer etu_id = Integer.parseInt(request.getParameter("id"));
        Etudiant etudiant = EtudiantDAO.retrieveById(etu_id);

        if (etudiant.getNbAbsences() > 0) {
            etudiant.setNbAbsences(etudiant.getNbAbsences() - 1);

            EtudiantDAO.update(etudiant);
        }

        doConsultationAbsences(request, response);
    }


    /**
     * Charge la JSP indiquée en paramètre
     *
     * @param url
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void loadJSP(String url, HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        // L'interface RequestDispatcher permet de transférer le contrôle à une
        // autre servlet
        // Deux méthodes possibles :
        // - forward() : donne le contrôle à une autre servlet. Annule le flux
        // de sortie de la servlet courante
        // - include() : inclus dynamiquement une autre servlet
        // + le contrôle est donné à une autre servlet puis revient à la servlet
        // courante (sorte d'appel de fonction).
        // + Le flux de sortie n'est pas supprimé et les deux se cumulent

        ServletContext sc = getServletContext();
        System.out.println(sc.getContextPath());
        RequestDispatcher rd = sc.getRequestDispatcher(url);
        rd.forward(request, response);
    }

}
