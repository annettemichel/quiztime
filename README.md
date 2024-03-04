# Quizwebapp mit Quarkus: Quiztime

## How to start

1. Docker starten

```shell script
mvn clean compile quarkus:dev
```

> **_WICHTIG:_**  Die Quarkus Oberfläche wird zuerst abgebildet, um schnell die DEV UI aufrufen zu können.
> Soll die Webapplication aufgerufen werden, dann bitte folgenden Link aufrufen:
>http://localhost:8080/welcome

## Weboberfläche

Mithilfe der Navigationspunkten kann durch die Seite navigiert werden.
Um sich Anzumelden können folgende Benutzerdaten verwendet werden:

### Admin:
Benutzername: `admin`
Passwort: `admin`

Der Admin hat die Möglichkeit Nutzer und Quizfragen zu erstellen und zu verwalten.

### User:
Benutzername: `user`
Passwort: `user`

Der User kann die Bestenliste einsehen und seine eigenen Daten verwalten oder löschen.

Das Quiz selber ist vorerst ein dummy und ist noch nicht spielbar.

Es kann  natürlich auch ein Benutzer registriert werden. Der registrierte Benutzer hat allerdings nur die user-Rolle und eine eingeschränkte Übersicht. Ein Admin kann dem erstellten Benutzer eine Admin-Rolle vergeben.