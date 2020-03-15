### Asennusohjeet:

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

### Käyttöohjeet:
