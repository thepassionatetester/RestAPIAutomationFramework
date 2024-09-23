package api.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {
	
	Faker faker;
	User userPayLoad;
	
	@BeforeClass
	public void generateTestData()
	{
		faker = new Faker();
		userPayLoad = new User();
		
		userPayLoad.setId(faker.idNumber().hashCode());
		userPayLoad.setUsername(faker.name().username());
		userPayLoad.setFirstName(faker.name().firstName());
		userPayLoad.setLastName(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());
		userPayLoad.setPassword(faker.internet().password(5,8));
		userPayLoad.setPhone(faker.phoneNumber().cellPhone());
	}
	
	@Test(priority = 1)
	/* Validate POST response*/
	public void testCreateUser()
	{
		Response response = UserEndPoints.createUser(userPayLoad);
		
		//log response
		response.then().log().all();
		
		//validation(), expected);
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//Read user data to check if first name is updated
		
		Response responsePostUpdate = UserEndPoints.GetUser(this.userPayLoad.getUsername());
		
		System.out.println("After update user data.");
		
		responsePostUpdate.then().log().all();		
	}
	
	
	
	@Test(priority = 2)
	/* Validate PUT response*/
	public void testGetUser()
	{
		//Only first name is taken here as request body.
		userPayLoad.setFirstName(faker.name().firstName());
		
		//Call the Update method
		Response response = UserEndPoints.GetUser(this.userPayLoad.getUsername());
		
		//log the response 
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.getStatusCode(), 200);	
	}
	
	
	@Test (priority = 3)
	/* Validate PUT response*/
	public void testUpdateUser()
	{
		//Only first name is taken here as request body.
		userPayLoad.setFirstName(faker.name().firstName());
		
		//Call the Update method
		Response response = UserEndPoints.UpdateUser(this.userPayLoad.getUsername(), userPayLoad);
		
		//log the response 
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.getStatusCode(), 200);	
		
		//Read user data to check if first name is updated
		
		Response responsePostUpdate = UserEndPoints.GetUser(this.userPayLoad.getUsername());
		
		System.out.println("After update user data.");
		
		responsePostUpdate.then().log().all();		
	}

	
	@Test (priority = 4)
	/* Validate PUT response*/
	public void testDeleteUser()
	{
		
		//Call the Update method
		Response response = UserEndPoints.DeleteUser(this.userPayLoad.getUsername());
		
		//log the response 
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.getStatusCode(), 200);	
	}
}
