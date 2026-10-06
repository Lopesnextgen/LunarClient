package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod;

import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DebugArchive {
   private final List<DebugArchive.Data> field1 = new ArrayList<>();
   private final List<DebugArchive.FpsDebugError> field2 = new ArrayList<>();

   public DebugArchive() {
   }

   public DebugArchive method1(String text1, byte[] items2) {
      this.field1.add(new DebugArchive.Data(text1, items2));
      return this;
   }

   public DebugArchive method2(String text1, String text2) {
      return this.method1(text1, text2.getBytes(StandardCharsets.UTF_8));
   }

   public DebugArchive method3(DebugArchive fpsdebugmod1) {
      this.field1.addAll(fpsdebugmod1.field1);
      this.field2.addAll(fpsdebugmod1.field2);
      return this;
   }

   public DebugArchive method4(String text1, Exception exception2) {
      this.field2.add(new DebugArchive.FpsDebugError(text1, exception2));
      CrashReporter.method5(exception2, "FpsDataWithError");
      return this;
   }

   public void method5(ZipOutputStream zipoutputstream1) {
      HashSet set2 = new HashSet();
      ArrayList list3 = new ArrayList<>(this.field1);
      if (!this.field2.isEmpty()) {
         String text4 = this.field2.stream().map(arg0 -> arg0.field1 + "\n" + arg0.field2.getMessage()).collect(Collectors.joining("\n\n"));
         list3.add(new DebugArchive.Data("errors.txt", text4.getBytes(StandardCharsets.UTF_8)));
      }

      for (DebugArchive.Data data5 : list3) {
         String text6 = this.method6(data5.field1, set2);
         set2.add(text6);
         zipoutputstream1.putNextEntry(new ZipEntry(text6));
         zipoutputstream1.write(data5.field2);
         zipoutputstream1.closeEntry();
      }
   }

   private String method6(String text1, Set<String> set2) {
      int number3 = -1;
      String text4 = text1;
      String text5 = text1.substring(0, text1.lastIndexOf(46));
      String text6 = text1.substring(text1.lastIndexOf(46));

      while (set2.contains(text4)) {
         text4 = text5 + "-" + ++number3 + "." + text6;
      }

      return text4;
   }

   private class Data {
      private final String field1;
      private final byte[] field2;

      private Data(String text1, byte[] items2) {
         this.field1 = text1;
         this.field2 = items2;
      }

      public String method1() {
         return this.field1;
      }

      public byte[] method2() {
         return this.field2;
      }
   }

   private class FpsDebugError {
      private final String field1;
      private final Exception field2;

      private FpsDebugError(String text1, Exception exception2) {
         this.field1 = text1;
         this.field2 = exception2;
      }

      public String method1() {
         return this.field1;
      }

      public Exception method2() {
         return this.field2;
      }
   }
}
