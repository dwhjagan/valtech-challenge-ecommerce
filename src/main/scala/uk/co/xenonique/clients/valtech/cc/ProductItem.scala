package uk.co.xenonique.clients.valtech.cc

/**
  * The type ProductItem that uses sealed case classes for shopping cart items
  *
  * @author Peter Pilgrim (peter)
  */
sealed trait ProductItem {
  val name: String;
  val price: BigDecimal
}

final case class Orange( override val name: String = "Orange",
                     override val price: BigDecimal = 0.60) extends ProductItem

final case class Apple( override val name: String = "Apple",
                    override val price: BigDecimal = 0.25) extends ProductItem

