package lesson3;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RequestTest extends AbstractTest {
    @Test
    void getCuisine() {
        given()
                .when()
                .get(getBaseUrl()+"/recipes/complexSearch?apiKey="+getApiKey()+"&cuisine={cuisine}&equipment={equipment}", "korean", "pan")
                .then()
                .statusCode(200);
    }
    @Test
    void getDiet() {
        given()
                .when()
                .get(getBaseUrl()+"/recipes/complexSearch?apiKey="+getApiKey()+"&diet={diet}&maxFat={fat}", "paleo", 100)
                .then()
                .statusCode(200);
    }
    @Test
    void getIntolerances() {
        given()
                .when()
                .get(getBaseUrl()+"/recipes/complexSearch?apiKey="+getApiKey()+"&intolerances={intolerances}&cuisine={cuisine}", "soy, egg", "american")
                .then()
                .statusCode(200);
    }
    @Test
    void getIngredients() {
        given()
                .when()
                .get(getBaseUrl()+"/recipes/complexSearch?apiKey="+getApiKey()+"&cuisine={cuisine}&includeIngredients={include}&excludeIngredients={exclude}", "italian", "tomato", "eggs")
                .then()
                .statusCode(200);
    }
    @Test
    void getType() {
        given()
                .when()
                .get(getBaseUrl()+"/recipes/complexSearch?apiKey="+getApiKey()+"&type={type}", "sauce")
                .then()
                .statusCode(200);
    }
    @Test
    void postKorean() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","kimchi")
                .formParam("language","en")
                .when()
                .post(getBaseUrl()+"/recipes/cuisine?apiKey="+getApiKey())
                .then()
                .statusCode(200);
    }
    @Test
    void postAmerican() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","burger")
                .when()
                .post(getBaseUrl()+"/recipes/cuisine?apiKey="+getApiKey())
                .then()
                .statusCode(200);
    }
    @Test
    void postAfrican() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","African Bean Soup")
                .when()
                .post(getBaseUrl()+"/recipes/cuisine?apiKey="+getApiKey())
                .then()
                .statusCode(200);
    }
    @Test
    void postBritish() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Beef Wellington")
                .formParam("ingredientList","meat")
                .when()
                .post(getBaseUrl()+"/recipes/cuisine?apiKey="+getApiKey())
                .then()
                .statusCode(200);
    }
    @Test
    void postChinese() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Skinny Veggie Fried Rice")
                .formParam("language","en")
                .when()
                .post(getBaseUrl()+"/recipes/cuisine?apiKey="+getApiKey())
                .then()
                .statusCode(200);
    }
    @Test
    void addMealTest() {
        String id = given()
                .queryParam("hash", "dd7fdb60c338d80f0557bc06c92bd23ea88ae553")
                .queryParam("apiKey", getApiKey())
                .body("{\n"
                        + " \"date\": 1644881179,\n"
                        + " \"slot\": 1,\n"
                        + " \"position\": 0,\n"
                        + " \"type\": \"INGREDIENTS\",\n"
                        + " \"value\": {\n"
                        + " \"ingredients\": [\n"
                        + " {\n"
                        + " \"name\": \"2 eggs\"\n"
                        + " }\n"
                        + " ]\n"
                        + " }\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/pitt/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();

        given()
                .queryParam("hash", "dd7fdb60c338d80f0557bc06c92bd23ea88ae553")
                .queryParam("apiKey", getApiKey())
                .delete("https://api.spoonacular.com/mealplanner/pitt/items/" + id)
                .then()
                .statusCode(200);
    }
}
