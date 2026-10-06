package com.moonsworth.lunar.client.network.server;

import com.moonsworth.lunar.bridge.Bridge3_19;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.loading.ItemSetHandler;
import com.moonsworth.lunar.client.keystrokes.Keystrokes3;
import com.moonsworth.lunar.client.network.transfer.TransferSrvResolver;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ServerIconManager extends ItemSetHandler<Keystrokes3> {
   private final Map<String, Keystrokes3> field2 = new HashMap<>();

   @Override
   protected Set<Keystrokes3> method3() {
      return ConcurrentHashMap.newKeySet();
   }

   public void method2(Bridge3_19 var1, MixinHelper_4 var2, int value, int var4) {
      this.method3(var1.bridge$serverIP())
         .ifPresent(
            var3x -> {
               if (var3x.getResource() != null) {
                  var2.method25(
                     var3x.getResource(), value - 16, var4 + 12, 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, 8.0F, var3x.getResource() == LcuiScreen.field2 ? -256 : -1
                  );
               }
            }
         );
   }

   private Optional<Keystrokes3> method3(String var1) {
      if (this.field2.containsKey(var1.toLowerCase())) {
         return Optional.ofNullable(this.field2.get(var1.toLowerCase()));
      }

      Keystrokes3 var2 = null;

      for (Keystrokes3 var4 : this.method13()) {
         if (var4.method1().stream().anyMatch(var1x -> TransferSrvResolver.method1(var1x, var1))) {
            var2 = var4;
            break;
         }
      }

      this.field2.put(var1.toLowerCase(), var2);
      return Optional.ofNullable(var2);
   }

   public void clear() {
      this.method13().clear();
      this.field2.clear();
   }
}
