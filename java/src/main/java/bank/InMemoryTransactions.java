package bank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InMemoryTransactions implements Transactions {

	private List<Transaction> transactions;

	public InMemoryTransactions() {
		transactions = new ArrayList<>();
	}

	@Override
	public void deposit(float amount, LocalDate now) {
		transactions.add(new Deposit(amount, now));
	}

	@Override
	public void withdraw(float amount, LocalDate now) {
		transactions.add(new Withdraw(amount, now));
		
	}
	
	@Override
	public int count() {
		return transactions.size();
	}

	public List<Transaction> getTransactions() {
		return Collections.unmodifiableList(transactions);
	}

	@Override
	public String map(trandform) {
		return transactions.stream().reduce(trandform).get();
	}

}
