package com.moonsworth.lunar.client.driver.core;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.TexturePixelFormat;
import com.moonsworth.lunar.bridge.slayer.Slayer2;
import com.moonsworth.lunar.bridge.slayer.Slayer4;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.cosmetics.ShaderCloakRenderer;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.config.Config;
import com.moonsworth.webosr.wrappers.image.TexturedImageSource;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Fishing {
   public static final Set<Fishing> field1 = new HashSet<>();
   public static Boolean field2;
   private static boolean field3;
   public static Integer field4;
   public static boolean field5;
   public static Integer field6;
   private final String field7;
   private final boolean field8;
   private TexturedImageSource field9;
   private final Map<Integer, Bridge3_24> field10 = new HashMap<>();
   private Bridge3_24 field11;
   private boolean field12;
   private final int field13;
   private long field14;
   private int field15 = 0;
   private int field16 = 0;

   public Fishing(String var1, boolean var2) {
      this(var1, var2, 0);
   }

   public Fishing(String var1, boolean var2, int var3) {
      DriverViewportLegacy.method50().method46(var1);
      this.field7 = var1;
      this.field8 = var2;
      this.field13 = var3;
      if (!DriverViewportLegacy.method53()) {
         this.field9 = DriverViewportLegacy.method52().createImageSourceTextured(var1);
         field1.add(this);
      }
   }

   public Fishing(String var1) {
      this(var1, false);
   }

   public void method1(Bridge3_24 var1) {
      if (this.field13 == 0 || System.currentTimeMillis() - this.field14 >= 1000 / this.field13) {
         if (this.method11()) {
            if (!Bridge.method22().method1()) {
               this.method8(true);
            } else {
               this.method6(var1.bridge$framebufferWidth(), var1.bridge$framebufferHeight());
               this.method9(var1);
               this.field14 = System.currentTimeMillis();
            }
         }
      }
   }

   public boolean method2(AbstractRenderContext var1, int var2, int var3) {
      if (var2 > 0 && var3 > 0) {
         if (this.field13 != 0 && System.currentTimeMillis() - this.field14 < 1000 / this.field13) {
            return false;
         } else if (!this.method11()) {
            return false;
         } else if (!Bridge.method22().method1()) {
            this.method8(true);
            return false;
         } else {
            this.method5(var1, var2, var3);
            var1.method16();
            var1.method14();
            return true;
         }
      } else {
         return false;
      }
   }

   public void method3(AbstractRenderContext var1) {
      this.method7(var1);
      this.invalidate();
      this.field14 = System.currentTimeMillis();
   }

   public void delete() {
      for (Bridge3_24 var2 : this.field10.values()) {
         var2.bridge$delete();
      }

      this.field10.clear();
      field1.remove(this);
      this.method8(false);
      if (this.field9 != null) {
         this.field9.unbind();
         this.field9.close();
         this.field9 = null;
         if (this.field12) {
            DriverViewportLegacy.method50()
               .method23(
                  DriverViewportLegacy.method50().method55().method13(), "gameTexture", ThreadModuleDump48.field22.toJson(new Fishing.Data2(this.field7, false))
               );
            this.field12 = false;
         }
      }
   }

   public Bridge3_24 method4() {
      return this.field11;
   }

   private void method5(AbstractRenderContext var1, int var2, int var3) {
      Bridge3_24 var4 = this.field10.get(this.field9.hashCode());
      boolean var5 = var4 == null || var2 != var4.bridge$framebufferWidth() || var3 != var4.bridge$framebufferHeight();
      if (var4 == null || var5) {
         if (var4 != null) {
            var4.bridge$delete();
         }

         Bridge3_24 var6 = this.field8 ? Bridge3_24.method4(var2, var3) : Bridge3_24.method2(var2, var3, false);
         var6.bridge$setClearColor(0.0F, 0.0F, 0.0F, 0.0F);
         var4 = var6;
         this.field10.put(this.field9.hashCode(), var4);
      }

      this.field11 = var4;
      this.method6(var2, var3);
      this.field11.bridge$framebufferClear();
      ThreadModuleDump63.method3().method1(this.field11, true);
      ShaderCloakRenderer.method31(this.field11);
   }

   private void method6(int var1, int var2) {
      if (this.field9 != null && var1 > 0 && var2 > 0) {
         if (var1 != this.field15 || var2 != this.field16) {
            this.field9.size(var1, var2);
            this.field15 = var1;
            this.field16 = var2;
         }
      }
   }

   private void method7(AbstractRenderContext var1) {
      ThreadModuleDump63.method3().method1(null, true);
      ShaderCloakRenderer.method31(null);
   }

   private void method8(boolean var1) {
      Optional var2 = Bridge.method5();
      if (var2.isPresent()) {
         Slayer2 var3 = (Slayer2)var2.get();
         Slayer4 var4 = var3.getConfig();
         if (var1) {
            if (var4.hasFastRender() && (field2 == null || field2)) {
               if (field2 != null) {
                  field2 = null;
                  return;
               }

               Slayer.method3("OptiFine Fast Render disabled due to active GameTextures");
               field2 = false;
               field3 = true;
            }

            if (var4.hasAntiAliasing() && var4.getAntialiasingLevel() > 0 && field4 == null) {
               field6 = var4.getAntialiasingLevel();
               Slayer.method3("OptiFine Antialiasing disabled due to active GameTextures (original level: " + field6 + ")");
               field4 = 0;
               field5 = true;
            }
         } else {
            if (!field1.isEmpty()) {
               return;
            }

            if (field3 && (field2 == null || !field2)) {
               if (field2 != null) {
                  field2 = null;
                  return;
               }

               Slayer.method3("OptiFine Fast Render enabled");
               field2 = true;
               field3 = false;
            }

            if (field5 && field4 == null && var4.getAntialiasingLevel() == 0 && field6 != null) {
               field4 = field6;
               Slayer.method3("OptiFine Antialiasing restored to level: " + field4);
               field5 = false;
            }
         }
      }
   }

   private void invalidate() {
      this.method9(this.field11);
   }

   private void method9(Bridge3_24 var1) {
      if (this.field9 != null) {
         int var2 = var1.bridge$framebufferWidth();
         int var3 = var1.bridge$framebufferHeight();
         Bridge.method8().method91(var1.bridge$getColorTexture(true), 0, 0, var2, var3, TexturePixelFormat.RGBA8, this::update);
      }
   }

   private void update(ByteBuffer var1) {
      if (this.field9 != null) {
         byte var2 = 0;

         for (int var3 = var1.remaining(); var2 < var3; var2 += 4) {
            byte var4 = var1.get(var2);
            var1.put(var2, var1.get(var2 + 2));
            var1.put(var2 + 2, var4);
         }

         this.field9.update(var1);
         this.method10();
      }
   }

   private void method10() {
      if (!this.field12) {
         this.field12 = true;
         this.field9.bind();
         DriverViewportLegacy.method50()
            .method23(
               DriverViewportLegacy.method50().method55().method13(), "gameTexture", ThreadModuleDump48.field22.toJson(new Fishing.Data2(this.field7, true))
            );
      }
   }

   private boolean method11() {
      if (this.field9 == null && !DriverViewportLegacy.method53()) {
         DriverViewportLegacy.method50().method46(this.field7);
         this.field9 = DriverViewportLegacy.method52().createImageSourceTextured(this.field7);
         field1.add(this);
      }

      return this.field9 != null;
   }

   public static void method12() {
      if (field2 != null || field4 != null) {
         Bridge5_12 var0 = ThreadModuleDump63.method3();
         Optional var1 = Bridge.method5();
         if (var1.isPresent()) {
            boolean var2 = Bridge.getMinecraftVersion().method6(Config.field1);
            boolean var3 = false;
            boolean var4 = false;
            boolean var5 = false;
            Slayer4 var6 = ((Slayer2)var1.get()).getConfig();
            if (field4 != null) {
               int var7 = field4;
               if (var6.hasAntiAliasing() && var7 == 0) {
                  var6.setAntialiasingLevel(0);
                  var3 = true;
                  var4 = true;
               } else if (!var6.hasFastRender() && var7 > 0) {
                  var6.setAntialiasingLevel(var7);
                  var5 = true;
                  var4 = true;
                  var3 = true;
               }

               field4 = null;
            }

            if (field2 != null) {
               if (var6.hasFastRender() && !field2) {
                  var6.setFastRender(false);
                  var4 = true;
                  var3 = var3 || var2;
               } else if (!var6.hasFastRender() && field2) {
                  var6.setFastRender(true);
                  var5 = true;
                  var4 = true;
                  var3 = var3 || var2;
               }

               field2 = null;
            }

            if (var5) {
               var0.bridge$getGameRenderer().bridge$stopUseShader();
            }

            if (var4) {
               var6.updateFramebufferSize();
               var0.bridge$recreateLoadingScreen();
            }

            if (var3) {
               var6.updateLevelRenderer();
            }
         }
      }
   }

   private class Data2 {
      @SerializedName("id")
      private final String field1;
      @SerializedName("ready")
      private final boolean ready;

      private Data2(String var1, boolean var2) {
         this.field1 = var1;
         this.ready = var2;
      }

      @SerializedName("id")
      public String id() {
         return this.field1;
      }
   }
}
