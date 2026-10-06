package com.moonsworth.lunar.client.framework.feature.markers;

import com.lunarclient.common.v1.Vector3f;
import com.lunarclient.websocket.marker.v1.BroadcastNewMarkerRequest;
import com.lunarclient.websocket.marker.v1.Marker;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.horsestats.MissResult;
import com.moonsworth.lunar.bridge.horsestats.EntityHitResult;
import com.moonsworth.lunar.client.mod.render.markers.ApolloMarkerData;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.network.server.ServerBrandWatcher;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.util.game.NpcUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.network.apollo.ProtoConverter;
import com.moonsworth.lunar.client.util.raytrace.Raycaster;
import com.moonsworth.lunar.client.util.raytrace.EntityRaycastContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.Generated;
import org.joml.Vector3d;

public class MarkerManager {
   public static final double field1 = Math.sqrt(3.0);
   private static final int field2 = 12;
   private final com.moonsworth.lunar.client.mod.render.markers.Markers field3;
   private final List<Markers2> field4 = new ArrayList<>();

   protected boolean method1(Markers2.Type type1) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 == null) {
         return false;
      }

      double value3 = Ref.method3().bridge$getGameSettings().bridge$getRenderDistance() * 16.0 * field1;
      value3 = Math.floor(value3 / 16.0) * 16.0;
      EntityHitResult horsestatshandler65 = (EntityHitResult)com.moonsworth.lunar.client.util.raytrace.Ray.method9(Raycaster.field9)
         .method8(bridge5extension_52, value3, Ref.method3().bridge$getTimer().method1())
         .method15(
            arg1x -> {
               if (arg1x == Ref.method3().bridge$getRenderViewEntity()
                  || !arg1x.bridge$isAlive()
                  || arg1x.bridge$isInvisible()
                  || arg1x.bridge$isInvisibleTo(bridge5extension_52)) {
                  return false;
               } else {
                  return arg1x instanceof Bridge6_10 bridge6_102x
                     ? !NpcUtils.method2(bridge6_102x, ServerBrandWatcher.method8(KeystrokesType.HYPIXEL))
                     : arg1x instanceof EntityItemBridge || arg1x.bridge$canBeCollidedWith();
               }
            }
         )
         .method1(new EntityRaycastContext(true, 0.2F, Ref.method3().bridge$getTimer().method1()))
         .method18()
         .method8(bridge5extension_52.bridge$getWorld());
      MissResult horsestatshandler6 = (MissResult)com.moonsworth.lunar.client.util.raytrace.Ray.method9(Raycaster.field3)
         .method8(bridge5extension_52, value3, Ref.method3().bridge$getTimer().method1())
         .method14(
            (arg0, arg1x) -> {
               if (!arg1x.bridge$isAir() && !arg1x.bridge$isWater() && !arg1x.bridge$isFoliage() && !arg1x.bridge$isFlower()) {
                  String text2x = arg1x.bridge$getRegistryName();
                  if ("minecraft:barrier".equals(text2x)
                     || "minecraft:fire".equals(text2x)
                     || "minecraft:soul_fire".equals(text2x)
                     || "minecraft:end_portal".equals(text2x)
                     || "minecraft:portal".equals(text2x)
                     || "minecraft:nether_portal".equals(text2x)) {
                     return false;
                  } else {
                     return Ref.MC_VERSION <= 5
                        ? !"minecraft:snow_layer".equals(text2x)
                        : !"minecraft:snow".equals(text2x)
                           && !"minecraft:structure_void".equals(text2x)
                           && !"minecraft:structure_block".equals(text2x)
                           && !"minecraft:light".equals(text2x)
                           && !"minecraft:jigsaw".equals(text2x);
                  }
               } else {
                  return false;
               }
            }
         )
         .method18()
         .method8(bridge5extension_52.bridge$getWorld());
      Vector3d vector3d7 = horsestatshandler65.isSuccessful() ? horsestatshandler65.method5().bridge$getEyePosition().method6() : null;
      Vector3d vector3d8 = horsestatshandler6.isSuccessful() ? horsestatshandler6.method5().method6() : null;
      if (vector3d7 != null && vector3d8 != null) {
         Vector3d vector3d9 = bridge5extension_52.bridge$getEyePosition(Ref.method3().bridge$getTimer().method1()).method6();
         double value10 = vector3d9.distance(vector3d7);
         double value12 = vector3d9.distance(vector3d8);
         if (value10 <= value12) {
            vector3d8 = null;
         } else {
            vector3d7 = null;
         }
      }

      if (vector3d7 == null && vector3d8 == null) {
         return false;
      }

      Vector3d vector3d18 = null;
      Markers2.Type2 type219 = null;
      Optional optional11 = Optional.empty();
      if (vector3d7 != null) {
         vector3d18 = vector3d7;
         BridgeExtension bridgeextension20 = horsestatshandler65.method5();
         if (bridgeextension20.bridge$isMinecart() || bridgeextension20.bridge$isBoat()) {
            vector3d18 = new Vector3d(bridgeextension20.bridge$getPosX(), bridgeextension20.bridge$getPosY(), bridgeextension20.bridge$getPosZ());
            type219 = Markers2.Type2.ITEM;
            optional11 = IconRegistry.method6().ICOOHRIORIOOIIRRIHHOOIOHHCORIR(bridgeextension20.bridge$getPickResult());
         } else if (bridgeextension20 instanceof EntityItemBridge bridgeextension5213) {
            type219 = Markers2.Type2.ITEM;
            optional11 = IconRegistry.method6().ICOOHRIORIOOIIRRIHHOOIOHHCORIR(bridgeextension5213.bridge$getItemStack());
         } else if (bridgeextension20 instanceof Bridge6_10 bridge6_1014) {
            type219 = Markers2.Type2.PLAYER;
            optional11 = Optional.of(
               Markers2.Data2.method1(
                  bridge6_1014.bridge$getUniqueID(), bridge5extension_52.bridge$canSeeName(bridge6_1014) ? bridge6_1014.bridge$getDisplayNameComponent() : null, bridge6_1014.bridge$showHat()
               )
            );
         } else {
            type219 = Markers2.Type2.ENTITY;
            optional11 = IconRegistry.method5().ICOOHRIORIOOIIRRIHHOOIOHHCORIR(bridgeextension20);
         }
      }

      if (vector3d8 != null) {
         Vec3iBridge horsestats2021 = horsestatshandler6.method6();
         if (vector3d8.x + vector3d8.y + vector3d8.z == 0.0) {
            vector3d18 = new Vector3d(horsestats2021.bridge$toJoml());
         } else {
            vector3d18 = vector3d8;
         }

         type219 = Markers2.Type2.BLOCK;
         Bridge3_23 bridge3_2323 = bridge5extension_52.bridge$getWorld().method4(horsestats2021);
         if (bridge3_2323 != null) {
            ItemStackBridge bridgeextension_425 = bridge3_2323.bridge$getStack(horsestats2021);
            if (bridgeextension_425 != null && bridgeextension_425.bridge$getItem() != null && !bridgeextension_425.bridge$isEmpty()) {
               optional11 = IconRegistry.method6().ICOOHRIORIOOIIRRIHHOOIOHHCORIR(bridgeextension_425);
            } else {
               String text15 = bridge3_2323.bridge$getRegistryName();
               if ("minecraft:lava".equals(text15) || "minecraft:flowing_lava".equals(text15)) {
                  text15 = "minecraft:lava_bucket";
               }

               Optional optional16 = ItemIconRegistry.method8(text15);
               if (optional16.isPresent()) {
                  optional11 = IconRegistry.method6().ICOOHRIORIOOIIRRIHHOOIOHHCORIR((ItemStackBridge)optional16.get());
               }
            }
         }
      }

      if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
         LunarLogger.method3("[MarkerManager] typeKey = %s", new Object[]{optional11});
      }

      Markers2.Data data22 = new Markers2.Data(type219, (Markers3_2)optional11.orElse(null), type1);
      Markers2 markers224 = new Markers2(bridge5extension_52.bridge$getUniqueID(), bridge5extension_52.bridge$getName(), Ref.MC_VERSION, vector3d18, data22);
      this.field4.add(markers224);
      this.field3.addMarker(markers224);
      this.method3(markers224);
      this.method5(markers224);
      return true;
   }

   protected void method2() {
      long number1 = Ref.method3().bridge$getSystemTime();
      long number3 = TimeUnit.SECONDS.toMillis(((Integer)this.field3.getVisibleDuration().get()).intValue());
      Iterator iterator5 = this.field4.iterator();

      while (iterator5.hasNext()) {
         Markers2 markers26 = (Markers2)iterator5.next();
         ApolloMarkerData mixinhelper_27 = markers26.method27();
         long number8 = mixinhelper_27 != null && mixinhelper_27.method2() != null ? mixinhelper_27.method2() : number3;
         long number10 = markers26.method28() ? markers26.method29() : markers26.method23();
         if (number1 - number10 >= number8) {
            iterator5.remove();
         } else {
            markers26.method30(number1 - markers26.method23());
         }
      }
   }

   private void method3(Markers2 markers21) {
      Vector3d vector3d2 = markers21.getPos();
      double value3 = vector3d2.x;
      double value5 = vector3d2.y;
      double value7 = vector3d2.z;
      Iterator iterator9 = this.field4.iterator();

      while (iterator9.hasNext()) {
         Markers2 markers210 = (Markers2)iterator9.next();
         if (markers210 != markers21 && !markers210.method28()) {
            if (markers210.getOwnerId().equals(markers21.getOwnerId())) {
               vector3d2 = markers210.getPos();
               double value11 = vector3d2.x - value3;
               double value13 = vector3d2.y - value5;
               double value15 = vector3d2.z - value7;
               if (value11 * value11 + value13 * value13 + value15 * value15 <= 1.8225000000000002) {
                  iterator9.remove();
                  continue;
               }
            }

            if (this.method4() > 12L) {
               boolean flag19 = markers210.field8 != null && !markers210.field8.method20();
               boolean flag12 = markers210.method26() >= TimeUnit.SECONDS.toMillis(((Integer)this.field3.getVisibleDuration().get()).intValue());
               if (flag19 || flag12) {
                  iterator9.remove();
               }
            }
         }
      }

      while (this.method4() > 12L) {
         int index18 = -1;

         for (int index20 = 0; index20 < this.field4.size(); index20++) {
            if (!this.field4.get(index20).method28()) {
               index18 = index20;
               break;
            }
         }

         if (index18 == -1) {
            break;
         }

         this.field4.remove(index18);
      }
   }

   private long method4() {
      return this.field4.stream().filter(arg0 -> !arg0.method28()).count();
   }

   private void method5(Markers2 markers21) {
      List list2 = this.field3.getMarkerSettings().method2().stream().map(ProtoConverter::method3).toList();
      if (list2.isEmpty()) {
         if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
            LunarLogger.method3("[MarkerManager] Not broadcasting marker because no receivers", new Object[0]);
         }
      } else {
         Marker marker3 = markers21.method20(this.field3.getMarkerSettings().method4());
         if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
            String text4 = this.field3.getMarkerSettings().method2().stream().map(UUID::toString).collect(Collectors.joining(","));
            LunarLogger.method3("[MarkerManager] Broadcasting marker to [%s]: %s", new Object[]{text4, marker3});
         }

         Ref.method5()
            .ifPresent(
               arg2x -> arg2x.method108()
                  .broadcastNewMarker(null, BroadcastNewMarkerRequest.newBuilder().addAllReceiverUuids(list2).setMarker(marker3).build(), arg0x -> {})
            );
      }
   }

   public void method6(UUID uuid1, Marker marker2) {
      if (this.field3.isEnabled()) {
         if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
            LunarLogger.method3("[MarkerManager] Received external marker from %s", new Object[]{uuid1});
         }

         Bridge5Extension_5 bridge5extension_53 = Ref.method7();
         if (bridge5extension_53 != null && Ref.method8() != null) {
            Vector3f vector3f4 = marker2.getPosition();
            float value5 = vector3f4.getX() - bridge5extension_53.bridge$getBlockX();
            float value6 = vector3f4.getY() - bridge5extension_53.bridge$getBlockY();
            float value7 = vector3f4.getZ() - bridge5extension_53.bridge$getBlockZ();
            float value8 = value5 * value5 + value6 * value6 + value7 * value7;
            if (value8 > 1048576.0F) {
               if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
                  LunarLogger.method3("[MarkerManager] Discarding marker from %s because too far away %.2f", new Object[]{uuid1, Math.sqrt(value8)});
               }
            } else {
               com.moonsworth.lunar.client.framework.feature.markers.Markers markers9 = com.moonsworth.lunar.client.framework.feature.markers.Markers.method2(
                  marker2.getSource()
               );
               if (this.field3.getMarkerSettings().method3(markers9, uuid1)) {
                  if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
                     LunarLogger.method3("[MarkerManager] Adding external marker %s", new Object[]{marker2});
                  }

                  String text10 = Ref.method3().bridge$getClientPacketListener().bridge$getPlayerInfo(uuid1).bridge$getGameProfile().getName();
                  Markers2 markers211 = Markers2.method21(uuid1, text10, markers9.method4(), marker2);
                  this.field4.add(markers211);
                  this.field3.addMarker(markers211);
                  this.method3(markers211);
               }
            }
         }
      }
   }

   public void method7(Markers2 markers21, ApolloMarkerData mixinhelper_22) {
      markers21.method31(mixinhelper_22);

      for (Markers2 markers24 : this.field4) {
         if (markers24.method28() && markers24.method27().getId().equals(mixinhelper_22.getId())) {
            markers24.method7(markers21, mixinhelper_22);
            return;
         }
      }

      this.field4.add(markers21);
      this.field3.addMarker(markers21);
   }

   public void method8(String text1) {
      this.field4.removeIf(arg1x -> arg1x.method28() && arg1x.method27().getId().equals(text1));
   }

   public void method9() {
      this.field4.removeIf(Markers2::method28);
   }

   @Generated
   public MarkerManager(com.moonsworth.lunar.client.mod.render.markers.Markers markers1) {
      this.field3 = markers1;
   }

   @Generated
   public List<Markers2> method10() {
      return this.field4;
   }
}
