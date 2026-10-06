package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.particle.ParticleType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.NameplateType;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.util.concurrent.SupplierExtension;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.render.color.RewindhandlersExtension;
import com.moonsworth.lunar.files.ValuePair;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.joml.Vector3d;

public class RouteRenderer {
   public static final double[] field1 = new double[]{0.5, 0.2, 0.5};
   private static final RewindhandlersExtension field2 = RewindhandlersExtension.method23(-65536);
   private static final double field3 = 2.0;
   private static final double field4 = 1.0;
   private final RouteManager field5;
   private final SupplierExtension<ItemStackBridge> field6 = SupplierExtension.lazy(() -> Bridge.method8().method38(Bridge.method28().method19()));

   public RouteRenderer(RouteManager holograms3_31) {
      this.field5 = holograms3_31;
   }

   public void method1(AbstractRenderContext bridgeextension_91, RouteRenderer.Type type2, RouteSection holograms73, List<int[]> list4) {
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomInstance holograms35 = this.field5.method17().orElse(null);
      if (holograms35 != null) {
         SkyblockDungeonRoutes skyblockdungeonroutes6 = SkyblockDungeonRoutes.method13();
         if (type2 == RouteRenderer.Type.ESP) {
            for (TextHologram holograms10$data8 : holograms73.method6().getTextHolograms()) {
               double[] items9 = holograms35.method11(holograms10$data8.pos.x, holograms10$data8.pos.y, holograms10$data8.pos.z);
               WorldRenderUtils.drawBillboardText(bridgeextension_91, items9[0] + 0.5, items9[1] + 1.7, items9[2] + 0.5, holograms10$data8.text, true, true, false, 1.0F, RewindhandlersExtension.method23(-1));
            }
         }

         if (holograms73.getIndex() > 0) {
            RouteSection holograms715 = holograms73.method7().getSections().get(holograms73.getIndex() - 1);
            if (!holograms715.method9().isEmpty()) {
               ValuePair files6_218 = holograms715.method9().get(holograms715.method9().size() - 1);
               if (files6_218.field1 == NameplateType.ITEM_DROP) {
                  this.method3(bridgeextension_91, type2, list4, holograms35, files6_218, false, false);
               }
            }
         }

         if (type2 == RouteRenderer.Type.ESP && !holograms73.method8().isEmpty() && (this.field5.method9() || (Boolean)skyblockdungeonroutes6.method22().get() && holograms73.getIndex() == 0)) {
            int[] items16 = holograms73.method8().get(0);
            double[] items19 = holograms35.method11(items16[0] - 0.5, items16[1] + 2, items16[2] - 0.5);
            WorldRenderUtils.drawBillboardText(bridgeextension_91, items19[0], items19[1], items19[2], holograms73.method7().method22(), true, true, true, 1.0F, SkyblockDungeonRoutes.method13().method23());
         }

         if (type2 == RouteRenderer.Type.DEPTH
            && skyblockdungeonroutes6.method26().get() != com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.RouteRenderType.PARTICLES
            && skyblockdungeonroutes6.method26().get() != com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.RouteRenderType.NONE) {
            method5(
               bridgeextension_91,
               holograms73.method8(),
               holograms35,
               (com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.RouteRenderType)skyblockdungeonroutes6.method26().get(),
               field2
            );
         }

         ArrayList list17 = new ArrayList();

         for (ValuePair files6_223 : holograms73.method9()) {
            if (files6_223.field1 == NameplateType.PEARL) {
               list17.add(files6_223);
            }
         }

         for (ValuePair files6_224 : holograms73.method9()) {
            if (files6_224.field1 != NameplateType.PEARL) {
               list17.add(files6_224);
            }
         }

         HashSet set22 = new HashSet();
         HashSet set25 = new HashSet();
         HashSet set10 = new HashSet();
         ArrayList list11 = new ArrayList();
         ArrayList list12 = new ArrayList();

         for (ValuePair files6_214 : list17) {
            if (this.method2(list4, holograms35, files6_214, list11, list12)) {
               set22.add(files6_214);
            }

            if (!set10.add(((int[])files6_214.field2)[0] + "," + ((int[])files6_214.field2)[1] + "," + ((int[])files6_214.field2)[2])) {
               set25.add(files6_214);
            }
         }

         for (int index26 = list17.size() - 1; index26 >= 0; index26--) {
            ValuePair files6_227 = (ValuePair)list17.get(index26);
            this.method3(bridgeextension_91, type2, list4, holograms35, files6_227, set22.contains(files6_227), set25.contains(files6_227));
         }
      }
   }

   private boolean method2(
      List<int[]> list1,
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomInstance holograms32,
      ValuePair<NameplateType, int[]> files6_23,
      List<int[]> list4,
      List<int[]> list5
   ) {
      NameplateType nameplatetype6 = (NameplateType)files6_23.field1;
      if (!nameplatetype6.shouldRender()) {
         return false;
      }

      int[] items7 = (int[])files6_23.field2;
      int[] items8 = holograms32.method8(items7);
      boolean flag9 = list1.stream().anyMatch(arg1x -> arg1x[0] == items8[0] && arg1x[1] == items8[1] && arg1x[2] == items8[2]);
      SkyblockDungeonRoutes skyblockdungeonroutes10 = SkyblockDungeonRoutes.method13();
      boolean flag11 = !(Boolean)skyblockdungeonroutes10.method25().get();
      if (flag11) {
         for (int[] items13 : list5) {
            if (items13[0] == items8[1] && items13[1] == items8[1] && items13[2] == items8[2]) {
               flag11 = false;
               break;
            }
         }
      }

      if (flag11 && nameplatetype6 == NameplateType.BREAK_BLOCK) {
         for (int[] items17 : list4) {
            double value14 = Math.pow(items17[0] - items8[0], 2.0) + Math.pow(items17[1] - items8[1], 2.0) + Math.pow(items17[2] - items8[2], 2.0);
            if (value14 < 100.0) {
               flag11 = false;
               break;
            }
         }

         if (flag11) {
            list4.add(items8);
         }
      }

      if (!flag9 && flag11) {
         list5.add(items8);
         return true;
      } else {
         return false;
      }
   }

   private void method3(
      AbstractRenderContext bridgeextension_91,
      RouteRenderer.Type type2,
      List<int[]> list3,
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomInstance holograms34,
      ValuePair<NameplateType, int[]> files6_25,
      boolean flag6,
      boolean flag7
   ) {
      NameplateType nameplatetype8 = (NameplateType)files6_25.field1;
      if (nameplatetype8.shouldRender()) {
         int[] items9 = (int[])files6_25.field2;
         int[] items10 = holograms34.method8(items9);
         boolean flag11 = list3.stream().anyMatch(arg1x -> arg1x[0] == items10[0] && arg1x[1] == items10[1] && arg1x[2] == items10[2]);
         double value12 = items10[0] + (flag7 ? 0.1 : 0.0);
         double value14 = items10[1] + (flag7 ? 0.1 : 0.0);
         double value16 = items10[2] + (flag7 ? 0.1 : 0.0);
         double value18 = flag7 ? 0.8 : 1.0;
         if (type2 == RouteRenderer.Type.DEPTH && !flag11 && nameplatetype8 != NameplateType.BREAK_BLOCK) {
            WorldRenderUtils.drawBoxAt(bridgeextension_91, value12, value14, value16, value18, value18, value18, nameplatetype8.getColorAlpha(), true, 1.0F, true);
         }

         if (type2 == RouteRenderer.Type.ESP) {
            WorldRenderUtils.drawBoxAt(bridgeextension_91, value12, value14, value16, value18, value18, value18, flag11 ? nameplatetype8.getColorAlpha() : nameplatetype8.getColor(), false, 3.0F, true);
         }

         if (type2 == RouteRenderer.Type.ESP && flag6) {
            WorldRenderUtils.drawBillboardText(bridgeextension_91, items10[0] + 0.5, items10[1] + (flag7 ? 0.3 : 0.5), items10[2] + 0.5, nameplatetype8.getText(), true, true);
         }

         if (type2 == RouteRenderer.Type.ESP && nameplatetype8 == NameplateType.PEARL) {
            Bridge5Extension_5 bridge5extension_520 = Ref.method7();
            Vector3d vector3d21 = new Vector3d(bridge5extension_520.bridge$getPosX(), bridge5extension_520.bridge$getPosY(), bridge5extension_520.bridge$getPosZ());
            Vector3d vector3d22 = new Vector3d(items10[0] + 0.5, items10[1] + 0.5, items10[2] + 0.5);
            boolean flag23 = vector3d21.distanceSquared(vector3d22) > 25.0;
            Holograms.Data data24 = null;
            if (flag23) {
               data24 = Holograms.method3(new Vector3d(items10[0] + 0.5, items10[1] + 0.5, items10[2] + 0.5), true);
               if (data24.method3() != null && vector3d22.distanceSquared(data24.method3()) > 4.0) {
                  flag23 = false;
               }
            }

            if (flag23) {
               Vector3d vector3d25 = data24.method1().normalize(2.0);
               EntityRenderDispatcherBridge bridge2_4326 = Ref.method13();
               double value27 = bridge2_4326.bridge$renderPosX() + vector3d25.x * 10.0;
               double value29 = bridge2_4326.bridge$renderPosY() + vector3d25.y * 10.0 + Ref.method7().bridge$getEyeHeight();
               double value31 = bridge2_4326.bridge$renderPosZ() + vector3d25.z * 10.0;
               WorldRenderUtils.drawLine(bridgeextension_91, items10[0] + 0.5, items10[1] + 0.5, items10[2] + 0.5, value27, value29, value31, nameplatetype8.getColor(), 2.0F, true);
               bridgeextension_91.push();
               bridgeextension_91.translate(vector3d25.x * 10.0, vector3d25.y * 10.0 + Ref.method7().bridge$getEyeHeight(), vector3d25.z * 10.0);
               bridgeextension_91.translate(bridge2_4326.bridge$renderPosX(), bridge2_4326.bridge$renderPosY(), bridge2_4326.bridge$renderPosZ());
               WorldRenderUtils.drawBoxAt(bridgeextension_91, -0.25, -0.25, -0.25, 0.5, 0.5, 0.5, nameplatetype8.getColor(), true, 1.0F, false);
               bridgeextension_91.pop();
            }
         }
      }
   }

   public void method4(RouteSection holograms71) {
      SkyblockDungeonRoutes skyblockdungeonroutes2 = SkyblockDungeonRoutes.method13();
      if (skyblockdungeonroutes2.method26().get() == com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.RouteRenderType.PARTICLES) {
         com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomInstance holograms33 = this.field5.method17().orElse(null);
         if (holograms33 != null) {
            int[] items4 = null;

            for (int[] items6 : holograms71.method8()) {
               int[] items7 = holograms33.method8(items6);
               if (items4 != null) {
                  ParticleTrail.method1(items4, items7, field1, ParticleType.FLAME);
               }

               items4 = items7;
            }
         }
      }
   }

   public static void method5(
      AbstractRenderContext bridgeextension_90,
      List<int[]> list1,
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomInstance holograms32,
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.RouteRenderType gui2extension3,
      RewindhandlersExtension rewindhandlersextension4
   ) {
      boolean flag5 = gui2extension3 == com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.RouteRenderType.ARROW
         || gui2extension3 == com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.RouteRenderType.ARROW_DASHED;
      boolean flag6 = gui2extension3 == com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.RouteRenderType.ARROW_DASHED
         || gui2extension3 == com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.RouteRenderType.LINE_DASHED;
      int[] items7 = null;
      double value8 = 3.0;
      double value10 = -Ref.method3().bridge$getSystemTime() / 1000.0 % value8 + value8;
      boolean flag12 = false;

      for (int[] items14 : list1) {
         if (items7 != null) {
            value10 -= Math.sqrt(Math.pow(items14[0] - items7[0], 2.0) + Math.pow(items14[1] - items7[1], 2.0) + Math.pow(items14[2] - items7[2], 2.0));
         }

         items7 = items14;
      }

      value10 = value10 % value8 + value8;
      if (value10 > 2.0 && flag6) {
         flag12 = true;
         value10 -= 2.0;
      }

      items7 = null;

      for (int[] items29 : list1) {
         int[] items15 = items29;
         if (holograms32 != null) {
            items15 = holograms32.method8(items29);
         }

         if (items7 != null) {
            Vector3d vector3d16 = new Vector3d(items7[0] + field1[0], items7[1] + field1[1], items7[2] + field1[2]);
            Vector3d vector3d17 = new Vector3d(items15[0] + field1[0], items15[1] + field1[1], items15[2] + field1[2]);
            double value18 = vector3d16.distance(vector3d17);

            for (value10 += value18; flag6 && value10 > (flag12 ? 1.0 : 2.0); value18 = vector3d16.distance(vector3d17)) {
               double value20 = flag12 ? 1.0 : 2.0;
               double value22 = value10 - value20;
               Vector3d vector3d24 = vector3d17.sub(vector3d16, new Vector3d());
               Vector3d vector3d25 = vector3d16.add(vector3d24.normalize(value18 - value22, new Vector3d()), new Vector3d());
               value10 = value22;
               if (!flag12) {
                  WorldRenderUtils.drawLine(bridgeextension_90, vector3d16, vector3d25, rewindhandlersextension4, 2.0F, true, flag5);
               }

               flag12 = !flag12;
               vector3d16 = vector3d25;
            }

            if (!flag12) {
               WorldRenderUtils.drawLine(bridgeextension_90, vector3d16, vector3d17, rewindhandlersextension4, 2.0F, true, flag5 && !flag6);
            }
         }

         items7 = items15;
      }
   }

   public enum Type {
      DEPTH,
      ESP;

      Type() {
      }
   }
}
