package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    companion object {
        const val TAG = "GameViewModel"
        const val GTAG = "GameFragment"
    }

    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String
    private lateinit var _currentScrambledWord: String
    private var _score = 0
    private var _currentWordCount = 0

    val currentScrambledWord: String
        get() = _currentScrambledWord

    val score: Int
        get() = _score

    init {
        Log.d(GTAG, "GameViewModel Created")
        getNextWord()
    }

    private fun increaseScore() {
        _score += SCORE_INCREASE
    }

    fun isUserWordCorrect(input: String): Boolean {
        return if (currentWord.equals(input, true)) {
            increaseScore()
            true
        } else false
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(GTAG, "GameViewModel Destroyed")
    }

    fun nextWord(): Boolean {
        return if (_currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    private fun getNextWord() {
        currentWord = allWordsList.shuffled().random()
        while (wordsList.contains(currentWord)) {
            currentWord = allWordsList.shuffled().random()
        }
        _currentScrambledWord = currentWord
        while (_currentScrambledWord.equals(currentWord, false)) {
            _currentScrambledWord = currentWord.toList().shuffled().joinToString("")
        }
        wordsList.add(currentWord)
        ++_currentWordCount
    }
}