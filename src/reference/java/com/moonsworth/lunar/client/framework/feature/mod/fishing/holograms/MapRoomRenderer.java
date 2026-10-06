package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.HologramTextRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.WorldPosition;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.DungeonRoomTracker;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.render.color.RewindhandlersExtension;
import com.moonsworth.lunar.files.ValuePair;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.HashMap;
import java.util.HashSet;

@VersionGate(min = 33)
public class MapRoomRenderer {
   private final DungeonMapElement field1;
   private final DungeonMapOverlay field2;
   private MarkerModel<?> field3 = new Data2(0.0, 0.0);
   private DungeonRoomTracker field4;

   public MapRoomRenderer(DungeonMapOverlay holograms3_21, DungeonMapElement holograms6iterator2) {
      this.field2 = holograms3_21;
      this.field1 = holograms6iterator2;
   }

   public void method1(MixinHelper_4 mixinhelper_41, BettermapSettings holograms_92, DungeonStateTracker holograms2_53, DungeonRoomTracker holograms4iterator4, float value5, float value6, MarkerModel<?> markers7) {
      this.field3 = markers7;
      this.field4 = holograms4iterator4;
      if (!this.method6(holograms4iterator4, holograms_92)) {
         ValuePair files6_28 = this.method4(holograms_92, holograms2_53, holograms4iterator4, value5, value6);
         float value9 = (Float)files6_28.field1;
         float value10 = (Float)files6_28.field2;
         mixinhelper_41.push();
         MapTickIcon hologramstype211 = holograms_92.method23(holograms4iterator4);
         float value12 = holograms_92.method33() && holograms_92.method45() ? holograms2_53.method29().method14() : 0.0F;
         if (hologramstype211 != null) {
            float value13 = holograms_92.method24(holograms2_53);
            this.field2.method21(mixinhelper_41, hologramstype211, value9, value10, Math.min(value13, value13 * hologramstype211.getTextureWidth() / hologramstype211.getTextureHeight()), value13, value12);
         } else {
            MapTickStyle gui2extension220 = holograms4iterator4.method30().method6() == MapRoomType.PUZZLE ? holograms_92.method46() : holograms_92.method47();
            ChatFormatting horsestatstype814 = holograms4iterator4.method30().method2().asColor();
            String[] items15 = this.method5(holograms4iterator4, gui2extension220);
            Object obj16 = items15[0];
            Object obj17 = items15[1];
            Object obj18 = items15[2];
            mixinhelper_41.method38(value9 * this.field2.field8, value10 * this.field2.field8, 0.0F);
            mixinhelper_41.method42(value12);
            if (obj16 != null) {
               float value19 = holograms_92.method48() / 100.0F * 0.4F;
               this.field2
                  .method11(
                     mixinhelper_41, horsestatstype814 + obj16, 0.0F, -(obj18 == null ? 0.0F : value19 * 8.0F) - (obj17 == null ? 0.0F : value19 * 4.0F), value19, HologramTextRenderer.Type.BORDER
                  );
               if (obj17 != null) {
                  this.field2.method11(mixinhelper_41, horsestatstype814 + obj17, 0.0F, -(obj18 == null ? 0.0F : value19 * 8.0F) + value19 * 4.0F, value19, HologramTextRenderer.Type.BORDER);
               }
            }

            if (obj18 != null) {
               float value21 = holograms_92.method48() / 100.0F * 0.8F;
               this.field2
                  .method11(
                     mixinhelper_41, horsestatstype814 + obj18, 0.0F, (obj16 == null ? 0.0F : value21 * 4.0F) + (obj17 == null ? 0.0F : value21 * 2.0F), value21, HologramTextRenderer.Type.BORDER
                  );
            }
         }

         mixinhelper_41.pop();
      }
   }

   public void method2(MixinHelper_4 mixinhelper_41, BettermapSettings holograms_92, DungeonStateTracker holograms2_53, DungeonRoomTracker holograms4iterator4, float value5, float value6, MarkerModel<?> markers7) {
      if (holograms4iterator4.method30().method6() != null) {
         this.field3 = markers7;
         this.field4 = holograms4iterator4;
         HashSet set8 = new HashSet();

         for (WorldPosition nameplate410 : holograms4iterator4.method28()) {
            set8.add(nameplate410.method8() + nameplate410.method9() * 10);
         }

         RewindhandlersExtension rewindhandlersextension15 = holograms_92.method20(holograms4iterator4.method30().method6(), false);

         for (WorldPosition nameplate411 : holograms4iterator4.method28()) {
            float value12 = value5 + holograms_92.method35() + nameplate411.method12(holograms_92);
            float value13 = value6 + holograms_92.method35() + nameplate411.method13(holograms_92);
            this.method3(mixinhelper_41, value12 + holograms_92.method19(holograms2_53) / 2.0F, value13 + holograms_92.method19(holograms2_53) / 2.0F, holograms_92.method17(holograms2_53), holograms_92.method17(holograms2_53), rewindhandlersextension15);
            int index14 = 0;
            if (set8.contains(nameplate411.method8() + 1 + nameplate411.method9() * 10)) {
               this.method3(
                  mixinhelper_41,
                  value12 + holograms_92.method19(holograms2_53) / 2.0F + holograms_92.method17(holograms2_53),
                  value13 + holograms_92.method19(holograms2_53) / 2.0F,
                  holograms_92.method19(holograms2_53),
                  holograms_92.method17(holograms2_53),
                  rewindhandlersextension15
               );
               index14++;
            }

            if (set8.contains(nameplate411.method8() + nameplate411.method9() * 10 + 10)) {
               this.method3(
                  mixinhelper_41,
                  value12 + holograms_92.method19(holograms2_53) / 2.0F,
                  value13 + holograms_92.method19(holograms2_53) / 2.0F + holograms_92.method17(holograms2_53),
                  holograms_92.method17(holograms2_53),
                  holograms_92.method19(holograms2_53),
                  rewindhandlersextension15
               );
               index14++;
            }

            if (set8.contains(nameplate411.method8() + 1 + nameplate411.method9() * 10 + 10)) {
               index14++;
            }

            if (index14 == 3) {
               this.method3(
                  mixinhelper_41,
                  value12 + holograms_92.method19(holograms2_53) / 2.0F + holograms_92.method17(holograms2_53),
                  value13 + holograms_92.method19(holograms2_53) / 2.0F + holograms_92.method17(holograms2_53),
                  holograms_92.method19(holograms2_53),
                  holograms_92.method19(holograms2_53),
                  rewindhandlersextension15
               );
            }
         }
      }
   }

   private void method3(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4, float value5, RewindhandlersExtension rewindhandlersextension6) {
      this.field2.method17(mixinhelper_41, value2, value3, value4, value5, rewindhandlersextension6);
      if (this.field3.method10() >= value2 && this.field3.method10() <= value2 + value4 && this.field3.method11() >= value3 && this.field3.method11() <= value3 + value5) {
         this.field1.field9 = this.field4;
      }
   }

   private ValuePair<Float, Float> method4(BettermapSettings holograms_91, DungeonStateTracker holograms2_52, DungeonRoomTracker holograms4iterator3, float value4, float value5) {
      float value8 = holograms_91.method35();
      float value19;
      float value20;
      if (holograms_91.method49()) {
         value19 = 0.0F;
         value20 = 0.0F;
         int number9 = holograms4iterator3.method28().size();

         for (WorldPosition nameplate411 : holograms4iterator3.method28()) {
            value19 += value4 + value8 + nameplate411.method12(holograms_91) + holograms_91.method17(holograms2_52) / 2.0F + holograms_91.method19(holograms2_52) / 2.0F;
            value20 += value5 + value8 + nameplate411.method13(holograms_91) + holograms_91.method17(holograms2_52) / 2.0F + holograms_91.method19(holograms2_52) / 2.0F;
         }

         value19 /= number9;
         value20 /= number9;
         if (number9 == 3) {
            HashSet set22 = new HashSet();
            HashMap map24 = new HashMap();
            int number12 = 0;
            float value13 = 0.0F;

            for (WorldPosition nameplate415 : holograms4iterator3.method28()) {
               float value16 = value4 + value8 + nameplate415.method12(holograms_91) + holograms_91.method17(holograms2_52) / 2.0F + holograms_91.method19(holograms2_52) / 2.0F;
               float value17 = value5 + value8 + nameplate415.method13(holograms_91) + holograms_91.method17(holograms2_52) / 2.0F + holograms_91.method19(holograms2_52) / 2.0F;
               int number18 = map24.containsKey(value17) ? (Integer)map24.get(value17) + 1 : 1;
               set22.add(value16);
               map24.put(value17, number18);
               if (number18 > number12) {
                  number12 = number18;
                  value13 = value17;
               }
            }

            if (set22.size() == 2 && map24.size() == 2) {
               value20 = value13;
            }
         }
      } else {
         value19 = Float.POSITIVE_INFINITY;
         value20 = Float.POSITIVE_INFINITY;

         for (WorldPosition nameplate423 : holograms4iterator3.method28()) {
            float value25 = value4 + value8 + nameplate423.method12(holograms_91) + holograms_91.method17(holograms2_52) / 2.0F + holograms_91.method19(holograms2_52) / 2.0F;
            float value26 = value5 + value8 + nameplate423.method13(holograms_91) + holograms_91.method17(holograms2_52) / 2.0F + holograms_91.method19(holograms2_52) / 2.0F;
            if (!(value19 < value25) && (value19 != value25 || !(value26 > value20))) {
               value19 = value25;
               value20 = value26;
            }
         }
      }

      return ValuePair.method1(value19, value20);
   }

   private String[] method5(DungeonRoomTracker holograms4iterator1, MapTickStyle gui2extension22) {
      String text3 = null;
      String text4 = null;
      String text5 = null;
      String text6 = holograms4iterator1.method11(true);
      if (gui2extension22 == MapTickStyle.ROOM_NAME || gui2extension22 == MapTickStyle.SECRETS_AND_NAME && text6 != null) {
         String[] items7 = (text6 == null ? "???" : text6).split(" ");
         if (items7.length > 1) {
            text4 = items7[items7.length - 1];
            items7[items7.length - 1] = "";
         }

         StringBuilder builder8 = new StringBuilder();

         for (String text12 : items7) {
            builder8.append(" ").append(text12);
         }

         text3 = builder8.toString().trim();
      }

      if ((gui2extension22 == MapTickStyle.SECRETS || gui2extension22 == MapTickStyle.SECRETS_AND_NAME && holograms4iterator1.method14() != -1)
         && (
            holograms4iterator1.method14() != -1
               || holograms4iterator1.method30().method2() != com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.OPENED
         )
         && holograms4iterator1.method14() != 0) {
         text5 = holograms4iterator1.method14() == -1 ? "?/?" : holograms4iterator1.method30().method1() + "/" + holograms4iterator1.method14();
      }

      return new String[]{text3, text4, text5};
   }

   private boolean method6(DungeonRoomTracker holograms4iterator1, BettermapSettings holograms_92) {
      MapRoomType hologramstype53 = holograms4iterator1.method30().method6();
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState hologramstype24 = holograms4iterator1.method30().method2();
      if (hologramstype53 == MapRoomType.SPAWN || hologramstype53 == MapRoomType.FAIRY) {
         return true;
      } else {
         return hologramstype53 == MapRoomType.UNKNOWN && !holograms_92.method22()
            ? true
            : hologramstype24 == com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.OPENED
               && !holograms_92.method26(holograms_92.method25(holograms4iterator1));
      }
   }
}
