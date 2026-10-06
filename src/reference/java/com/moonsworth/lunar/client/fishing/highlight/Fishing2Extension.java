package com.moonsworth.lunar.client.fishing.highlight;

import com.google.protobuf.Any;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.MixinHelper_15;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.fishing.Fishing2;
import com.moonsworth.lunar.client.cosmetics.OwnedCosmetic;
import com.moonsworth.lunar.client.config.option.ClientOption;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface Fishing2Extension extends Fishing2 {
   UUID field1 = new UUID(Long.MAX_VALUE, Long.MIN_VALUE);

   void handleMainMenuButton();

   boolean method1();

   boolean method2();

   boolean method3();

   boolean method4();

   boolean isRecording();

   boolean method5();

   ResourceLocationBridge method6();

   int method7();

   void method8(Any var1);

   boolean method9(Bridge5Extension6 var1);

   Set<MixinHelper_15> method10();

   void startRecording();

   void stopRecording();

   void method11();

   void method12();

   void method13();

   Map<String, ClientOption> method14();

   boolean method15();

   class Data {
      private final Set<OwnedCosmetic> field1;
      private final Map<Long, OwnedCosmetic> field2;

      public Data(Set<OwnedCosmetic> var1, Map<Long, OwnedCosmetic> map) {
         this.field1 = var1;
         this.field2 = map;
      }

      public Set<OwnedCosmetic> method1() {
         return this.field1;
      }

      public Map<Long, OwnedCosmetic> method2() {
         return this.field2;
      }
   }
}
