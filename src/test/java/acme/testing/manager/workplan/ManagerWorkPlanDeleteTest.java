
package acme.testing.manager.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkPlanDeleteTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/delete.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void delete(final int recordIndex) {
		super.signIn("manager1", "manager1");

		super.clickOnMenu("Manager", "Work plans list");

		super.clickOnListingRecord(recordIndex);

		super.clickOnSubmitButton("Delete");

		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------

}
