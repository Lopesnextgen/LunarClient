package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import java.util.stream.Stream;

public interface RouteSubcommand {
   void method1(String[] items1);

   Stream<String> method2(String[] items1);
}
