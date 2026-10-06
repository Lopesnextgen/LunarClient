package com.moonsworth.lunar.client.mod.misc.debug;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.client.util.performance.PerformanceReporter;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.feature.debug.Debug;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import java.lang.management.BufferPoolMXBean;
import java.util.List;
import com.moonsworth.lunar.client.mod.hud.memory.Memory;

public class DirectMemoryDebug extends Debug implements Debug.Extension {
   private static final BufferPoolMXBean field18 = PerformanceReporter.method9();
   protected final EnumOption<MemoryDebugChildMod.Type> field19 = (EnumOption<MemoryDebugChildMod.Type>)OptionFactory.method10(
         "memoryFormat", MemoryDebugChildMod.Type.MB
      )
      .method31();
   private long field20 = -1L;

   public DirectMemoryDebug(GraphDebugMod var1) {
      super(false);
      this.method2(Framework.field16, Framework4.method3(var1));
   }

   @Override
   public String getId() {
      return "DIRECT_MEMORY_DEBUG_CHILD_MOD";
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      super.method2(var1);
      var1.method4(SettingsPage.SETTINGS, var1x -> var1x.method9(new ClientOption[]{this.field19}));
   }

   @Override
   protected Debug.Data method13() {
      return new Debug.Data(0.0F, 72.0F, "Direct Memory");
   }

   @Override
   protected List<Debug.Extension> method14() {
      return ImmutableList.of(this);
   }

   @Override
   protected String method3(long var1) {
      return this.field19.get().getFormat().apply(var1);
   }

   @Override
   public long method21() {
      return this.field20;
   }

   @Override
   public long getValue() {
      long var1 = field18.getMemoryUsed();
      if (var1 == -1L) {
         return 0L;
      }

      var1 /= 1000L;
      if (var1 > this.field20) {
         this.field20 = var1;
      }

      return var1;
   }
}
