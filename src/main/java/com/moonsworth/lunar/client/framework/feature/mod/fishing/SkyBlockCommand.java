package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.Locale;
import java.util.Set;
import lombok.Generated;

public class SkyBlockCommand {
   private final String field1;
   private final String field2;
   private final boolean field3;
   private final String field4;
   private final boolean field5;
   private final Set<SkyBlockCommand> field6;
   private Set<String> field7;
   private SkyBlockCommand field8;

   public ResourceLocationBridge method1() {
      String text1 = this.field4 != null && !this.field4.isEmpty() ? this.field4.toLowerCase(Locale.ROOT) : "unknown";
      return ResourceLocationBridge.create("lunar", "icons/hypixel/" + text1 + ".webp");
   }

   public String key() {
      return this.field8 == null ? this.field1 : this.field8.key() + ":" + this.field1;
   }

   public String key(String text1) {
      return this.key() + ":" + text1;
   }

   public static SkyBlockCommandBuilder method2() {
      return new SkyBlockCommandBuilder();
   }

   @Generated
   SkyBlockCommand(String text1, String text, boolean flag, String text2, boolean flag2, Set<SkyBlockCommand> set, Set<String> set2, SkyBlockCommand fishing_28) {
      this.field1 = text1;
      this.field2 = text;
      this.field3 = flag;
      this.field4 = text2;
      this.field5 = flag2;
      this.field6 = set;
      this.field7 = set2;
      this.field8 = fishing_28;
   }

   @Generated
   public String getPrettyName() {
      return this.field1;
   }

   @Generated
   public String getCommand() {
      return this.field2;
   }

   @Generated
   public boolean method3() {
      return this.field3;
   }

   @Generated
   public String getIcon() {
      return this.field4;
   }

   @Generated
   public boolean method4() {
      return this.field5;
   }

   @Generated
   public Set<SkyBlockCommand> method5() {
      return this.field6;
   }

   @Generated
   public Set<String> method6() {
      return this.field7;
   }

   @Generated
   public SkyBlockCommand method7() {
      return this.field8;
   }

   @Generated
   public void method8(SkyBlockCommand fishing_21) {
      this.field8 = fishing_21;
   }
}
