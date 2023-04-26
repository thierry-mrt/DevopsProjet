# DevopsProjet


[![build-project-and-launch-tests](https://github.com/thierry-mrt/DevopsProjet/actions/workflows/build-and-tests.yml/badge.svg?branch=master&event=push)](https://github.com/thierry-mrt/DevopsProjet/actions/workflows/build-and-tests.yml)


[![codecov](https://codecov.io/gh/thierry-mrt/DevopsProjet/branch/master/graph/badge.svg?token=OKD8PQ96WK)](https://codecov.io/gh/thierry-mrt/DevopsProjet)

## Fonctionnalités

 - [x] Création d'un Dataframe à partir d'un tableau contenant les lignes (données passées en tant que chaines de caractères) , un tableau de chaines représentant les noms des colonnes, un tableau contenant les indices de chaque ligne, et un tableau contenant les types de données de chaque colonne.
 - [x] Création d'un Dataframe à partir du contenu d'un fichier .csv
 - [x] Affichage d'un Dataframe sur la console.
 - [x] Affichage des n-premières lignes d'un Dataframe.
 - [x] Affichage des n-dernières lignes d'un Dataframe.
 - [x] Calcul de la somme des données d'une colonne d'un Dataframe (si données numériques).
 - [x] Calcul de la moyenne des données d'une colonne d'un Dataframe (si données numériques).
 - [x] Calcul de la valeur maximale des données d'une colonne d'un Dataframe (si données numériques).
 - [x] Calcul de la valeur minimale des données d'une colonne d'un Dataframe (si données numériques).
 - [x] Création d'un Dataframe qui correspond au sous-ensemble des lignes d'un Dataframe existant.
 - [x] Création d'un Dataframe qui correspond au sous-ensemble des colonnes d'un Dataframe existant.

## WorkFlow 

<b> Schéma général : </b >Feature branch | Pull/Merge Request <br>

<b> Rôle des collaborateurs : </b> <br>
Lucas : Mise en place et configuration de l'intégration continue + revue de code. <br>
Thierry : Développement, Test, et revue de code. <br>
Abdoulhakim : Développement, Test, et revue de code. <br>

## Outils

GitHub : Versionnage <br>
Maven : build & compiler <br>
JaCoCo : outil de couverture de code, creation de rapport de couverture <br>
Codecov : outil de reporting pour la couverture de code (découvert sur github marketplace) <br>
JUnit : test
