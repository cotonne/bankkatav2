package bank;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class TransactionsTest {

	private Transactions transactions = new InMemoryTransactions();
	
	@Before
	public void setUp() throws Exception {
	
	}

	@Test
	public void should_register_deposit() {
		LocalDate dateTime = LocalDate.now();
		transactions.deposit(12.0f, dateTime );
		Assertions.assertThat(transactions.count()).isEqualTo(1);
		Assertions.assertThat(transactions.getTransactions())
			.contains(new Deposit(12.0f, dateTime));
	}
	
	@Test
	public void should_register_withdraw() {
		LocalDate dateTime = LocalDate.now();
		transactions.withdraw(50.0f, dateTime );
		Assertions.assertThat(transactions.count()).isEqualTo(1);
		Assertions.assertThat(transactions.getTransactions())
			.contains(new Withdraw(50.0f, dateTime));
	}

	
}
