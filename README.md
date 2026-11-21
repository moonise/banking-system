# Banking System

A Java banking system in development, starting with a transaction model for payments and transfers.

## Current features

- Shared transaction data: date, amount and description.
- Deposits and withdrawals with configurable fee rates.
- Transfers with sender and recipient information.
- Copy constructors, value comparison and matching hash codes.
- Console demo with automated checks for calculations and validation.
- UML class diagram in [UML.svg](UML.svg), with editable [PlantUML source](UML.puml).

## Run

Requires JDK 17 or later. No external dependencies.

```sh
mkdir -p out
javac --release 17 -encoding UTF-8 -d out src/bank/*.java src/Main.java
java -cp out Main
```

The demo prints transaction examples and check results. Invalid-value messages are expected for the validation checks. A successful run ends with `Alle automatischen Prüfungen erfolgreich.`

## Documentation

Generate API documentation from the source comments:

```sh
javadoc -encoding UTF-8 -d doc src/bank/*.java src/Main.java
```

Open `doc/index.html` to browse it.

## Project status

The current version provides the transaction layer. Account management, persistence and a user interface are not implemented yet. Amounts currently use `double`; this prototype is intended for development and demonstration.
