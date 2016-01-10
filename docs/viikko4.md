-ROBOTIN TOIMINTA
Robotin tarkoitus on etsiä ultraäänisensorilla jokin sen lähistöllä oleva kappale ja seurata sitä. Jos kappaletta liikuttaa niin robotti jatkaa sen seuraamista. Robotti pyrkii pitämään itsensä 20-25cm päässä kyseisetä esineestä. Jos robotti ei löydä tietyn ajan kuluessa esinettä tai enteriä painetaan, lopettaa robotti toimintansa.

-ONGELMIA
Koska rojbOS päätti lopettaa toimintansa (oletettavasti läppärin kovalevy on kuolemassa), en pääse enää käsiksi alkuperäiseen lähdekoodin, josta johtuen päätin replikoida robotin koodin mahdollisimman tarkkaan muistista pienten korjausten kera. Toiminnallisuutta en ole päässyt testaamaan mutta olettaen että replikoin koodin oikein, niin ainoa ero robottiini asennettuun ohjelmaan pitäisi olla esineen etsimisen toiminnallisuuden parantelu (vanhassa versiossa robotti lopettaa etsimisen liian nopeasti jos esinettä ei löydy). 

-KOKOAMINEN
Robotin kasaaminen on suhteellisen yksinkertaista: neljä rengasta, molemmilla puolilla yksi moottori ja eteenpäin osoittava ultraäänisensori. Vasemman renkaan moottori menee porttiin B, oikean porttiin C ja ultraäänisensori porttiin 1. 

-TESTAUS
Ainakin vanhalla ohjelmistolla sanoisin että robotti toimi "riittävän hyvin", vaikkakin ultraäänisensori tuntui toimivan hieman hassusti pitkillä etäisyksillä sekä tilanteissa joissa sensori saa lukemia esineestä joka on hieman sivulla. Kääntyminen oli hieman heikkoa, mikä johtui robotin rakenteesta (selitetty kohdassa "ajatuksia").

-AJATUKSIA
Vaikeinta kurssissa ei ollut itse robotti vaan teknologian kanssa taisteleminen, robotti oli oikeastaan melko yksinkertainen kasata ja ohjelmoida, koodin pituus on ehkä ~80 riviä + kommentit. RojbOS sen sijaan alkoi toimia kunnolla vasta viikolla 3 ja lopetti toimintansa kokonaan viime torstaina.
Robotin toiminnallisuutta olisi voinut mahdollisesti parantaa seuraavilla tavoilla:
1) Moottorin käyttö ultraäänisensorin kanssa - liikuttamalla sensoria moottorin avulla robotin ei olisi tarvinnut kääntyillä    jatkuvasti haettavaa esinettä etsittäessä.
2) Voimansiirto takapyöriin/takapyörien korvaaminen yhdellä vapaasti ympäri pyörivällä pyörällä - robotin kääntyminen tuntuu    hieman tuskalliselta sillä  takapyöriin ei ole minkäänlaista voimansiirtoa (osat eivät riittäneet) ja renkaiden kitka on        suhteellisen suuri. Demotilaisuudessa muutamalla robotilla oli takapyörät korvattu yhdellä pyörällä joka pääsee vapaasti        pyörimään ympäri ja näillä roboteilla kääntyminen näytti toimivan hyvin.
