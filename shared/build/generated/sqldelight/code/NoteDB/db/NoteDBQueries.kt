package db

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.Long
import kotlin.String

interface NoteDBQueries : Transacter {
  fun <T : Any> getAllNotes(mapper: (
    id: Long,
    title: String,
    body: String?
  ) -> T): Query<T>

  fun getAllNotes(): Query<Notes>

  fun <T : Any> getOneNote(id: Long, mapper: (
    id: Long,
    title: String,
    body: String?
  ) -> T): Query<T>

  fun getOneNote(id: Long): Query<Notes>

  fun insertNote(title: String, body: String?)

  fun deleteNote(id: Long)

  fun updateNote(
    title: String,
    body: String?,
    id: Long
  )
}
