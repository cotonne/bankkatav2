package bank;

import java.time.LocalDate;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public interface Transactions {

	void deposit(float amount, LocalDate now);

	void withdraw(float amount, LocalDate now);

	int count();

	List<Transaction> getTransactions();
	
	String map(BinaryOperator<Transaction> trandform);

}
