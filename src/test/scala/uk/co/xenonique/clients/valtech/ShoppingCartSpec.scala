package uk.co.xenonique.clients.valtech

import org.junit.runner.RunWith
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.junit.JUnitRunner


import scala.collection.immutable.List

import CartItem._

@RunWith(classOf[JUnitRunner])
class ShoppingCartSpec extends FlatSpec with Matchers {
  "CartItem" should "should be buildable in a list" in {

    val list = List( Orange, Apple, Orange, Apple)

    list.isEmpty should be === false
  }

  "Shopping cart" should "should be price items in a list" in {

    new ShoppingCart( List() ).price() should be === 0.0
    new ShoppingCart( List( Orange) ).price() should be === 0.60
    new ShoppingCart( List( Apple) ).price() should be === 0.25
    new ShoppingCart( List( Orange, Orange, Apple) ).price() should be === 1.45
    new ShoppingCart( List( Orange, Apple, Orange) ).price() should be === 1.45
    new ShoppingCart( List( Apple, Orange, Orange, Apple) ).price() should be === 1.70
  }

  "Discounter" should "should handle two oranges for one offer" in {
    val items = List(Orange, Orange)
    new BuyTwoApplesGetOneFreeDiscounter().calculateDiscountPrice(items)
  }

  "Shopping cart" should "should handle two apples for one offer" in {

    val discounters = List(new BuyTwoApplesGetOneFreeDiscounter())

    new ShoppingCart(List(), discounters).price() should be === 0.0
    new ShoppingCart(List(Apple), discounters).price() should be === 0.25
    new ShoppingCart(List(Apple, Apple), discounters).price() should be === 0.25
    new ShoppingCart(List(Apple, Apple, Apple), discounters).price() should be === 0.50
    new ShoppingCart(List(Apple, Apple, Apple, Apple), discounters).price() should be === 0.50
    new ShoppingCart(List(Apple, Apple, Apple, Apple, Apple), discounters).price() should be === 0.75
    new ShoppingCart(List(Apple, Apple, Apple, Apple, Apple, Apple), discounters).price() should be === 0.75
  }
}

