package junit;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import housingservice.model.House;
import housingservice.response.CustomResponse;
import junit.abstractbase.AbstractTestSpringContext;
/**
 * Test case file to unit test House controller
 * @author shifuddin
 * @since 03.08.2019
 * @version 0.0.1
 */
@SuppressWarnings("unchecked")
public class HouseControllerUnitTest extends AbstractTestSpringContext {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * Purpose: Test the GET API for an existing House. Scenario: 1. Mock getHouse
	 * method of house service with responseEntity<House>. 2. Perform GET operation
	 * 3. Match Http status and house ID.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAPI() throws Exception {
		house1.setHouseId("H-0001");
		responseEntityHouse = new ResponseEntity<>(house1, HttpStatus.OK);
		when(service.getHouse("H-0001")).thenReturn(responseEntityHouse);
		mockMvc.perform(get("/api/v1/house/H-0001").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.houseId", is(house1.getHouseId())));
	}

	/**
	 * Purpose: Test the GET API for an non existing House. Scenario: 1. Mock
	 * getHouse method of house service with responseEntity<CustomResponse>. 2.
	 * Perform GET operation 3. Match Http status and house ID.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAPIDoesNotExists() throws Exception {
		responseEntityCoustomResponse = new ResponseEntity<>(
				new CustomResponse("House", "H-0001", "Resource not found"), HttpStatus.NOT_FOUND);

		when(service.getHouse("H-0001")).thenReturn(responseEntityCoustomResponse);
		mockMvc.perform(get("/api/v1/house/H-0001").accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isNotFound()).andExpect(jsonPath("$.identifier", is("H-0001")));
	}

	/**
	 * Purpose: Test the GET ALL API. Scenario: 1. Mock getAllHouse method of house
	 * service with responseEntity<HouseList>. 2. Perform GET operation 3. Match
	 * Http status and list size and houseID.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAllAPI() throws Exception {
		house1.setHouseId("H-0001");
		house2.setHouseId("H-0002");
		responseEntityHouse = new ResponseEntity<>(houseList, HttpStatus.OK);
		when(service.getAllHouse()).thenReturn(responseEntityHouse);
		mockMvc.perform(get("/api/v1/houses").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].houseId", is("H-0001")))
				.andExpect(jsonPath("$[1].houseId", is("H-0002")));
	}

	/**
	 * Purpose: Test the POST API. Scenario: 1. Mock addHouse method of house
	 * service with responseEntity<Custom Response>. 2. Perform POST operation 3.
	 * Match Http status and return message.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPostAPI() throws Exception {

		responseEntityCoustomResponse = new ResponseEntity<>(
				new CustomResponse("House", "H-0001", "Successfully created resource"), HttpStatus.CREATED);
		when(service.addNewHouse(any(House.class))).thenReturn(responseEntityCoustomResponse);
		mockMvc.perform(post("/api/v1/house").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(house1)))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.message", is("Successfully created resource")));
	}

	/**
	 * Purpose: Test the POST API with existing house. Scenario: 1. Mock addHouse
	 * method of house service with responseEntity<Custom Response>. 2. Perform POST
	 * operation 3. Match Http status and return message.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPostAPIAlreadyExists() throws Exception {
		house1.setHouseId("H-0001");

		responseEntityCoustomResponse = new ResponseEntity<>(
				new CustomResponse("House", house1.getHouseId(), "House with the same address already exists!"),
				HttpStatus.CONFLICT);
		when(service.addNewHouse(any(House.class))).thenReturn(responseEntityCoustomResponse);
		mockMvc.perform(post("/api/v1/house").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(house1)))
				.andExpect(status().isConflict())
				.andExpect(jsonPath("$.message", is("House with the same address already exists!")));
	}

	/**
	 * Purpose: Test the DELETE API. Scenario: 1. Mock deleteHouse method of house
	 * service with responseEntity<Custom Response>. 2. Perform DELETE operation 3.
	 * Match Http status and return message.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteAPI() throws Exception {
		house1.setHouseId("H-0001");

		responseEntityCoustomResponse = new ResponseEntity<>(
				new CustomResponse("House", house1.getHouseId(), "Successfully deleted resource"), HttpStatus.ACCEPTED);
		when(service.deleteHouse(house1.getHouseId())).thenReturn(responseEntityCoustomResponse);
		mockMvc.perform(delete("/api/v1/house/H-0001").accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isAccepted()).andExpect(jsonPath("$.message", is("Successfully deleted resource")));
	}

	/**
	 * Purpose: Test the DELETE API with non existing house. Scenario: 1. Mock
	 * deleteHouse method of house service with responseEntity<Custom Response>. 2.
	 * Perform DELETE operation 3. Match Http status and return message.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteAPIDoesNotExists() throws Exception {
		house1.setHouseId("H-0001");

		responseEntityCoustomResponse = new ResponseEntity<>(
				new CustomResponse("House", house1.getHouseId(), "Resource not found"), HttpStatus.NOT_FOUND);
		when(service.deleteHouse(house1.getHouseId())).thenReturn(responseEntityCoustomResponse);
		mockMvc.perform(delete("/api/v1/house/H-0001").accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isNotFound()).andExpect(jsonPath("$.message", is("Resource not found")));
	}

	/**
	 * Purpose: Test the PUT API. Scenario: 1. Mock deleteHouse method of house
	 * service with responseEntity<Custom Response>. 2. Perform PUT operation 3.
	 * Match Http status and return message.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPutAPI() throws Exception {
		house1.setHouseId("H-0001");

		responseEntityCoustomResponse = new ResponseEntity<>(
				new CustomResponse("House", house1.getHouseId(), "Successfully updated resouce"), HttpStatus.OK);
		when(service.updateHouse(anyString(), any(House.class))).thenReturn(responseEntityCoustomResponse);
		mockMvc.perform(put("/api/v1/house/H-0001").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(house1)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.message", is("Successfully updated resouce")));
	}

	/**
	 * Purpose: Test the PUT API with non existing resource. Scenario: 1. Mock
	 * deleteHouse method of house service with responseEntity<Custom Response>. 2.
	 * Perform PUT operation 3. Match Http status and return message.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPutAPIResourceDoesNotExists() throws Exception {
		house1.setHouseId("H-0001");

		responseEntityCoustomResponse = new ResponseEntity<>(
				new CustomResponse("House", house1.getHouseId(), "Resource not found"), HttpStatus.NO_CONTENT);
		when(service.updateHouse(anyString(), any(House.class))).thenReturn(responseEntityCoustomResponse);
		mockMvc.perform(put("/api/v1/house/H-0001").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(house1)))
				.andExpect(status().isNoContent()).andExpect(jsonPath("$.message", is("Resource not found")));
	}

}
