package com.moonsworth.lunar.client.framework.feature.mod.impl.click.storageoverlay.mixin;

import com.moonsworth.lunar.client.util.Annotation;
import lombok.Generated;

public enum Gui2Extension6 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   DEFAULT("default", 0),
   SMALL("small", 1),
   NORMAL("normal", 2),
   LARGE("large", 3);

   private final String translationKey;
   private final int scale;

   Gui2Extension6(@Annotation(method1 = Annotation.Type.STORAGE_OVERLAY_INFO) String var3, int var4) {
      this.translationKey = var3;
      this.scale = var4;
   }

   @Override
   public String id() {
      return this.translationKey;
   }

   @Override
   public String getLanguagePath() {
      return "features.STORAGE_OVERLAY.info";
   }

   @Override
   public String toString() {
      return this.method1(this.id(), new Object[0]);
   }

   @Generated
   public String getTranslationKey() {
      return this.translationKey;
   }

   @Generated
   public int getScale() {
      return this.scale;
   }
}
