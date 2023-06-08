package fr.sae202.Models;

/**
 * Interface for the constants for javaFX
 */
public interface Constants {
    final String APP_NAME = "SAE202";   // App name
    final String[] MENU_BAR = {"Scénarios","Type","Critère","Ordre"};   // Menu bar strings
    final String[] MENU_TYPES = {"Efficace","Exhaustive"};  // Menu types strings
    final String[] MENU_CRITERIA = {"Glouton","Durée","Nombre de quêtes","Déplacement"}; // Menu criteria strings
    final String[] MENU_ORDERS = {"Meilleures solutions","Pires solutions"}; // Menu orders strings
    final String[] TEXT_HEADER = {"Scénario ","Nombre de solutions : "}; // Text header strings
    final String[] HEADER_MESSAGE = {"Veuillez entrer un nombre de solutions valide !"," solutions trouvées avec succès !"}; // Header message strings
    final String[] BUTTON = {"Valider"}; // Button strings
    final String[] TABLE_HEADER = {"N°","Suite de quêtes","Durée","Expérience","Distance","Quêtes"}; // Table header strings
}
