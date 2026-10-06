package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;

public class Click8<T> {
   private final Deque<Collection<T>> windows = new ArrayDeque<>();
   private Collection<T> current = new ArrayList<>();

   public Click8(int var1) {
      for (int var2 = 0; var2 < var1 + 1; var2++) {
         this.windows.add(new ArrayList<>());
      }
   }

   public void add(T var1) {
      this.windows.getLast().add((T)var1);
   }

   public Collection<T> next() {
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
