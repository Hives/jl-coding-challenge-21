package sudokuSolver

fun solve(board: Board): Board {
    val initialHistory = listOf(Guess(board, -1))
    val history = deduceAndGuessUnlessSolved(initialHistory)
    return history.last().board
}

typealias History = List<Guess>

private tailrec fun deduceAndGuessUnlessSolved(history: History): History =
    if (history.isSolved()) {
        history
    } else {
        val newHistory = deduceAndGuess(history)
        deduceAndGuessUnlessSolved(newHistory)
    }

private fun deduceAndGuess(history: History): History = try {
    val deduction = deduceUntilExhausted(history.last().board)

    if (deduction.isSolution()) {
        history + listOf(Guess(deduction, -1))
    } else {
        history + listOf(makeAGuess(deduction))
    }
} catch(e: Error) {
    modifyLastGuess(history)
}

private fun History.isSolved() = this.last().board.isSolution()