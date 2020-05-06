# Using recursion to write loops functionally

In my algorithm I alternated between making deductions and guesses. I would call a deduction method on a board, which would apply some rules of deduction, generating a new board. If it produced a board with fewer unknown squares, I would call the deduction function on the board again, and so on. At some point the deduction function would be able to make no further improvements and the board that came out would be the same as the board that went in.

At this point, if the board was not fully solved, I would pick an unsolved square and make a guess at the value. I would then continue the process of deducing and guessing. If by this process I ever arrived at an invalid state, I would know that one of my guesses had been wrong, so I would go back and change my guess to one of the alternatives for that square, and then start deducing and guessing again. In this way the algorithm eventually arrives at a valid solution. (The algorithm also works if you don't do any deducing, just using a brute force method of guessing and checking, but it's slower.)

## Looping imperatively

The part of this I want to talk about is the part where I repeatedly apply the deduction function until no further improvements are forthcoming. Writing in an imperative style the function might look like this:

```kotlin
fun deduceTilICantNoMore(board: Board): Board {
    var newBoard = board

    do {
        val oldBoard = newBoard
        newBoard = oldBoard.deduce()
    } while (newBoard != oldBoard)

    return newBoard
}
```

Here I iterate over a loop making a new deduction each time. The new value is compared with the old value, and if they are the same we conclude that no more improvements are possible, we break out of the loop and return the latest board.

The issue is that we have to store the value of the board in between loops in a mutable variable (initialised with the `var` keyword). Perhaps this is not the most terrible crime - `deduceTilICantNoMore` at least is still a pure function as it doesn't modify any state outside itself, but... why is it bad?....

## Looping recursively

In functional programming, loops like these are implemented without the need for a mutable loop variable by using recursion. Recursion is when a function calls itself, like this:

```kotlin
fun deduceTilICantNoMore(board: Board): Board {
    val newBoard = board.deduce()
    return if (newBoard == board) {
        board
    } else {
        deduceTilICantNoMore(newBoard)
    }
}
```

In this version of `deduceTilICantNoMore` we call the same `board.deduce()` function to produce a new board. If the new board is the same as the old one, we return it. But if not we continue by calling `deduceTilICantNoMore` a second time, passing the new board in as a parameter. Inside that function call we may potentially call the function a third time, and then a fourth and so on, until eventually the output of `board.deduce()` is equal to `board` and we stop. In this way we have eliminated our mutable `var`.

Recursion is potentially dangerous though - it carries the potential risk of causing a stack overflow. The call stack is a memory structure that stores information about functions running in your program. Each time a function gets called, a stack frame is added to the stack, storing information about the context of that function, such as the value of its internal variables. When a function returns, its stack frame can be disgarded. But a recursive function can call itself again and again before it ever returns, each time adding another stack frame before the previous ones can be disgarded. If the function calls itself enough times the size of the stack will exceed the amount of memory available to it, causing your program to crash with a stack overflow error. Try running this to see it happen:

```kotlin
fun main() {
    fun foo() {
        foo()
    }

    foo()
}
```

As a result recursion should be approached with caution, especially if the level of recursion is likely to be very deep or is unknown. Luckily Kotlin has a way to make this danger magically go away...

## Tail recursion

The compilers of languages which support functional programming generally use optimisation tricks to allow developers to protect themselves against the dangers of recursion. Kotlin supports tail recursion optimisation, which allows recursive functions to execute without the overhead of all those extra stack frames.

Tail recursion is a form of recursion where the recursive call is the last instruction in a function. If the Kotlin compiler knows that a recursive function is tail recursive, then it knows that it can throw away the function's stack frame when the recursive call is made, because it won't have to do anything else with it once the second function call returns. 