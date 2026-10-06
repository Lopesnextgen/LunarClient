package com.moonsworth.lunar.client.framework.feature.itemcounter;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.fog.Fog3;
import java.util.Objects;
import lombok.Generated;
import org.apache.commons.lang3.text.WordUtils;
import org.jetbrains.annotations.NotNull;

public class Itemcounter_2 {
   @NotNull
   protected final String field1;
   @NotNull
   protected final ItemStackBridge field2;
   private String field3 = null;

   public Itemcounter_2(@NotNull String var1) {
      this.field1 = var1;
      this.field2 = Bridge.method8().method38(Bridge.method28().method22(var1));
   }

   public String method1() {
      return this.field1;
   }

   public boolean method2(ItemStackBridge var1) {
      return var1 != null && var1.bridge$getItem() == this.field2.bridge$getItem();
   }

   @Override
   public String toString() {
      if (this.field3 != null) {
         return this.field3;
      }

      String var1 = this.field2.bridge$getDisplayName();
      String var2 = "";
      if (var1.length() > 2 && var1.charAt(0) == 167) {
         var2 = var1.substring(0, 2);
      }

      String var3 = WordUtils.capitalizeFully(this.method1().split(":")[1].replace('_', ' '));
      return this.field3 = var2 + var3;
   }

   protected static boolean method3(ItemStackBridge var0, ItemStackBridge var1) {
      Fog3 var2 = Bridge.method36();
      Bridge6_4 var3 = var0.bridge$getItem();
      Bridge6_4 var4 = var1.bridge$getItem();
      if (!var3.bridge$isItemPotion() || !var4.bridge$isItemPotion()) {
         return false;
      } else {
         return var3 != var4 ? false : Objects.equals(var2.method16(var0), var2.method16(var1));
      }
   }

   @NotNull
   @Generated
   public ItemStackBridge method4() {
      return this.field2;
   }
}
