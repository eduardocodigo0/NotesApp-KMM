package me.eduardo.androidApp.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import db.Notes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.eduardo.shared.db.DataSDK
import me.eduardo.shared.db.DatabaseDriverFactory

class NoteListViewModel(application: Application) : AndroidViewModel(application),
    LifecycleObserver {

    private val db = DataSDK(DatabaseDriverFactory(getApplication()))
    private val _notesList: MutableLiveData<List<Notes>> = MutableLiveData()
    val notesList: LiveData<List<Notes>> get() = _notesList

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun getNotes() {
        try {

            viewModelScope.launch(Dispatchers.IO) {
                _notesList.postValue(db.getAllNotes())
            }

        } catch (err: Exception) {
            Log.d("abc", "ERRO -> ${err.message}")
        }

    }

}