package junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
/**
 * Test suit for all test cases.
 * @author shifuddin
 * @since 03.08.2019
 * @version 0.0.1
 */
@RunWith(Suite.class)
@SuiteClasses({ ExceptionHandlerUnitTest.class, HouseControllerUnitTest.class, HouseServiceUnitTest.class })
public class AllTests { 

}
