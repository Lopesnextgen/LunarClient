package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import it.unimi.dsi.fastutil.bytes.ByteArrayList;
import it.unimi.dsi.fastutil.shorts.ShortArrayList;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class Bridge5_5 {
   public static final ThreadLocal<Bridge9_7> field1 = new ThreadLocal<>();
   @Nullable
   private ShortArrayList field2;
   @Nullable
   private ByteArrayList field3;

   public void method1(Horsestats20Extension2 var1, Horsestats20Extension2 var2, int var3, int value) {
      ShortArrayList var5 = this.field2;
      if (var5 == null) {
         this.field2 = var5 = new ShortArrayList();
      }

      ByteArrayList var6 = this.field3;
      if (var6 == null) {
         this.field3 = var6 = new ByteArrayList();
      }

      int var7 = var2.bridge$getX() - var1.bridge$getX();
      int var8 = var2.bridge$getY() - var1.bridge$getY() - 1;
      int var9 = var2.bridge$getZ() - var1.bridge$getZ();
      short var10 = (short)((var7 & 15) << 8 | (var8 & 15) << 4 | var9 & 15);
      byte var11 = (byte)((var3 & 15) << 4 | value & 15);
      var5.add(var10);
      var6.add(var11);
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

   public void method2(Bridge5_5 var1) {
      ShortArrayList var2 = this.field2;
      this.field2 = null;
      var1.field2 = var2;
      ByteArrayList var3 = this.field3;
      this.field3 = null;
      var1.field3 = var3;
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
