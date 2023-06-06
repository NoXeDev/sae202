# DOSSIER DE TEST - QUESTPARSER
## Ewen GILBERT - Noé COSTE - INF1-A

---
| Test de la classe QuestParser             | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 20/05/2023 |
| Responsable de la rédaction: Ewen GILBERT |

## 1. Introduction

Ce document est un dossier de test portant sur le QuestParser, étant le système qui va récupérer les données des fichiers textes contenant les données les scénarios.
## 2. Description de la procédure de test

Cette procédure a pour objectif de tester l'ensemble de la classe QuestParser à l'aide de plusieurs fonctions de test qui permettent de tester tous les cas possibles. Nous utiliserons pour cela JUnit. Nous exécutons les méthodes de la classe QuestParser, suivant plusieurs cas et contrôlant sa fiabilité. Nous utiliserons donc un test en boîte noire, qui permet donc de contrôler la valeur de sortie du programme.

## 3.	Description des informations à enregistrer pour les tests

### 1. Campagne de test

| Produit testés: Classe QuestParser                                     |
|------------------------------------------------------------------------|
| Configuration logicielle: Windows 11 - Java sur IntelliJ               |
| Configuration matérielle: PC Portable: CPU Intel i5-12500H, 16 Go DDR4 |
| Date : 20/05/2023                                                      |
| Tests à appliquer: Contrôle du parser de données                       |
| Responsable de la campagne de test : GILBERT Ewen                      |

### 2. Tests

#### 1. Test de la méthode parseScenarioTest()

| Test de la méthode parseScenarioTest()     | Version: 1.0     |
|--------------------------------------------|------------------|
| Document: Dossier de tests                 | Date: 20/05/2023 |
| Responsable de la rédaction: Ewen GILBERT  |

| Classe | Valeur nulle                                  | Résultat attendu      |
|--------|-----------------------------------------------|-----------------------|
| C1     | Précondition                                  | Pas d'exception levée |
| C2     | Second vecteur de précondition                | Pas d'exception levée |
| C3     | Une valeur du premier vecteur de précondition | Pas d'exception levée |
| C4     | Une valeur du second vecteur de précondition  | Pas d'exception levée |
| C5     | Aucune                                        | Pas d'exception levée |

#### 2. Test de la méthode parseScenarioExceptionsTest()

| Test de la méthode parseScenarioExceptionsTest() | Version: 1.0     |
|--------------------------------------------------|------------------|
| Document: Dossier de tests                       | Date: 20/05/2023 |
| Responsable de la rédaction: Ewen GILBERT        |

| Classe | Cas à tester              | Résultat attendu          |
|--------|---------------------------|---------------------------|
| C1     | ID de scénario non trouvé | ScenarioNotFoundException |
| C2     | Scénario donné invalide   | QuestParseException       |

### 3. Résulats de tests

#### 1. Test de la méthode parseScenarioTest()

| Test de la méthode parseScenarioTest()    | Version: 1.0     |
|-------------------------------------------|------------------|
| Document: Dossier de tests                | Date: 20/05/2023 |
| Responsable de la rédaction: Ewen GILBERT |

| Classe | Valeur nulle                                  | Résultat attendu       | Résultat observé      | Résultat du test |
|--------|-----------------------------------------------|------------------------|-----------------------|------------------|
| C1     | Précondition                                  | Pas d'exception levée  | Pas d'exception levée | OK               | 
| C2     | Seconde valeur de précondition                | Pas d'exception levée  | Pas d'exception levée | OK               |
| C3     | Une valeur du premier vecteur de précondition | Pas d'exception levée  | Pas d'exception levée | OK               |
| C4     | Une valeur du second vecteur de précondition  | Pas d'exception levée  | Pas d'exception levée | OK               |
| C5     | Aucune                                        | Pas d'exception levée  | Pas d'exception levée | OK               |

#### 2. Test de la méthode parseScenarioExceptionsTest()

| Test de la méthode parseScenarioExceptionsTest() | Version: 1.0     |
|--------------------------------------------------|------------------|
| Document: Dossier de tests                       | Date: 20/05/2023 |
| Responsable de la rédaction: Ewen GILBERT        |

| Classe | Cas à tester              | Résultat attendu          | Résultat observé          | Résultat du test  |
|--------|---------------------------|---------------------------|---------------------------|-------------------|
| C1     | ID de scénario non trouvé | ScenarioNotFoundException | ScenarioNotFoundException | OK                |
| C2     | Scénario donné invalide   | QuestParseException       | QuestParseException       | OK                |