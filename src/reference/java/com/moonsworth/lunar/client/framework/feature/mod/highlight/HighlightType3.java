package com.moonsworth.lunar.client.framework.feature.mod.highlight;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler28;
import java.util.Locale;
import java.util.function.BiPredicate;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public enum HighlightType3 {
   WHEAT("WHEAT", "wheat", (var0, var1) -> var0 == Bridge.method34().method52()),
   CARROT("CARROT_ITEM", "carrot", (var0, var1) -> var0 == Bridge.method34().method53()),
   POTATO("POTATO_ITEM", "potato", (var0, var1) -> var0 == Bridge.method34().method54()),
   PUMPKIN("PUMPKIN", "pumpkin", (var0, var1) -> var0 == Bridge.method34().method55()),
   MELON("MELON", "melon_slice", (var0, var1) -> var0 == Bridge.method34().method56()),
   SUGAR_CANE("SUGAR_CANE", "sugar_cane", (var0, var1) -> var0 == Bridge.method34().method57()),
   COCOA_BEANS("INK_SACK:3", "cocoa_beans", (var0, var1) -> var0 == Bridge.method34().method34()),
   CACTUS("CACTUS", "cactus", (var0, var1) -> var0 == Bridge.method34().method58()),
   NETHER_WART("NETHER_STALK", "nether_wart", (var0, var1) -> var0 == Bridge.method34().method59()),
   MUSHROOM("MUSHROOM_COLLECTION", "mushroom", (var0, var1) -> var0 == Bridge.method34().method60() || var0 == Bridge.method34().method61()),
   WILD_ROSE("WILD_ROSE", "wild_rose", (var0, var1) -> var0 == Bridge.method34().method63()),
   SUNFLOWER("DOUBLE_PLANT", "sunflower", (var0, var1) -> var0 == Bridge.method34().method62() && var1.method6() == HighlightType5.DAY),
   MOONFLOWER("MOONFLOWER", "moonflower", (var0, var1) -> var0 == Bridge.method34().method62() && var1.method6() == HighlightType5.NIGHT);

   private final String apiName;
   private final String localName;
   private final BiPredicate<Bridge3_23, GuiRewindhandlersHandler28> isCrop;

   @Nullable
   public static HighlightType3 fromApi(String var0) {
      for (HighlightType3 var4 : values()) {
         if (var4.getApiName().equals(var0)) {
            return var4;
         }
      }

      return null;
   }

   @Nullable
   public static HighlightType3 fromLocal(String var0) {
      for (HighlightType3 var4 : values()) {
         if (var4.getLocalName().equals(var0)) {
            return var4;
         }
      }

      return null;
   }

   @Nullable
   public static HighlightType3 fromTab(String var0) {
      String var1 = var0.toLowerCase(Locale.ROOT).replace(" ", "_");
      return fromLocal(var0);
   }

   @Generated
   HighlightType3(String text, String var4, BiPredicate<Bridge3_23, GuiRewindhandlersHandler28> predicate) {
      this.apiName = text;
      this.localName = var4;
      this.isCrop = predicate;
   }

   @Generated
   public String getApiName() {
      return this.apiName;
   }

   @Generated
   public String getLocalName() {
      return this.localName;
   }

   @Generated
   public BiPredicate<Bridge3_23, GuiRewindhandlersHandler28> getIsCrop() {
      return this.isCrop;
   }
}
