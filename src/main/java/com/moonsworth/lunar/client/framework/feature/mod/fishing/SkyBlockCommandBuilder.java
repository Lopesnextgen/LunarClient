package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import java.util.Set;
import lombok.Generated;

@Generated
public class SkyBlockCommandBuilder {
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
   private Set<SkyBlockCommand> field4;
   @Generated
   private Set<String> field5;
   @Generated
   private SkyBlockCommand field6;

   @Generated
   SkyBlockCommandBuilder() {
   }

   @Generated
   public SkyBlockCommandBuilder method1(String text1) {
      this.prettyName = text1;
      return this;
   }

   @Generated
   public SkyBlockCommandBuilder method2(String text1) {
      this.command = text1;
      return this;
   }

   @Generated
   public SkyBlockCommandBuilder method3(boolean flag1) {
      this.field1 = flag1;
      return this;
   }

   @Generated
   public SkyBlockCommandBuilder method4(String text1) {
      this.field2 = text1;
      return this;
   }

   @Generated
   public SkyBlockCommandBuilder method5(boolean flag1) {
      this.field3 = flag1;
      return this;
   }

   @Generated
   public SkyBlockCommandBuilder method6(Set<SkyBlockCommand> set1) {
      this.field4 = set1;
      return this;
   }

   @Generated
   public SkyBlockCommandBuilder method7(Set<String> set1) {
      this.field5 = set1;
      return this;
   }

   @Generated
   public SkyBlockCommandBuilder method8(SkyBlockCommand fishing_21) {
      this.field6 = fishing_21;
      return this;
   }

   @Generated
   public SkyBlockCommand method9() {
      return new SkyBlockCommand(this.prettyName, this.command, this.field1, this.field2, this.field3, this.field4, this.field5, this.field6);
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
