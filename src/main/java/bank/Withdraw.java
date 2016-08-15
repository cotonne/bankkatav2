package bank;

import java.time.LocalDate;
import java.util.Optional;

public class Withdraw extends Transaction {

	public Withdraw(float amount, LocalDate dateTime) {
		super(amount, dateTime);
	}

	@Override
	public Optional<Float> getDebit() {
		return Optional.of(amount);
	}

	@Override
	public Optional<Float> getCredit() {
		return Optional.empty();
	}

}
