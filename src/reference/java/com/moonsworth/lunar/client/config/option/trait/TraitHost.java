package com.moonsworth.lunar.client.config.option.trait;

import java.util.stream.Stream;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.config.option.trait.TraitContainer;

public interface TraitHost extends TraitReader {
   TraitContainer method2();

   default <T> Stream<T> method2(Class<T> clazz1) {
      return this.method2().stream().map(Trait::value).filter(arg1x -> clazz1.isAssignableFrom(arg1x.getClass())).map(arg0 -> (T)arg0);
   }

   @Nullable
   @Override
   default <T> T method1(TraitType<T> lightoverlay91) {
      return this.method2().method1(lightoverlay91);
   }

   @Override
   default <T> T method5(TraitType<T> lightoverlay91, T value2) {
      return (T)this.method2().HHRROIIHRRICIIHIIHICRHHRHOHHOO(lightoverlay91, value2);
   }
}
