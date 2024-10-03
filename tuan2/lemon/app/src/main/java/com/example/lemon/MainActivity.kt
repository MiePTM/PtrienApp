package com.example.lemon

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var lemonTree: ImageView
    private lateinit var lemonImage: ImageView
    private lateinit var drinkGlass: ImageView
    private lateinit var instructionText: TextView

    private var squeezeCount = 0
    private var requiredSqueeze = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lemonTree = findViewById(R.id.lemonTree)
        lemonImage = findViewById(R.id.lemonImage)
        drinkGlass = findViewById(R.id.drinkGlass)
        instructionText = findViewById(R.id.instructionText)

        lemonTree.setOnClickListener { selectLemon() }
        lemonImage.setOnClickListener { squeezeLemon() }
        drinkGlass.setOnClickListener { drinkLemonade() }
    }

    private fun selectLemon() {
        lemonTree.visibility = View.GONE // Ẩn cây chanh
        lemonImage.setImageResource(R.drawable.lemon_squeeze) // Hiển thị quả chanh
        lemonImage.visibility = View.VISIBLE
        instructionText.text = "Keep tapping the lemon to squeeze it"
        requiredSqueeze = Random.nextInt(2, 6) // Số lần nhấn ngẫu nhiên
        squeezeCount = 0 // Đặt lại số lần nhấn
    }

    private fun squeezeLemon() {
        squeezeCount++
        if (squeezeCount >= requiredSqueeze) {
            lemonImage.visibility = View.GONE // Ẩn quả chanh
            drinkGlass.setImageResource(R.drawable.lemon_drink) // Hiển thị cốc chanh đầy nước
            drinkGlass.visibility = View.VISIBLE
            instructionText.text = "Tap the lemonade to drink it"
        }
    }

    private fun drinkLemonade() {
        if (drinkGlass.drawable.constantState == resources.getDrawable(R.drawable.lemon_drink).constantState) {
            // Nếu đang là cốc chanh đầy nước, chuyển sang cốc rỗng
            drinkGlass.setImageResource(R.drawable.lemon_restart) // Hiển thị cốc rỗng
            instructionText.text = "Tap the empty glass to start again"
        } else {
            // Reset lại game
            drinkGlass.visibility = View.GONE // Ẩn cốc
            lemonTree.visibility = View.VISIBLE // Hiển thị lại cây chanh
            instructionText.text = "Tap the lemon tree to select a lemon"
        }
    }
}
