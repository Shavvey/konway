package src.main

import java.lang.Thread
import java.util.*

fun main() {
  val board = Board(5, 5)
  do {
    var key = '!'
    // If no input is found, continue updating and displaying board
    board.update()
    board.display()
    // Non-blocking call that estimates bytes to be read from stdin
    if (System.`in`.available() > 0) {
      key = System.`in`.read().toChar()
    }
    Thread.sleep(1_000L)
  } while (key != 'q')
}
