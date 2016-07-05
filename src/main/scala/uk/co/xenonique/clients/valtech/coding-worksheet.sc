import uk.co.xenonique.clients.valtech._
import uk.co.xenonique.clients.valtech.CartItem._

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










