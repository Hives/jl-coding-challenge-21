import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object DeducerTest : Spek({
    describe("deducer") {
        context("If it can't make a deduction") {
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
            it("returns the same board") {
                assertThat(deduce(board)).isEqualTo(board)
            }
        }

        describe("rows") {
            context("If every square but one in the first row is complete") {
                val board = Board(
                    listOf(
                        1, 2, 3, 4, 0, 6, 7, 8, 9,
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
                it("it returns a board with the first row completed") {
                    val expectedBoard = Board(
                        listOf(
                            1, 2, 3, 4, 5, 6, 7, 8, 9,
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
                    assertThat(deduce(board)).isEqualTo(expectedBoard)
                }
            }

            context("If every square but one in the last row is complete") {
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
                        9, 8, 7, 6, 5, 4, 3, 0, 1
                    )
                )
                it("it returns a board with the first row completed") {
                    val expectedBoard = Board(
                        listOf(
                            0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0,
                            9, 8, 7, 6, 5, 4, 3, 2, 1
                        )
                    )
                    assertThat(deduce(board)).isEqualTo(expectedBoard)
                }
            }
        }

        describe("columns") {
            context("If every square but one in the first column is complete") {
                val board = Board(
                    listOf(
                        1, 0, 0, 0, 0, 0, 0, 0, 0,
                        2, 0, 0, 0, 0, 0, 0, 0, 0,
                        3, 0, 0, 0, 0, 0, 0, 0, 0,
                        4, 0, 0, 0, 0, 0, 0, 0, 0,
                        5, 0, 0, 0, 0, 0, 0, 0, 0,
                        6, 0, 0, 0, 0, 0, 0, 0, 0,
                        7, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        9, 0, 0, 0, 0, 0, 0, 0, 0
                    )
                )
                it("it returns a board with the first column completed") {
                    val expectedBoard = Board(
                        listOf(
                            1, 0, 0, 0, 0, 0, 0, 0, 0,
                            2, 0, 0, 0, 0, 0, 0, 0, 0,
                            3, 0, 0, 0, 0, 0, 0, 0, 0,
                            4, 0, 0, 0, 0, 0, 0, 0, 0,
                            5, 0, 0, 0, 0, 0, 0, 0, 0,
                            6, 0, 0, 0, 0, 0, 0, 0, 0,
                            7, 0, 0, 0, 0, 0, 0, 0, 0,
                            8, 0, 0, 0, 0, 0, 0, 0, 0,
                            9, 0, 0, 0, 0, 0, 0, 0, 0
                        )
                    )
                    assertThat(deduce(board)).isEqualTo(expectedBoard)
                }
            }

            context("If every square but one in the last column is complete") {
                val board = Board(
                    listOf(
                        0, 0, 0, 0, 0, 0, 0, 0, 9,
                        0, 0, 0, 0, 0, 0, 0, 0, 8,
                        0, 0, 0, 0, 0, 0, 0, 0, 7,
                        0, 0, 0, 0, 0, 0, 0, 0, 6,
                        0, 0, 0, 0, 0, 0, 0, 0, 5,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 3,
                        0, 0, 0, 0, 0, 0, 0, 0, 2,
                        0, 0, 0, 0, 0, 0, 0, 0, 1
                    )
                )
                it("it returns a board with the last column completed") {
                    val expectedBoard = Board(
                        listOf(
                            0, 0, 0, 0, 0, 0, 0, 0, 9,
                            0, 0, 0, 0, 0, 0, 0, 0, 8,
                            0, 0, 0, 0, 0, 0, 0, 0, 7,
                            0, 0, 0, 0, 0, 0, 0, 0, 6,
                            0, 0, 0, 0, 0, 0, 0, 0, 5,
                            0, 0, 0, 0, 0, 0, 0, 0, 4,
                            0, 0, 0, 0, 0, 0, 0, 0, 3,
                            0, 0, 0, 0, 0, 0, 0, 0, 2,
                            0, 0, 0, 0, 0, 0, 0, 0, 1
                        )
                    )
                    assertThat(deduce(board)).isEqualTo(expectedBoard)
                }
            }
        }

        describe("subgrids") {
            context("If every square but one in the first subgrid is complete") {
                val board = Board(
                    listOf(
                        1, 2, 3, 0, 0, 0, 0, 0, 0,
                        0, 5, 6, 0, 0, 0, 0, 0, 0,
                        7, 8, 9, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0
                    )
                )
                it("it returns a board with the first subgrid completed") {
                    val expectedBoard = Board(
                        listOf(
                            1, 2, 3, 0, 0, 0, 0, 0, 0,
                            4, 5, 6, 0, 0, 0, 0, 0, 0,
                            7, 8, 9, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0
                        )
                    )
                    assertThat(deduce(board)).isEqualTo(expectedBoard)
                }
            }

            context("If every square but one in the last subgrid is complete") {
                val board = Board(
                    listOf(
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 1, 4, 7,
                        0, 0, 0, 0, 0, 0, 2, 0, 8,
                        0, 0, 0, 0, 0, 0, 3, 6, 9
                    )
                )
                it("it returns a board with the first subgrid completed") {
                    val expectedBoard = Board(
                        listOf(
                            0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 1, 4, 7,
                            0, 0, 0, 0, 0, 0, 2, 5 , 8,
                            0, 0, 0, 0, 0, 0, 3, 6, 9
                        )
                    )
                    assertThat(deduce(board)).isEqualTo(expectedBoard)
                }
            }

        }

        describe("rows, columns + subgrids together") {
            context("If a square's row, column and subgrid contain all but one of the numbers 1-9") {
                val board = Board(
                    listOf(
                        0, 0, 0, 0, 3, 0, 0, 0, 0,
                        0, 0, 0, 0, 4, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 6, 0, 7, 0, 0, 0,
                        1, 0, 0, 0, 0, 0, 0, 2, 0,
                        0, 0, 0, 0, 0, 8, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 5, 0, 0, 0, 0
                    )
                )
                it("it returns a board with that square completed") {
                    val expectedBoard = Board(
                        listOf(
                            0, 0, 0, 0, 3, 0, 0, 0, 0,
                            0, 0, 0, 0, 4, 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 6, 0, 7, 0, 0, 0,
                            1, 0, 0, 0, 9, 0, 0, 2, 0,
                            0, 0, 0, 0, 0, 8, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0, 5, 0, 0, 0, 0
                        )
                    )
                    assertThat(deduce(board)).isEqualTo(expectedBoard)
                }
            }

        }
    }
})