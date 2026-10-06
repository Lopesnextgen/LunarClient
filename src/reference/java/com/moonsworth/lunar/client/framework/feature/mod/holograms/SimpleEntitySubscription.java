package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.bridge.MovementInputMarker;
import com.moonsworth.lunar.client.framework.listener.GuiRewindhandlers;
import com.moonsworth.lunar.client.util.collection.EmptyIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;

class SimpleEntitySubscription<Parent extends EntitySubscription<T> & GuiRewindhandlers, T extends MovementInputMarker> implements EntitySubscription<T>, GuiRewindhandlers {
   private final Parent field1;
   private final List<Consumer<T>> field2 = new ArrayList<>();
   private final List<Consumer<T>> field3 = new ArrayList<>();
   private final List<Consumer<T>> field4 = new ArrayList<>();
   private int field5;

   SimpleEntitySubscription(Parent parent1) {
      this.field1 = (Parent)parent1;
      this.field1.method3(this::method1);
      this.field1.method4(this::method2);
      this.field1.method5(this::method3);
   }

   private void method1(T value1) {
      if (this.field5 != 0) {
         this.field2.forEach(arg1x -> arg1x.accept((T)value1));
      }
   }

   private void method2(T value1) {
      if (this.field5 != 0) {
         this.field3.forEach(arg1x -> arg1x.accept((T)value1));
      }
   }

   private void method3(T value1) {
      if (this.field5 != 0) {
         this.field4.forEach(arg1x -> arg1x.accept((T)value1));
      }
   }

   @Override
   public boolean method4(MovementInputMarker movementInputMarker) {
      return this.field5 == 0 ? false : this.field1.method4(movementInputMarker);
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
   public EntitySubscription<T> method3(Consumer<T> consumer1) {
      this.field2.add(consumer1);
      return this;
   }

   @Override
   public EntitySubscription<T> method4(Consumer<T> consumer1) {
      this.field3.add(consumer1);
      return this;
   }

   @Override
   public EntitySubscription<T> method5(Consumer<T> consumer1) {
      this.field4.add(consumer1);
      return this;
   }

   @NotNull
   @Override
   public Iterator<T> iterator() {
      return (Iterator<T>)(this.field5 == 0 ? new EmptyIterator() : this.field1.iterator());
   }

   public void method1() {
      this.field5++;
      if (this.field5 == 1) {
         this.field2.forEach(arg1 -> {
            for (MovementInputMarker bridgeextension3 : this.field1) {
               arg1.accept((T)bridgeextension3);
            }
         });
      }

      this.field1.method1();
   }

   public void method3() {
      this.field5--;
      this.field1.method3();
      if (this.field5 == 0) {
         this.field3.forEach(arg1 -> {
            for (MovementInputMarker bridgeextension3 : this.field1) {
               arg1.accept((T)bridgeextension3);
            }
         });
      }
   }
}
