package me.eduardo.sqldelight.db

import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.db.SqlDriver
import db.NoteDBQueries
import me.eduardo.sqldelight.db.shared.newInstance
import me.eduardo.sqldelight.db.shared.schema

interface NoteDB : Transacter {
  val noteDBQueries: NoteDBQueries

  companion object {
    val Schema: SqlDriver.Schema
      get() = NoteDB::class.schema

    operator fun invoke(driver: SqlDriver): NoteDB = NoteDB::class.newInstance(driver)}
}
