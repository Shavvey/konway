package src.main

class Board(var rows: Int, var cols: Int) {
  var initialState: State = State.ALIVE
  var tiles: Array<Array<State>> = Array(rows) { Array(cols) { initialState } }

  fun display() {
    for (i in 0..rows - 1) {
      for (j in 0..cols - 1) {
        print(tiles[i][j].str)
      }
      println()
    }
  }
}
