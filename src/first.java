import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class first {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    RestAssured.baseURI="https://reqres.in/";
    given().
            param("page","2").
            when().
            get("api/users").
            then().assertThat().
            statusCode(200);
	}

}
