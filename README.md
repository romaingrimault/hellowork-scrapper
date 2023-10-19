# hellowork-scrapper
## Rapel du projet
Faire une petite application en Java qui permet de : 
- scrapper et stocker les informations ( url de l'offre et référence ) des offres d'une entreprise sur HelloWork.  Se limiter à la page 1.
- pouvoir à partir d'une référence connaître l'url d'une offre
- Requis : Pour le robot : utilisation de la librairie Java HtmlUnit.
## Temps de dev
5 heures réparties en plusieurs sessions

## Initialisation du projets 
/!\ Prérequis docker 
télécarger l'image docker:

docker image pull romain53/scrapper-hellowork:2

lancer le projet: 

docker run -p 8085:8085 romain53/scrapper-hellowork:2

## Utilisation des methodes api 
Pour rendre le fonctionnement de l'api plus simple j'ai mis en place un swagger permettant d'utiliser les différentes méthodes
lien du swagger : http://localhost:8085/swagger-ui/index.html

## Choix technique
### Bdd
Utilisation de la base de données h2 pour gagner du temps. J'aurais pu utiliser une bdd mysql externe mais pour le peu à faire ça ne valiat pas le coup. Seul bémol quand on ferme l'appli les datas sont perdus. 

### Swagger
Documentation de l'enssemble des endpoint et des paramètres pour une utilisation plus simple du projet.

### Docker 
Apprentissage succinct de docker pour générer une image du projet et faciliter l'utilisation par une personne tierce.

## Choix de Conception

### Company
Mise en place de la notion de company pour pouvoir stocker l'enssemble des offres d'entreprise y compris celles qui aurait la même ref.

### xPath
Utilisation des xPath pour récupérer en une requête la node html voulu et en extraire les données

### Regex
Utilisation de regex pour récupérer la ref peu importe son format

## Amélioration possible
### Test
Mise en place de test et de CI pour améliorer la performance du développement

### Syncro complète
Récupération de l'ensemble des annonces d'entreprise et suppression de celle ayant été supprimé sur le site