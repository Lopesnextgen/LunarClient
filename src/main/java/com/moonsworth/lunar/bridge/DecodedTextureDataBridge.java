package com.moonsworth.lunar.bridge;

import java.util.Arrays;
import javax.annotation.Nullable;
import lombok.Generated;

public class DecodedTextureDataBridge {
   private final boolean clamp;
   private final boolean field1;
   @Nullable
   private final AnimationMetadataSectionBridge field2;
   @Nullable
   private final EmissiveMetadataSectionBridge field3;
   private final int field4;
   private final int field5;
   private final int field6;
   private final PixelFormat field7;
   private final int[] data;
   private final String field8;

   @Generated
   public DecodedTextureDataBridge(
      boolean flag,
      boolean flag2,
      @Nullable AnimationMetadataSectionBridge bridge3extension3,
      @Nullable EmissiveMetadataSectionBridge bridge3extension2_24,
      int value,
      int value2,
      int value3,
      PixelFormat bridgetype_98,
      int[] items9,
      String text10
   ) {
      this.clamp = flag;
      this.field1 = flag2;
      this.field2 = bridge3extension3;
      this.field3 = bridge3extension2_24;
      this.field4 = value;
      this.field5 = value2;
      this.field6 = value3;
      this.field7 = bridgetype_98;
      this.data = items9;
      this.field8 = text10;
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
   public AnimationMetadataSectionBridge method3() {
      return this.field2;
   }

   @Nullable
   @Generated
   public EmissiveMetadataSectionBridge method4() {
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
   public PixelFormat method6() {
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
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof DecodedTextureDataBridge bridge6_92)) {
         return false;
      } else {
         if (!bridge6_92.canEqual(this)) {
            return false;
         }

         if (this.isClamp() != bridge6_92.isClamp()) {
            return false;
         }

         if (this.method2() != bridge6_92.method2()) {
            return false;
         }

         if (this.getWidth() != bridge6_92.getWidth()) {
            return false;
         }

         if (this.getHeight() != bridge6_92.getHeight()) {
            return false;
         }

         if (this.method5() != bridge6_92.method5()) {
            return false;
         }

         AnimationMetadataSectionBridge bridge3extension3 = this.method3();
         AnimationMetadataSectionBridge bridge3extension4 = bridge6_92.method3();
         if (bridge3extension3 == null ? bridge3extension4 == null : bridge3extension3.equals(bridge3extension4)) {
            EmissiveMetadataSectionBridge bridge3extension2_25 = this.method4();
            EmissiveMetadataSectionBridge bridge3extension2_26 = bridge6_92.method4();
            if (bridge3extension2_25 == null ? bridge3extension2_26 == null : bridge3extension2_25.equals(bridge3extension2_26)) {
               PixelFormat bridgetype_97 = this.method6();
               PixelFormat bridgetype_98 = bridge6_92.method6();
               if (bridgetype_97 == null ? bridgetype_98 == null : bridgetype_97.equals(bridgetype_98)) {
                  if (!Arrays.equals(this.getData(), bridge6_92.getData())) {
                     return false;
                  }

                  String text9 = this.getName();
                  String text10 = bridge6_92.getName();
                  return text9 == null ? text10 == null : text9.equals(text10);
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
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof DecodedTextureDataBridge;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      number2 = number2 * 59 + (this.isClamp() ? 79 : 97);
      number2 = number2 * 59 + (this.method2() ? 79 : 97);
      number2 = number2 * 59 + this.getWidth();
      number2 = number2 * 59 + this.getHeight();
      number2 = number2 * 59 + this.method5();
      AnimationMetadataSectionBridge bridge3extension3 = this.method3();
      number2 = number2 * 59 + (bridge3extension3 == null ? 43 : bridge3extension3.hashCode());
      EmissiveMetadataSectionBridge bridge3extension2_24 = this.method4();
      number2 = number2 * 59 + (bridge3extension2_24 == null ? 43 : bridge3extension2_24.hashCode());
      PixelFormat bridgetype_95 = this.method6();
      number2 = number2 * 59 + (bridgetype_95 == null ? 43 : bridgetype_95.hashCode());
      number2 = number2 * 59 + Arrays.hashCode(this.getData());
      String text6 = this.getName();
      return number2 * 59 + (text6 == null ? 43 : text6.hashCode());
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
