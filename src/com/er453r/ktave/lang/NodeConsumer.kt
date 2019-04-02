package com.er453r.ktave.lang

import com.er453r.ktave.parser.Token

interface NodeConsumer {
    val isAccepting:Boolean

    fun addNode(token:Token?)
}
