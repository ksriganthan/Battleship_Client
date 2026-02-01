#  Battleship Client

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
