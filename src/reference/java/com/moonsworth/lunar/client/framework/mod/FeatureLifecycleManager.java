package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.ModChildren;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModLifecycle;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.command.ClientCommand;
import com.moonsworth.lunar.client.framework.listener.GuiRewindhandlers;
import com.moonsworth.lunar.client.event.LunarEvent;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.EventListener;
import com.moonsworth.lunar.client.event.mixin.EventCommand;
import com.moonsworth.lunar.client.event.mixin.EventCommandRegister;
import com.moonsworth.lunar.client.event.mixin.EventCommand.CommandInput;
import com.moonsworth.lunar.client.framework.Ref;
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

public class FeatureLifecycleManager implements ModLifecycle {
   private final Map<Class<LunarEvent>, EventListener<LunarEvent>> field1 = new HashMap<>();
   private final Set<GuiRewindhandlers> field2 = new HashSet<>();
   private final List<ClientCommand> field3 = new ArrayList<>();
   private final List<Framework7Extension> field4 = new ArrayList<>();
   private final List<Runnable> field5 = new ArrayList<>();
   private final List<Runnable> field6 = new ArrayList<>();
   private final List<Runnable> field7 = new ArrayList<>();
   private boolean field8 = false;
   private boolean field9 = false;
   private boolean field10 = false;

   public FeatureLifecycleManager() {
   }

   @Override
   public void method1(Framework7Extension framework7extension1, boolean flag2, boolean flag3) {
      if (flag2 != this.field9) {
         if (flag3) {
            String text4 = flag2 ? "enabled " : "disabled ";
            System.out.println("[ServerIntegration] " + text4 + framework7extension1.getId());
         }

         this.field9 = flag2;
         DynamicCondition framework115 = (DynamicCondition)framework7extension1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field19);
         if (framework115 != null) {
            framework115.method3(false);
         }

         this.method3(
            framework115,
            flag3,
            (ModChildren)framework7extension1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field5),
            (ChildModBinding)framework7extension1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field16)
         );
      }
   }

   @Override
   public void method7(@Nullable DynamicCondition framework111, @Nullable ModChildren alertextension2, @Nullable ChildModBinding framework43) {
      this.method3(framework111, false, alertextension2, framework43);
   }

   public void method3(@Nullable DynamicCondition framework111, boolean flag2, @Nullable ModChildren alertextension3, @Nullable ChildModBinding framework44) {
      boolean flag5 = framework111 == null || framework111.method2();
      boolean flag6 = this.method4(framework44);
      boolean flag7 = this.field9 && flag5 && flag6;
      if (flag7 != this.field8) {
         this.field8 = flag7;
         if (flag7) {
            this.method7();
         } else {
            this.method8();
         }

         if (alertextension3 != null) {
            this.method5(alertextension3.getChildren());
         }
      }

      for (Framework7Extension framework7extension9 : this.field4) {
         ModLifecycle framework10extension10 = (ModLifecycle)framework7extension9.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field12);
         if (framework10extension10 != null) {
            framework10extension10.method1(framework7extension9, flag7, flag2);
         }
      }
   }

   private boolean method4(@Nullable ChildModBinding framework41) {
      if (framework41 == null) {
         return true;
      }

      ModLifecycle framework10extension2 = (ModLifecycle)framework41.<Framework7Extension>method1().HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field12);
      return framework10extension2 == null ? this.method4((ChildModBinding)framework41.<Framework7Extension>method1().HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field16)) : framework10extension2.method12();
   }

   private void method5(List<Framework7Extension> list1) {
      for (Framework7Extension framework7extension3 : list1) {
         ModLifecycle framework10extension4 = (ModLifecycle)framework7extension3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field12);
         if (framework10extension4 != null) {
            framework10extension4.method7(
               (DynamicCondition)framework7extension3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field19),
               (ModChildren)framework7extension3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field5),
               (ChildModBinding)framework7extension3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field16)
            );
         } else {
            ModChildren alertextension5 = (ModChildren)framework7extension3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field5);
            if (alertextension5 != null) {
               this.method5(alertextension5.getChildren());
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

      for (Entry entry2 : this.field1.entrySet()) {
         Class clazz3 = (Class)entry2.getKey();
         LunarEventBus.method29().method4(clazz3, ((EventListener)entry2.getValue()).method1(), ((EventListener)entry2.getValue()).priority());
      }

      this.field5.forEach(Runnable::run);
      if (!this.field10) {
         this.field10 = true;
         this.field7.forEach(Runnable::run);
      }
   }

   protected void method8() {
      this.field2.forEach(GuiRewindhandlers::method3);

      for (Entry entry2 : this.field1.entrySet()) {
         Class clazz3 = (Class)entry2.getKey();
         Consumer consumer4 = ((EventListener)entry2.getValue()).method1();
         LunarEventBus.method29().method6(clazz3, consumer4);
      }

      this.field6.forEach(Runnable::run);
   }

   public <T extends LunarEvent> void handle(Class<T> clazz1, Consumer<T> consumer2) {
      this.field1.put(clazz1, new EventListener(100, consumer2));
      if (this.field8) {
         LunarEventBus.method29().method4(clazz1, consumer2, 100);
      }
   }

   public <T extends LunarEvent> void method2(Class<T> clazz1, Consumer<T> consumer2, int number3) {
      this.field1.put(clazz1, new EventListener(number3, consumer2));
      if (this.field8) {
         LunarEventBus.method29().method4(clazz1, consumer2, number3);
      }
   }

   @Override
   public void method2(GuiRewindhandlers guirewindhandlers1) {
      this.field2.add(guirewindhandlers1);
   }

   @Override
   public void method8(ClientCommand mixinnameplate21) {
      if (this.field3.isEmpty()) {
         this.handle(EventCommand.class, arg1x -> {
            for (ClientCommand mixinnameplate23 : this.field3) {
               if (mixinnameplate23.isEnabled() && arg1x.method1(mixinnameplate23.method1())) {
                  if (!mixinnameplate23.method2(arg1x.getCommand())) {
                     Ref.method17("Failed to execute command: " + arg1x.getCommand());
                  }

                  arg1x.cancel();
                  return;
               }
            }
         });
         this.handle(CommandInput.class, arg1x -> {
            for (ClientCommand mixinnameplate23 : this.field3) {
               if (mixinnameplate23.isEnabled() && arg1x.method1(mixinnameplate23.method1())) {
                  arg1x.method2();
                  return;
               }
            }
         });
         this.handle(EventCommandRegister.class, arg1x -> {
            for (ClientCommand mixinnameplate23 : this.field3) {
               if (mixinnameplate23.isEnabled() && mixinnameplate23.method4()) {
                  arg1x.method1(mixinnameplate23);
               }
            }
         });
      }

      this.field3.add(mixinnameplate21);
   }

   @Override
   public void method9(Runnable runnable1) {
      this.field5.add(runnable1);
   }

   @Override
   public void method10(Runnable runnable1) {
      this.field6.add(runnable1);
   }

   @Override
   public void method11(Runnable runnable1) {
      this.field7.add(runnable1);
   }

   @Override
   public void method4(Framework7Extension framework7extension1) {
      this.field4.add(framework7extension1);
      if (this.field8) {
         ModLifecycle framework10extension2 = (ModLifecycle)framework7extension1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field12);
         if (framework10extension2 != null) {
            framework10extension2.method1(framework7extension1, true, false);
         }
      }
   }

   @Override
   public void method5(Framework7Extension framework7extension1) {
      this.field4.remove(framework7extension1);
      ModLifecycle framework10extension2 = (ModLifecycle)framework7extension1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field12);
      if (framework10extension2 != null) {
         framework10extension2.method1(framework7extension1, false, false);
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
