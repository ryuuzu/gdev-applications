package com.example.affirmations.data

import com.example.affirmations.R
import com.example.affirmations.model.Affirmation

class DataSource {
    fun loadAffirmations(): List<Affirmation> {
        return listOf(
            Affirmation(R.string.affirmations1),
            Affirmation(R.string.affirmations2),
            Affirmation(R.string.affirmations3),
            Affirmation(R.string.affirmations4),
            Affirmation(R.string.affirmations5),
            Affirmation(R.string.affirmations6),
            Affirmation(R.string.affirmations7),
            Affirmation(R.string.affirmations8),
            Affirmation(R.string.affirmations9),
            Affirmation(R.string.affirmations10)
        )
    }
}