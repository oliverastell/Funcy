
interface Status {val result: Any?}

data class OkStatus(override val result: Any?) : Status
data class BadStatus(override val result: Error) : Status