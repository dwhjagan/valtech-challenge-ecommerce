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


  "Shopping cart" should "should handle buy 3 oranges for 2 offer" in {

    val discounters = List(new BuyThreeOrangesForTwoDiscounter())

    new ShoppingCart(List(), discounters).price() should be === 0.0
    new ShoppingCart(List(Orange), discounters).price() should be === 0.60
    new ShoppingCart(List(Orange, Orange), discounters).price() should be === 1.2
    new ShoppingCart(List(Orange, Orange, Orange), discounters).price() should be === 1.2
    new ShoppingCart(List(Orange, Orange, Orange, Orange), discounters).price() should be === 1.8
    new ShoppingCart(List(Orange, Orange, Orange, Orange, Orange), discounters).price() should be === 2.4
    new ShoppingCart(List(Orange, Orange, Orange, Orange, Orange, Orange), discounters).price() should be === 2.4
  }


}

