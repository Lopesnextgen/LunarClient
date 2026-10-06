package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.bridge.MovementInputMarker;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModLifecycle;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.listener.Nameplate;
import com.moonsworth.lunar.client.framework.listener.GuiRewindhandlersHandler;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;

abstract class ParentTHandler<T extends ParentT, ParentT extends MovementInputMarker>
   implements EntityChangeListener<ParentT>,
   EntityQuery<T>,
   IterableExtension<T>,
   EntitySubscription<T> {
   private final Int2ObjectOpenHashMap<T> field1 = new Int2ObjectOpenHashMap();
   private final WeakHashMap<EntityChangeListener<T>, Object> field2 = new WeakHashMap<>();
   private final IterableExtension<ParentT> field3;
   private final List<Consumer<T>> field4 = new ArrayList<>();
   private final List<Consumer<T>> field5 = new ArrayList<>();
   private final List<Consumer<T>> field6 = new ArrayList<>();
   private int field7;

   ParentTHandler(IterableExtension<ParentT> iterableextension1) {
      this.field3 = iterableextension1;
   }

   protected void onEnable() {
      for (MovementInputMarker bridgeextension2 : this.field3) {
         this.method7(bridgeextension2);
      }
   }

   protected void onDisable() {
      this.clear();
   }

   public void method1() {
      if (this.field7 == 0) {
         this.onEnable();
         this.field3.COOHCOCCROIIRRCHCORICOIIIIIIII();
      }

      this.field7++;
   }

   public void method3() {
      this.field7--;
      if (this.field7 == 0) {
         this.onDisable();
         this.field3.OOICHHCCHHCCHOCOCICCCOOOOCHCII();
      }
   }

   protected boolean method5(T value1) {
      if (this.field1.containsKey(value1.bridge$getEntityId())) {
         return false;
      }

      this.field1.put(value1.bridge$getEntityId(), value1);
      this.field2.forEach((arg1x, arg2) -> arg1x.method1((T)value1));
      this.field4.forEach(arg1x -> arg1x.accept((T)value1));
      return true;
   }

   protected void method6(T value1) {
      if (this.field1.remove(value1.bridge$getEntityId()) != null) {
         this.field2.forEach((arg1x, arg2) -> arg1x.method2((T)value1));
         this.field5.forEach(arg1x -> arg1x.accept((T)value1));
      }
   }

   protected void method7(T value1) {
      if (!this.method5(value1)) {
         this.field2.forEach((arg1x, arg2) -> arg1x.method3((T)value1));
         this.field6.forEach(arg1x -> arg1x.accept((T)value1));
      }
   }

   @Override
   public void clear() {
      if (this.isEnabled()) {
         ObjectIterator objectiterator1 = this.field1.values().iterator();

         while (objectiterator1.hasNext()) {
            MovementInputMarker bridgeextension2 = (MovementInputMarker)objectiterator1.next();
            this.field5.forEach(arg1x -> arg1x.accept((T)bridgeextension2));
         }

         this.field1.clear();
         this.field2.forEach((arg0, arg1x) -> arg0.clear());
      }
   }

   @Override
   public boolean method4(MovementInputMarker bridgeextension1) {
      return this.field1.containsKey(bridgeextension1.bridge$getEntityId());
   }

   @Override
   public boolean isEmpty() {
      return this.field1.isEmpty();
   }

   @Override
   public Stream<T> stream() {
      return this.field1.values().stream();
   }

   @Override
   public EntitySubscription<T> method3(Consumer<T> consumer1) {
      this.field4.add(consumer1);
      return this;
   }

   @Override
   public EntitySubscription<T> method4(Consumer<T> consumer1) {
      this.field5.add(consumer1);
      return this;
   }

   @Override
   public EntitySubscription<T> method5(Consumer<T> consumer1) {
      this.field6.add(consumer1);
      return this;
   }

   @Override
   public EntityQuery<T> method2(Predicate<T> predicate1) {
      ParentTImpl parenttimpl2 = new ParentTImpl<>(this, predicate1);
      this.field2.put(parenttimpl2, true);

      for (MovementInputMarker bridgeextension4 : this) {
         parenttimpl2.method1(bridgeextension4);
      }

      return parenttimpl2;
   }

   @Override
   public <V extends T> EntityQuery<V> method1(Class<V> clazz1) {
      ClassFilteredEntityQuery parenttimpl22 = new ClassFilteredEntityQuery<>(this, clazz1);
      this.field2.put(parenttimpl22, true);

      for (MovementInputMarker bridgeextension4 : this) {
         parenttimpl22.method1(bridgeextension4);
      }

      return parenttimpl22;
   }

   @NotNull
   @Override
   public Iterator<T> iterator() {
      return this.field1.values().iterator();
   }

   @Override
   public EntitySubscription<T> method4(AbstractFeature framework7extension21) {
      framework7extension21.<ModLifecycle>method5(ModTraits.field12, arg0 -> ModLifecycle.method13()).method2(this);
      return this;
   }

   @Override
   public EntitySubscription<T> method3(com.moonsworth.lunar.client.framework.listener.DynamicListener guirewindhandlershandler21) {
      guirewindhandlershandler21.method12(this);
      return this;
   }

   @Override
   public EntitySubscription<T> method6(AbstractFeature framework7extension21, Nameplate nameplate2) {
      SimpleEntitySubscription holograms3iterator3 = new SimpleEntitySubscription<>(this);
      framework7extension21.<ModLifecycle>method5(ModTraits.field12, arg0 -> ModLifecycle.method13()).method2(new GuiRewindhandlersHandler(holograms3iterator3, nameplate2));
      return holograms3iterator3;
   }

   @Override
   public EntitySubscription<T> method5(com.moonsworth.lunar.client.framework.listener.DynamicListener guirewindhandlershandler21, Nameplate nameplate2) {
      SimpleEntitySubscription holograms3iterator3 = new SimpleEntitySubscription<>(this);
      guirewindhandlershandler21.method12(new GuiRewindhandlersHandler(holograms3iterator3, nameplate2));
      return holograms3iterator3;
   }

   protected boolean isEnabled() {
      return this.field7 > 0;
   }
}
