import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object ValidatorTest : Spek({
    describe("Validator") {
        it("empty board is ok") {
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
            assertThat(validate(board)).isTrue()
        }

        it("sample board is ok") {
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
            assertThat(validate(board)).isTrue()
        }

        it("board with duplicates in a row is not ok") {
            val board = Board(
                listOf(
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    7, 0, 0, 0, 0, 0, 7, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0
                )
            )
            assertThat(validate(board)).isFalse()
        }

        it("board with duplicates in a column is not ok") {
            val board = Board(
                listOf(
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 3, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 3, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0
                )
            )
            assertThat(validate(board)).isFalse()
        }

        it("board with duplicates in a sub grid is not ok") {
            val board = Board(
                listOf(
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 4, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    4, 0, 0, 0, 0, 0, 0, 0, 0
                )
            )
            assertThat(validate(board)).isFalse()
        }

    }
})