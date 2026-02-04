#  Battleship Client (Deutsch)

Eine Android-App für das klassische Schiffe-Versenken-Spiel, entwickelt mit Kotlin und Jetpack Compose.

##  Features

- **Multiplayer-Unterstützung**: Trete einem Spiel mit einem Game-Key bei
- **Drei Hauptbildschirme**:
  -  **Login**: Spieler-Registrierung und Spielbeitritt
  -  **Angriff**: Schieße auf das gegnerische Spielfeld
  -  **Abfrage**: Zeige gegnerische Angriffe auf dein Spielfeld

##  Technologie-Stack

- **Sprache**: Kotlin
- **UI-Framework**: Jetpack Compose mit Material Design 3
- **Navigation**: Jetpack Navigation Component
- **Architektur**: MVVM mit ViewModel
- **Netzwerk**: REST-API Kommunikation über HTTP

##  Spielablauf

1. **Login**: Gib deinen Spielernamen und Game-Key ein
2. **Schiffe platzieren**: Die App platziert automatisch 5 Schiffe:
   - Carrier (5 Felder)
   - Battleship (4 Felder)
   - Submarine (3 Felder)
   - Destroyer (3 Felder)
   - PatrolBoat (2 Felder)
3. **Angreifen**: Wähle Koordinaten auf dem gegnerischen Spielfeld
4. **Verteidigen**: Überprüfe, wo der Gegner geschossen hat

##  Projektstruktur

```
app/src/main/java/ch/kapischan_sriganthan/battleship_client/
├── MainActivity.kt          # Hauptaktivität mit Navigation
├── LoginScreen.kt          # Login-Bildschirm
├── FireScreen.kt           # Angriffs-Bildschirm
├── EnemyScreen.kt          # Verteidigungs-Bildschirm
├── GameViewModel.kt        # State-Management
├── APIFunctions.kt         # Server-Kommunikation
├── Ship.kt                 # Schiffs-Datenmodell
├── Screen.kt               # Navigation-Definition
└── ui/theme/               # Material Design Theme
```

##  Installation

1. Klone das Repository:
```bash
git clone https://github.com/ksriganthan/Battleship_Client.git
```

2. Öffne das Projekt in Android Studio

3. Stelle sicher, dass du mindestens API Level 24 (Android 7.0) verwendest

4. Baue und starte die App auf einem Emulator oder physischen Gerät

##  API-Endpunkte

Die App kommuniziert mit folgenden Endpunkten:
- `POST /game/join` - Spiel beitreten
- `POST /game/fire` - Schuss abgeben
- `POST /game/enemyFire` - Gegnerische Schüsse abrufen

##  Berechtigungen

- `INTERNET`: Für die Kommunikation mit dem Spiel-Server
- `usesCleartextTraffic`: Erlaubt HTTP-Verbindungen

##  UI-Design

- Material Design 3
- Dynamic Color Support (Android 12+)
- Dark Mode Unterstützung
- Responsive Grid-Layout für Spielfelder

##  Entwickler

- Kapischan Sriganthan
- Loic Bösch
- Francesco Leone


# Battleship Client (English)

An Android app for the classic Battleship game, developed with Kotlin and Jetpack Compose.

## Features

- **Multiplayer Support**: Join a game with a Game-Key
- **Three Main Screens**:
  - **Login**: Player registration and game joining
  - **Attack**: Shoot at the opponent's board
  - **Query**: View enemy attacks on your board

## Technology Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose with Material Design 3
- **Navigation**: Jetpack Navigation Component
- **Architecture**: MVVM with ViewModel
- **Network**: REST API communication via HTTP

## Gameplay

1. **Login**: Enter your player name and Game-Key
2. **Place Ships**: The app automatically places 5 ships:
   - Carrier (5 cells)
   - Battleship (4 cells)
   - Submarine (3 cells)
   - Destroyer (3 cells)
   - PatrolBoat (2 cells)
3. **Attack**: Select coordinates on the opponent's board
4. **Defend**: Check where the opponent has fired

## Project Structure

```
app/src/main/java/ch/kapischan_sriganthan/battleship_client/
├── MainActivity.kt          # Main activity with navigation
├── LoginScreen.kt          # Login screen
├── FireScreen.kt           # Attack screen
├── EnemyScreen.kt          # Defense screen
├── GameViewModel.kt        # State management
├── APIFunctions.kt         # Server communication
├── Ship.kt                 # Ship data model
├── Screen.kt               # Navigation definition
└── ui/theme/               # Material Design Theme
```

## Installation

1. Clone the repository:
```bash
git clone https://github.com/ksriganthan/Battleship_Client.git
```

2. Open the project in Android Studio

3. Make sure you are using at least API Level 24 (Android 7.0)

4. Build and run the app on an emulator or physical device

## API Endpoints

The app communicates with the following endpoints:
- `POST /game/join` - Join game
- `POST /game/fire` - Fire shot
- `POST /game/enemyFire` - Retrieve enemy shots

## Permissions

- `INTERNET`: For communication with the game server
- `usesCleartextTraffic`: Allows HTTP connections

## UI Design

- Material Design 3
- Dynamic Color Support (Android 12+)
- Dark Mode Support
- Responsive Grid Layout for game boards

## Developers

- Kapischan Sriganthan
- Loic Bösch
- Francesco Leone
