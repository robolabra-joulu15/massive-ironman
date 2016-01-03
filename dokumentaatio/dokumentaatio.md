author: Henri Vaara

#suunnitelma:

aiheena on tasapainotteleva droidi, tavoitteena saada rakennelma pysymään kahden pyörän varassa pystyssä. mikäli onnistuu niin liikettä eri suuntiin.

#viikko1:

fyysisestä robotista ~10 iteraatio tällä hetkellä, kehitetty: "build, fail, think.." rebuild menetelmällä.
ohjelmasta 3. iteraatio. koodia on atm neljä luokkaa, alkaa jo hieman näyttää merkkejä pystyssä pysymisestä, tukea ei tarvitse enää kuin hiukan. tällä hetkellä mitataan kallistuskulma ja sen mukaisesti kasvatetaan korjausliikkeen nopeutta, ongelmana, että korjaus vastakkaiseen suuntaan alkaa vasta puolivälin kohdalla. pitäis siis ensiviikolle tehdä jokin kulmakiihtyvyyteen perustuva algo.

#viikko2:

fyysinen robotti alkaa olla lopullisessa muodossaan, joskin painopistettä joutuu ehkä vielä muuttamaan korkeussuunnassa. Ohjelmassa on nykyään jo useampi luokka ja tasapainon pitämiseen käytän tällä hetkellä kolmiulotteista korjausliikkeiden karttaa joka muistuttaa x^3 funktiota josta on projektoitu taso korjauskartalle suunnassa kulmasta kulmaan. laite ei vielä täysin pysy pystyssä, mutta heikolla otteella 40cm etäisyydellä usb johdosta tuettuna vekotin jo pysyy pystyssä. Ilman tukea noin kaksi sekuntia. Korjausliikkeille tällä hetkellä oleva staattinen kolmiulotteinen kartta ei ehkä ihan riitä robotin pystyssä pysymiseen, sillä myös laitteen maanopeus vaikuttaa korjausliikkeen suuruuteen, puhumattakaan mikäli painopisteeseen tulisi massamuutoksia. Algoritmi nykymuodossaan esilaskee 21*21 kokoisen int mapin ja tekee 200 paikkaiset hajautustaulut jotta hidas nxt ei joutuisi laskemaan arvoja ja kaikki korjausliikkeet löytyisivät vakioajassa käyttömuistista.

#viikko3:

edistystä ei niin hirveästi ole tapahtunut, myin välipäivät raketteja yli 10 tuntisia päiviä ja uv-aattona oli ystäväni hääjuhlat. koodi on kuitenkin nyt siistitty ja kommentoitu kattavasti sekä olen päässäni miettinyt miten implementoin maanopeuden vaikutuksen korjausliikkeen suuruuteen. ennen perjantain demoa se on done ja katsotaan saanko tuon pysymään pystyssä, ainakin perus idea on auennut.
