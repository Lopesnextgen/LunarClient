package com.moonsworth.lunar.client.framework.feature.mod.impl.click.storageoverlay.mixin;

import com.moonsworth.lunar.client.util.Annotation;
import lombok.Generated;

public enum Gui2Extension3 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   LOCKED("locked", true, true),
   VISUAL("visual", false, true),
   DISABLED("disabled", false, false);

   private final String translationKey;
   private final boolean isInteractionsLocked;
   private final boolean isVisible;

   Gui2Extension3(@Annotation(method1 = Annotation.Type.STORAGE_OVERLAY_INFO) String var3, boolean var4, boolean var5) {
      this.translationKey = var3;
      this.isInteractionsLocked = var4;
      this.isVisible = var5;
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
   public boolean isInteractionsLocked() {
      return this.isInteractionsLocked;
   }

   @Generated
   public boolean isVisible() {
      return this.isVisible;
   }
}
