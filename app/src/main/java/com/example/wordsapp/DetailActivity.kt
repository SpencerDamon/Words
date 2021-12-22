/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.wordsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordsapp.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {
    // For a small list such as this hardcoding "letter" is fine, but for larger apps where you'll
    // have multiple views, this is not feasible. see 8.
    // 8. A companion object is similar to other objects, such as instances of a class. However,
    // only a single instance of a companion object will exist for the duration of your program,
    // which is why this is sometimes called the singleton pattern.For now, use a companion object
    // as a way to organize constants and make them accessible outside of the DetailActivity.
    // You'll start by using a companion object to refactor the code for the "letter" extra.
    // the object keyword. There's also a keyword companion, meaning it's associated with the
    // DetailActivity class, and we don't need to give it a separate type name.
    companion object {
        // 9. Add a property for the letter constant.
        const val LETTER = "letter"


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve a binding object that allows you to refer to views by id name
        // Names are converted from snake case to camel case.
        // For example, a View with the id word_one is referenced as binding.wordOne
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the LETTER from the Intent extras
        // intent.extras.getString returns String? (String or null)
        // so toString() guarantees that the value will be a String
        // 7. Replace the hard coded letter with code to get the letterId passed in from the intent.
        // Then run the app, should now display 5 random words for each appropriate letter clicked
        // on the MainActivity, not just words beginning with A.
        // 10. To use the new constant, update your hard coded letter call in onCreate()
        // from getString("letter") to getString(Letter)
        // 11. GOTO LetterAdapter
        val letterId = intent?.extras?.getString(LETTER).toString()

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = WordAdapter(letterId, this)

        // Adds a [DividerItemDecoration] between items
        recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        title = getString(R.string.detail_prefix) + " " + letterId
    }
}