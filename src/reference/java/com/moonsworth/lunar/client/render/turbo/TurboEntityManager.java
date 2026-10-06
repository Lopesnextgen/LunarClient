package com.moonsworth.lunar.client.render.turbo;

import com.eliotlash.molang.variables.ExecutionContext;
import com.eliotlash.molang.variables.VariableFlavor;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.OpenGlHelperBridge;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.Bridge3Extension_7;
import com.moonsworth.lunar.bridge.Bridge3_4;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.Bridge8_6;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.Bridge_28;
import com.moonsworth.lunar.bridge.Bridge_61;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.framework.loading.LoadableHandler;
import com.moonsworth.lunar.client.cosmetics.emote.RenderContext;
import com.moonsworth.lunar.client.cosmetics.PlayerModelPartMap;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelRenderConfig;
import com.moonsworth.lunar.client.cosmetics.gecko.RenderPass;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteModel;
import com.moonsworth.lunar.client.cosmetics.OwnedCosmetic;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.cosmetics.CosmeticCategoryType;
import com.moonsworth.lunar.client.cosmetics.molang.MolangScope;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.player.EventPlayerRemoval;
import com.moonsworth.lunar.client.event.player.PlayerJoinWorldEventLegacy;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.gui.DisconnectEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.EntitiesRenderEvent;
import com.moonsworth.lunar.client.cosmetics.gecko.EmoteDefinition;
import com.moonsworth.lunar.client.inactive.mixin.Gui2Impl;
import com.moonsworth.lunar.client.cosmetics.gecko.AnimationTaskEntry;
import com.moonsworth.lunar.client.cosmetics.molang.MolangRuntime;
import com.moonsworth.lunar.client.cosmetics.molang.MolangAnimationState;
import com.moonsworth.lunar.client.cosmetics.molang.EvaluatorImpl;
import com.moonsworth.lunar.client.cosmetics.gecko.BoneList;
import com.moonsworth.lunar.client.mod.misc.debug.CompanionDebug;
import com.moonsworth.lunar.client.mod.misc.debug.GeckolibDebugMod;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.cosmetics.ThreadModuleDump91;
import com.moonsworth.lunar.client.util.alert.Alert5;
import it.unimi.dsi.fastutil.objects.ObjectIntMutablePair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.function.BooleanSupplier;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Math;
import org.joml.Vector3d;
import com.moonsworth.lunar.client.framework.UnfocusedFpsLimiter;

public class TurboEntityManager implements LoadableHandler, EventRegistrar {
   private int nextId = Integer.MAX_VALUE;
   private final Map<Bridge_61, TurboEntityFilter<?>> field1 = new HashMap<>(1);
   private final Map<UUID, ObjectIntMutablePair<BooleanSupplier>> field2 = new HashMap<>(1);
   private final List<TurboEntityManager.Data> field3 = new ArrayList<>();
   @Nullable
   private List<UnfocusedFpsLimiter> field4;

   public TurboEntityManager() {
      this.handle(EventClientTick.class, this::method12);
      this.handle(EntitiesRenderEvent.class, this::method13);
      this.handle(EventWorldLifecycle.EventWorldLoaded.class, var1 -> {
         this.method5();
         if (this.field4 != null) {
            for (com.moonsworth.lunar.client.render.turbo.PathFinderHooks var3 : this.field4) {
               var3.method1();
            }
         }
      });
      this.handle(PlayerJoinWorldEventLegacy.class, var1 -> ThreadModuleDump37.method13(() -> this.method7(var1.method1()), 2));
      this.handle(EventPlayerRemoval.class, var1 -> {
         if (var1.method1() instanceof Bridge5_11 var2) {
            this.method4(var2);
         }
      });
      this.handle(DisconnectEvent.class, var1 -> this.method5());
   }

   public void method1(Itemcounter6 var1, Bridge8_6 var2, TurboEntityFilter<?> var3) {
      this.method2(var2.bridge$create(var1), var3);
   }

   public void method2(Bridge_61 var1, TurboEntityFilter<?> var2) {
      if (var1 instanceof BridgeExtension var3) {
         var3.bridge$decreaseEntityIdCounter();
         var3.bridge$setId(this.nextId);
         if (ThreadModuleDump63.MC_VERSION >= 6) {
            var3.bridge$setNoPhysics(true);
         }

         this.nextId--;
      }

      this.field1.put(var1, var2);
   }

   public void method3(BridgeExtension var1) {
      this.field1.remove(var1);
   }

   public void method4(Bridge5_11 var1) {
      this.field2.remove(var1.bridge$getUniqueID());
      this.field1.keySet().removeIf(var1x -> var1x instanceof EmoteDefinition var2 ? var2.method29().bridge$getUniqueID().equals(var1.bridge$getUniqueID()) : false);
   }

   public void method5() {
      this.field1.clear();
      this.nextId = Integer.MAX_VALUE;
   }

   public void method6() {
      this.field1.keySet().removeIf(var0 -> var0 instanceof EmoteDefinition);
   }

   public void method7(Bridge6_10 var1) {
      if (CosmeticCategoryType.COMPANION.canShowCosmetic()) {
         if (var1 instanceof Bridge5_11 var2) {
            if (!this.field2.containsKey(var2.bridge$getUniqueID())) {
               this.method4(var2);

               for (CosmeticMetadata var5 : ThreadModuleDump63.method4().method53().method18(var1.bridge$getUniqueID())) {
                  OwnedCosmetic var6 = var5.method4();
                  if (var6.method10() == CosmeticCategoryType.COMPANION && var6 instanceof EmoteModel var7) {
                     if (!this.method8(var2, var5, var7)) {
                        this.field2
                           .computeIfAbsent(
                              var2.bridge$getUniqueID(), var4 -> ObjectIntMutablePair.of((BooleanSupplier)() -> this.method8(var2, var5, var7), 10)
                           );
                     }

                     return;
                  }
               }
            }
         }
      }
   }

   public boolean method8(@NotNull Bridge5_11 var1, CosmeticMetadata var2, EmoteModel var3) {
      Optional var4 = var3.method6();
      if (var4.isEmpty()) {
         return false;
      }

      Optional var5 = var3.method5();
      if (var5.isEmpty()) {
         return false;
      }

      if (var4.get() instanceof Gui2Impl var6) {
         RenderContext var15 = RenderContext.method6(var1);
         EvaluatorImpl var8 = new EvaluatorImpl();
         var15.setEvaluator(var8);
         ExecutionContext var9 = new ExecutionContext(var8);
         MolangRuntime var10 = new MolangRuntime(var9, var8);
         var8.method1(var10);
         var8.setExecutionContext(var9);
         com.moonsworth.lunar.client.cosmetics.emote.MolangResourceModel var11 = (com.moonsworth.lunar.client.cosmetics.emote.MolangResourceModel)var5.get();
         var11.method1(var15);
         Optional var12 = var11.method1(var11.method1(var3, var15.getEvaluator()));
         if (var12.isEmpty()) {
            return false;
         }

         EmoteDefinition var13 = new EmoteDefinition(var1, var2, var3, var10, var6.method20());
         this.method9(var9, var13, var10.method10());
         var10.clearCache();
         var15.method13(var13);
         PathFilter var14 = new PathFilter(var15, var13, var6);
         this.method2(var13, var14);
         return true;
      } else {
         return false;
      }
   }

   private void method9(ExecutionContext var1, EmoteDefinition var2, MolangScope var3) {
      com.moonsworth.lunar.client.inactive.mixin.Gui2Handler var4 = var2.method28().method6().orElse(null);
      if (var4 instanceof Gui2Impl var5) {
         for (AnimationTaskEntry var9 : var5.method23()) {
            String var10 = var9.getId().toLowerCase();
            MolangAnimationState var11 = new MolangAnimationState("is_state_" + var10, false, var10, var2);
            var3.method2(VariableFlavor.LUNAR.name + ".is_or_was_state_" + var10, var11);
            var1.registerFunction(VariableFlavor.LUNAR.name + ".is_state_" + var10, var11);
            MolangAnimationState var12 = new MolangAnimationState("is_or_was_state_" + var10, false, var10, var2);
            var3.method2(VariableFlavor.LUNAR.name + ".is_or_was_state_" + var10, var12);
            var1.registerFunction(VariableFlavor.LUNAR.name + ".is_or_was_state_" + var10, var12);
         }
      }
   }

   public void method10() {
      this.method6();
      Itemcounter6Extension var1 = ThreadModuleDump63.method8();
      if (var1 != null) {
         var1.bridge$getPlayerEntities().forEach(this::method7);
      }
   }

   public void method11(UUID var1) {
      if (ThreadModuleDump63.method7() != null && ThreadModuleDump63.method7().bridge$getUniqueID().equals(var1)) {
         this.method7(ThreadModuleDump63.method7());
      } else {
         if (ThreadModuleDump63.method8() != null) {
            for (Bridge6_10 var3 : ThreadModuleDump63.method8().bridge$getPlayerEntities()) {
               if (var3.bridge$getUniqueID().equals(var1)) {
                  this.method7(var3);
                  return;
               }
            }
         }
      }
   }

   public void method12(EventClientTick var1) {
      Itemcounter6Extension var2 = ThreadModuleDump63.method8();
      if (var2 != null) {
         Bridge5Extension_5 var3 = ThreadModuleDump63.method7();
         if (var3 != null) {
            if (!this.field2.isEmpty() && var2.bridge$getGameTime() % 5L == 0L) {
               this.field2.entrySet().removeIf(var0 -> {
                  ObjectIntMutablePair var1x = var0.getValue();
                  int var2x = var1x.rightInt();
                  if (var2x <= 0) {
                     return true;
                  }

                  var1x.right(var2x - 1);
                  return ((BooleanSupplier)var1x.left()).getAsBoolean();
               });
            }

            this.field1.entrySet().removeIf(var3x -> ((TurboEntityFilter<Bridge_61>)var3x.getValue()).method1(this, var2, var3, var3x.getKey()));
            if (this.field4 != null) {
               for (com.moonsworth.lunar.client.render.turbo.PathFinderHooks var5 : this.field4) {
                  var5.method2();
               }
            }
         }
      }
   }

   private void method13(EntitiesRenderEvent var1) {
      if (!this.field1.isEmpty()) {
         AbstractRenderContext var2 = var1.method1();
         double var3 = var1.getX();
         double var5 = var1.getY();
         double var7 = var1.getZ();
         this.method14(var2.method28());
         this.method15(var2, var3, var5, var7);
         if (ThreadModuleDump63.MC_VERSION < 39) {
            this.method16(var2, var3, var5, var7);
         }

         this.method17(var2, var3, var5, var7);
      }
   }

   public void method14(float var1) {
      this.field3.clear();
      if (!this.field1.isEmpty() && ThreadModuleDump63.method7() != null) {
         float var2 = ThreadModuleDump63.method4().method41().method7().method24().get().intValue();
         var2 *= var2;

         for (Entry var4 : this.field1.entrySet()) {
            if (var4.getKey() instanceof EmoteDefinition var5 && var4.getValue() instanceof PathFilter var6 && !var5.bridge$isInvisible()) {
               double var18 = var5.bridge$distanceToCameraSq();
               if (!(var18 > var2) && ThreadModuleDump63.method4().method41().method6().method6(var5.method29()) && !var5.method28().method5().isEmpty()) {
                  double var9 = Math.lerp(var5.method11(), var5.bridge$getPosX(), var1);
                  double var11 = Math.lerp(var5.method12(), var5.bridge$getPosY(), var1);
                  double var13 = Math.lerp(var5.method13(), var5.bridge$getPosZ(), var1);
                  float var15 = EmoteDefinition.wrapDegrees(this.method18(var5.method34(), var5.getYaw(), var1)) + 180.0F;
                  this.field3.add(new TurboEntityManager.Data(var5, var6, var9, var11, var13, var15, var18));
               }
            }
         }
      }
   }

   public void method15(AbstractRenderContext var1, double var2, double var4, double var6) {
      for (TurboEntityManager.Data var9 : this.field3) {
         EmoteDefinition var10 = var9.method1();
         var1.push();

         try {
            var1.translate(var9.method3() - var2, var9.method4() - var4, var9.method5() - var6);
            this.method23(var10, var9.method2(), var1, var9.method6());
         } finally {
            var1.pop();
         }

         if (ThreadModuleDump48.field31) {
            this.method19(var1, var9.method3(), var9.method4(), var9.method5(), var2, var4, var6, var10, var9.method2());
         }
      }
   }

   public void method16(AbstractRenderContext var1, double var2, double var4, double var6) {
      if (!this.field3.isEmpty()
         && ThreadModuleDump63.method3().bridge$getGameSettings().bridge$getEntityShadows()
         && ThreadModuleDump63.method4().method40().method84().method37()) {
         Itemcounter6Extension var8 = ThreadModuleDump63.method8();
         if (var8 != null) {
            Bridge2_43 var9 = ThreadModuleDump63.method13();

            for (TurboEntityManager.Data var11 : this.field3) {
               EmoteDefinition var12 = var11.method1();
               var1.push();
               var1.translate(var11.method3() - var2, var11.method4() - var4, var11.method5() - var6);
               float var13 = var12.bridge$getWidth() * 0.75F;
               var9.bridge$renderShadow(var1, var8, var11.method3(), var11.method4(), var11.method5(), var13, var11.method7(), 1.0);
               var1.pop();
            }
         }
      }
   }

   public void method17(AbstractRenderContext var1, double var2, double var4, double var6) {
      float var8 = ThreadModuleDump63.method4().method41().method7().method24().get().intValue();
      var8 *= var8;
      Bridge2_43 var9 = ThreadModuleDump63.method13();

      for (Bridge_61 var11 : this.field1.keySet()) {
         if (!(var11 instanceof EmoteDefinition) && var11 instanceof BridgeExtension var12) {
            double var13 = var11.bridge$distanceToCameraSq();
            if (!(var13 > var8) && var12.bridge$shouldRenderAtSqrDistance(var13)) {
               this.method20(var1, var9, var12, var2, var4, var6);
            }
         }
      }
   }

   private float method18(float var1, float var2, float var3) {
      float var4 = ((var2 - var1 + 180.0F) % 360.0F + 360.0F) % 360.0F - 180.0F;
      return var1 + var4 * var3;
   }

   private void method19(
      AbstractRenderContext var1, double var2, double var4, double var6, double var8, double var10, double var12, EmoteDefinition var14, PathFilter var15
   ) {
      GeckolibDebugMod var16 = ThreadModuleDump63.method4().method40().method73();
      if (var16 != null && var16.method2(Framework.field10) && var16.isEnabled()) {
         CompanionDebug var17 = var16.method19();
         if (var17.isEnabled()) {
            if (var17.field8.get()) {
               var1.push();
               var1.translate(var2 - var8, var4 - var10, var6 - var12);
               AxisAlignedBBBridge var18 = AxisAlignedBBBridge.method2(-0.05, -0.05, -0.05, 0.05, 0.05, 0.05);
               Bridge_28 var19 = var1.method12(1.0F, false);
               Click.drawBoxWires(var19, var18, -1);
               var19.end();
               var1.method5(0.0F, var14.getYaw(), 0.0F);
               var19 = var1.method12(1.5F, false);
               var19.method1(-65536);
               float var20 = var14.bridge$getEyeHeight();
               var19.method3(0.0, var20, 0.0, 0.0, var20, 1.0);
               var19.end();
               var1.pop();
               Vector3d var21 = var14.method38();
               if (var21 != null) {
                  AxisAlignedBBBridge var22 = AxisAlignedBBBridge.method2(0.0, 0.0, 0.0, 0.1, 0.1, 0.1);
                  var1.push();
                  var1.translate(var21.x - var8 - 0.05, var21.y - var10 - 0.05, var21.z - var12 - 0.05);
                  var19 = var1.method12(1.0F, false);
                  Click.drawBoxWires(var19, var22, -16711936);
                  var19.end();
                  var1.pop();
               }

               TurboPath var36 = var15.method5().method30();
               if (var36 != null) {
                  AxisAlignedBBBridge var23 = AxisAlignedBBBridge.method2(0.1, 0.0, 0.1, 0.9, 0.2, 0.9);
                  int var24 = var36.method8();

                  for (int var25 = 0; var25 < var24; var25++) {
                     com.moonsworth.lunar.client.render.turbo.PathNode var26 = var36.method5(var25);
                     var1.push();
                     var1.translate(var26.x - var8, var26.y - var10, var26.z - var12);
                     var19 = var1.method12(1.0F, false);
                     if (var25 == var24 - 1) {
                        Click.drawBoxWires(var19, AxisAlignedBBBridge.method1(), -16711681);
                     } else {
                        Click.drawBoxWires(var19, var23, -65281);
                     }

                     var19.end();
                     var1.pop();
                  }
               }
            }

            boolean var27 = var17.field9.get();
            boolean var31 = var17.field10.get();
            if (var27 || var31) {
               AxisAlignedBBBridge var32 = var14.method7();
               AxisAlignedBBBridge var34 = var32.bridge$expand(var14.bridge$getMotionX(), var14.bridge$getMotionY(), var14.bridge$getMotionZ());
               if (ThreadModuleDump63.MC_VERSION >= 1 && ThreadModuleDump63.MC_VERSION <= 5) {
                  var34 = var34.bridge$expand(0.0, -1.0, 0.0);
                  if (ThreadModuleDump63.MC_VERSION == 1) {
                     var34 = var34.method11(0.5);
                  }
               }

               var1.push();
               var1.translate(-var8, -var10, -var12);
               if (var27) {
                  Bridge_28 var37 = var1.method12(1.0F, false);
                  Click.drawBoxWires(var37, var34, -16776961);
                  var37.end();
               }

               if (var31) {
                  for (AxisAlignedBBBridge var41 : ThreadModuleDump63.method8().bridge$getBlockCollisionBoxes(null, var34)) {
                     Bridge_28 var42 = var1.method12(2.0F, false);
                     Click.drawBoxWires(var42, var41.method11(0.01), -47958);
                     var42.end();
                  }
               }

               var1.pop();
            }

            List var33 = var17.method3(var14, false);
            int var35 = 0;

            for (int var39 = var33.size() - 1; var39 >= 0; var39--) {
               var1.push();
               Click.drawComponentCentered(var1, (Component)var33.get(var39), var2 - var8, var4 - var10 + 1.0 + var35 * 0.25, var6 - var12, true);
               var1.pop();
               var35++;
            }
         }
      }
   }

   private void method20(AbstractRenderContext var1, Bridge2_43 var2, BridgeExtension var3, double var4, double var6, double var8) {
      float var10 = var1.method28();
      double var11 = Math.lerp(var3.method3(), var3.bridge$getPosX(), var10);
      double var13 = Math.lerp(var3.method4(), var3.bridge$getPosY(), var10);
      double var15 = Math.lerp(var3.method5(), var3.bridge$getPosZ(), var10);
      var1.push();
      var1.translate(var11 - var4, var13 - var6, var15 - var8);
      var1.method5(0.0F, (float)var3.bridge$getRotationYaw(), 0.0F);
      var2.bridge$renderEntityWithPosYaw(var1, var3, 0.0, 0.0, 0.0, 0.0F, var10, var2.bridge$getPackedLightCoords(var3, var10));
      var1.pop();
   }

   public void method21(com.moonsworth.lunar.client.render.turbo.PathFinderHooks var1) {
      if (this.field4 == null) {
         this.field4 = new ArrayList<>();
      }

      this.field4.add(var1);
   }

   public void method22(com.moonsworth.lunar.client.render.turbo.PathFinderHooks var1) {
      if (this.field4 != null) {
         this.field4.remove(var1);
         if (this.field4.isEmpty()) {
            this.field4 = null;
         }
      }
   }

   public void method23(EmoteDefinition var1, PathFilter var2, AbstractRenderContext var3, float var4) {
      EmoteModel var5 = var1.method28();
      Optional var6 = var5.method5();
      if (!var6.isEmpty()) {
         com.moonsworth.lunar.client.cosmetics.emote.MolangResourceModel var7 = (com.moonsworth.lunar.client.cosmetics.emote.MolangResourceModel)var6.get();
         RenderContext var8 = var2.method4();
         var7.method1(var8);
         var7.setLivingAnimations(var5, var5.method4());
         Optional var9 = var7.method1(var7.method1(var5, var8.getEvaluator()));
         if (!var9.isEmpty()) {
            ResourceLocationBridge var10 = PlayerModelPartMap.method24(var5, var7, var8.method1().orElseThrow(), var8.getEvaluator());
            Optional var11 = ThreadModuleDump63.method4().method53().method37(var10, var8.method1().get().bridge$getUniqueID());
            if (!var11.isEmpty()) {
               Bridge8Extension3 var12 = (Bridge8Extension3)var11.get();
               Bridge3_4 var13 = var12.method2();
               if (var13 != null) {
                  if (var13 instanceof Bridge3Extension_7 var14) {
                     var14.method3(true);
                  }

                  if (var13 instanceof Alert5 var20) {
                     var20.method12();
                  }
               }

               var3.push();
               var3.method5(0.0F, var4, 0.0F);
               var5.method6().ifPresent(var3x -> {
                  for (ThreadModuleDump91 var5x : var3x.method12()) {
                     if (var5x.getCondition().applies(var8, var1.method27())) {
                        var5x.transform(var3, null, 0.0F);
                     }
                  }
               });
               Itemcounter6Extension var21 = ThreadModuleDump63.method8();
               Horsestats20Extension2 var15 = var1.method8();
               int var16 = var15 != null ? var21.bridge$getPackedLight(var15) : Bridge.method8().method92();
               if (var16 == 0 && var1.bridge$getPosY() - Math.floor(var1.bridge$getPosY()) > 0.75) {
                  var16 = var21.bridge$getPackedLight(var15.bridge$above());
               }

               int var17 = var16;
               ModelRenderConfig var18 = ModelRenderConfig.method6()
                  .method1(var3)
                  .method2(var7.getAnimationProcessor())
                  .method3(var10)
                  .method4((BoneList)var9.get())
                  .method8(RenderPass.NORMAL)
                  .method10(var16)
                  .method5(var5.method6().map(com.moonsworth.lunar.client.inactive.mixin.Gui2Handler::method8).orElse(false))
                  .method12();
               if (var3.method39()) {
                  OpenGlHelperBridge var19 = Bridge.method22();
                  var19.method4(var19.method5(), var17 & 0xFF, var17 >> 16);
               }

               PlayerModelPartMap.method1(var18);
               var3.pop();
            }
         }
      }
   }

   @Override
   public void close() {
   }

   @Override
   public void init() {
   }

   @Generated
   public Map<Bridge_61, TurboEntityFilter<?>> method24() {
      return this.field1;
   }

   private class Data {
      private final EmoteDefinition field1;
      private final PathFilter field2;
      private final double field3;
      private final double field4;
      private final double field5;
      private final float field6;
      private final double field7;

      private Data(EmoteDefinition var1, PathFilter var2, double var3, double var5, double var7, float var9, double var10) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var5;
         this.field5 = var7;
         this.field6 = var9;
         this.field7 = var10;
      }

      public EmoteDefinition method1() {
         return this.field1;
      }

      public PathFilter method2() {
         return this.field2;
      }

      public double method3() {
         return this.field3;
      }

      public double method4() {
         return this.field4;
      }

      public double method5() {
         return this.field5;
      }

      public float method6() {
         return this.field6;
      }

      public double method7() {
         return this.field7;
      }
   }
}
