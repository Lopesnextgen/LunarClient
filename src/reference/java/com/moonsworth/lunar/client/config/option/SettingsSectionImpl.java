package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BooleanSupplier;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;

public class SettingsSectionImpl implements SettingsSection<SettingsSectionImpl> {
   private final List<ClientOption<?>> field1;
   private final List<ClientOption<?>> field2 = new ArrayList<>();
   @Nullable
   private BooleanSupplier field3 = null;
   private boolean field4;
   private boolean field5;

   public SettingsSectionImpl(ClientOption<?>[] items1) {
      this.field1 = new LinkedList<>(List.of(items1));
   }

   public SettingsSectionImpl method2(ClientOption<?>... items1) {
      this.field2.addAll(Arrays.asList(items1));
      return this;
   }

   public SettingsSectionImpl method2(@NotNull BooleanSupplier booleansupplier1) {
      if (this.field3 == null) {
         this.field3 = booleansupplier1;
      } else {
         BooleanSupplier booleansupplier2 = this.field3;
         this.field3 = () -> booleansupplier2.getAsBoolean() || booleansupplier1.getAsBoolean();
      }

      return this;
   }

   public SettingsSectionImpl method5(@NotNull BooleanSupplier booleansupplier1) {
      if (booleansupplier1.getAsBoolean()) {
         this.field4 = true;
      }

      return this;
   }

   public SettingsSectionImpl method4(boolean flag1) {
      this.field5 = flag1;
      return this;
   }

   @TestOnly
   @Override
   public void method6(boolean flag1) {
      this.field4 |= flag1;
   }

   public SettingsSectionImpl method6(int... items1) {
      for (int index5 : items1) {
         if (Ref.MC_VERSION == index5) {
            return this;
         }
      }

      this.field4 = true;
      return this;
   }

   public SettingsSectionImpl method7(int... items1) {
      for (int index5 : items1) {
         if (Ref.MC_VERSION == index5) {
            this.field4 = true;
            break;
         }
      }

      return this;
   }

   public SettingsSectionImpl method8(String... items1) {
      for (String text5 : items1) {
         if (!Ref.hasModule(text5)) {
            this.field4 = true;
            break;
         }
      }

      return this;
   }

   public SettingsSectionImpl method9(String... items1) {
      for (String text5 : items1) {
         if (Ref.hasModule(text5)) {
            this.field4 = true;
            break;
         }
      }

      return this;
   }

   @Override
   public boolean method5() {
      return this.field5;
   }

   public SettingsSectionImpl method11(int index1) {
      ArrayList list2 = new ArrayList(this.field1.size() - index1);

      for (int index3 = index1; index3 < this.field1.size(); index3++) {
         list2.add(this.field1.remove(index1));
      }

      SettingsSectionImpl threadmoduledump43extension224 = new SettingsSectionImpl(list2);
      threadmoduledump43extension224.field2.addAll(this.field2);
      threadmoduledump43extension224.field3 = this.field3;
      threadmoduledump43extension224.field4 = this.field4;
      return threadmoduledump43extension224;
   }

   @Generated
   @Override
   public List<ClientOption<?>> method1() {
      return this.field1;
   }

   @Generated
   @Override
   public List<ClientOption<?>> method2() {
      return this.field2;
   }

   @Nullable
   @Generated
   @Override
   public BooleanSupplier method3() {
      return this.field3;
   }

   @Generated
   @Override
   public boolean method4() {
      return this.field4;
   }

   @Generated
   public boolean method17() {
      return this.field5;
   }

   @Generated
   public SettingsSectionImpl(List<ClientOption<?>> list1) {
      this.field1 = list1;
   }
}
