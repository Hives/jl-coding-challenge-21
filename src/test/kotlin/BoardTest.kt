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
            assertThat(board.getPeers(23)).isEqualTo(setOf(1,2,3,4,6,7))
        }
    }
})