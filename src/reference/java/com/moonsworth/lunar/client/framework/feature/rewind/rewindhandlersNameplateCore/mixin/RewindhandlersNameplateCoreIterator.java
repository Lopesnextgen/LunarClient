package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin;

import com.moonsworth.lunar.client.alert.mixin.Alert2;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind_4;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2Impl6;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2Impl_2;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers5;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Map.Entry;

public class RewindhandlersNameplateCoreIterator extends RewindhandlersNameplateCore {
   @Override
   public void method2(EventClientTick var1, RewindHandlers5 var2, Rewind_4 var3) {
      if (var3.method5()) {
         for (Framework7Extension var5 : ThreadModuleDump63.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
            if (var5 instanceof AbstractFeature var6 && var6.method33() != null) {
               for (Entry var8 : var6.method33().method3().entrySet()) {
                  Nameplate2Impl_2 var9 = new Nameplate2Impl_2(var5.getId(), (String)var8.getKey(), Nameplate2Impl_2.method3(var8.getValue()), var8.getValue());
                  var3.method9(var9, var2.getTick());
               }

               Alert2 var10 = (Alert2)var5.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field4);
               if (var10 != null && var10.method3().isPresent()) {
                  Nameplate2Impl6 var11 = new Nameplate2Impl6(var5.getId(), Nameplate2Impl6.Type.fromBoolean((Boolean)var10.method3().get()));
                  var3.method9(var11, var2.getTick());
               }
            }
         }
      }
   }
}
