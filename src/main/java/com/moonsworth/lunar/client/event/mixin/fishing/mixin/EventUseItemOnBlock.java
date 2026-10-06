package com.moonsworth.lunar.client.event.mixin.fishing.mixin;

import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;
import org.joml.Vector3d;
import org.joml.Vector3i;

public class EventUseItemOnBlock extends LunarEvent {
   private final Vector3i field1;
   private final int field2;
   private final int field3;
   private final Vector3d field4;
   private final boolean field5;
   private final boolean field6;

   @Generated
   public Vector3i method1() {
      return this.field1;
   }

   @Generated
   public int method2() {
      return this.field2;
   }

   @Generated
   public int method3() {
      return this.field3;
   }

   @Generated
   public Vector3d method4() {
      return this.field4;
   }

   @Generated
   public boolean method5() {
      return this.field5;
   }

   @Generated
   public boolean method6() {
      return this.field6;
   }

   @Generated
   public EventUseItemOnBlock(Vector3i vector3i1, int value, int value2, Vector3d vector3d4, boolean flag, boolean flag2) {
      this.field1 = vector3i1;
      this.field2 = value;
      this.field3 = value2;
      this.field4 = vector3d4;
      this.field5 = flag;
      this.field6 = flag2;
   }
}
