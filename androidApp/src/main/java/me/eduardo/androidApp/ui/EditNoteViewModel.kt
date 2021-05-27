package me.eduardo.androidApp.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import db.Notes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.eduardo.shared.db.DataSDK
import me.eduardo.shared.db.DatabaseDriverFactory

class EditNoteViewModel(application: Application): AndroidViewModel(application) {

    private val _note: MutableLiveData<Notes> = MutableLiveData()
    val note: LiveData<Notes> get() = _note

    private val _uploaded: MutableLiveData<Boolean> = MutableLiveData()
    val uploaded: LiveData<Boolean> get() = _uploaded

    private val _deleted: MutableLiveData<Boolean> = MutableLiveData()
    val deleted: LiveData<Boolean> get() = _deleted

    private val db = DataSDK(DatabaseDriverFactory(getApplication()))

    fun getNote(id: Long){
        try{
            viewModelScope.launch(Dispatchers.IO) {
                _note.postValue(db.getOneNote(id))
            }
        }catch (err: Exception){
            Log.d("abc", "ERRO -> ${err.message}")
        }
    }

    fun updateNote(title: String, body: String, id: Long){
        viewModelScope.launch(Dispatchers.IO) {
            _uploaded.postValue(db.updateNote(title, body, id))
        }
    }

    fun deleteNote(id: Long){
        viewModelScope.launch(Dispatchers.IO) {
            _deleted.postValue(db.deleteNote(id))
        }
    }

}