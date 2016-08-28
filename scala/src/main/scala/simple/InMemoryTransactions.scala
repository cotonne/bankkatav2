package bank.simple

import bank.domain._
import scala.collection.mutable.MutableList

class InMemoryTransactions extends Transactions {

  var transactions: MutableList[Transaction] = MutableList[Transaction]()

  override def record(aTransaction: Transaction) = transactions += aTransaction

  override def accept(printer: Printer): String = printer.visit(this)

  override def foldLeft(z: (List[String], Double))(f: ((List[String], Double), Transaction) => (List[String], Double)): (List[String], Double) = (transactions.foldLeft(z))(f)

}
