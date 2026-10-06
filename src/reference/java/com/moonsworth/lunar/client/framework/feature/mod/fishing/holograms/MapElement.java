package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.AnimatedValue;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.DungeonPlayerTracker;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import org.joml.Vector2f;

@VersionGate(min = 33)
public abstract class MapElement {
   private final String field1;
   protected final DungeonMapOverlay field2;
   private final AnimatedValue field3;
   private boolean field4;
   private float field5 = 0.0F;
   private final Vector2f field6 = new Vector2f();

   public MapElement(String text, DungeonMapOverlay holograms3_22) {
      this.field1 = text;
      this.field2 = holograms3_22;
      this.field3 = new AnimatedValue(0.0, AnimatedValue.Type.SIN_IN_OUT);
      this.field4 = false;
   }

   public abstract void method1(MixinHelper_4 mixinhelper_41, BettermapSettings holograms_92, DungeonStateTracker holograms2_53, float value4, float value5, MarkerModel<?> markers6);

   public abstract void method2(MixinHelper_4 mixinhelper_41, BettermapSettings holograms_92, DungeonStateTracker holograms2_53, float value4, float value5, MarkerModel<?> markers6);

   public abstract boolean method3(BettermapSettings holograms_91, DungeonStateTracker holograms2_52);

   public boolean method4() {
      return this.field4;
   }

   public float method5() {
      return (float)this.field3.getValue();
   }

   public float method6() {
      if (this.field5 == 0.0F) {
         this.field5 = Ref.method10().bridge$getStringWidth(this.field1);
      }

      return this.field5 * 0.75F + 5.0F;
   }

   public String getName() {
      return this.field1;
   }

   public void method7(BettermapSettings holograms_91, DungeonStateTracker holograms2_52) {
      if (this.method3(holograms_91, holograms2_52)) {
         this.field3.animateTo(10.0, 500L);
         if (!this.field4) {
            this.field4 = true;
            this.field2.method22(this);
         }
      } else {
         this.field3.animateTo(0.0, 500L);
         if (this.field4) {
            this.field4 = false;
            this.field2.method23();
         }
      }
   }

   protected void method8(
      MixinHelper_4 mixinhelper_41, BettermapSettings holograms_92, DungeonStateTracker holograms2_53, float value4, float value5, MarkerModel<?> markers6, float value, float value8, MapRenderCallback holograms6$extension9
   ) {
      mixinhelper_41.push();
      DungeonPlayerTracker holograms4updater10 = holograms2_53.method29();
      float value11 = this.field2.field8;
      if (!holograms_92.method33()) {
         if (holograms_92.method34()) {
            mixinhelper_41.method38((value4 + 50.0F) * value11, (value5 + 50.0F) * value11, 0.0F);
            markers6 = markers6.method15(value4 + 50.0F, value5 + 50.0F);
            mixinhelper_41.scale(holograms_92.method50(), holograms_92.method50(), 1.0F);
            markers6 = markers6.method17(holograms_92.method50());
            mixinhelper_41.method38(-(value4 + 50.0F) * value11, -(value5 + 50.0F) * value11, 0.0F);
            markers6 = markers6.method14(value4 + 50.0F, value5 + 50.0F);
            this.field6.set(50.0F - value, 50.0F - value8);
            mixinhelper_41.method38((50.0F - value) * value11, (50.0F - value8) * value11, 0.0F);
            markers6 = markers6.method14(value - 50.0F, value8 - 50.0F);
         }

         holograms6$extension9.run(value4, value5, markers6);
      } else {
         mixinhelper_41.method38((value4 + 50.0F) * value11, (value5 + 50.0F) * value11, 0.0F);
         markers6 = markers6.method15(value4 + 50.0F, value5 + 50.0F);
         mixinhelper_41.scale(holograms_92.method50(), holograms_92.method50(), 1.0F);
         markers6 = markers6.method17(holograms_92.method50());
         mixinhelper_41.method42(-holograms4updater10.method14());
         float value12 = markers6.method12();
         float value13 = markers6.method13();
         float value14 = (float)Math.sin(holograms4updater10.method14() / 180.0F * Math.PI);
         float value15 = (float)Math.cos(holograms4updater10.method14() / 180.0F * Math.PI);
         markers6 = new Data2(value15 * value12 - value14 * value13, value14 * value12 + value15 * value13);
         if (holograms_92.method34()) {
            this.field6.set(50.0F - value, 50.0F - value8);
            mixinhelper_41.method38((50.0F - value) * value11, (50.0F - value8) * value11, 0.0F);
            markers6 = markers6.method14(value - 50.0F, value8 - 50.0F);
         }

         holograms6$extension9.run(-50.0F, -50.0F, markers6);
      }

      mixinhelper_41.pop();
   }

   protected boolean method9(BettermapSettings holograms_91, DungeonStateTracker holograms2_52, float value, float value4, float value5, float value2) {
      value5 += this.field6.x;
      value2 += this.field6.y;
      DungeonPlayerTracker holograms4updater7 = holograms2_52.method29();
      if (holograms_91.method33()) {
         float value8 = value5;
         float value9 = value2;
         float value10 = (float)Math.sin(-holograms4updater7.method14() / 180.0F * Math.PI);
         float value11 = (float)Math.cos(-holograms4updater7.method14() / 180.0F * Math.PI);
         value5 = value11 * value8 - value10 * value9;
         value2 = value10 * value8 + value11 * value9;
      }

      if (holograms_91.method34() || holograms_91.method33()) {
         value5 *= holograms_91.method50();
         value2 *= holograms_91.method50();
      }

      return value5 > value - holograms_91.method37() && value5 < value + 100.0F + holograms_91.method37() && value2 > value4 - holograms_91.method37() && value2 < value4 + 100.0F + holograms_91.method37();
   }
}
