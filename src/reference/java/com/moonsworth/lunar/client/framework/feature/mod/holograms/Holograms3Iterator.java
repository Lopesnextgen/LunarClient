package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlers;
import com.moonsworth.lunar.client.util.ThreadModuleDumpIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;

class Holograms3Iterator<Parent extends Holograms3<T> & GuiRewindhandlers, T extends BridgeExtension> implements Holograms3<T>, GuiRewindhandlers {
   private final Parent field1;
   private final List<Consumer<T>> field2 = new ArrayList<>();
   private final List<Consumer<T>> field3 = new ArrayList<>();
   private final List<Consumer<T>> field4 = new ArrayList<>();
   private int field5;

   Holograms3Iterator(Parent var1) {
      this.field1 = (Parent)var1;
      this.field1.method3(this::method1);
      this.field1.method4(this::method2);
      this.field1.method5(this::method3);
   }

   private void method1(T var1) {
      if (this.field5 != 0) {
         this.field2.forEach(var1x -> var1x.accept((T)var1));
      }
   }

   private void method2(T var1) {
      if (this.field5 != 0) {
         this.field3.forEach(var1x -> var1x.accept((T)var1));
      }
   }

   private void method3(T var1) {
      if (this.field5 != 0) {
         this.field4.forEach(var1x -> var1x.accept((T)var1));
      }
   }

   @Override
   public boolean method4(BridgeExtension var1) {
      return this.field5 == 0 ? false : this.field1.method4(var1);
   }

   @Override
   public boolean isEmpty() {
      return this.field5 == 0 ? true : this.field1.isEmpty();
   }

   @Override
   public Stream<T> stream() {
      return this.field5 == 0 ? Stream.empty() : this.field1.stream();
   }

   @Override
   public Holograms3<T> method3(Consumer<T> var1) {
      this.field2.add(var1);
      return this;
   }

   @Override
   public Holograms3<T> method4(Consumer<T> var1) {
      this.field3.add(var1);
      return this;
   }

   @Override
   public Holograms3<T> method5(Consumer<T> var1) {
      this.field4.add(var1);
      return this;
   }

   @NotNull
   @Override
   public Iterator<T> iterator() {
      return (Iterator<T>)(this.field5 == 0 ? new ThreadModuleDumpIterator() : this.field1.iterator());
   }

   public void method1() {
      this.field5++;
      if (this.field5 == 1) {
         this.field2.forEach(var1 -> {
            for (BridgeExtension var3 : this.field1) {
               var1.accept((T)var3);
            }
         });
      }

      this.field1.method1();
   }

   public void method3() {
      this.field5--;
      this.field1.method3();
      if (this.field5 == 0) {
         this.field3.forEach(var1 -> {
            for (BridgeExtension var3 : this.field1) {
               var1.accept((T)var3);
            }
         });
      }
   }
}
