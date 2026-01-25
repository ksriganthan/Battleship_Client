package ch.kapischan_sriganthan.battleship_client

import android.os.Handler
import android.os.Looper
import ch.kapischan_sriganthan.battleship_client.Ship
import org.json.JSONArray
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

fun joinGame(player: String, gameKey: String, ships: List<Ship>, callback: (String) -> Unit) {
    Thread {
        try {
            val url = URL("http://javaprojects.ch:50003/game/join")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.doOutput = true

            val shipArray = JSONArray()
            ships.forEach {
                shipArray.put(JSONObject().apply {
                    put("ship", it.name)
                    put("x", it.x)
                    put("y", it.y)
                    put("orientation", it.orientation)
                })
            }

            val json = JSONObject().apply {
                put("player", player)
                put("gamekey", gameKey)
                put("ships", shipArray)
            }

            val writer = OutputStreamWriter(connection.outputStream)
            writer.write(json.toString())
            writer.flush()

            val response = connection.inputStream.bufferedReader().readText()
            Handler(Looper.getMainLooper()).post {
                callback("✅ Join erfolgreich: $response")
            }

        } catch (e: Exception) {
            Handler(Looper.getMainLooper()).post {
                callback("❌ Fehler beim Join: ${e.message}")
            }
        }
    }.start()
}

fun fireAtEnemy(player: String, gameKey: String, x: Int, y: Int, callback: (String) -> Unit) {
    Thread {
        try {
            val url = URL("http://javaprojects.ch:50003/game/fire")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.doOutput = true

            val json = JSONObject().apply {
                put("player", player)
                put("gamekey", gameKey)
                put("x", x)
                put("y", y)
            }

            val writer = OutputStreamWriter(connection.outputStream)
            writer.write(json.toString())
            writer.flush()

            val response = connection.inputStream.bufferedReader().readText()
            Handler(Looper.getMainLooper()).post {
                callback("🎯 Antwort: $response")
            }

        } catch (e: Exception) {
            Handler(Looper.getMainLooper()).post {
                callback("❌ Fehler beim Angriff: ${e.message}")
            }
        }
    }.start()
}

fun checkEnemyFire(player: String, gameKey: String, callback: (String) -> Unit) {
    Thread {
        try {
            val url = URL("http://brad-home.ch:50003/game/enemyFire")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.doOutput = true

            val json = JSONObject().apply {
                put("player", player)
                put("gamekey", gameKey)
            }

            val writer = OutputStreamWriter(connection.outputStream)
            writer.write(json.toString())
            writer.flush()

            val response = connection.inputStream.bufferedReader().readText()
            println("📦 Serverantwort (EnemyFire): $response")
            Handler(Looper.getMainLooper()).post {
                callback("📡 Gegnerischer Schuss: $response")
            }

        } catch (e: Exception) {
            Handler(Looper.getMainLooper()).post {
                callback("❌ Fehler beim Abfragen: ${e.message}")
            }
        }

    }.start()
}
