package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import lombok.Generated;

public class RewindhandlersNameplateCore_3 {
   private final String field1;
   private final File field2;
   private final DataOutputStream field3;

   public RewindhandlersNameplateCore_3(String var1, File var2, boolean var3) {
      this.field1 = var1;
      this.field2 = var2;
      var2.deleteOnExit();
      if (var3) {
         this.field3 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(var2)));
      } else {
         this.field3 = null;
      }
   }

   public void method1(RewindhandlersNameplateCore4 var1) {
      var1.method2(this.field3);
   }

   public void method2(RewindhandlersNameplateCore4 var1) {
      var1.method3(this.field3);
   }

   public void close() {
      if (this.field3 != null) {
         this.field3.close();
      }
   }

   @Generated
   public String getId() {
      return this.field1;
   }

   @Generated
   public File getFile() {
      return this.field2;
   }

   @Generated
   public DataOutputStream method3() {
      return this.field3;
   }
}
