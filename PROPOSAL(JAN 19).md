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

