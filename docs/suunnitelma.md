#Suunnitelma (Opiskelija 014614288)
Aion toteuttaa viivaa seuraavan robotin.

###Perustoteutus
Robotilla on kaksi rengasta, jotka on kytketty moottoreihin. Lisäksi robotin taka-osassa on pieni rengas ilman moottoria,
joka pitää robotin pystyssä. Käytän viivan tunnistamiseen valosensoria, jonka pitäisi pystyä erottamaan musta viiva valkoisesta 
taustasta. Robotti kulkee eteenpäin viivaa seuraten.

###Lisäsuunnitelmia
Lisäsuunnitelmia toteutan sen mukaan mitä ehdin ja miten perustoiminnalisuuden saan toimimaan.

- Robotti mahdollisesti osaa kalibroida itsensä (alussa tutkii mustan viivan ja valkoisen taustan värieron)
- Robotti pyrkii myös kulkemaan mahdollisimman kovaa (ehkä vaihteet, tarkka ja hidas <=> nopea ja hieman hurjapäinen)
- Robotti piippailee söpösti :3
- Esteen kiertäminen (etäisyyssensorin avulla tunnistaa esteen -> osaa kiertää?)
- Suunnan vaihto? Esimerkiksi jos tulee kohtaan jossa viiva loppuu ja viivan päässä on pieni väkänen (esim. T-kirjain)
- Jotain toiminnallisuutta brickiin, mahdollisesti tietoja LCD-näytölle?

###Mahdolliset ongelmatapaukset
- Valosensori ei tunnistakaan viivaa? -> tarvitsee paremman sensorin? rajoitteita nopeudelle?
- Robotti eksyy helposti vauhdin kasvaessa liian suureksi?
