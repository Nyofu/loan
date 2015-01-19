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
 * @author Arden
 *
 */
/**
 * @author Arden
 *
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
	
	public void processLoan(){
		int i = 0;
		for(Payment pmnt : this.payments){
			
			if(pmnt == null){
				payment((0.0 + this.flatAidditional), i);
			}
			else{
				payment(pmnt == null ? this.flatAidditional : (pmnt.getAdditional() + this.flatAidditional), i);
				this.additionalPayments[i] = pmnt.getAdditional() + this.flatAidditional;
			}
		
			i++;
		}
	}
	
	
	public void resetAndProcessLoan(){
		this.term = term;
		this.rate = rate;
		this.adjustedPrinciple = this.loanAmount;
		processLoan();
	}
	
	private void payment( double additionalPrinciple, int i){
		payment(monlthlyFixedPayment(), additionalPrinciple, i);
	}

		
	private void payment(double monthlyPayment, double additionalPrinciple, int payementNumber){

		this.payment = monthlyPayment;
		
		double currentIntrest = this.rate * this.adjustedPrinciple;
		double monthPrinc = this.payment - currentIntrest;
		
		this.adjustedPrinciple = this.adjustedPrinciple - monthPrinc;
		this.adjustedPrinciple = this.adjustedPrinciple - (additionalPrinciple);
		
		payments[payementNumber] = new Payment(payementNumber, this.adjustedPrinciple, additionalPrinciple, currentIntrest);
		
	}
	
	
	private double monlthlyFixedPayment(){
//		L[c(1 + c)^n]/[(1 + c)^n - 1]
		double rate_1 =1.0 + this.rate;
		double a = Math.pow(rate_1, this.term);

		double c = this.rate * a * this.loanAmount;
		double d = a - 1;
		 
		double monthlyPayment = c / d;
		this.fixedMonlthlyPayment = monthlyPayment;
		return monthlyPayment;
	}
	
	public void setAdditationPayments(double[] additionalValues){
		if(additionalValues.length != this.term){
			throw new IndexOutOfBoundsException("Addiditional Values size dose not match the term size");
		}
		else{
			synchronized (this.additionalPayments) {
				for(int i = 0; i< this.additionalPayments.length; i++){
					this.additionalPayments[i] = additionalValues[i];
				}
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
	
	
	public double[] getAdditionalPayments(){
		return this.additionalPayments;
	}
	
	
	public void addFlatAdditionalToEachMonth(double flatAditional){
		this.flatAidditional = flatAditional;
	}
	
	
	public Payment[] getPayments(){
		return this.payments;
	}
	
	public double getFixedMonthlyPayment(){
		return this.fixedMonlthlyPayment;
	}
	
	public int getTerm(){
		return this.term;
	}

}
