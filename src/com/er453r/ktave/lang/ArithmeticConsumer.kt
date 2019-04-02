package com.er453r.ktave.lang

import com.er453r.ktave.parser.Token
import com.er453r.ktave.parser.TokenConsumer
import mu.KotlinLogging

class ArithmeticConsumer : TokenConsumer {
    private val log = KotlinLogging.logger {}

    //    private val nodes = mutableListOf<Token>()
    private var currentNode: Token? = null

    override fun addToken(token: Token) {
        log.info { "New ${token::class.simpleName} appeared" }

        if(token is Space)
            return

        if (token is NodeConsumer && token.isAccepting) {
            log.info { "New consumer appeared" }

            token.addNode(currentNode)

            this.currentNode = token

            return
        }

        if (currentNode == null) {
            log.info { "Adding node" }

            currentNode = token

            return
        }

        currentNode?.let {
            if (it is NodeConsumer && it.isAccepting) {
                log.info { "Adding to existing consumer" }

                it.addNode(token)

                return
            }
        }

        throw Exception("Do not know what to do with $token")
    }
}
