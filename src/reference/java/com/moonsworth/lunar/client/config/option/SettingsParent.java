package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.config.option.SettingsSectionBuilder;
import com.moonsworth.lunar.client.util.Annotation;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;

public class SettingsParent<Setting extends SettingsSectionBuilder<Setting>, Parent extends SettingsParent<Setting, Parent>>
   implements SettingsComposer<SettingsSectionBuilder<Setting>, Parent>,
   SettingsSectionBuilder<Parent> {
   private final SettingsComposer<SettingsSectionBuilder<Setting>, Parent> field1;
   private final SettingsSectionBuilder<?> field2;

   SettingsParent(SettingsComposer<Setting, Parent> var1, SettingsSectionBuilder<?> var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   public Parent method2(ClientOption<?>... var1) {
      this.field2.method1(var1);
      return (Parent)this;
   }

   public Parent method2(@NotNull BooleanSupplier var1) {
      this.field2.method3(var1);
      return (Parent)this;
   }

   public Parent method5(@NotNull BooleanSupplier var1) {
      this.field2.method4(var1);
      return (Parent)this;
   }

   public Parent method4(boolean var1) {
      this.field2.method5(var1);
      return (Parent)this;
   }

   @TestOnly
   @Override
   public void method6(boolean var1) {
      this.field2.method6(var1);
   }

   @Override
   public void method1(Consumer<SettingsComposer<SettingsSectionBuilder<Setting>, Parent>> var1, Consumer<SettingsSectionBuilder<Setting>> var2) {
      this.field1.method1(var1, var2);
   }

   public SettingsSectionBuilder<Setting> method7(ClientOption<?> var1, Consumer<Parent> var2) {
      return this.field1.method2(var1, var2);
   }

   public SettingsSectionBuilder<Setting> method8(ClientOption<?> var1, Consumer<Parent> var2, Consumer<SettingsSectionBuilder<Setting>> var3) {
      return this.field1.method4(var1, var2, var3);
   }

   public SettingsSectionBuilder<Setting> method9(ClientOption<?>... var1) {
      return this.field1.method6(var1);
   }

   public SettingsSectionBuilder<Setting> method11(ClientOption<?> var1, ClientOption<?>... var2) {
      return this.field1.method8(var1, var2);
   }

   public SettingsSectionBuilder<Setting> method12(ClientOption<?> var1, ClientOption<?>... var2) {
      return this.field1.method10(var1, var2);
   }

   public SettingsSectionBuilder<Setting> method13(ClientOption<?>... var1) {
      return this.field1.method12(var1);
   }

   public SettingsSectionBuilder<Setting> method13(@Annotation(method1 = Annotation.Type.SETTING_LABELS) String var1) {
      return this.field1.method15(var1);
   }

   public SettingsSectionBuilder<Setting> method19() {
      return this.field1.method21();
   }

   public SettingsSectionBuilder<Setting> method20() {
      return this.field1.method17();
   }

   @Override
   public List<SettingsSectionBuilder<Setting>> method18() {
      return this.field1.method18();
   }

   public Parent method17(int... var1) {
      this.field2.method2(var1);
      return (Parent)this;
   }

   public Parent method18(int... var1) {
      this.field2.ICRHORIIHOHROHOHOCOOHOOCOORRHO(var1);
      return (Parent)this;
   }

   public Parent method19(String... var1) {
      this.field2.method3(var1);
      return (Parent)this;
   }

   public Parent method20(String... var1) {
      this.field2.method4(var1);
      return (Parent)this;
   }

   public static <Setting extends SettingsSectionBuilder<Setting>, Parent extends SettingsParent<Setting, Parent>> SettingsParent<Setting, Parent> method21(
      SettingsComposer<Setting, Parent> var0, SettingsSectionBuilder<?> var1
   ) {
      return new SettingsParent<>(var0, var1);
   }
}
