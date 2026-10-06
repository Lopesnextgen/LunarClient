package com.moonsworth.lunar.client.framework.feature.mod.impl.click.storageoverlay.mixin;

import com.moonsworth.lunar.client.framework.feature.mod.MixinHelperType;
import com.moonsworth.lunar.client.util.Annotation;
import lombok.Generated;

public enum Gui2Extension5 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   LIGHT("defaultLight", MixinHelperType.DEFAULT_LIGHT),
   DARK("defaultDark", MixinHelperType.DEFAULT_DARK),
   BLUE("darkBlue", MixinHelperType.DARK_BLUE);

   private final String translationKey;
   private final MixinHelperType theme;

   Gui2Extension5(@Annotation(method1 = Annotation.Type.STORAGE_OVERLAY_INFO) String var3, MixinHelperType var4) {
      this.translationKey = var3;
      this.theme = var4;
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
   public MixinHelperType getTheme() {
      return this.theme;
   }
}
