package ch.kapischan_sriganthan.battleship_client

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import ch.kapischan_sriganthan.battleship_client.FieldResult

class GameViewModel : ViewModel() {
    val shotHistory = mutableStateListOf<FieldResult>()
    val ownShips = mutableStateListOf<Ship>() //
    val enemyShots = mutableStateListOf<FieldResult>()  // 🆕

    fun addEnemyShot(x: Int, y: Int, hit: Boolean) {
        if (enemyShots.none { it.x == x && it.y == y }) {
            enemyShots.add(FieldResult(x, y, hit))
        }
    }


    fun addShot(x: Int, y: Int, hit: Boolean) {
        if (shotHistory.none { it.x == x && it.y == y }) {
            shotHistory.add(FieldResult(x, y, hit))
        }
    }

    fun setShips(ships: List<Ship>) {
        ownShips.clear()
        ownShips.addAll(ships)
    }

    fun reset() {
        shotHistory.clear()
        enemyShots.clear()
        ownShips.clear()
    }
}