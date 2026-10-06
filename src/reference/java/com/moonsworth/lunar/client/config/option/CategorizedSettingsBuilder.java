package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.config.option.SettingsSectionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.driver.PhosphorIconLegacy;
import com.moonsworth.lunar.client.util.Annotation;
import it.unimi.dsi.fastutil.Pair;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.intellij.lang.annotations.Subst;
import org.jetbrains.annotations.Nullable;

public interface CategorizedSettingsBuilder<Setting extends SettingsSectionBuilder<Setting>, Node extends OptionHierarchyNode<Node>, Parent extends SettingsParent<Setting, Parent>>
   extends SettingsComposer<Setting, Parent> {
   Setting method1(@Annotation(method1 = Annotation.Type.SETTING_LABELS) String var1, Consumer<Parent> var2);

   Setting method2(@Annotation(method1 = Annotation.Type.SETTING_LABELS) String var1, PhosphorIconLegacy var2, Consumer<Parent> var3);

   Setting method3(Supplier<String> var1, Consumer<Parent> var2);

   default Setting method4(SettingsPage var1, Consumer<Parent> var2) {
      return this.method2(var1.getName(), var1.getIcon(), var2);
   }

   Setting method5(@Subst("generalOptions") SettingsPage var1);

   void method6(ClientOption<?> var1);

   void method7(ClientOption<?> var1, ClientOption<?>... var2);

   default void method8(ClientOption<?> var1, OptionSupplier<?, ?>... var2) {
      this.method7(var1, OptionSupplier.method2(var2));
   }

   void method9(ClientOption<?> var1, ClientOption<?>... var2);

   default void method10(ClientOption<?> var1, OptionSupplier<?, ?>... var2) {
      this.method9(var1, OptionSupplier.method2(var2));
   }

   default void method11(OptionSupplier<?, ?> var1, OptionSupplier<?, ?>... var2) {
      this.method9(var1.method1(), OptionSupplier.method2(var2));
   }

   Map<ClientOption<?>, Node> method12();

   static <N extends OptionHierarchyNode<ClientOption<?>>, P> Set<ClientOption<?>> method13(
      Map<ClientOption<?>, N> var0, Map<ClientOption<?>, P> var1, Predicate<P> var2
   ) {
      return method14(var0, var2x -> {
         Object var3 = var1.get(var2x);
         return var3 != null && var2.test(var3);
      });
   }

   static <N extends OptionHierarchyNode<ClientOption<?>>> Set<ClientOption<?>> method14(
      Map<ClientOption<?>, N> var0, Predicate<ClientOption<?>> var1
   ) {
      LinkedHashSet var2 = new LinkedHashSet();

      for (Entry var4 : var0.entrySet()) {
         if (var1.test((ClientOption)var4.getKey())) {
            var2.add((ClientOption)var4.getKey());
         }
      }

      return var2;
   }

   static Set<ClientOption<?>> method15(
      Map<ClientOption<?>, OptionGraphNode> var0,
      OptionTreePruner<ResolvedOptionNode<ClientOption<?>>, ?> var1,
      boolean var2,
      @Nullable Consumer<ClientOption<?>> var3
   ) {
      if (var2) {
         Pair var4 = var1.method4(var0);
         var0 = (Map)var4.key();
         if (var3 != null) {
            for (OptionGraphNode var6 : (Collection)var4.value()) {
               var3.accept(var6.method3());
            }
         }
      }

      Map var7 = var1.method1(var0);

      for (Entry var9 : var7.entrySet()) {
         ((ClientOption)var9.getKey()).method21((ResolvedOptionNode<?>)var9.getValue());
      }

      return method13(var7, var0, var0x -> var0x.method2().isEmpty());
   }
}
