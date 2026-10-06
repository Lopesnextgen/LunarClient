package com.moonsworth.lunar.client.network.apollo;

import com.google.protobuf.Message;
import com.lunarclient.apollo.common.v1.BlockLocation;
import com.lunarclient.apollo.waypoint.v1.DisplayWaypointMessage;
import com.lunarclient.apollo.waypoint.v1.HideWaypointMessage;
import com.lunarclient.apollo.waypoint.v1.RemoveWaypointMessage;
import com.lunarclient.apollo.waypoint.v1.ResetWaypointsMessage;
import com.lunarclient.apollo.waypoint.v1.ShowWaypointMessage;
import com.lunarclient.apollo.waypoint.v1.WaypointTextStyle;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.mod.render.WaypointStore;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiHandler2;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiLoader;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import java.util.Set;
import com.moonsworth.lunar.client.framework.Client;

public class WaypointApolloHandler extends ApolloModuleHandler {
   public WaypointApolloHandler() {
      super("waypoint", "Waypoint");
   }

   @Override
   public Set<Class<? extends Message>> method2() {
      return Set.of(
         DisplayWaypointMessage.class, RemoveWaypointMessage.class, ResetWaypointsMessage.class, ShowWaypointMessage.class, HideWaypointMessage.class
      );
   }

   @Override
   protected void onEnable() {
      this.method4();
   }

   @Override
   protected void onDisable() {
      this.method4();
   }

   @Override
   public void method3(HighlightImpl_3 var1) {
      var1.unpack(DisplayWaypointMessage.class)
         .ifPresent(
            var0 -> {
               if (var0.hasLocation()) {
                  BlockLocation var1x = var0.getLocation();
                  Vec3Bridge var2 = Vec3Bridge.method2(var1x.getX() + 0.5, var1x.getY(), var1x.getZ() + 0.5);
                  WaypointTextStyle var3 = var0.getStyle();
                  GuiLoader var4 = new GuiLoader();
                  var4.method4().method1(Integer.valueOf(var0.getColor().getColor()));
                  var4.setShowBeam(var0.getShowBeam());
                  var4.setHighlightBlock(var0.getHighlightBlock());
                  float var5 = var0.getHighlightBlockLineWidth();
                  if (var5 != 0.0F) {
                     var4.method12(var5);
                  }

                  if (var0.hasStyle()) {
                     var4.setShowText(var3.getShowText());
                     var4.setShowDistance(var3.getShowDistance());
                     GuiLoader.Data var6 = new GuiLoader.Data(
                        var3.getOnlyShowTextWhenLookingNear(),
                        var3.getShowIcons(),
                        var3.getTextIconScale(),
                        var3.getLabelScale(),
                        var3.getBoxPadding(),
                        var3.getBoxBorders(),
                        var3.getTextShadow()
                     );
                     var4.method13(var6);
                  }

                  GuiHandler2 var7 = GuiHandler2.method18()
                     .method2(var0.getName())
                     .method3(var2)
                     .method4(var1x.getWorld())
                     .method10(!var0.getHidden())
                     .method11(var0.getPreventRemoval())
                     .method12(WaypointStore.method19())
                     .method18(var4)
                     .method19();
                  Client.method109().method48().method5(var7, false);
               }
            }
         );
      var1.unpack(RemoveWaypointMessage.class)
         .ifPresent(var0 -> Client.method109().method48().removeIf(var1x -> var1x.method41() && var1x.getName().equals(var0.getName())));
      var1.unpack(ResetWaypointsMessage.class).ifPresent(var1x -> this.method4());
      var1.unpack(ShowWaypointMessage.class).ifPresent(var1x -> this.method3(var1x.getName(), true));
      var1.unpack(HideWaypointMessage.class).ifPresent(var1x -> this.method3(var1x.getName(), false));
   }

   private void method3(String var1, boolean var2) {
      WaypointStore var3 = Client.method109().method48();

      for (GuiHandler2 var5 : var3.IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
         if (var5.method41() && var5.getName().equals(var1)) {
            var5.setVisible(var2);
            var3.method20();
            return;
         }
      }
   }

   private void method4() {
      WaypointStore var1 = Client.method109().method48();
      var1.IIORHHIRHIORHRCCCOICCRCHRRCCRH().removeIf(GuiHandler2::method41);
      var1.method20();
   }
}
