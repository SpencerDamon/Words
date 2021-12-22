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

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.DetailActivity.Companion.LETTER

/**
 * Adapter for the [RecyclerView] in [MainActivity].
 */
class LetterAdapter :
    RecyclerView.Adapter<LetterAdapter.LetterViewHolder>() {

    // Generates a [CharRange] from 'A' to 'Z' and converts it to a list
    private val list = ('A').rangeTo('Z').toList()

    /**
     * Provides a reference for the views needed to display items in your list.
     */
    class LetterViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button = view.findViewById<Button>(R.id.button_item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    /**
     * Creates new views with R.layout.item_view as its template
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        val layout = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_view, parent, false)
        // Setup custom accessibility delegate to set the text read
        layout.accessibilityDelegate = Accessibility
        return LetterViewHolder(layout)
    }

    /**
     * Replaces the content of an existing view with new data
     */
    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        val item = list.get(position)
        holder.button.text = item.toString()
        // 1. Set the onClickLister on the holder.button

        holder.button.setOnClickListener {

            //2. Get a reference from the context

            val context = holder.view.context

            // 3. Create an Intent, passing in the context, as well as the class name
            // of the destination activity

            val intent = Intent(context, DetailActivity::class.java)

            // 4. The name of the activity you want to show is specified with DetailActivity::class
            // .java. An actual DetailActivity object is created behind the scenes.
            // Call the putExtra method, passing in "letter" as the first argument and the button
            // text as the second argument.

            // 11. Modify the call to putExtra to use the new constant LETTER and import.
            // import com.example.wordsapp.DetailActivity.Companion.LETTER
            //  By refactoring, you just made your code easier to read, and easier to maintain.
            //  If this, or any other constant you add, ever needs to change, you only need to do so
            //  in one place.

            intent.putExtra(LETTER, holder.button.text.toString())

            // 5. Call the startActivity() method on the context object, passing in the intent.
            // GOTO DetailActivity

            context.startActivity(intent)

            // 6. Run the app, you should get MainActivity displaying a Header Words and a button
            // for each letter. When you click on the letter, DetailActivity opens displaying a
            // header that says Words That Start With A, and 5 buttons each displaying a random word
            // beginning with the letter A. But, it only display A words for every different letter
            // clicked, until step 7.
        }
    }

    // Setup custom accessibility delegate to set the text read with
    // an accessibility service
    companion object Accessibility : View.AccessibilityDelegate() {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onInitializeAccessibilityNodeInfo(
            host: View?,
            info: AccessibilityNodeInfo?
        ) {
            super.onInitializeAccessibilityNodeInfo(host, info)
            // With `null` as the second argument to [AccessibilityAction], the
            // accessibility service announces "double tap to activate".
            // If a custom string is provided,
            // it announces "double tap to <custom string>".
            val customString = host?.context?.getString(R.string.look_up_words)
            val customClick =
                AccessibilityNodeInfo.AccessibilityAction(
                    AccessibilityNodeInfo.ACTION_CLICK,
                    customString
                )
            info?.addAction(customClick)
        }
    }
}