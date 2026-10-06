package com.moonsworth.lunar.client.cosmetics;

import com.eliotlash.molang.ast.Evaluator;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_7;
import com.moonsworth.lunar.bridge.MExtension;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.cosmetics.CosmeticCategoryType;
import com.moonsworth.lunar.client.cosmetics.gecko.InactiveType;
import com.moonsworth.lunar.client.cosmetics.gecko.MolangFunctionRegistry;
import com.moonsworth.lunar.client.inactive.mixin.Gui2Handler;
import com.moonsworth.lunar.client.inactive.mixin.Gui2Impl2;
import com.moonsworth.lunar.client.driver.core.DriverViewLegacy;
import com.moonsworth.lunar.client.mod.combat.shields.Shields;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Generated;
import com.moonsworth.lunar.client.cosmetics.emote.RenderContext;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteModel;
import com.moonsworth.lunar.client.cosmetics.emote.RenderEntityHandle;

public class CosmeticLayerRenderer implements MExtension<Bridge5_11, EntityPlayerBridge> {
   protected final List<RenderEntityHandle> field1 = new ArrayList<>();
   private CosmeticManager field2;
   protected Evaluator field3;

   public CosmeticLayerRenderer(CosmeticManager var1) {
      this.field2 = var1;
   }

   public int method1(EntityPlayerBridge var1) {
      this.field1.clear();
      if (!this.shouldRender(var1)) {
         return 0;
      }

      MolangFunctionRegistry var2 = ThreadModuleDump63.method4().method76();
      List var3 = var1.bridge$getWornCosmetics();
      boolean var4 = false;
      boolean var5 = false;
      if (var3 != null) {
         for (CosmeticMetadata var7 : var3) {
            if (this.method7(var1, var7.method4()) && var7.method4() instanceof EmoteModel var8) {
               if (var8.method10().isHeldItemCosmetic() && var2.method14(var8)) {
                  Gui2Handler var11 = var8.method6().get();
                  if (var8.method8(var1.bridge$getMainHandItemRenderState(), var1) && !var4) {
                     this.field1.add(new RenderEntityHandle(var7, false));
                     var4 = true;
                  }

                  if (var11 instanceof Gui2Impl2 var10
                     && var10.method24() != InactiveType.NONE
                     && ThreadModuleDump63.MC_VERSION >= 5
                     && var8.method8(var1.bridge$getOffHandItemRenderState(), var1)
                     && !var5) {
                     this.field1.add(new RenderEntityHandle(var7, true));
                     var5 = true;
                  }
               } else {
                  this.field1.add(new RenderEntityHandle(var7, false));
               }
            }
         }
      }

      return this.field1.size();
   }

   public Optional<ResourceLocationBridge> method2(EntityPlayerBridge var1, int var2) {
      return this.field3 == null
         ? Optional.empty()
         : this.method3(var1, var2).map(var0 -> (EmoteModel)var0.method4()).flatMap(var1x -> var1x.method5().map(var2x -> var2x.method2(var1x, this.field3)));
   }

   public Optional<CosmeticMetadata> method3(EntityPlayerBridge var1, int var2) {
      return Optional.of(this.field1.get(var2).method1());
   }

   public static Color method4(EmoteModel var0, EntityPlayerBridge var1) {
      if (var0.method10() == CosmeticCategoryType.SHIELDS) {
         Shields var2 = ThreadModuleDump63.method4().method40().method91();
         if (var2.isEnabled()) {
            int var3 = var2.method11(var1.bridge$getUniqueID());
            if (var3 != -1) {
               return new Color(var3 >> 16 & 0xFF, var3 >> 8 & 0xFF, var3 & 0xFF, var3 >> 24 & 0xFF);
            }
         }
      }

      return new Color(255, 255, 255, 255);
   }

   public Optional<RenderLayerBridge> method6(ResourceLocationBridge var1) {
      return Optional.of(LunarRenderTypes.field8.get(var1));
   }

   public boolean method9(EntityPlayerBridge var1) {
      return ThreadModuleDump63.method4().method41().method6().method6(var1);
   }

   protected boolean shouldRender(EntityPlayerBridge var1) {
      return !var1.bridge$isSpectator();
   }

   protected boolean method7(EntityPlayerBridge var1, com.moonsworth.lunar.client.cosmetics.OwnedCosmetic var2) {
      return !var1.bridge$isInvisible() && !var1.bridge$isInvisibleToPlayer() ? true : var2.method10().isHeldItemCosmetic() && var2.method10() != CosmeticCategoryType.HAND;
   }

   protected RenderContext method8(EntityPlayerBridge var1, BridgeExtension2_7 var2) {
      if (var1.method2()) {
         RenderContext var4 = DriverViewLegacy.method21().method14().method6(var1.bridge$getUniqueID(), var2);
         return var4 == null ? RenderContext.method10() : var4;
      }

      if (ThreadModuleDump63.MC_VERSION < 26) {
         return RenderContext.method7((Bridge5_11)var1, var2);
      }

      if (ThreadModuleDump63.method8() == null) {
         return RenderContext.method8(null, var2);
      }

      Bridge6_10 var3 = (Bridge6_10)ThreadModuleDump63.method8().bridge$getPlayerByUniqueId(var1.bridge$getUniqueID()).orElse(null);
      return RenderContext.method7((Bridge5_11)var3, var2);
   }

   @Generated
   public CosmeticManager method9() {
      return this.field2;
   }
}
