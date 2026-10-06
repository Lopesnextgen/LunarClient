package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod;

import com.google.gson.Gson;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.List;
import java.util.zip.GZIPOutputStream;

public class ProfileEncoder {
   public ProfileEncoder() {
   }

   public static byte[] method1(List<Profile> list0, ProfileMetadata profilerdebugmod31) {
      ByteArrayOutputStream bytearrayoutputstream2 = new ByteArrayOutputStream();
      GZIPOutputStream gzipoutputstream3 = new GZIPOutputStream(bytearrayoutputstream2);
      DataOutputStream output4 = new DataOutputStream(gzipoutputstream3);
      StringPool profilerdebugmod_35 = new StringPool(output4);
      output4.writeInt(3);
      output4.writeUTF(new Gson().toJson(profilerdebugmod31));

      for (Profile profilerdebugmod27 : list0) {
         profilerdebugmod27.method4(profilerdebugmod_35);
      }

      profilerdebugmod_35.method2();
      output4.writeInt(list0.size());

      for (Profile profilerdebugmod29 : list0) {
         profilerdebugmod29.method5(output4, profilerdebugmod_35);
      }

      gzipoutputstream3.finish();
      return bytearrayoutputstream2.toByteArray();
   }
}
