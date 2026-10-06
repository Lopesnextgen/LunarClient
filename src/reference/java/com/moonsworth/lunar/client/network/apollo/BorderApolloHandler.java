package com.moonsworth.lunar.client.network.apollo;

import com.google.protobuf.Message;
import com.lunarclient.apollo.border.v1.DisplayBorderMessage;
import com.lunarclient.apollo.border.v1.RemoveBorderMessage;
import com.lunarclient.apollo.border.v1.ResetBordersMessage;
import com.lunarclient.apollo.common.v1.Cuboid2D;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Itemcounter4Extension;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.client.mod.render.ServerBorderRegistry;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Set;
import com.moonsworth.lunar.client.framework.Client;

public class BorderApolloHandler extends ApolloModuleHandler {
   public BorderApolloHandler() {
      super("border", "Border");
   }

   @Override
   public Set<Class<? extends Message>> method2() {
      return Set.of(DisplayBorderMessage.class, RemoveBorderMessage.class, ResetBordersMessage.class);
   }

   @Override
   protected void onEnable() {
      this.method3();
   }

   @Override
   protected void onDisable() {
      this.method3();
   }

   @Override
   public void method3(HighlightImpl_3 highlightImpl_3) {
      ServerBorderRegistry var2 = Client.method109().method63();
      highlightImpl_3.unpack(DisplayBorderMessage.class).ifPresent(var1x -> {
         if (var1x.hasBounds()) {
            String var2x = var1x.getId();
            Cuboid2D var3 = var1x.getBounds();
            double var4 = var3.getMinX();
            double var6 = var3.getMinZ();
            double var8 = var3.getMaxX() + 1.0;
            double var10 = var3.getMaxZ() + 1.0;
            if (var4 > var8) {
               double var12 = var4;
               var4 = var8;
               var8 = var12;
            }

            if (var6 > var10) {
               double var25 = var6;
               var6 = var10;
               var10 = var25;
            }

            boolean var26 = var1x.getCancelEntry();
            boolean var13 = var1x.getCancelExit();
            boolean var14 = var1x.getCanShrinkOrExpand();
            int var15 = var1x.getColor().getColor();
            if (var2.method2().containsKey(var2x)) {
               var2.method6(var2x, var4, var6, var8, var10, var1x.getDurationTicks(), var26, var13, var15);
            } else {
               Itemcounter4Extension var16 = Bridge.method8().method46(var1x.getWorld(), var15);
               Itemcounter6Extension var17 = ThreadModuleDump63.method8();
               int var18 = var17 == null ? 0 : var17.bridge$getMinBuildHeight();
               int var19 = var17 == null ? 256 : var17.bridge$getMaxBuildHeight();
               AxisAlignedBBBridge var20 = Bridge.method8().method45(var4, var18, var6, var8, var19, var10);
               double var21 = (var20.bridge$getMaxX() - var20.bridge$getMinX()) / 2.0;
               double var23 = (var20.bridge$getMaxZ() - var20.bridge$getMinZ()) / 2.0;
               var16.method12(var21 + var23);
               var16.method9(var20.bridge$getMinX() + var21, var20.bridge$getMinZ() + var23);
               var16.setCancelEntry(var26);
               var16.setCancelExit(var13);
               var16.method4(var14);
               var16.setColor(var15);
               if (!var14) {
                  var16.method6(var20);
               }

               var2.method3(var2x, var16);
            }
         }
      });
      highlightImpl_3.unpack(RemoveBorderMessage.class).ifPresent(var1x -> var2.method4(var1x.getId()));
      highlightImpl_3.unpack(ResetBordersMessage.class).ifPresent(var1x -> this.method3());
   }

   private void method3() {
      Client.method109().method63().clear();
   }
}
