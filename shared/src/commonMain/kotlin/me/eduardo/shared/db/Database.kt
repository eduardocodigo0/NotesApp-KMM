package me.eduardo.shared.db

import db.Notes
import me.eduardo.sqldelight.db.NoteDB
import kotlin.native.concurrent.ThreadLocal

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = NoteDB(databaseDriverFactory.createDriver())
    private val dbQuery = database.noteDBQueries

    internal fun insertNote(title: String, body: String) {
        dbQuery.insertNote(title, body)
    }

    internal fun deleteNote(id: Long) {
        dbQuery.deleteNote(id)

    }

    internal fun updateNote(title: String, body: String, id: Long) {
        dbQuery.updateNote(title, body, id)
    }

    internal fun getAllNotes(): List<Notes> {
        return dbQuery.getAllNotes().executeAsList()
    }

    internal fun getOneNote(id: Long): Notes? {
        return dbQuery.getOneNote(id).executeAsOneOrNull()
    }

}