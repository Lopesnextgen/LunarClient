package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.alert.mixin.Alert2;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.util.ThreadModuleDump93;
import java.util.List;
import java.util.function.BooleanSupplier;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;

public class ResolvedOptionNode<Child> implements OptionHierarchyNode<Child> {
   @Nullable
   private final String field1;
   private final List<Child> field2;
   @Nullable
   private BooleanSupplier field3;
   private boolean field4;

   public ResolvedOptionNode(@Nullable String var1, List<Child> var2, @Nullable BooleanSupplier var3) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var3 != null;
   }

   @NotNull
   @Override
   public List<Child> getChildren() {
      return this.field2 == null ? List.of() : this.field2;
   }

   public boolean method1(ClientOption<?> var1) {
      return this.method2(var1, true);
   }

   @VisibleForTesting
   public boolean method2(ClientOption<?> var1, boolean var2) {
      if (this.field3 != null) {
         boolean var3 = this.field3.getAsBoolean();
         if (this.field4 != var3) {
            this.field4 = var3;
            if (var2) {
               ThreadModuleDump93.method1();
            }
         }

         return var3 || var1.method3(OptionTraits.field5).flatMap(Alert2::method3).isPresent();
      } else {
         return var1.method3(OptionTraits.field5).flatMap(Alert2::method3).isPresent();
      }
   }

   @Nullable
   @Generated
   public String getFeatureId() {
      return this.field1;
   }

   @Nullable
   @Generated
   @Override
   public BooleanSupplier method1() {
      return this.field3;
   }

   @Generated
   public boolean method4() {
      return this.field4;
   }

   @Generated
   public void method5(@Nullable BooleanSupplier var1) {
      this.field3 = var1;
   }
}
