package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import it.unimi.dsi.fastutil.bytes.ByteArrayList;
import it.unimi.dsi.fastutil.shorts.ShortArrayList;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class LightOverlayTrackerBridge {
   public static final ThreadLocal<Bridge9_7> field1 = new ThreadLocal<>();
   @Nullable
   private ShortArrayList field2;
   @Nullable
   private ByteArrayList field3;

   public LightOverlayTrackerBridge() {
   }

   public void method1(Horsestats20Extension2 horsestats20Extension2, Horsestats20Extension2 horsestats20Extension22, int value, int value2) {
      ShortArrayList shortarraylist5 = this.field2;
      if (shortarraylist5 == null) {
         this.field2 = shortarraylist5 = new ShortArrayList();
      }

      ByteArrayList bytearraylist6 = this.field3;
      if (bytearraylist6 == null) {
         this.field3 = bytearraylist6 = new ByteArrayList();
      }

      int number7 = horsestats20Extension22.bridge$getX() - horsestats20Extension2.bridge$getX();
      int number8 = horsestats20Extension22.bridge$getY() - horsestats20Extension2.bridge$getY() - 1;
      int number9 = horsestats20Extension22.bridge$getZ() - horsestats20Extension2.bridge$getZ();
      short index10 = (short)((number7 & 15) << 8 | (number8 & 15) << 4 | number9 & 15);
      byte index11 = (byte)((value & 15) << 4 | value2 & 15);
      shortarraylist5.add(index10);
      bytearraylist6.add(index11);
   }

   public void reset() {
      if (this.field2 != null) {
         this.field2.clear();
      }

      if (this.field3 != null) {
         this.field3.clear();
      }
   }

   public void clear() {
      this.reset();
      this.field2 = null;
      this.field3 = null;
   }

   public void method2(LightOverlayTrackerBridge bridge5_51) {
      ShortArrayList shortarraylist2 = this.field2;
      this.field2 = null;
      bridge5_51.field2 = shortarraylist2;
      ByteArrayList bytearraylist3 = this.field3;
      this.field3 = null;
      bridge5_51.field3 = bytearraylist3;
   }

   @Nullable
   @Generated
   public ShortArrayList method3() {
      return this.field2;
   }

   @Nullable
   @Generated
   public ByteArrayList method4() {
      return this.field3;
   }
}
