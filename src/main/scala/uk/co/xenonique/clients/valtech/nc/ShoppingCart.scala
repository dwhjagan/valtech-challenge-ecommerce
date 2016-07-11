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

package uk.co.xenonique.clients.valtech.nc

import uk.co.xenonique.clients.valtech.nc.CartItem._
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
    val n = items.filter( x => x.equals(Apple)).size
    if ( n >= 2 )
      n / 2 * Apple.price
    else
      0.0
  }
}


// 3 for the price of 2 on Oranges

class BuyThreeOrangesForTwoDiscounter extends Discounter {
  def calculateDiscountPrice(items: List[CartItem]): BigDecimal = {
    val n = items.filter( x => x.equals(Orange)).size
    if ( n >= 3)
      n / 3  * Orange.price
    else
      0.0
  }
}


