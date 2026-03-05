package src.main

import kotlin.random.Random

class Board(var rows: Int, var cols: Int, var numPoints: Int = 3 * rows) {
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
    print(ANSICodes.CLEAR_SCREEN.code)
    for (i in 0..rows - 1) {
      for (j in 0..cols - 1) {
        val state = tiles[i][j]
        when (state) {
          State.ALIVE -> {
            print(ANSICodes.YELLOW_BEGIN.code)
            print(state.str)
            print(ANSICodes.COLOR_END.code)
          }
          State.DEAD -> {
            print(state.str)
          }
        }
      }
      println()
    }
  }

  fun update() {
    for (i in 0..rows - 1) {
      for (j in 0..cols - 1) {
        // get current state and number of alive neighbors
        val state = tiles[i][j]
        val neighbors = neighbors(i, j)
        when (state) {
          State.ALIVE -> {
            // any alive cell with more than 3 neighbors dies
            if (neighbors > 3) {
              tiles[i][j] = State.DEAD
            }
            // any alive cell with less than 2 neighbors also dies
            if (neighbors < 2) {
              tiles[i][j] = State.DEAD
            }
          }
          State.DEAD -> {
            // any dead cell with become alive if they have exactly three neighbors
            if (neighbors == 3) {
              tiles[i][j] = State.ALIVE
            }
          }
        }
      }
    }
  }

  // given coords of a cell find all alive neighbors
  fun neighbors(x: Int, y: Int): Int {
    var neighbors: Int = 0
    for (dx in -1..1) {
      for (dy in -1..1) {
        val i = mod(x + dx, rows)
        val j = mod(y + dy, cols)
        if (tiles[i][j] == State.ALIVE) neighbors++
      }
    }
    return neighbors
  }
}

fun mod(a: Int, b: Int): Int {
  return (a % b + b) % b
}
