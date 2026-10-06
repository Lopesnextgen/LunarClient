package com.moonsworth.lunar.client.network.apollo;

import com.google.protobuf.Message;
import com.lunarclient.apollo.common.location.ApolloBlockLocation;
import com.lunarclient.apollo.cosmetic.v1.BodyOptions;
import com.lunarclient.apollo.cosmetic.v1.Cosmetic;
import com.lunarclient.apollo.cosmetic.v1.DisplaySprayMessage;
import com.lunarclient.apollo.cosmetic.v1.Emote;
import com.lunarclient.apollo.cosmetic.v1.EquipNpcCosmeticsMessage;
import com.lunarclient.apollo.cosmetic.v1.HatOptions;
import com.lunarclient.apollo.cosmetic.v1.RemoveSprayMessage;
import com.lunarclient.apollo.cosmetic.v1.ResetNpcCosmeticsMessage;
import com.lunarclient.apollo.cosmetic.v1.ResetNpcEmotesMessage;
import com.lunarclient.apollo.cosmetic.v1.ResetSpraysMessage;
import com.lunarclient.apollo.cosmetic.v1.StartNpcEmoteMessage;
import com.lunarclient.apollo.cosmetic.v1.StopNpcEmoteMessage;
import com.lunarclient.apollo.cosmetic.v1.UnequipNpcCosmeticsMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType$Type2;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.client.feature.Module2;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteManager;
import com.moonsworth.lunar.client.cosmetics.SprayManager;
import com.moonsworth.lunar.client.cosmetics.SprayPlacementTracker;
import com.moonsworth.lunar.client.fov.FovHandler;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import com.moonsworth.lunar.client.cosmetics.CosmeticRenderFilter;

public class CosmeticApolloHandler extends ApolloModuleHandler {
   private static final UUID field4 = new UUID(0L, 0L);
   private final Set<UUID> field5 = new HashSet<>();
   private final Set<UUID> field6 = new HashSet<>();

   public CosmeticApolloHandler() {
      super("cosmetic", "Cosmetic");
   }

   @Override
   protected void onDisable() {
      for (UUID var2 : this.field5) {
         ThreadModuleDump63.method4().method55().removePlayer(var2);
         ThreadModuleDump63.method4().method88().method11(var2);
      }

      this.field5.clear();
      this.method10();
      this.method14();
   }

   @Override
   public Set<Class<? extends Message>> method2() {
      return Set.of(
         EquipNpcCosmeticsMessage.class,
         UnequipNpcCosmeticsMessage.class,
         ResetNpcCosmeticsMessage.class,
         DisplaySprayMessage.class,
         RemoveSprayMessage.class,
         ResetSpraysMessage.class,
         StartNpcEmoteMessage.class,
         StopNpcEmoteMessage.class,
         ResetNpcEmotesMessage.class
      );
   }

   @Override
   public void method3(HighlightImpl_3 var1) {
      var1.unpack(EquipNpcCosmeticsMessage.class).ifPresent(var1x -> {
         UUID var2 = NetworkTypes.fromProtobuf(var1x.getNpcUuid());
         ArrayList var3 = new ArrayList();
         HashMap var4 = new HashMap();
         if (var1x.getCopyLocalCosmetics() && CosmeticRenderFilter.method5()) {
            for (CosmeticMetadata var6 : ThreadModuleDump63.method4().method53().method19(ThreadModuleDump63.method7().bridge$getUniqueID())) {
               int var7 = (int)var6.method4().method9();
               var3.add(var7);
               var4.put(var7, var6.method6());
            }
         }

         for (Cosmetic var9 : var1x.getCosmeticsList()) {
            var3.add(var9.getId());
            Module2 var10 = this.method7(var9);
            if (var10 != null) {
               var4.put(var9.getId(), var10);
            }
         }

         this.method3(var2, var3, var4);
      });
      var1.unpack(UnequipNpcCosmeticsMessage.class).ifPresent(var1x -> {
         UUID var2 = NetworkTypes.fromProtobuf(var1x.getNpcUuid());
         this.method4(var2, var1x.getCosmeticIdsList());
      });
      var1.unpack(ResetNpcCosmeticsMessage.class).ifPresent(var1x -> {
         UUID var2 = NetworkTypes.fromProtobuf(var1x.getNpcUuid());
         this.method5(var2);
      });
      var1.unpack(DisplaySprayMessage.class).ifPresent(var1x -> {
         ApolloBlockLocation var2 = NetworkTypes.fromProtobuf(var1x.getLocation());
         HorsestatsType_2 var3 = HorsestatsType_2.byId(var1x.getFacingValue() - 1);
         int var4 = (int)(NetworkTypes.fromProtobuf(var1x.getDuration()).toMillis() / 50L);
         this.method8(var1x.getSprayId(), var2, var3, var1x.getRotation(), var4);
      });
      var1.unpack(RemoveSprayMessage.class)
         .ifPresent(var1x -> this.method9(var1x.getSprayId(), var1x.hasLocation() ? NetworkTypes.fromProtobuf(var1x.getLocation()) : null));
      var1.unpack(ResetSpraysMessage.class).ifPresent(var1x -> this.method10());
      var1.unpack(StartNpcEmoteMessage.class).ifPresent(var1x -> {
         UUID var2 = NetworkTypes.fromProtobuf(var1x.getNpcUuid());
         Emote var3 = var1x.getEmote();
         this.method12(var2, var3.getId(), var3.getMetadata());
      });
      var1.unpack(StopNpcEmoteMessage.class).ifPresent(var1x -> {
         UUID var2 = NetworkTypes.fromProtobuf(var1x.getNpcUuid());
         this.method13(var2);
      });
      var1.unpack(ResetNpcEmotesMessage.class).ifPresent(var1x -> this.method14());
   }

   public void method3(UUID var1, List<Integer> var2, @Nullable Map<Integer, Module2> var3) {
      if (this.method6(var1)) {
         List var4 = CosmeticRenderFilter.method6(var2);
         if (var4 != null) {
            ThreadModuleDump63.method4().method53().method55(var1, var4, var3);
            this.field5.add(var1);
         }
      }
   }

   public void method4(UUID var1, List<Integer> var2) {
      if (this.method6(var1)) {
         ThreadModuleDump63.method4().method53().method56(var1, var2);
      }
   }

   public void method5(UUID var1) {
      if (this.method6(var1)) {
         ThreadModuleDump63.method4().method55().removePlayer(var1);
         ThreadModuleDump63.method4().method88().method11(var1);
         this.field5.remove(var1);
      }
   }

   private boolean method6(UUID var1) {
      return var1.getLeastSignificantBits() == 0L;
   }

   @Nullable
   private Module2 method7(Cosmetic var1) {
      Module2 var2 = new Module2();
      switch (var1.getOptionsCase()) {
         case HAT_OPTIONS:
            HatOptions var4 = var1.getHatOptions();
            var2.method8(var4.getShowOverHelmet());
            var2.method12(var4.getShowOverSkinLayer());
            var2.method14(var4.getHeightOffset());
            break;
         case CLOAK_OPTIONS:
            var2.method7(var1.getCloakOptions().getUseClothPhysics());
            break;
         case PET_OPTIONS:
            var2.method13(var1.getPetOptions().getFlipShoulder());
            break;
         case BODY_OPTIONS:
            BodyOptions var3 = var1.getBodyOptions();
            var2.setShowOverChestplate(var3.getShowOverChestplate());
            var2.setShowOverLeggings(var3.getShowOverLeggings());
            var2.setShowOverBoots(var3.getShowOverBoots());
            break;
         case OPTIONS_NOT_SET:
            return null;
      }

      return var2;
   }

   public void method8(int var1, ApolloBlockLocation var2, HorsestatsType_2 var3, float var4, int var5) {
      if (CosmeticRenderFilter.method7(var1)) {
         SprayManager var6 = ThreadModuleDump63.method4().method46();
         com.moonsworth.lunar.client.cosmetics.SprayEntry var7 = var6.method5(var1);
         if (var7 != null) {
            Vec3Bridge var8 = Vec3Bridge.method2(var2.getX(), var2.getY(), var2.getZ());
            Vec3Bridge var9 = this.method11(var8, var3);
            Vector3f var10 = SprayManager.method26(true, var9, var3);
            SprayPlacementTracker var11 = var6.getProvider(var7, var10, var3, var4, false);
            if (var11 != null) {
               var11.method7(EventClientTick.field1 + Math.max(var5, 1));
               var6.method2(field4, var11, 100, false);
            }
         }
      }
   }

   public void method9(int var1, @Nullable ApolloBlockLocation var2) {
      LinkedList var3 = ThreadModuleDump63.method4().method46().method43().get(field4);
      if (var3 != null) {
         Vec3Bridge var4 = var2 != null ? Vec3Bridge.method2(var2.getX(), var2.getY(), var2.getZ()) : null;
         var3.removeIf(var2x -> {
            if (var2x.method1().getId() != var1) {
               return false;
            }

            if (var4 == null) {
               return true;
            }

            Vector3f var3x = SprayManager.method26(true, var4, var2x.method3());
            return var2x.method2().equals(var3x);
         });
      }
   }

   public void method10() {
      ThreadModuleDump63.method4().method46().method43().remove(field4);
   }

   private Vec3Bridge method11(Vec3Bridge var1, HorsestatsType_2 var2) {
      return var2.getDirection() == HorsestatsType$Type2.POSITIVE
         ? Vec3Bridge.method2(var1.bridge$xCoord() + var2.getOffsetX(), var1.bridge$yCoord() + var2.getOffsetY(), var1.bridge$zCoord() + var2.getOffsetZ())
         : var1;
   }

   public void method12(UUID var1, int var2, int var3) {
      if (this.method6(var1)) {
         if (CosmeticRenderFilter.method8(var2)) {
            Bridge6_10 var4 = ThreadModuleDump63.method8().bridge$getPlayerByUniqueId(var1).orElse(null);
            if (var4 != null) {
               EmoteManager var5 = ThreadModuleDump63.method4().method45();
               FovHandler var6 = var5.method13(var2);
               if (var6 != null) {
                  var5.method7(var4, var6, var3, null, 0);
                  this.field6.add(var1);
               }
            }
         }
      }
   }

   public void method13(UUID var1) {
      if (this.method6(var1)) {
         Bridge6_10 var2 = ThreadModuleDump63.method8().bridge$getPlayerByUniqueId(var1).orElse(null);
         if (var2 != null) {
            ThreadModuleDump63.method4().method45().method11(var2, false, false);
         }

         this.field6.remove(var1);
      }
   }

   public void method14() {
      for (UUID var2 : this.field6) {
         Bridge6_10 var3 = ThreadModuleDump63.method8().bridge$getPlayerByUniqueId(var2).orElse(null);
         if (var3 != null) {
            ThreadModuleDump63.method4().method45().method11(var3, false, false);
         }
      }

      this.field6.clear();
   }

   @Generated
   public Set<UUID> method15() {
      return this.field5;
   }

   @Generated
   public Set<UUID> method16() {
      return this.field6;
   }
}
