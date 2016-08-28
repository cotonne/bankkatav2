package bank.domain

import java.util.Date

abstract class Transaction (val operation: Date) {
  def deposit: Double

  def withdraw: Double

  def balance(amount: Double): Double = amount + this.deposit - this.withdraw
}
