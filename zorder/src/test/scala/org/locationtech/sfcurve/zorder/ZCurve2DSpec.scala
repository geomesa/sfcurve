package org.locationtech.sfcurve.zorder

import org.locationtech.sfcurve.SpaceFillingCurves
import org.scalatest.{FunSpec, Matchers}

class ZCurve2DSpec extends FunSpec with Matchers {
  describe("SPI access") {
    it("provides an SFC") {
      val sfc = SpaceFillingCurves("zorder", Map(ZOrderSFCProvider.RESOLUTION_PARAM -> Int.box(100)))
      sfc.isInstanceOf[ZCurve2D] should equal(true)
    }

    it("computes a covering set of ranges") {
      val sfc = SpaceFillingCurves("zorder", Map(ZOrderSFCProvider.RESOLUTION_PARAM -> Int.box(1024)))
      val ranges = sfc.toRanges(-80.0, 35.0, -75.0, 40.0, ZCurve2D.hints(maxRecurse = 12))

      ranges.length shouldBe 18
      val (l, r, contains) = ranges.head
      l shouldBe 197616
      r shouldBe 197631
      contains shouldBe true
    }
  }

}
