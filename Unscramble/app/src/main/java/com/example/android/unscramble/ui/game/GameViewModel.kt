package com.example.android.unscramble.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var _currentWord: String

    // private lateinit var _currentScrambledWord: String
    private val _currentScrambledWord = MutableLiveData<String>()
    private var _score = MutableLiveData(0)
    private var _currentWordCount = MutableLiveData(0)

    val currentScrambledWord: LiveData<String>
        get() = _currentScrambledWord

    val score: LiveData<Int>
        get() = _score

    val currentWordCount: LiveData<Int>
        get() = _currentWordCount

    init {
        getNextWord()
    }

    private fun increaseScore() {
        _score.value = _score.value?.plus(SCORE_INCREASE)
    }

    fun reinitializeData() {
        _score.value = 0
        _currentWordCount.value = 0
        wordsList.clear()
        getNextWord()
    }

    fun isUserWordCorrect(input: String): Boolean {
        return if (_currentWord.equals(input, true)) {
            increaseScore()
            true
        } else false
    }


    fun nextWord(): Boolean {
        return if (_currentWordCount.value!! < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    private fun getNextWord() {
        _currentWord = allWordsList.shuffled().random()
        while (wordsList.contains(_currentWord)) {
            _currentWord = allWordsList.shuffled().random()
        }
        _currentScrambledWord.value = _currentWord
        while (_currentScrambledWord.value.equals(_currentWord, false)) {
            _currentScrambledWord.value = _currentWord.toList().shuffled().joinToString("")
        }
        wordsList.add(_currentWord)
        _currentWordCount.value = _currentWordCount.value?.inc()
    }
}