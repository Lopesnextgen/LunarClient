package com.moonsworth.lunar.client.framework.feature.mod.impl.click.storageoverlay.mixin;

import com.moonsworth.lunar.client.util.Annotation;
import lombok.Generated;

public enum Gui2Extension implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   TINY("tiny"),
   SMALL("small"),
   MEDIUM("medium"),
   LARGE("large");

   private final String translationKey;

   Gui2Extension(@Annotation(method1 = Annotation.Type.STORAGE_OVERLAY_INFO) String var3) {
      this.translationKey = var3;
   }

   public int getHeight() {
      return 75 * (this.ordinal() + 2);
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
}
