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