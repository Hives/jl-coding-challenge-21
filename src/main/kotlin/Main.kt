fun main() {
    println("hello")
}

fun validate(board: Board): Boolean =
    board.squares
        .filterIndexed { index, value ->
            board.getPeers(index).contains(value)
        }.isEmpty()

fun deduce(board: Board): Board = Board(board.squares.mapIndexed { index, value ->
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



//data class Guess(val board: Board, val index: Int)

//fun makeGuess(board: Board): Guess {
//    board.squares.map {
//        if (it == 0) {
//            val peers = board.getPeers(it)
////            if (peers !)
//        }
//    }
//}