package com.er453r.ktave.lang

interface NodeConsumer {
    val isAccepting: Boolean

    fun addNode(token: Node?)
}
