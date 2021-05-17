package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskCreateTest extends AcmePlannerTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String title, final String startPeriod, final String endPeriod, final String workload, final String description, final String visibility, final String link) {
		super.signIn("manager1", "manager1");

		super.clickOnMenu("Manager", "Create task");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("startExecutionPeriod", startPeriod);
		super.fillInputBoxIn("endExecutionPeriod", endPeriod);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("share", visibility);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmitButton("Create");

		super.clickOnMenu("Manager", "Tasks list");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, startPeriod);
		super.checkColumnHasValue(recordIndex, 2, endPeriod);
		super.checkColumnHasValue(recordIndex, 3, workload);

		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("startExecutionPeriod", startPeriod);
		super.checkInputBoxHasValue("endExecutionPeriod", endPeriod);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("share", visibility);
		super.checkInputBoxHasValue("link", link);

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final int recordIndex, final String title, final String startPeriod, final String endPeriod, final String workload, final String description, final String visibility, final String link) {
		super.signIn("manager1", "manager1");

		super.clickOnMenu("Manager", "Create task");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("startExecutionPeriod", startPeriod);
		super.fillInputBoxIn("endExecutionPeriod", endPeriod);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("share", visibility);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmitButton("Create");

		super.checkErrorsExist();

		super.signOut();
	}
}
