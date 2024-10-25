package com.example.coroutine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    private val _result = MutableStateFlow("Chưa thực hiện tác vụ")
    val result: StateFlow<String> = _result

    fun startTasks() {
        viewModelScope.launch {
            _result.value = "Đang thực hiện tác vụ..."
            val result1 = loadDataFromNetwork()
            val result2 = queryDatabase()
            val result3 = performIOOperation()

            _result.value = "$result1\n$result2\n$result3"
        }
    }

    private suspend fun loadDataFromNetwork(): String {
        delay(2000) // Mô phỏng thời gian tải dữ liệu
        return "Dữ liệu đã tải từ mạng."
    }

    private suspend fun queryDatabase(): String {
        delay(1500) // Mô phỏng thời gian truy vấn cơ sở dữ liệu
        return "Truy vấn cơ sở dữ liệu hoàn thành."
    }

    private suspend fun performIOOperation(): String {
        delay(1000) // Mô phỏng thời gian thực hiện tác vụ I/O
        return "Tác vụ I/O hoàn thành."
    }
}
