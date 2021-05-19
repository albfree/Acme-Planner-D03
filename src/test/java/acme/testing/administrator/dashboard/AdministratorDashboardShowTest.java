
package acme.testing.administrator.dashboard;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import com.google.common.collect.Lists;

import acme.testing.AcmePlannerTest;

public class AdministratorDashboardShowTest extends AcmePlannerTest {

	//Estos metodos necesitan loggear cada iteracion por lo que loggean más de 20 veces y me parece algo excesivo.
	
//	@ParameterizedTest
//	@CsvFileSource(resources = "/administrator/dashboard/show-positive-task.csv", encoding = "utf-8", numLinesToSkip = 1)
//	@Order(10)
//	public void showPositiveTasks(final String recordIndex, final String value) {
//
//		By locator;
//
//		locator = By.xpath("//table/tbody/tr[" + recordIndex + "]/td");
//
//		super.signIn("administrator", "administrator");
//
//		super.clickOnMenu("Administrator", "Dashboard");
//
//		final String rowValue = super.locateOne(locator).getText();
//
//		Assertions.assertEquals(rowValue, value);
//
//		super.signOut();
//	}

	//	@ParameterizedTest
	//	@CsvFileSource(resources = "/administrator/dashboard/show-positive-workplans.csv", encoding = "utf-8", numLinesToSkip = 1)
	//	@Order(20)
	//	public void showPositiveWorkplans( final String recordIndex, final String value) {
	//
	//		final By locator;
	//		
	//		locator = By.xpath("//table[2]/tbody/tr["+ recordIndex +  "]/td");
	//		
	//		super.signIn("administrator", "administrator");
	//		
	//		super.clickOnMenu("Administrator", "Dashboard");		
	//		
	//		final String rowValue = super.locateOne(locator).getText();
	//				
	//		Assertions.assertEquals(rowValue, value);
	//		
	//		super.signOut();
	//	}
	
	//Estos metodos solo necesitan loggear una vez como administrador por lo que van mucho más rapido
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/dashboard/show-positive-task-1iteracion.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void showPositiveTasksTable(final String expectedValues) {

		final List<String> values = Lists.newArrayList(expectedValues.split("-"));

		By locator;

		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Dashboard");

		for (int i = 1; i < values.size(); i++) {

			locator = By.xpath("//table[1]/tbody/tr[" + i + "]/td");

			final String rowValue = super.locateOne(locator).getText();

			Assertions.assertEquals(rowValue, values.get(i-1));

		}

		super.signOut();
	}


	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/dashboard/show-positive-workplans-1iteracion.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void showPositiveWorkplansTable(final String expectedValues) {

		final List<String> values = Lists.newArrayList(expectedValues.split("-"));

		By locator;

		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Dashboard");

		for (int i = 1; i < values.size(); i++) {

			locator = By.xpath("//table[2]/tbody/tr[" + i + "]/td");

			final String rowValue = super.locateOne(locator).getText();

			Assertions.assertEquals(rowValue, values.get(i-1));

		}

		super.signOut();
	}

}
