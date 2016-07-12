/*******************************************************************************
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2016 by Peter Pilgrim, Milton Keynes, P.E.A.T UK LTD
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Creative Commons 3.0
 * Non Commercial Non Derivation Share-alike License
 * https://creativecommons.org/licenses/by-nc-nd/4.0/
 *
 * Developers:
 * Peter Pilgrim -- design, development and implementation
 *               -- Blog: http://www.xenonique.co.uk/blog/
 *               -- Twitter: @peter_pilgrim
 *
 * Contributors:
 *
 *******************************************************************************/
package uk.co.xenonique.clients.valtech.cc

import org.junit.runner.RunWith
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.junit.JUnitRunner

import scala.collection.immutable.List

/**
  * The {@link ProductCartSpec} validates the operation and behaviour of @{link ProductCart}
  *
  * @author Peter Pilgrim (peter)
  */

@RunWith(classOf[JUnitRunner])
class ProductCartSpec  extends FlatSpec with Matchers {

  "List" should "be reversible" in {

    List(1,2,3).reverse should be === List(3,2,1)
  }

  "Shopping cart" should "calculate price items in a list" in {

    new ProductCart( List() ).price() should be === 0.0
    new ProductCart( List( Orange()) ).price() should be === 0.60
    new ProductCart( List( Apple()) ).price() should be === 0.25
    new ProductCart( List( Orange(), Orange(), Apple()) ).price() should be === 1.45
    new ProductCart( List( Orange(), Apple(), Orange()) ).price() should be === 1.45
    new ProductCart( List( Apple(), Orange(), Orange(), Apple()) ).price() should be === 1.70
  }

  "Shopping cart" should "calculate two apples for one offer" in {

    val discounters = List(new BuyTwoApplesGetOneFreeDiscounter())

    new ProductCart(List(), discounters).price() should be === 0.0
    new ProductCart(List(Apple()), discounters).price() should be === 0.25
    new ProductCart(List(Apple(), Apple()), discounters).price() should be === 0.25
    new ProductCart(List(Apple(), Apple(), Apple()), discounters).price() should be === 0.50
    new ProductCart(List(Apple(), Apple(), Apple(), Apple()), discounters).price() should be === 0.50
    new ProductCart(List(Apple(), Apple(), Apple(), Apple(), Apple()), discounters).price() should be === 0.75
    new ProductCart(List(Apple(), Apple(), Apple(), Apple(), Apple(), Apple()), discounters).price() should be === 0.75
  }


  "Shopping cart" should "calculate buy 3 oranges for 2 offer" in {

    val discounters = List(new BuyThreeOrangesForTwoDiscounter())

    new ProductCart(List(), discounters).price() should be === 0.0
    new ProductCart(List(Orange()), discounters).price() should be === 0.60
    new ProductCart(List(Orange(), Orange()), discounters).price() should be === 1.2
    new ProductCart(List(Orange(), Orange(), Orange()), discounters).price() should be === 1.2
    new ProductCart(List(Orange(), Orange(), Orange(), Orange()), discounters).price() should be === 1.8
    new ProductCart(List(Orange(), Orange(), Orange(), Orange(), Orange()), discounters).price() should be === 2.4
    new ProductCart(List(Orange(), Orange(), Orange(), Orange(), Orange(), Orange()), discounters).price() should be === 2.4
  }


  "Shopping cart" should "calculate mixed discount offers" in {

    val discounters = List(new BuyThreeOrangesForTwoDiscounter(), new BuyTwoApplesGetOneFreeDiscounter)

    new ProductCart(List(), discounters).price() should be === 0.0
    new ProductCart(List(Orange()), discounters).price() should be === 0.60
    new ProductCart(List(Orange(), Apple()), discounters).price() should be === 0.85
    new ProductCart(List(Orange(), Apple(), Orange()), discounters).price() should be === 1.45
    new ProductCart(List(Orange(), Apple(), Orange(), Apple()), discounters).price() should be === 1.45
    new ProductCart(List(Orange(), Apple(), Apple(), Orange(), Apple()), discounters).price() should be === 1.70
    new ProductCart(List(Orange(), Apple(), Orange(), Apple(), Orange(), Apple()), discounters).price() should be === 1.70
    new ProductCart(List(Orange(), Apple(), Orange(), Apple(), Orange(), Apple(), Orange()), discounters).price() should be === 2.30
  }
}
