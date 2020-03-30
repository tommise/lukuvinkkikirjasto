## Asennusohjeet:

Lataa tiedosto [lukuvinkkikirjasto-all.jar](https://github.com/tommise/lukuvinkkikirjasto/releases/tag/v1.1).

Käynnistä ohjelma komennolla:
```
java -jar lukuvinkkikirjasto-all.jar
```

## Käyttöohjeet:

Kun olet käynnistänyt sovelluksen asennusohjeiden mukaisesti, saat konsoliisi seuraavan näkymän:

```
Valitse joku seuraavista komennoista:
1: luo uusi lukuvinkki
2: tarkastele vinkkejä
3: poista lukuvinkki
x: lopeta
```

#### Luo uusi lukuvinkki

Komennolla "1" luot uuden lukuvinkin. Luodessasi uuden lukuvinkin ohjelma kysyy sinulta ensin lukuvinkin otsikkoa.
```
Otsikko:
```
Kun olet valinnut lukuvinkillesi haluamasi otsikon ja painanut enter, kysytään seuraavaksi linkkiä. Linkkiä ei ole pakko lisätä, joten tämän kohdan voi jättää tyhjäksi.
```
Linkki:
```
Painettuasi enter voit lisätä lukuvinkille kuvauksen. Myös kuvauksen voi jättää tyhjäksi:
```
Kuvaus:
```
Tämän jälkeen voit lisätä halutessasi lukuvinkille yhden tai useamman tagin. Erottele tagit pilkulla (esim. tagi1, tagi2):
```
tagit: 
```
Kun lukuvinkille on valittu haluamasi tiedot ja painettu enter, ohjelma ilmoittaa sinulle lukuvinkin lisäämisen onnistumisesta.
```
Lukuvinkki lisatty!
```

#### Tarkastele lukuvinkkejä

Komennolla "2" voit tarkastella luotuja lukuvinkkejä. Lukuvinkkisi listataan seuraavanlaisesti:
```
Otsikko: Parhaat ohjelmointiniksit
Linkki: www.parhaatniksit.fi
Tallennettu: 22/03/2020 14:15
Kuvaus: kollega sanoi että tästä blogista löytyy tosi hyviä juttuja
Tagit: ohjelmointi, blogit
Id: 28

```

### Poista lukuvinkki

Komennolla "3" voit poistaa lukuvinkin id:n perusteella. Id:n voit tarkistaa komennolla "2" saamasta listauksesta.

```
Poistettavan lukuvinkin id: 
```
Kun olet syöttänyt poistettavan lukuvinkin id:n, ohjelma ilmoittaa sinulle lukuvinkin poistamisen onnistumisesta:
```
Vinkki poistettu.
```

Ohjelmasta poistutaan komennolla "x". Kun seuraavan kerran käynnistät ohjelman, aiemmin syöttämäsi tiedot ovat tallessa.

