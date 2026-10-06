package com.moonsworth.lunar.client.config.option.trait;

import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.config.option.trait.TraitMutator;
import com.moonsworth.lunar.client.config.option.trait.TraitContainer;

public interface MutableTraitHost extends TraitMutator, TraitContainer {
   @Nullable
   <T> T set(int number1, @Nullable T value2);

   <T> void method1(TraitType<T> lightoverlay91, TraitListener<T> lightoverlay52);

   <T> void method2(TraitType<T> lightoverlay91, TraitListener<T> lightoverlay52);

   MutableTraitHost method3();
}
