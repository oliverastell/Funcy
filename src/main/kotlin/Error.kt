import lexer.Position

interface Error {
    fun represent(): String {
        return ""
    }
}

// Lexer
class InvalidEscapeSequence(private val position: Position) : Error {
    override fun represent(): String {
        return "Invalid escape sequence"
    }
}
class UnknownToken(private val position: Position) : Error {
    override fun represent(): String {
        return "Unknown token"
    }
}

// Parser
class UnexpectedToken(private val position: Position) : Error {
    override fun represent(): String {
        return "Unexpected token"
    }
}