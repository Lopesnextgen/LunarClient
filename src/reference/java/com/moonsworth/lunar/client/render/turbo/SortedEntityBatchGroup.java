package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.client.render.turbo.EntityBatchType;
import com.moonsworth.lunar.client.util.ThreadModuleDumpIterator2;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;
import org.jetbrains.annotations.NotNull;

@Annotation2(min = 8)
public class SortedEntityBatchGroup<ID> extends EntityBatchGroup<ID> implements SectionBatchGroup<ID> {
   private final Set<ID> field6 = new HashSet<>();

   public SortedEntityBatchGroup(EntityBatchType<ID> var1) {
      super(var1);
   }

   @NotNull
   @Override
   public Iterator<ID> iterator() {
      return new ThreadModuleDumpIterator2(super.iterator(), var1 -> !this.field6.contains(var1));
   }

   @Override
   public void method1() {
      this.method2();
   }

   protected void method2() {
      this.field6.clear();
   }

   @Override
   public void method4(ID var1) {
      this.field6.add((ID)var1);
   }

   @Override
   public void method5(ID var1) {
      this.field6.remove(var1);
   }

   @Override
   public boolean method4(ID var1, Predicate<ID> predicate) {
      if (this.field6.contains(var1)) {
         return true;
      } else if (predicate.test(var1)) {
         this.field6.add((ID)var1);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean method6(ID var1) {
      return this.field6.contains(var1);
   }

   @Override
   public void clear() {
      super.clear();
      this.field6.clear();
   }

   @Override
   public void method2(ID var1) {
      super.method2((ID)var1);
      this.field6.remove(var1);
   }

   @Override
   public void method3(ID var1) {
      super.method3((ID)var1);
      this.field6.remove(var1);
   }
}
