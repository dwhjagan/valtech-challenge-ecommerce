package uk.co.xenonique.clients.valtech

/**
  * The type CartItem
  *
  * @author Peter Pilgrim (peter)
  */

class CartItem( val name: String, var price: BigDecimal)

object CartItem {
  val Orange = new CartItem( "Orange", 0.60)
  val Apple = new CartItem("Apple", 0.25)
}
