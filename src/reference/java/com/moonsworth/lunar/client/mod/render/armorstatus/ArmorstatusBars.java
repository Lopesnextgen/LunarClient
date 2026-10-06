package com.moonsworth.lunar.client.mod.render.armorstatus;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6Extension3;
import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.MathHelperBridge;
import com.moonsworth.lunar.bridge.horsestats.EquipmentSlotBridge;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework2;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.armorstatus.armorstatusbarschild.Armorstatusbarschild;
import com.moonsworth.lunar.client.framework.feature.armorstatus.armorstatusbarschild.Armorstatusbarschild2;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.highlight.AlertUpdateEvent;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Arrays;
import org.jetbrains.annotations.Nullable;

public class ArmorstatusBars extends AbstractFeature {
   private static final EquipmentSlotBridge[] field8 = new EquipmentSlotBridge[]{
      EquipmentSlotBridge.HEAD, EquipmentSlotBridge.CHEST, EquipmentSlotBridge.LEGS, EquipmentSlotBridge.FEET
   };
   public final ToggleOption field9 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "smartArmorColors"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   public final ToggleOption field10 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "showGlint"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   public final ToggleOption field11 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "lowDurabilityIndicator"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   public final IntegerOption field12 = (IntegerOption)((IntegerOption.Data)((IntegerOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
               "lowDurabilityThreshold"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(10))
         .method7(1, 100))
      .method31();
   private final Armorstatusbarschild field13 = new Armorstatusbarschild();
   private final Armorstatusbarschild2 field14 = new Armorstatusbarschild2();
   private final ArmorstatusBars.Data[] field15 = new ArmorstatusBars.Data[20];

   public ArmorstatusBars(Framework7Extension var1) {
      super(false);
      this.method2(Framework.field16, Framework4.method3(var1));
      this.method2(Framework.field17, Framework2.method2(SettingsPage.GENERAL));
      this.method1(AlertUpdateEvent.class, () -> {
         this.field13.reset();
         this.field14.reset();
      });
      this.method1(EventClientTick.class, this::method13);
      this.field9.method9(this.field13::reset);
   }

   @Override
   public String getId() {
      return "ARMORSTATUS_BARS_CHILD";
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field9, this.field10});
      var1.method2(this.field11, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field12}));
   }

   public boolean method2(MixinHelper_4 var1, int var2, int var3, int var4) {
      if (this.field15[0] == null) {
         return false;
      }

      int var5 = var2 * 2;
      ArmorstatusBars.Data var6 = this.field15[var5];
      ArmorstatusBars.Data var7 = this.field15[var5 + 1];
      if (var6 != null && !var6.equals(var7)) {
         this.method3(var1, var3, var4, Armorstatusbarschild2.Type.LEFT, var6);
         this.method3(var1, var3, var4, Armorstatusbarschild2.Type.RIGHT, var7);
      } else {
         this.method3(var1, var3, var4, Armorstatusbarschild2.Type.WHOLE, var6);
      }

      return true;
   }

   private void method3(MixinHelper_4 var1, int var2, int var3, Armorstatusbarschild2.Type var4, ArmorstatusBars.Data var5) {
      this.field14.method3(var1, var2, var3, var4);
      if (var5 != null) {
         int var6 = var5.method1();
         float var7 = 1.0F;
         if (var5.method3()) {
            double var8 = System.currentTimeMillis() % 1200L / 1200.0 * Math.PI * 2.0;
            var7 = MathHelperBridge.method2(var8) * 0.5F + 0.5F;
            int var10 = (int)((var6 >> 24 & 0xFF) * var7);
            var6 = var10 << 24 | var6 & 16777215;
         }

         this.field14.method2(var1, var2, var3, var4, var6);
         if (var5.method2()) {
            this.field14.method5(var1, var2, var3, var4, var7);
         }
      }
   }

   private void method13() {
      Arrays.fill(this.field15, null);
      Bridge5Extension_5 var1 = ThreadModuleDump63.method7();
      if (var1 != null) {
         boolean var2 = this.field9.get();
         boolean var3 = this.field10.get();
         boolean var4 = this.field11.get();
         int var5 = this.field12.get();
         int var6 = Math.min(this.field15.length, var1.bridge$getTotalArmorValue());
         int var7 = 0;

         for (EquipmentSlotBridge var11 : field8) {
            ItemStackBridge var12 = var1.bridge$getEquipmentInSlot(var11);
            if (var12 != null) {
               Bridge6_4 var13 = var12.bridge$getItem();
               if (var13 != null && var13.bridge$isArmor()) {
                  Bridge6Extension3 var14 = (Bridge6Extension3)var13;
                  int var15 = var14.bridge$getArmorValue(var12);
                  if (var15 > 0) {
                     ArmorstatusBars.Data var16 = new ArmorstatusBars.Data(
                        this.field13.method1(var14.bridge$getArmorMaterial(), var2),
                        var3 && var12.bridge$isItemEnchanted(),
                        var4 && Armorstatus.method7(var12, var5)
                     );

                     for (int var17 = var15; var17 > 0 && var7 < var6; var17--) {
                        this.field15[var7++] = var16;
                     }
                  }
               }
            }
         }

         ArmorstatusBars.Data var18 = new ArmorstatusBars.Data(-1, false, false);

         while (var7 < var6) {
            this.field15[var7++] = var18;
         }
      }
   }

   @Nullable
   public static ArmorstatusBars method14() {
      if (ThreadModuleDump63.method4() != null && ThreadModuleDump63.method4().method40() != null) {
         ArmorstatusBars var0 = ThreadModuleDump63.method4().method40().method42().method14();
         return var0.isEnabled() ? var0 : null;
      } else {
         return null;
      }
   }

   private class Data {
      private final int field1;
      private final boolean field2;
      private final boolean field3;

      private Data(int var1, boolean var2, boolean var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      public int method1() {
         return this.field1;
      }

      public boolean method2() {
         return this.field2;
      }

      public boolean method3() {
         return this.field3;
      }
   }
}
