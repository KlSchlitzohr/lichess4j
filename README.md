# lichess4j

Java wrapper for the API of lichess.org. https://lichess.org/api
**NOTE**: This is an unofficial wrapper. Do not expect the lichess community to provide support for this wrapper.

## OPEN ALPHA ##

This Projekt is currently in alpha. Currently only the Account, User and Realtions Manager are ready!

## Create LicessClient

Creating the LichessCLient is done via the LichessClient class. 

**LichessCLient Example**:
```java
LichessClient lichessClient = new LichessClient("{yourToken}");
```

**LichessClient Manager**
The LichessClient has a manager class for each information category.

```java
AccountManager accountManager = lichessClient.getAccountManager();
UsersManager usersManager = lichessClient.getUsersManager();
RelationsManager relationsManager = lichessClient.getRelationsManager();
```

From there you can easily make requests.

```java
String email = accountManager.getEmail();
```

**Maven**
```xml
<dependency>
  <groupId>de.klschlitzohr</groupId>
  <artifactId>lichess4j</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```
```xml
<repository>
    <id>github</id>
    <url>https://maven.pkg.github.com/klschlitzohr/lichess4j</url>
</repository>
```

**Gradle**
```gradle
dependencies {
    implementation 'de.klschlitzohr:lichess4j:1.0-SNAPSHOT'
}

repositories {
    maven {
      url = 'https://maven.pkg.github.com/klschlitzohr/lichess4j'
    }
}
```

**Contributing to lichess4j**

You are cordially invited to contribute to this project.

**Getting Help**

If you need help or have questions you can contact me on Discord (KlSchlitzohr#1166).
