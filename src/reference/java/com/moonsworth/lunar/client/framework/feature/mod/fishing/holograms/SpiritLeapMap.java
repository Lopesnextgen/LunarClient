package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.DungeonPlayerTracker;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5;
import com.moonsworth.lunar.client.driver.core.Fishing;
import com.moonsworth.lunar.client.driver.nameplate.Nameplate;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.function.Consumer;
import lombok.Generated;

@VersionGate(min = 33)
public class SpiritLeapMap {
   private final DungeonStats field1;
   private final Consumer<String> field2;
   private Nameplate field3;
   private boolean field4;
   private final DungeonMapOverlay field5;
   private Fishing field6;

   public SpiritLeapMap(Consumer<String> consumer1, DungeonStats holograms_62) {
      this.field1 = holograms_62;
      this.field5 = new DungeonMapOverlay(holograms_62);
      this.field2 = consumer1;
   }

   public void method1(AbstractRenderContext bridgeextension_91, BettermapSettings holograms_92, Data5 data53) {
      if (this.field3 != null && this.field4) {
         if (this.field6 == null) {
            this.field6 = new Fishing("spirit-leap-map", false, 60);
         }

         if (this.field6.method2(bridgeextension_91, (int)this.field3.method1(), (int)this.field3.method2())) {
            bridgeextension_91.method40();
            if (Ref.MC_VERSION >= 39) {
               bridgeextension_91.method35(0.0, (int)this.field3.method1(), (int)this.field3.method2(), 0.0, 21000.0, 1000.0);
            } else {
               bridgeextension_91.method35(0.0, (int)this.field3.method1(), (int)this.field3.method2(), 0.0, 1000.0, 21000.0);
            }

            DungeonStateTracker holograms2_54 = this.field1.method1();
            float value5 = 0.0F;
            float value6 = 0.0F;
            float value7 = Math.min(this.field3.method1(), this.field3.method2());
            float value8 = value7 / 100.0F;
            if (this.field3.method1() > this.field3.method2()) {
               float value9 = (this.field3.method1() - value7) / 2.0F;
               value5 += value9;
            } else if (this.field3.method2() > this.field3.method1()) {
               float value11 = (this.field3.method2() - value7) / 2.0F;
               value6 += value11;
            }

            if (holograms2_54 == null) {
               bridgeextension_91.push();
               bridgeextension_91.scale(value8, value8, 1.0F);
               LcuiScreen.method97(bridgeextension_91, value5 / value8, value6 / value8, 100.0F, 100.0F, 1862270976);
               Ref.method10()
                  .method13(bridgeextension_91, "Dungeon Map", value5 / value8 + 50.0F, value6 / value8 + 50.0F - Ref.method10().method19() / 2.0F, 16777215, true);
               bridgeextension_91.pop();
            } else {
               data53 = (Data5)data53.HICCRORORCRIHCORCCIORIOROORIHR(value5 + this.field3.x(), value6 + this.field3.y());
               this.field5.method1(new LegacyGuiGraphicsBridge(bridgeextension_91), holograms_92, holograms2_54, value5, value6, value8, data53);
            }

            bridgeextension_91.method30().method48();
            bridgeextension_91.method41();
            this.field6.method3(bridgeextension_91);
         }
      }
   }

   public void method2() {
      DungeonPlayerTracker holograms4updater1 = this.field5.method27();
      if (holograms4updater1 != null) {
         this.field2.accept(holograms4updater1.method20(false));
      }
   }

   public void method3(boolean flag1) {
      if (!flag1 && this.field6 != null) {
         this.field6.delete();
         this.field6 = null;
      }

      this.field4 = flag1;
   }

   @Generated
   public void method4(Nameplate nameplate1) {
      this.field3 = nameplate1;
   }

   @Generated
   public Nameplate method5() {
      return this.field3;
   }

   @Generated
   public DungeonMapOverlay method6() {
      return this.field5;
   }
}
