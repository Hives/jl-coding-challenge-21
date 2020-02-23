package sudokuSolver

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object UtilTest : Spek({
    describe("'update' for immutable lists'") {
        it("can update a list of ints") {
            assertThat(listOf(1, 2, 3).update(0, 99)).isEqualTo(listOf(99, 2, 3))
        }

        it("returns the original list of index out of bounds") {
            assertThat(listOf(1, 2, 3).update(3, 99)).isEqualTo(listOf(1, 2, 3))
        }

        it("can update a list of strings") {
            assertThat(
                listOf("one", "two", "three").update(0, "ninetynine")
            ).isEqualTo(listOf("ninetynine", "two", "three"))
        }
    }

    describe("'add' for immutable lists") {
        it("returns a new list with the element inserted") {
            assertThat(listOf(1,1,1,1).add(2, 9)).isEqualTo(listOf(1,1,9,1,1))
        }
        it("can operate on a string") {
            assertThat(listOf("0","0","0","0").add(2, "|")).isEqualTo(listOf("0","0","|","0","0"))
        }
    }
})