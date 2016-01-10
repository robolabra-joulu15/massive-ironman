# Viivanseuraaja - Loppuraportti

Toteutin kurssilla robotin, joka osaa seurata mustasta viivasta tehtyä rataa. Robotti saavuttaa parhaan tuloksen, kun alusta on mahdollisimman vaalea. Myös hyvä valaistus parantaa robotin suorituskykyä. Itse testasin robottia huoneeni parkettilattiaan mustalla teipillä tehdyllä radalla, ja valaistuksena toimi ihan normaali kattolamppu. Robotti toimi moitteettomasti myös tässä ympäristössä. Robotti hyödyntää toiminnassaan PID-kontrolleria, jonka avulla pystytään toteuttamaan viivan seuraaminen mahdollisimman sulavasti. 

Robotissa on myös NXT-brickin näytölle toteutettu käyttöliittymä, josta pystyy säätämään robotin arvoja mieleisikseen. Tämä mahdollistaa sen, että robottia on mahdollista ajaa lukuisilla eri asetuksilla helposti ilman, että koodia tarvitsee kääntää uudestaan, mikä lisää joustavuutta huomattavasti. 

Säädettävät arvot:
* Moottorien portit
* Taustan ja viivan valoarvot
* Liikkumisnopeus
* PID-kontrollerin vakiot (kp, ki ja kd)

Liikkumisnopeutta ja PID-kontrollerin vakioita säätämällä robotin saa esimerkiksi menemään todella kovaa jos rata on loiva, tai jos rata sisältää todella jyrkkiä mutkia, voi robotin säätää menemään hiljaa mutta tarkasti.

## Robotin rakenne ja rakennusohje

Robotin toiminnalliset osat:
* Kaksi moottoria
    - Vasen ja oikea, oletuksena vasen portissa A ja oikea portissa B, nämä kuitenkin säädettävissä robotin asetuksista.
* Valosensori portissa 1, ei säädettävissä.

Robottia kasatessani tajusin kuinka huono olenkaan leikkimään legoilla. Jouduin siis hetken palikoiden kanssa tuskailtuani etsimään inspiraatiota internetistä, ja päädyin toteuttamaan robotin fyysistä rakennetta [tämän](http://www.nxtprograms.com/castor_bot/index.html) ohjeen pohjalta. Ohjeen avulla toteutettu rakenne osoittautui todella sopivaksi viivanseuraajalle, sillä se on vankka ja kääntyy todella helposti ostoskärryn pyöriä muistuttavan takapyöränsä ansiosta.

Viivanseuraajaa varten tarvitsin vielä robotin etuosaan valosensorin, ja kehittelin sille itse kiinnityksen alla olevan kuvan mukaisesti. Valosensorin etäisyys lattiasta on pyritty minimoimaan, ja kiinnitysratkaisuni sallii etäisyyden säätämisen tarvittaessa. Kuvassa etäisyys on säädetty n. 3mm lattiasta.

![](https://raw.githubusercontent.com/TheDuckFIN/massive-ironman/master/pictures/robotv2.jpg)
![](https://raw.githubusercontent.com/TheDuckFIN/massive-ironman/master/pictures/robotv2_2.jpg)
![](https://raw.githubusercontent.com/TheDuckFIN/massive-ironman/master/pictures/valosensori.jpg)

## Koodin rakenne

Koodin pakkausrakenne on seuraava:

* **logic**
    - `LineFollower.java`
    - `PIDController.java`
    - `ValueChecker.java`
* **main**
    - `Main.java`
* **ui**
    - **components**
        + `Error.java`
        + `LightValueSelector.java`
        + `MotorSelector.java`
        + `NumberSelector.java`
        + `Pointer.java`
    - `ConfiguratorUI.java`
    - `PIDConfiguratorUI.java`
* **util**
    - `CharToMotorPortTools.java`
    - `Configuration.java`
    - `NumberArrayTools.java`
    - `StringTools.java`

Logic-pakkaus sisältää robotin toimintaan liittyvät luokat, kuten PID-kontrollerin, joka hoitaa viivan seuraamisen. Main-pakkaus sisältää vain yhden luokan, pääohjelman, jonka ainut toiminnallisuus on luoda uusi LineFollower-olio ja käynnistää se. Ui-pakkaus sisältää robotin LCD-näytön käyttöliittymään liittyvät luokat. Ui-pakkaus sisältää alipakkauksen components, joka sisältää yksinkertaiset "käyttöliittymäkomponentit". Näitä komponentteja hyödynnetään muun muassa itse ui-paketin asetusvalikkoluokissa `ConfiguratorUI.java` sekä `PIDConfiguratorUI.java`. `Error.java`-luokan kanssa olin kahden vaiheilla tulisiko se laskea komponentiksi vai kokonaiseksi valikoksi, mutta päädyin lopulta tulkitsemaan sen komponentiksi. Util-pakkaus sisältää muissa luokissa hyödynnettyjä aputyökaluja sekä konfiguraatioluokan.

Ohjelman suoritus lähtee liikkeelle luokasta `Main.java`, joka luo uuden luokan `LineFollower.java` ilmentymän ja kutsuu sen metodia `start()`. `LineFollower.java`-luokka luo konstruktorissaan seuraavat ohjelman keskeiset oliot:

* **`Configuration.java`-luokan olio**: `Configuration.java`-luokka toimii säilönä koko sovelluksen asetuksille. Luokan konstruktoriin on kovakoodattu ohjelman oletusasetukset, ja kaikkia näitä asetuksia voi muokata luokan sisältämillä gettereillä ja settereillä. Ideana on, että luokasta luodaan yksi ainoa instanssi (tässä tapauksessa sen luo `LineFollower.java`), joka annetaan parametrina kaikille luokille, jotka haluavat päästä lukemaan tai muokkaamaan asetuksia.
* **leJOS `LightSensor`-luokan olio**: LightSensoria käytetään monessa paikkaa ympäri koodia. `LineFollower.java` luo tämän luokan olion, joka annetaan parametrina niille luokille, jotka sitä tarvitsevat.
* **`ConfiguratorUI.java`-luokan olio**: "Pääasetusvalikon" olio, jolle annetaan parametriksi aiemmin luodut `Configuration.java`-luokan olio sekä LightSensor. 
* **`PIDController.java`-luokan olio**: `LineFollower.java` luo myös itse viivanseurauslogiikan hoitavan PID-kontrolleriluokan, joka saa parametrikseen sekä `Configuration.java`-luokan olion että LightSensorin.
* **`ValueChecker.java`-luokan olio**: Saa parametrikseen `Configuration.java`-luokan olion. `ValueChecker.java`-luokan vastuulla on tarkistaa, että kaikki käyttäjän syöttämät konfiguraatioarvot ovat valideja.

`LineFollower.java`-luokan `start()`-metodi toimii ohjelman "selkärankana". Se sisältää while-loopin, jossa ensin kutsutaan konstruktorissa `ConfiguratorUI.java`-luokasta luodun olion metodia `start()`, joka palauttaa joko `true` (käyttäjä valinnut kohdan "START!") tai `false` (käyttäjä painanut escape-näppäintä). Jos palautuu false, murtaudutaan ulos while-loopista ja tällöin `LineFollower.java`:n `start()`-metodi loppuu, ja suoritus palautuu pääohjelmaan, jolloin robotti käytännössä sammuu. Jos palautuu true, pääsee ohjelma jatkamaan kohtaan, jossa `LineFollower.java` kutsuu `ValueChecker.java`-luokasta luodun olion metodia `check()`, joka palauttaa `false`, jos joku konfiguraatioarvoista ei ole validi, jolloin päädytään uudestaan while-loopin alkuun. Jos taas arvot ovat valideja, `LineFollower.java` kutsuu `PIDController.java`-luokasta luodun olion `start()`-metodia, jolloin viivanseuraaja käynnistyy ja robotti lähtee liikkeelle.

`ConfiguratorUI.java`-luokka käyttää avukseen **components**-paketin luokkia valikon toteutuksessa. `ConfiguratorUI.java`-luokan yksi valinta luo uuden `PIDConfiguratorUI.java`-luokan olion ja kutsuu sen `start()`-metodia, jolloin aukeaa PID-asetusvalikko. Kyseinen `start()`-metodi palauttaa `false` jos käyttäjä valitsee "BACK"-vaihtoehdon tai painaa escape-näppäintä. `True` palautetaan, jos käyttäjä valitsee "START!", jolloin myös `ConfiguratorUI.java`-luokan olio josta kutsuttiin PID-asetusvalikkoa palauttaa `true`, jolloin palataan `LineFollower.java`:an.

`ConfiguratorUI.java` ja `PIDConfiguratorUI.java` muistuttavat toiminnallisuudeltaan toisiaan hyvin paljon, ja niille olisi voinut toteuttaa jonkinlaisen interfacen/yläluokan. Päädyin kuitenkin pitämään ne erillään ajanpuutteen vuoksi, ja interfacen merkitys ei kuitenkaan olisi tässä tapauksessa ollut kovin suuri, koska se ei oikeastaan olisi luonut koodiin yhtään enempää joustavuutta. Jonkun geneerisemmän menusysteemin olisin todennäköisesti toteuttanut, jos aikaa olisi ollut tarpeeksi.

`PIDController.java`-luokka toteuttaa liikkumisen toimittamalla PID-kontrolleriin liittyviä laskutoimituksia metodissa `start()`, ja laskutoimitusten avulla saa tietoonsa sekä vasemman että oikean moottorin halutut nopeudet, jotka se asettaa `NXTMotor`-luokan metodin `setPower()` avulla. Käytin [tätä](http://www.inpharmix.com/jps/PID_Controller_For_Lego_Mindstorms_Robots.html) opasta hyödykseni PID-kontrolleria toteuttaessani.

## Testaus

Robotin koodia olisi käytännössä ollut vaikea yksikkötestata, joten päätin keskittyä testeissäni käytännön testaamiseen. Koska robotti on säädettävissä, ilmoitan kunkin testitapauksen yhteydessä robotin nopeuden, sekä PID-kontrollerin arvot sillä ne vaikuttavat robotin suoriutumiseen.

**Testi 1:**
![](https://raw.githubusercontent.com/TheDuckFIN/massive-ironman/master/pictures/rata.jpg)

_Variaatio 1:_
Nopeus: 20, KP: 1.2, KI: 0.0008, KD: 5.0

Robotti suoriutui testistä moitteettomasti, sillä tämä moodi on hidas ja tarkka (ja myös oletusasetukset).

_Variaatio 2:_
Nopeus: 40, KP: 3.8, KI: 0.0008, KD: 5.0

Robotti suoriutui yhtä hyvin kuin variaatiosta 1, mutta nopeammin. [Video testistä.](https://www.youtube.com/watch?v=YamIPa5vLpc)

_Variaatio 3:_
Testasin nopeudella 60, mutta en löytänyt mitään PID-kontrollerin arvoja, joilla robotti ei olisi suistunut radalta ennemmin tai myöhemmin.

**Testi 2:**
![](https://raw.githubusercontent.com/TheDuckFIN/massive-ironman/master/pictures/risteys.jpg)

_Variaatio 1:_ Nopeus: 20, KP: 1.2, KI: 0.0008, KD: 5.0

Robotti nytkähtää risteyksen kohdalla hieman, mutta selviää siitä kuitenkin hyvin. Vaikka robottia ei ole erikseen ohjelmoitu risteystilanteisiin, vaikuttaisi se silti pärjäävän hyvin kohtisuorissa risteyksissä.

_Variaatio 2:_
Nopeus: 40, KP: 3.8, KI: 0.0008, KD: 5.0

Robotti suoriutui yhtä hyvin kuin variaatiosta 1, mutta nopeammin. [Video testistä.](https://www.youtube.com/watch?v=55bDc3rohFQ)

_Variaatio 3:_
Testasin nopeudella 60, mutta en löytänyt mitään PID-kontrollerin arvoja, joilla robotti ei olisi suistunut radalta ennemmin tai myöhemmin.

**Testi 3:**
![](https://raw.githubusercontent.com/TheDuckFIN/massive-ironman/master/pictures/hitto.jpg)
Tässä testissä oli tarkoitus testata muita kuin 90-asteen risteyksiä, mutta teippi loppui kesken :( Olen kerran aikaisemmin testannut tälläisellä, mutta siinä oli jyrkemmät mutkat. Tällöin robotti suistui radalta. Ennustukseni on, että jos risteyksen mutka on tarpeeksi loiva, robotti saattaa risteyksessä lähteä väärään suuntaan. Mutta testin toteuttaminen epäonnistui valitettavasti.

**Testi 4:**
![](https://raw.githubusercontent.com/TheDuckFIN/massive-ironman/master/pictures/jyrkat.jpg)
Tässä testissä testasin jyrkkiä mutkia.

_Variaatio 1:_ Nopeus: 20, KP: 1.2, KI: 0.0008, KD: 5.0

Mutka 1: Robotti selvisi mutkasta helposti
Mutka 2: Robotti suistui radalta
Mutka 3: Robotti suistui radalta mutkan loppupäässä

_Variaatio 2:_ Nopeus: 40, KP: 3.8, KI: 0.0008, KD: 5.0

Samat tulokset kuin variaatiossa 1.

Vaikuttaisi siis siltä, että robotti on virhealtis liian tiukissa mutkissa. Minulta tosin loppui lattiatila, ja valaistus testinurkassa oli todella huono, joten paremmissa olosuhteissa uskoisin robottini selviävän mutkasta 3. Mutka 2 kuitenkin on uskoakseni robotilleni liikaa. Lisäksi asetuksia säätämällä robotin saa todennäköisesti soveltumaan tiukkoihinkin mutkiin, mutta silloin myös ääritapauksissa sulavuus kärsii (esim. robotti ei osaisi mennä enää suoraan vaan vaappuisi edes takas). 

# Puutteet ja mahdolliset parannukset

Jos olisi ollut aikaa, olisin voinut hioa PID-kontrolleria hieman enemmän. Nyt se on hieman virhealtis tiukoissa mutkissa. Lisäksi olisi voinut parantaa viivanseuraajalogiikan kykyä selvitä risteyksistä, sillä jos risteys ei olisi 90-asteinen, voi robotti mahdollisesti eksyä radalta (testi 3). 

Asetusvalikoiden toteutukseen olisi voinut myös tehdä geneerisemmän systeemin erillisten luokkien sijaan, kuten ylempänä koodin rakenne -osiossa mainitsin, mutta aika ei riittänyt tämän toteutukseen.

Robotti olisi mahdollisesti voinut kerätä jotain dataa viivaa seuratessaan, ja tulostaa sitä näytölle tai logiin. Lisäksi eri konfiguraatiokombinaatiot olisi voinut tallentaa pysyviksi "profiileiksi". Ajanpuutteen vuoksi nämä, ja moni muu suunnitelmassa mainituista ominaisuuksista jäi toteuttamatta, mutta robotin perustoiminnallisuuteen olen kyllä tyytyväinen, ja sain sen sellaiseen kuntoon kuin halusin.

# Käyttöohje

Kasaa robotti ohjeiden mukaan ja tee sille rata. Tämän jälkeen voit siirtää koodin robottiin seuraavasti:

1. Pystytä rojbOS [tämän](https://github.com/robolabra-joulu15/massive-ironman/wiki/1.-ymp%C3%A4rist%C3%B6) ohjeen perusteella. Alla olevat kohdat neuvovat miten tässä ympäristössä siirrät koodin robottiin. Muissa kehitysympäristöissä saattavat kohdat hieman vaihdella.
2. Kloonaa tämä repositorio ja avaa projekti eclipsessä.
3. Kytke robotti USB-kaapelilla kiinni koneeseen
4. Paina oikealla hiiren napilla build.xml-tiedostoa ja valitse Run as -> Ant build
5. Ohjelma avautuu robotissa
6. Irroita USB-kaapeli

Nyt olet valmis käyttämään robottia! Säädä eka asetukset! Edessäsi on seuraavanlainen näkymä: 

![](https://raw.githubusercontent.com/TheDuckFIN/massive-ironman/master/pictures/main_new.jpg)

Liiku valikossa nuolten avulla (vasen ylös, oikea alas), enter eli iso nappi keskellä valitsee ja escape eli pieni nappi alhaalla palaa takaisin eli tässä tapauksessa sammuttaa robotin.

Valitsemalla kohdan "left motor" tai "right motor" pääset valitsemaan moottoriportit samoilla kontrolleilla kuin päävalikossa:

![](https://raw.githubusercontent.com/TheDuckFIN/massive-ironman/master/pictures/motorselector.jpg)

Valitsemalla päävalikosta kohdan "line color" tai "bg color" päädyt seuraavanlaiseen valitsimeen: 

![](https://raw.githubusercontent.com/TheDuckFIN/massive-ironman/master/pictures/lightvalueselector.jpg)

Aseta robotin valosensori joko viivan (line) tai taustan (bg) päälle ja paina enteriä, jolloin robotti tunnistaa valoarvon.

Valitsemalla päävalikosta move speed -kohdan päädyt numerovalitsimeen:

![](https://raw.githubusercontent.com/TheDuckFIN/massive-ironman/master/pictures/numberselector.jpg)

Oikea/vasen nuoli liikkuu osoittamaansa suuntaan, enter kasvattaa arvoa (9 hyppää nollaan) ja escape-nappi palaa takaisin valikkoon.

Valitsemalla CONFIGURE PID -kohdan päädyt PID-asetusvalikkoon:

![](https://raw.githubusercontent.com/TheDuckFIN/massive-ironman/master/pictures/pidconfig.jpg)

Tästä valikosta voit säätää PID-kontrollerin vakioiden arvoja. Painamalla escape-nappia, tai menemällä BACK-kohtaan ja painamalla enteriä pääset takaisin päävalikkoon. Jos päätät muokata jotain arvoa, päädyt seuraavan näköiseen valitsimeen: 

![](https://raw.githubusercontent.com/TheDuckFIN/massive-ironman/master/pictures/numberselector_double.jpg)

Täysin samanlainen kuin aikaisempi numerovalitsin, erona vain piste erottamassa kokonaisosan desimaaliosasta.

Valitsemalla START! jommastakummasta valikosta robotti lähtee käyntiin. Robotin tulee olla viivan päällä jotta se osaa lähteä sitä seuraamaan. Jos asetuksissa meni jotain todella pieleen, robotti osaa ilmoittaa siitä:

![](https://raw.githubusercontent.com/TheDuckFIN/massive-ironman/master/pictures/error.jpg)

Siinäpä se! Ei muutakun viivoja seurailemaan :D