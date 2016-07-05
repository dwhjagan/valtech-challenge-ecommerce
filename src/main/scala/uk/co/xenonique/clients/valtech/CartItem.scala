package uk.co.xenonique.clients.valtech

/**
  * The type CartItem
  *
  * @author Peter Pilgrim (peter)
  */

trait CartItem {
  val name: String;
  val price: Float;
}

case class Apple( override val name: String = "Apple",
                  override val price: Float = 0.60F) extends CartItem

case class Orange( override val name: String = "Orange",
                   override val price: Float = 0.25F) extends CartItem

