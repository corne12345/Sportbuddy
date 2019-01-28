# day 1 (JAN 7 2019)
Het plan voor de app sportbuddy geüpdate. Van een app die vooral vrienden zoekt toch ook een voedingsaspect uitgezocht. Deze optie
maakt op een interessantere manier gebruik van een uitgebreide API en zorgt misschien voor een mooiere leercurve dan alleen maar eindeloos
veel activities aanmaken.

# day 2 (JAN 8 2019)
Allereerst de Nutritionix API aangemeld en gekeken of het lukt om via Postman request te doen. Deze geeft alle gewenste resultaten mbt
het opzoeken van voedingswaarden van voedsel en zelfs het invullen van een totale maaltijd.
Tijdens de eerste meeting kennis gemaakt met mijn groepsgenoten Daan en Jeffrey. Hierbij hebben we informatie uitgewisseld over onze apps en over de dingen waar we mee bezig waren. Hieruit kwam naar voren dat, ondanks de totaal verschillende apps, er toch wel de mogelijkheid is voor samenwerking en wederzijdse hulp in kleine dingen.

# day 3 & 4 (JAN 9 & 10 2019)
Afwezig wegens privé-omstandigheden

# day 5 (JAN 11 2019)
Nog een aantal wijzigingen aangebracht aan de layout en opzet van de app en bezig geweest met de API goed werkend te krijgen.
Verder begonnnen in Android Studiio met het maken van de alfa versie.

# day 6 & 7 (JAN 12 & 13 2019)
Verder gegaan met het maken van de alfa versie.

# day 8 (JAN 14 2019)
Een compleet werkende alfaversie gemaakt. Tijdens het mentorgesprek kwam verder naar voren dat het leuk zou zijn om niet alleen op afstand te focussen in de app (en dus Google Maps te gebruiken), maar ook de duur van de activiteiten te monitoren door een timer te implementeren in het laatste scherm. Dit zal ik ook uit gaan zoeken.
Daarnaast de knoop doorgehakt om alleen de belangrijkste nutriënten uit de API te halen ivm relevantie.
Voor morgen ligt de nadruk met name op om de API-calls ook in de app te krijgen, zodat met daadwerkelijke data gewerkt kan worden.

# day 9 (JAN 15 2019)
Begonnen met de Google Maps API werkend te krijgen. Daarnaast ook een timer toegevoegd die aftelt en trilt wanneer de gedane tijd is gelopen. De implementatie van beiden viel me erg tegen en is iets waar ik de rest van het project mee bezig zou kunnen zijn, maar waar niet de focus ligt voor deze app. Voor morgen staat het updaten van de locatie op het programma en eventueel het berekenen van de afstand tussen 2 punten.

# day 10 (JAN 16 2019)
De eerder gemaakte maps-functionaliteit bleek uiteindelijk niet naar behoren te werken. Hierdoor ben ik opnieuw begonnen deze functionaliteit te implementeren. Deze werkt nu en update iedere 5 seconden de locatie en plaatst hiervan een marker en slaat de coordinaten op. De afstand tussen de punten wordt berekend en kan getoond worden, indien de activiteit klaar is.

# day 11 (JAN 17 2019)
Er was al eerder gecheckt of de API werkt, maar ik kreeg deze nog niet goed geïmplementeerd in Android Studio. Dit werkt nu, al moet er nog wel verder geïmplementeerd worden om te kijken naar de precieze verweving in de app. De API returnt nu log prints.

# day 12 (JAN 18 2019)
De GET-request voor het vinden van namen van voedingsmiddelen werkt nu.
DE POST-request voor het terugkrijgen van voedingsinformatie geeft allerlei foutmeldingen. Hier de hele dag aan gezeten.

# day 14 + day 15 (JAN 19 & JAN 20 2019)
Geen voortgang

# day 16 (JAN 21 2019)
Hele dag bezig geweest de API goed geïmplementeerd te krijgen. Dit lukte helaas niet goed. Vervolgens liep de gehele BUILD.GRADLE ook vast, waardoor de hele app niets meer deed.

# day 17 (JAN 22 2019)
Vanwege de aangepast dienstregeling niet de stap genomen om naar Amsterdam te gaan. Zelf de hele dag bezig geweest met debuggen met uiteindelijk als resultaat dat de request werkend is. Vanwege de toch wel groeiende druk besloten de komende dagen ervoor te zorgen dat de verschillende activities zo veel mogelijk goed werkend zijn, voordat er andere activiteiten gedaan worden.
Het plan om de zoekrsultaten in een ListView te zetten bijvoorbeeld staan voorlopig op de lange baan.

# day 18 (JAN 23 2019) 
Met de werkende API alle gehardcode gegevens uit de verschillende schermen verwijderd. Hierdoor werkt de app nu volledig op basis van user input. De Nutritionix API kan calorieën berekenen bij een bepaalde tijd een activiteit, maar kan niet eenvoudig de tijd berekenen voor een bepaalde activiteit. Dit is nu met de hand erin berekend, en zou mogelijk nog geïmplementeerd kunnen worden.

# day 19 (JAN 24 2019)
Geen progress in verband met Hackathon.

# day 20 (JAN 25 2019)
De InputActivity aangepast met listview in plaats van RadioGroup zorgt ervoor dat er op een veel gemakkelijkere manier een keuze gemaakt kan worden qua opties. Verder een aanpassing om ook de serving size te laten zien. Ik heb ervoor gekozen de listView clickable te maken en de NextButton te verwijderen. 
Verder de hele code nagelopen en meerdere TO-DO's toegevoegd.

# day 21 & day 22 (JAN 26 & JAN 27 2019)
Geen progress in het weekend, behalve een korte bespreking met een vriendin van mij om inspiratie op te doen over de layout en UI.

# day 23 (JAN 28 2019)
Enkele bugs verwijderd en de maps API nogmaals herzien, om deze beter te laten werken. De locatiebepaling is vrij onnauwkeurig binnen, waardoor de totaal afgelegde afstand te snel toeneemt. Ik ben bezig hier een oplossing voor te zoeken.
Toevoeging van een begin- en eindscherm aan de app is het meest in het oog springend. Het leek me beter om toch een goed begin en einde aan mijn app te verbinden; dit was namelijk nog geen onderdeel van mijn app en liet het geheel daardoor een beetje onsamenhagend overkomen.
