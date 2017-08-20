boolean GESTION_VENTES = (boolean) session.getAttribute("GESTION_VENTES") && (boolean) session.getAttribute("gesticom");
boolean GESTION_ACHATS = (boolean) session.getAttribute("GESTION_ACHATS") && (boolean) session.getAttribute("gesticom");
boolean GESTION_CHANTIERS = (boolean) session.getAttribute("GESTION_CHANTIERS") && (boolean) session.getAttribute("warsh");
boolean GESTION_STOCKS = (boolean) session.getAttribute("GESTION_STOCKS") && (boolean) session.getAttribute("gesticom");
boolean GESTION_COMPTA = (boolean) session.getAttribute("GESTION_COMPTA") && (boolean) session.getAttribute("gesticom");
boolean PARAMETRAGE = (boolean) session.getAttribute("PARAMETRAGE");
boolean ADMINISTRATION = (boolean) session.getAttribute("ADMINISTRATION");
boolean TDB = (boolean) session.getAttribute("TDB");
boolean ROOT = (boolean) session.getAttribute("root");

Integer currentmenu = 4;

Object cm = Executions.getCurrent().getParameter("cm");
if(cm != null) {
    currentmenu = Integer.parseInt(cm);
} else {
    if(TDB == true) {
        currentmenu = 4;
    } else if(GESTION_VENTES == true) {
        currentmenu = 0;
    } else if(GESTION_ACHATS == true) {
        currentmenu = 1;
    } else if(GESTION_STOCKS == true) {
        currentmenu = 2;
    } else if(GESTION_COMPTA == true) {
        currentmenu = 3;
    } else if(PARAMETRAGE == true) {
        currentmenu = 5;
    } else if(ADMINISTRATION == true) {
        currentmenu = 6;
    } 
    String url = "/zul/main.zul?cm="+currentmenu;
    Executions.sendRedirect(url);
}

boolean affichervente = (GESTION_VENTES == true) && (currentmenu == 0);
boolean afficherachat = (GESTION_ACHATS == true) && (currentmenu == 1);
boolean afficherstock = (GESTION_STOCKS == true) && (currentmenu == 2);
boolean affichercompta = (GESTION_COMPTA == true) && (currentmenu == 3);
boolean afficheranalyse = (TDB == true) && (currentmenu == 4);
boolean afficheradmin = (PARAMETRAGE == true) && (currentmenu == 5);
boolean afficherroot = (ADMINISTRATION == true) && (currentmenu == 6);
boolean afficherprojet = (GESTION_CHANTIERS == true) && (currentmenu == 7);

String style = "background : #1360E5;";
if(affichervente == true) {
    style = "background : #1360E5;";
} else if(afficherachat == true) {
    style = "background : #4D8613;";
} else if (afficherstock == true) {
    style = "background : #6E3827;";
} else if (affichercompta == true) {
    style = "background : #7F7F00;";
} else if(afficheranalyse == true) {
    style = "background : #7F7F7F;";
} else if(afficheradmin == true) {
    style = "background : #041D41;";
} else if(afficherroot == true) {
    style = "background : #000000;";
} else if(afficherprojet == true) {
    style = "background : #FF0000;";
} 

String imgPath = "/images/skin";