Viikkoraportti 3:

Huomasin, että HOG-tunnistin ei kyennyt tunnistamaan ihmisiä, koska kameraa ei ollut kalibroitu. Toteutin tutoriaalin pohjalta oman kalibroija-luokan, joka etsii kamerasyötteestä shakkilautakuviota ja määrittää korjaavat parametrit. Kun viimein sain kalibroinnin tehtyä, ihmisten tunnistus alkoi toimia saman tien. Tosin kalibroinnin ja kuvan korjauksen tuloksena näkökenttä on entistä kapeampi ja resoluutio vieläkin pienempi.

Korvatakseni HOG-tunnistuksen hitautta loin erillisen luokan, joka tarkkailee kameran syötettä ja etsii ihmisiä kymmenen framen välein. Korjasin lisäksi pienen bugin, jossa ohjelma kaatui suljettaessa, koska kamerasyötettä läpikäyvällä säikeellä ei ollut lopetusehtoa (siis ikuinen while-luuppi), jolloin python joutui sulkemaan sen väkisin.

Seuraavana etappina etsin kameraa lähinnä olevan ihmisen ja laskin tämän etäisyyden näkökentän keskustasta; tästä lasken sen jälkeen kulman, jonka verran dronen tulisi osapuilleen kääntyä, jotta lähin ihminen tulisi sen näkökentän keskiöön. Yritin testata tätä, mutta valitettavasti huoneessani ei ollut riittävästi tilaa.

Ensi viikolla yritän etsiä suuremman tilan testejä varten ja kokeilen ehkä varovaisen lähestymiskäyttäytymisen implementoimista. Toteutan myös jonkinlaisen kasvontunnistimen, tosin jää nähtäväksi, saanko sen toimimaan dronella näin alhaisella resoluutiolla.
