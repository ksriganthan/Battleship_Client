package ch.kapischan_sriganthan.battleship_client

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun EnemyScreen(
    player: String,
    gameKey: String,
    navController: NavController,
    viewModel: GameViewModel
) {
    var status by remember { mutableStateOf("🧭 Lade Gegner-Angriffe...") }

    // ✅ Automatisch alle gegnerischen Schüsse abrufen
    LaunchedEffect(Unit) {
        repeat(5) {
            checkEnemyFire(player, gameKey) { response ->
                val x = Regex("\"x\":(\\d+)").find(response)?.groupValues?.get(1)?.toIntOrNull()
                val y = Regex("\"y\":(\\d+)").find(response)?.groupValues?.get(1)?.toIntOrNull()
                val hit = Regex("\"hit\":(true|false)").find(response)?.groupValues?.get(1)?.toBooleanStrictOrNull() ?: false


                if (x != null && y != null && hit != null) {
                    if (viewModel.enemyShots.none { it.x == x && it.y == y }) {
                        println("💥 Treffer empfangen bei x=$x, y=$y, hit=$hit")
                        viewModel.addEnemyShot(x, y, hit)
                        status = "📡 Neuer Schuss bei ($x, $y): ${if (hit) "Treffer" else "Fehlschuss"}"
                    }
                } else {
                    status = "✔️ Alle Angriffe geladen"
                }
            }
            delay(500)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("🛡️ Gegnerischer Schuss", style = MaterialTheme.typography.titleLarge)
        Text(status)

        Text("🗺️ Mein Spielfeld", style = MaterialTheme.typography.titleMedium)

        Column {
            for (yIndex in 0..9) {
                Row {
                    for (xIndex in 0..9) {
                        val shot = viewModel.enemyShots.find { it.x == xIndex && it.y == yIndex }

                        val shipHere = viewModel.ownShips.find { ship ->
                            val length = when (ship.name) {
                                "Carrier" -> 5
                                "Battleship" -> 4
                                "Submarine" -> 3
                                "Destroyer" -> 3
                                "PatrolBoat" -> 2
                                else -> 1
                            }

                            if (ship.orientation == "horizontal") {
                                yIndex == ship.y && xIndex in ship.x until (ship.x + length)
                            } else {
                                xIndex == ship.x && yIndex in ship.y until (ship.y + length)
                            }
                        }

                        // ✅ Farbe je nach Trefferstatus setzen
                        val bgColor = when (shot?.hit) {
                            true -> MaterialTheme.colorScheme.error      // 🔴 Treffer
                            false -> MaterialTheme.colorScheme.primary   // 🔵 daneben
                            null -> MaterialTheme.colorScheme.surfaceVariant // ⚪ neutral
                        }

                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .padding(1.dp)
                                .background(bgColor),
                            contentAlignment = Alignment.Center
                        ) {
                            if (shipHere != null) {
                                Text(shipHere.name.first().toString())
                            }
                        }
                    }
                }
            }
        }

    }
}
