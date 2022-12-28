package lesson5;

import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateProductTest {
    static ProductService productService;
    Product product = null;

    @BeforeAll
    static void beforeAll() {
        productService = RetroUtils.getRetrofit()
                .create(ProductService.class);
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle("Coconut")
                .withCategoryTitle("Food")
                .withPrice(400);
    }

    @Test
    void createProductInFoodCategoryTest() throws IOException {
        Response<Product> response = productService.createProduct(product)
                .execute();
        assertThat(response.body().getTitle(), equalTo("Coconut"));
    }

    @Test
    void modifyProduct() throws IOException {
        Response<Product> response = productService.modifyProduct(new Product(4,"Fresh Meat",5000,"Food"))
                .execute();
        assertThat(response.body().getTitle(), equalTo("Fresh Meat"));
    }
    @Test
    void getProductById() throws IOException {
        Response<Product> response = productService.getProductById(1)
                .execute();
        assertThat(response.body().getTitle(), equalTo("Milk"));
    }

    @Test
    void getProducts() throws IOException {
        Response<ResponseBody> response = productService.getProducts()
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

    @Test
    void deleteProduct() throws IOException {
        Response<ResponseBody> response = productService.deleteProduct(1)
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
