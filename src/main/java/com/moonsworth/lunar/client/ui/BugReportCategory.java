package com.moonsworth.lunar.client.ui;

import lombok.Generated;

public enum BugReportCategory {
   MOD("mod"),
   GUI("gui"),
   COSMETIC("cosmetic"),
   STORE("store"),
   VOICE("voice"),
   LAUNCHER("launcher"),
   OTHER("other");

   private final String id;

   @Override
   public String toString() {
      return this.id;
   }

   @Generated
   BugReportCategory(String text) {
      this.id = text;
   }
}
