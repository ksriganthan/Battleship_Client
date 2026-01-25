package ch.kapischan_sriganthan.battleship_client

sealed class Screen(val route: String, val label: String, val icon: String) {
    object Login : Screen("login", "Login", "👤")
    object Fire : Screen("fire", "Angriff", "🎯")
    object Enemy : Screen("enemy", "Abfrage", "🛡️")
}
