package com.moonsworth.lunar.client.render.texture;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.render.texture.AnimatedSprite;
import com.moonsworth.lunar.client.render.texture.SpriteFrameList;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import lombok.Generated;

public class AnimatedSpriteImpl extends AnimatedSprite {
   private static final int field3 = 30;
   private final List<AnimatedSprite> images;
   private AnimatedSprite field4 = null;
   private int index = -1;
   private long field5 = 0L;

   public AnimatedSpriteImpl(List<AnimatedSprite> var1, boolean var2) {
      super(var2);
      this.images = var1;
   }

   @Override
   public boolean method1() {
      if (this.images.isEmpty()) {
         this.field4 = null;
         return false;
      }

      long var1 = ThreadModuleDump63.method4().method85().method16();
      if (this.field4 == null || this.field5 != var1 && var1 % 30L == 0L) {
         this.field5 = var1;
         this.index = (this.index + 1) % this.images.size();
         this.field4 = this.images.get(this.index);
      }

      return this.field4.method1();
   }

   @Override
   public AnimatedSprite.Data3 method2(MixinHelper_4 var1, int var2, int value, int value2, int value3) {
      return this.field4 != null ? this.field4.method2(var1, var2, value, value2, value3) : AnimatedSprite.Data3.field3;
   }

   @Override
   public void destroy() {
      this.images.forEach(AnimatedSprite::destroy);
      this.images.clear();
   }

   @Override
   public void method3(SpriteFrameList var1) {
      super.method3(var1);
      this.images.forEach(var1x -> var1x.method3(var1));
   }

   @Generated
   public List<AnimatedSprite> getImages() {
      return this.images;
   }
}
