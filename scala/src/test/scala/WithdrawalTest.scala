package bank.domain

import org.scalatest._
import java.util.Date


class WithdrawalTest extends FunSuite with Assertions {
  test("should not accept negative amount") {
    intercept[IllegalArgumentException] {
      new Withdraw(-1, new Date)
    }
  }
}
