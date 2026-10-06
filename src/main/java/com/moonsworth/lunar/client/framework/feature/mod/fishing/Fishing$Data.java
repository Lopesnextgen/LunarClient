package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import java.util.Set;
import lombok.Generated;

@Generated
public class Fishing$Data {
   @Generated
   private String prettyName;
   @Generated
   private String command;
   @Generated
   private boolean field1;
   @Generated
   private String field2;
   @Generated
   private boolean field3;
   @Generated
   private Set<Fishing_2> field4;
   @Generated
   private Set<String> field5;
   @Generated
   private Fishing_2 field6;

   @Generated
   Fishing$Data() {
   }

   @Generated
   public Fishing$Data method1(String var1) {
      this.prettyName = var1;
      return this;
   }

   @Generated
   public Fishing$Data method2(String var1) {
      this.command = var1;
      return this;
   }

   @Generated
   public Fishing$Data method3(boolean var1) {
      this.field1 = var1;
      return this;
   }

   @Generated
   public Fishing$Data method4(String var1) {
      this.field2 = var1;
      return this;
   }

   @Generated
   public Fishing$Data method5(boolean var1) {
      this.field3 = var1;
      return this;
   }

   @Generated
   public Fishing$Data method6(Set<Fishing_2> var1) {
      this.field4 = var1;
      return this;
   }

   @Generated
   public Fishing$Data method7(Set<String> var1) {
      this.field5 = var1;
      return this;
   }

   @Generated
   public Fishing$Data method8(Fishing_2 var1) {
      this.field6 = var1;
      return this;
   }

   @Generated
   public Fishing_2 method9() {
      return new Fishing_2(this.prettyName, this.command, this.field1, this.field2, this.field3, this.field4, this.field5, this.field6);
   }

   @Generated
   @Override
   public String toString() {
      return "SkyBlockCommand.SkyBlockCommandBuilder(prettyName="
         + this.prettyName
         + ", command="
         + this.command
         + ", allowsArguments="
         + this.field1
         + ", icon="
         + this.field2
         + ", anySubCommand="
         + this.field3
         + ", subCommands="
         + this.field4
         + ", userDefinedSubCommands="
         + this.field5
         + ", parent="
         + this.field6
         + ")";
   }
}
