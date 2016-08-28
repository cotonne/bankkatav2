package bank;

import java.time.LocalDate;
import java.util.Optional;

public class Deposit extends Transaction {

	public Deposit(float amount, LocalDate dateTime) {
		super(amount, dateTime);
	}

	@Override
	public Optional<Float> getDebit() {
		return Optional.empty();
	}

	@Override
	public Optional<Float> getCredit() {
		return Optional.of(amount);
	}

}
