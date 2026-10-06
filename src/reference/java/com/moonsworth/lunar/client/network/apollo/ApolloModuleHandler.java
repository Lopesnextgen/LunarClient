package com.moonsworth.lunar.client.network.apollo;

import com.google.common.collect.Sets;
import com.google.protobuf.Message;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.Options;
import com.moonsworth.lunar.client.highlight.Highlight;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import lombok.Generated;

public abstract class ApolloModuleHandler implements EventRegistrar {
   private final List<ApolloModuleHandler$ListenerEntry> field1 = new ArrayList<>();
   private boolean enabled;
   private Options options = Options.empty();
   private final String field2;
   private final String field3;

   protected ApolloModuleHandler(String var1, String var2) {
      this.field2 = var1;
      this.field3 = var2;
   }

   public Collection<Option<?, ?, ?>> method1() {
      return Collections.emptyList();
   }

   public Set<Class<? extends Message>> method2() {
      return Sets.newHashSet();
   }

   public void enable() {
      if (!this.enabled) {
         this.enabled = true;
         Collection var1 = this.method1();
         if (!var1.isEmpty()) {
            this.options = new OptionsImpl(var1);
         }

         this.onEnable();
         this.method5();
      }
   }

   protected void onEnable() {
   }

   public void disable() {
      if (this.enabled) {
         this.enabled = false;
         this.options = new OptionsImpl(Collections.emptyList());
         this.onDisable();
         this.method7();
      }
   }

   protected void onDisable() {
   }

   public abstract void method3(HighlightImpl_3 var1);

   @Override
   public <T extends Highlight> void handle(Class<T> var1, Consumer<T> var2) {
      this.method2(var1, var2, -1);
   }

   @Override
   public <T extends Highlight> void method2(Class<T> var1, Consumer<T> var2, int var3) {
      ApolloModuleHandler$ListenerEntry var4 = new ApolloModuleHandler$ListenerEntry(var1, var2, var3);
      this.field1.add(var4);
      if (this.enabled) {
         this.method6(var4);
      }
   }

   protected void sendPacket(Message var1) {
      ThreadModuleDump63.method4().method84().method16(var1, this.field2);
   }

   private void method5() {
      for (ApolloModuleHandler$ListenerEntry var2 : this.field1) {
         this.method6(var2);
      }
   }

   private void method6(ApolloModuleHandler$ListenerEntry var1) {
      if (var1.priority() == -1) {
         ClientEventBus.method29().method2(var1.method1(), var1.method2());
      } else {
         ClientEventBus.method29().method4(var1.method1(), var1.method2(), var1.priority());
      }
   }

   private void method7() {
      for (ApolloModuleHandler$ListenerEntry var2 : this.field1) {
         ClientEventBus.method29().method6(var2.method1(), var2.method2());
      }
   }

   @Generated
   public boolean isEnabled() {
      return this.enabled;
   }

   @Generated
   public Options getOptions() {
      return this.options;
   }

   @Generated
   public void setOptions(Options var1) {
      this.options = var1;
   }

   @Generated
   public String getId() {
      return this.field2;
   }

   @Generated
   public String getName() {
      return this.field3;
   }
}
