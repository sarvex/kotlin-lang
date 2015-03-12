package test

class SecondaryConstructors(x: Boolean) {
    init {
    }

    constructor(x: String) : this(x == "abc") {
    }

    init {
    }

    constructor(x: Int) : this(x < 0) {
    }
}
