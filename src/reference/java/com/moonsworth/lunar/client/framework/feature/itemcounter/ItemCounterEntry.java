package com.moonsworth.lunar.client.framework.feature.itemcounter;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.potion.PotionRegistryBridge;
import java.util.Objects;
import lombok.Generated;
import org.apache.commons.lang3.text.WordUtils;
import org.jetbrains.annotations.NotNull;

public class ItemCounterEntry {
   @NotNull
   protected final String field1;
   @NotNull
   protected final ItemStackBridge field2;
   private String field3 = null;

   public ItemCounterEntry(@NotNull String text1) {
      this.field1 = text1;
      this.field2 = Bridge.method8().method38(Bridge.method28().method22(text1));
   }

   public String method1() {
      return this.field1;
   }

   public boolean method2(ItemStackBridge bridgeextension_41) {
      return bridgeextension_41 != null && bridgeextension_41.bridge$getItem() == this.field2.bridge$getItem();
   }

   @Override
   public String toString() {
      if (this.field3 != null) {
         return this.field3;
      }

      String text1 = this.field2.bridge$getDisplayName();
      String text2 = "";
      if (text1.length() > 2 && text1.charAt(0) == 167) {
         text2 = text1.substring(0, 2);
      }

      String text3 = WordUtils.capitalizeFully(this.method1().split(":")[1].replace('_', ' '));
      return this.field3 = text2 + text3;
   }

   protected static boolean method3(ItemStackBridge bridgeextension_40, ItemStackBridge bridgeextension_41) {
      PotionRegistryBridge fog32 = Bridge.method36();
      ItemBridge bridge6_43 = bridgeextension_40.bridge$getItem();
      ItemBridge bridge6_44 = bridgeextension_41.bridge$getItem();
      if (!bridge6_43.bridge$isItemPotion() || !bridge6_44.bridge$isItemPotion()) {
         return false;
      } else {
         return bridge6_43 != bridge6_44 ? false : Objects.equals(fog32.method16(bridgeextension_40), fog32.method16(bridgeextension_41));
      }
   }

   @NotNull
   @Generated
   public ItemStackBridge method4() {
      return this.field2;
   }
}
