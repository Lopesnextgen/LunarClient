package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringPool {
   private final DataOutputStream dos;
   private final Map<String, Integer> field1 = new HashMap<>();
   private final List<String> field2 = new ArrayList<>();
   private boolean hasWritten;

   public StringPool(DataOutputStream output1) {
      this.dos = output1;
   }

   public void method1(String text1) {
      if (this.hasWritten) {
         throw new IllegalStateException("Cannot collect strings after writing");
      }

      if (text1 == null) {
         text1 = "null";
      }

      this.field1.computeIfAbsent(text1, arg1x -> {
         this.field2.add(arg1x);
         return this.field2.size() - 1;
      });
   }

   public void method2() {
      this.hasWritten = true;
      this.dos.writeInt(this.field2.size());

      for (String text2 : this.field2) {
         this.dos.writeUTF(text2);
      }
   }

   public void writeString(String text1) {
      if (!this.hasWritten) {
         throw new IllegalStateException("Cannot write strings before writing");
      }

      if (text1 == null) {
         text1 = "null";
      }

      if (!this.field1.containsKey(text1)) {
         throw new IllegalArgumentException("String must be collected before writing");
      }

      int number2 = this.field1.get(text1);
      this.dos.writeInt(number2);
   }
}
