package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Profilerdebugmod_3 {
   private final DataOutputStream dos;
   private final Map<String, Integer> field1 = new HashMap<>();
   private final List<String> field2 = new ArrayList<>();
   private boolean hasWritten;

   public Profilerdebugmod_3(DataOutputStream var1) {
      this.dos = var1;
   }

   public void method1(String var1) {
      if (this.hasWritten) {
         throw new IllegalStateException("Cannot collect strings after writing");
      }

      if (var1 == null) {
         var1 = "null";
      }

      this.field1.computeIfAbsent(var1, var1x -> {
         this.field2.add(var1x);
         return this.field2.size() - 1;
      });
   }

   public void method2() {
      this.hasWritten = true;
      this.dos.writeInt(this.field2.size());

      for (String var2 : this.field2) {
         this.dos.writeUTF(var2);
      }
   }

   public void writeString(String var1) {
      if (!this.hasWritten) {
         throw new IllegalStateException("Cannot write strings before writing");
      }

      if (var1 == null) {
         var1 = "null";
      }

      if (!this.field1.containsKey(var1)) {
         throw new IllegalArgumentException("String must be collected before writing");
      }

      int var2 = this.field1.get(var1);
      this.dos.writeInt(var2);
   }
}
