Robottiohjelmoinnin harjoitustyö
Suunnitelma
Ville Tenhunen


Tarkoituksenani on tehdä jalkapalloa pelaava robotti. Ensimmäisessä vaiheessa on tarkoitus saada toteutettua robotti, joka pystyy kävelemään kaatumatta ja liikkuu vain suoraan eteenpäin. Kun tämä onnistuu, voi robotti kävellessään siirtää eteenpäin jalkoihin asetettua valkoista paperipalloa, ainakin kunnes (kenties heti), pallo karkaa kulkusuunnasta sivuun. Tässä vaiheessa tarvitaan siis vain jalkojen liikuttamiseen vaadittavat moottorit ja niiden johdonmukainen operointi. 

Tämän jälkeen seuraava vaihe on että pelaaja pystyy myös vaihtamaan kulkusuuntaa ja löytää pallon lähistöltään, menee sen luokse ja alkaa potkia sitä eteenpäin. Nyt pelaaja löytää pallon eikä enää hukkaa sitä potkiessaan. Pallon löytäminen voi kuitenkin olla haastavaa, joten tilannetta ensi vaiheessa pelkistetään niin, että pallo on riittävän lähellä, esimerkiksi alle metrin päässä, ja mitään muita esteitä ei alle metrin päästä löydy. Tällöin etäisyyssensorilla pystytään löytämään jokin este, mikä voidaan nyt tulkita palloksi. Näin ollen pelaamassa ei voi olla muita pelaajia eikä muita esteitä / esineitä. Seuraavassa kehittelyvaiheessa pallo pitäisi vielä erottaa muista esteistä. Tämän voisi yksinkertaisimmillaan toteuttaa oletuksella, että pallo on matalin objekti tilassa, jolloin sen voisi erottaa muista esineistä nostamalla, moottorin avulla, etäisyyssensoria korkeammalle. 

Seuraavassa vaiheessa, jonne asti ei varmaankaan tämän harjoitustyön aikarajan puitteissa tulla kerkiämään olisi pelikentän ja maalin hahmotus ja maalia kohti eteneminen. Pelikentän voisi ohjelmoida koordinaatteina, jolloin maalien koordinaatit ja kentän koordinaatit olisi tiedossa, ja jos koodissa pelaajaolio tietää kulloisenkin suuntansa ja etenemisnopeutensa, voisi se myös tietää kentällä olo koordinaattinsa. Näin ollen robotti osaa suunnistaa myös maalia kohti. 

Nämä osatavoitteet eivät ehkä ratkea kovin helposti, joten voi olla, että näistä suurempikin osa jää jatkokehitysvaiheeseen, eli tämän harjoitustyön ulkopuolelle.
