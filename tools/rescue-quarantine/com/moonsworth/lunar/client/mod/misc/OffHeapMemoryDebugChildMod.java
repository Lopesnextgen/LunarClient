package com.moonsworth.lunar.client.mod.misc;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.client.framework.Framework;
import com.moonsworth.lunar.client.framework.Framework4;
import com.moonsworth.lunar.client.framework.feature.debug.Debug;
import com.moonsworth.lunar.client.framework.feature.debug.Debug.Data;
import com.moonsworth.lunar.client.framework.feature.debug.Debug.Extension;
import com.moonsworth.lunar.client.lighting.Lighting;
import com.moonsworth.lunar.client.lighting.LightingExtension;
import com.moonsworth.lunar.client.lighting.LightingExtension23;
import com.moonsworth.lunar.client.lighting.LightingExtension497;
import com.moonsworth.lunar.client.lighting.rewindhandlers.RewindhandlersType;
import java.lang.management.ManagementFactory;
import java.util.List;

public class OffHeapMemoryDebugChildMod extends Debug implements Extension {
   protected final LightingExtension497<MemoryDebugChildMod.Type> field18 = (LightingExtension497<MemoryDebugChildMod.Type>)Lighting.method10(
         "memoryFormat", MemoryDebugChildMod.Type.MB
      )
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private long field19 = 0L;

   public OffHeapMemoryDebugChildMod(GraphDebugMod var1) {
      super(false);
      this.method2(Framework.field16, Framework4.method3(var1));
   }

   public String getId() {
      return "OFF_HEAP_MEMORY_DEBUG_CHILD_MOD";
   }

   protected Data method13() {
      return new Data(this, 0.0F, 96.0F, "Off-Heap Memory");
   }

   public void method2(LightingExtension23 var1) {
      super.method2(var1);
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(RewindhandlersType.SETTINGS, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field18}));
   }

   protected List<Extension> method14() {
      return ImmutableList.of(this);
   }

   protected String method3(long var1) {
      return ((MemoryDebugChildMod.Type)this.field18.get()).getFormat().apply(var1);
   }

   public long method21() {
      long var1 = ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getMax();
      return var1 == -1L ? this.field19 : var1;
   }

   public long getValue() {
      long var1 = ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed() / 1000L;
      if (var1 > this.field19) {
         this.field19 = var1;
      }

      return var1;
   }
}
