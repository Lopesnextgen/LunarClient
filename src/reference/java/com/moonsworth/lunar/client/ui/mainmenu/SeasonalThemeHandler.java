package com.moonsworth.lunar.client.ui.mainmenu;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.MonthDay;
import java.util.function.BooleanSupplier;
import lombok.Generated;

public class SeasonalThemeHandler<T extends MainMenuTheme> implements SeasonalTheme<T> {
   private final SeasonalThemeHandler.Extension<T> field1;

   @Override
   public T method3() {
      return this.field1.create();
   }

   public static <T extends MainMenuTheme> SeasonalTheme<T> method2(SeasonalThemeHandler.Extension<T> var0, Month... var1) {
      return new SeasonalThemeHandler.Data<>(var0, var1);
   }

   public static <T extends MainMenuTheme> SeasonalTheme<T> method3(SeasonalThemeHandler.Extension<T> var0, MonthDay var1) {
      return new SeasonalThemeHandler.Data5<>(var0, var1);
   }

   public static <T extends MainMenuTheme> SeasonalTheme<T> method4(SeasonalThemeHandler.Extension<T> var0, MonthDay var1, MonthDay var2) {
      return new SeasonalThemeHandler.Data4<>(var0, var1, var2);
   }

   public static <T extends MainMenuTheme> SeasonalTheme<T> method5(SeasonalThemeHandler.Extension<T> var0, LocalDateTime var1, LocalDateTime var2) {
      return new SeasonalThemeHandler.Data3<>(var0, var1, var2);
   }

   public static <T extends MainMenuTheme> SeasonalTheme<T> method6(SeasonalThemeHandler.Extension<T> var0, boolean var1, BooleanSupplier var2) {
      return new SeasonalThemeHandler.Data2<>(new SeasonalThemeHandler<>(var0), var1, var2);
   }

   public static <T extends MainMenuTheme> SeasonalTheme<T> method7(SeasonalThemeHandler.Extension<T> var0, boolean var1, BooleanSupplier var2, Month... var3) {
      return new SeasonalThemeHandler.Data2<>(method2(var0, var3), var1, var2);
   }

   public static <T extends MainMenuTheme> SeasonalTheme<T> method8(SeasonalThemeHandler.Extension<T> var0, boolean var1, MonthDay var2, BooleanSupplier var3) {
      return new SeasonalThemeHandler.Data2<>(method3(var0, var2), var1, var3);
   }

   public static <T extends MainMenuTheme> SeasonalTheme<T> method9(
      SeasonalThemeHandler.Extension<T> var0, boolean var1, MonthDay var2, MonthDay var3, BooleanSupplier var4
   ) {
      return new SeasonalThemeHandler.Data2<>(method4(var0, var2, var3), var1, var4);
   }

   public static <T extends MainMenuTheme> SeasonalTheme<T> method10(SeasonalThemeHandler.Extension<T> var0, boolean var1) {
      return new SeasonalThemeHandler.Data6<>(method2(var0), var1);
   }

   @Generated
   private SeasonalThemeHandler(SeasonalThemeHandler.Extension<T> var1) {
      this.field1 = var1;
   }

   public static class Data<T extends MainMenuTheme> extends SeasonalThemeHandler<T> {
      private final Month[] field2;

      private Data(SeasonalThemeHandler.Extension<T> var1, Month[] var2) {
         super(var1);
         this.field2 = var2;
      }

      @Override
      public boolean method1(LocalDateTime var1, Month var2) {
         for (Month var6 : this.field2) {
            if (var6.equals(var2)) {
               return true;
            }
         }

         return false;
      }
   }

   public static class Data2<T extends MainMenuTheme> extends SeasonalThemeHandler.Data6<T> {
      private final BooleanSupplier field3;

      public Data2(SeasonalTheme<T> var1, boolean var2, BooleanSupplier var3) {
         super(var1, var2);
         this.field3 = var3;
      }

      @Override
      public boolean method1(LocalDateTime var1, Month var2) {
         return this.field3.getAsBoolean() && this.CICIRIOIROIHICCCRIOIOIRCOHCIIO.method1(var1, var2);
      }

      @Override
      public T method3() {
         return (T)this.CICIRIOIROIHICCCRIOIOIRCOHCIIO.method3();
      }
   }

   public static class Data3<T extends MainMenuTheme> extends SeasonalThemeHandler<T> {
      private final LocalDateTime field2;
      private final LocalDateTime field3;

      private Data3(SeasonalThemeHandler.Extension<T> var1, LocalDateTime var2, LocalDateTime var3) {
         super(var1);
         this.field2 = var2;
         this.field3 = var3;
      }

      @Override
      public boolean method1(LocalDateTime var1, Month var2) {
         return !var1.isBefore(this.field2) && var1.isBefore(this.field3);
      }
   }

   public static class Data4<T extends MainMenuTheme> extends SeasonalThemeHandler<T> {
      private final MonthDay field2;
      private final MonthDay field3;

      private Data4(SeasonalThemeHandler.Extension<T> var1, MonthDay var2, MonthDay var3) {
         super(var1);
         this.field2 = var2;
         this.field3 = var3;
      }

      @Override
      public boolean method1(LocalDateTime var1, Month var2) {
         LocalDateTime var3 = var1.withMonth(this.field2.getMonthValue()).withDayOfMonth(this.field2.getDayOfMonth());
         LocalDateTime var4 = var1.withMonth(this.field3.getMonthValue()).withDayOfMonth(this.field3.getDayOfMonth());
         return !var1.isBefore(var3) && var1.isBefore(var4);
      }
   }

   public static class Data5<T extends MainMenuTheme> extends SeasonalThemeHandler<T> {
      private final MonthDay field2;

      private Data5(SeasonalThemeHandler.Extension<T> var1, MonthDay var2) {
         super(var1);
         this.field2 = var2;
      }

      @Override
      public boolean method1(LocalDateTime var1, Month var2) {
         return this.field2.getMonthValue() == var1.getMonthValue() && this.field2.getDayOfMonth() == var1.getDayOfMonth();
      }
   }

   public static class Data6<T extends MainMenuTheme> implements SeasonalTheme<T> {
      protected final SeasonalTheme<T> field1;
      private boolean field2;

      @Override
      public boolean method2() {
         return this.field2 ? true : SeasonalTheme.super.method2();
      }

      @Override
      public T method3() {
         return this.field1.method3();
      }

      @Generated
      public Data6(SeasonalTheme<T> var1, boolean var2) {
         this.field1 = var1;
         this.field2 = var2;
      }
   }

   @FunctionalInterface
   public interface Extension<T extends MainMenuTheme> {
      T create();
   }
}
