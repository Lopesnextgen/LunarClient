package com.moonsworth.lunar.client.util;

import java.lang.annotation.Documented;
import org.intellij.lang.annotations.Pattern;

@Documented
@Pattern("^([a-z]+\\.)?[a-z0-9]+(([A-Z]{1,4}[a-z0-9]+)*[A-Z]{0,3}|([a-z0-9]+[A-Z]{1,3})*|[A-Z]{1,3})$")
public @interface Annotation {
   Annotation.Type method1();

   enum Type {
      MOD_INFO("features.$MOD$.info"),
      SETTING("settings"),
      SETTING_LABELS("settings.labels"),
      SETTING_BUTTONS("settings.buttons"),
      GUI_COMPONENT("gui.components"),
      DUNGEON_ROUTES("features.SKYBLOCK.dungeonRoutes"),
      STORAGE_OVERLAY_INFO("features.STORAGE_OVERLAY.info"),
      INVENTORY_SEARCH_INFO("features.INVENTORY_SEARCH.info");

      private final String translationKeyObject;

      Type(String text) {
         this.translationKeyObject = text;
      }

      public String getTranslationKeyObject() {
         return this.translationKeyObject;
      }
   }
}
