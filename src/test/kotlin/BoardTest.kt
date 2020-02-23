import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object BoardTest : Spek({
    describe("Get peers") {
        it("can get the row containing a given square") {
            val board = Board(
                listOf(
                    0, 0, 0, 1, 3, 0, 0, 0, 0,
                    0, 0, 0, 2, 0, 0, 0, 0, 0,
                    4, 0, 0, 0, 0, 9, 0, 0, 5,
                    0, 0, 0, 0, 0, 6, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 7, 0, 0, 0
                    )
            )

            assertThat(board.getPeers(23)).isEqualTo(setOf(1,2,3,4,5,6,7,9))
        }
    }
})