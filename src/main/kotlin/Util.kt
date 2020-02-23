fun <T> List<T>.update(requestedIndex: Int, newValue: T) =
    this.mapIndexed { index, value ->
        if (index == requestedIndex) {
            newValue
        } else {
            value
        }
    }

fun <T> List<T>.add(index: Int, value: T) =
    this.subList(0, index) + listOf(value) + this.subList(index, this.size)