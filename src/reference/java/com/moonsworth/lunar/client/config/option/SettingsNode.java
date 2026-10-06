package com.moonsworth.lunar.client.config.option;

import java.util.List;
import java.util.function.BooleanSupplier;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.config.option.ClientOption;

public class SettingsNode implements OptionTreeNode<SettingsNode> {
   private final List<SettingsNode> field1;
   private final ClientOption<?> field2;
   private final List<SettingsNode> field3;
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
   public boolean equals(Object object) {
      return object instanceof SettingsNode lightinghandler22 ? lightinghandler22.method3().equals(this.method3()) : false;
   }

   @Generated
   public List<SettingsNode> method2() {
      return this.field1;
   }

   @Generated
   public ClientOption<?> method3() {
      return this.field2;
   }

   @Generated
   @Override
   public List<SettingsNode> getChildren() {
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
   public void method6(@Nullable BooleanSupplier booleansupplier1) {
      this.field4 = booleansupplier1;
   }

   @Generated
   public void method7(boolean flag1) {
      this.field5 = flag1;
   }

   @Generated
   public void method8(boolean flag1) {
      this.field6 = flag1;
   }

   @Generated
   public SettingsNode(
      List<SettingsNode> list, ClientOption<?> option, List<SettingsNode> list2, @Nullable BooleanSupplier booleansupplier4, boolean flag, boolean flag2
   ) {
      this.field1 = list;
      this.field2 = option;
      this.field3 = list2;
      this.field4 = booleansupplier4;
      this.field5 = flag;
      this.field6 = flag2;
   }
}
