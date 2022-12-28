package lesson5;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoryService {
    @GET("http://localhost:8189/market/api/v1/categories/{id}")
    Call<GetCategoryResponse> getCategory(@Path("id") int id);
}
