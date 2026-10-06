package com.moonsworth.lunar.client.render.texture;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.render.texture.AnimatedSprite;
import com.moonsworth.lunar.client.render.texture.SpriteFrameList;
import java.util.List;
import lombok.Generated;

public class SpriteImage extends AnimatedSprite {
   private final List<AnimatedSprite> field3;

   public SpriteImage(List<AnimatedSprite> var1, boolean var2) {
      super(var2);
      this.field3 = var1;
   }

   @Override
   public boolean method1() {
      boolean var1 = false;

      for (AnimatedSprite var3 : this.field3) {
         var1 |= var3.method1();
      }

      return var1;
   }

   @Override
   public AnimatedSprite.Data3 method2(MixinHelper_4 var1, int var2, int var3, int value, int value2) {
      AnimatedSprite.Data3 var6 = AnimatedSprite.Data3.field3;

      for (AnimatedSprite var8 : this.field3) {
         var6 = var6.method1(var8.method2(var1, var2, var3, value, value2));
      }

      return var6;
   }

   @Override
   public void destroy() {
      this.field3.forEach(AnimatedSprite::destroy);
      this.field3.clear();
   }

   @Override
   public void method3(SpriteFrameList var1) {
      super.method3(var1);
      this.field3.forEach(var1x -> var1x.method3(var1));
   }

   @Generated
   public List<AnimatedSprite> getImages() {
      return this.field3;
   }
}
