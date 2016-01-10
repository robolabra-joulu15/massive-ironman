Ihmisen automaattinen tunnistaminen ja seuraaminen AR Drone 2.0 -nelikopterilla


Projektin päämääränä oli ohjelmoida AR Drone 2.0 -mallinen nelikopteri tunnistamaan ihminen kasvojen perusteella ja seurata tätä testaustilan halki. Vaikka päämäärää ei kokonaisuudessaan saavutettu, ratkaisussa tehtiin tärkeitä edistysaskeleita.

Nykyisellään nelikopteri kykenee tunnistamaan ihmisen näkökentässään, kääntymään tätä kohti ja lähestymään tätä pysytellen (toivottavasti) turvallisen etäisyyden päässä. Ohjelma kuuntelee nelikopterin kameran lähettämää syötettä ja reagoi lentoa ohjaavilla käskyillä. Kopterin ja ohjelmaa pyörittävän tietokoneen välillä on julkinen WiFi-yhteys.

Kirjoitin koodin Python-kielellä, hyödyntäen Pythonin omien kirjastojen lisäksi AR Dronen hallintaan tarkoitettua PS-Drone -kirjastoa (http://www.playsheep.de/drone/index.html) sekä avoimia konenäkö- ja kuvanmuokkausalgoritmeja tarjoavan openCV:n Python-sidontoja.


Ohjelman rakenne:

Ohjelma rakentuu neljän luokan varaan. Nämä luokat ovat Quadcopter, cameraImageGetter, HOG_people_detector ja droneController.

Näistä Quadcopter on yksinkertaisesti wrapper-luokka ps_dronen tarjoamille, kopterin liikkeitä ohjaaville funktioille. Sen tarkoituksena on helpottaa laitteiston vaihtamista tulevaisuudessa (esimerkiksi jos haluan käyttää samaa ohjelmaa toisen drone-mallin kanssa, minun tarvitsee vain luoda sen ohjaamiseen sopiva wrapper-luokka sen sijaan että joutuisin muokkaamaan itse ohjelmaa.)

Luokka cameraImageGetter ottaa vastaan kamerakuvaa erillisessä säikeessä, mikä vähentää kuvan viivettä. Alun perin luokan tarkoituksena oli korjata ongelma, jossa videokuvan puskuri oli niin suuri, että kuva oli useita sekunteja jäljessä. Kun videokuva haetaan erillisessä säikeessä tuomatta sitä heti näytölle, prosessointi on paljon nopeampaa ja puskuri jää näin huomattavasti tyhjemmäksi, eikä viivettä esiinny. 

Tämän lisäksi cameraImageGetter korjaa bugin, jossa cv2.VideoCapture() ei aina palautakaan toimivaa kamerasyötettä, yrittämällä automaattisesti ottaa yhteyden uudelleen.

HOG_people_detector on nimensä mukaisesti ihmisen tunnistamiseen erikoistunut luokka. Kun se initialisoidaan, sille tulee antaa parametri maxframecount, joka määrittää, miten monen framen välein ihmiset tulisi tunnistaa. Siis HOG_people_detector(0) palauttaa tunnistimen, joka yrittää löytää ihmiset jokaisesta sille syötetystä kuvasta, HOG_people_detector(10) taas tunnistimen, joka etsii ihmiset joka kymmenennestä framesta. Tämän toiminnallisuuden tarkoituksena on nopeuttaa ihmisten tunnistamista sen verran, että kamerakuvaa voidaan tarkkailla reaaliajassa ilman liiallista viivettä.

Muutoin HOG_people_detector on käytännössä wrapperi opencv:n tarjoamalle ihmistunnistimelle, joka perustuu tällä alalla suosittuun HOG (Histogram of Oriented Gradients) -tekniikkaan. Lähinnä muokkasin winStride ja scale -parametreja parantaakseni tunnistustarkkuutta.

Kopteria ohjaava äly sisältyy droneController -luokkaan, joka ottaa initialisoitaessa Quadcopter -luokan (tai muun vastaavan) instanssin, jota se alkaa ohjaamaan erillisessä säikeessä pääohjelmasta tulevien tietojen perusteella. Jostain syystä toteutin luokan muuttujille setterit, vaikka tämä ei Pythonissa ole välttämätöntä, koska kaikki muuttujat ovat julkisia. Joka tapauksessa pääohjelma päivittää kontrollerin muuttujia deviation, humansDetected ja continueFlying reaaliajassa, ja droneController tarkistaa näiden muuttujien tilan ja lähettää niiden perusteella uuden käskyn nelikopterille yhden sekunnin välein. Näin vältetään ongelma, jossa ohjelmä yrittää lähettää lennokille käskyjä liian nopeaan tahtiin. Lisäksi kontrolleri on helppo laittaa pois päältä (kommentoimalla droneController.start() -käsky), jos halutaan testata vain kameraa.

Pääohjelman kulku on seuraava: tarvittavien importtien jälkeen aloitetaan luomalla Quadcopter-instanssi, drone, ja tulostetaan funktion drone.getBattery avulla nelikopterin paristotaso. Tämän jälkeen luodaan instanssit luokista HOG_people_detector ja cameraImageGetter, ja aletaan kaappamaan kamerakuvaa. Kamerakuva tuodaan muokkaamattomana ohjelmaa ajavan tietokoneen ruudulle. Tässä vaiheessa käyttäjän tulee odottaa, että kamerakuva muuttuu suunnilleen reaaliaikaiseksi (aluksi kuvassa on useamman sekunnin viive; en ole saanut tätä ongelmaa vielä korjattua.) Tämän jälkeen käyttäjä painaa s-näppäintä, jolloin ohjelma lataa kameran kalibrointiin tarvittavat parametrit tiedostosta calibrationconfiguration. Sitten luodaan instanssi droneControllerista ja laitetaan kopteri lentoon. Seuraavassa while-luupissa  kamerakuvaan lisätään suorakulmio rajaamaan HOG_people_detectorin havaitsemaa lähintä ihmisiä ja tulostetaan kuva ruudulle. Samalla päivitetään droneControllerin liikkeeseen vaikuttavat muuttujat, kuten lähimmän ihmisen etäisyys näkökentän keskipisteestä. Silmukan lopussa kuunnellaan näppäimistöä; kun käyttäjä painaa q-näppäintä, while-silmukka päättyy. Tämän jälkeen kuvankaappaus lopetetaan, laite laskeutuu maahan ja ohjelman suoritus loppuu.

Ohjelman mukana on kaksi kansiota, johon keräsin pari muuta tehtävää varten kirjoittamaani ohjelmaa. Kansiossa Calibration on ohjelma, jota käytin AR Dronen kameran kalibroimiseen. Se tarvitsee vain ajaa ja sen jälkeen esitellä dronen kameralle shakkilautakuviota siihen saakka, että se on kerännyt riittävästi dataa (sen pitäisi havaita kuvio n. 10 kertaa), jonka jälkeen konfiguraatioparametrit tallentuvat tiedostoon calibrationconfiguration.

Toisessa kansiossa, FaceDetector, on luokka, joka löytää kasvot annetusta kuvasta Viola-Jones -tekniikalla. Jos olisin toteuttanut kasvojentunnistus-toiminnallisuuden, tämä olisi tietysti ollut välttämätön lähtökohta.

Käyttöohjeet:


Kiinnitä AR Dronen akku ja aseta se sopivalle alustalle riittävän suuressa tilassa. Ota siihen Wi-Fi -yhteys. 

Aja ohjelma käskyllä "python ps_drone_people_flying2.py" ja odottele. Jos ohjelma joutuu silmukkaan, jossa ruudulle tulostuu jatkuvasti "Capturing...", sulje komentorivi, ota uudelleen wi-fi -yhteys ja koeta uudestaan. 

Tämän jälkeen ruudulla tulisi näkyä dronen kaappaamaa kamerakuvaa. Odota, että kuva on suunnilleen reaaliaikaista (voit yrittää liikkua kameran edessä). Paina lopuksi 's'-näppäintä, jolloin dronen pitäisi lähteä lentoon.

Voit seurata laitteen näkemää kuvaa ruudulta. Lähimmän ihmisen ympärillä pitäisi olla punainen suorakulmio. Jos näin ei käy, tämän tulee yrittää vaihtaa asentoa.

Kun ohjelma halutaan sammuttaa, paina 'q'-näppäintä. Huomaa, että sammumisessa on parin sekunnin viive, joten vaaratilanteessa on toimittava äkkiä.

Jos haluat kokeilla kasvojentunnistinta esimerkkikuvan avulla, aja Pythonissa ohjelma facedetection_example.py. Kokeillaksesi kameran kalibrointia käynnistä AR Drone ja ota siihen Wi-Fi -yhteys, ja aja tämän jälkeen ohjelma ps_drone_calibrate.py. Kalibrointia varten sinulla tulee olla tulostettuna mustavalkoinen shakkilautakuvio, esimerkiksi sellainen kuin tiedostossa "OpenCV_Chessboard.png".

Oma loppuarvio:


Olin iloinen päästessäni tutkimaan ja toteuttamaan konenäköalgoritmeja sekä kokeilemaan nelikopterin lennättämista ja ohjelmointia. Automaattinen ohjaus oli odotettua hankalampaa, ja kopteri oli usein vaarassa törmätä; onneksi suoja esti pahemmat haaverit. Toisaalta ihmisen tunnistaminen openCV:n avulla oli odotettua paljon helpompaa, ja Pythonilla koodaaminen oli yhtä miellyttävää kuin aina. Olisin mielelläni vielä toteuttanut kasvojentunnistus -järjestelmän ja parantanut ohjaus-järjestelmää, mutta olen kaiken kaikkiaan tyytyväinen siihen, miten projekti sujui joulun ja uuden vuoden kiireistä huolimatta.

Kiitokset kurssista!
