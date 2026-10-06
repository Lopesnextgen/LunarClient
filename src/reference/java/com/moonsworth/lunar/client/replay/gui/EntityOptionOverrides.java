package com.moonsworth.lunar.client.replay.gui;

import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.EntityRenderStateBridge;
import com.moonsworth.lunar.client.config.option.ClientOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class EntityOptionOverrides {
   private final Map<String, Map<String, HighlightOptionWatcher>> field1 = new HashMap<>();

   public EntityOptionOverrides() {
   }

   public void method1(String text1, String text2, ClientOption<?> lightingextension3) {
      Map map4 = this.field1.computeIfAbsent(text1, arg0 -> new HashMap<>());
      HighlightOptionWatcher rewindhandlers2_55 = (HighlightOptionWatcher)map4.get(text2);
      if (rewindhandlers2_55 == null || !Objects.equals(rewindhandlers2_55.getOption(), lightingextension3)) {
         map4.put(text2, new HighlightOptionWatcher(lightingextension3));
      }
   }

   public void method2(String text1, String text2, ClientOption<?> lightingextension3) {
      Map map4 = this.field1.get(text1);
      if (map4 != null) {
         map4.remove(text2, new HighlightOptionWatcher(lightingextension3));
         if (map4.isEmpty()) {
            this.field1.remove(text1);
         }
      }
   }

   public <T extends ClientOption<?>> void method3(EntityRenderStateBridge bridge_611, String text2, Consumer<T> consumer3) {
      String text4;
      if (bridge_611 instanceof EntityPlayerBridge bridgeextension2225) {
         text4 = bridgeextension2225.bridge$getUniqueID().toString();
      } else {
         text4 = String.valueOf(bridge_611.bridge$getEntityId());
      }

      Map map7 = this.field1.get(text4);
      if (map7 != null) {
         HighlightOptionWatcher rewindhandlers2_56 = (HighlightOptionWatcher)map7.get(text2);
         if (rewindhandlers2_56 != null) {
            rewindhandlers2_56.method2(bridge_611.method1());
            consumer3.accept(rewindhandlers2_56.getOption());
            rewindhandlers2_56.update();
         }
      }
   }
}
