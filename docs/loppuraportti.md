# Viivanseuraaja - Loppuraportti

Toteutin kurssilla robotin, joka osaa seurata mustasta viivasta tehtyä rataa. Robotti saavuttaa parhaan tuloksen, kun alusta on mahdollisimman vaalea. Myös hyvä valaistus parantaa robotin suorituskykyä. Itse testasin robottia huoneeni parkettilattiaan mustalla teipillä tehdyllä radalla, ja valaistuksena toimi ihan normaali kattolamppu. Robotti toimi moitteettomasti myös tässä ympäristössä. Robotti hyödyntää toiminnassaan PID-kontrolleria, jonka avulla pystytään toteuttamaan viivan seuraaminen mahdollisimman sulavasti. 

Robotissa on myös NXT-brickin näytölle toteutettu käyttöliittymä, josta pystyy säätämään robotin arvoja mieleisikseen. Tämä mahdollistaa sen, että robottia on mahdollista ajaa lukuisilla eri asetuksilla helposti ilman, että koodia tarvitsee kääntää uudestaan, mikä lisää joustavuutta huomattavasti. 

Säädettävät arvot:
* Moottorien portit
* Taustan ja viivan valoarvot
* Liikkumisnopeus
* PID-kontrollerin vakiot (kp, ki ja kd)

Liikkumisnopeutta ja PID-kontrollerin vakioita säätämällä robotin saa esimerkiksi menemään todella kovaa jos rata on loiva, tai jos rata sisältää todella jyrkkiä mutkia, voi robotin säätää menemään hiljaa mutta tarkasti.

## Robotin rakenne

Robotin toiminnalliset osat:
* Kaksi moottoria
    - Vasen ja oikea, oletuksena vasen portissa A ja oikea portissa B, nämä kuitenkin säädettävissä robotin asetuksista.
* Valosensori

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

* **`Configurator.java`-luokan olio**: `Configurator.java`-luokka toimii säilönä koko sovelluksen asetuksille. Luokan konstruktoriin on kovakoodattu ohjelman oletusasetukset, ja kaikkia näitä voi muokata luokan sisältämillä gettereillä ja settereillä. Ideana on, että luokasta luodaan yksi ainoa instanssi (tässä tapauksessa sen luo `LineFollower.java`), joka annetaan parametrina kaikille luokille, jotka haluavat päästä lukemaan tai muokkaamaan asetuksia.
* **leJOS `LightSensor`-luokan olio**: LightSensoria käytetään monessa paikkaa ympäri koodia. `LineFollower.java` luo tämän luokan olion, joka annetaan parametrina niille luokille, jotka sitä tarvitsevat.
* **`ConfiguratorUI.java`-luokan olio**: `LineFollower.java` luo pääasetusluokan olion, ja antaa sille parametriksi aiemmin luomansa `Configurator.java`-luokan olion sekä LightSensorin.  