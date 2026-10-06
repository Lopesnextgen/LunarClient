package com.moonsworth.lunar.client.cosmetics;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.BridgeType2_4;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.driver.core.gui.JsonProviderLegacy;
import com.moonsworth.lunar.client.driver.core.holograms.HologramRendererLegacy;
import com.moonsworth.lunar.client.driver.core.holograms.mixin.HologramCameraPresetLegacy;
import com.moonsworth.lunar.client.util.Annotation;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.longs.LongSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.Generated;
import org.jspecify.annotations.Nullable;

public enum CosmeticCategoryType implements JsonProviderLegacy {
   CLOAK("cloak", CosmeticType.CLOAK, HologramCameraPresetLegacy.CLOAK, "lunar"),
   HEADWEAR("headwearui", CosmeticType.HAT, HologramCameraPresetLegacy.HAT, "hat-40x40", "lunarhats"),
   HAT("hat", HEADWEAR, CosmeticType.HAT, HologramCameraPresetLegacy.HAT, "lunarhats"),
   BANDANNA("bandanna", HEADWEAR, CosmeticType.HAT, HologramCameraPresetLegacy.HAT, "bandana-28x28", "lunarbandannas"),
   GLASSES("glasses", HEADWEAR, CosmeticType.HAT, HologramCameraPresetLegacy.HAT, "lunar"),
   MASK("mask", HEADWEAR, CosmeticType.HAT, HologramCameraPresetLegacy.HAT, "mask-28x28", "lunarmasks"),
   PET("pet", CosmeticType.GECKOLIB, "lunar"),
   COMPANION("companion", CosmeticType.GECKOLIB, "lunar"),
   BODYWEAR_UI("bodywearui", CosmeticType.BODYWEAR, HologramCameraPresetLegacy.BUST, "bodywear-40x40", "lunar"),
   BACKPACK("backpack", BODYWEAR_UI, CosmeticType.BODYWEAR, HologramCameraPresetLegacy.CLOAK, "lunar"),
   BODYWEAR("bodywear", BODYWEAR_UI, CosmeticType.BODYWEAR, HologramCameraPresetLegacy.BUST, "lunar"),
   NECKWEAR("neckwear", BODYWEAR_UI, CosmeticType.BODYWEAR, HologramCameraPresetLegacy.BUST, "necklace-64x64", "lunar"),
   WINGS("wings", BODYWEAR_UI, CosmeticType.WINGS, HologramCameraPresetLegacy.WING, "wings"),
   BELTS("belts", BODYWEAR_UI, CosmeticType.BODYWEAR, HologramCameraPresetLegacy.BELT, "lunar"),
   SHOES("shoes", BODYWEAR_UI, CosmeticType.BODYWEAR, HologramCameraPresetLegacy.SHOES, "lunar"),
   WRISTWEAR("wristwear", BACKPACK, CosmeticType.GECKOLIB, "lunar"),
   SHIELDS("shields", BACKPACK, CosmeticType.GECKOLIB, "lunar"),
   AURAS("auras", CosmeticType.BODYWEAR, HologramCameraPresetLegacy.DEFAULT, "lunar"),
   SUITS("suits", CosmeticType.BODYWEAR, HologramCameraPresetLegacy.SUITS, "lunar"),
   ITEM("items", CosmeticType.GECKOLIB, HologramCameraPresetLegacy.ITEM, "item-40x40", "lunar"),
   SWORD("sword", ITEM, CosmeticType.GECKOLIB, HologramCameraPresetLegacy.ITEM, "item-40x40", "lunar"),
   PICKAXE("pickaxe", ITEM, CosmeticType.GECKOLIB, HologramCameraPresetLegacy.ITEM, "item-40x40", "lunar"),
   AXE("axe", ITEM, CosmeticType.GECKOLIB, HologramCameraPresetLegacy.ITEM, "item-40x40", "lunar"),
   SHOVEL("shovel", ITEM, CosmeticType.GECKOLIB, HologramCameraPresetLegacy.ITEM, "item-40x40", "lunar"),
   HOE("hoe", ITEM, CosmeticType.GECKOLIB, HologramCameraPresetLegacy.ITEM, "item-40x40", "lunar"),
   HAND("hand", ITEM, CosmeticType.GECKOLIB, HologramCameraPresetLegacy.ITEM, "item-40x40", "lunar"),
   DEV_COSMETICS("devCosmetics", CosmeticType.GECKOLIB, HologramCameraPresetLegacy.DEFAULT, "lunar");

   private static final Set<CosmeticCategoryType> HELD_ITEM_COSMETICS = EnumSet.of(SHIELDS, ITEM, SWORD, PICKAXE, AXE, SHOVEL, HOE, HAND);
   private final String name;
   private final CosmeticCategoryType parent;
   private final CosmeticType renderAs;
   private final @Nullable HologramCameraPresetLegacy display;
   private final String iconPath;
   private final String storeLinkName;
   private final ToggleOption showCosmetic;
   private final List<CosmeticCategoryType> children = new ArrayList<>(0);

   CosmeticCategoryType(
      @Annotation(method1 = Annotation.Type.SETTING) String var3,
      CosmeticCategoryType var4,
      CosmeticType var5,
      @Nullable HologramCameraPresetLegacy var6,
      String var7,
      String var8
   ) {
      this.name = var3;
      this.parent = var4;
      this.renderAs = var5;
      this.display = var6;
      this.iconPath = "icons/cosmetics/" + var7 + ".png";
      this.showCosmetic = (ToggleOption)((ToggleOption.ToggleOptionBuilder)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
                  "show" + var3.substring(0, 1).toUpperCase() + var3.substring(1)
               )
               .method4(true))
            .method16(DriverFieldTypeLegacy.TOGGLE))
         .method2(() -> HologramRendererLegacy.field1)
         .method31();
      this.showCosmetic.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var0 -> {
         if (ThreadModuleDump63.method4().method53() != null) {
            ThreadModuleDump63.method4().method53().method53();
         }
      });
      this.storeLinkName = var8;
      if (var4 != null) {
         var4.children.add(this);
      }

      if (var3.equals("companion")) {
         this.showCosmetic.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var0 -> ThreadModuleDump63.method4().method88().method10());
      }
   }

   CosmeticCategoryType(@Annotation(method1 = Annotation.Type.SETTING) String var3, CosmeticCategoryType var4, CosmeticType var5, String var6, String var7) {
      this(var3, var4, var5, null, var6, var7);
   }

   CosmeticCategoryType(@Annotation(method1 = Annotation.Type.SETTING) String var3, CosmeticCategoryType var4, CosmeticType var5, String var6) {
      this(var3, var4, var5, null, var3 + "-40x40", var6);
   }

   CosmeticCategoryType(@Annotation(method1 = Annotation.Type.SETTING) String var3, CosmeticType var4, String var5, String var6) {
      this(var3, null, var4, null, var5, var6);
   }

   CosmeticCategoryType(@Annotation(method1 = Annotation.Type.SETTING) String var3, CosmeticType var4, String var5) {
      this(var3, null, var4, null, var3 + "-40x40", var5);
   }

   CosmeticCategoryType(@Annotation(method1 = Annotation.Type.SETTING) String var3, CosmeticType var4, HologramCameraPresetLegacy var5, String var6, String var7) {
      this(var3, null, var4, var5, var6, var7);
   }

   CosmeticCategoryType(@Annotation(method1 = Annotation.Type.SETTING) String var3, CosmeticCategoryType var4, CosmeticType var5, HologramCameraPresetLegacy var6, String var7) {
      this(var3, var4, var5, var6, var3 + "-40x40", var7);
   }

   CosmeticCategoryType(@Annotation(method1 = Annotation.Type.SETTING) String var3, CosmeticType var4, HologramCameraPresetLegacy var5, String var6) {
      this(var3, null, var4, var5, var6);
   }

   public String getDisplayName() {
      return ThreadModuleDump63.method4().method67().method2("settings", this.name);
   }

   public static Optional<CosmeticCategoryType> from(String var0) {
      return var0.equalsIgnoreCase("dragon_wings") ? Optional.of(WINGS) : Arrays.stream(values()).filter(var1 -> var1.name.equalsIgnoreCase(var0)).findFirst();
   }

   public boolean isHeldItemCosmetic() {
      return HELD_ITEM_COSMETICS.contains(this);
   }

   public boolean canShowCosmetic() {
      return this.parent != null && !this.parent.canShowCosmetic() ? false : this.showCosmetic.get();
   }

   @Override
   public JsonElement provide() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("id", this.name);
      var1.addProperty("name", this.getDisplayName());
      var1.addProperty("icon", this.iconPath);
      if (this.display != null) {
         var1.addProperty("display", this.display.name());
      }

      var1.addProperty("storeLinkName", this.storeLinkName);
      var1.addProperty("isShown", this.showCosmetic.get());
      if (this.parent != null) {
         var1.addProperty("parent", this.parent.name);
      }

      JsonArray var2 = new JsonArray();
      this.children.forEach(var1x -> var2.add(var1x.provide()));
      var1.add("children", var2);
      if (ThreadModuleDump63.method4() != null && ThreadModuleDump63.method4().method53() != null) {
         List var3 = ThreadModuleDump63.method4()
            .method53()
            .IIORHHIRHIORHRCCCOICCRCHRRCCRH()
            .stream()
            .filter(var1x -> var1x.method10() == this || this.children.contains(var1x.method10()))
            .toList();
         var1.addProperty("amountOwned", var3.size());
         var1.addProperty(
            "hasNewItems",
            var3.stream().anyMatch(var0 -> !((LongSet)ThreadModuleDump63.method4().method41().method9().method21().get()).contains(var0.method9()))
         );
      }

      return var1;
   }

   public BridgeType2_4 getItemForCosmeticType() {
      return switch (this) {
         case SWORD -> BridgeType2_4.SWORD;
         case AXE -> BridgeType2_4.AXE;
         case PICKAXE -> BridgeType2_4.PICKAXE;
         case SHOVEL -> BridgeType2_4.SHOVEL;
         case HOE -> BridgeType2_4.HOE;
         default -> null;
      };
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public CosmeticCategoryType getParent() {
      return this.parent;
   }

   @Generated
   public CosmeticType getRenderAs() {
      return this.renderAs;
   }

   @Generated
   public @Nullable HologramCameraPresetLegacy getDisplay() {
      return this.display;
   }

   @Generated
   public String getIconPath() {
      return this.iconPath;
   }

   @Generated
   public String getStoreLinkName() {
      return this.storeLinkName;
   }

   @Generated
   public ToggleOption getShowCosmetic() {
      return this.showCosmetic;
   }

   @Generated
   public List<CosmeticCategoryType> getChildren() {
      return this.children;
   }
}
