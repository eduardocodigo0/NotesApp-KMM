package me.eduardo.androidApp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.eduardo.shared.db.DataSDK
import me.eduardo.shared.db.DatabaseDriverFactory

class NewNoteViewModel(application: Application) : AndroidViewModel(application) {

    private val db = DataSDK(DatabaseDriverFactory(getApplication()))
    private val _newNoteCreated: MutableLiveData<Boolean> = MutableLiveData()
    val newNoteCreated get() = _newNoteCreated

    fun saveNote(title: String, body: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _newNoteCreated.postValue(db.insertNote(title, body))
        }
    }

}