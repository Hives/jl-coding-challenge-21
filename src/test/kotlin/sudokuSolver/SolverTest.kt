package sudokuSolver

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object SolverTest : Spek({
    describe("Solver") {
        it("gives the right answer for a board that only takes one iteration of the deducer") {
            val board = Board(
                listOf(
                    0, 0, 3, 4, 5, 6, 7, 8, 9,
                    4, 5, 6, 7, 8, 9, 1, 2, 3,
                    7, 8, 9, 1, 2, 3, 4, 5, 6,
                    2, 3, 4, 5, 6, 7, 8, 9, 1,
                    5, 6, 7, 8, 9, 1, 2, 3, 4,
                    8, 9, 1, 2, 3, 4, 5, 6, 7,
                    3, 4, 5, 6, 7, 8, 9, 1, 2,
                    6, 7, 8, 9, 1, 2, 3, 4, 5,
                    9, 1, 2, 3, 4, 5, 6, 7, 8
                )
            )
            assertThat(solve(board)).isEqualTo(completeBoard)
        }

        it("gives the right answer for a board that takes two iterations of the deducer") {
            val board = Board(
                listOf(
                    0, 2, 3, 4, 5, 6, 7, 8, 0,
                    4, 5, 6, 7, 8, 9, 1, 2, 3,
                    7, 8, 0, 1, 2, 3, 4, 5, 6,
                    2, 3, 4, 5, 6, 7, 8, 9, 1,
                    5, 6, 7, 8, 9, 1, 2, 3, 4,
                    8, 9, 1, 2, 3, 4, 5, 6, 7,
                    3, 4, 5, 6, 7, 8, 9, 1, 2,
                    6, 7, 8, 9, 1, 2, 3, 4, 5,
                    0, 1, 2, 3, 4, 5, 6, 7, 8
                )
            )
            assertThat(solve(board)).isEqualTo(completeBoard)
        }

        it("gives the right answer for a board that takes multiple iterations of the deducer") {
            val board = Board(
                listOf(
                    7, 0, 9, 0, 0, 2, 6, 8, 0,
                    0, 0, 2, 0, 5, 0, 7, 0, 4,
                    0, 0, 0, 0, 0, 0, 2, 0, 0,
                    1, 9, 0, 0, 0, 7, 0, 6, 0,
                    8, 6, 7, 1, 9, 5, 0, 4, 0,
                    5, 0, 4, 0, 0, 0, 0, 9, 0,
                    4, 3, 5, 7, 8, 0, 0, 2, 0,
                    0, 0, 6, 4, 0, 0, 0, 0, 1,
                    9, 8, 0, 5, 0, 6, 0, 0, 3
                )
            )
            val solution = Board(
                listOf(
                    7, 4, 9, 3, 1, 2, 6, 8, 5,
                    6, 1, 2, 9, 5, 8, 7, 3, 4,
                    3, 5, 8, 6, 7, 4, 2, 1, 9,
                    1, 9, 3, 2, 4, 7, 5, 6, 8,
                    8, 6, 7, 1, 9, 5, 3, 4, 2,
                    5, 2, 4, 8, 6, 3, 1, 9, 7,
                    4, 3, 5, 7, 8, 1, 9, 2, 6,
                    2, 7, 6, 4, 3, 9, 8, 5, 1,
                    9, 8, 1, 5, 2, 6, 4, 7, 3
                )
            )
            assertThat(solve(board)).isEqualTo(solution)
        }
    }
})