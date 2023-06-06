<div align="center">
  <h1>PROJET DURÉE DE VIE DU RPG</h1>
  <img src="https://img.shields.io/badge/Java-000000?style=for-the-badge&logo=intellijidea&logoColor=white"/>
  <img src="https://img.shields.io/badge/JavaFX-1572B6?style=for-the-badge&logo=css3&logoColor=white"/>
</div>

## À propos

Ce projet de développement/IHM avait pour but de réaliser une application permettant d'utiliser des algorithmes de calcul de durée de vie d'un jeu vidéo de type RPG depuis une interface graphique JavaFX.

## Contenu

Ce projet contient :
- Un répertoire [/src/fr/sae202](src/fr/sae202) dans lequel sont rangées les composantes d'une architecture MVC.

    - [/Models](src/fr/sae202/Models/) contient toutes les classes de gestion de données de l'application comme la classe [Player](src/fr/sae202) permettant de conserver les méthodes et attributs du joueur, ou encore la classe [Quest](src/fr/sae202) contenant les méthodes et attributs des quêtes du jeu.
    - [/Vue](src/fr/sae202/Vue/) contient toutes les classes de gestion de l'interface graphique de l'application qui permettent de gérer le menu de selection avec la classe [VBoxMenu](src/fr/sae202/Vue/VBoxMenu.java) ou encore [VBoxSolutionTable](src/fr/sae202/Vue/VBoxSolutionTable.java) qui permet de gérer l'affichage des solutions dans un tableau.
    - [/Controller](src/fr/sae202/Controller/) contient la classe de gestion des évènements de l'application, [MainController](src/fr/sae202/Controller/MainController.java) qui permet de gérer les évènements de l'application comme le choix d'une solution ou du nombre de solutions souhaitées.
    - [/Core](src/fr/sae202/Core/) contient toutes les classes de gestion des algorithmes de calcul de durée de vie du jeu, comme la classe [QuestParser](src/fr/sae202/Core/QuestParser.java) qui permet de récupérer les données des quêtes situées dans les fichiers texte de scénario, ou encore la classe [Game](src/fr/sae202/Core/Game.java) qui permet de gérer les données du jeu et de calculer la durée de vie du jeu en fonction des critères demandés.
    - [/Utils](src/fr/sae202/Utils/) contient les classes utilitaires de l'application comme la classe [Vector2](src/fr/sae202/Utils/Vector2.java) qui permet de gérer des vecteurs 2D.
    - [/Exceptions](src/fr/sae202/Exceptions/) contient les classes d'exceptions de l'application comme la classe [QuestParseException](src/fr/sae202/Exceptions/QuestParseException.java) qui permet de gérer les exceptions liées au [QuestParser](src/fr/sae202/Core/QuestParser.java).
  
- Un répertoire [/test](test) contenant les classes de tests unitaires de l'application réalisés avec JUnit.

- Un répertoire [/res](res) contenant tous les fichiers textes des différents scénarios à analyser.

- Un répertoire [/css](css) contenant le fichier css permettant de mettre en forme l'application JavaFX.

- Un répertoire [/docs](docs) contenant :
    - La [documentation](docs/index.html) générée par la JavaDoc.
    - Un [rapport](docs/Rapport.pdf) expliquant le fonctionnement de l'application et les choix de conception.
    - Un [diagramme de classes](docs/DIAGRAMME-DES-CLASSES.png) au format .png et .uml (IntelliJ).
    - Les [rapports de tests](docs/tests) unitaires de l'application.

## Fonctionnalités

Cetta application a pour but de permettre à l'utilisateur de calculer la durée de vie d'un jeu vidéo de type RPG en fonction de différents critères.

L'utilisateur peut choisir parmis les scénarios disponibles dans le menu de selection, puis choisir sa méthode de recherche de solutions. Il y a trois grands critères de recherche :
- Le type de chemin à effectuer :
  - Efficace : Le but est de trouver le chemin le plus efficace pour finir le jeu, en réalisant la quête finale sans avoir à finir toutes les quêtes
  - Exhaustive : Le but est de trouver un chemin permettant de finir toutes les quêtes du jeu avant de faire la quête finale.
- Le critère de recherche :
  - Gloutonne : Le but est de prendre la quête la plus proche possible à chaque fois, sans vraiment réfléchir sur l'ordre de réalisation
  - En fonction de la durée : Le but est d'avoir le chemin le plus court possible en termes de durée et d'arriver à la quête finale le plus vite possible.
  - En fonction du nombre de quêtes : Le but est d'avoir le chemin le plus court possible en termes de nombre de quêtes et d'arriver à la quête finale en réalisant le moins de quêtes possible.
  - En fonction de la distance parcourue : Le but est d'avoir le chemin le plus court possible en termes de distance parcourue et d'arriver à la quête finale en parcourant le moins de distance possible.
- L'ordre des résultats :
  - Les meilleures solutions : L'application affiche les meilleures solutions en fonction du critère de recherche choisi.
  - Les pires solutions : L'application affiche les pires solutions en fonction du critère de recherche choisi.

Une fois tous les critères selectionnés dans le menu, l'utilisateur peut sélectionner le nombre de solutions qu'il souhaite afficher, puis appuyer sur le bouton "Valider" pour afficher les solutions dans le tableau juste en dessous.

Toutes les solutions sont affichées dans le tableau avec les informations suivantes :
- Le n° de la solution dans la liste des solutions (la 1 correspond à la meilleure solution)
- La suite de quêtes à réaliser pour cette solution
- La durée totale de la solution
- L'expérience accumulée à la fin de la solution
- La distance parcourue à la fin de la solution
- Le nombre de quêtes réalisées

Pour plus de précisions sur les classes et les méthodes de l'application, rendez-vous sur la [documentation](docs/index.html) générée par la JavaDoc.

## Comment utiliser ?

Pour lancer l'application, il suffit d'exécuter le fichier java [App.java](src/fr/sae202/App.java) avec la commande `gradle run` et l'application se lancera.

Une fois l'application lancée, il suffit de sélectionner le scénario que l'on souhaite analyser, puis de choisir la méthode de recherche de solutions, le critère de recherche et l'ordre des résultats. Enfin, il suffit de choisir le nombre de solutions que l'on souhaite afficher et d'appuyer sur le bouton "Valider" pour afficher les solutions dans le tableau.

## Crédits
Projet réalisé par :
- [Noé COSTE](https://github.com/NoxeDev)
- [Ewen GILBERT](https://github.com/EwenDev)

Ce projet a été réalisé dans le cadre des projets du second semestre de BUT Informatique à l'IUT de Vélizy.