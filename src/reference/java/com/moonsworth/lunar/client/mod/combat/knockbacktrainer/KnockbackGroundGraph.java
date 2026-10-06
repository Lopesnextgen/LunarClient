package com.moonsworth.lunar.client.mod.combat.knockbacktrainer;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.framework.feature.knockbacktrainer.KnockbackEvent;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.DoubleOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import lombok.Generated;

public class KnockbackGroundGraph extends AbstractFeature {
   private static final int field8 = 200;
   private final KnockbackGroundGraph.Data field9 = new KnockbackGroundGraph.Data(200);
   private final DoubleOption field10 = (DoubleOption)((com.moonsworth.lunar.client.config.option.DoubleOption.Data)((com.moonsworth.lunar.client.config.option.DoubleOption.Data)((com.moonsworth.lunar.client.config.option.DoubleOption.Data)OptionFactory.method1(
                  "groundScanCap"
               )
               .OIRHOOIICOCIOOHICRRRICORIHHIHC(2.0))
            .method8(1.0, 5.0))
         .method6(1))
      .method31();
   private final ColorOption field11 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "graphColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11141291))
      .method31();
   private final ColorOption field12 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "graphHitColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-43691))
      .method31();
   private final ColorOption field13 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "graphBackgroundColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1157627904))
      .method31();
   private final ColorOption field14 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "graphBorderColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1358954495))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "graphShowHits"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final IntegerOption field16 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "graphWidth"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(80))
         .method7(40, 200))
      .method31();
   private final IntegerOption field17 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "graphHeight"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(40))
         .method7(20, 120))
      .method31();

   public KnockbackGroundGraph(KnockbackTrainer knockbacktrainer1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(knockbacktrainer1));
      this.method2(ModTraits.field1, new KnockbackGroundGraph.KnockbackGraphHud());
      this.method14(EventTick.class, this::method13);
      this.method14(KnockbackEvent.class, this.field9::method3);
      this.method14(EventWorldChange.class, this.field9::reset);
      this.method50(this.field9::reset);
      this.method51(this.field9::reset);
   }

   private void method13() {
      Bridge5Extension_5 bridge5extension_51 = Ref.method7();
      WorldBridgeExtension itemcounter6extension2 = Ref.method8();
      if (bridge5extension_51 != null && itemcounter6extension2 != null) {
         double value3 = !bridge5extension_51.bridge$isFlying() && !bridge5extension_51.bridge$isRiding() && !bridge5extension_51.bridge$isInWater()
            ? this.method2(bridge5extension_51, itemcounter6extension2, (Double)this.field10.get())
            : 0.0;
         this.field9.method1(value3);
      }
   }

   private double method2(Bridge5Extension_5 bridge5extension_51, Itemcounter6 itemcounter62, double value3) {
      AxisAlignedBBBridge horsestats125 = bridge5extension_51.bridge$getBoundingBox();
      double value6 = horsestats125.bridge$getMinY();
      AxisAlignedBBBridge horsestats128 = AxisAlignedBBBridge.method2(horsestats125.bridge$getMinX(), value6 - value3, horsestats125.bridge$getMinZ(), horsestats125.bridge$getMaxX(), value6, horsestats125.bridge$getMaxZ());
      List list9 = itemcounter62.bridge$getBlockCollisionBoxes(bridge5extension_51, horsestats128);
      double value10 = value6 - value3;

      for (AxisAlignedBBBridge horsestats1213 : list9) {
         double value14 = horsestats1213.bridge$getMaxY();
         if (value14 <= value6 + 0.001 && value14 > value10) {
            value10 = value14;
         }
      }

      double value16 = value6 - value10;
      return value16 < 0.0 ? 0.0 : Math.min(value16, value3);
   }

   public String getId() {
      return "KNOCKBACK_GROUND_GRAPH";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> arg1x.method9(new ClientOption[]{this.field16, this.field17, this.field15, this.field10})
      );
      lightingextension231.method7(
         SettingsPage.COLOR,
         arg1x -> arg1x.method9(new ClientOption[]{this.field11, this.field12, this.field13, this.field14})
      );
   }

   private int method14() {
      return Math.max(1, (int)Math.round((Double)this.field10.get() * 100.0));
   }

   @Generated
   public KnockbackGroundGraph.Data method15() {
      return this.field9;
   }

   @Generated
   public DoubleOption method16() {
      return this.field10;
   }

   public static final class Data {
      private final int[] field1;
      private final boolean[] field2;
      private int head = 0;
      private int count = 0;

      private Data(int index1) {
         this.field1 = new int[index1];
         this.field2 = new boolean[index1];
      }

      private void method1(double value1) {
         this.method2((int)Math.round(value1 * 100.0));
      }

      private void method2(int number1) {
         this.field1[this.head] = number1;
         this.field2[this.head] = false;
         this.head = (this.head + 1) % this.field1.length;
         if (this.count < this.field1.length) {
            this.count++;
         }
      }

      private void method3() {
         if (this.count != 0) {
            int index1 = (this.head - 1 + this.field1.length) % this.field1.length;
            this.field2[index1] = true;
         }
      }

      public boolean method4() {
         return this.count > 0;
      }

      public int[] method5() {
         int[] items1 = new int[this.count];
         int number2 = (this.head - this.count + this.field1.length) % this.field1.length;

         for (int index3 = 0; index3 < this.count; index3++) {
            items1[index3] = this.field1[(number2 + index3) % this.field1.length];
         }

         return items1;
      }

      public boolean[] method6() {
         boolean[] items1 = new boolean[this.count];
         int number2 = (this.head - this.count + this.field1.length) % this.field1.length;

         for (int index3 = 0; index3 < this.count; index3++) {
            items1[index3] = this.field2[(number2 + index3) % this.field1.length];
         }

         return items1;
      }

      private void reset() {
         this.head = 0;
         this.count = 0;
      }
   }

   private final class KnockbackGraphHud extends HudElementBase {
      private KnockbackGraphHud() {
         super(70.0F, 70.0F, HudAnchor.TOP_LEFT);
      }

      public boolean method4(boolean flag1) {
         return flag1 ? true : Ref.method8() != null && Ref.method11() == null && KnockbackGroundGraph.this.field9.method4();
      }

      public boolean method31() {
         return false;
      }

      public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         int number5 = (Integer)KnockbackGroundGraph.this.field16.get();
         int number6 = (Integer)KnockbackGroundGraph.this.field17.get();
         this.method7(number5, number6);
         int number7 = KnockbackGroundGraph.this.method14();
         int[] items8 = flag4 ? this.method5(number7) : KnockbackGroundGraph.this.field9.method5();
         boolean[] items9 = flag4 ? this.method6(items8.length) : KnockbackGroundGraph.this.field9.method6();
         if (items8.length != 0) {
            MixinHelper_4 mixinhelper_410 = highlightimpl1.method2();
            int number11 = Math.round(value2);
            int number12 = Math.round(value3);
            int number13 = number11 + number5;
            int number14 = number12 + number6;
            mixinhelper_410.method1(number11, number12, number13, number14, KnockbackGroundGraph.this.field13.method14(0.0F));
            this.method4(mixinhelper_410, number11, number12, number13, number14, KnockbackGroundGraph.this.field14.method14(0.0F));
            int number15 = number11 + 1;
            int number16 = number12 + 1;
            int number17 = number14 - 1;
            int number18 = Math.max(1, number17 - number16);
            int number19 = Math.max(1, number5 - 2);
            int number20 = KnockbackGroundGraph.this.field11.method14(0.0F);
            int number21 = KnockbackGroundGraph.this.field12.method14(0.0F);
            boolean flag22 = (Boolean)KnockbackGroundGraph.this.field15.get();
            int number23 = Math.min(number19, items8.length);
            int number24 = items8.length - number23;

            for (int index25 = 0; index25 < number23; index25++) {
               int index26 = number24 + index25;
               int number27 = number15 + index25;
               if (flag22 && items9[index26]) {
                  mixinhelper_410.method1(number27, number16, number27 + 1, number17, number21);
               } else {
                  int number28 = items8[index26];
                  if (number28 != 0) {
                     int number29 = Math.max(0, Math.min(number28, number7));
                     int number30 = (int)Math.round((double)number29 / number7 * number18);
                     if (number30 > 0) {
                        mixinhelper_410.method1(number27, number17 - number30, number27 + 1, number17, number20);
                     }
                  }
               }
            }
         }
      }

      private void method4(MixinHelper_4 mixinhelper_41, int number2, int number3, int number4, int number5, int number6) {
         mixinhelper_41.method1(number2, number3, number4, number3 + 1, number6);
         mixinhelper_41.method1(number2, number5 - 1, number4, number5, number6);
         mixinhelper_41.method1(number2, number3, number2 + 1, number5, number6);
         mixinhelper_41.method1(number4 - 1, number3, number4, number5, number6);
      }

      private int[] method5(int number1) {
         int[] items2 = new int[60];

         for (int index3 = 0; index3 < items2.length; index3++) {
            items2[index3] = (int)(number1 * (0.5 + 0.5 * Math.sin(index3 * 0.3)));
         }

         return items2;
      }

      private boolean[] method6(int index1) {
         boolean[] items2 = new boolean[index1];

         for (int index3 = 0; index3 < index1; index3++) {
            items2[index3] = index3 % 17 == 8;
         }

         return items2;
      }
   }
}
