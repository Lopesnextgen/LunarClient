package com.moonsworth.lunar.client.network.apollo;

import com.google.protobuf.Message;
import com.lunarclient.apollo.staffmod.v1.DisableStaffModsMessage;
import com.lunarclient.apollo.staffmod.v1.EnableStaffModsMessage;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.feature.Staffxray;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import java.util.Set;

public class StaffModApolloHandler extends ApolloModuleHandler {
   public StaffModApolloHandler() {
      super("staff_mod", "Staff Mod");
   }

   @Override
   public Set<Class<? extends Message>> method2() {
      return Set.of(EnableStaffModsMessage.class, DisableStaffModsMessage.class);
   }

   @Override
   public void method3(HighlightImpl_3 var1) {
      var1.unpack(EnableStaffModsMessage.class).ifPresent(var0 -> {
         List var1x = var0.getStaffModsList().stream().map(Enum::ordinal).toList();
         boolean var2 = var0.getEnabledByDefault();

         for (Framework7Extension var4 : ThreadModuleDump63.method4().method44().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
            Staffxray var5 = (Staffxray)var4.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field2);
            if (var1x.contains(var5.getOrdinal())) {
               var5.method2(true);
               if (var2) {
                  ModEnabledState var6 = (ModEnabledState)var4.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field6);
                  if (var6 != null) {
                     var6.setEnabled(true);
                  }
               }
            }
         }
      });
      var1.unpack(DisableStaffModsMessage.class).ifPresent(var0 -> {
         List var1x = var0.getStaffModsList().stream().map(Enum::ordinal).toList();

         for (Framework7Extension var3 : ThreadModuleDump63.method4().method44().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
            Staffxray var4 = (Staffxray)var3.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field2);
            if (var1x.contains(var4.getOrdinal())) {
               var4.method2(false);
               ModEnabledState var5 = (ModEnabledState)var3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field6);
               if (var5 != null) {
                  var5.setEnabled(false);
               }
            }
         }
      });
   }
}
