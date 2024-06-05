package com.example.hw2

import Affirmation
import com.example.hw2.R

class Datasource() {
    fun loadAffirmations(): List<Affirmation> {
        return listOf<Affirmation>(
            Affirmation(R.string.affirmation1,R.string.affirmation11, R.drawable.image1),
            Affirmation(R.string.affirmation2,R.string.affirmation22, R.drawable.image2),
            Affirmation(R.string.affirmation3,R.string.affirmation33, R.drawable.image3),
            Affirmation(R.string.affirmation4,R.string.affirmation44, R.drawable.image4),
            Affirmation(R.string.affirmation5,R.string.affirmation55, R.drawable.image5),
            Affirmation(R.string.affirmation6,R.string.affirmation66, R.drawable.image6),
            Affirmation(R.string.affirmation7,R.string.affirmation77, R.drawable.image7),
            Affirmation(R.string.affirmation8,R.string.affirmation88, R.drawable.image8),
            Affirmation(R.string.affirmation9,R.string.affirmation99, R.drawable.image9),
            Affirmation(R.string.affirmation10,R.string.affirmation110, R.drawable.image10)
        )
    }
}