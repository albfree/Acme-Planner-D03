package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskDeleteTest extends AcmePlannerTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deletePositive(final int recordIndex, final String title, final String startPeriod, final String endPeriod, final String workload, final String nextTitle) {
		super.signIn("manager1", "manager1");

		super.clickOnMenu("Manager", "Tasks list");
		
		super.checkNotPanicExists();
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, startPeriod);
		super.checkColumnHasValue(recordIndex, 2, endPeriod);
		super.checkColumnHasValue(recordIndex, 3, workload);
		
		super.clickOnListingRecord(recordIndex);

		super.clickOnSubmitButton("Delete");

		super.clickOnMenu("Manager", "Tasks list");
		
		super.checkColumnHasValue(recordIndex, 0, nextTitle);

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/delete-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void deleteNegative(final int recordIndex, final String title, final String startPeriod, final String endPeriod, final String workload) {
		super.signIn("manager1", "manager1");

		super.clickOnMenu("Manager", "Tasks list");
		
		super.checkNotPanicExists();
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, startPeriod);
		super.checkColumnHasValue(recordIndex, 2, endPeriod);
		super.checkColumnHasValue(recordIndex, 3, workload);
		
		super.clickOnListingRecord(recordIndex);

		super.clickOnSubmitButton("Delete");

		super.checkErrorsExist();

		super.signOut();
	}
	
	@Test
	@Order(30)
	public void authorizeNegative() {
		super.signIn("administrator", "administrator");
		
		super.navigate("/manager/task/delete", null);
		
		super.checkPanicExists();
		
		super.signOut();
	}
}
