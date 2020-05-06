# Using recursion to loop in a functional style

Over the last few weeks I have been trying to develop my knowledge of function programming techniques. Functional programming is a style which is well supported in Kotlin, and in my team (Digital Merchandising) we use a couple of functional libraries - [HTTP4k](https://www.http4k.org/), for making web applications, and [Result4K](https://github.com/npryce/result4k) for error handling.

We generally try to abide by the principles of functional programming, but this sometimes requires a very different approach to the more familiar imperative style, and sometimes we've come across problems that we struggled to solve in a strictly functional way, so I've been trying to learn more about it to help in my day to day work.

This article is about a simple thing I learned to solve a problem - what is the functional equivalent of the common imperative structure of looping with a mutable loop variable. But first:

## What is functional programming?

Functional programming is a style of programming where programs are made up of pure functions. A pure function is one that has no side effects, i.e. it doesn't modify any state outside of itself, and it doesn't depend on any state outside of itself.

Functional programs avoid shared state and mutable data. Among the benefits of this style is that functions become easier to test, with less set up required, because you know a given set of inputs will always give the same output. Avoiding mutable data also makes it easier to reason about your program as you know that any given entity can only have one value.

One practice we try to adhere to in our team to leverage these benefits is to always avoid the use of mutable data where possible. Kotlin encourages this by providing various immutable data structures (e.g. `List` versus `MutableList`) and the ability to declare variables with the keyword `val`, meaning they cannot be reassigned.

## The problem: how to loop without a mutable variable 

I came across this problem when I was working on JL's [Coding Challenge Community's challenge 21](https://coding-challenges.jl-engineering.net/challenges/challenge-21/) - writing a sudoku solver. (Go to #comm-coding-challenge on Slack to join the fun!) Part of my algorithm involved running a sudoku board through a deduction method until the output was the same as the input, i.e. no further deductions were possible. In an imperative style that might look like this:

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

Here I iterate over a loop making a new deduction each time. The new value is compared with the old value, and if they are the same we conclude that no more improvements are possible, so we break out of the loop and return the latest board.

The issue is that we have to store the value of the board in between loops in a mutable variable (initialised with the `var` keyword). It's not the end of the world, but I knew there must be a functional way of doing this which would get rid of the mutable variable. It took a lot of head-scratching before I came to the solution:

## The solution: looping recursively

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

In this version of `deduceTilICantNoMore` we call the same `board.deduce()` function to produce a new board. If the new board is the same as the old one, we know that no further deductions are possible, so we return it. But if not we continue by calling `deduceTilICantNoMore` a second time, passing the new board in as a parameter. Inside that function call we may potentially call the function a third time, and then a fourth and so on, until eventually the output of `board.deduce()` is equal to `board` and we stop. In this way we have banished all mutable state, and the gods of functional programming are appeased 🙏.

It turns out this is a very common pattern in functional programming, and a standard way of implementing loops.

## Recursion is dangerous

Recursion is dangerous though - it carries the potential risk of causing a stack overflow at runtime. The call stack is a memory structure that stores information about subroutines running in your program. Each time a function gets called, a stack frame is added to the stack, storing information about the context of that function, such as the value of its internal variables. When a function returns, its stack frame can be discarded. But a recursive function can call itself again and again before it ever returns, each time adding another stack frame before the previous ones can be discarded. If the function calls itself enough times the size of the stack will exceed the amount of memory available, causing your program to crash with a stack overflow error. Try running this to see it happen:

```kotlin
fun foo() {
    foo()
}

foo()
```

As a result recursion should be approached with caution, especially if the level of recursion is likely to be very deep or is unknown. Luckily Kotlin has a way to make this danger magically go away.

## Tail recursion is safe

The compilers of languages which support functional programming use various optimisation tricks to allow developers to protect themselves against the danger of excessive recursion. Kotlin supports tail recursion optimisation, which allows recursive functions to execute without the overhead of all those extra stack frames.

Tail recursion is a form of recursion where the recursive call is the last instruction in a function. If the Kotlin compiler knows that a recursive function is tail recursive, then it knows that it can throw away the function's stack frame when the recursive call is made. This means that each previous stack frame is discarded when the next one is created, so the number of stack frames stays the same instead of increasing, no matter how many times the recursive function calls itself. Hence no stack overflow error.

How this magical sleight-of-hand trick by the compiler works is very interesting but I'm not going to go into detail here. If you want to know more, this article goes into some depth, including what the compiled bytecode looks like: <https://medium.com/@stephen.leigh/tail-recursion-in-kotlin-with-bytecode-c89a169b9f23>

The important thing to note is that in general when using recursion in Kotlin you should always use the tail recursion optimisation to avoid the possibility of getting a stack overflow at runtime. You do that by adding the `tailrec` modifier to the start of your function definition. Hence, the final form of the function:

```kotlin
tailrec fun deduceTilICantNoMore(board: Board): Board {
    val newBoard = board.deduce()
    return if (newBoard == board) {
        board
    } else {
        deduceTilICantNoMore(newBoard)
    }
}
```

Note that this only works if the recursive function call is the last instruction in the function. If you try and apply the `tailrec` modifier to a function that the compiler can't optimise it will highlight your function with a warning that "A function is marked as tail-recursive but no tail calls are found". If your recursive call is not tail recursive it should be possible to rewrite it so that it is, so that the compiler can optimise it safely, but that's another story.

## Applications

Since learning this, our team has moved on to working on moving product category information out of ATG into a new application in JLDP. Categories exist in a hierarchical tree, which we are representing internally in a recursive data stucture. As a result we've been writing lots of recursive functions to manipulate and traverse this data, so I've been able apply the tail recursion optimisation a number of times, ensuring we're always recursing in safety. In general recursion is a common feature of functional programming, so learning to use it safely and generally getting familiar with it has helped me writing functional code in my day to day work.