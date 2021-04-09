import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class twitter {
	
	String ConsumerKey="JAtISEfmgUG9AePDN9R8JBB5m";
	String ConsumerSecret="wo1isYf2MWz0lrMkybTaYIUZTcxC3RX4rE9erIS6ZZyhp5uKe5";
	String Token="1366789726049431553-FN3IM81mYzQYIZ2l2ATQ2lgwJMEtLj";
	String TokenSecret="wK5yhItTOCrjFNsQHyPwG46fHMCt0d4SKxWpZtKCK6rMc";

	private static Logger log=LogManager.getLogger(twitter.class.getName());
@Test
public void getTweet()
{
	RestAssured.baseURI="https://api.twitter.com/1.1/statuses/";
	Response res=given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret).
	queryParam("count","1")
	.when().get("/home_timeline.json").then().extract().response();
	String response=res.asString();
	System.out.println(response);
	JsonPath js=new JsonPath(response);
	String id=js.get("id").toString();
	System.out.println(id);
	String text=js.get("text").toString();
	System.out.println(text);	
}


@Test
public void createTweet()
{
RestAssured.baseURI="https://api.twitter.com/1.1/statuses/";
Response res=given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret).
queryParam("status","This is my tweet through automation")
.when().post("/update.json").then().extract().response();
String response=res.asString();
System.out.println(response);
JsonPath js=new JsonPath(response);
String id=js.get("id").toString();
System.out.println(id);
String text=js.get("text").toString();
System.out.println(text);	

given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret).
when().post("/destroy/"+id+".json").then().assertThat().statusCode(200);
}

}
