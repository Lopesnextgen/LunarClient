package com.moonsworth.lunar.client.framework.feature.quickplay;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.List;
import java.util.Locale;
import lombok.Generated;

public class Quickplay {
   private Quickplay field1;
   private String key;
   private boolean disabled;
   private String name;
   private String field2;
   private String command;
   private List<Quickplay> field3 = ImmutableList.of();

   public Quickplay() {
   }

   public ResourceLocationBridge method1() {
      String text1 = this.field2 != null && !this.field2.isEmpty() ? this.field2.toLowerCase(Locale.ROOT) : "unknown";
      return ResourceLocationBridge.create("lunar", "icons/hypixel/" + text1 + ".webp");
   }

   public String method2() {
      String text1 = this.field1 == null ? "/l " : "/play ";
      return this.command != null && !this.command.isEmpty() ? this.command : text1 + this.key.toLowerCase(Locale.ROOT);
   }

   @Generated
   public Quickplay method3() {
      return this.field1;
   }

   @Generated
   public String getKey() {
      return this.key;
   }

   @Generated
   public boolean isDisabled() {
      return this.disabled;
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public String getIcon() {
      return this.field2;
   }

   @Generated
   public String getCommand() {
      return this.command;
   }

   @Generated
   public List<Quickplay> method4() {
      return this.field3;
   }

   @Generated
   public void method5(Quickplay quickplay1) {
      this.field1 = quickplay1;
   }

   @Generated
   public void setKey(String text1) {
      this.key = text1;
   }

   @Generated
   public void setDisabled(boolean flag) {
      this.disabled = flag;
   }

   @Generated
   public void setName(String text1) {
      this.name = text1;
   }

   @Generated
   public void method8(String text1) {
      this.field2 = text1;
   }

   @Generated
   public void setCommand(String text1) {
      this.command = text1;
   }

   @Generated
   public void method10(List<Quickplay> list) {
      this.field3 = list;
   }

   @Generated
   @Override
   public String toString() {
      return "HypixelGame(parent="
         + this.method3()
         + ", key="
         + this.getKey()
         + ", disabled="
         + this.isDisabled()
         + ", name="
         + this.getName()
         + ", icon="
         + this.getIcon()
         + ", command="
         + this.getCommand()
         + ", modes="
         + this.method4()
         + ")";
   }
}
