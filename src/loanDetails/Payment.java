package loanDetails;

/**
 * Payment.java
 * Copyright (C) 2014  Nyofu - C. Arden Gudger
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License,
 * with the "Linking Exception", which can be found at the license.txt
 * file in this program.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Arden Gudger 
 * @email ardengudger@gmail.com
 *
 */


/**
 * Payment class that maintains the value for each payment in the loan structure.
 * 
 * Read only, values set at creation.
 * 
 * @author Arden Gudger
 */
public class Payment {

	private int number = 0;
	private double principleInteratedValue = 0.0;
	private double additional = 0.0;
	private double intrest = 0.0;

	/**
	 * Creates a Payment for the loan
	 * 
	 * @param number - payment number must be less than the term
	 * @param principle - principle owed at this payment
	 * @param additional - additional value added when calculating this payment (pre not post)
	 * @param intrest - interst paid with this payment
	 */
	public Payment(int number, double base, double additional, double intrest){
		this.number = number;
		this.principleInteratedValue = base;
		this.additional = additional;
		this.intrest = intrest;
	}

	/**
	 * 
	 * 
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}



	/**
	 * Returns the principle fo the loan with this payment.
	 * 
	 * @return the principleInteratedValue
	 */
	public double getPricipleWithThisPayment() {
		return principleInteratedValue;
	}



	/**
	 * Returns the additional value for this payemnt.
	 * 
	 * 	The additional value will be the sum of any flat value or itemized value
	 * 
	 * @return the additional
	 */
	public double getAdditional() {
		return additional;
	}



	/**
	 * Returns the interest paid with this payment.
	 * 
	 * @return the interest
	 */
	public double getIntrest() {
		return intrest;
	}
	
	
	
}
