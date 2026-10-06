package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.FeatureOptionContainer;
import com.moonsworth.lunar.client.config.option.ClientOption;
import java.util.Set;
import java.util.function.Predicate;

public interface OptionContainer {
   Set<ClientOption<?>> method1();

   Set<ClientOption<?>> method2();

   Set<ClientOption<?>> method3();

   void method4(Predicate<ClientOption<?>> predicate1);

   void method5(Framework7Extension framework7extension1);

   static OptionContainer method6(Predicate<String> predicate0, boolean flag) {
      return new FeatureOptionContainer(predicate0, flag);
   }
}
