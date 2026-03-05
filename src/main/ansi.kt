enum class ANSICodes(val code: String) {
  CLEAR_SCREEN("\u001b[H\u001b[2J"), // clear out the screens
  YELLOW_BEGIN("\u001b[33m"),
  COLOR_END("\u001b[0m"),
}
