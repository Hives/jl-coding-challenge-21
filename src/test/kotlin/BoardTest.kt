import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object BoardTest : Spek({
    describe("Methods to get rows, columns and subgrids") {
        val board = Board((1..81).toList())

        it("can get the row containing a given square") {
            val expectedRow3 = listOf(19, 20, 21, 22, 23, 24, 25, 26, 27)
            assertThat(board.getRowContaining(19)).isEqualTo(expectedRow3)
        }

        it("can get the column containing a given square") {
            val expectedCol3 = listOf(3, 12, 21, 30, 39, 48, 57, 66, 75)
            assertThat(board.getColumnContaining(2)).isEqualTo(expectedCol3)
        }

         it("can get a sub grid containing a given square") {
            val expectedMiddleSubGrid = listOf(31, 32, 33, 40, 41, 42, 49, 50, 51)
            assertThat(board.getSubGridContaining(40)).isEqualTo(expectedMiddleSubGrid)
        }
    }
})