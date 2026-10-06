package com.moonsworth.lunar.client.mod.misc.debug;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.feature.debug.Debug;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import java.lang.management.ManagementFactory;
import java.util.List;
import com.moonsworth.lunar.client.mod.hud.memory.Memory;

public class OffHeapMemoryDebug extends Debug implements Debug.Extension {
   protected final EnumOption<MemoryDebug.Type> field18 = (EnumOption<MemoryDebug.Type>)OptionFactory.method10(
         "memoryFormat", MemoryDebug.Type.MB
      )
      .method31();
   private long field19 = 0L;

   public OffHeapMemoryDebug(GraphDebugMod var1) {
      super(false);
      this.method2(Framework.field16, Framework4.method3(var1));
   }

   @Override
   public String getId() {
      return "OFF_HEAP_MEMORY_DEBUG_CHILD_MOD";
   }

   @Override
   protected Debug.Data method13() {
      return new Debug.Data(0.0F, 96.0F, "Off-Heap Memory");
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      super.method2(var1);
      var1.method4(SettingsPage.SETTINGS, var1x -> var1x.method9(new ClientOption[]{this.field18}));
   }

   @Override
   protected List<Debug.Extension> method14() {
      return ImmutableList.of(this);
   }

   @Override
   protected String method3(long var1) {
      return this.field18.get().getFormat().apply(var1);
   }

   @Override
   public long method21() {
      long var1 = ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getMax();
      return var1 == -1L ? this.field19 : var1;
   }

   @Override
   public long getValue() {
      long var1 = ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed() / 1000L;
      if (var1 > this.field19) {
         this.field19 = var1;
      }

      return var1;
   }
}
