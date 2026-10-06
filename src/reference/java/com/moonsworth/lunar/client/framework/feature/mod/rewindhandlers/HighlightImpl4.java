package com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.CoordinatesType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.CoordinatesType2;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler26;
import com.moonsworth.lunar.client.guiRewindhandlers.Annotation3;
import com.moonsworth.lunar.client.guiRewindhandlers.nameplate.Nameplate2;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

@Annotation3(GuiRewindhandlersHandler26.class)
public class HighlightImpl4 extends Highlight implements Nameplate2 {
   private final CoordinatesType field1;
   private final CoordinatesType2 field2;
   private final double field3;
   private final double field4;
   private final double field5;
   private final double field6;
   private final long field7;

   @Generated
   public HighlightImpl4(CoordinatesType coordinatesType, CoordinatesType2 coordinatesType2, double value, double value2, double value3, double value4, long value5) {
      this.field1 = coordinatesType;
      this.field2 = coordinatesType2;
      this.field3 = value;
      this.field4 = value2;
      this.field5 = value3;
      this.field6 = value4;
      this.field7 = value5;
   }

   @Generated
   public CoordinatesType method1() {
      return this.field1;
   }

   @Generated
   public CoordinatesType2 method2() {
      return this.field2;
   }

   @Generated
   public double method3() {
      return this.field3;
   }

   @Generated
   public double method4() {
      return this.field4;
   }

   @Generated
   public double method5() {
      return this.field5;
   }

   @Generated
   public double method6() {
      return this.field6;
   }

   @Generated
   public long method7() {
      return this.field7;
   }
}
