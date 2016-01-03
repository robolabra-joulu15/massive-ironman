#Viikkoraportti 3 (Opiskelija: 014614288)
Viikon 3 raporttia :)

###Viikon kuulumiset
Olen viikon aikana edistynyt jonkin verran :) Yhtä tehokas viikko ei ollut kuin jouluviikko muiden kiireiden vuoksi, mutta projekti on silti hyvällä mallilla. Viikon aikana toteutin PID-kontrollerin logiikan löytämäni oppaan avulla (http://www.inpharmix.com/jps/PID_Controller_For_Lego_Mindstorms_Robots.html). Ymmärsin oppaan avulla P-osan mielestäni hyvin, mutta I ja D osat jäivät hieman hämärän peittoon. 

Tässä video nykytilanteesta:
https://www.youtube.com/watch?v=YamIPa5vLpc

Videolla robotti käyttää asetuksia MoveSpeed=40, kp=4.2, ki=0.0008 ja kd=3.0. Asetusten keksiminen osoittautui hieman haasteelliseksi, ja en ole saanut tähän mennessä robottia menemään yhtään kovempaa. Se on myös todella virhealtis, ja jos radassa olisi yhtään jyrkempiä mutkia, se todennäköisesti suistuisi viivalta ja alkaisi pyöriä ympyrää. Robotti ei myöskään selvinnyt kahdeksikon muotoisesta radasta, eli viivojen risteämiset ovat todennäköisesti ongelma nykyisellä toteutuksella.

Muokkasin myös tällä viikolla hieman UI:ta: poistin rotation speedin turhana ja lisäsin valikon PID-kontrollerin säädöille. Lisäksi tein Error-käyttöliittymäkomponentin ja logiikkaosioon olion, joka tarkistaa onko kaikki arvot kuten pitää (moottorit eivät saa olla samat, movement speed tulee olla väliltä 1-100). Alla muutama kuva.

![Main_New](https://raw.githubusercontent.com/TheDuckFIN/massive-ironman/master/pictures/main_new.jpg)
![NumberSelector](https://raw.githubusercontent.com/TheDuckFIN/massive-ironman/master/pictures/numberselector_double.jpg)
![PidConfig](https://raw.githubusercontent.com/TheDuckFIN/massive-ironman/master/pictures/pidconfig.jpg)

Ohjelmointiympäristön puutteet tulivat jälleen vastaan tällä viikolla, kun metodeita String.format(), String.split() sekä DecimalFormat-luokkaa ei ollut olemassa. Tämän vuoksi doublen muotoilu haluamallani tavalla ei oikein onnistunut (8.0E-4 -> 0.0008), mutta se on loppujenlopuksi aika pikkujuttu. String.splitin puuttuminen aiheutti sen, että jouduin tekemään rivittäjä-apumetodin String.indexOf:n ja String.substringin avulla.

###Seuraavan viikon suunnitelmat
Luen koodia läpi ja pyrin refaktoroimaan sitä tarpeen tullen. Voisin myös heittää muutamia kommentteja sekaan selkeyden lisäämiseksi. Yritän myös löytää PID-kontrollerille mahdollisimman toimivat arvot suuremmilla nopeuksilla. NumberSelectorissa on myös pieni bugi, joka voi johtua varsin hyvin doublen epätarkkuudesta (esim. 4.2 muuttuu 4.19999:ksi), enkä ole varma voiko sille oikeastaan mitään fiksua tehdä johtuen tavasta, millä numerot muutetaan taulukoksi ja takaisin (util/NumberArrayTools.java). Yritän kuitenkin tutkia mahdollista korjaustapaa. Lisäksi yritän parannella PID-kontrolleria, että se toimisi esimerkiksi risteyksissä. En ole kuitenkaan varma osaanko tätä toteuttaa, sillä PID-kontrollerin saaminen toimimaan sillä tavalla, miten se nyt toimii oli minulle jo iso erävoitto, sillä en ymmärrä tarkalleen edelleenkään esimerkiksi miten vakioiden kp, ki ja kd muuttaminen vaikuttaa toimintaan :D
