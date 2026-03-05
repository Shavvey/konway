package src.main

import java.lang.Thread

fun main(args: Array<String>) {
  val board = Board(10, 30, 300)
  while (true) {
    board.update()
    board.display()
    // delay update-display loop so you can actually see what is happening
    Thread.sleep(1_000L)
  }
}
