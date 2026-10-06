package com.moonsworth.lunar.client.config.option.trait;

import java.util.Optional;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface TraitReader {
   @Nullable
   <T> T method1(TraitType<T> lightoverlay91);

   default boolean method2(TraitType<?> lightoverlay91) {
      return this.method1(lightoverlay91) != null;
   }

   default <T> Optional<T> method3(TraitType<T> lightoverlay91) {
      return Optional.ofNullable(this.method1(lightoverlay91));
   }

   @NotNull
   default <T> T method4(TraitType<T> lightoverlay91) {
      return this.method1(lightoverlay91);
   }

   @Contract("_,!null->!null; _,_->_")
   default <T> T method5(TraitType<T> lightoverlay91, T t) {
      Object obj3 = this.method1(lightoverlay91);
      return (T)(obj3 != null ? obj3 : t);
   }
}
