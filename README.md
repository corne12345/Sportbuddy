# Sportbuddy
## Programmeerproject

### Introductie
Een vriendin van mij studeert communicatie en media design aan de HU. Voor haar studie moest zij een app
ontwerpen in Marvel (puur design, niets programmeren). Nadat ze eind september de app liet zien, raakte
ik geïnspireerd om ooit aan het einde van de minor deze app daadwerkelijk te ontwikkelen. Daarom wil ik 
graag als eindproject de app SportBuddy maken.

https://marvelapp.com/519eeb3/screen/49316209 

### Problem Statement
Tussen het drukke leven door zoeken we allemaal een gaatje om nog fijn te kunnen sporten. En natuurlijk doet
iedereen dat het liefste met iemand samen, maar dat lukt niet altijd goed met vrienden. Om daarom alsnog een
stok achter de deur te vinden en medestanders te vinden is daarom de app SportBuddy bedacht. Hiermee kun je
mensen in de omgeving vinden die ook willen sporten en ze uitnodigen om samen te sporten. 

### Oplossing
SportBuddy is daarom dé oplossing om sport en gezelligheid te commbineren en beiden te maximaliseren.
De hier toegevoegde screenshots zijn door deze vriendin ontworpen, maar wil ik wel in een eigen jasje
gieten en op mijn manier implementeren.

1. Bij het openen van de app een scherm om te registeren
Deze gegevens moeten opgeslagen worden in een SQLite database die weer geladen kan worden bij het inloggen.
Het wachtwoord moet encrypt worden.
![Alt Text](https://github.com/corne12345/Sportbuddy/blob/master/doc/Sportbuddy2.PNG)

2. Na registratie een inlogscherm, dat eenmalig nodig is. Hiervoor moet de database opgeroepen worden
![Alt Text](https://github.com/corne12345/Sportbuddy/blob/master/doc/Sportbuddy3.PNG)

3. Een homescherm met daarin de opties om te chatten, je profiel inactief/actief te maken, de kaart te
beijken, je agenda te zien en een sportgeschiedenis met visualisaties. Deze geschiedenis moet opgebouwd
worden uit een andere SQLite database. Deze opties zijn niet allemaal noodzakelijk, maar is wel leuk om
zo compleet mogelijk te doen.
Aangezien ik ook dataprocessing volg, zou ik het leuk vinden om in ieder geval dat aspect te kunnen verwerken
en qua datapresenting er echt uit te springen (eventueel met een interactief figuur).
![Alt Text](https://github.com/corne12345/Sportbuddy/blob/master/doc/Sportbuddy4.PNG)

4. Je eigen profielpagina met daarop gegeens. Dit wil ik echter groter maken met daaronder een overzichtshistorie.
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
