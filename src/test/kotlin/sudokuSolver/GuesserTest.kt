package sudokuSolver

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

    describe("Modify last guess") {
        context("if the value of the last guess is less than 9") {
            val guess1 = Guess(completeBoard, 99)
            val guess2 = Guess(
                Board(
                    listOf(
                        8, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0
                    )
                ),
                0
            )
            val history = listOf(guess1, guess2)
            val modifiedHistory = modifyLastGuess(history)

            it("leaves the previous guess unchanged") {
                assertThat(modifiedHistory.first()).isEqualTo(guess1)
            }

            it("doesn't add another guess") {
                assertThat(modifiedHistory.size).isEqualTo(history.size)
            }

            it("increments the value of the last guess") {
                assertThat(modifiedHistory.last().board.squares[0]).isEqualTo(9)
            }
        }

        context("if the value of the last guess is 9") {
            val guess1 = Guess(completeBoard, 99)
            val guess2 = Guess(
                Board(
                    listOf(
                        8, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0
                    )
                ),
                0
            )
            val guess3 = Guess(
                Board(
                    listOf(
                        8, 9, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0
                    )
                ),
                1
            )
            val history = listOf(guess1, guess2, guess3)
            val modifiedHistory = modifyLastGuess(history)

            it("deletes the last guess") {
                assertThat(modifiedHistory.size).isEqualTo(history.size - 1)
            }
            it("increments the last one before that") {
                assertThat(modifiedHistory.last().board.squares[0]).isEqualTo(9)
            }
        }
    }
})
