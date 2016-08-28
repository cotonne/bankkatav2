package bank.printer


import bank._
import bank.domain._
import java.text.SimpleDateFormat
import java.text.DecimalFormat

class StatementPrinter extends Printer {
    val dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
    val doubleFormat = new DecimalFormat("#.00 "); 
    val separator = "|| "

    override def visit(anAccount: Account) : String = List("date ", "credit ", "debit ", "balance").mkString(separator)

    override def visit(transactions: Transactions) : String = "\n" + ((transactions.foldLeft((List[String](), 0))) { (acc, elt) => (acc._1 :+ visit(elt) + formatDouble(elt.balance(acc._2)).trim, elt.balance(acc._2)) })._1.reverse.mkString("\n")

    override def visit(aTransaction: Transaction) : String = List(dateFormat.format(aTransaction.operation), formatDouble(aTransaction.deposit) , formatDouble(aTransaction.withdraw), "").mkString(separator)
    
    def formatDouble(d: Double) : String = if(d == 0.0) return "" else doubleFormat.format(d)
}
