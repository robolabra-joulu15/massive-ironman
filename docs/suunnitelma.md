### 18.12.215 TKTL Robolabra
## Suunnitelma: Bilerobo


Bilerobo on Lego Mindstorm robotti, joka liikehtii eli tanssii musiikin tahtiin. Suunnitelmani mukaan robotissa on kolme moottoria, joista kaksi heiluttaa robotin "käsiä" suuntaa vaihdellen ja yksi liikuttaa robottia itseään akselinsa ympäri (tai vaihtoehtoisesti jotain muuta, riippuen insinööritaidoistani). Musiikkina toimii yksinkertainen biitti ja robotin liikehdintä perustuu biitin nopeuteen.

Biitin tunnistamiseen käytetään Lego Mindstormin omaa äänisensoria. Alkuperäisen suunnitelman mukaan robotti olisi tunnistanut äänentaajuksia, mutta kyseinen sensori ei siihen pysty. Seuraavaksi haasteena on tarkastella, kuinka tarkkaan mikrofoni poimii ääntä ja kuinka biitti saadaan erilleen muusta hälinästä.

Bilerobotti tunnistaa aluksi vain nelitahtisen rytmin. Se laskee signaaleista itselleen nopeuden jonka mukaan "raajojen" nopeus määräytyy. Jatkokehitysideoita mm.: liikeratojen vaihtelu biitin nopeuden mukaan, eri jakoisten biittien tunnistaminen ja liikkeiden niihin sopeuttaminen.

Äänen poimimisen jälkeen suurin haaste tulee olemaan koodipuolella tahdin ja jakoisuuden tunnistaminen. Bilerobotti tulee todennäköisesti vaihtamaan liikerata-algoritmiaan vain yhden tahdin eli neljän iskun välein. Tällä tavalla pyritään minimoimaan viiveestä aiheutuvat ongelmat sekä yritetään saada liikeratojen vaihtelu mahdollisimman sulavaksi.