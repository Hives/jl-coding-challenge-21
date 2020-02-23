fun main() {
    println("hello")
}

fun deducer(board: Board): Board = Board(board.squares.mapIndexed { index, value ->
    if (value == 0) {
        val unusedNumbers =
            (1..9).toList().filter { !(board.getPeers(index)).contains(it) }

        if (unusedNumbers.size == 1) {
            unusedNumbers.single()
        } else {
            0
        }
    } else {
        value
    }
})
