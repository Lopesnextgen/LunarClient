package com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.CoordinatesType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.SkillXpSource;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkillXpListener;
import com.moonsworth.lunar.client.framework.listener.TriggeredBy;
import com.moonsworth.lunar.client.event.DynamicListenerEvent;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

@TriggeredBy(SkillXpListener.class)
public class SkillXpUpdateEvent extends LunarEvent implements DynamicListenerEvent {
   private final CoordinatesType field1;
   private final SkillXpSource field2;
   private final double field3;
   private final double field4;
   private final double field5;
   private final double field6;
   private final long field7;

   @Generated
   public SkillXpUpdateEvent(CoordinatesType coordinatesType, SkillXpSource skillXpSource, double value, double value2, double value3, double value4, long value5) {
      this.field1 = coordinatesType;
      this.field2 = skillXpSource;
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
   public SkillXpSource method2() {
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
