# DOSSIER DE TEST - CLASSE ALGORITHMS
## Ewen GILBERT - Noé COSTE - INF1-A

---
| Test de la classe Algorithms              | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 05/06/2023 |
| Responsable de la rédaction: Ewen GILBERT |

## 1. Introduction

Ce document est un dossier de test portant sur la classe Algorithms, qui va gérer les principaux algorithmes qui vont être utilisés par la classe Game pour générer les différentes solutions.
## 2. Description de la procédure de test

Cette procédure a pour objectif de tester l'ensemble de la classe Algorithms à l'aide de plusieurs fonctions de test qui permettent de tester tous les cas possibles. Nous utiliserons pour cela JUnit. Nous exécutons les méthodes de la classe Algorithms, suivant plusieurs cas et contrôlant sa fiabilité. Nous utiliserons donc un test en boîte noire, qui permet donc de contrôler la valeur de sortie du programme.

## 3.	Description des informations à enregistrer pour les tests

Pour tester la classe, on va créer une suite de quêtes aléatoires, et on va tester les méthodes de la classe Algorithms sur cette suite de quêtes.

### 1. Campagne de test

| Produit testés: Classe Algorithm                                       |
|------------------------------------------------------------------------|
| Configuration logicielle: Windows 11 - Java sur IntelliJ               |
| Configuration matérielle: PC Portable: CPU Intel i5-12500H, 16 Go DDR4 |
| Date : 05/06/2023                                                      |
| Tests à appliquer: Tests sur les méthodes de la classe Algorithm       |
| Responsable de la campagne de test : GILBERT Ewen                      |

### 2. Tests

#### 1. Test de la méthode nearestQuest()

| Test de la méthode nearestQuest()         | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 05/06/2023 |
| Responsable de la rédaction: Ewen GILBERT |

| Classe | Cas à tester                       | Résultat attendu |
|--------|------------------------------------|------------------|
| C1     | Quête la plus proche de la quête 1 | Quête 2          |
| C2     | Quête la plus proche de la quête 2 | Quête 4          |


#### 2. Test de la méthode fetchAvailableQuests()

| Test de la méthode fetchAvailableQuests() | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 05/06/2023 |
| Responsable de la rédaction: Ewen GILBERT |

| Classe | Cas à tester                                           | Résultat attendu |
|--------|--------------------------------------------------------|------------------|
| C1     | Quêtes disponibles après avoir terminé la quête 1 et 2 | Quêtes 3 et 4    |

#### 3. Test de la méthode findAllPaths()

| Test de la méthode findAllPaths()          | Version: 1.0     |
|--------------------------------------------|------------------|
| Document: Dossier de tests                 | Date: 07/06/2023 |
| Responsable de la rédaction: Ewen GILBERT  |

| Classe | Chemins à trouver | Résultat attendu |
|--------|-------------------|------------------|
| C1     | Chemin 1          | 1,2,3,0          |
| C2     | Chemin 2          | 1,2,3,4,0        |
| C3     | Chemin 3          | 1,2,4,0          |
| C4     | Chemin 4          | 1,2,4,3,0        |

#### 4. Test de la méthode findAllPaths() pour une recherche exhaustive

| Test de la méthode findAllPaths() pour une recherche exhaustive | Version: 1.0     |
|-----------------------------------------------------------------|------------------|
| Document: Dossier de tests                                      | Date: 07/06/2023 |
| Responsable de la rédaction: Ewen GILBERT                       |

| Classe | Cas à tester                   | Résultat attendu |
|--------|--------------------------------|------------------|
| C1     | Les chemins ont la même taille | VRAI             |

#### 5. Test de la méthode fastestPath()

| Test de la méthode fastestPath()          | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 07/06/2023 |
| Responsable de la rédaction: Ewen GILBERT |

| Classe | Cas à tester       | Résultat attendu |
|--------|--------------------|------------------|
| C1     | Meilleure solution | 1,2,4,0          |
| C2     | Pire solution      | 1,2,3,4,0        |

#### 6. Test de la méthode shortestNBQuestsPath()

| Test de la méthode fastestPath()          | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 07/06/2023 |
| Responsable de la rédaction: Ewen GILBERT |

| Classe | Cas à tester       | Résultat attendu |
|--------|--------------------|------------------|
| C1     | Meilleure solution | 1,2,4,0          |
| C2     | Pire solution      | 1,2,4,3,0        |

#### 7. Test de la méthode shortestDistancePath()

| Test de la méthode shortestDistancePath() | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 07/06/2023 |
| Responsable de la rédaction: Ewen GILBERT |

| Classe | Cas à tester       | Résultat attendu |
|--------|--------------------|------------------|
| C1     | Meilleure solution | 1,2,4,0          |
| C2     | Pire solution      | 1,2,3,4,5        |

### 3. Résulats de tests

#### 1. Test de la méthode nearestQuest()

| Test de la méthode nearestQuest()         | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 05/06/2023 |
| Responsable de la rédaction: Ewen GILBERT |

| Classe | Cas à tester                       | Résultat attendu | Résultat observé | Résultat du test |
|--------|------------------------------------|------------------|------------------|------------------|
| C1     | Quête la plus proche de la quête 1 | Quête 2          | Quête 2          | OK               |
| C2     | Quête la plus proche de la quête 2 | Quête 4          | Quête 4          | OK               |


#### 2. Test de la méthode fetchAvailableQuests()

| Test de la méthode fetchAvailableQuests() | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 05/06/2023 |
| Responsable de la rédaction: Ewen GILBERT |

| Classe | Cas à tester                                           | Résultat attendu | Résultat observé | Résultat du test |
|--------|--------------------------------------------------------|------------------|------------------|------------------|
| C1     | Quêtes disponibles après avoir terminé la quête 1 et 2 | Quêtes 3 et 4    | Quêtes 3 et 4    | OK               |

#### 3. Test de la méthode findAllPaths()

| Test de la méthode findAllPaths()          | Version: 1.0     |
|--------------------------------------------|------------------|
| Document: Dossier de tests                 | Date: 07/06/2023 |
| Responsable de la rédaction: Ewen GILBERT  |

| Classe | Chemins à trouver | Résultat attendu | Résultat observé | Résultat du test |
|--------|-------------------|------------------|------------------|------------------|
| C1     | Chemin 1          | 1,2,3,0          | 1,2,3,0          | OK               |
| C2     | Chemin 2          | 1,2,3,4,0        | 1,2,3,4,0        | OK               |
| C3     | Chemin 3          | 1,2,4,0          | 1,2,4,0          | OK               |
| C4     | Chemin 4          | 1,2,4,3,0        | 1,2,4,3,0        | OK               |

#### 4. Test de la méthode findAllPaths() pour une recherche exhaustive

| Test de la méthode findAllPaths() pour une recherche exhaustive | Version: 1.0     |
|-----------------------------------------------------------------|------------------|
| Document: Dossier de tests                                      | Date: 07/06/2023 |
| Responsable de la rédaction: Ewen GILBERT                       |

| Classe | Cas à tester                   | Résultat attendu | Résultat observé | Résultat du test |
|--------|--------------------------------|------------------|------------------|------------------|
| C1     | Les chemins ont la même taille | VRAI             | VRAI             | OK               |

#### 5. Test de la méthode fastestPath()

| Test de la méthode fastestPath()          | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 07/06/2023 |
| Responsable de la rédaction: Ewen GILBERT |

| Classe | Cas à tester       | Résultat attendu | Résultat observé | Résultat du test |
|--------|--------------------|------------------|------------------|------------------|
| C1     | Meilleure solution | 1,2,4,0          | 1,2,4,0          | OK               |
| C2     | Pire solution      | 1,2,3,4,0        | 1,2,3,4,0        | OK               |

#### 6. Test de la méthode shortestNBQuestsPath()

| Test de la méthode fastestPath()          | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 07/06/2023 |
| Responsable de la rédaction: Ewen GILBERT |

| Classe | Cas à tester       | Résultat attendu | Résultat observé | Résultat du test |
|--------|--------------------|------------------|------------------|------------------|
| C1     | Meilleure solution | 1,2,4,0          | 1,2,4,0          | OK               |
| C2     | Pire solution      | 1,2,4,3,0        | 1,2,4,3,0        | OK               |

#### 7. Test de la méthode shortestDistancePath()

| Test de la méthode shortestDistancePath() | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 07/06/2023 |
| Responsable de la rédaction: Ewen GILBERT |

| Classe | Cas à tester       | Résultat attendu |
|--------|--------------------|------------------|
| C1     | Meilleure solution | 1,2,4,0          |
| C2     | Pire solution      | 1,2,3,4,0        |