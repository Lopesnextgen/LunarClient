package com.moonsworth.lunar.client.mod.render.armorstatus;

import com.moonsworth.lunar.bridge.Bridge2_26;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6Extension3;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.minecraft.EntityEquipmentSlotBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.event.player.EventInventoryUpdate;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Arrays;
import java.util.Map.Entry;

public class ArmorstatusProtection extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showArmorToughness")
      .method31();
   private String field9 = "";
   private String field10 = "";

   public ArmorstatusProtection(Framework7Extension framework7extension1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(framework7extension1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.GENERAL));
      this.method2(ModTraits.field1, TypedHudRenderer.method22(0.0F, 0.0F, HudAnchor.BOTTOM_RIGHT, HudSize.method1(18, 20, 28, 34, 50, 62), arg1x -> {
         if (arg1x) {
            return Ref.MC_VERSION >= 6 && this.field8.get() ? Arrays.asList("80%", "+2.0") : "80%";
         } else {
            return Ref.MC_VERSION >= 6 && this.field8.get() && !this.field10.isEmpty() ? Arrays.asList(this.field9, this.field10) : this.field9;
         }
      }));
      this.handle(EventInventoryUpdate.class, arg1x -> this.method13());
      this.field8.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.method13());
   }

   public String getId() {
      return "ARMORSTATUS_PROTECTION_CHILD";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      super.method45(lightingextension231);
      if (Ref.MC_VERSION >= 6) {
         lightingextension231.method9(new ClientOption[]{this.field8});
      }
   }

   protected void method13() {
      Bridge5Extension_5 bridge5extension_51 = Ref.method7();
      if (bridge5extension_51 != null) {
         float value2 = 0.0F;
         if (Ref.MC_VERSION >= 6 && (Boolean)this.field8.get()) {
            for (EntityEquipmentSlotBridge horsestatstype26 : EntityEquipmentSlotBridge.armorValues()) {
               ItemStackBridge bridgeextension_47 = bridge5extension_51.bridge$getEquipmentInSlot(horsestatstype26);
               if (bridgeextension_47 != null && bridgeextension_47.bridge$getItem() instanceof Bridge6Extension3 bridge6extension39) {
                  value2 += bridge6extension39.bridge$getArmorToughness(bridgeextension_47);
               }
            }

            this.field10 = "+" + value2;
         }

         int number13 = 0;
         int number14 = 0;

         for (EntityEquipmentSlotBridge horsestatstype220 : EntityEquipmentSlotBridge.armorValues()) {
            ItemStackBridge bridgeextension_421 = bridge5extension_51.bridge$getEquipmentInSlot(horsestatstype220);
            if (bridgeextension_421 != null) {
               ItemBridge bridge6_410 = bridgeextension_421.bridge$getItem();
               if (bridge6_410.bridge$isArmor()) {
                  number13 += ((Bridge6Extension3)bridge6_410).bridge$getArmorValue(bridgeextension_421);
               }

               for (Entry entry12 : bridgeextension_421.bridge$getEnchantments().entrySet()) {
                  if (((Bridge2_26)entry12.getKey()).bridge$isProtection()) {
                     number14 = (int)(number14 + Math.floor((6 + (Integer)entry12.getValue() ^ 2) * 0.75 / 3.0));
                     break;
                  }
               }
            }
         }

         float value16 = Math.min(Math.min(number14, 25) * 0.04F * 0.75F, 0.8F);
         float value18 = 1.0F - (1.0F - number13 * 0.04F) * (1.0F - value16);
         this.field9 = String.format("%3.1f%%", value18 * 100.0F);
      }
   }
}
