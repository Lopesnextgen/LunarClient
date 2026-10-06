package com.moonsworth.lunar.client.cosmetics.molang;

import java.util.List;
import lombok.Generated;

public class MolangCompileOptions {
   private boolean field1 = true;
   private boolean field2 = true;
   private boolean field3 = true;
   private boolean field4 = true;
   private List<String> field5 = null;

   public MolangCompileOptions() {
   }

   public MolangCompileOptions method1(boolean flag1) {
      this.field1 = flag1;
      return this;
   }

   public MolangCompileOptions method2(boolean flag1) {
      this.field2 = flag1;
      return this;
   }

   public MolangCompileOptions method3(boolean flag1) {
      this.field3 = flag1;
      return this;
   }

   public MolangCompileOptions method4(boolean flag1) {
      this.field4 = flag1;
      return this;
   }

   public MolangCompileOptions method5(List<String> list) {
      this.field5 = list;
      return this;
   }

   @Generated
   public boolean method6() {
      return this.field1;
   }

   @Generated
   public boolean method7() {
      return this.field2;
   }

   @Generated
   public boolean method8() {
      return this.field3;
   }

   @Generated
   public boolean method9() {
      return this.field4;
   }

   @Generated
   public List<String> method10() {
      return this.field5;
   }
}
