package bank;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {
	@InjectMocks
	private Account account;
	@Mock
	private Clock clock;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void should_print_the_history_of_movements() {
		given_a_deposit_of(1000,"10-01-2012");
		and_a_deposit_of(2000,"13-01-2012");
		and_a_withdraw_of(500,"14-01-2012");
		
		assertThat(accountStatement())
			.as("The history is invalid")
			.containsSequence(
				"date || credit || debit || balance",
				"14/01/2012 || || 500.00 || 2500.00",
				"13/01/2012 || 2000.00 || || 3000.00",
				"10/01/2012 || 1000.00 || || 1000.00");
	}

	private String[] accountStatement() {
		return account.printStatement().split("\n");
	}

	private void and_a_withdraw_of(float amount, String date) {
		mockDate(date);
		account.withdraw(amount);
	}

	private void and_a_deposit_of(float amount, String date) {
		mockDate(date);
		account.withdraw(amount);
	}

	private void given_a_deposit_of(float amount,String date) {
		mockDate(date);
		account.deposit(amount);
	}

	private void mockDate(String date) {
		Mockito.when(clock.now()).thenReturn(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
	}
}
