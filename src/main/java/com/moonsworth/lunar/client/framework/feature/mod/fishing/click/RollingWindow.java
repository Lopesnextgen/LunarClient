package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;

public class RollingWindow<T> {
   private final Deque<Collection<T>> windows = new ArrayDeque<>();
   private Collection<T> current = new ArrayList<>();

   public RollingWindow(int value) {
      for (int index2 = 0; index2 < value + 1; index2++) {
         this.windows.add(new ArrayList<>());
      }
   }

   public void add(T t) {
      this.windows.getLast().add((T)t);
   }

   public Collection<T> advance() {
      this.current.clear();
      this.windows.addLast(this.current);
      this.current = this.windows.pollFirst();
      return this.current;
   }

   public void clear() {
      this.windows.forEach(Collection::clear);
      this.current.clear();
   }
}
