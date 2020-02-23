package sudokuSolver

tailrec fun deduceUntilExhausted(board: Board): Board {
    val deduced = board.deduce()
    return if (board == deduced) {
        board
    } else {
        deduceUntilExhausted(deduced)
    }
}

fun Board.deduce(): Board {
    val newBoard = Board(this.squares.mapIndexed { index, value ->
        if (value == 0 && getPossibilitiesFor(index).size == 1) {
            getPossibilitiesFor(index).single()
        } else {
            value
        }
    })

    if (newBoard.isInvalid()) throw Error("Board is not valid")

    return newBoard
}