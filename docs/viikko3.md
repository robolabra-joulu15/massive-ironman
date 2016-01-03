#Viikkoraportti 3 (Opiskelija: 014614288)
Viikon 3 raporttia :)

###Viikon kuulumiset
Olen viikon aikana edistynyt jonkin verran :) Yhtä tehokas viikko ei ollut kuin jouluviikko muiden kiireiden vuoksi, mutta projekti on silti hyvällä mallilla. Viikon aikana toteutin PID-kontrollerin logiikan löytämäni oppaan avulla (http://www.inpharmix.com/jps/PID_Controller_For_Lego_Mindstorms_Robots.html). Ymmärsin oppaan avulla P-osan mielestäni hyvin, mutta I ja D osat jäivät hieman hämärän peittoon. 

Tässä video nykytilanteesta:
https://www.youtube.com/watch?v=YamIPa5vLpc

Videolla robotti käyttää asetuksia MoveSpeed=40, kp=4.2, ki=0.0008 ja kd=3.0. Asetusten keksiminen osoittautui hieman haasteelliseksi, ja en ole saanut tähän mennessä robottia menemään yhtään kovempaa. Se on myös todella virhealtis, ja jos radassa olisi yhtään jyrkempiä mutkia, se todennäköisesti suistuisi viivalta ja alkaisi pyöriä ympyrää. Robotti ei myöskään selvinnyt kahdeksikon muotoisesta radasta, eli viivojen risteämiset ovat todennäköisesti ongelma nykyisellä toteutuksella.

Muokkasin myös tällä viikolla hieman UI:ta: poistin rotation speedin turhana ja lisäsin valikon PID-kontrollerin säädöille. Lisäksi tein Error-käyttöliittymäkomponentin ja logiikkaosioon olion, joka tarkistaa onko kaikki arvot kuten pitää (moottorit eivät saa olla samat, movement speed tulee olla väliltä 1-100). Alla muutama kuva.

Ohjelmointiympäristön puutteet tulivat jälleen vastaan tällä viikolla, kun String.format(), String.split() sekä DecimalFormat-luokka eivät kuuluneet 
