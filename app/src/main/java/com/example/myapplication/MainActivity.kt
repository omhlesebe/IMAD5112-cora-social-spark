
package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("CoraApp", "App started")

        val etTime = findViewById<EditText>(R.id.etTimeOfDay)
        val btnSuggest = findViewById<Button>(R.id.btnSuggest)
        val btnReset = findViewById<Button>(R.id.btnReset)
        val tvSuggestion = findViewById<TextView>(R.id.tvSuggestion)

        btnSuggest.setOnClickListener {
            val input = etTime.text.toString().trim()
            if (input.isEmpty()) {
                Toast.makeText(this, "Please enter time e.g. Morning", Toast.LENGTH_SHORT).show()
                tvSuggestion.text = "Oops! Please enter a time of day."
            } else {
                tvSuggestion.text = getSocialSpark(input)
            }
        }

        btnReset.setOnClickListener {
            etTime.text.clear()
            tvSuggestion.text = "Your spark will appear here..."
        }
    }

    private fun getSocialSpark(timeOfDay: String): String {
        val time = timeOfDay.lowercase()
        return if (time.contains("morning") && !time.contains("mid")) {
            "Morning: Send a 'Good morning' text to a family member. ☀️"
        } else if (time.contains("mid-morning") || time == "mid morning") {
            "Mid-morning: Reach out to a colleague with a quick 'Thank you.' 🙏"
        } else if (time.contains("afternoon") && time.contains("snack")) {
            "Afternoon Snack Time: Send a quick 'thinking of you' message. 💭"
        } else if (time.contains("afternoon")) {
            "Afternoon: Share a funny meme or interesting link with a friend. 😂"
        } else if (time.contains("dinner") && !time.contains("after")) {
            "Dinner: Call a friend or relative for a 5-minute catch-up. 📞"
        } else if (time.contains("after") || time.contains("night") || time.contains("evening")) {
            "After Dinner / Night: Leave a thoughtful comment on a friend's post. 🌙"
        } else {
            "Hmm, I don't know '$timeOfDay'. Try: Morning, Mid-morning, Afternoon, Afternoon Snack Time, Dinner, After Dinner"
        }
    }
}
