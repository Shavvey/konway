package src.main

class Board(var rows: Int, var cols: Int) {
  var tiles: Array<Array<State>> = Array(rows) { Array(cols) { State.DEAD } }

  init {
    tiles[1][2] = State.ALIVE
    tiles[2][2] = State.ALIVE
    tiles[3][2] = State.ALIVE
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
    val neighbors_arr = get_neighbors()
    for (i in 0..rows - 1) {
      for (j in 0..cols - 1) {
        // get current state and number of alive neighbors
        val state = tiles[i][j]
        val neighbors = neighbors_arr[i][j]
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

  // return all the neighbors for each cell in board
  fun get_neighbors(): Array<Array<Int>> {
    var neighbors_arr: Array<Array<Int>> = Array(rows) { Array(cols) { 0 } }
    for (i in 0..rows - 1) {
      for (j in 0..cols - 1) {
        if (tiles[i][j] == State.ALIVE) neighbors_arr[i][j] -= 1
        for (r in -1..1) {
          for (s in -1..1) {
            val x = mod(i + r, rows)
            val y = mod(j + s, cols)
            if (tiles[x][y] == State.ALIVE) {
              neighbors_arr[i][j] += 1
            }
          }
        }
      }
    }
    return neighbors_arr
  }
}

fun mod(a: Int, b: Int): Int {
  return (a % b + b) % b
}

fun print_neighbor_arr(neighbors_arr: Array<Array<Int>>) {
  neighbors_arr.forEach {
    print("[")
    it.forEach { print(it) }
    println("]")
  }
}
