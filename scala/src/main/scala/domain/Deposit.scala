package bank.domain

import java.util.Date

class Deposit(val amount: Double, operation: Date) extends Transaction(operation){
  if(amount <= 0) throw new IllegalArgumentException("the number must be strictly positive.")

  override def deposit: Double = amount

  override def withdraw: Double = 0

  override def equals(that: Any): Boolean =
    that match {
      case that: Deposit => this.amount.equals(that.amount) && this.operation.equals(that.operation)
      case _ => false
   }

  override def hashCode:Int = {
    val prime = 31
    var result = 1
    result = prime * result + amount.hashCode;
    result = prime * result + operation.hashCode;
    return result
  }

  override def toString = s"Deposit of $amount"

}
