package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class Profile {
   private final ProfileCallNode field1 = new ProfileCallNode();
   private long field2;
   private boolean field3;
   private final List<ProfilerEngine.Data> field4 = new ArrayList<>();

   public Profile() {
   }

   public void method1(long number1) {
      this.field2 = number1;
   }

   public void method2(StackTraceElement[] items1) {
      this.field1.method1(items1, 1L);
   }

   public void method3(String text1, StackTraceElement[] items2) {
      this.field1.method2(1L);
      ProfileCallNode profilerdebugmod43 = this.field1.method5(text1);
      profilerdebugmod43.method1(items2, 1L);
   }

   public void method4(StringPool profilerdebugmod_31) {
      this.field1.method7(profilerdebugmod_31);

      for (ProfilerEngine.Data data3 : this.field4) {
         data3.method1(profilerdebugmod_31);
      }
   }

   public void method5(DataOutputStream output1, StringPool profilerdebugmod_32) {
      output1.writeLong(this.field2);
      this.field1.method8(output1, profilerdebugmod_32);
      output1.writeBoolean(this.field3);
      output1.writeInt(this.field4.size());

      for (ProfilerEngine.Data data4 : this.field4) {
         data4.method2(output1, profilerdebugmod_32);
      }
   }

   public void method6(ProfilerEngine.Data data1) {
      this.field4.add(data1);
   }

   public List<ProfilerEngine.Data> method7() {
      return this.field4;
   }

   @Generated
   public void method8(boolean flag1) {
      this.field3 = flag1;
   }
}
