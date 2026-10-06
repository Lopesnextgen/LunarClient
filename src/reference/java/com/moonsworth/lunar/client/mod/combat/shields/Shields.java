package com.moonsworth.lunar.client.mod.combat.shields;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ItemStackRenderStateBridge;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.minecraft.EntityEquipmentSlotBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.event.entity.EventEntityStatus;
import com.moonsworth.lunar.client.event.combat.EventPreAttackEntity;
import com.moonsworth.lunar.client.event.mixin.fishing.EventPlaySound;
import com.moonsworth.lunar.client.event.mixin.fishing.EventExplosion;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.driver.core.DualMarkerScreenLegacy;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.util.collection.MutableValue;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.config.Config;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.Generated;

public class Shields extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("enableShieldColors").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field9 = (ToggleOption)OptionFactory.method7("animateColorTransition").method31();
   private final ColorOption field10 = (ColorOption)((Data)OptionFactory.method8("shieldActiveColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16711936))
      .method16()
      .method31();
   private final ColorOption field11 = (ColorOption)((Data)OptionFactory.method8("shieldInactiveColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-65536))
      .method16()
      .method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("shieldInactiveSound").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("shieldUseSound").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("ignore5Ticks").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("betterBlocking").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final Map<UUID, MutableValue<Integer>> field16 = new HashMap<>();
   private final Map<UUID, MutableValue<Integer>> field17 = new HashMap<>();
   private int field18 = -1;

   public Shields() {
      super(false);
      this.method9(ModTraits.field18, arg0 -> arg0.method11(Config.field6));
      this.handle(EventPlaySound.class, this::method4);
      this.handle(EventTick.class, this::method5);
      if (Ref.MC_VERSION < 29) {
         this.handle(EventEntityStatus.class, this::method3);
         this.handle(EventPreAttackEntity.class, this::method6);
         this.handle(EventExplosion.class, this::method7);
      }
   }

   public String getId() {
      return "SHIELDS";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field3}).method3(new String[]{"xb4d", "Walksy"}).method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         this.field8, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field10, this.field11, this.field9})
      );
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field12, this.field13}))
         .method6(28);
      lightingextension231.method9(new ClientOption[]{this.field14, this.field15});
   }

   private void method3(EventEntityStatus highlightimpl111) {
      if (highlightimpl111.method1() != Ref.method7() && highlightimpl111.method1() instanceof Bridge6_10 bridge6_102 && bridge6_102.bridge$isBlocking()) {
         if (highlightimpl111.method2() == 30 || highlightimpl111.method2() == 29) {
            int number5 = highlightimpl111.method2() == 30 ? 100 : 5;
            if (number5 != 5 || !(Boolean)this.field14.get()) {
               MutableValue threadmoduledump264 = this.field16.computeIfAbsent(bridge6_102.bridge$getUniqueID(), arg0 -> new MutableValue(0));
               threadmoduledump264.value = Math.max((Integer)threadmoduledump264.value, number5);
            }
         }

         if ((Boolean)this.field12.get() && highlightimpl111.method2() == 30) {
            this.method8("minecraft:item.shield.break", highlightimpl111.method1().bridge$getPosX(), highlightimpl111.method1().bridge$getPosY(), highlightimpl111.method1().bridge$getPosZ());
         }
      }
   }

   private void method4(EventPlaySound highlightimpl131) {
      if (Ref.method8() != null) {
         String text2 = highlightimpl131.method3().toString();
         if ("minecraft:item.shield.break".equals(text2) || "minecraft:item.shield.block".equals(text2)) {
            List list3 = this.method9(highlightimpl131.method5(), highlightimpl131.method6(), highlightimpl131.method7(), 0.5);
            if (!list3.isEmpty()) {
               int number4 = "minecraft:item.shield.break".equals(text2) ? 100 : 5;
               if (number4 != 5 || !(Boolean)this.field14.get()) {
                  MutableValue threadmoduledump265 = this.field16
                     .computeIfAbsent(((BridgeExtension)list3.get(0)).bridge$getUniqueID(), arg0 -> new MutableValue(0));
                  threadmoduledump265.value = Math.max((Integer)threadmoduledump265.value, number4);
               }
            }
         }
      }
   }

   private void method5(EventTick highlightimpl21) {
      this.field16.entrySet().removeIf(arg0 -> {
         MutableValue threadmoduledump261x = arg0.getValue();
         MutableValue threadmoduledump262 = threadmoduledump261x;
         Integer number3 = (Integer)threadmoduledump262.value;
         threadmoduledump262.value = (Integer)threadmoduledump262.value - 1;
         return (Integer)threadmoduledump261x.value <= 0;
      });
      this.field17.entrySet().removeIf(arg0 -> {
         MutableValue threadmoduledump261x = arg0.getValue();
         MutableValue threadmoduledump262 = threadmoduledump261x;
         Integer number3 = (Integer)threadmoduledump262.value;
         threadmoduledump262.value = (Integer)threadmoduledump262.value - 1;
         return (Integer)threadmoduledump261x.value <= 0;
      });
      if ((Boolean)this.field15.get() && Ref.method8() != null) {
         Ref.method8().bridge$getPlayerEntities().forEach(arg1x -> {
            if (arg1x != Ref.method7()) {
               if (arg1x.bridge$isUsingItem() && Ref.MC_VERSION > 1) {
                  ItemStackBridge bridgeextension_42 = arg1x.bridge$getEquipmentInSlot(EntityEquipmentSlotBridge.MAINHAND);
                  boolean flag3 = "minecraft:shield".equals(bridgeextension_42.bridge$getItemRegistryName());
                  if (this.method10(arg1x) && (flag3 || bridgeextension_42.bridge$getMaxItemUseDuration() == 0)) {
                     EntityEquipmentSlotBridge horsestatstype24 = flag3 ? EntityEquipmentSlotBridge.MAINHAND : EntityEquipmentSlotBridge.OFFHAND;
                     arg1x.bridge$setUseItem(arg1x.bridge$getEquipmentInSlot(horsestatstype24));
                     MutableValue threadmoduledump265 = this.field17.computeIfAbsent(arg1x.bridge$getUniqueID(), arg0 -> new MutableValue(72000));
                     arg1x.bridge$setUseItemRemaining((Integer)threadmoduledump265.value);
                  } else {
                     this.field17.remove(arg1x.bridge$getUniqueID());
                  }
               } else {
                  this.field17.remove(arg1x.bridge$getUniqueID());
               }
            }
         });
      }
   }

   private void method6(EventPreAttackEntity highlightimpl5_21) {
      if ((Boolean)this.field13.get() && Ref.method7() != null) {
         Bridge5Extension_5 bridge5extension_52 = Ref.method7();
         if (!bridge5extension_52.bridge$getEquipmentInSlot(EntityEquipmentSlotBridge.MAINHAND).bridge$getItem().bridge$isAxe()) {
            double value3 = bridge5extension_52.bridge$getPosX();
            double value5 = bridge5extension_52.bridge$getPosY();
            double value7 = bridge5extension_52.bridge$getPosZ();
            if (highlightimpl5_21.method2() instanceof Bridge6_10 bridge6_109 && bridge6_109.bridge$canShieldBeAttacked(value3, value5, value7)) {
               this.method8("minecraft:item.shield.block", highlightimpl5_21.method2().bridge$getPosX(), highlightimpl5_21.method2().bridge$getPosY(), highlightimpl5_21.method2().bridge$getPosZ());
            }
         }
      }
   }

   private void method7(EventExplosion highlightimpl161) {
      if ((Boolean)this.field13.get() && Ref.method8() != null) {
         double value2 = highlightimpl161.method2() * 2.0F;
         List list4 = this.method9(highlightimpl161.getX(), highlightimpl161.getY(), highlightimpl161.getZ(), value2 + 0.5);
         list4.stream()
            .map(arg0 -> (Bridge6_10)arg0)
            .filter(arg1x -> arg1x.bridge$canShieldBeAttacked(highlightimpl161.getX(), highlightimpl161.getY(), highlightimpl161.getZ()))
            .forEach(arg1x -> this.method8("minecraft:item.shield.block", arg1x.bridge$getPosX(), arg1x.bridge$getPosY(), arg1x.bridge$getPosZ()));
      }
   }

   private void method8(String text1, double value2, double value4, double value6) {
      Ref.method3()
         .bridge$getPlayer()
         .bridge$getWorld()
         .bridge$playSound(value2, value4, value6, text1, 1.0F, (float)(0.8F + Math.random() * 0.4F), false);
   }

   private List<BridgeExtension> method9(double value1, double value3, double value5, double value7) {
      AxisAlignedBBBridge horsestats129 = Bridge.method8().method45(value1 - value7, value3 - value7, value5 - value7, value1 + value7, value3 + value7, value5 + value7);
      return Ref.method8()
         .bridge$getEntities(
            horsestats129,
            arg1x -> arg1x != Ref.method7() && arg1x instanceof Bridge6_10 bridge6_102 && bridge6_102.bridge$isBlocking() && arg1x.bridge$isAlive()
               ? this.method10(bridge6_102)
               : false
         );
   }

   private boolean method10(Bridge6_10 bridge6_101) {
      if (Ref.MC_VERSION < 5) {
         return false;
      }

      ItemStackRenderStateBridge mixinhelper_142 = bridge6_101.bridge$getMainHandItemRenderState();
      ItemStackRenderStateBridge mixinhelper_143 = bridge6_101.bridge$getOffHandItemRenderState();
      return "minecraft:shield".equals(mixinhelper_142.bridge$getItemRegistryName()) || "minecraft:shield".equals(mixinhelper_143.bridge$getItemRegistryName());
   }

   public int method11(UUID uuid1) {
      if (Ref.method7() != null && (Boolean)this.field8.get() && !Ref.method7().bridge$getUniqueID().equals(uuid1)) {
         GuiScreenBridge bridge5extension62 = Ref.method3().bridge$getCurrentScreenOrRewind();
         if (bridge5extension62 != null && bridge5extension62.method1(DualMarkerScreenLegacy.class)) {
            return -1;
         }

         boolean flag3 = this.field16.containsKey(uuid1);
         float value4 = flag3 ? 1.0F : 0.0F;
         if (flag3 && (Boolean)this.field9.get()) {
            value4 = Math.min(1.0F, Math.max(0.3F, ((Integer)this.field16.get(uuid1).value).intValue() / 50.0F));
         }

         return ColorUtils.method35(this.field11.method14(0.0F), this.field10.method14(0.0F), value4);
      } else {
         return -1;
      }
   }

   @Generated
   public ToggleOption method13() {
      return this.field8;
   }

   @Generated
   public int method14() {
      return this.field18;
   }

   @Generated
   public void method14(int number1) {
      this.field18 = number1;
   }
}
