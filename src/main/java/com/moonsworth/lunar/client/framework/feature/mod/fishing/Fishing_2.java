package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.Locale;
import java.util.Set;
import lombok.Generated;

public class Fishing_2 {
   private final String field1;
   private final String field2;
   private final boolean field3;
   private final String field4;
   private final boolean field5;
   private final Set<Fishing_2> field6;
   private Set<String> field7;
   private Fishing_2 field8;

   public ResourceLocationBridge method1() {
      String var1 = this.field4 != null && !this.field4.isEmpty() ? this.field4.toLowerCase(Locale.ROOT) : "unknown";
      return ResourceLocationBridge.create("lunar", "icons/hypixel/" + var1 + ".webp");
   }

   public String key() {
      return this.field8 == null ? this.field1 : this.field8.key() + ":" + this.field1;
   }

   public String key(String var1) {
      return this.key() + ":" + var1;
   }

   public static Fishing$Data method2() {
      return new Fishing$Data();
   }

   @Generated
   Fishing_2(String var1, String text, boolean flag, String text2, boolean flag2, Set<Fishing_2> set, Set<String> set2, Fishing_2 fishing_2) {
      this.field1 = var1;
      this.field2 = text;
      this.field3 = flag;
      this.field4 = text2;
      this.field5 = flag2;
      this.field6 = set;
      this.field7 = set2;
      this.field8 = fishing_2;
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
   public Set<Fishing_2> method5() {
      return this.field6;
   }

   @Generated
   public Set<String> method6() {
      return this.field7;
   }

   @Generated
   public Fishing_2 method7() {
      return this.field8;
   }

   @Generated
   public void method8(Fishing_2 var1) {
      this.field8 = var1;
   }
}
