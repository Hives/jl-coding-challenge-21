data class Board(val squares: List<Int>) {
    fun getRowContaining(requestedPosition: Int) =
        squares.filterIndexed { index, _ -> rowIndexOf(index) == rowIndexOf(requestedPosition) }

    fun getColumnContaining(requestedPosition: Int) =
        squares.filterIndexed { index, _ -> colIndexOf(index) == colIndexOf(requestedPosition) }

    fun getSubGridContaining(requestedPosition: Int) =
        squares.filterIndexed { index, _ -> subGridIndexOf(index) == subGridIndexOf(requestedPosition) }

    private fun rowIndexOf(position: Int) = position / 9
    private fun colIndexOf(position: Int) = position % 9
    private fun subGridIndexOf(position: Int) = (colIndexOf(position) / 3) + (3 * (rowIndexOf(position) / 3))
}

fun deducer(board: Board): Board = Board(board.squares.mapIndexed { index, value ->
    if (value == 0) {
        val numbersInRow = board.getRowContaining(index).filter { it != 0 }
        val numbersInColumn = board.getColumnContaining(index).filter { it != 0 }
        val numbersInSubGrid = board.getSubGridContaining(index).filter { it != 0 }

        val unusedNumbers =
            (1..9).toList().filter { !(numbersInRow + numbersInColumn + numbersInSubGrid).contains(it) }

        if (unusedNumbers.size == 1) {
            unusedNumbers.single()
        } else {
            0
        }
    } else {
        value
    }
})