package com.moonsworth.lunar.client.framework.listener;

import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.ui.hud.MixinCore5;
import com.moonsworth.lunar.client.ui.hud.TextHudComponent;
import com.moonsworth.lunar.client.ui.hud.MixinCore5Iterator;
import com.moonsworth.lunar.client.ui.hud.MixinCore5Task;
import com.moonsworth.lunar.client.guiRewindhandlers.nameplate.Nameplate;
import com.moonsworth.lunar.client.highlight.Highlight;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.mod.misc.debug.DynamiclistenerDebugMod;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlers3;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlers;
import com.moonsworth.lunar.client.guiRewindhandlers.Annotation2;

public abstract class DynamicListener implements GuiRewindhandlers {
   private static final Map<Class<?>, DynamicListener> field1 = new HashMap<>();
   private final Nameplate field2 = new Nameplate();
   private final Set<GuiRewindhandlers> field3 = new HashSet<>();
   private int field4 = 0;
   private boolean enabled = false;
   private Runnable field5;
   private boolean field6;

   private void method2() {
      this.field2.method2();
      this.field3.forEach(GuiRewindhandlers::method1);
      this.enabled = true;
      this.onEnable();
   }

   private void method4() {
      this.onDisable();
      this.enabled = false;
      this.field3.forEach(GuiRewindhandlers::method3);
      this.field2.method3();
   }

   private void init() {
      this.field6 = true;
      Runnable var1 = () -> this.field5.run();
      GuiRewindhandlers3 var2 = GuiRewindhandlers3.method2(var1);
      this.field5 = () -> {
         boolean var2x = var2.method3(() -> this.field4 != 0 && this.isEnabled());
         if (var2x != this.enabled) {
            if (var2x) {
               this.method2();
            } else {
               this.method4();
            }
         }
      };
      this.field5.run();
   }

   protected <T extends DynamicListener> T method3(Class<T> var1) {
      if (this.field6) {
         throw new IllegalStateException(
            "DynamicListener#addDependency() must be called before the listener is initialized! (Do it in the constructor or field initialization)"
         );
      }

      DynamicListener var2 = method7(var1);
      this.field3.add(var2);
      DynamiclistenerDebugMod.method5(this, var2);
      return (T)var2;
   }

   protected static <T extends DynamicListener> T method4(Class<T> var0) {
      DynamicListener var1 = method7(var0);
      var1.method1();
      DynamiclistenerDebugMod.method5("GLOBAL DEP", var1);
      return (T)var1;
   }

   @Override
   public void method1() {
      int var1 = ++this.field4;
      if (var1 == 1 && this.field5 != null) {
         this.field5.run();
      }
   }

   @Override
   public void method3() {
      int var1 = --this.field4;
      if (var1 == 0 && this.field5 != null) {
         this.field5.run();
      }
   }

   public static <T extends DynamicListener> T method7(Class<T> var0) {
      DynamicListener var1 = field1.get(var0);
      if (var1 == null) {
         method8(var0);
         DynamicListener var2 = field1.get(var0);
         if (var2 != null) {
            var1 = var2;
         } else {
            var1 = method10(var0);
            DynamicListener var3 = field1.get(var0);
            if (var3 != null) {
               var1 = var3;
            } else {
               field1.put(var0, var1);
            }
         }
      }

      return (T)var1;
   }

   private static void method8(Class<?> var0) {
      try {
         Class.forName(var0.getName(), true, var0.getClassLoader());
      } catch (ClassNotFoundException var2) {
         throw new AssertionError(var2);
      }
   }

   public static <T extends DynamicListener> Optional<T> method9(Class<T> var0) {
      DynamicListener var1 = field1.get(var0);
      return var1 != null && var1.isEnabled2() ? Optional.of((T)var1) : Optional.empty();
   }

   private static synchronized DynamicListener method10(Class<? extends DynamicListener> var0) {
      try {
         Constructor var1 = var0.getDeclaredConstructor();
         boolean var2 = Modifier.isPublic(var1.getModifiers());
         if (!var2) {
            var1.setAccessible(true);
         }

         DynamicListener var3 = (DynamicListener)var1.newInstance();
         if (!var2) {
            var1.setAccessible(false);
         }

         var3.init();
         return var3;
      } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException var4) {
         throw new RuntimeException(var4);
      }
   }

   @Annotation2(method1 = Annotation2.Type.DYNAMICLISTENER_ISENABLED)
   protected boolean isEnabled() {
      return true;
   }

   protected void onEnable() {
   }

   protected void onDisable() {
   }

   protected <T extends Highlight> void handle(Class<T> var1, Consumer<T> var2) {
      this.method11(var1, var2, 100);
   }

   protected <T extends Highlight> void method11(Class<T> var1, Consumer<T> var2, int var3) {
      this.field2.method1(var2, var3, var1);
   }

   public void method12(GuiRewindhandlers var1) {
      this.field3.add(var1);
   }

   public boolean isEnabled2() {
      return this.enabled;
   }

   public static List<String> method14() {
      ArrayList var0 = new ArrayList();

      for (DynamicListener var2 : field1.values().stream().sorted(Comparator.comparing(var0x -> var0x.getClass().getName())).toList()) {
         boolean var3 = var2.isEnabled2();
         StringBuilder var4 = new StringBuilder();
         var4.append(var2.getClass().getName()).append(": ").append(var3 ? "ENABLED" : "DISABLED").append(" (").append(var2.field4).append(" deps)");
         var0.add(var4.toString());

         for (String var6 : DynamiclistenerDebugMod.method8(var2, true)) {
            var0.add("  - " + var6);
         }
      }

      return var0;
   }

   public static List<MixinCore5> method15(ColorOption var0, ColorOption var1) {
      ArrayList var2 = new ArrayList();

      for (DynamicListener var4 : field1.values().stream().sorted(Comparator.comparing(var0x -> var0x.getClass().getSimpleName())).toList()) {
         boolean var5 = var4.isEnabled2();
         var2.add(
            new MixinCore5Task(
                  new MixinCore5Iterator()
                     .method5(new TextHudComponent(var4.getClass().getSimpleName()))
                     .method5(new TextHudComponent(": ").method9(AdventureChatFormatting.GRAY))
                     .method5(new TextHudComponent(var5 ? "ENABLED" : "DISABLED").method7(var5 ? var0 : var1))
                     .method5(new TextHudComponent(" ("))
                     .method5(new TextHudComponent(String.valueOf(var4.field4)))
                     .method5(new TextHudComponent(" deps)"))
               )
               .method3(DynamiclistenerDebugMod.method7(var4))
         );
      }

      return var2;
   }
}
