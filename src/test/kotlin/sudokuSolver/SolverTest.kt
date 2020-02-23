package sudokuSolver

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object SolverTest : Spek({
    describe("Solver") {
        it("gives the right answer for a board that only takes one iteration of the deducer") {
            val board = Board(
                completeBoard.squares
                    .update(0,0)
                    .update(1,0)
            )
            assertThat(solve(board)).isEqualTo(completeBoard)
        }
    }
})