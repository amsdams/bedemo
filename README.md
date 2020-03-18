prod database

### create db, user, grant, exit

```
psql
```


```sql
create database bedemo;
```
```sql
create user myuser with encrypted password 'mypass';
```

```sql
grant all privileges on database bedemo to myuser;


# building

## build frontend

```bash
cd src/main/web/bedemo/
ng build --prod
```

### run e2e in local browser
```bash
mvn spring-boot:run

cd src/main/web/bedemo/
ng e2e
```

## build backend

```bash
mvn clean install
```
## run 'prod'
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```


## run 'dev' and 'test'
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```
