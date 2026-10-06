package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.config.override.SettingIntercept;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.DirtyFlag;
import java.util.List;
import java.util.function.BooleanSupplier;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;
import com.moonsworth.lunar.client.config.option.ClientOption;

public class BakedOptionNode<Child> implements OptionTreeNode<Child> {
   @Nullable
   private final String field1;
   private final List<Child> field2;
   @Nullable
   private BooleanSupplier field3;
   private boolean field4;

   public BakedOptionNode(@Nullable String text1, List<Child> list2, @Nullable BooleanSupplier booleansupplier3) {
      this.field1 = text1;
      this.field2 = list2;
      this.field3 = booleansupplier3;
      this.field4 = booleansupplier3 != null;
   }

   @NotNull
   @Override
   public List<Child> getChildren() {
      return this.field2 == null ? List.of() : this.field2;
   }

   public boolean method1(ClientOption<?> lightingextension1) {
      return this.method2(lightingextension1, true);
   }

   @VisibleForTesting
   public boolean method2(ClientOption<?> lightingextension1, boolean flag2) {
      if (this.field3 != null) {
         boolean flag3 = this.field3.getAsBoolean();
         if (this.field4 != flag3) {
            this.field4 = flag3;
            if (flag2) {
               DirtyFlag.method1();
            }
         }

         return flag3 || lightingextension1.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(OptionTraits.field5).flatMap(SettingIntercept::method3).isPresent();
      } else {
         return lightingextension1.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(OptionTraits.field5).flatMap(SettingIntercept::method3).isPresent();
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
   public void method5(@Nullable BooleanSupplier booleansupplier1) {
      this.field3 = booleansupplier1;
   }
}
