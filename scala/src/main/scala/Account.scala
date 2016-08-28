package bank

import bank.domain._
import bank.printer._
import java.util.Date

class Account(val transactions: Transactions) {

  def deposit(amount: Double)(implicit clock: Clock): Unit = transactions.record(new Deposit(amount, clock.date))

  def withdraw(amount: Double)(implicit clock: Clock): Unit = transactions.record(new Withdraw(amount, clock.date))

  def printStatement(printer: Printer): String = printer.visit(this) + transactions.accept(printer)

}
