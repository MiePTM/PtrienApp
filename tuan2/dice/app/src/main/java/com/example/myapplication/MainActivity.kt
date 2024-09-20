package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var diceImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        diceImageView = findViewById(R.id.imageView)
        val rollButton: Button = findViewById(R.id.buttonOne)

        rollButton.setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {
        val diceRoll = Random.nextInt(1, 7)  // Random number between 1 and 6

        // Update the image based on the dice roll
        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        diceImageView.setImageResource(drawableResource)
    }
}
