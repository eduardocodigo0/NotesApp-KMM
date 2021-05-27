package db

import kotlin.Long
import kotlin.String

data class Notes(
  val id: Long,
  val title: String,
  val body: String?
) {
  override fun toString(): String = """
  |Notes [
  |  id: $id
  |  title: $title
  |  body: $body
  |]
  """.trimMargin()
}
