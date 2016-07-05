package uk.co.xenonique.clients.valtech

/**
  * The type ShoppingCart
  *
  * @author Peter Pilgrim (peter)
  */

class ShoppingCart(val items: List[(String,Float) => CartItem with Product with Serializable]) {

  def price(): Float = 0.0F;

}