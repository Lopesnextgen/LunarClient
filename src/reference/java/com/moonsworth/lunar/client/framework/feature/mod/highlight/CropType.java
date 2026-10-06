package com.moonsworth.lunar.client.framework.feature.mod.highlight;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockTimeListener;
import java.util.Locale;
import java.util.function.BiPredicate;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public enum CropType {
   WHEAT("WHEAT", "wheat", (arg0, arg1) -> arg0 == Bridge.method34().method52()),
   CARROT("CARROT_ITEM", "carrot", (arg0, arg1) -> arg0 == Bridge.method34().method53()),
   POTATO("POTATO_ITEM", "potato", (arg0, arg1) -> arg0 == Bridge.method34().method54()),
   PUMPKIN("PUMPKIN", "pumpkin", (arg0, arg1) -> arg0 == Bridge.method34().method55()),
   MELON("MELON", "melon_slice", (arg0, arg1) -> arg0 == Bridge.method34().method56()),
   SUGAR_CANE("SUGAR_CANE", "sugar_cane", (arg0, arg1) -> arg0 == Bridge.method34().method57()),
   COCOA_BEANS("INK_SACK:3", "cocoa_beans", (arg0, arg1) -> arg0 == Bridge.method34().method34()),
   CACTUS("CACTUS", "cactus", (arg0, arg1) -> arg0 == Bridge.method34().method58()),
   NETHER_WART("NETHER_STALK", "nether_wart", (arg0, arg1) -> arg0 == Bridge.method34().method59()),
   MUSHROOM("MUSHROOM_COLLECTION", "mushroom", (arg0, arg1) -> arg0 == Bridge.method34().method60() || arg0 == Bridge.method34().method61()),
   WILD_ROSE("WILD_ROSE", "wild_rose", (arg0, arg1) -> arg0 == Bridge.method34().method63()),
   SUNFLOWER("DOUBLE_PLANT", "sunflower", (arg0, arg1) -> arg0 == Bridge.method34().method62() && arg1.method6() == SkyblockWeather.DAY),
   MOONFLOWER("MOONFLOWER", "moonflower", (arg0, arg1) -> arg0 == Bridge.method34().method62() && arg1.method6() == SkyblockWeather.NIGHT);

   private final String apiName;
   private final String localName;
   private final BiPredicate<Bridge3_23, SkyblockTimeListener> isCrop;

   @Nullable
   public static CropType fromApi(String text0) {
      for (CropType highlighttype34 : values()) {
         if (highlighttype34.getApiName().equals(text0)) {
            return highlighttype34;
         }
      }

      return null;
   }

   @Nullable
   public static CropType fromLocal(String text0) {
      for (CropType highlighttype34 : values()) {
         if (highlighttype34.getLocalName().equals(text0)) {
            return highlighttype34;
         }
      }

      return null;
   }

   @Nullable
   public static CropType fromTab(String text0) {
      String text1 = text0.toLowerCase(Locale.ROOT).replace(" ", "_");
      return fromLocal(text0);
   }

   @Generated
   CropType(String text, String text2, BiPredicate<Bridge3_23, SkyblockTimeListener> bipredicate5) {
      this.apiName = text;
      this.localName = text2;
      this.isCrop = bipredicate5;
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
   public BiPredicate<Bridge3_23, SkyblockTimeListener> getIsCrop() {
      return this.isCrop;
   }
}
