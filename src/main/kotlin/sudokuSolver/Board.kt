package sudokuSolver

data class Board(val squares: List<Int>) {

    fun getPossibilitiesFor(index: Int) =
        ((1..9).toSet() - getPeersFor(index)).toList().sorted()

    fun isSolution(): Boolean = !squares.contains(0) && isValid()

    fun isValid(): Boolean = noSquareHasSameValueAsAPeer() && everyEmptySquareHasAPossibleSolution()

    fun isInvalid(): Boolean = !isValid()

    private fun noSquareHasSameValueAsAPeer(): Boolean =
        squares
            .filterIndexed { index, value -> getPeersFor(index).contains(value) }
            .isEmpty()

    private fun everyEmptySquareHasAPossibleSolution(): Boolean =
        squares
            .mapIndexed { index, value -> Pair(index, value) }
            .filter { (_, value) -> value == 0 }
            .all { (index, _) -> getPossibilitiesFor(index).isNotEmpty() }

    fun unsolvedSquareWithFewestPossibilities(): Int =
        squares
            .mapIndexed { index, value -> Pair(index, value) }
            .filter { (_, value) -> value == 0 }
            .minBy { (index, _) -> getPossibilitiesFor(index).size }
            ?.let { (index, _) ->
                if (getPeersFor(index).size == 9) throw Error("This square has NO possibilities?!")
                index
            }
            ?: throw Error("Couldn't find a square with fewest possibilities?!")

    fun getPeersFor(index: Int) =
        (getRowPeers(index) + getColumnPeers(index) + getSubGridPeers(index))
            .filter { it != 0 }.toSet()

    private fun getRowPeers(requestedPosition: Int) =
        squares.filterIndexed { index, _ ->
            rowIndexOf(index) == rowIndexOf(requestedPosition) && index != requestedPosition
        }

    private fun getColumnPeers(requestedPosition: Int) =
        squares.filterIndexed { index, _ ->
            colIndexOf(index) == colIndexOf(requestedPosition) && index != requestedPosition
        }

    private fun getSubGridPeers(requestedPosition: Int) =
        squares.filterIndexed { index, _ ->
            subGridIndexOf(index) == subGridIndexOf(requestedPosition) && index != requestedPosition
        }

    private fun rowIndexOf(position: Int) = position / 9
    private fun colIndexOf(position: Int) = position % 9
    private fun subGridIndexOf(position: Int) = (colIndexOf(position) / 3) + (3 * (rowIndexOf(position) / 3))

    fun print() = run {
        val formattedSquares = squares
            .map { it.toString() }
            .chunked(9)
            .map { it.add(6, "│").add(3, "│") }
            .map { it.joinToString(" ") }
            .add(6, "──────┼───────┼──────")
            .add(3, "──────┼───────┼──────")
            .joinToString("\n", postfix = "\n")

        println(formattedSquares)
    }

}