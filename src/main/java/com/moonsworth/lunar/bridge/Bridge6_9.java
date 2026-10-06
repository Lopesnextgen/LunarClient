package com.moonsworth.lunar.bridge;

import java.util.Arrays;
import javax.annotation.Nullable;
import lombok.Generated;

public class Bridge6_9 {
   private final boolean clamp;
   private final boolean field1;
   @Nullable
   private final Bridge3Extension field2;
   @Nullable
   private final Bridge3Extension2_2 field3;
   private final int field4;
   private final int field5;
   private final int field6;
   private final ColorChannelOrder field7;
   private final int[] data;
   private final String field8;

   @Generated
   public Bridge6_9(
      boolean var1,
      boolean var2,
      @Nullable Bridge3Extension var3,
      @Nullable Bridge3Extension2_2 var4,
      int var5,
      int var6,
      int var7,
      ColorChannelOrder var8,
      int[] var9,
      String var10
   ) {
      this.clamp = var1;
      this.field1 = var2;
      this.field2 = var3;
      this.field3 = var4;
      this.field4 = var5;
      this.field5 = var6;
      this.field6 = var7;
      this.field7 = var8;
      this.data = var9;
      this.field8 = var10;
   }

   @Generated
   public boolean isClamp() {
      return this.clamp;
   }

   @Generated
   public boolean method2() {
      return this.field1;
   }

   @Nullable
   @Generated
   public Bridge3Extension method3() {
      return this.field2;
   }

   @Nullable
   @Generated
   public Bridge3Extension2_2 method4() {
      return this.field3;
   }

   @Generated
   public int getWidth() {
      return this.field4;
   }

   @Generated
   public int getHeight() {
      return this.field5;
   }

   @Generated
   public int method5() {
      return this.field6;
   }

   @Generated
   public ColorChannelOrder method6() {
      return this.field7;
   }

   @Generated
   public int[] getData() {
      return this.data;
   }

   @Generated
   public String getName() {
      return this.field8;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Bridge6_9 var2)) {
         return false;
      } else {
         if (!var2.canEqual(this)) {
            return false;
         }

         if (this.isClamp() != var2.isClamp()) {
            return false;
         }

         if (this.method2() != var2.method2()) {
            return false;
         }

         if (this.getWidth() != var2.getWidth()) {
            return false;
         }

         if (this.getHeight() != var2.getHeight()) {
            return false;
         }

         if (this.method5() != var2.method5()) {
            return false;
         }

         Bridge3Extension var3 = this.method3();
         Bridge3Extension var4 = var2.method3();
         if (var3 == null ? var4 == null : var3.equals(var4)) {
            Bridge3Extension2_2 var5 = this.method4();
            Bridge3Extension2_2 var6 = var2.method4();
            if (var5 == null ? var6 == null : var5.equals(var6)) {
               ColorChannelOrder var7 = this.method6();
               ColorChannelOrder var8 = var2.method6();
               if (var7 == null ? var8 == null : var7.equals(var8)) {
                  if (!Arrays.equals(this.getData(), var2.getData())) {
                     return false;
                  }

                  String var9 = this.getName();
                  String var10 = var2.getName();
                  return var9 == null ? var10 == null : var9.equals(var10);
               } else {
                  return false;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object var1) {
      return var1 instanceof Bridge6_9;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      var2 = var2 * 59 + (this.isClamp() ? 79 : 97);
      var2 = var2 * 59 + (this.method2() ? 79 : 97);
      var2 = var2 * 59 + this.getWidth();
      var2 = var2 * 59 + this.getHeight();
      var2 = var2 * 59 + this.method5();
      Bridge3Extension var3 = this.method3();
      var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      Bridge3Extension2_2 var4 = this.method4();
      var2 = var2 * 59 + (var4 == null ? 43 : var4.hashCode());
      ColorChannelOrder var5 = this.method6();
      var2 = var2 * 59 + (var5 == null ? 43 : var5.hashCode());
      var2 = var2 * 59 + Arrays.hashCode(this.getData());
      String var6 = this.getName();
      return var2 * 59 + (var6 == null ? 43 : var6.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "DecodedTextureData(clamp="
         + this.isClamp()
         + ", blur="
         + this.method2()
         + ", animationMetadata="
         + this.method3()
         + ", lunarMetadata="
         + this.method4()
         + ", width="
         + this.getWidth()
         + ", height="
         + this.getHeight()
         + ", originalHeight="
         + this.method5()
         + ", format="
         + this.method6()
         + ", data="
         + Arrays.toString(this.getData())
         + ", name="
         + this.getName()
         + ")";
   }
}
