import lexer.Lexer

fun main() {
    val lexer = Lexer("""
        1 2 3f
        #hi
        4
    """.trimIndent())

    val result = lexer.lex()
    println(result.result)
}

// comment