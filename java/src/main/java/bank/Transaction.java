package bank;

import java.time.LocalDate;
import java.util.Optional;

public abstract class Transaction {

	protected float amount;
	private LocalDate operationDate;

	public Transaction(float amount, LocalDate dateTime) {
		this.amount = amount;
		this.operationDate = dateTime;
	}

	public LocalDate getOperationDate() {
		return operationDate;
	}
	
	public abstract Optional<Float> getDebit();
	public abstract Optional<Float> getCredit();
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(amount);
		result = prime * result + ((operationDate == null) ? 0 : operationDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
			return false;
		if (operationDate == null) {
			if (other.operationDate != null)
				return false;
		} else if (!operationDate.equals(other.operationDate))
			return false;
		return true;
	}
	
	

}
