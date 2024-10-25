import retrofit2.Call
import retrofit2.http.GET

interface MyApiService {
    @GET("your-endpoint") // Thay thế bằng endpoint thực tế
    fun getData(): Call<MyApiResponse>
}

data class MyApiResponse(val data: String) // Thay thế cho cấu trúc dữ liệu của bạn
