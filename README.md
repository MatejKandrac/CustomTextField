# Opis práce na zadaní

## Definovanie konštánt
V rámci pridelenej Figmy som najprv vybral všetky farby, typografiu a iné konštanty.
Tie sa nachádzajú v súbore Constants.kt.

V zadaní je spomenuté, že mám použiť font Inter (napriek tomu, že v príklade bol použitý iný font).
Stiahol som ho teda z Google Fonts stránky a prihodil do resources.
https://fonts.google.com/specimen/Inter

V normálnom prípade by som vytvoril tému applikácie a pouzil `MaterialTheme`, no tým, že neviem všetky farby aplikácie
som sa rozhodol používať konštanty.

## Prvotná hierarchia a uvažovanie
Najprv som vytvoril compose funkciu pre InputView a po prezeraní komponentu vo Figme som najprv
pripravil jednoduchú štruktúru, ktorá obsahovala jeden stĺpec, jeden riadok a OutlinedTextField composable.
Hierarchia teda prvotne vyzerala nasledovne:
```
Column
    - Row
        - Text (label)
        - Text (optional text)
    - OutlinedTextField
```
### Label, Optional a Placeholder text
Po pridaní label, optional a placeholder textu som chcel zachovať to, aby som mohol v prípade potreby zmeniť kompletne
štýl celého textu a to kvôli tomu, že je tažké povedať, na čo sa reálne tento komponent použije, a možno v 
nejakom špeciálnom prípade by niekto chcel zmeniť toto štýlovanie.

Rozhodol som sa, aby medzi parametre nešiel v prípade týchto textov Composable, ale konkrétny String, a to
aby som mohol meniť farby textu podľa stavu (error).

Všetkým týmto textom som pridal aj možnosť zmeny prednastavenej farby (v prípade, ak by to chcel niekto meniť).

### Príliš dlhé texty
Už pri pozretí na zadanie som videl potenciálny problém s príliš dlhými textami. V prípade ak by som v pôvodnej hierarchii pouižil
Row a label text by bol príliž dlhý, optional text sa ani nezobrazí. Preto som sa rozhodol použiť FlowRow namiesto Row.
https://developer.android.com/develop/ui/compose/layouts/flow
Nová hierarchia teda vyzerala takto:
```
Column
    - FlowRow
        - Text (label)
        - Text (optional text)
    - OutlinedTextField
```
FlowRow zabezpečí, že ak je label text príliš dlhý, optional text sa presunie do daľšieho riadku.

Počaš používania FlowRow som narazil na problém so zarovnaním textu, nakoľko veľkosť riadku pri rozdielnych 
textových štýloch spôsobovala, že sa nezarovnávali správne. Našiel som ale diskuskiu v ktorej tento problém prebrali
a podarilo sa mi to opraviť tak, že som pridal modifier pre texty.
https://slack-chats.kotlinlang.org/t/12085939/has-anyone-been-able-to-successfully-use-flowrow-and-alignme

### Štýlovanie komponentu
Nakoľko používam OutlinedTextField, pridal som mimo hore spomenutých parametrov pre štýlovanie textov aj
všetky možné parametre ktoré OutlinedTextField má. Týmto zabezpečím, že komponent bude mať rovnaké možnosti 
štýlovania ako pôvodný komponent OutlinedTextField.
