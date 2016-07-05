package uk.co.xenonique.clients.valtech

/**
  * The type ShoppingCart
  *
  * @author Peter Pilgrim (peter)
  */

class ShoppingCart(val items: List[CartItem]) {

  def price(): BigDecimal = items.map( x => x.price).sum

}