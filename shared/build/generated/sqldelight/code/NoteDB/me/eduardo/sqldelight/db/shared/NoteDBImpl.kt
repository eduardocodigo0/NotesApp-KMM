package me.eduardo.sqldelight.db.shared

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.db.SqlCursor
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.internal.copyOnWriteList
import db.NoteDBQueries
import db.Notes
import kotlin.Any
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.MutableList
import kotlin.jvm.JvmField
import kotlin.reflect.KClass
import me.eduardo.sqldelight.db.NoteDB

internal val KClass<NoteDB>.schema: SqlDriver.Schema
  get() = NoteDBImpl.Schema

internal fun KClass<NoteDB>.newInstance(driver: SqlDriver): NoteDB = NoteDBImpl(driver)

private class NoteDBImpl(
  driver: SqlDriver
) : TransacterImpl(driver), NoteDB {
  override val noteDBQueries: NoteDBQueriesImpl = NoteDBQueriesImpl(this, driver)

  object Schema : SqlDriver.Schema {
    override val version: Int
      get() = 1

    override fun create(driver: SqlDriver) {
      driver.execute(null, """
          |CREATE TABLE notes(
          |    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
          |    title TEXT NOT NULL,
          |    body TEXT
          |)
          """.trimMargin(), 0)
    }

    override fun migrate(
      driver: SqlDriver,
      oldVersion: Int,
      newVersion: Int
    ) {
    }
  }
}

private class NoteDBQueriesImpl(
  private val database: NoteDBImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), NoteDBQueries {
  internal val getAllNotes: MutableList<Query<*>> = copyOnWriteList()

  internal val getOneNote: MutableList<Query<*>> = copyOnWriteList()

  override fun <T : Any> getAllNotes(mapper: (
    id: Long,
    title: String,
    body: String?
  ) -> T): Query<T> = Query(1935945628, getAllNotes, driver, "NoteDB.sq", "getAllNotes",
      "SELECT * FROM notes") { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)
    )
  }

  override fun getAllNotes(): Query<Notes> = getAllNotes { id, title, body ->
    db.Notes(
      id,
      title,
      body
    )
  }

  override fun <T : Any> getOneNote(id: Long, mapper: (
    id: Long,
    title: String,
    body: String?
  ) -> T): Query<T> = GetOneNoteQuery(id) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)
    )
  }

  override fun getOneNote(id: Long): Query<Notes> = getOneNote(id) { id, title, body ->
    db.Notes(
      id,
      title,
      body
    )
  }

  override fun insertNote(title: String, body: String?) {
    driver.execute(626821381, """INSERT INTO notes(title, body) VALUES (?, ?)""", 2) {
      bindString(1, title)
      bindString(2, body)
    }
    notifyQueries(626821381, {database.noteDBQueries.getAllNotes +
        database.noteDBQueries.getOneNote})
  }

  override fun deleteNote(id: Long) {
    driver.execute(1425244919, """DELETE FROM notes WHERE id = ?""", 1) {
      bindLong(1, id)
    }
    notifyQueries(1425244919, {database.noteDBQueries.getAllNotes +
        database.noteDBQueries.getOneNote})
  }

  override fun updateNote(
    title: String,
    body: String?,
    id: Long
  ) {
    driver.execute(-635275499, """UPDATE notes SET title = ?, body = ? WHERE id = ?""", 3) {
      bindString(1, title)
      bindString(2, body)
      bindLong(3, id)
    }
    notifyQueries(-635275499, {database.noteDBQueries.getAllNotes +
        database.noteDBQueries.getOneNote})
  }

  private inner class GetOneNoteQuery<out T : Any>(
    @JvmField
    val id: Long,
    mapper: (SqlCursor) -> T
  ) : Query<T>(getOneNote, mapper) {
    override fun execute(): SqlCursor = driver.executeQuery(-623701508,
        """SELECT * FROM notes WHERE id = ?""", 1) {
      bindLong(1, id)
    }

    override fun toString(): String = "NoteDB.sq:getOneNote"
  }
}
