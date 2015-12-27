#Viikkoraportti 2 (Opiskelija: 014614288)

Viikon 2 raportti :) Pahoittelut pituudesta! :D

###Viikon kuulumiset

Ehdin tehdä yllättävän paljon joulun pyhienkin aikana! 

Kohtasin viikon aikana useita valaistuksia. Aluksi luin tutoriaalista behavior-pohjaisesta ohjelmoinnista, josta inspiroiduin ja lähdin toteuttamaan projektia tämän mallin mukaan. Tein behaviorien hallintaa varten tosi fancyn BehaviorHandler-nimisen luokan, joka käytti PriorityQueuea behaviorien taulukkoon järjestämiseen, joka sitten annetaan Arbitraattorille. RojbOS:n openjdk6(?) ei tukenut PriorityQueuea, ja sen päivittäminen osoittautui ongelmalliseksi, joten päädyin tekemään oman PriorityQueue-luokan. Luokan kanssa tuli hieman ongelmia, koska geneerinen taulukko ei ollut Javan mieleen, eikä StackOverflow'sta löytynyt kikka toiminut, koska openjdk6 ei tukenut Array-luokkaa?! >:( Tein sitten vain Behavioreita järjestävän PriorityQueuen.

Sain tällä tavalla jollakin asteella toimivan viivanseuraajan aikaiseksi, mutta se oli todella kehno ja lopulta totesin, että sujuvan viivanseuraajan toteuttaminen Behaviorien avulla todennäköisesti ei tulisi onnistumaan. Heitin koodin roskiin (kansio old_src repon juuressa) ja lähdin toteuttamaan projektia uudesta näkökulmasta.

Tutustuin minulle vinkattuun PID-kontrolleriin enemmän ja tajusin, että se on paljon fiksumpi tapa lähteä tätä projektia toteuttamaan, vaikka en sen toimintaa kunnolla ymmärräkään. Aloitin projektin uudelleenkirjoituksen, ja tällä kertaa lähdinkin ensimmäisenä tekemään käyttöliittymää, koska logiikan toteuttaminen on paljon helpompaa, kun robotti osaa itse skannata tarvittavat valoarvot Light Sensorilla ja liikkumisnopeuden/pyörimisnopeuden muutokseen ei tarvitse joka kerta kääntää koodia uudestaan.

Koodasin monia erilaisia käyttöliittymäelementtejä (NumberSelector, MotorSelector, LightValueSelector) sekä tein robotin asetuksista olion, jonka gettereiden ja settereiden avulla asetusten lukeminen ja muokkaaminen eri luokista on helppoa. Sain käyttöliittymän kasaan melko nopeasti (tai no, koodasin todella innokkaasti), ja seuraavaksi onkin vuorossa sitten itse viivanseuraajan toteutus.

Viime viikolla määrittelemistäni "ensi viikon suunnitelmista" poikkesin huomattavasti, mutta tämä johtuu siitä, etten ollut ehtinyt tutustua vielä tarpeeksi LeJOS:n materiaaleihin. Esimerkiksi moottoreiden hallintaan oli LeJOS:ssa valmiina todella hyvin tähän käyttötarkoitukseen sopiva luokka, DifferentialPilot. Valosensorin käyttö tuskin tarvitsee omaa luokkaa, koska käytännössä minun tarvitsee vain luoda LightSensor-luokan olio ja lukea valosensorin arvo sen metodilla readValue(). Viivanseuraajalogiikan kanssa työskentelin aluksi, mutta suunnitelmien muuttumisen vuoksi se on vielä uudessa koodissa täysin implementoimatta. 

Muutama kuva projektin tilanteesta!

![Main](https://raw.githubusercontent.com/TheDuckFIN/massive-ironman/master/pictures/main.jpg)
![MotorSelector](https://raw.githubusercontent.com/TheDuckFIN/massive-ironman/master/pictures/motorselector.jpg)
![NumberSelector](https://raw.githubusercontent.com/TheDuckFIN/massive-ironman/master/pictures/numberselector.jpg)
![LightValueSelector](https://raw.githubusercontent.com/TheDuckFIN/massive-ironman/master/pictures/lightvalueselector.jpg)
![RoboV1](https://raw.githubusercontent.com/TheDuckFIN/massive-ironman/master/pictures/robotv1.jpg)

###Ensi viikon suunnitelmat

Käyttöliittymään liittyen teen vielä mahdollisesti virheentarkastuksen asetuksiin (esim. heittää virheilmoituksen, jos valitsee vasemman sekä oikean moottorin samaksi). Lähden tutkimaan lisää PID-kontrolleria ja pyrin viikon aikana toteuttamaan toimivan viivanseuraajalogiikan. Jos saan peruslogiikan toimimaan ongelmitta, alan mahdollisesti toteuttaa jotain suunnitelmassa mainitsemiani lisäominaisuuksia.
