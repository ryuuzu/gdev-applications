package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    companion object {
        const val TAG = "GameViewModel"
        const val GTAG = "GameFragment"
    }

    private var _score = 0
    private var _currentWordCount = 0
    private var _currentScrambledWord = "test"

    val currentScrambledWord: String
        get() = _currentScrambledWord

    init {
        Log.d(GTAG, "GameViewModel Created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(GTAG, "GameViewModel Destroyed")
    }
}