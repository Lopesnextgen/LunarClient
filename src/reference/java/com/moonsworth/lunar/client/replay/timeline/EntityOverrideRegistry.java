package com.moonsworth.lunar.client.replay.timeline;

import com.moonsworth.lunar.client.replay.timeline.KeyframeProperty;
import com.moonsworth.lunar.client.replay.timeline.BooleanProperty;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.TextOption.Data;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import lombok.Generated;

public class EntityOverrideRegistry {
   private final Map<String, Supplier<KeyframeProperty<?, ?>>> field1 = new HashMap<>();
   private final Map<String, Set<String>> field2 = new HashMap<>();

   public EntityOverrideRegistry(UndoRedoManager nameplate21) {
      this.method1(() -> new BooleanProperty(nameplate21, OptionFactory.method7("hide").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH()), Set.of("player", "entity"));
      this.method1(
         () -> new BooleanProperty(nameplate21, ((Data)OptionFactory.method12("name").HIIIOHRRROCICIOIORRRIRCRCHHIII("Text")).RIRRHIRCCHCCHOICRHRHHRIHOIHHRH()),
         Set.of("player", "entity")
      );
      this.method1(() -> new BooleanProperty(nameplate21, OptionFactory.method12("skin").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH()), Set.of("player"));
   }

   private void method1(Supplier<KeyframeProperty<?, ?>> supplier1, Set<String> set2) {
      KeyframeProperty fishing2loader3 = (KeyframeProperty)supplier1.get();
      this.field1.put(fishing2loader3.type(), supplier1);
      this.field2.put(fishing2loader3.type(), set2);
   }

   public KeyframeProperty<?, ?> method2(String text1) {
      KeyframeProperty fishing2loader2 = null;
      if (this.field1.containsKey(text1)) {
         fishing2loader2 = this.field1.get(text1).get();
      }

      return fishing2loader2;
   }

   @Generated
   public Map<String, Set<String>> method3() {
      return this.field2;
   }
}
