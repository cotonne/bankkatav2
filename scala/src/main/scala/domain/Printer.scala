package bank.domain

import bank._

trait Printer {
  def visit(anAccount: Account) : String
  def visit(transactions: Transactions) : String
  def visit(aTransaction: Transaction) : String
}
