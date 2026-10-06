package com.moonsworth.lunar.client.framework.listener;

import com.moonsworth.lunar.bridge.ServerDataBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.event.EventBusAccess;
import com.moonsworth.lunar.client.event.mixin.gui.EventDisconnect;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerBrand;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerJoin;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.framework.Ref;

public class ServerSettingsListener implements EventBusAccess {
   public ServerSettingsListener() {
      this.handle(
         EventServerJoin.class,
         arg0 -> {
            ServerDataBridge bridge3_191 = Ref.method3().bridge$getCurrentServerData();
            if (bridge3_191 != null) {
               String text2 = bridge3_191.bridge$serverIP();
               Ref.method4()
                  .method40()
                  .IIORHHIRHIORHRCCCOICCRCHRRCCRH()
                  .forEach(arg1x -> arg1x.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(ModTraits.field4).ifPresent(arg2x -> arg2x.method4(arg1x, text2)));
               Ref.method4()
                  .method41()
                  .IORHHHROCRRHORHRCHCCHHIHICCRCO()
                  .forEach(
                     (arg1x, arg2x) -> arg2x.method13()
                        .forEach(arg1xx -> arg1xx.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(OptionTraits.field5).ifPresent(arg2xx -> arg2xx.method4(arg1xx, text2)))
                  );
            }
         }
      );
      this.handle(
         EventDisconnect.class,
         arg0 -> {
            Ref.method4()
               .method40()
               .IIORHHIRHIORHRCCCOICCRCHRRCCRH()
               .forEach(arg0x -> arg0x.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(ModTraits.field4).ifPresent(arg1 -> arg1.method5(arg0x)));
            Ref.method4()
               .method41()
               .IORHHHROCRRHORHRCHCCHHIHICCRCO()
               .forEach(
                  (arg0x, arg1) -> arg1.method13()
                     .forEach(arg0xx -> arg0xx.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(OptionTraits.field5).ifPresent(arg1x -> arg1x.method5(arg0xx)))
               );
         }
      );
      this.handle(
         EventServerBrand.class,
         arg0 -> {
            if (arg0.method1().toLowerCase().contains("brand")) {
               ServerDataBridge bridge3_191 = Ref.method3().bridge$getCurrentServerData();
               String text2 = bridge3_191 == null ? null : bridge3_191.bridge$serverIP();
               Ref.method4()
                  .method40()
                  .IIORHHIRHIORHRCCCOICCRCHRRCCRH()
                  .forEach(arg1x -> arg1x.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(ModTraits.field4).ifPresent(arg2x -> arg2x.method4(arg1x, text2)));
               Ref.method4()
                  .method41()
                  .IORHHHROCRRHORHRCHCCHHIHICCRCO()
                  .forEach(
                     (arg1x, arg2x) -> arg2x.method13()
                        .forEach(arg1xx -> arg1xx.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(OptionTraits.field5).ifPresent(arg2xx -> arg2xx.method4(arg1xx, text2)))
                  );
            }
         }
      );
   }
}
