package parser

import Error
import Status
import lexer.LexToken

class Parser(private val source: Array<LexToken>) {
    
    // Properties
    private var index: Int = -1
    private var token: LexToken? = null

    // Increment the index and return the character at the position
    private fun next() {
        index++
        token = source[index]
    }
    private fun <T>eat(): Error? {
        if token !is T return UnexpectedToken(token.position)
        next()
    }

    private fun factor(): Status {
        if eat
    }

    fun parse(): Status {
        next()
        return factor()
    }
}