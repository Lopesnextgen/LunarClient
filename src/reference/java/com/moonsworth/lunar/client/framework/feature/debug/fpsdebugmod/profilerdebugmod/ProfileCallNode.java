package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod;

import java.io.DataOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ProfileCallNode {
   private long time;
   private final String className;
   private final String methodName;
   private final int field1;
   private final Map<String, ProfileCallNode> field2 = new HashMap<>();

   public ProfileCallNode() {
      this.className = "";
      this.methodName = "root";
      this.field1 = 0;
   }

   public ProfileCallNode(String text1) {
      this.className = "";
      this.methodName = text1;
      this.field1 = 0;
   }

   public ProfileCallNode(StackTraceElement stacktraceelement1) {
      this.className = stacktraceelement1.getClassName();
      this.methodName = stacktraceelement1.getMethodName();
      this.field1 = stacktraceelement1.getLineNumber();
   }

   public void method1(StackTraceElement[] items1, long number2) {
      if (items1.length != 0) {
         this.method3(items1, items1.length - 1, number2);
      }
   }

   public void method2(long number1) {
      this.time += number1;
   }

   private void method3(StackTraceElement[] items1, int index2, long number3) {
      this.time += number3;
      if (index2 != 0) {
         StackTraceElement stacktraceelement5 = items1[index2];
         ProfileCallNode profilerdebugmod46 = this.method4(stacktraceelement5);
         profilerdebugmod46.method3(items1, --index2, number3);
      }
   }

   private ProfileCallNode method4(StackTraceElement stacktraceelement1) {
      String text2 = method6(stacktraceelement1);
      return this.field2.computeIfAbsent(text2, arg1x -> new ProfileCallNode(stacktraceelement1));
   }

   public ProfileCallNode method5(String text1) {
      return this.field2.computeIfAbsent(text1, ProfileCallNode::new);
   }

   private static String method6(StackTraceElement stacktraceelement0) {
      return stacktraceelement0.getClassName() + "#" + stacktraceelement0.getMethodName() + ":" + stacktraceelement0.getLineNumber();
   }

   public void method7(StringPool profilerdebugmod_31) {
      profilerdebugmod_31.method1(this.className);
      profilerdebugmod_31.method1(this.methodName);

      for (Entry entry3 : this.field2.entrySet()) {
         ((ProfileCallNode)entry3.getValue()).method7(profilerdebugmod_31);
      }
   }

   public void method8(DataOutputStream output1, StringPool profilerdebugmod_32) {
      output1.writeLong(this.time);
      profilerdebugmod_32.writeString(this.className);
      profilerdebugmod_32.writeString(this.methodName);
      output1.writeInt(this.field1);
      output1.writeInt(this.field2.size());

      for (Entry entry4 : this.field2.entrySet()) {
         ((ProfileCallNode)entry4.getValue()).method8(output1, profilerdebugmod_32);
      }
   }
}
