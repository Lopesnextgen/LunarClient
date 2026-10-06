package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod;

import com.google.gson.Gson;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.List;
import java.util.zip.GZIPOutputStream;

public class Profilerdebugmod2_2 {
   public static byte[] method1(List<Profilerdebugmod2> var0, Profilerdebugmod3 var1) {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      GZIPOutputStream var3 = new GZIPOutputStream(var2);
      DataOutputStream var4 = new DataOutputStream(var3);
      Profilerdebugmod_3 var5 = new Profilerdebugmod_3(var4);
      var4.writeInt(3);
      var4.writeUTF(new Gson().toJson(var1));

      for (Profilerdebugmod2 var7 : var0) {
         var7.method4(var5);
      }

      var5.method2();
      var4.writeInt(var0.size());

      for (Profilerdebugmod2 var9 : var0) {
         var9.method5(var4, var5);
      }

      var3.finish();
      return var2.toByteArray();
   }
}
