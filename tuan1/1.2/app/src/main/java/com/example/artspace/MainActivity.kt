package com.example.artspace

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val artworks = listOf(
        Artwork(R.drawable.art1, "Robin", "ien", "2024"),
        Artwork(R.drawable.art2, "March 7th", "nirako mona", "2024"),
        Artwork(R.drawable.art3, "Qingque", "0 uoxou", "2024")
    )

    private var currentArtworkIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val artworkImageView: ImageView = findViewById(R.id.artworkImageView)
        val titleTextView: TextView = findViewById(R.id.titleTextView)
        val authorYearTextView: TextView = findViewById(R.id.authorYearTextView)
        val previousButton: Button = findViewById(R.id.previousButton)
        val nextButton: Button = findViewById(R.id.nextButton)

        // Function to update UI with the current artwork
        fun updateArtwork() {
            val artwork = artworks[currentArtworkIndex]
            artworkImageView.setImageResource(artwork.imageResId)
            titleTextView.text = artwork.title
            authorYearTextView.text = "${artwork.author} (${artwork.year})"
        }

        // Initial display
        updateArtwork()

        // Next button functionality
        nextButton.setOnClickListener {
            if (currentArtworkIndex < artworks.size - 1) {
                currentArtworkIndex++
            } else {
                currentArtworkIndex = 0
            }
            updateArtwork()
        }

        // Previous button functionality
        previousButton.setOnClickListener {
            if (currentArtworkIndex > 0) {
                currentArtworkIndex--
            } else {
                currentArtworkIndex = artworks.size - 1
            }
            updateArtwork()
        }
    }
}

data class Artwork(val imageResId: Int, val title: String, val author: String, val year: String)
