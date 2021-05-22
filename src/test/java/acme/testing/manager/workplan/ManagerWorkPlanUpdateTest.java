
package acme.testing.manager.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class ManagerWorkPlanUpdateTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updatePositive(final int recordIndex, final String title, final String startExecutionPeriod, final String endExecutionPeriod, final String share, final String totalWorkload, final String addTask, final String deleteTask,
		final String checkTasks) {
		super.signIn("manager1", "manager1");

		super.clickOnMenu("Manager", "Work plans list");

		super.clickOnListingRecord(recordIndex);

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("startExecutionPeriod", startExecutionPeriod);
		super.fillInputBoxIn("endExecutionPeriod", endExecutionPeriod);
		super.fillInputBoxIn("share", share);

		final By inputLocatorAddTask = By.name("addTask");

		if (addTask != null && super.exists(inputLocatorAddTask)) {
			super.fillInputBoxIn("addTask", addTask);
		}

		final By inputLocatorDeleteTask = By.name("deleteTask");

		if (deleteTask != null && super.exists(inputLocatorDeleteTask)) {
			super.fillInputBoxIn("deleteTask", deleteTask);
		}

		super.clickOnSubmitButton("Update");

		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, startExecutionPeriod);
		super.checkColumnHasValue(recordIndex, 2, endExecutionPeriod);
		super.checkColumnHasValue(recordIndex, 3, share);

		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("startExecutionPeriod", startExecutionPeriod);
		super.checkInputBoxHasValue("endExecutionPeriod", endExecutionPeriod);
		super.checkInputBoxHasValue("share", share);
		super.checkInputBoxHasValue("totalWorkload", totalWorkload);

		if (!checkTasks.isEmpty()) {

			By locator;
			locator = By.xpath(String.format("//button[normalize-space()='%s']", "View work plan tasks"));
			super.clickAndWait(locator);

			if (checkTasks.contains(".")) {
				final String tasksIds[] = checkTasks.split("\\.");

				for (int i = 0; i < tasksIds.length; i++) {
					super.clickOnListingRecord(i);
					super.checkInputBoxHasValue("id", tasksIds[i]);
					super.clickOnReturnButton("Return");
				}

			} else {
				super.clickOnListingRecord(0);
				super.checkInputBoxHasValue("id", checkTasks);
			}
		}

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void updateNegative(final int recordIndex, final String title, final String startExecutionPeriod, final String endExecutionPeriod, final String share, final String addTask) {
		super.signIn("manager1", "manager1");

		super.clickOnMenu("Manager", "Work plans list");
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("startExecutionPeriod", startExecutionPeriod);
		super.fillInputBoxIn("endExecutionPeriod", endExecutionPeriod);
		super.fillInputBoxIn("share", share);
		
		final By inputLocatorAddTask = By.name("addTask");

		if (addTask != null && super.exists(inputLocatorAddTask)) {
			super.fillInputBoxIn("addTask", addTask);
		}
		
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();

		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------

}
