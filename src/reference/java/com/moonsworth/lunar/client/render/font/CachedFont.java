package com.moonsworth.lunar.client.render.font;

import com.moonsworth.lunar.bridge.IResourceBridge;
import com.moonsworth.lunar.bridge.Bridge8Extension34;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.loading.LoadableResource;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.alert.Alert5Impl;
import java.awt.Font;
import java.io.InputStream;
import java.util.concurrent.CompletableFuture;
import lombok.Generated;
import com.moonsworth.lunar.client.render.texture.AsyncRasterLoader;

public class CachedFont implements LoadableResource {
   public static final int field1 = 1048;
   public static final float field2 = 1048.0F;
   protected final ResourceLocationBridge field3;
   protected final ResourceLocationBridge field4;
   private final float field5;
   protected int index;
   protected int remaining;
   protected boolean[] rendered;
   protected CachedFont.Data[] field6 = new CachedFont.Data[256];
   protected Font field7;
   protected boolean antiAlias;
   protected boolean field8;
   protected int field9 = -1;
   protected int field10 = 0;

   public CachedFont(ResourceLocationBridge var1, float var2) {
      this.field3 = var1;
      String var3 = var1.bridge$getPath();
      var3 = var3.substring(var3.indexOf(47) + 1, var3.indexOf(46));
      this.field4 = ResourceLocationBridge.create("lunar", var3 + "-cfont_default-" + (int)var2);
      this.field5 = var2;
      this.antiAlias = true;
      this.field8 = true;
   }

   @Override
   public boolean isLoaded() {
      if (this.field7 != null) {
         return true;
      }

      Font var1;
      try {
         if (ThreadModuleDump63.method2() && ThreadModuleDump63.method3().bridge$getResourceManager() != null) {
            IResourceBridge var4 = ThreadModuleDump63.method3().bridge$getResourceManager().bridge$getResource(this.field3);
            if (var4 != null) {
               var1 = Font.createFont(0, var4.bridge$getInputStream()).deriveFont(this.field5);
            } else {
               Slayer.method5("Couldn't find the CFont resource file: " + this.field3, new Object[0]);
               var1 = new Font("Arial", 0, (int)this.field5);
            }
         } else {
            InputStream var2 = ThreadModuleDump63.method3().bridge$getMcDefaultResourcePack().bridge$getInputStream(this.field3);
            var1 = Font.createFont(0, var2).deriveFont(this.field5);
         }
      } catch (Exception var3) {
         var1 = new Font("Arial", 0, (int)this.field5);
      }

      this.field7 = var1;
      ThreadModuleDump63.method3().bridge$getTextureManager().bridge$deleteTexture(this.field4);
      this.method3(this.field4, this.field7, this.antiAlias, this.field8, this.field6, true).thenAccept(var1x -> this.field9 = var1x);
      return true;
   }

   @Override
   public String getName() {
      return this.field3.bridge$getPath();
   }

   protected CompletableFuture<Integer> method3(ResourceLocationBridge var1, Font var2, boolean var3, boolean var4, CachedFont.Data[] var5, boolean var6) {
      try {
         Bridge8Extension34 var7 = ThreadModuleDump63.method3().bridge$getTextureManager().method3(var1, new Alert5Impl());
         return AsyncRasterLoader.method8().method2(var7, var2, var3, var4, var5, var6, var1.toString());
      } catch (Throwable var8) {
         throw var8;
      }
   }

   protected void markRendered() {
      if (!this.rendered[this.index]) {
         this.rendered[this.index] = true;
         this.remaining--;
      }
   }

   public int getHeight() {
      return (this.field9 - 8) / 2;
   }

   public float method4(String var1) {
      int var2 = 0;

      for (char var6 : var1.toCharArray()) {
         if (var6 < this.field6.length) {
            var2 += this.field6[var6].width - 8 + this.field10;
         }
      }

      return var2 / 2.0F;
   }

   @Generated
   public Font method5() {
      return this.field7;
   }

   @Generated
   public boolean isAntiAlias() {
      return this.antiAlias;
   }

   @Generated
   public boolean method6() {
      return this.field8;
   }

   public static class Data {
      public int width;
      public int height;
      public int field1;
      public int field2;
      public float field3;
      public float field4;
      public float field5;
      public float field6;
   }
}
