package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers;

import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.Bridge_61;
import com.moonsworth.lunar.client.config.option.ClientOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class Rewindhandlers_4 {
   private final Map<String, Map<String, Rewindhandlers2_5>> overrides = new HashMap<>();

   public void add(String var1, String var2, ClientOption<?> var3) {
      Map var4 = this.overrides.computeIfAbsent(var1, var0 -> new HashMap<>());
      Rewindhandlers2_5 var5 = (Rewindhandlers2_5)var4.get(var2);
      if (var5 == null || !Objects.equals(var5.getOption(), var3)) {
         var4.put(var2, new Rewindhandlers2_5(var3));
      }
   }

   public void remove(String var1, String var2, ClientOption<?> var3) {
      Map var4 = this.overrides.get(var1);
      if (var4 != null) {
         var4.remove(var2, new Rewindhandlers2_5(var3));
         if (var4.isEmpty()) {
            this.overrides.remove(var1);
         }
      }
   }

   public <T extends ClientOption<?>> void apply(Bridge_61 var1, String var2, Consumer<T> var3) {
      String var4;
      if (var1 instanceof EntityPlayerBridge var5) {
         var4 = var5.bridge$getUniqueID().toString();
      } else {
         var4 = String.valueOf(var1.bridge$getEntityId());
      }

      Map var7 = this.overrides.get(var4);
      if (var7 != null) {
         Rewindhandlers2_5 var6 = (Rewindhandlers2_5)var7.get(var2);
         if (var6 != null) {
            var6.setEntity(var1.method1());
            var3.accept(var6.getOption());
            var6.update();
         }
      }
   }
}
