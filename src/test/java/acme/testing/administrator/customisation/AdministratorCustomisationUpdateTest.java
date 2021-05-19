/*
 * EmployerApplicationUpdateTest.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing.administrator.customisation;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorCustomisationUpdateTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/customisation/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void updatePositive(final String spamWords, final String treshold ) {		
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Customisation parameters");		
				
		super.fillInputBoxIn("spamWords", spamWords);
		super.fillInputBoxIn("threshold", treshold);
		
		super.clickOnSubmitButton("Update");
		
		super.clickOnMenu("Administrator", "Customisation parameters");	
		
		super.checkInputBoxHasValue("spamWords", spamWords);
		super.checkInputBoxHasValue("threshold", treshold);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/customisation/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void updateNegative(final String spamWords, final String treshold ) {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Customisation parameters");		
			
		super.fillInputBoxIn("spamWords", spamWords);
		super.fillInputBoxIn("threshold", treshold);
		
		super.clickOnSubmitButton("Update");

		super.checkErrorsExist();
		
		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------


}
