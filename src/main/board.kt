package src.main

import kotlin.random.Random

class Board(var rows: Int, var cols: Int, var numPoints: Int = 3) {
  var tiles: Array<Array<State>> = Array(rows) { Array(cols) { State.DEAD } }

  init {
    // make random konway cells alive
    for (p in 0..numPoints) {
      // generate random point indices on the board
      var i = Random.Default.nextInt() % rows
      var j = Random.Default.nextInt() % cols
      // clamp to positive vals
      i = if (i > 0) i else -i
      j = if (j > 0) j else -j
      tiles[i][j] = State.ALIVE
    }
  }

  fun display() {
    for (i in 0..rows - 1) {
      for (j in 0..cols - 1) {
        val state = tiles[i][j]
        when (state) {
          State.ALIVE -> {
            print(ANSICodes.YELLOW_BEGIN.code)
            print(state.str)
            print(ANSICodes.YELLOW_END.code)
          }
          State.DEAD -> {
            print(state.str)
          }
        }
      }
      println()
    }
  }
}

fun Mod(a: Int, b: Int): Int {
  return (a % b + b) % b
}
