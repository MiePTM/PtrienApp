package com.example.ui_test

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertIsDisplayed
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun button_isDisplayed_and_clickable() {
        // Thiết lập nội dung kiểm thử
        composeTestRule.setContent {
            TestButtonScreen()
        }

        // Kiểm tra xem nút "Click Me" có hiển thị hay không
        composeTestRule.onNodeWithText("Click Me").assertIsDisplayed()

        // Giả lập việc bấm nút
        composeTestRule.onNodeWithText("Click Me").performClick()

        // Kiểm tra sau khi bấm nút, văn bản chuyển thành "Button Clicked"
        composeTestRule.onNodeWithText("Button Clicked").assertIsDisplayed()
    }
}
