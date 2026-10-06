package com.moonsworth.lunar.client.framework.feature.rewind.holograms;

import com.moonsworth.lunar.client.framework.feature.rewind.Fishing2Loader;
import com.moonsworth.lunar.client.framework.feature.rewind.Fishing2Loader2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.TextOption.Data;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import lombok.Generated;

public class Holograms2 {
   private final Map<String, Supplier<Fishing2Loader<?, ?>>> field1 = new HashMap<>();
   private final Map<String, Set<String>> field2 = new HashMap<>();

   public Holograms2(Nameplate2 var1) {
      this.method1(() -> new Fishing2Loader2(var1, OptionFactory.method7("hide").method31()), Set.of("player", "entity"));
      this.method1(
         () -> new Fishing2Loader2(var1, ((Data)OptionFactory.method12("name").method2("Text")).method31()),
         Set.of("player", "entity")
      );
      this.method1(() -> new Fishing2Loader2(var1, OptionFactory.method12("skin").method31()), Set.of("player"));
   }

   private void method1(Supplier<Fishing2Loader<?, ?>> var1, Set<String> var2) {
      Fishing2Loader var3 = (Fishing2Loader)var1.get();
      this.field1.put(var3.type(), var1);
      this.field2.put(var3.type(), var2);
   }

   public Fishing2Loader<?, ?> method2(String var1) {
      Fishing2Loader var2 = null;
      if (this.field1.containsKey(var1)) {
         var2 = this.field1.get(var1).get();
      }

      return var2;
   }

   @Generated
   public Map<String, Set<String>> method3() {
      return this.field2;
   }
}
