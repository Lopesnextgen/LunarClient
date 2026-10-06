package com.moonsworth.lunar.client.event.input;

import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.client.event.input.MouseInputTypeLegacy;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import lombok.Generated;
import com.moonsworth.lunar.client.highlight.HighlightImpl;

public class MarkerInputEvent extends HighlightImpl {
   private final Bridge5Extension6 field1;
   private final MarkerModel.Data4 field2;
   private final int field3;
   private final MouseInputTypeLegacy field4;
   private long field5 = 0L;
   private double field6 = 0.0;
   private double field7 = 0.0;
   private double field8 = 0.0;
   private double field9 = 0.0;

   @Generated
   public MarkerInputEvent(Bridge5Extension6 var1, MarkerModel.Data4 data, int value, MouseInputTypeLegacy highlightType3) {
      this.field1 = var1;
      this.field2 = data;
      this.field3 = value;
      this.field4 = highlightType3;
   }

   @Generated
   public Bridge5Extension6 method1() {
      return this.field1;
   }

   @Generated
   public MarkerModel.Data4 method2() {
      return this.field2;
   }

   @Generated
   public int method3() {
      return this.field3;
   }

   @Generated
   public MouseInputTypeLegacy method4() {
      return this.field4;
   }

   @Generated
   public long method5() {
      return this.field5;
   }

   @Generated
   public double method6() {
      return this.field6;
   }

   @Generated
   public double method7() {
      return this.field7;
   }

   @Generated
   public double method8() {
      return this.field8;
   }

   @Generated
   public double method9() {
      return this.field9;
   }

   @Generated
   public void method10(long var1) {
      this.field5 = var1;
   }

   @Generated
   public void method11(double var1) {
      this.field6 = var1;
   }

   @Generated
   public void method12(double var1) {
      this.field7 = var1;
   }

   @Generated
   public void method13(double var1) {
      this.field8 = var1;
   }

   @Generated
   public void method14(double var1) {
      this.field9 = var1;
   }
}
