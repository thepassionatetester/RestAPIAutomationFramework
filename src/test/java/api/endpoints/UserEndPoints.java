package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class UserEndPoints {
	
	public static Response createUser(User payLoad)
	{
		Response response = given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(payLoad)
		
		.when()
		.post(Routers.post_url);
		
		return response;
	}
	
	
	
	public static Response GetUser(String userName)
	{
		Response response = given()
		.accept(ContentType.JSON)
		.pathParam("username",userName )
		.when()
		.get(Routers.get_url);
		
		return response;
	}
	
	
	public static Response UpdateUser(String userName, User payLoad)
	{
		Response response = given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.pathParam("username", userName)
		.body(payLoad)
		
		.when()
		.put(Routers.put_url);
		
		return response;
	}
	
	public static Response DeleteUser(String userName)
	{
		Response response = given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.pathParam("username", userName)
		
		.when()
		.put(Routers.del_url);
		
		return response;
	}
}
