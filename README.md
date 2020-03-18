#DeDemo

# prod database using psql

## create db, user, grant, exit

```bash
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
```

```sql
exit
```

# building

## frontend build

```bash
mvn clean install
```

or

```bash
cd src/main/web/
ng build --prod
```
# frontend development

```bash
npm install
ng serve
```
## test  e2e in local browser
```bash
mvn spring-boot:run

cd src/main/web/
ng e2e
```

see [angular README](src/main/web/README.md)

## development backend

```bash
mvn spring-boot:run
```

# build backend

```bash
mvn clean install
```
## run 'prod'
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

# deploying
```bash
heroku login
heroku create
git push heroku master
heroku open
```
