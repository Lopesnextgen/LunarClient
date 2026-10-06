package com.moonsworth.lunar.client.driver.core.gui;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.files.Files6_2;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class GuiIterator implements Gui {
   private static final Lookup field1 = MethodHandles.lookup();
   private static final ClassValue<Map<String, GuiIterator.Data>> field2 = new ClassValue<Map<String, GuiIterator.Data>>() {
      protected Map<String, GuiIterator.Data> method1(Class<?> var1) {
         ConcurrentHashMap var2 = new ConcurrentHashMap();
         GuiIterator.method10(var1).forEach(var1x -> var2.put(var1x.field1, var1x));
         return var2;
      }
   };
   private final Map<String, Object> field3 = new ConcurrentHashMap<>();
   @Nullable
   private JsonObject field4 = null;
   private boolean dirty = false;
   private boolean field5 = false;

   public <T> void method1(String var1, T[] var2) {
      this.method2(var1, Arrays.asList(var2));
   }

   public <T> void method2(String var1, Collection<T> var2) {
      LinkedHashSet var4 = new LinkedHashSet();
      boolean var5 = false;

      for (Object var7 : var2) {
         Object var3;
         if (var7 instanceof GuiIterator var8) {
            var3 = var8;
         } else if (var7 instanceof GuiIterator.Extension var9) {
            var3 = var9.method15();
         } else if (var7 instanceof Gui var10) {
            var3 = var10;
         } else if (var7 instanceof JsonProviderLegacy var11) {
            var3 = var11.provide();
         } else if (var7 != null
            && !(var7 instanceof Boolean)
            && !(var7 instanceof CharSequence)
            && !(var7 instanceof Number)
            && !(var7 instanceof JsonElement)) {
            var3 = this.method9(var7, var1).method1(var7);
         } else {
            var3 = var7;
         }

         var5 |= var3 instanceof Gui;
         var4.add(var3);
      }

      this.method3(var1, var5 ? JsonSetSerializerLegacy.method3(var4) : JsonSetSerializerLegacy.method2(var4));
   }

   public void method3(String var1, @Nullable Object var2) {
      Object var3;
      if (var2 instanceof GuiIterator var4) {
         var3 = var4;
      } else if (var2 instanceof GuiIterator.Extension var5) {
         var3 = var5.method15();
      } else if (var2 instanceof Gui var6) {
         var3 = new GuiIterator.Data2(var6);
      } else if (var2 instanceof JsonProviderLegacy var7) {
         var3 = var7.provide();
      } else if (var2 != null && !(var2 instanceof Boolean) && !(var2 instanceof CharSequence) && !(var2 instanceof Number) && !(var2 instanceof JsonElement)) {
         var3 = this.method9(var2, var1).method1(var2);
      } else {
         var3 = var2;
      }

      if (var3 instanceof Gui) {
         this.field5 = true;
      }

      this.field3.compute(var1, (var2x, var3x) -> {
         boolean var4x = var3x == null;
         if (var4x && var3 == null) {
            return null;
         }

         if (var4x || !var3x.equals(var3)) {
            this.dirty = true;
         }

         return var3;
      });
   }

   @Override
   public JsonElement method128() {
      if (this.field5 && !this.dirty) {
         this.field3.forEach((var1, var2) -> {
            if (var2 instanceof GuiIterator var3) {
               this.dirty = this.dirty | var3.dirty;
            } else if (var2 instanceof GuiIterator.Data2 var4) {
               var4.method128();
            }
         });
      }

      if (this.dirty) {
         this.method5();
      }

      return this.field4;
   }

   private void method5() {
      this.dirty = false;
      if (this.field3.isEmpty()) {
         this.field4 = null;
      } else {
         this.field4 = new JsonObject();
         this.field3.forEach((var1, var2) -> this.field4.add(var1, method8(var2)));
      }
   }

   public void method6(GuiIterator var1) {
      this.field5 = this.field5 | var1.field5;
      this.field3.putAll(var1.field3);
      this.dirty = true;
   }

   public void method7(String var1, Object var2) {
      if (this.method9(var2, var1).method1(var2) instanceof GuiIterator var4) {
         this.method6(var4);
      } else {
         throw new IllegalStateException(var1 + " is not a ComposingJsonDataProvider");
      }
   }

   public void clear() {
      this.field3.clear();
      this.dirty = true;
   }

   public static JsonElement method8(Object var0) {
      if (var0 instanceof JsonElement var1) {
         return var1;
      } else if (var0 instanceof JsonProviderLegacy var2) {
         return var2.provide();
      } else {
         return var0 instanceof Gui var3 ? var3.method128() : ThreadModuleDump48.field22.toJsonTree(var0);
      }
   }

   private GuiIterator.Data method9(Object var1, String var2) {
      GuiIterator.Data var3 = field2.get(var1.getClass()).get(var2);
      if (var3 != null) {
         return var3;
      } else {
         throw new IllegalStateException("No data provider with that name assigned");
      }
   }

   private static Set<GuiIterator.Data> method10(Class<?> var0) {
      return Arrays.stream(var0.getMethods())
         .filter(var0x -> var0x.isAnnotationPresent(Annotation.class))
         .map(var0x -> Files6_2.method1(var0x.getAnnotation(Annotation.class), var0x))
         .map(GuiIterator::method12)
         .map(GuiIterator::method11)
         .collect(Collectors.toSet());
   }

   private static GuiIterator.Data method11(Files6_2<Annotation, CallSite> var0) {
      try {
         return new GuiIterator.Data(var0.field1.value(), (Function)var0.field2.getTarget().invokeExact());
      } catch (Throwable var2) {
         throw new RuntimeException(var2);
      }
   }

   private static Files6_2<Annotation, CallSite> method12(Files6_2<Annotation, Method> var0) {
      try {
         MethodHandle var1 = field1.unreflect(var0.field2);
         return Files6_2.method1(
            var0.field1,
            LambdaMetafactory.metafactory(
               field1, "apply", MethodType.methodType(Function.class), MethodType.methodType(Object.class, Object.class), var1, var1.type()
            )
         );
      } catch (Throwable var2) {
         throw new RuntimeException(var2);
      }
   }

   @Generated
   public Map<String, Object> method13() {
      return this.field3;
   }

   private class Data {
      private final String field1;
      private final Function<Object, Object> field2;

      private Data(String var1, Function<Object, Object> var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      Object method1(Object var1) {
         return this.field2.apply(var1);
      }

      public String name() {
         return this.field1;
      }

      public Function<Object, Object> method2() {
         return this.field2;
      }
   }

   protected class Data2 implements Gui {
      private final Gui field1;
      private JsonElement field2 = null;

      public Data2(Gui var2) {
         this.field1 = var2;
      }

      @Nullable
      @Override
      public JsonElement method128() {
         boolean var1 = this.field2 == null;
         JsonElement var2 = this.field1.method128();
         if (var1 && var2 == null) {
            return null;
         }

         if (var1 || !this.field2.equals(var2)) {
            GuiIterator.this.dirty = true;
         }

         this.field2 = var2;
         return var2;
      }
   }

   public interface Extension extends Gui, JsonProviderLegacy {
      GuiIterator getProvider();

      @Nullable
      @Override
      default JsonElement method128() {
         return this.getProvider() == null ? null : this.getProvider().method128();
      }

      @Override
      default JsonElement provide() {
         return this.getProvider() == null ? null : this.getProvider().method128();
      }
   }
}
