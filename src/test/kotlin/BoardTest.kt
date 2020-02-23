import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object BoardTest : Spek({
    describe("Methods to get rows, columns and subgrids") {
        it("can get the row containing a given square") {
            val board = Board(
                listOf(
                    1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1,
                    2, 3, 4, 2, 3, 4, 2, 3, 4,
                    1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1,
                    7, 8, 9, 7, 8, 9, 7, 8, 9,
                    1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1
                )
            )

            assertThat(board.getRowContaining(18)).isEqualTo(listOf(2, 3, 4, 2, 3, 4, 2, 3, 4))
            assertThat(board.getRowContaining(26)).isEqualTo(listOf(2, 3, 4, 2, 3, 4, 2, 3, 4))

            assertThat(board.getRowContaining(54)).isEqualTo(listOf(7, 8, 9, 7, 8, 9, 7, 8, 9))
            assertThat(board.getRowContaining(62)).isEqualTo(listOf(7, 8, 9, 7, 8, 9, 7, 8, 9))
        }

        it("can get the column containing a given square") {
            val board = Board(
                listOf(
                    1, 2, 1, 1, 1, 1, 7, 1, 1,
                    1, 3, 1, 1, 1, 1, 8, 1, 1,
                    1, 4, 1, 1, 1, 1, 9, 1, 1,
                    1, 2, 1, 1, 1, 1, 7, 1, 1,
                    1, 3, 1, 1, 1, 1, 8, 1, 1,
                    1, 4, 1, 1, 1, 1, 9, 1, 1,
                    1, 2, 1, 1, 1, 1, 7, 1, 1,
                    1, 3, 1, 1, 1, 1, 8, 1, 1,
                    1, 4, 1, 1, 1, 1, 9, 1, 1
                )
            )

            assertThat(board.getColumnContaining(1)).isEqualTo(listOf(2, 3, 4, 2, 3, 4, 2, 3, 4))
            assertThat(board.getColumnContaining(73)).isEqualTo(listOf(2, 3, 4, 2, 3, 4, 2, 3, 4))

            assertThat(board.getColumnContaining(6)).isEqualTo(listOf(7, 8, 9, 7, 8, 9, 7, 8, 9))
            assertThat(board.getColumnContaining(78)).isEqualTo(listOf(7, 8, 9, 7, 8, 9, 7, 8, 9))
        }

        it("can get a sub grid containing a given square") {
            val board = Board(
                listOf(
                    1, 1, 1, 1, 2, 3, 1, 1, 1,
                    1, 1, 1, 4, 5, 6, 1, 1, 1,
                    1, 1, 1, 7, 8, 9, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 9, 8, 7,
                    1, 1, 1, 1, 1, 1, 6, 5, 4,
                    1, 1, 1, 1, 1, 1, 3, 2, 1
                )
            )

            assertThat(board.getSubGridContaining(3)).isEqualTo(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9))
            assertThat(board.getSubGridContaining(23)).isEqualTo(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9))

            assertThat(board.getSubGridContaining(60)).isEqualTo(listOf(9, 8, 7, 6, 5, 4, 3, 2, 1))
            assertThat(board.getSubGridContaining(80)).isEqualTo(listOf(9, 8, 7, 6, 5, 4, 3, 2, 1))
        }
    }
})