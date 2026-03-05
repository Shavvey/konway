package src.main

import kotlin.random.Random

class Board(var rows: Int, var cols: Int, var numPoints: Int = 3) {
  var tiles: Array<Array<State>> = Array(rows) { Array(cols) { State.DEAD } }

  init {
    // make random konway cells alive
    for (p in 0..numPoints) {
      var i = Random.Default.nextInt() % rows
      var j = Random.Default.nextInt() % cols
      i = if (i > 0) i else -i
      j = if (j > 0) j else -j
      tiles[i][j] = State.ALIVE
    }
  }

  fun display() {
    for (i in 0..rows - 1) {
      for (j in 0..cols - 1) {
        print(tiles[i][j].str)
      }
      println()
    }
  }
}
