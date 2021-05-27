package me.eduardo.shared.db

import db.Notes
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.ThreadLocal

class DataSDK(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = Database(databaseDriverFactory)

    fun insertNote(title: String, body: String): Boolean {

        return try {
            database.insertNote(title, body)
            true
        } catch (err: Exception) {
            false
        }

    }

    fun deleteNote(id: Long): Boolean {
        return try {
            database.deleteNote(id)
            true
        } catch (err: Exception) {
            false
        }
    }

    fun updateNote(title: String, body: String, id: Long): Boolean {
        return try {
            database.updateNote(title, body, id)
            true
        } catch (err: Exception) {
            false
        }
    }

    fun getAllNotes(): List<Notes> {
        return database.getAllNotes()
    }

    fun getOneNote(id: Long): Notes? {
        return database.getOneNote(id)
    }
}