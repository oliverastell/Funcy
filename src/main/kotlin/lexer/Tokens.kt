package lexer

// Syntax
const val IDENTIFIERS: String = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_0123456789"
val ops: Map<Char, String> = mapOf(
    '+' to "add",
    '-' to "sub",
    '*' to "mul",
    '/' to "div",
    '%' to "mod",
    '^' to "pow",
    '(' to "lbracket",
    ')' to "rbracket",
    '{' to "lcurly",
    '}' to "rcurly",
    'λ' to "lambda",
    '>' to "grt",
    '<' to "les",
    '=' to "eqs"
)
val eqOps: Map<Char, String> = mapOf(
    '>' to "grteqs",
    '<' to "leseqs",
    '=' to "eqseqs"
)
val keywords: Array<String> = arrayOf<String>(
    "int",
    "uint",
    "float",
    "string",
    "lambda",
    "tuple",
    "char",
    "null"
)


// String
const val CHAR_CAP: Char = '\''
const val STRING_CAP: Char = '"'


// Number
const val DIGITS: String = "0123456789"
const val PERIOD: Char = '.'
const val UNSIGNED: Char = 'u'
const val FLOAT: Char = 'f'


// Whitespace
const val WHITESPACE: String = " \t\n"
const val COMMENT: Char = '#'