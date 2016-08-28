package bank;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Account {

	private Clock clock;
	private Transactions transactions = new InMemoryTransactions();
	
	public void deposit(float amount) {
		transactions.deposit(amount, clock.now());
	}

	public void withdraw(float amount) {
		transactions.withdraw(amount, clock.now());
	}

	public String printStatement() {
		List<String> rows = transactions.map(this::toRow);
		return rows.stream().collect(Collectors.joining("\n"));
	}
	
	public String toRow(Transaction transaction){
		StringJoiner joiner = new StringJoiner(" || ");
		return joiner.add(getDateString(transaction))
				.add(transaction.getCredit().map(String::valueOf).orElse(""))
				.add(transaction.getDebit().map(String::valueOf).orElse(""))
				.toString();
	}

	private String getDateString(Transaction transaction) {
		return transaction.getOperationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
}
