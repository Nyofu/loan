package loanDetails;

public class Payment {

	private int number = 0;
	private double base = 0.0;
	private double additional = 0.0;
	private double intrest = 0.0;
	
	
	

	public Payment(int number, double base, double additional, double intrest){
		this.number = number;
		this.base = base;
		this.additional = additional;
		this.intrest = intrest;
	}



	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}



	/**
	 * @return the base
	 */
	public double getBase() {
		return base;
	}



	/**
	 * @return the additional
	 */
	public double getAdditional() {
		return additional;
	}



	/**
	 * @return the intrest
	 */
	public double getIntrest() {
		return intrest;
	}
	
	
	
}
