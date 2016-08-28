package bank.domain

import java.util.Date

class Withdraw(val amount: Double, operation: Date) extends Transaction(operation) {
  if(amount <= 0) throw new IllegalArgumentException("the number must be strictly positive.")

  def deposit: Double = 0.0

  def withdraw: Double = amount

  override def equals(that: Any): Boolean =
    that match {
      case that: Withdraw => this.amount.equals(that.amount)
      case _ => false
   }

  override def hashCode:Int = {
    val prime = 31
    var result = 1
    result = prime * result + amount.hashCode;
    return result
  }

  override def toString = s"Withdraw of $amount"

}
