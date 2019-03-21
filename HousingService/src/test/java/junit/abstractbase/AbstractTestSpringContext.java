package junit.abstractbase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import housingservice.HousingApplication;
import housingservice.model.Address;
import housingservice.model.House;
import housingservice.services.HouseService;
/**
 * Abstract class for Spring runner
 * @author shifuddin
 * @since 03.08.2019
 * @version 0.0.1
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HousingApplication.class)
@Profile("1&1test")
public abstract class AbstractTestSpringContext {
	protected MockMvc mockMvc;

	@Autowired
	protected WebApplicationContext webApplicationContext;
	
	@MockBean
	protected HouseService service;


	protected Address address1, address2;
	protected House house1, house2;
	@SuppressWarnings("rawtypes")
	protected ResponseEntity responseEntityHouse, responseEntityCoustomResponse;
	protected List<House> houseList;
	protected ObjectMapper objectMapper;
	
	/**
	 * Initialize variables
	 * @throws Exception
	 */
	@SuppressWarnings("serial")
	protected void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	    address1 = new Address("Hans-Leipelt-Str", (long) 7, "80805", "Munich", "Germany");
	    address2 = new Address("Hans-Leipelt-Str", (long)8, "80805", "Munich", "Germany");
	    
		house1 = new House("My House",address1, 3, 2.0, false, 0.4, 1.0, 20000.0, new Date(), false,
				1400.0, "Mansion", 2.5);
		house2 = new House("My House",address2, 3, 2.0, false, 0.4, 1.0, 20000.0, new Date(), false,
				1400.0, "Mansion", 2.5);
		houseList = new ArrayList<House>() {{
			add(house1);
			add(house2);
		}};
		objectMapper = new ObjectMapper();
	}
}
