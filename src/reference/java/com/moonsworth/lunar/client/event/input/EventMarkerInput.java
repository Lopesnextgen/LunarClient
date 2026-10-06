package com.moonsworth.lunar.client.event.input;

import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.client.event.input.MouseInputType;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data4;
import lombok.Generated;
import com.moonsworth.lunar.client.event.CancellableEvent;

public class EventMarkerInput extends CancellableEvent {
   private final GuiScreenBridge field1;
   private final Data4 field2;
   private final int field3;
   private final MouseInputType field4;
   private long field5 = 0L;
   private double field6 = 0.0;
   private double field7 = 0.0;
   private double field8 = 0.0;
   private double field9 = 0.0;

   @Generated
   public EventMarkerInput(GuiScreenBridge guiScreenBridge, Data4 data, int value, MouseInputType mouseInputType) {
      this.field1 = guiScreenBridge;
      this.field2 = data;
      this.field3 = value;
      this.field4 = mouseInputType;
   }

   @Generated
   public GuiScreenBridge method1() {
      return this.field1;
   }

   @Generated
   public Data4 method2() {
      return this.field2;
   }

   @Generated
   public int method3() {
      return this.field3;
   }

   @Generated
   public MouseInputType method4() {
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
   public void method10(long value) {
      this.field5 = value;
   }

   @Generated
   public void method11(double value1) {
      this.field6 = value1;
   }

   @Generated
   public void method12(double value1) {
      this.field7 = value1;
   }

   @Generated
   public void method13(double value1) {
      this.field8 = value1;
   }

   @Generated
   public void method14(double value1) {
      this.field9 = value1;
   }
}
