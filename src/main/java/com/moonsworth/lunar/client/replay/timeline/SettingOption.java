package com.moonsworth.lunar.client.replay.timeline;

import java.util.UUID;

public class SettingOption {
   private final String field1;
   private final String field2;
   private final UUID field3;

   public SettingOption(String text1, String text2) {
      this(text1, text2, null);
   }

   public SettingOption(String text1, String text2, UUID uuid3) {
      this.field1 = text1;
      this.field2 = text2;
      this.field3 = uuid3;
   }

   public String type() {
      return this.field1;
   }

   public String name() {
      return this.field2;
   }

   public UUID method1() {
      return this.field3;
   }
}
