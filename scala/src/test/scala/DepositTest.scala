package bank.domain

import org.scalatest._
import java.util.Date


class DepositTest extends FunSuite with Assertions {
  test("Deposit of an amount of 10 should be equals to another Deposit of 10") {
    val date = new Date
    assert(new Deposit(10, date) == new Deposit(10, date))
  }

  test("Deposit of an amount of 10 one day should not be equals to another Deposit of 10 another day") {
    val spf = new java.text.SimpleDateFormat("dd/MM/yyyy")
    val oneDay = spf.parse("01/01/2000")
    val anotherDay = spf.parse("02/01/2000")
    assert(new Deposit(10, oneDay) != new Deposit(10, anotherDay))
  }

  test("should not accept negative amount") {
    intercept[IllegalArgumentException] {
      new Deposit(-1, new Date)
    }
  }
}
