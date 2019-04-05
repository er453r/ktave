package com.er453r.ktave.lang

class Arithmetic() : Node(), NodeConsumer {
    private var left: Node? = null
    private var right: Node? = null

    override val isAccepting = true

    override fun addNode(token: Node?) {
        if (left == null && token == null) {
            left = Number("0") // TODO - can be static

            return
        }

        if (left == null)
            left = token
        else if (right == null)
            right = token
        else if (right is NodeConsumer)
            right?.let {
                if (it is NodeConsumer && it.isAccepting)
                    it.addNode(token)
            }
        else
            throw Exception("Do not know how to handle $token")

    }

    override val regex = Regex("\\+")

    private constructor(statement: String) : this() {

    }

    override fun fromString(statement: String) = Arithmetic(statement)
}
