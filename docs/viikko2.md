#Viikkoraportti 2 (Opiskelija: 014614288)

Viikon 2 raportti :)

###Viikon kuulumiset

Ehdin tehdä yllättävän paljon joulun pyhienkin aikana! 

Kohtasin viikon aikana useita valaistuksia. Aluksi luin tutoriaalista behavior-pohjaisesta ohjelmoinnista, josta inspiroiduin ja 
lähdin toteuttamaan projektia tämän mallin mukaan. Tein behaviorien hallintaa varten tosi fancyn BehaviorHandler-nimisen luokan, 
joka  käytti PriorityQueuea behaviorien taulukkoon järjestämiseen, joka sitten annetaan Arbitraattorille. RojbOS:n openjdk6(?) 
ei tukenut PriorityQueuea, ja sen päivittäminen osoittautui ongelmalliseksi, joten päädyin tekemään oman PriorityQueue-luokan. 
Luokan kanssa tuli hieman ongelmia, koska geneerinen taulukko ei ollut Javan mieleen, eikä StackOverflow'sta löytynyt kikka
toiminut, koska openjdk6 ei tukenut Array-luokkaa?! >:( Tein sitten vain Behavioreita järjestävän PriorityQueuen.

Sain tällä tavalla jollakin asteella toimivan viivanseuraajan aikaiseksi, mutta se oli todella kehno ja lopulta totesin, että
sujuvan viivanseuraajan toteuttaminen Behaviorien avulla todennäköisesti ei tulisi onnistumaan. Heitin koodin roskiin (kansio 
old_src repon juuressa) ja lähdin toteuttamaan projektia uudesta näkökulmasta.

Tutustuin minulle vinkattuun PID-kontrolleriin enemmän ja tajusin, että se on paljon fiksumpi tapa lähteä tätä projektia
toteuttamaan, vaikka en sen toimintaa kunnolla ymmärräkään. Aloitin projektin uudelleenkirjoituksen, ja tällä kertaa lähdinkin
ensimmäisenä tekemään käyttöliittymää, koska logiikan toteuttaminen on paljon helpompaa, kun robotti osaa itse skannata 
tarvittavat valoarvot Light Sensorilla ja liikkumisnopeuden/pyörimisnopeuden muutokseen ei tarvitse joka kerta kääntää koodia 
uudestaan.
