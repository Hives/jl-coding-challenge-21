package sudokuSolver

data class Guess(val board: Board, val index: Int)

fun guess(board: Board): Guess {
    val guessedIndex = board.unsolvedSquareWithFewestPossibilities()
    println("guessedIndex: $guessedIndex")
    println("possibilities: ${board.getPossibilitiesFor(guessedIndex)}")
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

tailrec fun modifyLastGuess(history: History): History {
    val lastGuess = history.last()
    val lastGuessValue = lastGuess.board.squares[lastGuess.index]
    val possibilities = lastGuess.board.getPossibilitiesFor(lastGuess.index)

    return if (lastGuessValue != possibilities.last()) {
        val newGuessValue = possibilities.getNextValueAfter(lastGuessValue)
        val newBoard = Board(
            lastGuess.board.squares.update(lastGuess.index, newGuessValue)
        )
        history.dropLast(1) + listOf(Guess(newBoard, lastGuess.index))
    } else {
        modifyLastGuess(history.dropLast(1))
    }
}

private fun List<Int>.getNextValueAfter(value: Int): Int {
    val index = this.indexOf(value)
    return this[index + 1]
}
