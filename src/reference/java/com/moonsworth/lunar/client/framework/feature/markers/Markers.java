package com.moonsworth.lunar.client.framework.feature.markers;

import com.lunarclient.common.v1.Vector3f;
import com.lunarclient.websocket.marker.v1.BroadcastNewMarkerRequest;
import com.lunarclient.websocket.marker.v1.Marker;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.ItemEntityBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.MissResult;
import com.moonsworth.lunar.bridge.horsestats.EntityHitResult;
import com.moonsworth.lunar.client.mod.render.markers.ApolloMarkerData;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.keystrokes.Highlight3Iterator;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.util.ThreadModuleDump22;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import com.moonsworth.lunar.client.util.chest.SImpl;
import com.moonsworth.lunar.client.util.chest.mixin.ChestHandler3;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.Generated;
import org.joml.Vector3d;

public class MarkerModel {
   public static final double field1 = Math.sqrt(3.0);
   private static final int field2 = 12;
   private final com.moonsworth.lunar.client.mod.render.markers.Markers field3;
   private final List<Markers2> field4 = new ArrayList<>();

   protected boolean method1(Markers2.Type var1) {
      Bridge5Extension_5 var2 = ThreadModuleDump63.method7();
      if (var2 == null) {
         return false;
      }

      double var3 = ThreadModuleDump63.method3().bridge$getGameSettings().bridge$getRenderDistance() * 16.0 * field1;
      var3 = Math.floor(var3 / 16.0) * 16.0;
      EntityHitResult var5 = (EntityHitResult)com.moonsworth.lunar.client.util.chest.SExtension.method9(SImpl.ENTITY)
         .method8(var2, var3, ThreadModuleDump63.method3().bridge$getTimer().method1())
         .method15(
            var1x -> {
               if (var1x == ThreadModuleDump63.method3().bridge$getRenderViewEntity()
                  || !var1x.bridge$isAlive()
                  || var1x.bridge$isInvisible()
                  || var1x.bridge$isInvisibleTo(var2)) {
                  return false;
               } else {
                  return var1x instanceof Bridge6_10 var2x
                     ? !ThreadModuleDump22.method2(var2x, Highlight3Iterator.method8(KeystrokesType.HYPIXEL))
                     : var1x instanceof ItemEntityBridge || var1x.bridge$canBeCollidedWith();
               }
            }
         )
         .method1(new ChestHandler3(true, 0.2F, ThreadModuleDump63.method3().bridge$getTimer().method1()))
         .method18()
         .method8(var2.bridge$getWorld());
      MissResult var6 = (MissResult)com.moonsworth.lunar.client.util.chest.SExtension.method9(SImpl.BLOCK_AT)
         .method8(var2, var3, ThreadModuleDump63.method3().bridge$getTimer().method1())
         .method14(
            (var0, var1x) -> {
               if (!var1x.bridge$isAir() && !var1x.bridge$isWater() && !var1x.bridge$isFoliage() && !var1x.bridge$isFlower()) {
                  String var2x = var1x.bridge$getRegistryName();
                  if ("minecraft:barrier".equals(var2x)
                     || "minecraft:fire".equals(var2x)
                     || "minecraft:soul_fire".equals(var2x)
                     || "minecraft:end_portal".equals(var2x)
                     || "minecraft:portal".equals(var2x)
                     || "minecraft:nether_portal".equals(var2x)) {
                     return false;
                  } else {
                     return ThreadModuleDump63.MC_VERSION <= 5
                        ? !"minecraft:snow_layer".equals(var2x)
                        : !"minecraft:snow".equals(var2x)
                           && !"minecraft:structure_void".equals(var2x)
                           && !"minecraft:structure_block".equals(var2x)
                           && !"minecraft:light".equals(var2x)
                           && !"minecraft:jigsaw".equals(var2x);
                  }
               } else {
                  return false;
               }
            }
         )
         .method18()
         .method8(var2.bridge$getWorld());
      Vector3d var7 = var5.isSuccessful() ? var5.method5().bridge$getEyePosition().method6() : null;
      Vector3d var8 = var6.isSuccessful() ? var6.method5().method6() : null;
      if (var7 != null && var8 != null) {
         Vector3d var9 = var2.bridge$getEyePosition(ThreadModuleDump63.method3().bridge$getTimer().method1()).method6();
         double var10 = var9.distance(var7);
         double var12 = var9.distance(var8);
         if (var10 <= var12) {
            var8 = null;
         } else {
            var7 = null;
         }
      }

      if (var7 == null && var8 == null) {
         return false;
      }

      Vector3d var18 = null;
      Markers2.Type2 var19 = null;
      Optional var11 = Optional.empty();
      if (var7 != null) {
         var18 = var7;
         BridgeExtension var20 = var5.method5();
         if (var20.bridge$isMinecart() || var20.bridge$isBoat()) {
            var18 = new Vector3d(var20.bridge$getPosX(), var20.bridge$getPosY(), var20.bridge$getPosZ());
            var19 = Markers2.Type2.ITEM;
            var11 = SIterator.method6().ICOOHRIORIOOIIRRIHHOOIOHHCORIR(var20.bridge$getPickResult());
         } else if (var20 instanceof ItemEntityBridge var13) {
            var19 = Markers2.Type2.ITEM;
            var11 = SIterator.method6().ICOOHRIORIOOIIRRIHHOOIOHHCORIR(var13.bridge$getItemStack());
         } else if (var20 instanceof Bridge6_10 var14) {
            var19 = Markers2.Type2.PLAYER;
            var11 = Optional.of(
               Markers2.Data2.method1(
                  var14.bridge$getUniqueID(), var2.bridge$canSeeName(var14) ? var14.bridge$getDisplayNameComponent() : null, var14.bridge$showHat()
               )
            );
         } else {
            var19 = Markers2.Type2.ENTITY;
            var11 = SIterator.method5().ICOOHRIORIOOIIRRIHHOOIOHHCORIR(var20);
         }
      }

      if (var8 != null) {
         Vector3iBridge var21 = var6.method6();
         if (var8.x + var8.y + var8.z == 0.0) {
            var18 = new Vector3d(var21.bridge$toJoml());
         } else {
            var18 = var8;
         }

         var19 = Markers2.Type2.BLOCK;
         Bridge3_23 var23 = var2.bridge$getWorld().method4(var21);
         if (var23 != null) {
            ItemStackBridge var25 = var23.bridge$getStack(var21);
            if (var25 != null && var25.bridge$getItem() != null && !var25.bridge$isEmpty()) {
               var11 = SIterator.method6().ICOOHRIORIOOIIRRIHHOOIOHHCORIR(var25);
            } else {
               String var15 = var23.bridge$getRegistryName();
               if ("minecraft:lava".equals(var15) || "minecraft:flowing_lava".equals(var15)) {
                  var15 = "minecraft:lava_bucket";
               }

               Optional var16 = SIterator_2.method8(var15);
               if (var16.isPresent()) {
                  var11 = SIterator.method6().ICOOHRIORIOOIIRRIHHOOIOHHCORIR((ItemStackBridge)var16.get());
               }
            }
         }
      }

      if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
         Slayer.method3("[MarkerModel] typeKey = %s", new Object[]{var11});
      }

      Markers2.Data var22 = new Markers2.Data(var19, (Markers3_2)var11.orElse(null), var1);
      Markers2 var24 = new Markers2(var2.bridge$getUniqueID(), var2.bridge$getName(), ThreadModuleDump63.MC_VERSION, var18, var22);
      this.field4.add(var24);
      this.field3.addMarker(var24);
      this.method3(var24);
      this.method5(var24);
      return true;
   }

   protected void method2() {
      long var1 = ThreadModuleDump63.method3().bridge$getSystemTime();
      long var3 = TimeUnit.SECONDS.toMillis(((Integer)this.field3.getVisibleDuration().get()).intValue());
      Iterator var5 = this.field4.iterator();

      while (var5.hasNext()) {
         Markers2 var6 = (Markers2)var5.next();
         ApolloMarkerData var7 = var6.method27();
         long var8 = var7 != null && var7.method2() != null ? var7.method2() : var3;
         long var10 = var6.method28() ? var6.method29() : var6.method23();
         if (var1 - var10 >= var8) {
            var5.remove();
         } else {
            var6.method30(var1 - var6.method23());
         }
      }
   }

   private void method3(Markers2 var1) {
      Vector3d var2 = var1.getPos();
      double var3 = var2.x;
      double var5 = var2.y;
      double var7 = var2.z;
      Iterator var9 = this.field4.iterator();

      while (var9.hasNext()) {
         Markers2 var10 = (Markers2)var9.next();
         if (var10 != var1 && !var10.method28()) {
            if (var10.getOwnerId().equals(var1.getOwnerId())) {
               var2 = var10.getPos();
               double var11 = var2.x - var3;
               double var13 = var2.y - var5;
               double var15 = var2.z - var7;
               if (var11 * var11 + var13 * var13 + var15 * var15 <= 1.8225000000000002) {
                  var9.remove();
                  continue;
               }
            }

            if (this.method4() > 12L) {
               boolean var19 = var10.field8 != null && !var10.field8.method20();
               boolean var12 = var10.method26() >= TimeUnit.SECONDS.toMillis(((Integer)this.field3.getVisibleDuration().get()).intValue());
               if (var19 || var12) {
                  var9.remove();
               }
            }
         }
      }

      while (this.method4() > 12L) {
         int var18 = -1;

         for (int var20 = 0; var20 < this.field4.size(); var20++) {
            if (!this.field4.get(var20).method28()) {
               var18 = var20;
               break;
            }
         }

         if (var18 == -1) {
            break;
         }

         this.field4.remove(var18);
      }
   }

   private long method4() {
      return this.field4.stream().filter(var0 -> !var0.method28()).count();
   }

   private void method5(Markers2 var1) {
      List var2 = this.field3.getMarkerSettings().method2().stream().map(ThreadModuleDump66::method3).toList();
      if (var2.isEmpty()) {
         if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
            Slayer.method3("[MarkerModel] Not broadcasting marker because no receivers", new Object[0]);
         }
      } else {
         Marker var3 = var1.method20(this.field3.getMarkerSettings().method4());
         if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
            String var4 = this.field3.getMarkerSettings().method2().stream().map(UUID::toString).collect(Collectors.joining(","));
            Slayer.method3("[MarkerModel] Broadcasting marker to [%s]: %s", new Object[]{var4, var3});
         }

         ThreadModuleDump63.method5()
            .ifPresent(
               var2x -> var2x.method108()
                  .broadcastNewMarker(null, BroadcastNewMarkerRequest.newBuilder().addAllReceiverUuids(var2).setMarker(var3).build(), var0x -> {})
            );
      }
   }

   public void method6(UUID var1, Marker var2) {
      if (this.field3.isEnabled()) {
         if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
            Slayer.method3("[MarkerModel] Received external marker from %s", new Object[]{var1});
         }

         Bridge5Extension_5 var3 = ThreadModuleDump63.method7();
         if (var3 != null && ThreadModuleDump63.method8() != null) {
            Vector3f var4 = var2.getPosition();
            float var5 = var4.getX() - var3.bridge$getBlockX();
            float var6 = var4.getY() - var3.bridge$getBlockY();
            float var7 = var4.getZ() - var3.bridge$getBlockZ();
            float var8 = var5 * var5 + var6 * var6 + var7 * var7;
            if (var8 > 1048576.0F) {
               if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
                  Slayer.method3("[MarkerModel] Discarding marker from %s because too far away %.2f", new Object[]{var1, Math.sqrt(var8)});
               }
            } else {
               com.moonsworth.lunar.client.framework.feature.markers.mixin.Markers var9 = com.moonsworth.lunar.client.framework.feature.markers.mixin.Markers.method2(
                  var2.getSource()
               );
               if (this.field3.getMarkerSettings().method3(var9, var1)) {
                  if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
                     Slayer.method3("[MarkerModel] Adding external marker %s", new Object[]{var2});
                  }

                  String var10 = ThreadModuleDump63.method3().bridge$getClientPacketListener().bridge$getPlayerInfo(var1).bridge$getGameProfile().getName();
                  Markers2 var11 = Markers2.method21(var1, var10, var9.method4(), var2);
                  this.field4.add(var11);
                  this.field3.addMarker(var11);
                  this.method3(var11);
               }
            }
         }
      }
   }

   public void method7(Markers2 var1, ApolloMarkerData var2) {
      var1.method31(var2);

      for (Markers2 var4 : this.field4) {
         if (var4.method28() && var4.method27().getId().equals(var2.getId())) {
            var4.method7(var1, var2);
            return;
         }
      }

      this.field4.add(var1);
      this.field3.addMarker(var1);
   }

   public void method8(String var1) {
      this.field4.removeIf(var1x -> var1x.method28() && var1x.method27().getId().equals(var1));
   }

   public void method9() {
      this.field4.removeIf(Markers2::method28);
   }

   @Generated
   public MarkerModel(com.moonsworth.lunar.client.mod.render.markers.Markers var1) {
      this.field3 = var1;
   }

   @Generated
   public List<Markers2> method10() {
      return this.field4;
   }
}
