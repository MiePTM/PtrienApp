package com.example.internet

import MyApiResponse
import MyApiService
import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.Data
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        return try {
            val response = fetchDataFromApi()
            if (response != null) {
                Log.d("NetworkWorker", "Data received: ${response.data}")

                // Tạo Data để trả về
                val outputData = Data.Builder()
                    .putString("result", "success")
                    .putString("data", response.data.toString())
                    .build()

                Result.success(outputData)
            } else {
                Log.d("NetworkWorker", "No data received")

                // Tạo Data để trả về trong trường hợp không có dữ liệu
                val outputData = Data.Builder()
                    .putString("result", "failure")
                    .putString("error", "No data received")
                    .build()

                Result.failure(outputData)
            }
        } catch (e: Exception) {
            Log.e("NetworkWorker", "Error fetching data", e)

            // Tạo Data để trả về trong trường hợp có lỗi
            val outputData = Data.Builder()
                .putString("result", "failure")
                .putString("error", e.message ?: "Unknown error")
                .build()

            Result.failure(outputData)
        }
    }

    private fun fetchDataFromApi(): MyApiResponse? {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.example.com/") // Thay thế bằng URL API của bạn
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(MyApiService::class.java)

        return try {
            val response = api.getData().execute()
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}
