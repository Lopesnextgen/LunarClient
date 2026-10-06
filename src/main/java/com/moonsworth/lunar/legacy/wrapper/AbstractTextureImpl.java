package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge11_2;
import com.moonsworth.lunar.bridge.TextureBridge;
import com.moonsworth.lunar.bridge.Bridge8Extension;
import com.moonsworth.lunar.bridge.Bridge8Extension2;
import com.moonsworth.lunar.bridge.Bridge8Extension34;
import com.moonsworth.lunar.bridge.AbstractTextureMultiTexBridge;
import com.moonsworth.lunar.client.render.texture.PersistentTexture;
import net.minecraft.client.renderer.texture.AbstractTexture;

public class AbstractTextureImpl<C extends TextureBridge> extends AbstractTexture implements Bridge8Extension34 {
   protected final C field1;
   private int field2 = -1;
   private boolean field3;
   private final boolean field4;

   public AbstractTextureImpl(C c) {
      this.field1 = (C)c;
      this.field4 = c instanceof PersistentTexture;
   }

   public void loadTexture(net.minecraft.client.resources.IResourceManager iresourcemanager1) {
      this.deleteGlTexture();
      this.field1.method1((Bridge11_2)iresourcemanager1, this);
   }

   public void deleteGlTexture() {
      if (!this.field4) {
         this.release();
      }
   }

   public void release() {
      if (this.field3) {
         if (this instanceof AbstractTextureMultiTexBridge bridge_331) {
            bridge_331.bridge$setMultiTextureBase(this.field2);
         }

         this.glTextureId = this.field2;
         this.field2 = -1;
         this.field3 = false;
      }

      super.deleteGlTexture();
      this.field1.method22();
   }

   public void method2(Bridge8Extension bridge8) {
      int number2 = ((Bridge8Extension2)bridge8).lunar$getHandle();
      if (!this.field3) {
         this.field2 = this.glTextureId;
         this.field3 = true;
      }

      this.glTextureId = number2;
      if (this instanceof AbstractTextureMultiTexBridge bridge_333) {
         bridge_333.bridge$setMultiTextureBase(number2);
      }
   }

   public C method3() {
      return this.field1;
   }

   public void method1() {
      this.release();
   }
}
