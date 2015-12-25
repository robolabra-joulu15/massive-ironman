Viikkoraportti 2:

Tällä viikolla latasin ps_drone -rajapinnan, joka mahdollistaa dronen ohjaamisen pythonin avulla, sekä opencv:n, jonka avulla saan kaapattua dronen lähettämän videokuvan ja myöhemmin myös analysoitua sen. Kopterin ohjaaminen ps_dronen avulla osoittautui hankalaksi. Hyvä, että minulla oli tällä viikolla liikuntasali  käytössäni testailua varten, mikä vähensi törmäilyä.

Suuremmaksi ongelmaksi muodostui kamerakuva, joka oli liian suuren puskurin takia aluksi ainakin n. 5 sekuntia jäljessä reaaliajasta. Tutkin asiaa stackoverflow:ssa ja eriytin kamerakuvan hakemisen omaan, erilliseen säikeeseensä, mikä poisti ongelman lähes kokonaan. Nyt kuva on alle sekunnin jäljessä.

Seuraavalla viikolla alkaa projektin haastavin osuus, eli ihmisten tunnistaminen ja kopterin automaattiohjaus. Ehdin jo kokeilla opencv:n valmista HOG -tekniikkaan perustuvaa ihmistunnistinta, mutta havaitsin sen olevan aivan liian hidas reaaliaikaiseen käyttöön (eikä se edes tunnistanut ihmisiä kunnolla). Seuraavaksi aion testailla parametreja, syventyä asiaan liittyviin papereihin/tutoriaaleihin ja kouluttaa mahdollisesti oman tunnistimen.
