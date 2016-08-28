package bank

import org.scalatest._

import bank.simple._
import bank.printer._
import bank.domain.Clock
import java.util.Date

class AccountSpec extends FunSpec with GivenWhenThen {
  implicit object clock extends Clock {
    var aDate: Date = new Date
    val spf = new java.text.SimpleDateFormat("dd-MM-yyyy")
    override def date = aDate
    def defineDate(date:String): Unit  = this.aDate = spf.parse(date)
  }

  describe("An account") {
   it("should print a valid history of transactions") {
     Given("a client makes a deposit of 1000 on 10-01-2012")
     var anAccount: Account = new Account(new InMemoryTransactions)
     clock.defineDate("10-01-2012")
     anAccount.deposit(1000)

     And("a deposit of 2000 on 13-01-2012")
     clock.defineDate("13-01-2012")
     anAccount.deposit(2000)
     And("a withdrawal of 500 on 14-01-2012")
     clock.defineDate("14-01-2012")
     anAccount.withdraw(500)

     When("she prints her bank statement")
     val statement = anAccount.printStatement(new StatementPrinter)

    Then("""she would see
date || credit || debit || balance
14/01/2012 || || 500.00 || 2500.00
13/01/2012 || 2000.00 || || 3000.00
10/01/2012 || 1000.00 || || 1000.00""")
     assert(statement === """date || credit || debit || balance
14/01/2012 || || 500.00 || 2500.00
13/01/2012 || 2000.00 || || 3000.00
10/01/2012 || 1000.00 || || 1000.00""")
    }
  }
}
