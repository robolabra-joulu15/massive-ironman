Robottiohjelmoinnin harjoitustyö, lopullinen palautus - Jarkko Karttunen

KUVAUS

Robotin tarkoitus on etsiä ultraäänisensorilla jokin sen lähistöllä oleva kappale ja seurata sitä. Jos kappaletta liikuttaa niin robotti jatkaa sen seuraamista. Robotti pyrkii pitämään itsensä 20-25cm päässä kyseisetä esineestä. Jos robotti ei löydä tietyn ajan kuluessa esinettä tai enteriä painetaan, lopettaa robotti toimintansa.

ROBOTIN RAKENNE

Rakenteeltaan robotti on suhteellisen yksinkertainen, suunnilleen autoa muistuttava. Robotissa on neljä rengasta, molemmilla puolilla yksi moottori ja eteenpäin osoittava ultraäänisensori. Vasemman renkaan moottori menee porttiin B, oikea porttiin C ja ultraäänisensori porttiin 1.

KOODI

Koodi koostuu yhdestä luokasta ja koodissa on seuraavat metodit:
main(): Kutsuu etsi metodia jos esine ei ole robotin edessä, muuten liikkuu eteen/taakse 	tai pysähtyy.
etsi(): Kääntyy vuorotellen vasemmalle ja oikealle ja kutsuu metodia odota. Jos esinettä ei 	löydy niin keskeyttää ohjelman toiminnan.
odota(int ms): Odottaa parametrina annetun ajan ja jos odotuksen aikana esine tulee 	robotin eteen niin palauttaa arvon true ja muuten false (tarkistaa 20ms välein onko 	esine robotin edessä).

lisäksi on metodit eteen, vasen, oikea, taakse ja luoKuuntelija, joka luo kuuntelijan joka pysäyttää ohjelman kun enteriä painetaan


TESTAUS

Varsinaisia testitapauksia minulla ei ole mutta seuraavat asiat onnistuin toteamaan testausten aikana:
1) Eteen/taakse liikkuvuus toimii hyvin, jos robotti on liian lähellä esinettä niin robotti peruuttaa onnistuneesti, ajaa kohti kaukana olevia esineitä, pysähtyy jos tarpeeksi lähellä jne. Jos esine on hieman sivussa robottiin nähden niin tämä tuntuu aihettavan hieman ongelmia (sensori ei oikein osaa päättää onko esine robotin edessä vai ei).
2) Kääntyminen on hieman heikkoa, tämä johtuu robotin takarenkaiden aiheuttamasta kitkasta ja joku muu ratkaisu tässä tapauksessa olisi ollut parempi.
3) Esimerkiksi kissa on huono kohde robotille, sillä ultraäänisensori ei tuntunut tykkäävän siitä (ja oletettavasti muista tämän kaltaisista esineistä/otuksista), eikä myöskään kissa robotista.
Robotti siis suunnilleen toimii.




RAJOITUKSET JA TULEVAISUUS

Koska robotti havaitsee ensimmäisen esineen joka sen eteen tulee, vaatii se suhteellisen paljon tyhjää tilaa ympärillään jotta se ei lähde liikkumaan esimerkiksi seiniä tai pöytiä kohti.
Suurin ongelma robotin kanssa oli kääntyminen, joka johtui siitä, että osat eivät riittäneet voimansiirron rakentamiseen takarenkaisiin. Parempi ratkaisu olisi ollut korvata takarenkaat yhdellä vapaasti kääntyvällä tukipyörällä.
Ultraäänisensorin voisi myös kääntää moottorilla jolloin koko robotin ei tarvitsisi kääntyä kun esinettä etsitään.

KÄYTTÖOHJE

1. Lataa ohjelma robottiin
2. Käynnistä ohjelma
3. ????
4. Robotti (toivottavasti) etsii jonkin esineen ja alkaa seuraamaan sitä
5. Robotti sammuu jos se ei löydä mitään esinettä tietyn ajan kuluessa tai jos enteriä painetaan.
