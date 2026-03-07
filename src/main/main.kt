package src.main

import java.lang.Thread

fun main() {
  val board = Board(5, 5)
  while (true) {
    board.update()
    board.display()
    Thread.sleep(1_000L)
  }
}
