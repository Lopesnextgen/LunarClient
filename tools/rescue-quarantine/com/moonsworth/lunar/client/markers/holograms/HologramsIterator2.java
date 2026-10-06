package com.moonsworth.lunar.client.markers.holograms;

import com.eliotlash.molang.ast.Evaluator;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge17;
import com.moonsworth.lunar.bridge.Bridge20;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.Bridge3Extension_7;
import com.moonsworth.lunar.bridge.Bridge3_4;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.Bridge5_6;
import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.BridgeExtension_9;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.SExtension;
import com.moonsworth.lunar.bridge.Bridge12.Extension2;
import com.moonsworth.lunar.bridge.Bridge2.Data;
import com.moonsworth.lunar.bridge.horsestats.Horsestats;
import com.moonsworth.lunar.bridge.horsestats.Horsestats14;
import com.moonsworth.lunar.client.Slayer;
import com.moonsworth.lunar.client.fog.holograms.Holograms12;
import com.moonsworth.lunar.client.fov.Fov10;
import com.moonsworth.lunar.client.fov.Fov11;
import com.moonsworth.lunar.client.fov.Fov2_2;
import com.moonsworth.lunar.client.fov.Fov3;
import com.moonsworth.lunar.client.fov.Fov6_3;
import com.moonsworth.lunar.client.fov.Fov8;
import com.moonsworth.lunar.client.fov.FovType3;
import com.moonsworth.lunar.client.fov.Gui2Iterator;
import com.moonsworth.lunar.client.fov.mixin.Gui2Handler3;
import com.moonsworth.lunar.client.fov.mixin.Gui2Type;
import com.moonsworth.lunar.client.glintcolorizer.nameplate.Nameplate;
import com.moonsworth.lunar.client.inactive.mixin.Gui2Handler;
import com.moonsworth.lunar.client.inactive.rewindhandlers.Rewindhandlers2_2;
import com.moonsworth.lunar.client.markers.Markers;
import com.moonsworth.lunar.client.markers.mixin.holograms.Holograms4;
import com.moonsworth.lunar.client.markers.mixin.holograms.mixin.Holograms2Iterator;
import com.moonsworth.lunar.client.markers.mixin.holograms.mixin.HologramsType;
import com.moonsworth.lunar.client.markers.mixin.holograms.mixin.HologramsType2;
import com.moonsworth.lunar.client.util.ThreadModuleDump54;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump91;
import com.moonsworth.lunar.client.util.alert.Alert5;
import com.moonsworth.lunar.config.Config;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import lombok.Generated;
import org.joml.Matrix4f;
import org.joml.Vector4f;

public class HologramsIterator2 implements Holograms_2<Holograms2Iterator> {
   private static final Map<String, Horsestats14> field1 = new HashMap<>();
   private static final float field2 = 1.15F;
   private static final float field3 = 0.0F;
   private static Bridge5Extension_5 field4;
   private static boolean field5;
   private static Horsestats14 field6;
   private UUID uuid;
   public static boolean field7 = false;
   public static float playerViewX;
   public static float playerViewY;
   private Holograms2Iterator field8;

   public void method1(Holograms2Iterator var1) {
      if (method14() != null) {
         if (this.uuid == null) {
            this.uuid = field4.bridge$getUniqueID();
         }

         var1.init();
         HologramsType2 var2 = var1.method10();
         field4.bridge$setUniqueID(var1.getUuid());
         ThreadModuleDump54 var3 = (ThreadModuleDump54)field4;
         ThreadModuleDump54.Type var4 = var3.getDummyPlayerType();
         ThreadModuleDump54.Type var5 = var1.method10() == HologramsType2.SELF ? ThreadModuleDump54.Type.SELF : ThreadModuleDump54.Type.MANNEQUIN;
         var3.setDummyPlayerType(var5);
         var3.setRenderNametag(var1.isRenderNametag());
         if (Bridge.getMinecraftVersion() != Config.field1 && (var4 != var5 || !field5)) {
            field5 = true;
            int var6 = 0;
            if (var5 == ThreadModuleDump54.Type.SELF) {
               var6 |= 1;

               for (Bridge5_6 var8 : ThreadModuleDump63.method3().bridge$getGameSettings().bridge$getModelParts()) {
                  var6 |= var8.bridge$getMask();
               }
            } else {
               var6 = 255;
            }

            field4.bridge$getDataWatcher().bridge$updateObject(10, (byte)var6);
         }

         if (var1.method33() != null) {
            String var10 = var1.method33().getType();
            if (var10.equals("classic")) {
               var10 = "default";
            }

            Horsestats14 var11 = method18(var1.method33().getHash(), var1.method33().getUrl());
            field4.bridge$setSkinLocationOverride(var11, var10);
         } else {
            field4.bridge$setSkinLocationOverride(var2.getSkinLocation(), var2.getSkinType());
         }

         field4.bridge$getInventory().bridge$getMainInventory().set(0, var1.method43());
         if (ThreadModuleDump63.MC_VERSION >= 5) {
            field4.bridge$getInventory().bridge$setOffhandItem(var1.method44());
         }
      }
   }

   public void method2(BridgeExtension_9 var1, Holograms2Iterator var2, Markers.Data5 var3) {
      if (method14() != null) {
         field7 = true;
         this.field8 = var2;
         double var4 = this.method9(var1, var2);
         this.method4(var1, var2, var4);
         this.method6(var1, var2, var4);
         if (var2.isEmoting() || var2.method23()) {
            var1.translate(0.0, var4, 0.0);
         }

         this.method8(var1, var2);
         this.method13(var1);
         field4.bridge$setSkinLocationOverride(null, null);
         field4.bridge$getInventory().bridge$getMainInventory().set(0, null);
         field4.bridge$setUniqueID(this.uuid);
         this.field8 = null;
         field7 = false;
      }
   }

   private void method3(BridgeExtension_9 var1, Holograms2Iterator var2, double var3) {
      if (var2.isRenderNametag()) {
         BridgeExtension var5 = ThreadModuleDump63.method3().bridge$getRenderViewEntity();
         ThreadModuleDump63.method3().bridge$setRenderViewEntity(null);
         var1.method4(-180.0F, 0.0F, 1.0F, 0.0F);
         if (var2.RHCHOROROOORIRCOHHCOOCICCIICHI() && var2.HOOCCOICOORRIIRHIOOOIIIRIRCROH() != 0.0F) {
            var1.method4(-var2.HOOCCOICOORRIIRHIOOOIIIRIRCROH(), 0.0F, 1.0F, 0.0F);
         }

         for (Vector4f var7 : var2.getDisplay().getRotation()) {
            var1.method4(-var7.x, 0.0F, var7.z, 0.0F);
         }

         SExtension var8 = (SExtension)Bridge.method9().bridge$getEntityRenderDispatcher().bridge$defaultPlayerRenderer();
         if (var1.method38()) {
            var1.translate(0.0, -var3 + 0.22F, 0.0);
            if (ThreadModuleDump63.MC_VERSION >= 26) {
               var1.scale(-1.0F, 1.0F, -1.0F);
               var1.method4(var2.HRHIRHROCHOROHHROHOHROIOIORIIR(), -1.0F, 0.0F, 0.0F);
            } else {
               var1.method4(var2.HRHIRHROCHOROHHROHOHROIOIORIIR(), 1.0F, 0.0F, 0.0F);
            }

            var8.bridge$renderName(field4, 0.0, -2.2F, 0.0, 0.0F, var1.method30().method51(), (Bridge17)var1.method30().method43().get(), 0.0F);
         }

         ThreadModuleDump63.method3().bridge$setRenderViewEntity(var5);
      }
   }

   private void method4(BridgeExtension_9 var1, Holograms2Iterator var2, double var3) {
      Bridge2_43 var5 = ThreadModuleDump63.method3().bridge$getEntityRenderDispatcher();
      if (Bridge.getMinecraftVersion().method19()) {
         Bridge5_16 var6 = var1.method30().method51();
         Extension2 var7 = Bridge.method42().method83();
         Bridge5_16 var8 = null;
         if (Bridge.getMinecraftVersion().method23()) {
            var8 = var7.method12();
            var8.bridge$pushPose();
            var8.bridge$mulPose(var6.bridge$last().bridge$pose());
            var7.method6();
         } else {
            var7.CROORCRRCORRICIOIRIICOOICHOHOO();
            var7.method17(var6.bridge$last().bridge$pose());
         }

         var6.bridge$pushPose();
         var6.bridge$loadIdentity();
         this.method12(var6, var2);
         if (ThreadModuleDump63.MC_VERSION <= 32) {
            var5.bridge$setRenderShadow(false);
         }

         this.method5(var1, var3, var5);
         this.method3(var1, var2, var3);
         if (ThreadModuleDump63.MC_VERSION <= 32) {
            var5.bridge$setRenderShadow(true);
         }

         var1.method30().method48();
         var6.bridge$popPose();
         if (Bridge.getMinecraftVersion().method23()) {
            var8.bridge$popPose();
            var7.method6();
         } else {
            var7.IORRRICHRIHCCIORRIICIIORIRRRRC();
         }
      } else {
         var1.push();
         this.method11(var1, var2);
         var5.bridge$setRenderShadow(false);
         this.method5(var1, var3, var5);
         var5.bridge$setRenderShadow(true);
         var1.pop();
      }
   }

   private void method5(BridgeExtension_9 var1, double var2, Bridge2_43 var4) {
      if (Bridge.getMinecraftVersion().method21()) {
         Bridge.method42().method18(770, 771, 1, 771);
      }

      if (var1.method38() && var4.bridge$getCamera().isEmpty()) {
         var4.bridge$prepare(field4.bridge$getWorld(), field4);
      }

      boolean var5 = false;
      if (ThreadModuleDump63.method3().bridge$getWorld() == null && field4.bridge$getWorld() instanceof Itemcounter6Extension var6) {
         ThreadModuleDump63.method3().bridge$setWorldDirect(var6);
         var5 = true;
      }

      boolean var11 = false;
      if (ThreadModuleDump63.method3().bridge$getPlayer() == null) {
         ThreadModuleDump63.method3().bridge$setPlayer(field4);
         ThreadModuleDump63.method3().bridge$setRenderViewEntity(field4);
         var11 = true;
      }

      try {
         var4.bridge$renderEntityWithPosYaw(var1, field4, 0.0, var2, 0.0, 0.0F, 0.0F, Bridge.method8().method92());
      } finally {
         if (var5) {
            ThreadModuleDump63.method3().bridge$setWorldDirect(null);
         }

         if (var11) {
            ThreadModuleDump63.method3().bridge$setPlayer(null);
            ThreadModuleDump63.method3().bridge$setRenderViewEntity(null);
         }

         if (Bridge.getMinecraftVersion().method21()) {
            Bridge.method42().method36();
         }
      }
   }

   private void method6(BridgeExtension_9 var1, Holograms2Iterator var2, double var3) {
      if (var2.method36() && var2.method39() != null) {
         for (Gui2Handler3 var6 : var2.method39()) {
            if (var6.method4().method10() == Gui2Type.COMPANION && var6.method4() instanceof Gui2Iterator var7) {
               this.method7(var1, var2, var3, var6, var7);
            }
         }
      }
   }

   private void method7(BridgeExtension_9 var1, Holograms2Iterator var2, double var3, Gui2Handler3 var5, Gui2Iterator var6) {
      if (ThreadModuleDump63.method4().method76().method14(var6)) {
         Optional var7 = var6.method5();
         if (!var6.method6().isEmpty() && !var7.isEmpty()) {
            com.moonsworth.lunar.client.inactive.mixin.holograms.Holograms var8 = (com.moonsworth.lunar.client.inactive.mixin.holograms.Holograms)var7.get();
            Fov10 var9 = Fov10.method11(method14());
            Holograms12 var10 = ThreadModuleDump63.method4().method53();
            var10.method22(var6.HHRHHCIOIOIHOHRCCHRHOOHRHRIHRO()).ifPresentOrElse(var9::method24, () -> var9.method24(var5));
            var8.method1(var9);
            var8.setLivingAnimations(var6, var6.method4());
            Evaluator var11 = var8.IIRCROCIRCCHHIHRIOCROOOCRHIHHO().getEvaluator();
            Optional var12 = var8.RICOCIIIHIRIRHCIICHCIIRCRIRIRO(var8.method1(var6, var11));
            if (!var12.isEmpty()) {
               Horsestats14 var13 = Fov11.method24(var6, var8, null, var11);
               UUID var14 = ThreadModuleDump63.method7() == null ? null : ThreadModuleDump63.method7().bridge$getUniqueID();
               Optional var15 = var10.method37(var13, var14);
               if (!var15.isEmpty()) {
                  Bridge3_4 var16 = ((Bridge8Extension3)var15.get()).method2();
                  if (var16 != null) {
                     if (var16 instanceof Bridge3Extension_7 var17) {
                        var17.method3(true);
                     }

                     if (var16 instanceof Alert5 var21) {
                        var21.method12();
                     }
                  }

                  float var22 = var2.method37() != null ? var2.method37() : 1.15F;
                  float var18 = var2.method38() != null ? var2.method38() : 0.0F;
                  var1.push();
                  this.method11(var1, var2);
                  var1.translate(var22, var3 + var18, 0.0);
                  var1.method4(180.0F, 0.0F, 1.0F, 0.0F);
                  var1.method16();
                  var1.method14();

                  for (ThreadModuleDump91 var20 : var6.method6().get().method12()) {
                     if (var20.getCondition().applies(var9, var5)) {
                        var20.transform(var1, null, 0.0F);
                     }
                  }

                  Fov8 var23 = Fov8.method6()
                     .method1(var1)
                     .method2(var8.getAnimationProcessor())
                     .method3(var13)
                     .method4((Rewindhandlers2_2)var12.get())
                     .method8(FovType3.NORMAL)
                     .method5(var6.method6().<Boolean>map(Gui2Handler::method8).orElse(false))
                     .method12();
                  var1.method7(var2x -> {
                     ThreadModuleDump63.method3().bridge$getTextureManager().bridge$bindTexture(var13);
                     Fov6_3.method1(var23);
                  }, var2x -> {
                     Bridge20 var3x = Gui2Iterator.method9(var13);
                     Bridge5_16 var4 = var2x.method51();
                     var4.bridge$pushPose();
                     Fov2_2.method3(var23, var3x, false, false);
                     var4.bridge$popPose();
                     var2x.method33(var3x);
                  });
                  var1.method15();
                  var1.method17();
                  var1.method33();
                  var1.pop();
               }
            }
         }
      }
   }

   private void method8(BridgeExtension_9 var1, Holograms2Iterator var2) {
      List var3 = ThreadModuleDump63.method4().method70().method3(var2.getUuid());
      Data var4 = new Data(var1.method28(), field4);
      if (Holograms4.field1) {
         Bridge.method42().method29();
      }

      var1.push();
      this.method11(var1, var2);
      Nameplate.method1(var1, var4, var3);
      var1.pop();
      if (Holograms4.field1) {
         Bridge.method42().method28();
      }
   }

   private double method9(BridgeExtension_9 var1, Holograms2Iterator var2) {
      HologramsType var3 = var2.getDisplay();
      HologramsType var4 = var2.OIRORIOHOCHRHOHHCCHHOCRRIOIHII() ? HologramsType.DUMMY : var2.getDisplay();
      float var5 = var2.OCHIRHIHHORHHIOHIICRRCHIHOCOOO().getWidth();
      float var6 = var2.OCHIRHIHHORHHIOHIICRRCHIHOCOOO().getHeight();
      Fov3.method29(true);
      Fov3.method30(var5, var6);
      var1.push();
      if (ThreadModuleDump63.method1()) {
         var1.method35(0.0, var5, var6, 0.0, 21000.0, 1000.0);
      } else {
         var1.method35(0.0, var5, var6, 0.0, 1000.0, ThreadModuleDump63.MC_VERSION >= 17 ? 21000.0 : 3000.0);
      }

      if (ThreadModuleDump63.MC_VERSION < 8) {
         var1.method27(0, 0, (int)var5, (int)var6);
      }

      float var7 = var4.getXOffset();
      float var8 = var4.getYOffset();
      float var9 = var4.getZoom() * var2.getZoom();
      float var10 = var2.OCHIRHIHHORHHIOHIICRRCHIHOCOOO().getHeight() / 1.9F * var9;
      var1.method22();
      var1.method18();
      if (Bridge.getMinecraftVersion().method21()) {
         var1.method26();
      }

      var1.method25(1.0F, 1.0F, 1.0F, 1.0F);
      double var11 = ThreadModuleDump63.MC_VERSION == 0 ? 0.67 : -0.95;
      var1.translate(var5 * var7 + var2.getX(), -var2.getY() + var6 / 2.0F + var8 * var6, 0.0);
      var1.scale(-var10, var10, var10);
      playerViewX = var2.HRHIRHROCHOROHHROHOHROIOIORIIR();
      playerViewY = 180.0F + var2.HOOCCOICOORRIIRHIOOOIIIRIRCROH();
      var1.method5(0.0F, 0.0F, 180.0F);
      return var11;
   }

   public Matrix4f method10() {
      Matrix4f var1 = new Matrix4f();
      Holograms2Iterator var2 = this.field8;
      var1.translate(0.0F, 0.0F, -5.0F);
      if (var2.RCCHOIOHHOICRRCRHIOCOORRIRIOIR() && var2.HRHIRHROCHOROHHROHOHROIOIORIIR() != 0.0F) {
         var1.rotateX((float)Math.toRadians(var2.HRHIRHROCHOROHHROHOHROIOIORIIR()));
      }

      for (Vector4f var4 : var2.getDisplay().getRotation()) {
         playerViewX = playerViewX + var4.x * var4.y;
         playerViewY = playerViewY + var4.x * var4.z;
         var1.rotate((float)Math.toRadians(var4.x), var4.y, var4.z, var4.w);
      }

      if (var2.RHCHOROROOORIRCOHHCOOCICCIICHI() && var2.HOOCCOICOORRIIRHIOOOIIIRIRCROH() != 0.0F) {
         var1.rotateY((float)Math.toRadians(var2.HOOCCOICOORRIIRHIOOOIIIRIRCROH()));
      }

      return var1.invert();
   }

   private void method11(BridgeExtension_9 var1, Holograms2Iterator var2) {
      if (ThreadModuleDump63.MC_VERSION >= 7) {
         var1.translate(0.0, 0.0, -5.0);
      }

      if (var2.RCCHOIOHHOICRRCRHIOCOORRIRIOIR() && var2.HRHIRHROCHOROHHROHOHROIOIORIIR() != 0.0F) {
         var1.method4(var2.HRHIRHROCHOROHHROHOHROIOIORIIR(), 1.0F, 0.0F, 0.0F);
      }

      for (Vector4f var4 : var2.getDisplay().getRotation()) {
         playerViewX = playerViewX + var4.x * var4.y;
         playerViewY = playerViewY + var4.x * var4.z;
         var1.method4(var4.x, var4.y, var4.z, var4.w);
      }

      if (var2.RHCHOROROOORIRCOHHCOOCICCIICHI() && var2.HOOCCOICOORRIIRHIOOOIIIRIRCROH() != 0.0F) {
         var1.method4(var2.HOOCCOICOORRIIRHIOOOIIIRIRCROH(), 0.0F, 1.0F, 0.0F);
      }
   }

   private void method12(Bridge5_16 var1, Holograms2Iterator var2) {
      var1.bridge$translate(0.0, 0.0, -5.0);
      if (var2.RCCHOIOHHOICRRCRHIOCOORRIRIOIR() && var2.HRHIRHROCHOROHHROHOHROIOIORIIR() != 0.0F) {
         var1.bridge$rotateDegrees(var2.HRHIRHROCHOROHHROHOHROIOIORIIR(), 0.0F, 0.0F);
      }

      for (Vector4f var4 : var2.getDisplay().getRotation()) {
         playerViewX = playerViewX + var4.x * var4.y;
         playerViewY = playerViewY + var4.x * var4.z;
         var1.bridge$rotateDegrees(var4.y * var4.x, var4.z * var4.x, var4.w * var4.x);
      }

      if (var2.RHCHOROROOORIRCOHHCOOCICCIICHI() && var2.HOOCCOICOORRIIRHIOOOIIIRIRCROH() != 0.0F) {
         var1.bridge$rotateDegrees(0.0F, var2.HOOCCOICOORRIIRHIOOOIIIRIRCROH(), 0.0F);
      }
   }

   private void method13(BridgeExtension_9 var1) {
      Fov3.method29(false);
      var1.method41();
      var1.pop();
      var1.method23();
      var1.method6(var1x -> {
         var1.method28(Bridge.method22().method5());
         var1.method13();
         var1.method28(Bridge.method22().method6());
      });
   }

   public static Bridge5Extension_5 method14() {
      if (field4 == null) {
         initialize();
      }

      return field4;
   }

   public static Horsestats14 method15() {
      if (field4 == null) {
         initialize();
      }

      return field6;
   }

   public static void initialize() {
      Horsestats var0 = ThreadModuleDump63.method3().bridge$getSession();
      if (var0 != null && var0.bridge$getProfile() != null && var0.bridge$getProfile().getId() != null) {
         field4 = method16();
         field4.bridge$getLocationSkin();
         com.moonsworth.lunar.client.fog.fishing.Gui2Handler var1 = ThreadModuleDump63.method4().method75().method12();
         field6 = method18(var1.getHash(), var1.getUrl());
      }
   }

   private static Bridge5Extension_5 method16() {
      Bridge5_12 var0 = ThreadModuleDump63.method3();
      Bridge5Extension_5 var1 = Bridge.method8().method48();
      var1.bridge$setDimension(0);
      var1.bridge$setMovementInput(Bridge.method8().method49(var0.bridge$getGameSettings()));
      Bridge2_43 var2 = var0.bridge$getEntityRenderDispatcher();
      var2.bridge$setTextureManager(var0.bridge$getTextureManager());
      var2.bridge$setLivingEntity(var1);
      var2.bridge$setOptions(var0.bridge$getGameSettings());
      var1.bridge$setOldPos(0.0, 0.0, 0.0);
      var1.bridge$setPosX(0.0);
      var1.bridge$setPreviousPosX(0.0);
      var1.bridge$setPosZ(0.0);
      var1.bridge$setPreviousPosZ(0.0);
      var1.bridge$setPosY(0.0);
      var1.bridge$setPreviousPosY(0.0);
      var1.bridge$setLastTickY(0.0);
      var1.bridge$setLastTickZ(-0.01);
      var1.bridge$setLastTickX(0.0);
      var1.bridge$setDimension(0);
      var1.bridge$preparePlayerToSpawn();
      var2.bridge$prepare(var1.bridge$getWorld(), var1);
      return var1;
   }

   public static void method17(UUID var0, Consumer<Bridge5Extension_5> var1) {
      UUID var2 = field4.bridge$getUniqueID();
      field4.bridge$setUniqueID(var0);
      var1.accept(field4);
      field4.bridge$setUniqueID(var2);
   }

   public static Horsestats14 method18(String var0, String var1) {
      Optional var2 = method19(var0, var1);
      return var2.orElseGet(() -> Horsestats14.create("minecraft", "textures/skins/wide/steve.png"));
   }

   public static Optional<Horsestats14> method19(String var0, String var1) {
      if (!field1.containsKey(var0)) {
         Horsestats14 var2;
         try {
            var2 = ThreadModuleDump63.method3()
               .bridge$getSkinManager()
               .bridge$registerTexture(new MinecraftProfileTexture(var1, Collections.emptyMap()), Type.SKIN);
         } catch (Exception var4) {
            Slayer.method8("Skin Changer", "Failed to load skin resource for " + var1 + ": " + var4.getMessage(), new Object[0]);
            var4.printStackTrace();
            return Optional.empty();
         }

         field1.put(var0, var2);
         return Optional.of(var2);
      } else {
         return Optional.ofNullable(field1.get(var0));
      }
   }

   @Generated
   public static Map<String, Horsestats14> method20() {
      return field1;
   }

   @Generated
   public static void method21(Horsestats14 var0) {
      field6 = var0;
   }
}
