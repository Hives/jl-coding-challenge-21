package sudokuSolver

fun main() {
    val puzzle = Board(
        listOf(
            7, 8, 1, 5, 4, 3, 9, 2, 6,
            0, 0, 6, 1, 7, 9, 5, 0, 0,
            9, 5, 4, 6, 2, 8, 7, 3, 1,
            6, 9, 5, 8, 3, 7, 2, 1, 4,
            1, 4, 8, 2, 6, 5, 3, 7, 9,
            3, 2, 7, 9, 1, 4, 8, 0, 0,
            4, 1, 3, 7, 5, 2, 6, 9, 8,
            0, 0, 2, 0, 0, 0, 4, 0, 0,
            5, 7, 9, 4, 8, 6, 1, 0, 3
        )
    )
    val solution = solve(puzzle)
    solution.print()
}