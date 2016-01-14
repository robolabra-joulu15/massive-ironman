# massive-ironman
Joulun robolabra 2015
Author: Jaakko Virtanen

## Huomioitavaa:
Arvostelun kannalta keskeisimmät tiedostot löytyvät /docs/-kansion alta (erityisesti loppuraportti) ja koodi taasen /DogTreatThrower/src/-kansiosta.

Tavoitteekseni tälle projektille muodostui puhtaan koodin kirjoittaminen, selkeä ja järkevä OOP-Design ja riittävä dokumentointi. Nämä valitsin ihan siitä syystä, että normaalisti olen hieman laiska ja huono dokumentoimaan. Hieman taka-alalle tällä kertaa jäivät robotin rakenteen viimeistely (vaikkakin toimii hyvin nytkin), sekä useiden eri toiminnallisuuksien ohjelmoiminen ensimmäiseen versioon robotista. Tein siis mieluummin hyvän kivijalan, joka kestäisi tulevaa kuormitusta uusien toiminnallisuuksien muodossa vähin muutoksin olemassa olevaan. "Program to an interface, not an implementation." / "Favor composition over inheritance." ovat muutamat Design Prinsiipit, joita pyrin koodini rakenteessa noudattamaan. Myös monisäikeistys toi hivenen vaikeusastetta muutoin yksinkertaiseen työhöni.

Laajuutta ja lisää oppia projektiin päätin hakea kirjoittamalla dokumentaation englanniksi, sekä kuvaamalla toimintaa melko tarkasti. Tein myös kevyitä UML-kaavioita täydentämään dokumentaatiota.

## Repon rakenne:
<pre>
.
├── docs
│   ├── pictures
│   │   └── DTT.gif
│   ├── rapsat
│   │   ├── suunnitelma.md
│   │   ├── viikko1.md
│   │   ├── viikko2.md
│   │   ├── viikko3.md
│   │   └── viikko4.md
│   └── videos
│       └── DTT_UI_video_low_resolution.mkv
├── DogTreatThrower
│   ├── bin
│   │   ├── basicBehaviour
│   │   │   ├── RoboRoutine.class
│   │   │   └── TriggerListener.class
│   │   ├── Main
│   │   │   └── Main.class
│   │   └── triggers
│   │       ├── ButtonTrigger.class
│   │       ├── Trigger.class
│   │       └── VoiceTrigger.class
│   ├── build
│   │   ├── basicBehaviour
│   │   │   ├── RoboRoutine.class
│   │   │   └── TriggerListener.class
│   │   ├── Main
│   │   │   └── Main.class
│   │   ├── Main.nxd
│   │   ├── Main.nxj
│   │   └── triggers
│   │       ├── ButtonTrigger.class
│   │       ├── Trigger.class
│   │       └── VoiceTrigger.class
│   ├── build.properties
│   ├── build.xml
│   ├── doc
│   │   ├── allclasses-frame.html
│   │   ├── allclasses-noframe.html
│   │   ├── constant-values.html
│   │   ├── deprecated-list.html
│   │   ├── help-doc.html
│   │   ├── index-all.html
│   │   ├── index.html
│   │   ├── org
│   │   │   └── lejos
│   │   │       └── example
│   │   │           ├── HelloWorld.html
│   │   │           ├── package-frame.html
│   │   │           ├── package-summary.html
│   │   │           └── package-tree.html
│   │   ├── overview-tree.html
│   │   ├── package-list
│   │   ├── resources
│   │   │   └── inherit.gif
│   │   └── stylesheet.css
│   ├── nbproject
│   │   ├── ide-file-targets.xml
│   │   └── project.xml
│   └── src
│       ├── basicBehaviour
│       │   ├── RoboRoutine.java
│       │   └── TriggerListener.java
│       ├── Main
│       │   └── Main.java
│       ├── taaltaLoytyyKooditArvosteluun.txt
│       └── triggers
│           ├── ButtonTrigger.java
│           ├── Trigger.java
│           └── VoiceTrigger.java
├── README.md
└── reponRakenne.txt

23 directories, 49 files
.
├── docs
│   ├── pictures
│   │   ├── DTT.gif
│   │   ├── SAM_8175.JPG
│   │   ├── SAM_8176.JPG
│   │   ├── SAM_8177.JPG
│   │   ├── SAM_8178.JPG
│   │   ├── SAM_8179.JPG
│   │   ├── SAM_8180.JPG
│   │   ├── SAM_8181.JPG
│   │   ├── SAM_8182.JPG
│   │   ├── SAM_8183.JPG
│   │   ├── SAM_8185.JPG
│   │   ├── SAM_8198.JPG
│   │   ├── SAM_8203.JPG
│   │   └── SAM_8209.JPG
│   ├── rapsat
│   │   ├── DogTreatThrower - Final Documentation.pdf
│   │   ├── suunnitelma.md
│   │   ├── viikko1.md
│   │   ├── viikko2.md
│   │   ├── viikko3.md
│   │   └── viikko4.md
│   └── videos
│       ├── DTT_low_resolution.mpeg
│       ├── firingAmmos_SLOMO.MOV
│       ├── myDogPavlov.mpeg
│       └── releasingAmmos_SLOMO.MOV
├── DogTreatThrower
│   ├── bin
│   │   ├── basicBehaviour
│   │   │   ├── RoboRoutine.class
│   │   │   └── TriggerListener.class
│   │   ├── Main
│   │   │   └── Main.class
│   │   └── triggers
│   │       ├── ButtonTrigger.class
│   │       ├── Trigger.class
│   │       └── VoiceTrigger.class
│   ├── build
│   │   ├── basicBehaviour
│   │   │   ├── RoboRoutine.class
│   │   │   └── TriggerListener.class
│   │   ├── Main
│   │   │   └── Main.class
│   │   ├── Main.nxd
│   │   ├── Main.nxj
│   │   └── triggers
│   │       ├── ButtonTrigger.class
│   │       ├── Trigger.class
│   │       └── VoiceTrigger.class
│   ├── build.properties
│   ├── build.xml
│   ├── doc
│   │   ├── allclasses-frame.html
│   │   ├── allclasses-noframe.html
│   │   ├── constant-values.html
│   │   ├── deprecated-list.html
│   │   ├── help-doc.html
│   │   ├── index-all.html
│   │   ├── index.html
│   │   ├── org
│   │   │   └── lejos
│   │   │       └── example
│   │   │           ├── HelloWorld.html
│   │   │           ├── package-frame.html
│   │   │           ├── package-summary.html
│   │   │           └── package-tree.html
│   │   ├── overview-tree.html
│   │   ├── package-list
│   │   ├── resources
│   │   │   └── inherit.gif
│   │   └── stylesheet.css
│   ├── nbproject
│   │   ├── ide-file-targets.xml
│   │   └── project.xml
│   └── src
│       ├── basicBehaviour
│       │   ├── RoboRoutine.java
│       │   └── TriggerListener.java
│       ├── Main
│       │   └── Main.java
│       ├── taaltaLoytyyKooditArvosteluun.txt
│       └── triggers
│           ├── ButtonTrigger.java
│           ├── Trigger.java
│           └── VoiceTrigger.java
├── README.md
└── reponRakenne.txt

23 directories, 66 files
<pre>
