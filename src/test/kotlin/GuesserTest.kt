import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object GuesserTest : Spek({
    describe("Guesser") {
        it("guesses on an empty board") {
            val board = Board(
                listOf(
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0
                )
            )
            assertThat(guess(board).index).isEqualTo(0)
            assertThat(guess(board).board.squares[0]).isEqualTo(1)
        }

        it("guesses on a non-empty board") {
            val board = Board(
                listOf(
                    1, 2, 3, 4, 5, 6, 7, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0
                )
            )
            assertThat(guess(board).index).isEqualTo(7)
            assertThat(guess(board).board.squares[7]).isEqualTo(8)
        }

        it("guesses on another non-empty board") {
            val board = Board(
                listOf(
                    1, 2, 3, 4, 5, 6, 7, 8, 9,
                    4, 5, 6, 7, 8, 9, 1, 2, 3,
                    7, 8, 9, 0, 0, 0, 4, 5, 6,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0
                )
            )
            assertThat(guess(board).index).isEqualTo(21)
            assertThat(guess(board).board.squares[21]).isEqualTo(1)
        }
    }
})