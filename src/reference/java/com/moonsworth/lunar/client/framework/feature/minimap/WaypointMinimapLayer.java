package com.moonsworth.lunar.client.framework.feature.minimap;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.world.mixin.ChunkBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.MinimapOptionWidget;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.waypoints.Waypoint;
import com.moonsworth.lunar.client.driver.DriverRouteRegistry;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.waypoint.ConfigureWaypointProps;
import com.moonsworth.lunar.client.util.text.TextUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.LinkedHashMap;

public class WaypointMinimapLayer extends MinimapLayer<Waypoint> {
   private float field5 = -1.0F;

   public WaypointMinimapLayer(com.moonsworth.lunar.client.mod.render.minimap.MinimapMod minimap1, double value2, double value4, float value6, float value7, Waypoint guihandler28) {
      super(minimap1, value2, value4, value6, value7, guihandler28);
   }

   @Override
   public void method1(MixinHelper_4 mixinhelper_41, Bridge5Extension_5 bridge5extension_52, float value3, float value4, float value5) {
      String text6 = TextUtils.capitalizeFirstLetter(((Waypoint)this.method10()).getName());
      mixinhelper_41.push();
      mixinhelper_41.method38(value4, value5, 0.0F);
      mixinhelper_41.method42(-value3);
      mixinhelper_41.method38(-Ref.method10().bridge$getStringWidth(text6) / 2.0F, -4.0F, 0.0F);
      mixinhelper_41.method18(
         Ref.method10(),
         text6,
         0,
         0,
         ((Waypoint)this.method10()).method46().method4().method13(),
         (Boolean)this.method7().method23().get()
      );
      mixinhelper_41.pop();
   }

   @Override
   public void method2(MinimapOptionWidget calculator2iterator1, MixinHelper_4 mixinhelper_42, Data2 data23) {
      String[] items4 = calculator2iterator1.OHROCHICOIOICHOCRROORRCIIICIHO("waypointTooltip", new Object[]{((Waypoint)this.method10()).getName()})
         .split("\n");
      float value5 = 0.0F;

      for (String text9 : items4) {
         value5 = Math.max(value5, FontRegistry.method9().method4(text9) + 8.0F);
      }

      float value13 = data23.IIRCROICCRROCOCOIOIHHOCRHOIHIR() + 9.0F;
      float value14 = data23.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() + 9.0F;
      mixinhelper_42.push();
      mixinhelper_42.method38(0.0F, 0.0F, 10.0F);
      LcuiScreen.method117(mixinhelper_42, value13, value14, value5, FontRegistry.method9().getHeight() * items4.length * 2 + 5, 5.0F, Integer.MIN_VALUE);
      int number15 = 0;

      for (String text12 : items4) {
         FontRegistry.method9().method17(mixinhelper_42, text12, value13 + 4.0F, value14 + 2.0F + number15, -1, false);
         number15 += FontRegistry.method9().getHeight() * 2;
      }

      mixinhelper_42.pop();
   }

   @Override
   public void method3() {
      this.field5 = (float)((Waypoint)this.method10()).method35().bridge$yCoord();
   }

   @Override
   public void method4(float value1, float value2) {
      Vec3Bridge horsestats153 = ((Waypoint)this.method10()).method35();
      double value4 = horsestats153.bridge$xCoord() + value1;
      double value6 = horsestats153.bridge$zCoord() + value2;
      ((Waypoint)this.method10())
         .method20(Vec3Bridge.method2(value4, this.method6((int)Math.round(value4), (int)this.field5, (int)Math.round(value6)), value6));
   }

   @Override
   public void method5() {
      Client.method109().method48().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
      Client.method109().method48().method20();
      this.field5 = -1.0F;
   }

   public int method6(int number1, int number2, int number3) {
      WorldBridgeExtension itemcounter6extension4 = Ref.method8();
      if (itemcounter6extension4 == null) {
         return number2;
      }

      if (number2 <= itemcounter6extension4.bridge$getMinBuildHeight()) {
         number2 = itemcounter6extension4.bridge$getMinBuildHeight() + 1;
      }

      ChunkBridge itemcounter25 = itemcounter6extension4.bridge$getChunk(number1 >> 4, number3 >> 4);
      if (itemcounter25 == null) {
         return number2;
      }

      int number6 = number1 & 15;
      int index7 = 0;
      int number8 = number3 & 15;
      if (itemcounter25.bridge$getBlockState(number6, number2, number8).bridge$getBlock().bridge$isAir()
         && itemcounter25.bridge$getBlockState(number6, number2 - 1, number8).bridge$getBlock().bridge$isAir()) {
         while (index7 < number2) {
            if (this.method7(itemcounter25, number6, number2 - index7, number8)) {
               return number2 - index7;
            }

            index7++;
         }

         index7 = 0;
      }

      while (index7 < itemcounter6extension4.bridge$getMaxBuildHeight() || index7 > itemcounter6extension4.bridge$getMaxBuildHeight() + 1) {
         if (index7 > itemcounter6extension4.bridge$getMaxBuildHeight() + 1 && this.method7(itemcounter25, number6, number2 - index7, number8)) {
            return number2 - index7;
         }

         if (index7 < itemcounter6extension4.bridge$getMaxBuildHeight() && this.method7(itemcounter25, number6, number2 + index7, number8)) {
            return number2 + index7;
         }

         index7++;
      }

      return this.method7().method29().method12(itemcounter25, number1, number2 - 1, number3) + 1;
   }

   private boolean method7(ChunkBridge itemcounter21, int number2, int number3, int number4) {
      return itemcounter21.bridge$getBlockState(number2, number3, number4).bridge$getBlock().bridge$isAir()
         && !itemcounter21.bridge$getBlockState(number2, number3 - 1, number4).bridge$getBlock().bridge$isAir();
   }

   @Override
   public LinkedHashMap<String, Runnable> method6() {
      LinkedHashMap map1 = new LinkedHashMap();
      map1.put(
         "edit",
         () -> DriverViewportLegacy.method50()
            .method17(
               DriverRouteRegistry.field18,
               ConfigureWaypointProps.method2()
                  .method1(((Waypoint)this.method10()).getServer())
                  .method2(((Waypoint)this.method10()).getWorld())
                  .method3(((Waypoint)this.method10()).getName())
                  .method4(((Waypoint)this.method10()).method35().bridge$xCoord())
                  .method5(((Waypoint)this.method10()).method35().bridge$yCoord())
                  .method6(((Waypoint)this.method10()).method35().bridge$zCoord())
                  .method7()
            )
      );
      map1.put("remove", () -> Client.method109().method48().method9((Waypoint)this.method10()));
      return map1;
   }
}
