package bank

import org.scalatest._
import bank.domain._
import bank.simple._
import bank.printer._
import org.scalamock.scalatest.MockFactory
import java.text.SimpleDateFormat
import java.util.Date

class PrintStatementTest extends FunSuite  with BeforeAndAfter with MockFactory {

  var printer: StatementPrinter = _
  var anAccount: Account = _
  var format: SimpleDateFormat = _
  var transactions: InMemoryTransactions = _

  before {
    printer = new StatementPrinter
    format = new SimpleDateFormat("dd/mm/yyyy")
    anAccount = new Account(new InMemoryTransactions)
    transactions = new InMemoryTransactions
  }

  test("should only print headers when no transactions is done") {
    val statement = printer.visit(anAccount)

    assert(statement === "date || credit || debit || balance")
  }

  test("should print a valid statement for a deposit and a withdraw") {
    transactions.record(new Deposit(1000.0, format.parse("10/01/2012")))
    transactions.record(new Withdraw(400.0, format.parse("11/01/2012")))

    val statement = printer.visit(transactions)

    assert(statement === """
11/01/2012 || || 400.00 || 600.00
10/01/2012 || 1000.00 || || 1000.00""")
  }

}
