#viikko 2

Toisella viikolla on p��sty hieman pidemm�lle varsinaiseen labyrintin ratkaisijan koodaukseen ja rakenteluun.

Aluksi vuorossa olivat kompassin ja ultra��nisensorin testaukset. Testeiss� sin�ns� ei ollut sen suurempia vaikeuksia, mutta sen sijaan jatkossa ongelmia tuottaa sensorien kohina, jota oli mittaustuloksissa melko runsaasti.

Testien j�lkeen ensimm�isen� tavoitteena oli rakentaa yksinkertainen 3-py�r�inen robotti, joka l�yt�� labyrintista ulos seuraamalla vasenta sein�� ultra��nisensorilla. Toteutus paljasti useita ongelmia sek� robotin rakenteessa ett� koodauksessa; alkuper�ist� rakennetta piti muokata useaan otteeseen, jotta robotti pystyisi k��ntym��n tehokkaasti. Vakavammat ongelmat liittyiv�t kuitenkin sensoreihin: erityisesti ultra��nisensorit eiv�t n�yt� toimivan kovin luotettavasti kotitekoisessa, l�hinn� kirjapinoista rakennetussa labyrintissa. Useiden testiajojen ja muokkausten j�lkeen robotti suoriutui labyrintissa ulos tismalleen kerran, mik� oli sin�ns� suuren kansanjuhlan aihe.

Robotin seuraava versio monimutkaistaa ohjelmointia aavistuksen: pelk�n sein�n seurailun sijaan tarkoituksena on pit�� kirjaa robotin tilasta, joka voi perusversiossa olla k�yt�v� tai kulmaus. Ajatuksena on rakentaa yksinkertainen Markovin ketjumainen mallinnus, jossa robotti muistaa edellisen tilan, yritt�� ultra��nisensorin ja kompassin avulla p��tell� nykyisen ja toimia sen mukaan. Ensimm�isten testien perusteella robotti n�ytt�isi toimivan v�hint��n yht� hyvin kuin sein�� seurannut edelt�j�ns�, mutta ohjelmointi on viel� keskener�inen.

Seuraavasta viikosta suuri osaa kuluu joululomalla muualla kuin robottipajalla sek� uuden vuoden vietossa, joten edistymist� ei luultavasti juurikaan tule. Ty�n jatkuessa seuraava askel on parantaa tilan mallinnusta lis��m�ll� edellisen tilan hy�dynt�minen robotin toimintaan.





