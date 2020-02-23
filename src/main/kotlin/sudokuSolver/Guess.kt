package sudokuSolver

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

tailrec fun modifyLastGuess(history: List<Guess>): List<Guess> {
    val lastGuess = history.last()
    val lastGuessValue = lastGuess.board.squares[lastGuess.index]

    return if (lastGuessValue < 9) {
        val newBoard = Board(
            lastGuess.board.squares.update(lastGuess.index, lastGuessValue + 1)
        )
        history.dropLast(1) + listOf(Guess(newBoard, lastGuess.index))
    } else {
        modifyLastGuess(history.dropLast(1))
    }
}
