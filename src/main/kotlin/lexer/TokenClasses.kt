package lexer

interface LexToken

fun makeOp(type: String, position: Position): LexToken {
    return when (type) {
        "add" -> LexAdd(position)
        "sub" -> LexSub(position)
        "div" -> LexDiv(position)
        "mul" -> LexMul(position)
        "mod" -> LexMod(position)
        "pow" -> LexPow(position)
        "eqs" -> LexEqs(position)
        "grt" -> LexGrt(position)
        "les" -> LexLes(position)
        "eqseqs" -> LexEqsEqs(position)
        "grteqs" -> LexGrtEqs(position)
        "leseqs" -> LexLesEqs(position)
        "lbracket" -> LexLBracket(position)
        "rbracket" -> LexRBracket(position)
        "lcurly" -> LexLCurly(position)
        "rcurly" -> LexRCurly(position)
        "divider" -> LexDivider(position)
        "colon" -> LexColon(position)
        "period" -> LexPeriod(position)
        "int" -> LexKwInt(position)
        "uint" -> LexKwUint(position)
        "float" -> LexKwFloat(position)
        "string" -> LexKwString(position)
        "lambda" -> LexKwLambda(position)
        "tuple" -> LexKwTuple(position)
        "char" -> LexKwChar(position)
        "null" -> LexKwNull(position)
        else -> throw Throwable("invalid token type: $type")
    }
}

// Types
data class LexString(val position: Position, val value: String) : LexToken
data class LexChar(val position: Position, val value: String) : LexToken
data class LexFloat(val position: Position, val value: String) : LexToken
data class LexInt(val position: Position, val value: String) : LexToken
data class LexUnsigned(val position: Position, val value: String) : LexToken

// Identifier
data class LexIdentifier(val position: Position, val value: String) : LexToken

// Characters
data class LexAdd(val position: Position) : LexToken
data class LexSub(val position: Position) : LexToken
data class LexDiv(val position: Position) : LexToken
data class LexMul(val position: Position) : LexToken
data class LexMod(val position: Position) : LexToken
data class LexPow(val position: Position) : LexToken

data class LexEqs(val position: Position) : LexToken
data class LexGrt(val position: Position) : LexToken
data class LexLes(val position: Position) : LexToken
data class LexEqsEqs(val position: Position) : LexToken
data class LexGrtEqs(val position: Position) : LexToken
data class LexLesEqs(val position: Position) : LexToken

data class LexLBracket(val position: Position) : LexToken
data class LexRBracket(val position: Position) : LexToken
data class LexLCurly(val position: Position) : LexToken
data class LexRCurly(val position: Position) : LexToken

data class LexDivider(val position: Position) : LexToken
data class LexColon(val position: Position) : LexToken
data class LexPeriod(val position: Position) : LexToken

data class LexKwInt(val position: Position) : LexToken
data class LexKwUint(val position: Position) : LexToken
data class LexKwFloat(val position: Position) : LexToken
data class LexKwString(val position: Position) : LexToken
data class LexKwLambda(val position: Position) : LexToken
data class LexKwTuple(val position: Position) : LexToken
data class LexKwChar(val position: Position) : LexToken
data class LexKwNull(val position: Position) : LexToken