package com.moonsworth.lunar.client.network.apollo;

import com.google.protobuf.Message;
import com.lunarclient.apollo.common.v1.Location;
import com.lunarclient.apollo.marker.v1.DisplayMarkerMessage;
import com.lunarclient.apollo.marker.v1.MarkerFlag;
import com.lunarclient.apollo.marker.v1.MarkerStyle;
import com.lunarclient.apollo.marker.v1.MarkerTarget;
import com.lunarclient.apollo.marker.v1.PlayerTarget;
import com.lunarclient.apollo.marker.v1.RemoveMarkerMessage;
import com.lunarclient.apollo.marker.v1.ResetMarkersMessage;
import com.lunarclient.apollo.marker.v1.MarkerFlag.FlagCase;
import com.lunarclient.apollo.network.NetworkTypes;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.feature.markers.Markers2;
import com.moonsworth.lunar.client.framework.feature.markers.Markers2_3;
import com.moonsworth.lunar.client.framework.feature.markers.Markers3_2;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.mod.render.markers.Markers;
import com.moonsworth.lunar.client.rewindhandlers.Rewindhandlers3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Set;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.joml.Vector3d;
import com.moonsworth.lunar.client.mod.render.markers.MarkerData$Builder;
import com.moonsworth.lunar.client.mod.render.markers.ApolloMarkerData;

public class MarkerApolloHandler extends ApolloModuleHandler {
   private static final double field4 = 1048576.0;

   public MarkerApolloHandler() {
      super("marker", "Marker");
   }

   @Override
   public Set<Class<? extends Message>> method2() {
      return Set.of(DisplayMarkerMessage.class, RemoveMarkerMessage.class, ResetMarkersMessage.class);
   }

   @Override
   protected void onEnable() {
      ThreadModuleDump63.method4().method40().method87().method51().method9();
   }

   @Override
   protected void onDisable() {
      ThreadModuleDump63.method4().method40().method87().method51().method9();
   }

   @Override
   public void method3(HighlightImpl_3 var1) {
      var1.unpack(DisplayMarkerMessage.class).ifPresent(this::method3);
      var1.unpack(RemoveMarkerMessage.class).ifPresent(var0 -> ThreadModuleDump63.method4().method40().method87().method51().method8(var0.getId()));
      var1.unpack(ResetMarkersMessage.class).ifPresent(var0 -> ThreadModuleDump63.method4().method40().method87().method51().method9());
   }

   private void method3(DisplayMarkerMessage var1) {
      Markers var2 = ThreadModuleDump63.method4().method40().method87();
      Bridge5Extension_5 var3 = ThreadModuleDump63.method7();
      if (var2.isEnabled() && var3 != null && var1.hasLocation()) {
         Location var4 = var1.getLocation();
         Vector3d var5 = new Vector3d(var4.getX(), var4.getY(), var4.getZ());
         double var6 = var5.x - var3.bridge$getBlockX();
         double var8 = var5.y - var3.bridge$getBlockY();
         double var10 = var5.z - var3.bridge$getBlockZ();
         if (!(var6 * var6 + var8 * var8 + var10 * var10 > 1048576.0)) {
            UUID var12 = NetworkTypes.fromProtobuf(var1.getOwnerId());
            Markers2.Type var13 = this.method5(var1.getFlag().getFlagCase());
            MarkerTarget var14 = var1.getTarget();
            Markers3_2 var16 = null;
            ItemStackBridge var17 = null;
            Markers2.Type2 var15;
            switch (var14.getTargetCase()) {
               case ITEM:
                  var15 = Markers2.Type2.ITEM;
                  var17 = Rewindhandlers3.method2(NetworkTypes.fromProtobuf(var14.getItem().getItemStack()));
                  break;
               case BLOCK:
                  var15 = Markers2.Type2.BLOCK;
                  var17 = Rewindhandlers3.method2(NetworkTypes.fromProtobuf(var14.getBlock().getItemStack()));
                  break;
               case ENTITY:
                  var15 = Markers2.Type2.ENTITY;
                  var16 = new Markers3_2(var14.getEntity().getEntityType(), Markers2_3.method2());
                  break;
               case PLAYER:
                  PlayerTarget var18 = var14.getPlayer();
                  TextComponent var19 = var18.getName().isEmpty() ? null : Component.text(var18.getName());
                  var15 = Markers2.Type2.PLAYER;
                  var16 = Markers2.Data2.method1(NetworkTypes.fromProtobuf(var18.getUuid()), var19, false);
                  break;
               case TARGET_NOT_SET:
               default:
                  return;
            }

            Markers2.Data var20 = new Markers2.Data(var15, var16, var13);
            Markers2 var21 = new Markers2(var12, var1.getOwnerName(), ThreadModuleDump63.MC_VERSION, var5, var20);
            var21.method32(true);
            if (var17 != null) {
               var21.method33(var17);
            }

            ThreadModuleDump63.method4().method40().method87().method51().method7(var21, this.method4(var1));
         }
      }
   }

   private ApolloMarkerData method4(DisplayMarkerMessage var1) {
      MarkerData$Builder var2 = ApolloMarkerData.method1()
         .method1(var1.getId())
         .method2(var1.getChatNotify())
         .method3(var1.getInGameNotification())
         .method4(var1.getMiddleClickRemove())
         .method6(this.method6(var1.getFlag()));
      if (var1.hasDuration()) {
         var2.method5(NetworkTypes.fromProtobuf(var1.getDuration()).toMillis());
      }

      if (var1.hasStyle()) {
         MarkerStyle var3 = var1.getStyle();
         var2.method7(var3.getScale())
            .method8(var3.getAnimateMarkerOnHover())
            .method9(var3.getCompactMode())
            .method10(var3.getTextShadow())
            .method11(var3.getOwnerSuffix())
            .method12(Markers.Type3.values()[var3.getOwnerDisplayValue() - 1])
            .method13(Markers.Type.values()[var3.getShowOwnerValue() - 1])
            .method14(Markers.Type.values()[var3.getShowCoordinatesValue() - 1])
            .method15(Markers.Type.values()[var3.getShowDistanceValue() - 1])
            .method16(Markers.Type.values()[var3.getShowDescriptionValue() - 1])
            .method17(Markers.Type2.values()[var3.getDescriptionDisplayValue() - 1]);
      }

      return var2.method18();
   }

   private Markers2.Type method5(FlagCase var1) {
      return switch (var1) {
         case DANGER -> Markers2.Type.DANGER;
         case INFO -> Markers2.Type.INFO;
         case INTEREST -> Markers2.Type.INTEREST;
         default -> Markers2.Type.NORMAL;
      };
   }

   private Integer method6(MarkerFlag var1) {
      return switch (var1.getFlagCase()) {
         case DANGER -> var1.getDanger().hasColor() ? var1.getDanger().getColor().getColor() : null;
         case INFO -> var1.getInfo().hasColor() ? var1.getInfo().getColor().getColor() : null;
         case INTEREST -> var1.getInterest().hasColor() ? var1.getInterest().getColor().getColor() : null;
         case NORMAL -> var1.getNormal().hasColor() ? var1.getNormal().getColor().getColor() : null;
         default -> null;
      };
   }
}
