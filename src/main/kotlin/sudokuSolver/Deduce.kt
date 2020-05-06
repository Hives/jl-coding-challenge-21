package sudokuSolver

tailrec fun deduceTilICantNoMore(board: Board): Board {
    val newBoard = board.deduce()
    return if (newBoard == board) {
        board
    } else {
        deduceTilICantNoMore(newBoard)
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