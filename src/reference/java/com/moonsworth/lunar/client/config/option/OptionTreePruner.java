package com.moonsworth.lunar.client.config.option;

import it.unimi.dsi.fastutil.Pair;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.BooleanSupplier;
import org.jetbrains.annotations.Nullable;

public abstract class OptionTreePruner<OUT extends OptionHierarchyNode<?>, Child> implements OptionTreeMapper<OptionGraphNode, OUT, Child> {
   public abstract OUT method1(OptionGraphNode var1, List<Child> var2, @Nullable BooleanSupplier var3);

   public OUT method2(OptionGraphNode var1) {
      return this.method1(var1, this.method6(var1), var1.method1());
   }

   @Override
   public Pair<Map<ClientOption<?>, OptionGraphNode>, Collection<OptionGraphNode>> method4(Map<ClientOption<?>, OptionGraphNode> var1) {
      HashSet var2 = new HashSet();
      Iterator var3 = var1.entrySet().iterator();

      while (var3.hasNext()) {
         Entry var4 = (Entry)var3.next();
         OptionGraphNode var5 = (OptionGraphNode)var4.getValue();
         if (var5.method4() || this.method5(var5, var2)) {
            var3.remove();
            var2.add(var5);
         }

         this.method4(var5, var2);
      }

      return Pair.of(var1, var2);
   }

   protected void method4(OptionGraphNode var1, Set<OptionGraphNode> var2) {
      Iterator var3 = var1.getChildren().iterator();

      while (var3.hasNext()) {
         OptionGraphNode var4 = (OptionGraphNode)var3.next();
         if (var4.method4() || this.method5(var4, var2)) {
            var3.remove();
            var2.add(var4);
         }

         this.method4(var4, var2);
      }
   }

   boolean method5(OptionGraphNode var1, Set<OptionGraphNode> var2) {
      if (var1.method2().isEmpty()) {
         return false;
      }

      for (OptionGraphNode var4 : var1.method2()) {
         if (!var4.method4() && !var2.contains(var4)) {
            return false;
         }
      }

      return true;
   }

   public List<Child> method6(OptionGraphNode var1) {
      LinkedList var2 = new LinkedList();

      for (OptionGraphNode var4 : var1.getChildren()) {
         var2.add(this.method4(var4));
      }

      return var2;
   }
}
