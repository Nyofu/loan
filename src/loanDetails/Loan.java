package loanDetails;

public class Loan {

	private int term;
	private double rate;
	private final double loanAmount;
	private double payment = 0.0;
	private double adjustedPrinciple;
	private double[] additionalPayments = null;
	private Payment[] payments= null;
	private double flatAidditional = 0.0;
	
	public int getTerm(){
		return this.term;
	}
		
	
	
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
	
	
	public void reProcessLoanWithAditionalPayments(){
		this.term = term;
		this.rate = rate;
		this.adjustedPrinciple = loanAmount;
		processLoan();
		
	}
	
	public void payment( double additionalPrinciple, int i){
		payment(monlthlyFixedPayment(), additionalPrinciple, i);
	}
	
	
	
	public void payment(double monthlyPayment, double additionalPrinciple, int payementNumber){

		this.payment = monthlyPayment;
		
		double currentIntrest = this.rate * adjustedPrinciple;
		double monthPrinc = this.payment - currentIntrest;
		
		adjustedPrinciple = adjustedPrinciple - monthPrinc;
		adjustedPrinciple = adjustedPrinciple - (additionalPrinciple);
		
		payments[payementNumber] = new Payment(payementNumber, adjustedPrinciple, additionalPrinciple, currentIntrest);
		
	}
	
	
	public double monlthlyFixedPayment(){
//		L[c(1 + c)^n]/[(1 + c)^n - 1]
		double rate_1 =1.0 + this.rate;
		double a = Math.pow(rate_1, this.term);

		double c = this.rate * a * this.loanAmount;
		double d = a - 1;
		 
		double monthlyPayment = c / d;
		return monthlyPayment;
	}
	
	public double[] getAdditionalPayments(){
		return this.additionalPayments;
	}
	
	
	public void addFlatAdditionalToEachMonth(double flatAditional){
		this.flatAidditional = flatAditional;
	}
	
	
	public Payment[] getPayments(){
		return payments;
	}

}
