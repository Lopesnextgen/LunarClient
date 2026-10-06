package com.moonsworth.lunar.client.replay.recording;

import com.moonsworth.lunar.client.config.override.SettingIntercept;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.replay.recording.ReplayHandler;
import com.moonsworth.lunar.client.replay.network.ServerSettingOverridePacket;
import com.moonsworth.lunar.client.replay.network.SettingValuePacket;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRecorder;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Map.Entry;

public class SettingRecorder extends RecorderEventListener {
   public SettingRecorder() {
   }

   @Override
   public void method2(EventTick highlightimpl21, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      if (rewind_43.method5()) {
         for (Framework7Extension framework7extension5 : Ref.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
            if (framework7extension5 instanceof AbstractFeature framework7extension26 && framework7extension26.method33() != null) {
               for (Entry entry8 : framework7extension26.method33().method3().entrySet()) {
                  SettingValuePacket nameplate2impl_29 = new SettingValuePacket(framework7extension5.getId(), (String)entry8.getKey(), SettingValuePacket.method3(entry8.getValue()), entry8.getValue());
                  rewind_43.method9(nameplate2impl_29, rewindhandlers52.getTick());
               }

               SettingIntercept alert210 = (SettingIntercept)framework7extension5.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field4);
               if (alert210 != null && alert210.method3().isPresent()) {
                  ServerSettingOverridePacket nameplate2impl611 = new ServerSettingOverridePacket(framework7extension5.getId(), ServerSettingOverridePacket.OverrideMode.fromBoolean((Boolean)alert210.method3().get()));
                  rewind_43.method9(nameplate2impl611, rewindhandlers52.getTick());
               }
            }
         }
      }
   }
}
