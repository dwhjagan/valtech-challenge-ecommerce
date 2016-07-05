/*******************************************************************************
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2016 by Peter Pilgrim, Milton Keynes, UK for  XeNoNiQUe UK
 * PILGRIM ENGINEERING ARCHITECTURE TECHNOLOGY UK LTD
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU GPL v3.0
 * which accompanies this distribution, and is available at:
 * http://www.gnu.org/licenses/gpl-3.0.txt
 *
 * Developers:
 * Peter Pilgrim -- design, development and implementation
 *               -- Blog: http://www.xenonique.co.uk/blog/
 *               -- Twitter: @peter_pilgrim
 *
 * Contributors:
 *
 *******************************************************************************/

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


  "Shopping cart" should "should handle mixed discount offers" in {

    val discounters = List(new BuyThreeOrangesForTwoDiscounter(), new BuyTwoApplesGetOneFreeDiscounter)

    new ShoppingCart(List(), discounters).price() should be === 0.0
    new ShoppingCart(List(Orange), discounters).price() should be === 0.60
    new ShoppingCart(List(Orange, Apple), discounters).price() should be === 0.85
    new ShoppingCart(List(Orange, Apple, Orange), discounters).price() should be === 1.45
    new ShoppingCart(List(Orange, Apple, Orange, Apple), discounters).price() should be === 1.45
    new ShoppingCart(List(Orange, Apple, Apple, Orange, Apple), discounters).price() should be === 1.70
    new ShoppingCart(List(Orange, Apple, Orange, Apple, Orange, Apple), discounters).price() should be === 1.70
    new ShoppingCart(List(Orange, Apple, Orange, Apple, Orange, Apple, Orange), discounters).price() should be === 2.30
  }
}

