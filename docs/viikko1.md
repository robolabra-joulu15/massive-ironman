#Viikkoraportti 1 (Opiskelija: 014614288)
Viikon 1 raportti.

###Viikon kuulumiset
Sain robokitin tiistaina ja aloin heti kotiin päästyäni testailemaan. Vaikutti siltä, että moottoriportti A ei toiminut
ollenkaan, ja menin seuraavana päivänä käymään pajassa. Selvisi, että brickissä oli jostain syystä Legon firmware, joten pajassa
flashattiin brickiin leJOS. Ilmeni kuitenkin paljon ongelmia kehitysympäristön pystyttämisessä, joita janluu ei myöskään saanut 
ratkaistua.

Seuraavia kokeiltiin:
- VirtualBoxin sisällä ajettu rojbOS (monta kertaa, monta eri virtualbox versiota)
- VirtualBoxin sisällä ajettu ubuntu, johon kasattu kaikki tarvittava softa (monta kertaa)
- 64bit emo-ubuntuun säädetty 32bit kehitysympäristö -> sama ongelma kuin muissa

Lopulta päädyin ajamaan rojbOS:a muistitikulta, koska itse kasatut kehitysympäristöt muistitikulle eivät myöskään toimineet. 
Muistitikulta ajettava rojbOS ei tue wlania eikä kunnolla touchpadia. Toiminta myöskin oletettavasti hieman hidasta. Pystyn 
kuitenkin jakamaan netin kännykän kautta USB:llä, ja ympäristö kuitenkin loppujenlopuksi toimii, joskin ei kovin hyvin.

Lähdin myös kasaamaan testimielessä robotin rakennetta ohjeen perusteella (http://www.nxtprograms.com/castor_bot/index.html). 
Ihastuin kyseisen robotin rakenteeseen, ja se on helposti laajennettavissa. Saatan käyttää tätä pohjana robotilleni ainakin näin 
aluksi, mutta mahdollisesti joudun tekemään uuden rungon, sillä viivanseuraajan kannalta robotin olisi hyvä olla hieman matalampi,
jotta se pystyisi korkeampiin nopeuksiin ilman kaatumista, ja lisäksi valosensori on helpompi saada lähemmäs maata.

###Ensi viikon suunnitelmat
Lähden ensin ohjelmoimaan robotille luokkaa, jonka avulla pystyn toteuttamaan sen liikkumista renkaiden avulla helposti. Sen jälkeen
toteutan luokan valosensorin käytölle, ja sitten mahdollisesti itse viivanseuraajalogiikkaa. Teen näistä ne mitä ehdin, koska
ensi viikolla on kuitenkin joulun pyhät, jolloin vietän joulua perheeni kanssa! :)
