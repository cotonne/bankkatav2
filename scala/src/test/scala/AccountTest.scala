package bank

import bank.domain._
import org.scalatest._
import org.scalamock.scalatest.MockFactory
import java.util.Date

class AccountTest extends FunSuite with BeforeAndAfter with MockFactory {

  var anAccount: Account  = _
  var transactions: Transactions = _
  var printer: Printer = _
  val aDate: Date = new Date
  implicit object clock extends Clock {
    override def date = aDate
  }

  before {
    printer = mock[Printer]
    transactions = mock[Transactions]
    anAccount = new Account(transactions)
  }

  test("should record deposit") {
    (transactions.record(_:Deposit)).expects(new Deposit(10, aDate)).once
    
    anAccount.deposit(10)
  }


  test("should record withdraw") {
    (transactions.record(_:Withdraw)).expects(new Withdraw(20, aDate)).once
    
    anAccount.withdraw(20)
  }

  test("should be visited by the printer in order to produce a valid statement") {
    (printer.visit(_:Account)).expects(anAccount).returns("""date || credit || debit || balance""").once
    (transactions.accept(_:Printer)).expects(printer).returns("").once

    val statement = anAccount.printStatement(printer)

    assert(statement === """date || credit || debit || balance""")
  }

}
