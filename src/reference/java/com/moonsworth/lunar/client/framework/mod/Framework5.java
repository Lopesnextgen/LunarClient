package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.Nameplate6;
import com.moonsworth.lunar.client.config.option.ClientOption;
import java.util.Set;
import java.util.function.Predicate;

public interface Framework5 {
   Set<ClientOption<?>> method1();

   Set<ClientOption<?>> method2();

   Set<ClientOption<?>> method3();

   void method4(Predicate<ClientOption<?>> var1);

   void method5(Framework7Extension var1);

   static Framework5 method6(Predicate<String> predicate, boolean var1) {
      return new Nameplate6(predicate, var1);
   }
}
