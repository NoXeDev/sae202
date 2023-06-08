# DOSSIER DE TEST - CLASSE GAME
## Ewen GILBERT - Noé COSTE - INF1-A

---
| Test de la classe Game                    | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 07/06/2023 |
| Responsable de la rédaction: Ewen GILBERT |

## 1. Introduction

Ce document est un dossier de test portant sur la classe Game, qui va mettre en relation les principaux algorithmes pour générer les différentes solutions.
## 2. Description de la procédure de test

Cette procédure a pour objectif de tester l'ensemble de la classe Game à l'aide de plusieurs fonctions de test qui permettent de tester tous les cas possibles. Nous utiliserons pour cela JUnit. Nous exécutons les méthodes de la classe Game, suivant plusieurs cas et contrôlant sa fiabilité. Nous utiliserons donc un test en boîte noire, qui permet donc de contrôler la valeur de sortie du programme.

## 3.	Description des informations à enregistrer pour les tests

Pour tester la classe, on va créer une suite de quêtes aléatoires, et on va tester les méthodes de la classe Game sur cette suite de quêtes.

### 1. Campagne de test

| Produit testés: Game                                                   |
|------------------------------------------------------------------------|
| Configuration logicielle: Windows 11 - Java sur IntelliJ               |
| Configuration matérielle: PC Portable: CPU Intel i5-12500H, 16 Go DDR4 |
| Date : 07/06/2023                                                      |
| Tests à appliquer: Tests sur les méthodes de la classe Game            |
| Responsable de la campagne de test : GILBERT Ewen                      |

### 2. Tests

#### 1. Test de la méthode solutionEfficaceGloutonne()

| Test de la méthode solutionEfficaceGloutonne() | Version: 1.0     |
|------------------------------------------------|------------------|
| Document: Dossier de tests                     | Date: 07/06/2023 |
| Responsable de la rédaction: Ewen GILBERT      |

| Classe | Cas à tester                      | Résultat attendu |
|--------|-----------------------------------|------------------|
| C1     | Durée de la solution              | Entre 27 et 30   |
| C2     | Experience de la solution         | Entre 350 et 450 |
| C3     | Nombre de quêtes de la solution   | 4                |
| C4     | Distance parcourue de la solution | Entre 14 et 20   |

#### 2. Test de la méthode solutionExhaustiveGloutonne()

| Test de la méthode solutionExhaustiveGloutonne() | Version: 1.0     |
|--------------------------------------------------|------------------|
| Document: Dossier de tests                       | Date: 07/06/2023 |
| Responsable de la rédaction: Ewen GILBERT        |

| Classe | Cas à tester                      | Résultat attendu |
|--------|-----------------------------------|------------------|
| C1     | Durée de la solution              | Entre 36 et 40   |
| C2     | Experience de la solution         | 550              |
| C3     | Nombre de quêtes de la solution   | 5                |
| C4     | Distance parcourue de la solution | Entre 20 et 24   |

#### 3. Test de la méthode speedrun()

| Test de la méthode speedrun()             | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 07/06/2023 |
| Responsable de la rédaction: Ewen GILBERT |

| Classe | Cas à tester       | Résultat attendu |
|--------|--------------------|------------------|
| C1     | Meilleure solution | 1,4,3,2,5,0      |

#### 4. Test de la méthode bestSpeedruns()

| Test de la méthode bestSpeedruns()        | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 07/06/2023 |
| Responsable de la rédaction: Ewen GILBERT |

| Classe | Cas à tester       | Résultat attendu |
|--------|--------------------|------------------|
| C1     | Meilleure solution | 1,4,3,2,5,0      |

#### 5. Test de la méthode bestNBQuests()

| Test de la méthode bestNBQuests()         | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 07/06/2023 |
| Responsable de la rédaction: Ewen GILBERT |

| Classe | Cas à tester       | Résultat attendu |
|--------|--------------------|------------------|
| C1     | Meilleure solution | 4,2,3,5,0        |

#### 6. Test de la méthode bestDistancePath()

| Test de la méthode bestDistancePath()     | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 07/06/2023 |
| Responsable de la rédaction: Ewen GILBERT |

| Classe | Cas à tester       | Résultat attendu |
|--------|--------------------|------------------|
| C1     | Meilleure solution | 1,4,3,2,5,0      |

### 3. Résulats de tests

#### 1. Test de la méthode nearestQuest()

| Test de la méthode solutionEfficaceGloutonne() | Version: 1.0     |
|------------------------------------------------|------------------|
| Document: Dossier de tests                     | Date: 07/06/2023 |
| Responsable de la rédaction: Ewen GILBERT      |

| Classe | Cas à tester                      | Résultat attendu | Résultat observé | Résultat du test |
|--------|-----------------------------------|------------------|------------------|------------------|
| C1     | Durée de la solution              | Entre 27 et 30   | 27               | OK               |
| C2     | Experience de la solution         | Entre 350 et 450 | 350              | OK               |
| C3     | Nombre de quêtes de la solution   | 4                | 4                | OK               |
| C4     | Distance parcourue de la solution | Entre 14 et 20   | 14               | OK               |

#### 2. Test de la méthode solutionExhaustiveGloutonne()

| Test de la méthode solutionExhaustiveGloutonne() | Version: 1.0     |
|--------------------------------------------------|------------------|
| Document: Dossier de tests                       | Date: 07/06/2023 |
| Responsable de la rédaction: Ewen GILBERT        |

| Classe | Cas à tester                      | Résultat attendu | Résultat observé | Résultat du test |
|--------|-----------------------------------|------------------|------------------|------------------|
| C1     | Durée de la solution              | Entre 36 et 40   | 40               | OK               |
| C2     | Experience de la solution         | 550              | 550              | OK               |
| C3     | Nombre de quêtes de la solution   | 5                | 5                | OK               |
| C4     | Distance parcourue de la solution | Entre 20 et 24   | 24               | OK               |

#### 3. Test de la méthode speedrun()

| Test de la méthode speedrun()             | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 07/06/2023 |
| Responsable de la rédaction: Ewen GILBERT |

| Classe | Cas à tester       | Résultat attendu | Résultat observé | Résultat du test |
|--------|--------------------|------------------|------------------|------------------|
| C1     | Meilleure solution | 1,4,3,2,5,0      | 1,4,3,2,5,0      | OK               |

#### 4. Test de la méthode bestSpeedruns()

| Test de la méthode bestSpeedruns()        | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 07/06/2023 |
| Responsable de la rédaction: Ewen GILBERT |

| Classe | Cas à tester       | Résultat attendu | Résultat observé | Résultat du test |
|--------|--------------------|------------------|------------------|------------------|
| C1     | Meilleure solution | 1,4,3,2,5,0      | 1,4,3,2,5,0      | OK               |

#### 5. Test de la méthode bestNBQuests()

| Test de la méthode bestNBQuests()         | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 07/06/2023 |
| Responsable de la rédaction: Ewen GILBERT |

| Classe | Cas à tester       | Résultat attendu | Résultat observé | Résultat du test |
|--------|--------------------|------------------|------------------|------------------|
| C1     | Meilleure solution | 4,2,3,5,0        | 4,2,3,5,0        | OK               |

#### 6. Test de la méthode bestDistancePath()

| Test de la méthode bestDistancePath()     | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 07/06/2023 |
| Responsable de la rédaction: Ewen GILBERT |

| Classe | Cas à tester       | Résultat attendu | Résultat observé | Résultat du test |
|--------|--------------------|------------------|------------------|------------------|
| C1     | Meilleure solution | 1,4,3,2,5,0      | 1,4,3,2,5,0      | OK               |