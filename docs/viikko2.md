#viikko 2

Toisella viikolla on päästy hieman pidemmälle varsinaiseen labyrintin ratkaisijan koodaukseen ja rakenteluun.

Aluksi vuorossa olivat kompassin ja ultraäänisensorin testaukset. Testeissä sinänsä ei ollut sen suurempia vaikeuksia, mutta sen sijaan jatkossa ongelmia tuottaa sensorien kohina, jota oli mittaustuloksissa melko runsaasti.

Testien jälkeen ensimmäisenä tavoitteena oli rakentaa yksinkertainen 3-pyöräinen robotti, joka löytää labyrintista ulos seuraamalla vasenta seinää ultraäänisensorilla. Toteutus paljasti useita ongelmia sekä robotin rakenteessa että koodauksessa; alkuperöistä rakennetta piti muokata useaan otteeseen, jotta robotti pystyisi kääntymään tehokkaasti. Vakavammat ongelmat liittyivät kuitenkin sensoreihin: erityisesti ultraäänisensorit eivät näytä toimivan kovin luotettavasti kotitekoisessa, lähinnä kirjapinoista rakennetussa labyrintissa. Useiden testiajojen ja muokkausten jälkeen robotti suoriutui labyrintissa ulos tismalleen kerran, mikä oli sinänsä suuren kansanjuhlan aihe.

Robotin seuraava versio monimutkaistaa ohjelmointia aavistuksen: pelkän seinän seurailun sijaan tarkoituksena on pitää kirjaa robotin tilasta, joka voi perusversiossa olla käytävä tai kulmaus. Ajatuksena on rakentaa yksinkertainen Markovin ketjumainen mallinnus, jossa robotti muistaa edellisen tilan, yrittää ultraäänisensorin ja kompassin avulla päätellä nykyisen ja toimia sen mukaan. Ensimmäisten testien perusteella robotti näyttäisi toimivan vähintään yhtä hyvin kuin seinää seurannut edeltäjänsä, mutta ohjelmointi on vielä keskeneräinen.

Seuraavasta viikosta suuri osaa kuluu joululomalla muualla kuin robottipajalla sekä uuden vuoden vietossa, joten edistymistä ei luultavasti juurikaan tule. Työn jatkuessa seuraava askel on parantaa tilan mallinnusta lisäämällä edellisen tilan hyödyntäminen robotin toimintaan.





