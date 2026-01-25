package ch.kapischan_sriganthan.battleship_client

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ch.kapischan_sriganthan.battleship_client.FieldResult

@Composable
fun FireScreen(
    player: String,
    gameKey: String,
    navController: NavController,
    viewModel: GameViewModel
) {
    var x by remember { mutableStateOf("") }
    var y by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("🕹️ Angriff bereit") }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("🎯 Angriff starten", style = MaterialTheme.typography.titleLarge)

        TextField(value = x, onValueChange = { x = it }, label = { Text("X-Koordinate (0–9)") })
        TextField(value = y, onValueChange = { y = it }, label = { Text("Y-Koordinate (0–9)") })

        Button(onClick = {
            val xInt = x.toIntOrNull()
            val yInt = y.toIntOrNull()

            if (xInt == null || yInt == null || xInt !in 0..9 || yInt !in 0..9) {
                result = "❌ Ungültige Koordinaten"
                return@Button
            }

            result = "📡 Schuss wird gesendet..."

            fireAtEnemy(player, gameKey, xInt, yInt) { res ->
                result = res
                if (res.contains("\"hit\":true")) {
                    viewModel.addShot(xInt, yInt, true)
                } else if (res.contains("\"hit\":false")) {
                    viewModel.addShot(xInt, yInt, false)
                }
            }
        }) {
            Text("Feuer frei!")
        }

        Text(result)

        // 🧱 Nur Schüsse anzeigen (ohne Schiffe)
        Text("🗺️ Übersicht", style = MaterialTheme.typography.titleMedium)
        Column {
            for (yIndex in 0..9) {
                Row {
                    for (xIndex in 0..9) {
                        val field = viewModel.shotHistory.find { it: FieldResult ->
                            it.x == xIndex && it.y == yIndex
                        }

                        val hit: Boolean? = field?.hit

                        val bgColor = when (hit) {
                            true -> MaterialTheme.colorScheme.error
                            false -> MaterialTheme.colorScheme.primary
                            null -> MaterialTheme.colorScheme.surfaceVariant
                        }

                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .padding(1.dp)
                                .background(bgColor)
                        )
                    }
                }
            }
        }


    }
}
