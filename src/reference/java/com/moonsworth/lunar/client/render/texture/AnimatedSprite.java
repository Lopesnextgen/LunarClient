package com.moonsworth.lunar.client.render.texture;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import lombok.Generated;

public abstract class AnimatedSprite {
   private SpriteFrameList field1;
   private final boolean field2;

   public abstract boolean method1();

   public abstract AnimatedSprite.Data3 method2(MixinHelper_4 var1, int var2, int var3, int var4, int var5);

   public abstract void destroy();

   @Generated
   public AnimatedSprite(boolean var1) {
      this.field2 = var1;
   }

   @Generated
   public void method3(SpriteFrameList var1) {
      this.field1 = var1;
   }

   @Generated
   public SpriteFrameList method4() {
      return this.field1;
   }

   @Generated
   public boolean method5() {
      return this.field2;
   }

   public class Data3 {
      private final int field1;
      private final int field2;
      public static AnimatedSprite.Data3 field3 = new AnimatedSprite.Data3(0, 0);

      public Data3(int var1, int var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public AnimatedSprite.Data3 method1(AnimatedSprite.Data3 var1) {
         return new AnimatedSprite.Data3(Math.max(this.method2(), var1.method2()), Math.max(this.method3(), var1.method3()));
      }

      public int method2() {
         return this.field1;
      }

      public int method3() {
         return this.field2;
      }
   }

   public static class Data4 extends AnimatedSprite {
      public Data4(SpriteFrameList var1, boolean var2) {
         super(var2);
         this.method3(var1);
      }

      @Override
      public boolean method1() {
         return false;
      }

      @Override
      public AnimatedSprite.Data3 method2(MixinHelper_4 var1, int var2, int var3, int var4, int var5) {
         return AnimatedSprite.Data3.field3;
      }

      @Override
      public void destroy() {
      }
   }
}
