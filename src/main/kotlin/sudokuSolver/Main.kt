package sudokuSolver

fun main() {

}

fun solve(board: Board): Board {
    val history = listOf(Guess(board, -1))
    val finalHistory = bar(history)
    println("solution:")
    finalHistory.last().board.print()
    return finalHistory.last().board
}

typealias History = List<Guess>

private tailrec fun bar(history: History): History =
    if (history.last().board.isSolution()) {
        history
    } else {
        bar(foo(history))
    }

private fun foo(history: History): History = try {
    val newBoard = deduceUntilExhausted(history.last().board)
    println("we got here by deducing:")
    newBoard.print()

    if (newBoard.isSolution()) {
        history + listOf(Guess(newBoard, -1))
    } else {
        val nextGuess = guess(newBoard)
        println("index of guess: ${nextGuess.index}")
        history + listOf(nextGuess)
    }
} catch(e: Error) {
    println("a guess was bad")
    modifyLastGuess(history)
}