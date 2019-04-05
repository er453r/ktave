package com.er453r.ktave.lang

class Space : Node() {
    override val regex = Regex(" ")
    override fun fromString(statement: String) = Space()
}
