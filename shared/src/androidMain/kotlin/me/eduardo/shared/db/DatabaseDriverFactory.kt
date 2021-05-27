package me.eduardo.shared.db

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import me.eduardo.sqldelight.db.NoteDB


actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
       return AndroidSqliteDriver(NoteDB.Schema, context, "notes.db")
    }
}