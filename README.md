# Sportbuddy
## Programmeerproejct

### Problem Statement
De meeste studenten proberen tusen het drukke leven van studeren, werken en andere activiteiten ook nog eens fit te blijven. De
feestjes en andere activiteiten verpesten helaas alleen nog wel eens de goede voornemens. Na een zware avond biertjes drinken in de 
kroeg en als afsluiter nog een kapsalon of een tripje naar de McDonalds blijven we dan de volgende dag met een schuldgevoel achter en 
een drive om dit de volgende dag te compenseren. Maar hoeveel rondjes moet ik hiervoor rennen of hoe ver fietsen?

### Solution
Sportbuddy is dé app die je calorie-inname van je cheatmoment berekent en vervolgens je helpt een plan te maken om deze caloriën er 
op een goede manier weer af te bewegen!
[Hier](https://marvelapp.com/project/3674379) is een representatie van de app. 

1. Het eerste scherm laat de gebruiker zoeken naar de 'slechte' inname die gecompenseerd moet worden. Hiervoor wordt de Nutritionix API gebruikt. Deze laat je zoeken naar losse items, maar ook naar complete maaltijden en veel menu's van bijvoorbeeld fastfoodrestaurants.
2. Het tweede scherm toont dan de totale voedingswaarden van al datgene wat gegeten is. Dit scherm wordt apart weergegeven, om op die manier ook een stukje bewustwording te creëeren. 
3. Het derde scherm laat de user vervolgens kiezen welke activiteit hij/zij wil uitvoeren om te compenseren.
4. Dit scherm geeft een overzicht met de hoeveelheid tijd en/of afstand de gebruiker moet gaan afleggen. Vervolgen is er de mogelijkheid heul te krijgen om dit te plannen.
5. Een Google Maps API helpt vervolgens de afstand te bepalen vanaf de huidige lcoatie naar een ander punt om zo een doelafstand te bepalen. 

De eerste 4 schermen vormen tezamen het minimum viable product (MVP), het vijfde scherm is optioneel om te implementeren. Mochten deze activiteiten op tijd afgerond worden kan de voeding toegevoegd worden aan een database en eventueel gevisualiseerd worden of kunnen elementen uit het onderstaande, eerste plan geïmplementeerd worden.

### Prerequisites
Deze app maakt gebruikt van twee API's:
- Nutritionix (https://www.nutritionix.com/business/api)
- Google Maps
Nutritionix is in staat een groot scala aan input van voeding te herkennen en de voedingswaarden hiervan weer te geven. Via deze database kan meteen ook een request gemaakt worden om de duur van deze activiteiten te bepalen. Hiervoor zou ook een andere database mogelijk gebruikt kunnen worden.

Indien er een database of historie van cheatfoods emaakt wordt is het nodig deze op te slaan in een SQLite database.

De grootste uitdaging van de app zit in het omzetten van de zoekactie van de gebruiker in een calorietotaal, met name wanneer de invoeropdrachten gecompliceerder worden of er een autocomplete/autocorrect noodzakelijk is. 

.
.
.
.
.
.
.
.
.
# Oude Versie



## Programmeerproject

### Introductie
Een vriendin van mij studeert communicatie en media design aan de HU. Voor haar studie moest zij een app
ontwerpen in Marvel (puur design, niets programmeren). Nadat ze eind september de app liet zien, raakte
ik geïnspireerd om ooit aan het einde van de minor deze app daadwerkelijk te ontwikkelen. Daarom wil ik 
graag als eindproject een variatie op haar app SportBuddy maken, HealthBuddy.

https://marvelapp.com/519eeb3/screen/49316209 

### Problem Statement
Tussen het drukke leven door zoeken we allemaal een gaatje om nog fijn te kunnen sporten. En natuurlijk doet
iedereen dat het liefste met iemand samen, maar dat lukt niet altijd goed met vrienden. Om daarom alsnog een
stok achter de deur te vinden en medestanders te vinden is daarom de app SportBuddy bedacht. Hiermee kun je
mensen in de omgeving vinden die ook willen sporten en ze uitnodigen om samen te sporten.
Een gezond leven begint natuurlijk niet bij sporten, maar bij goede voeding. Een extra aspect daarom is
een functie die het mogelijk maakt ingenomen voeding te registreren en bij te houden. Dit volgt echter op de eerste
functie van het vinden van vrienden.

### Oplossing
SportBuddy is daarom dé oplossing om sport en gezelligheid te commbineren en beiden te maximaliseren.
Om een werkende app te krijgen is het volgende noodzakelijk:
* Een scherm om in te loggen en/of te registreren met achterliggende SQLite-database
* Een kaart om andere gebruikers op te vinden
* Een chatfunctie
* Een pagina om de voedingswaarden van eten te kunnen vinden (Nutritionix) en dit op te slaan in een SQLite-database.
* Een SQLite-database om de opgeslagen activiteiten in op te slaan; ook calorieverbruik is hiervoor noodzakelijk.

De hier toegevoegde screenshots zijn door deze vriendin ontworpen, maar wil ik wel in een eigen jasje
gieten en op mijn manier implementeren.

1. Bij het openen van de app een scherm om te registeren
Deze gegevens moeten opgeslagen worden in een SQLite database die weer geladen kan worden bij het inloggen.
Het wachtwoord moet encrypt worden.
![Alt Text](https://github.com/corne12345/Sportbuddy/blob/master/doc/Oud/Sportbuddy2.PNG)

2. Na registratie een inlogscherm, dat eenmalig nodig is. Hiervoor moet de database opgeroepen worden
![Alt Text](https://github.com/corne12345/Sportbuddy/blob/master/doc/Sportbuddy3.PNG)

3. Een homescherm met daarin de opties om te chatten, je profiel inactief/actief te maken, de kaart te
beijken, je agenda te zien en een sportgeschiedenis met visualisaties. Deze geschiedenis moet opgebouwd
worden uit een andere SQLite database. Deze opties zijn niet allemaal noodzakelijk, maar is wel leuk om
zo compleet mogelijk te doen.
Aangezien ik ook dataprocessing volg, zou ik het leuk vinden om in ieder geval dat aspect te kunnen verwerken
en qua datapresenting er echt uit te springen (eventueel met een interactief figuur).

![Alt Text](https://github.com/corne12345/Sportbuddy/blob/master/doc/Sportbuddy4.PNG)

4. Je eigen profielpagina met daarop gegevens. Dit wil ik echter groter maken met daaronder een overzichtshistorie.
Hierop kan bijvoorbeeld hardloopsessies gelogd worden of voeding bijgehouden worden en het aantal activiteiten
per week.
![Alt Text](https://github.com/corne12345/Sportbuddy/blob/master/doc/Sportbuddy11.PNG)

5. Een map waarop de andere gebruikers zichtbaar zijn op hun locatie. Deze gebruikers moeten zichtbaar
zijn en klikbaar, waarna je op hun profielpagina terecht komt en hun sporten kan bekijken. Eventueel
kun je ze ook als vriend toevoegen.
![Alt Text](https://github.com/corne12345/Sportbuddy/blob/master/doc/Sportbuddy12.PNG)

Het profielscherm van vrienden zal erg lijken op het eigen profielscherm, maar zal minder info bevatten.
![Alt Text](https://github.com/corne12345/Sportbuddy/blob/master/doc/Sportbuddy8.PNG)

### Vereisten
Voor deze app zijn in principe geen externe data nodig. Eventueel zou er voor de voeding in het profiel
data geladen kunnen worden vanuit een leverancier van producten om klein te beginnen.

Voor de app is naast het gebruik van App Studio een SQLite database nodig voor de opslag van persoonsgegeven.

Deze app houdt het midden tussen een sportapp (denk aan Runkeeper) en een social media app (zoals Facebook).
Beiden zal ik goed gaan inspecteren om te zien hoe zij bepaalde zaken implementeren en hier hopelijk van
te leren.

De app lijkt redelijk ambitieus te zijn om volledig uit te voeren. De grootste valkuil is dat ik te snel
te veel wil doen, en er dan uiteindelijk niet een volledig mooi eindproduct uitkomt. Daarnaast denk ik
dat de koppeling tussen een database, een visualisatiekit en AppStudio lastig is, maar ik weet niet of
daarvoor nog handvatten ontstaan tijdens de course.
Een andere uitdaging lijkt me de vele schermen en buttons die allemaal goed met elkaar moeten linken. 
Ik zal dit langzaam moeten opbouwen en goed de voortgang moeten bewaken.
