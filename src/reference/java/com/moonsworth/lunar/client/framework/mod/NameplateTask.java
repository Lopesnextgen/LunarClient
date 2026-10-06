package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.AlertExtension;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework10Extension;
import com.moonsworth.lunar.client.framework.mod.Framework11;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.command.MixinNameplate2;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlers;
import com.moonsworth.lunar.client.highlight.Highlight;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.ListenerRegistration;
import com.moonsworth.lunar.client.event.mixin.command.EventCommandLegacy;
import com.moonsworth.lunar.client.event.mixin.command.EventCommandRegisterLegacy;
import com.moonsworth.lunar.client.event.mixin.command.EventCommandLegacy.Data;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Consumer;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class NameplateTask implements Framework10Extension {
   private final Map<Class<Highlight>, ListenerRegistration<Highlight>> field1 = new HashMap<>();
   private final Set<GuiRewindhandlers> field2 = new HashSet<>();
   private final List<MixinNameplate2> field3 = new ArrayList<>();
   private final List<Framework7Extension> field4 = new ArrayList<>();
   private final List<Runnable> field5 = new ArrayList<>();
   private final List<Runnable> field6 = new ArrayList<>();
   private final List<Runnable> field7 = new ArrayList<>();
   private boolean field8 = false;
   private boolean field9 = false;
   private boolean field10 = false;

   @Override
   public void method1(Framework7Extension var1, boolean var2, boolean var3) {
      if (var2 != this.field9) {
         if (var3) {
            String var4 = var2 ? "enabled " : "disabled ";
            System.out.println("[ServerIntegration] " + var4 + var1.getId());
         }

         this.field9 = var2;
         Framework11 var5 = (Framework11)var1.method1(Framework.field19);
         if (var5 != null) {
            var5.method3(false);
         }

         this.method3(
            var5,
            var3,
            (AlertExtension)var1.method1(Framework.field5),
            (Framework4)var1.method1(Framework.field16)
         );
      }
   }

   @Override
   public void method7(@Nullable Framework11 var1, @Nullable AlertExtension var2, @Nullable Framework4 var3) {
      this.method3(var1, false, var2, var3);
   }

   public void method3(@Nullable Framework11 var1, boolean var2, @Nullable AlertExtension var3, @Nullable Framework4 var4) {
      boolean var5 = var1 == null || var1.method2();
      boolean var6 = this.method4(var4);
      boolean var7 = this.field9 && var5 && var6;
      if (var7 != this.field8) {
         this.field8 = var7;
         if (var7) {
            this.method7();
         } else {
            this.method8();
         }

         if (var3 != null) {
            this.method5(var3.getChildren());
         }
      }

      for (Framework7Extension var9 : this.field4) {
         Framework10Extension var10 = (Framework10Extension)var9.method1(Framework.field12);
         if (var10 != null) {
            var10.method1(var9, var7, var2);
         }
      }
   }

   private boolean method4(@Nullable Framework4 var1) {
      if (var1 == null) {
         return true;
      }

      Framework10Extension var2 = (Framework10Extension)var1.<Framework7Extension>method1().method1(Framework.field12);
      return var2 == null ? this.method4((Framework4)var1.<Framework7Extension>method1().method1(Framework.field16)) : var2.method12();
   }

   private void method5(List<Framework7Extension> var1) {
      for (Framework7Extension var3 : var1) {
         Framework10Extension var4 = (Framework10Extension)var3.method1(Framework.field12);
         if (var4 != null) {
            var4.method7(
               (Framework11)var3.method1(Framework.field19),
               (AlertExtension)var3.method1(Framework.field5),
               (Framework4)var3.method1(Framework.field16)
            );
         } else {
            AlertExtension var5 = (AlertExtension)var3.method1(Framework.field5);
            if (var5 != null) {
               this.method5(var5.getChildren());
            }
         }
      }
   }

   @Override
   public int method3() {
      return this.field1.size();
   }

   protected void method7() {
      this.field2.forEach(GuiRewindhandlers::method1);

      for (Entry var2 : this.field1.entrySet()) {
         Class var3 = (Class)var2.getKey();
         ClientEventBus.method29().method4(var3, ((ListenerRegistration)var2.getValue()).method1(), ((ListenerRegistration)var2.getValue()).priority());
      }

      this.field5.forEach(Runnable::run);
      if (!this.field10) {
         this.field10 = true;
         this.field7.forEach(Runnable::run);
      }
   }

   protected void method8() {
      this.field2.forEach(GuiRewindhandlers::method3);

      for (Entry var2 : this.field1.entrySet()) {
         Class var3 = (Class)var2.getKey();
         Consumer var4 = ((ListenerRegistration)var2.getValue()).method1();
         ClientEventBus.method29().method6(var3, var4);
      }

      this.field6.forEach(Runnable::run);
   }

   public <T extends Highlight> void handle(Class<T> var1, Consumer<T> var2) {
      this.field1.put(var1, new ListenerRegistration(100, var2));
      if (this.field8) {
         ClientEventBus.method29().method4(var1, var2, 100);
      }
   }

   public <T extends Highlight> void method2(Class<T> var1, Consumer<T> var2, int var3) {
      this.field1.put(var1, new ListenerRegistration(var3, var2));
      if (this.field8) {
         ClientEventBus.method29().method4(var1, var2, var3);
      }
   }

   @Override
   public void method2(GuiRewindhandlers var1) {
      this.field2.add(var1);
   }

   @Override
   public void method8(MixinNameplate2 var1) {
      if (this.field3.isEmpty()) {
         this.handle(EventCommandLegacy.class, var1x -> {
            for (MixinNameplate2 var3 : this.field3) {
               if (var3.isEnabled() && var1x.method1(var3.method1())) {
                  if (!var3.method2(var1x.getCommand())) {
                     ThreadModuleDump63.method17("Failed to execute command: " + var1x.getCommand());
                  }

                  var1x.cancel();
                  return;
               }
            }
         });
         this.handle(Data.class, var1x -> {
            for (MixinNameplate2 var3 : this.field3) {
               if (var3.isEnabled() && var1x.method1(var3.method1())) {
                  var1x.method2();
                  return;
               }
            }
         });
         this.handle(EventCommandRegisterLegacy.class, var1x -> {
            for (MixinNameplate2 var3 : this.field3) {
               if (var3.isEnabled() && var3.method4()) {
                  var1x.method1(var3);
               }
            }
         });
      }

      this.field3.add(var1);
   }

   @Override
   public void method9(Runnable var1) {
      this.field5.add(var1);
   }

   @Override
   public void method10(Runnable var1) {
      this.field6.add(var1);
   }

   @Override
   public void method11(Runnable var1) {
      this.field7.add(var1);
   }

   @Override
   public void method4(Framework7Extension var1) {
      this.field4.add(var1);
      if (this.field8) {
         Framework10Extension var2 = (Framework10Extension)var1.method1(Framework.field12);
         if (var2 != null) {
            var2.method1(var1, true, false);
         }
      }
   }

   @Override
   public void method5(Framework7Extension var1) {
      this.field4.remove(var1);
      Framework10Extension var2 = (Framework10Extension)var1.method1(Framework.field12);
      if (var2 != null) {
         var2.method1(var1, false, false);
      }
   }

   @Generated
   @Override
   public boolean method12() {
      return this.field8;
   }

   @Generated
   @Override
   public boolean method6() {
      return this.field9;
   }
}
