import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object BoardTest : Spek({
    describe("Get peers") {
        it("can get the peers of a given square") {
            val board = Board(
                listOf(
                    0, 0, 0, 1, 3, 0, 0, 0, 0,
                    0, 0, 0, 2, 0, 0, 0, 0, 0,
                    1, 2, 4, 0, 0, 9, 0, 0, 3,
                    0, 0, 0, 0, 0, 1, 0, 0, 0,
                    0, 0, 0, 0, 0, 2, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 6, 0, 0, 0,
                    0, 0, 0, 0, 0, 7, 0, 0, 0
                )
            )

            // 23 = col 5, row 2 (0-indexed)
            assertThat(board.getPeersFor(23)).isEqualTo(setOf(1, 2, 3, 4, 6, 7))
        }
    }

    describe("Get possibilities for") {
        it("case 1") {
            val board = Board(
                listOf(
                    0, 0, 1, 2, 3, 4, 5, 6, 7,
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
            assertThat(board.getPossibilitiesFor(0)).isEqualTo(setOf(8, 9))
        }

        it("case 2") {
            val board = Board(
                listOf(
                    0, 0, 1, 2, 3, 4, 5, 6, 7,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    8, 0, 0, 0, 0, 0, 0, 0, 0,
                    9, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0
                )
            )
            assertThat(board.getPossibilitiesFor(0)).isEqualTo(emptySet())
        }
    }

    describe("Get square with fewest possibilities") {
        it("simple case") {
            val board = Board(
                listOf(
                    2, 0, 0, 0, 0, 0, 0, 0, 1,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 3, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0
                )
            )

            assertThat(board.unsolvedSquareWithFewestPossibilities()).isEqualTo(6)
        }
    }
})