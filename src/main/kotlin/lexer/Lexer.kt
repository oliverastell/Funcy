package lexer

import BadStatus
import InvalidEscapeSequence
import OkStatus
import Status
import UnknownToken

class Lexer(private val source: String) {

    // Properties
    private var index: Int = -1
    private var char: Char? = null

    // Increment the index and return the character at the position
    private fun next() {
        index++
        char = source.getOrNull(index)
    }

    // Lex operators
    // Special treatment for two character operators ending in =
    private fun makeOperator(array: ArrayList<LexToken>): Status {
        val token: Char = char!!
        val startIndex: Int = index
        next()
        array.add(if (char == '=') makeOp(eqOps[token]!!, Position(startIndex, startIndex)) else makeOp(ops[token]!!, Position(startIndex, startIndex)))

        return OkStatus(null)
    }

    // Lex numbers
    // Valid syntax:
    //  1.2  1.2f  1f  1_2  2u
    // Invalid syntax:
    //  1._2  1.2  1
    private fun makeNumber(array: ArrayList<LexToken>): Status {
        var token: String = ""
        val startIndex: Int = index

        while (char != null && DIGITS.contains(char!!)) {
            token += char
            next()
            while (char == '_') next()
        }

        if (char == UNSIGNED) {
            next()
            array.add(LexUnsigned(Position(startIndex, index-1), token))
            return OkStatus(null)
        } else if (char == FLOAT) {
            next()
            array.add(LexFloat(Position(startIndex, index-1), token))
            return OkStatus(null)
        } else if (char != PERIOD) {
            array.add(LexInt(Position(startIndex, index-1), token))
            return OkStatus(null)
        }

        val numOnly = token
        token += char
        next()
        if (char == null || !DIGITS.contains(char!!)) {
            if (numOnly == "") array.add(LexInt(Position(startIndex, index-2), token))
            array.add(makeOp("period", Position(index-1, index-1)))
            return OkStatus(null)
        }
        while (char != null && DIGITS.contains(char!!)) {
            token += char
            next()
            while (char == '_') next()
        }
        if (char == FLOAT) next()

        array.add(LexFloat(Position(startIndex, index-1), token))
        return OkStatus(null)
    }

    // Lex strings
    private fun makeString(array: ArrayList<LexToken>): Status {
        var token: String = ""
        val startIndex: Int = index

        next()
        while (char != null && char != STRING_CAP) {
            token += if (char == '\\') {
                next()
                when (char) {
                    'n' -> '\n'
                    '\\' -> '\\'
                    't' -> '\t'
                    else -> return BadStatus(InvalidEscapeSequence(Position(index-1, index)))
                }
            } else char
            next()
        }
        next()

        array.add(LexString(Position(startIndex, index-1), token))
        return OkStatus(null)
    }

    // Lex character
    private fun makeChar(array: ArrayList<LexToken>): Status {
        var token: String = ""
        val startIndex: Int = index

        next()
        token += if (char == '\\') {
            next()
            when (char) {
                'n' -> '\n'
                '\\' -> '\\'
                't' -> '\t'
                else -> return BadStatus(InvalidEscapeSequence(Position(index-1, index)))
            }
        } else char
        next()

        array.add(LexChar(Position(startIndex, index-1), token))
        return OkStatus(null)
    }

    // Lex identifiers
    // Special treatment for some keywords
    private fun makeIdentifier(array: ArrayList<LexToken>): Status {
        var token: String = ""
        val startIndex: Int = index

        while (char != null && IDENTIFIERS.contains(char!!)) {
            token += char
            next()
        }

        if (keywords.contains(token)) {
            array.add(makeOp(token, Position(startIndex, index-1)))
            return OkStatus(null)
        }

        array.add(LexIdentifier(Position(startIndex, index-1), token))
        return OkStatus(null)
    }

    // Check every character in the source if it matches any token
    // Find the whole token and add it to an array
    // Return the array as a status
    fun lex(): Status {
        next()

        val tokens: ArrayList<LexToken> = ArrayList()

        while (char != null) {
            val status: Status =
                if (ops.containsKey(char)) makeOperator(tokens)
                else if (DIGITS.contains(char!!)) makeNumber(tokens)
                else if (char == PERIOD) makeNumber(tokens)
                else if (char == STRING_CAP) makeString(tokens)
                else if (char == CHAR_CAP) makeChar(tokens)
                else if (IDENTIFIERS.contains(char!!)) makeIdentifier(tokens)
                else if (WHITESPACE.contains(char!!)) {next(); OkStatus(null)}
                else if (char == COMMENT) {while (char != null && char != '\n') next(); next(); OkStatus(null)}
                else BadStatus(UnknownToken(Position(index-1, index)))
            if (status is BadStatus) return status
        }

        return OkStatus(tokens)
    }
}