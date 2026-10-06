package com.moonsworth.lunar.client.framework.feature.waypoints;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.driver.PhosphorIconLegacy;
import com.moonsworth.lunar.client.driver.core.gui.Gui;
import lombok.Generated;

public class GuiLoader implements Gui {
   public static GuiLoader field1 = new GuiLoader();
   private final ColorOption field2 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "color"
         )
         .method4(0))
      .method31();
   private boolean showBeam = true;
   private PhosphorIconLegacy field3 = PhosphorIconLegacy.PI_MAP_PIN_SOLID;
   private boolean highlightBlock = false;
   private boolean showText = true;
   private boolean showDistance = true;
   private Float field4 = null;
   private GuiLoader.Data field5 = null;

   public GuiLoader(boolean var1, PhosphorIconLegacy var2, boolean var3, boolean var4, boolean var5) {
      this.showBeam = var1;
      this.field3 = var2;
      this.highlightBlock = var3;
      this.showText = var4;
      this.showDistance = var5;
   }

   public void method1(GuiLoader var1) {
      this.showBeam = var1.showBeam;
      this.field3 = var1.field3;
      this.highlightBlock = var1.highlightBlock;
      this.showText = var1.showText;
      this.showDistance = var1.showDistance;
      this.field2.method11(var1.field2);
   }

   public void load(JsonObject var1) {
      if (var1.has("color")) {
         this.field2.load(var1);
      }

      if (var1.has("showBeam")) {
         this.showBeam = var1.get("showBeam").getAsBoolean();
      }

      if (var1.has("icon")) {
         this.field3 = PhosphorIconLegacy.valueOf(var1.get("icon").getAsString());
      }

      if (var1.has("highlightBlock")) {
         this.highlightBlock = var1.get("highlightBlock").getAsBoolean();
      }

      if (var1.has("showText")) {
         this.showText = var1.get("showText").getAsBoolean();
      }

      if (var1.has("showDistance")) {
         this.showDistance = var1.get("showDistance").getAsBoolean();
      }
   }

   public JsonElement method128() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("showBeam", this.showBeam);
      var1.addProperty("showText", this.showText);
      var1.addProperty("showDistance", this.showDistance);
      var1.addProperty("highlightBlock", this.highlightBlock);
      var1.addProperty("icon", this.field3.ordinal());
      OptionDataProvider var2 = (OptionDataProvider)this.field2.method7(OptionTraits.field10);
      if (var2 != null) {
         var1.add("color", var2.provide());
      }

      return var1;
   }

   public void method3(JsonObject var1) {
      var1.addProperty("showBeam", this.showBeam);
      var1.addProperty("showText", this.showText);
      var1.addProperty("showDistance", this.showDistance);
      var1.addProperty("highlightBlock", this.highlightBlock);
      var1.addProperty("icon", this.field3.name());
      this.field2.load(var1);
   }

   @Generated
   public ColorOption method4() {
      return this.field2;
   }

   @Generated
   public boolean isShowBeam() {
      return this.showBeam;
   }

   @Generated
   public PhosphorIconLegacy getIcon() {
      return this.field3;
   }

   @Generated
   public boolean isHighlightBlock() {
      return this.highlightBlock;
   }

   @Generated
   public boolean isShowText() {
      return this.showText;
   }

   @Generated
   public boolean isShowDistance() {
      return this.showDistance;
   }

   @Generated
   public Float method5() {
      return this.field4;
   }

   @Generated
   public GuiLoader.Data method6() {
      return this.field5;
   }

   @Generated
   public void setShowBeam(boolean var1) {
      this.showBeam = var1;
   }

   @Generated
   public void method8(PhosphorIconLegacy var1) {
      this.field3 = var1;
   }

   @Generated
   public void setHighlightBlock(boolean var1) {
      this.highlightBlock = var1;
   }

   @Generated
   public void setShowText(boolean var1) {
      this.showText = var1;
   }

   @Generated
   public void setShowDistance(boolean var1) {
      this.showDistance = var1;
   }

   @Generated
   public void method12(Float var1) {
      this.field4 = var1;
   }

   @Generated
   public void method13(GuiLoader.Data var1) {
      this.field5 = var1;
   }

   @Generated
   public GuiLoader(boolean var1, PhosphorIconLegacy var2, boolean var3, boolean var4, boolean var5, Float var6, GuiLoader.Data var7) {
      this.showBeam = var1;
      this.field3 = var2;
      this.highlightBlock = var3;
      this.showText = var4;
      this.showDistance = var5;
      this.field4 = var6;
      this.field5 = var7;
   }

   @Generated
   public GuiLoader() {
   }

   public static class Data {
      private boolean onlyShowTextWhenLookingNear;
      private boolean showIcons;
      private float textIconScale;
      private float labelScale;
      private float boxPadding;
      boolean boxBorders;
      boolean textShadow;

      @Generated
      public boolean isOnlyShowTextWhenLookingNear() {
         return this.onlyShowTextWhenLookingNear;
      }

      @Generated
      public boolean isShowIcons() {
         return this.showIcons;
      }

      @Generated
      public float getTextIconScale() {
         return this.textIconScale;
      }

      @Generated
      public float getLabelScale() {
         return this.labelScale;
      }

      @Generated
      public float getBoxPadding() {
         return this.boxPadding;
      }

      @Generated
      public boolean isBoxBorders() {
         return this.boxBorders;
      }

      @Generated
      public boolean isTextShadow() {
         return this.textShadow;
      }

      @Generated
      public Data(boolean var1, boolean var2, float var3, float var4, float var5, boolean var6, boolean var7) {
         this.onlyShowTextWhenLookingNear = var1;
         this.showIcons = var2;
         this.textIconScale = var3;
         this.labelScale = var4;
         this.boxPadding = var5;
         this.boxBorders = var6;
         this.textShadow = var7;
      }
   }
}
