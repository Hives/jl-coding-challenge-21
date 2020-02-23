package sudokuSolver

fun main() {

}

fun solve(board: Board): Board {
    return deduceUntilExhausted(board)
}