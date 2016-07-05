package uk.co.xenonique.clients.valtech

import CartItem._
/**
  * The type ShoppingCart
  *
  * @author Peter Pilgrim (peter)
  */

class ShoppingCart(val items: List[CartItem], val discounters: List[Discounter] = List()) {

  def price(): BigDecimal = {
    val fullPrice = items.map( x => x.price).sum

    val discountPrice = discounters.map( discounter => discounter.calculateDiscountPrice(items)).sum

    fullPrice - discountPrice
  }

}

trait Discounter {
  def calculateDiscountPrice(items: List[CartItem]): BigDecimal
}

// Buy one, get one free on Apples

class BuyTwoApplesGetOneFreeDiscounter extends Discounter {
  def calculateDiscountPrice(items: List[CartItem]): BigDecimal = {
    val n = items.filter( x => x.equals(Orange)).size
    if ( n % 2 == 0)
      n / 2 * Orange.price
    else
      0.0
  }
}


// 3 for the price of 2 on Oranges

