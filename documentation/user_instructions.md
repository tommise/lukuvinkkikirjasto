## Asennusohjeet:

Kloonaa lukuvinkkikirjasto paikallisesti haluamaasi kansioon komennolla:

```
git clone git@github.com:tommise/lukuvinkkikirjasto.git
```
ja avaa kansio terminaalilla.

Suorita seuraavat gradlen komennot:
```
./gradlew
```
```
./gradlew clean
```
```
./gradlew build
```
Luo projektista suoritettava .jar tiedosto:
```
./gradlew shadowJar
```
Käynnistä sovellus projektin juurikansiossa komennolla:
```
java -jar build/libs/lukuvinkkikirjasto-all.jar
```

## Käyttöohjeet:

Kun olet käynnistänyt sovelluksen asennusohjeiden mukaisesti, saat konsoliisi seuraavan näkymän:

```
Valitse joku seuraavista komennoista:
1: luo uusi lukuvinkki
2: tarkastele vinkkejä
```

#### Luo uusi lukuvinkki

Komennolla "1" luot uuden lukuvinkin. Luodessasi uuden lukuvinkin ohjelma kysyy sinulta ensin lukuvinkin otsikkoa.
```
Otsikko:
```
Kun olet valinnut lukuvinkillesi haluamasi otsikon ja painanut enter, kysytään seuraavaksi linkkiä.
```
Linkki:
```
Kun lukuvinkille on valittu haluamasi linkki ja painettu enter, ohjelma ilmoittaa sinulle lukuvinkin lisäämisen onnistumisesta.
```
Lukuvinkki lisatty!
```

#### Tarkastele lukuvinkkejä

Komennolla "2" voit tarkastella luotuja lukuvinkkejä. Lukuvinkkisi listataan seuraavanlaisesti:
```
Otsikko: Lukuvinkin 1 otsikko
Linkki: Lukuvinkin 1 linkki

Otsikko: Lukuvinkin 2 otsikko
Linkki: Lukuvinkin 2 linkki
```

Ohjelmasta voidaan poistua esimerkiksi komennolla Ctrl + C.

