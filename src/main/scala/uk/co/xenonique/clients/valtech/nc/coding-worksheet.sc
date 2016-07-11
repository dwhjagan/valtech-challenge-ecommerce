import java.io.Serializable

import uk.co.xenonique.clients.valtech.nc.{BuyTwoApplesGetOneFreeDiscounter, CartItem, ShoppingCart}
import uk.co.xenonique.clients.valtech.nc.CartItem._

import scala.collection.immutable.List

var list = List(Orange,Apple)

List(Orange)

List(Apple)


list.map( x => x.price).sum

List(Orange,Apple,Orange).filter( x => x.equals(Orange))

val n = List(Orange,Apple,Orange,Orange).filter( x => x.equals(Orange)).size


new ShoppingCart(List(Apple), List(new BuyTwoApplesGetOneFreeDiscounter())).price()


val items = List(Apple, Apple)
new BuyTwoApplesGetOneFreeDiscounter().calculateDiscountPrice(items)

List(new BuyTwoApplesGetOneFreeDiscounter(), new BuyTwoApplesGetOneFreeDiscounter ()).map(d => d.calculateDiscountPrice(items)).sum


// Bloody hell, I could have used case classes after all!

sealed trait ProdItem extends Product with Serializable{
  val name: String;
  val price: BigDecimal;
}

final case class Foo( override val name: String = "Foo",
                  override val price: BigDecimal = 0.50) extends ProdItem

final case class Bar( override val name: String = "Bar",
                   override val price: BigDecimal = 0.35) extends ProdItem

val products = List( Foo(), Bar(), Foo(), Bar())

val totalPrice = products.map( x => x.price).sum

