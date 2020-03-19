# TODO's
- [ ] verify berekening #1 !!!
- [ ] verify berekening #2 !!!

- [ ] create maven build profles (prod vs default)

- [x] create entity werknemer met naw, email, geboortedatum, salaris
- [x] create jpa repo werknemer
- [x] create restcontroller werknemer

- [x] create entity werkgever
- [x] create jpa repo werkgever
- [x] create restcontroller werkgever

- [X] create entity regeling (rekeningnummer, franchise?, percentages?)
- [X] create jpa repo regeling
- [X] create restcontroller repo regeling
- [ ] add entity property 'in dienst'

- [?] create relation werkgever->werknemer
- [?] create relation werkgever->regeling

- [ ] create relation berekening #1<-werkgever<-regeling
- [ ] create relation berekening #1<-werkgever<-werknemer
- [?] create  berekening #1 using regeling and werknemer properties

- [ ] create relation berekening #2<-werkgever<-regeling
- [ ] create relation berekening #2<-werkgever<-werknemer
- [?] create  berekening #2 using regeling and werknemer properties

- [ ] update entities properties with validations (min max)
- [ ] update form properties  with validations (min max)
