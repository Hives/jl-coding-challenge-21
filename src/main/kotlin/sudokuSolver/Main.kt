package sudokuSolver

fun main() {

}

fun solve(board: Board): Board {
    val deduced = deduceUntilExhausted(board)
    return deduced
}

tailrec fun deduceUntilExhausted(board: Board): Board {
    val deduced = board.deduce()
    return if (board == deduced) {
        board
    } else {
        deduceUntilExhausted(deduced)
    }
}

data class Guess(val board: Board, val index: Int)

fun guess(board: Board): Guess {
    val guessedIndex = board.unsolvedSquareWithFewestPossibilities()
    val guessedValue = board.getPossibilitiesFor(guessedIndex).min()
        ?: throw Error("The square we tried to guess had no possible solutions")
    val newBoard =
        Board(board.squares.mapIndexed { index, value ->
            if (index == guessedIndex) {
                guessedValue
            } else {
                value
            }
        })
    return Guess(newBoard, guessedIndex)
}

fun Board.deduce(): Board =
    Board(this.squares.mapIndexed { index, value ->
        if (value == 0) {
            val unusedNumbers = (1..9).toList().filter { !(this.getPeersFor(index)).contains(it) }

            if (unusedNumbers.size == 1) {
                unusedNumbers.single()
            } else {
                0
            }
        } else {
            value
        }
    })

