package lesson4;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RequestTest2 extends AbstractTest2 {
    @Test
    void getCuisine() {
        given().spec(requestSpecification)
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch?apiKey="+getApiKey()+"&cuisine={cuisine}&equipment={equipment}", "korean", "pan")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void getDiet() {
        given().spec(requestSpecification)
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch?apiKey="+getApiKey()+"&diet={diet}&maxFat={fat}", "paleo", 100)
                .then()
                .spec(responseSpecification);
    }
    @Test
    void getIntolerances() {
        given().spec(requestSpecification)
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch?apiKey="+getApiKey()+"&intolerances={intolerances}&cuisine={cuisine}", "soy, egg", "american")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void getIngredients() {
        given().spec(requestSpecification)
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch?apiKey="+getApiKey()+"&cuisine={cuisine}&includeIngredients={include}&excludeIngredients={exclude}", "italian", "tomato", "eggs")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void getType() {
        given().spec(requestSpecification)
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch?apiKey="+getApiKey()+"&type={type}", "sauce")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void postKorean() {
        given().spec(requestSpecification)
                .queryParam("apiKey", getApiKey())
                .formParam("title","kimchi")
                .formParam("language","en")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine?apiKey="+getApiKey())
                .then()
                .spec(responseSpecification);
    }
    @Test
    void postAmerican() {
        given().spec(requestSpecification)
                .queryParam("apiKey", getApiKey())
                .formParam("title","burger")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine?apiKey="+getApiKey())
                .then()
                .spec(responseSpecification);
    }
    @Test
    void postAfrican() {
        given().spec(requestSpecification)
                .queryParam("apiKey", getApiKey())
                .formParam("title","African Bean Soup")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine?apiKey="+getApiKey())
                .then()
                .spec(responseSpecification);
    }
    @Test
    void postBritish() {
        given().spec(requestSpecification)
                .queryParam("apiKey", getApiKey())
                .formParam("title","Beef Wellington")
                .formParam("ingredientList","meat")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine?apiKey="+getApiKey())
                .then()
                .spec(responseSpecification);
    }
    @Test
    void postChinese() {
        given().spec(requestSpecification)
                .queryParam("apiKey", getApiKey())
                .formParam("title","Skinny Veggie Fried Rice")
                .formParam("language","en")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine?apiKey="+getApiKey())
                .then()
                .spec(responseSpecification);
    }
    @Test
    void addMealTest() {
        String id = given().spec(requestSpecification)
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

        given().spec(requestSpecification)
                .queryParam("hash", "dd7fdb60c338d80f0557bc06c92bd23ea88ae553")
                .queryParam("apiKey", getApiKey())
                .delete("https://api.spoonacular.com/mealplanner/pitt/items/" + id)
                .then()
                .spec(responseSpecification);
    }
    @Test
    void postAddToShoppingList() {
        String id = given().spec(requestSpecification)
                .queryParam("hash", "dd7fdb60c338d80f0557bc06c92bd23ea88ae553")
                .body("{\n" +
                        "\t\"item\": \"1 package baking powder\",\n" +
                        "\t\"aisle\": \"Baking\",\n" +
                        "\t\"parse\": true\n" +
                        "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/pitt/shopping-list/items")
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();

        given().spec(requestSpecification)
                .queryParam("hash", "dd7fdb60c338d80f0557bc06c92bd23ea88ae553")
                .when()
                .get("https://api.spoonacular.com/mealplanner/pitt/shopping-list")
                .prettyPeek()
                .then()
                .spec(responseSpecification);

        given().spec(requestSpecification)
                .queryParam("hash", "dd7fdb60c338d80f0557bc06c92bd23ea88ae553")
                .body("{\n" +
                        "    \"username\": \"pitt\",\n" +
                        "    \"hash\": \"dd7fdb60c338d80f0557bc06c92bd23ea88ae553\"\n" +
                        "}")
                .when()
                .delete("https://api.spoonacular.com/mealplanner/pitt/shopping-list/items/" + id)
                .prettyPeek()
                .then()
                .spec(responseSpecification);
    }
}
