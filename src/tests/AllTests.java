package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ Brewery_Test.class, DiceTests.class, Fleet_Test.class, Ownable_Test.class, Street_Test.class })
public class AllTests {

}
