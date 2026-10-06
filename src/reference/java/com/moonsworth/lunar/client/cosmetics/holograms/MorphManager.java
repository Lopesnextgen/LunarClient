package com.moonsworth.lunar.client.cosmetics.holograms;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.IResourceBridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.render.turbo.BoneTransformTable;
import com.moonsworth.lunar.client.render.particle.BedrockScheme;
import com.moonsworth.lunar.client.render.particle.HologramBatchRenderer;
import com.moonsworth.lunar.client.render.particle.ParticleEffectLoader;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.gui.DisconnectEvent;
import com.moonsworth.lunar.client.event.mixin.gui.LocationChangeEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.HologramUpdateEvent;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import lombok.Generated;
import lombok.NonNull;
import mchorse.emoticons.capabilities.cosmetic.EmoteController;
import mchorse.emoticons.morph.Morph;
import mchorse.emoticons.morph.MorphConfiguration;
import mchorse.emoticons.morph.MorphEntry;
import com.moonsworth.lunar.client.alert.DismissedAlertStore;

public class MorphManager extends com.moonsworth.lunar.client.framework.loading.ItemMapHandler<String, BedrockScheme> {
   private final HashMap<UUID, com.moonsworth.lunar.client.cosmetics.emote.EmoteMorphAnimator> field2 = new HashMap<>();
   private final HashMap<UUID, com.moonsworth.lunar.client.cosmetics.emote.EmoteMorphAnimator> field3 = new HashMap<>();
   private static final ResourceLocationBridge field4 = ResourceLocationBridge.create("lunar", "particles/configuration.json");
   private MorphConfiguration field5;
   private final Set<String> field6 = new HashSet<>();
   private static Vector4f vector;
   private static Matrix4f matrix4f;

   public MorphManager() {
      ClientEventBus.method29().method2(HologramUpdateEvent.class, this::method4);
      ClientEventBus.method29()
         .method2(
            EventClientTick.class,
            var1x -> {
               if (ThreadModuleDump63.method8() == null) {
                  this.field2.clear();
               } else {
                  this.field2
                     .keySet()
                     .removeIf(
                        var0 -> ThreadModuleDump63.method8()
                           .bridge$getPlayerByUniqueId(var0)
                           .map(var0x -> var0x.bridge$isRemoved() || var0x.bridge$getWorld() != ThreadModuleDump63.method8())
                           .orElse(true)
                     );
                  this.method4().forEach(com.moonsworth.lunar.client.cosmetics.emote.MorphRenderer::method3);
               }
            }
         );
      ClientEventBus.method29()
         .method2(
            com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent.class,
            var1x -> HologramBatchRenderer.method1(var1x.method3(), var1x.method4(), this.method4())
         );
      IResourceBridge var1 = Bridge.method9().bridge$getResourceManager().bridge$getResource(field4);
      if (var1 != null) {
         this.field5 = MorphConfiguration.parse(new InputStreamReader(var1.bridge$getInputStream()));
      } else {
         Slayer.method5("Could not find morph configuration json file: " + field4, new Object[0]);
         this.field5 = new MorphConfiguration(Map.of());
      }

      ClientEventBus.method29().method2(com.moonsworth.lunar.client.event.render.EventPlayerModelRender.class, this::method1);
      ClientEventBus.method29().method2(DisconnectEvent.class, var1x -> this.field2.clear());
      ClientEventBus.method29().method2(LocationChangeEvent.class, var1x -> this.field2.clear());
   }

   private void method1(com.moonsworth.lunar.client.event.render.EventPlayerModelRender var1) {
      com.moonsworth.lunar.client.cosmetics.emote.EmoteMorphAnimator var2 = this.method5(var1.method1()).get(var1.method1().bridge$getUniqueID());
      if (var2 != null && var2.method2()) {
         EntityPlayerBridge var3 = var1.method1();
         Matrix4f var4 = var1.method2();
         BoneTransformTable var5 = new BoneTransformTable(var4, var1.method3());
         Matrix4f var6 = new Matrix4f();
         var6.setIdentity();
         BridgeExtension var7 = ThreadModuleDump63.method3().bridge$getRenderViewEntity();
         if (var7 != null && !com.moonsworth.lunar.client.driver.core.holograms.HologramRendererLegacy.field1) {
            var6.setTranslation(
               new Vector3f(
                  (float)(var7.bridge$getPosX() - var3.bridge$getPosX()),
                  (float)(var7.bridge$getPosY() - var3.bridge$getPosY()),
                  (float)(var7.bridge$getPosZ() - var3.bridge$getPosZ())
               )
            );
         }

         this.method8(var3, var6, var5, var1.method4());
      }
   }

   private List<com.moonsworth.lunar.client.cosmetics.emote.MorphRenderer> method4() {
      ArrayList var1 = new ArrayList();
      Consumer var2 = var1::add;
      Itemcounter6Extension var3 = ThreadModuleDump63.method8();
      if (var3 == null) {
         this.field2.clear();
         return Collections.emptyList();
      } else {
         Rewind var4 = ThreadModuleDump63.method4().method40().method85();
         boolean var5 = var4 != null && var4.method17(var0 -> var0.method45().method19() || !var0.method45().method15().isFixedToPlayer());
         this.field2.forEach((var3x, var4x) -> var3.bridge$getPlayerByUniqueId(var3x).ifPresent(var3xx -> {
            boolean var4xx = var3xx == ThreadModuleDump63.method7() && ThreadModuleDump63.method3().bridge$getGameSettings().bridge$getThirdPersonView() == 0;
            if (!var3xx.bridge$isSpectator() && !var3xx.bridge$isInvisible() && (!var4xx || var5)) {
               var4x.method1(var2);
            }
         }));
         return var1;
      }
   }

   public List<com.moonsworth.lunar.client.cosmetics.emote.MorphRenderer> method3(UUID var1) {
      ArrayList var2 = new ArrayList();
      Consumer var3 = var2::add;
      if (this.field3.containsKey(var1)) {
         this.field3.get(var1).method1(var3);
      }

      return var2;
   }

   private void method4(HologramUpdateEvent var1) {
      Bridge6_10 var2 = var1.method1();
      if (var2.bridge$getWorld().bridge$isRemote()) {
         EmoteController var3 = (EmoteController)EmoteController.get(var2);
         com.moonsworth.lunar.client.cosmetics.emote.EmoteMorphAnimator var4 = this.method5(var1.method1())
            .computeIfAbsent(var2.bridge$getUniqueID(), var1x -> new com.moonsworth.lunar.client.cosmetics.emote.EmoteMorphAnimator(this.field5));
         var4.method3(var3, var1.method1());
      }
   }

   private Map<UUID, com.moonsworth.lunar.client.cosmetics.emote.EmoteMorphAnimator> method5(EntityPlayerBridge var1) {
      return var1.method2() ? this.field3 : this.field2;
   }

   @Override
   protected Map<String, BedrockScheme> method3() {
      return new HashMap<>();
   }

   public void method7(String var1) {
      if (!this.method3().containsKey(var1)) {
         BedrockScheme var2 = null;

         try {
            var2 = ParticleEffectLoader.method1(ResourceLocationBridge.create("lunar", "particles/schemes/" + var1 + ".particle.json"));
         } catch (IOException var4) {
            var4.printStackTrace();
         }

         if (var2 != null) {
            this.method3().put(var1, var2);
         }
      }
   }

   public void method8(EntityPlayerBridge var1, Matrix4f var2, com.moonsworth.lunar.client.cosmetics.emote.IBoneRenderer var3, float var4) {
      com.moonsworth.lunar.client.cosmetics.emote.EmoteMorphAnimator var5 = this.method5(var1).get(var1.bridge$getUniqueID());
      if (var5 != null) {
         var5.method4(var1, var2, var3, var4);
      }
   }

   public void method9(Matrix4f var1, EntityPlayerBridge var2, com.moonsworth.lunar.client.cosmetics.emote.IBoneRenderer var3, @NonNull DismissedAlertStore var4, float var5) {
      if (var4 == null) {
         throw new NullPointerException("emoteEffect is marked non-null but is null");
      }

      if (var4.getBone() != null) {
         var3.applyBoneTransform(var1, var4.getBone());
      }

      var4.method2().method1(var1);
      Matrix3f var6 = new Matrix3f();
      var6.m00 = var1.m00;
      var6.m01 = var1.m01;
      var6.m02 = var1.m02;
      var6.m10 = var1.m10;
      var6.m11 = var1.m11;
      var6.m12 = var1.m12;
      var6.m20 = var1.m20;
      var6.m21 = var1.m21;
      var6.m22 = var1.m22;
      Vector4f var7 = this.method10(var1, var2, var5);
      var4.method4(var7, var6);
   }

   public static Matrix4f getMatrix() {
      if (matrix4f == null) {
         matrix4f = new Matrix4f();
      }

      return matrix4f;
   }

   public Vector4f getVector() {
      if (vector == null) {
         vector = new Vector4f();
      }

      return vector;
   }

   public Vector4f method10(Matrix4f var1, EntityPlayerBridge var2, float var3) {
      Vector4f var4 = this.getVector();
      var4.set(0.0F, 0.0F, 0.0F, 1.0F);
      var1.transform(var4);
      if (ThreadModuleDump63.MC_VERSION >= 26) {
         var4.add(new Vector4f((float)var2.bridge$getPosX(), (float)var2.bridge$getPosY(), (float)var2.bridge$getPosZ(), 0.0F));
      } else {
         Bridge6_10 var5 = (Bridge6_10)var2;
         var4.add(
            new Vector4f(
               (float)var5.method21(var3),
               (float)var5.IHRHHRIHICHOOICIRIOOHOICHIRHOI(var3),
               (float)var5.IIORCIOOIHRRRICOHIRCIHOOCCOHRO(var3),
               0.0F
            )
         );
      }

      return var4;
   }

   public void method11(String var1) {
      if (!this.field6.contains(var1)) {
         this.field6.add(var1);
         MorphEntry var2 = (MorphEntry)this.field5.morphs().get(var1);
         if (var2 == null) {
            throw new IllegalStateException("Tried to register a morph " + var1 + " not present in the morph configuration");
         }

         for (Morph var4 : var2.getMorphs()) {
            if (!var4.getMorphNbt().bridge$getString("Scheme").equals("")) {
               this.method7(var4.getMorphNbt().bridge$getString("Scheme"));
            }
         }
      }
   }

   @Generated
   public HashMap<UUID, com.moonsworth.lunar.client.cosmetics.emote.EmoteMorphAnimator> method12() {
      return this.field3;
   }
}
