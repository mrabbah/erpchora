Object o = session.getAttribute("utilisateur");
if(o == null) {
    Executions.sendRedirect("/choraBarrage");
}