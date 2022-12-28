package lesson5;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetCategoryTest {
    static CategoryService categoryService;
    @BeforeAll
    static void beforeAll() {
        categoryService = RetroUtils.getRetrofit().create(CategoryService.class);
    }

    @Test
    void getCategoryByIdPositiveTest() throws IOException {
        Response<GetCategoryResponse> response = categoryService.getCategory(2)
                .execute();

        assertThat(response.body().getId(), equalTo(2));
        assertThat(response.body().getTitle(), equalTo("Electronic"));



    }
}
