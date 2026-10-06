package com.moonsworth.lunar.client.config.option;

import java.util.List;
import java.util.function.BooleanSupplier;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class OptionGraphNode implements OptionHierarchyNode<OptionGraphNode> {
   private final List<OptionGraphNode> field1;
   private final ClientOption<?> field2;
   private final List<OptionGraphNode> field3;
   @Nullable
   private BooleanSupplier field4;
   private boolean field5;
   private boolean field6;

   @Override
   public String toString() {
      return this.getClass().getSimpleName() + " - " + this.field2.toString();
   }

   @Override
   public int hashCode() {
      return this.field2.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      return var1 instanceof OptionGraphNode var2 ? var2.method3().equals(this.method3()) : false;
   }

   @Generated
   public List<OptionGraphNode> method2() {
      return this.field1;
   }

   @Generated
   public ClientOption<?> method3() {
      return this.field2;
   }

   @Generated
   @Override
   public List<OptionGraphNode> getChildren() {
      return this.field3;
   }

   @Nullable
   @Generated
   @Override
   public BooleanSupplier method1() {
      return this.field4;
   }

   @Generated
   public boolean method4() {
      return this.field5;
   }

   @Generated
   public boolean method5() {
      return this.field6;
   }

   @Generated
   public void method6(@Nullable BooleanSupplier var1) {
      this.field4 = var1;
   }

   @Generated
   public void method7(boolean var1) {
      this.field5 = var1;
   }

   @Generated
   public void method8(boolean var1) {
      this.field6 = var1;
   }

   @Generated
   public OptionGraphNode(
      List<OptionGraphNode> var1, ClientOption<?> var2, List<OptionGraphNode> list, @Nullable BooleanSupplier var4, boolean flag, boolean flag2
   ) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = list;
      this.field4 = var4;
      this.field5 = flag;
      this.field6 = flag2;
   }
}
