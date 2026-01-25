package ch.kapischan_sriganthan.battleship_client

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ch.kapischan_sriganthan.battleship_client.joinGame

@Composable
fun LoginScreen(
    viewModel: GameViewModel,  // 🆕 ViewModel wird übergeben
    onLoginSuccess: (String, String) -> Unit
) {
    var player by remember { mutableStateOf("") }
    var gameKey by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("🚀 Bitte Daten eingeben") }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("🎮 Login", style = MaterialTheme.typography.titleLarge)
        TextField(value = player, onValueChange = { player = it }, label = { Text("Spielername") })
        TextField(value = gameKey, onValueChange = { gameKey = it }, label = { Text("Game Key") })

        Button(onClick = {
            if (player.length < 3 || gameKey.length < 3) {
                status = "❌ Name und Key müssen ≥ 3 Zeichen sein"
                return@Button
            }

            status = "⏳ Verbinde..."

            val ships = listOf(
                Ship("Carrier", 0, 3, "horizontal"),
                Ship("Battleship", 0, 4, "horizontal"),
                Ship("Submarine", 0, 5, "horizontal"),
                Ship("Destroyer", 0, 6, "horizontal"),
                Ship("PatrolBoat", 0, 7, "horizontal")
            )

            joinGame(player, gameKey, ships) { response ->
                status = response
                if (response.startsWith("✅")) {
                    viewModel.setShips(ships)               // 🆕 Schiffe merken
                    onLoginSuccess(player, gameKey)         // Weiter zur App
                }
            }
        }) {
            Text("Starten")
        }

        Text(status)
    }
}
