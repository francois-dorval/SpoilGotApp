Une appli pour spoiler Game of Thrones!
pouet

(pas d'inquiétude les données sont incomplètes parce que bon...).

------------------------------------
# Technos utilisées

Appli java/springboot/maven  
Database : firebase  


------------------------------------
# front

L'appli a une partie web (src/main/resources/static/index.html) basique en vuejs.


------------------------------------
# API


L'appli se compose principalement d'une API avec un endpoint :  

GET /characters  
ou
GET /characters?season=<seasonNumber>  

Elle renvoie des personnages de Game of Thrones filtrés par saison.  
En java : une liste de "GotCharacterFront"  
Exemple de réponse : GET /characters?season=3  
``
[  
   {  
      "name":"Arya Stark",
      "deadInSeason":false
   },
   {  
      "name":"Roose Bolton",
      "deadInSeason":false
   },
   {  
      "name":"Robb Stark",
      "deadInSeason":true,
      "causeOfDeath":"killed by Roose Bolton"
   },
   (...)
   ]
  ``
Les personnages tués dans la saison ont le flag "deadInSeason" à true.  
Ceux qui ont été tués avant la saison en paramètre n'apparaissent pas.  
On a parfois un champ "causeOfDeath" qui indique qui a tué le personnage.  
   

------------------------------------
# Database
   
Les données dans firebase ressemblent à ça :  
``
[	
	GotCharacter [name=Arya Stark,           id=1,  killedby=null, killedinseason=null],   
	GotCharacter [name=Daenerys Targaryen,   id=10, killedby=null, killedinseason=null],   
	GotCharacter [name=Ramsay Bolton,        id=11, killedby=null, killedinseason=S6],   
	GotCharacter [name=Roose Bolton,         id=12, killedby=11,   killedinseason=S6],   
	GotCharacter [name=Robb Stark,           id=13, killedby=12,   killedinseason=S3],   
	GotCharacter [name=Ned Stark,            id=2,  killedby=3,    killedinseason=S1]  
(...)
``  
En java : une liste de "GotCharacterFirebase"

   
------------------------------------
# Code

L'appli ne fait que du filtrage et du mapping.

## Point d'entrée de l'api :   
* SpoilController.java  

## Couche métier :   
* SpoilBusiness.java  


# Qualimétrie
à compléter!

# Gitlab
à compléter aussi!

   
   
   
