# DOSSIER DE TEST - CLASSE PLAYER
## Ewen GILBERT - Noé COSTE - INF1-A

---
| Test de la classe Player                  | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 05/06/2023 |
| Responsable de la rédaction: Ewen GILBERT |

## 1. Introduction

Ce document est un dossier de test portant sur la classe Player, qui va gérer les informations relatives au joueur du RPG (Quêtes, expérience, position, temps)
## 2. Description de la procédure de test

Cette procédure a pour objectif de tester l'ensemble de la classe Player à l'aide de plusieurs fonctions de test qui permettent de tester tous les cas possibles. Nous utiliserons pour cela JUnit. Nous exécutons les méthodes de la classe Player, suivant plusieurs cas et contrôlant sa fiabilité. Nous utiliserons donc un test en boîte noire, qui permet donc de contrôler la valeur de sortie du programme.

## 3.	Description des informations à enregistrer pour les tests

Pour tester la classe, on va créer une suite de quêtes aléatoires, et on va tester les méthodes de la classe Player sur cette suite de quêtes. On contrôlera les méthodes avec les positions des quêtes par exemple.

### 1. Campagne de test

| Produit testés: Classe Player                                          |
|------------------------------------------------------------------------|
| Configuration logicielle: Windows 11 - Java sur IntelliJ               |
| Configuration matérielle: PC Portable: CPU Intel i5-12500H, 16 Go DDR4 |
| Date : 05/06/2023                                                      |
| Tests à appliquer: Tests sur les méthodes de la classe Player          |
| Responsable de la campagne de test : GILBERT Ewen                      |

### 2. Tests

#### 1. Test de la méthode movePlayer()

| Test de la méthode movePlayer()           | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 05/06/2023 |
| Responsable de la rédaction: Ewen GILBERT |

| Classe | Cas à tester              | Résultat attendu |
|--------|---------------------------|------------------|
| C1     | Position après la quête 1 | 1,2              |
| C2     | Temps après la quête 1    | 3                |
| C3     | Position après la quête 2 | -1,4             |
| C4     | Temps après la quête 2    | 7                |
| C5     | Position après la quête 3 | 2,-6             |
| C6     | Temps après la quête 3    | 20               |
| C7     | Position après la quête 4 | -3,-4            |
| C8     | Temps après la quête 4    | 27               |
| C9     | Position après la quête 0 | -5,2             |
| C10    | Temps après la quête 0    | 35               |


#### 2. Test de la méthode questDistance()

| Test de la méthode questDistance()        | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 05/06/2023 |
| Responsable de la rédaction: Ewen GILBERT |

| Classe | Cas à tester                           | Résultat attendu |
|--------|----------------------------------------|------------------|
| C1     | Distance entre le joueur et la quête 1 | 1                |
| C2     | Distance entre le joueur et la quête 2 | 3                |
| C3     | Distance entre le joueur et la quête 3 | 10               |
| C4     | Distance entre le joueur et la quête 4 | 11               |

#### 3. Test de la méthode addFinishedQuest()

| Test de la méthode addFinishedQuest()     | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 05/06/2023 |
| Responsable de la rédaction: Ewen GILBERT |

| Classe | Cas à tester                                              | Résultat attendu |
|--------|-----------------------------------------------------------|------------------|
| C1     | Temps après la quête 1                                    | 3                |
| C2     | Expérience après la quête 1                               | 100              |
| C3     | Temps après la quête 2                                    | 9                |
| C4     | Expérience après la quête 2                               | 300              |
| C5     | Temps après la quête 3                                    | 11               |
| C6     | Expérience après la quête 3                               | 600              |
| C7     | Temps après la quête 4                                    | 16               |
| C8     | Expérience après la quête 4                               | 1000             |
| C9     | Temps après la quête 0                                    | 21               |
| C10    | Expérience après la quête 0                               | 1000             |
| C11    | Présence de la quête 1 dans la liste des quêtes terminées | Oui              |
| C12    | Présence de la quête 2 dans la liste des quêtes terminées | Oui              |
| C13    | Présence de la quête 3 dans la liste des quêtes terminées | Oui              |
| C14    | Présence de la quête 4 dans la liste des quêtes terminées | Oui              |
| C15    | Présence de la quête 0 dans la liste des quêtes terminées | Oui              |

### 3. Résulats de tests

#### 1. Test de la méthode movePlayer()

| Test de la méthode movePlayer()           | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 05/06/2023 |
| Responsable de la rédaction: Ewen GILBERT |

| Classe | Cas à tester              | Résultat attendu | Résultat observé | Résultat du test |
|--------|---------------------------|------------------|------------------|------------------|
| C1     | Position après la quête 1 | 1,2              | 1,2              | OK               |
| C2     | Temps après la quête 1    | 3                | 3                | OK               |
| C3     | Position après la quête 2 | -1,4             | -1,4             | OK               |
| C4     | Temps après la quête 2    | 7                | 7                | OK               |
| C5     | Position après la quête 3 | 2,-6             | 2,-6             | OK               |
| C6     | Temps après la quête 3    | 20               | 20               | OK               |
| C7     | Position après la quête 4 | -3,-4            | -3,-4            | OK               |
| C8     | Temps après la quête 4    | 27               | 27               | OK               |
| C9     | Position après la quête 0 | -5,2             | -5,2             | OK               |
| C10    | Temps après la quête 0    | 35               | 35               | OK               |


#### 2. Test de la méthode questDistance()

| Test de la méthode questDistance()        | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 05/06/2023 |
| Responsable de la rédaction: Ewen GILBERT |

| Classe | Cas à tester                           | Résultat attendu | Résultat observé | Résultat du test |
|--------|----------------------------------------|------------------|------------------|------------------|
| C1     | Distance entre le joueur et la quête 1 | 1                | 1                | OK               |
| C2     | Distance entre le joueur et la quête 2 | 3                | 3                | OK               |
| C3     | Distance entre le joueur et la quête 3 | 10               | 10               | OK               |
| C4     | Distance entre le joueur et la quête 4 | 11               | 11               | OK               |

#### 3. Test de la méthode addFinishedQuest()

| Test de la méthode addFinishedQuest()     | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 05/06/2023 |
| Responsable de la rédaction: Ewen GILBERT |

| Classe | Cas à tester                                              | Résultat attendu | Résultat observé | Résultat du test |
|--------|-----------------------------------------------------------|------------------|------------------|------------------|
| C1     | Temps après la quête 1                                    | 3                | 3                | OK               |
| C2     | Expérience après la quête 1                               | 100              | 100              | OK               |
| C3     | Temps après la quête 2                                    | 9                | 9                | OK               |
| C4     | Expérience après la quête 2                               | 300              | 300              | OK               |
| C5     | Temps après la quête 3                                    | 11               | 11               | OK               |
| C6     | Expérience après la quête 3                               | 600              | 600              | OK               |
| C7     | Temps après la quête 4                                    | 16               | 16               | OK               |
| C8     | Expérience après la quête 4                               | 1000             | 1000             | OK               |
| C9     | Temps après la quête 0                                    | 21               | 21               | OK               |
| C10    | Expérience après la quête 0                               | 1000             | 1000             | OK               |
| C11    | Présence de la quête 1 dans la liste des quêtes terminées | Oui              | Oui              | OK               |
| C12    | Présence de la quête 2 dans la liste des quêtes terminées | Oui              | Oui              | OK               |
| C13    | Présence de la quête 3 dans la liste des quêtes terminées | Oui              | Oui              | OK               |
| C14    | Présence de la quête 4 dans la liste des quêtes terminées | Oui              | Oui              | OK               |
| C15    | Présence de la quête 0 dans la liste des quêtes terminées | Oui              | Oui              | OK               |