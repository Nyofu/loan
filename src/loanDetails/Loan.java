package loanDetails;

/**
 * Loan.java
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
 * Processes a loan.  This class was desinged to help itemize loan payments to see where the individual is 
 * 	in terms of paying off the loan.
 * 
 * @author Arden Gudger
 */
public class Loan {

	private int term;
	private double rate;
	private final double loanAmount;
	private double payment = 0.0;
	private double adjustedPrinciple;
	private double[] additionalPayments = null;
	private Payment[] payments= null;
	private double flatAidditional = 0.0;
	private double fixedMonlthlyPayment = 0.0;
	private boolean isProcessed = false;
	
	/**
	 * Params cannot be null.
	 * 
	 * @param term
	 * @param rate
	 * @param loanAmount
	 * @throws NullPointerException
	 */
	public Loan(final int term, final double rate, final double loanAmount) throws NullPointerException{
		this.term = term;
		this.rate = rate;
		this.loanAmount = loanAmount;
		this.adjustedPrinciple = loanAmount;
		this.additionalPayments = new double[term];
		this.payments = new Payment[term];




	
	}
	

	/**
	 * Processes the loan based on term, rate, and loan amount.
	 * 
	 * If the process has already been ran with out resetting the values return of false
	 * @return false is the process has not been ran and the loan was processed other wise true - process 
	 * 	wave previously ran.
	 */
	public boolean processLoan(){
		if(!isProcessed){
            int i = 0;
            for(Payment pmnt : this.payments){

                if(pmnt == null){
                    this.payments[i] = payment(this.additionalPayments[i] + flatAidditional , i);
                }
                else{
                    this.additionalPayments[i] = pmnt.getAdditional() + this.flatAidditional;
                    this.payments[i] = payment(pmnt == null ? this.flatAidditional : this.additionalPayments[i], i);
                }
                i++;
            }
		}
		return isProcessed;
	}
	
	
	/**
	 * Resets the values and re runs the loan process.
	 */
	public void resetAndProcessLoan(){
		isProcessed = false; 
		this.adjustedPrinciple = this.loanAmount;
		processLoan();
	}

    private Payment payment(int i){
        return payment(0.0, i);
    }


	private Payment payment( double additionalPrinciple, int i){
		return payment(monlthlyFixedPayment(), additionalPrinciple, i);
	}

		
	private Payment payment(double monthlyPayment, double additionalPrinciple, int payementNumber){

		this.payment = monthlyPayment;
		
		double currentIntrest = this.rate * this.adjustedPrinciple;
		double monthPrinc = this.payment - currentIntrest;
		
		this.adjustedPrinciple = this.adjustedPrinciple - monthPrinc;
		this.adjustedPrinciple = this.adjustedPrinciple - additionalPrinciple;
        return new Payment(payementNumber, this.adjustedPrinciple, additionalPrinciple, currentIntrest);
		
	}
	
	
	private double monlthlyFixedPayment(){
		/*
		 * Monthly fixed payment calculated with this formula
		 * 
		 * Equation used: L[c(1 + c)^n]/[(1 + c)^n - 1]
		 * 
		 */

		double rate_1 =1.0 + this.rate;
		double a = Math.pow(rate_1, this.term);

		double c = this.rate * a * this.loanAmount;
		double d = a - 1;
		 
		double monthlyPayment = c / d;
		this.fixedMonlthlyPayment = monthlyPayment;
		return monthlyPayment;
	}
	
	/**
	 * add additional payments.  If the array length does not match the term exception is trown.
	 * 
	 * @param additionalValues
	 */
	public void setAdditationPayments(double[] additionalValues){
		if(additionalValues.length != this.term){
			throw new IndexOutOfBoundsException("Addiditional Values size dose not match the term size");
		}
		else{
			for(int i = 0; i< this.additionalPayments.length; i++){
				double val = additionalValues[i];
				this.additionalPayments[i] = val;
			}
		}
		
	}
	
	/**
	 * adds an additional payment
	 * 
	 * @param additionalValue
	 * @param paymentIndex
	 */
	public void setAnAdditionalPayment(double additionalValue, int paymentIndex){
		this.additionalPayments[paymentIndex] = additionalValue;
	}
	
	
	/**
	 * Returns the additional amount for the loan.  Each month is the sum of the fixed and additional.
	 * 
	 * @return additional payments made.
	 */
	public double[] getAdditionalPayments(){
		return this.additionalPayments;
	}
	
	
	/**
	 * Adds an additional flat amount to the loan to be applied toward principle.
	 * 
	 * @param flatAditional
	 */
	public void addFlatAdditionalToEachMonth(double flatAditional){
		this.flatAidditional = flatAditional;
	}
	
	
	/**
	 * Returns all the payments made
	 * 
	 * @return all the payments made where index 0 is the first payment
	 */
	public Payment[] getPayments(){
		return this.payments;
	}
	
	/**
	 * Fixed montly payment bases on this formula.
	 * 		Equation used: L[c(1 + c)^n]/[(1 + c)^n - 1]
	 * 
	 * 
	 * @return the fixed monthly payment
	 */
	public double getFixedMonthlyPayment(){
		return this.fixedMonlthlyPayment;
	}
	
	/**
	 * How long the loan is for
	 * 
	 * @return the term of the loan
	 */
	public int getTerm(){
		return this.term;
	}

}
