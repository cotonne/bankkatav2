package bank.domain

trait Transactions {
  def record(aTransaction: Transaction)
 
  def accept(printer: Printer): String

  def foldLeft(z: (List[String], Double))(f: ((List[String], Double), Transaction) => (List[String], Double)): (List[String], Double) 
}
