package agenda_groups;

public class Product {
	private int balance;
	private Item item;
	private boolean insufficientBalance;
	
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public boolean isInsufficientBalance() {
		return insufficientBalance;
	}
	public void setInsufficientBalance(boolean insufficientBalance) {
		this.insufficientBalance = insufficientBalance;
	}

}
