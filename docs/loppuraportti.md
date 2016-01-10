#Viivanseuraaja - Loppuraportti

Toteutin kurssilla robotin, joka osaa seurata mustasta viivasta tehtyä rataa. Robotti saavuttaa parhaan tuloksen, kun alusta on mahdollisimman vaalea. Myös hyvä valaistus parantaa robotin suorituskykyä. Itse testasin robottia huoneeni parkettilattiaan mustalla teipillä tehdyllä radalla, ja valaistuksena toimi ihan normaali kattolamppu. Robotti toimi moitteettomasti myös tässä ympäristössä. Robotti hyödyntää toiminnassaan PID-kontrolleria, jonka avulla pystytään toteuttamaan viivan seuraaminen mahdollisimman sulavasti. 

Robotissa on myös NXT-brickin näytölle toteutettu käyttöliittymä, josta pystyy säätämään robotin arvoja mieleisikseen. Tämä mahdollistaa sen, että robottia on mahdollista ajaa lukuisilla eri asetuksilla helposti ilman, että koodia tarvitsee kääntää uudestaan, mikä lisää joustavuutta huomattavasti. 

Säädettävät arvot:
* Moottorien portit
* Taustan ja viivan valoarvot
* Liikkumisnopeus
* PID-kontrollerin vakiot (kp, ki ja kd)

Liikkumisnopeutta ja PID-kontrollerin vakioita säätämällä robotin saa esimerkiksi menemään todella kovaa jos rata on loiva, tai jos rata sisältää todella jyrkkiä mutkia, voi robotin säätää menemään hiljaa mutta tarkasti.
