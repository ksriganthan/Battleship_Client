package ch.kapischan_sriganthan.battleship_client

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import ch.kapischan_sriganthan.battleship_client.*
import ch.kapischan_sriganthan.battleship_client.ui.theme.Battleship_ClientTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Battleship_ClientTheme {
                val navController = rememberNavController()
                var player by remember { mutableStateOf("") }
                var gameKey by remember { mutableStateOf("") }
                val viewModel = remember { GameViewModel() }


                val items = listOf(
                    Screen.Login,
                    Screen.Fire,
                    Screen.Enemy
                )

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            val currentDestination = navController.currentBackStackEntryAsState().value?.destination
                            items.forEach { screen ->
                                NavigationBarItem(
                                    icon = { Text(screen.icon) },
                                    label = { Text(screen.label) },
                                    selected = currentDestination?.route == screen.route,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Login.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.Login.route) {
                            LoginScreen(viewModel) { p, g ->
                                player = p
                                gameKey = g
                            }
                        }
                        composable(Screen.Fire.route) {
                            FireScreen(player, gameKey, navController, viewModel)
                        }
                        composable(Screen.Enemy.route) {
                            EnemyScreen(player, gameKey, navController, viewModel)
                        }
                    }
                }
            }
        }
    }
}
