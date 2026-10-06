package com.moonsworth.lunar.client.mod.misc;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.client.fog.holograms.FogHandler257;
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
import java.lang.management.BufferPoolMXBean;
import java.util.List;

public class DirectMemoryDebugChildMod extends Debug implements Extension {
   private static final BufferPoolMXBean field18 = FogHandler257.method9();
   protected final LightingExtension497<MemoryDebugChildMod.Type> field19 = (LightingExtension497<MemoryDebugChildMod.Type>)Lighting.method10(
         "memoryFormat", MemoryDebugChildMod.Type.MB
      )
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private long field20 = -1L;

   public DirectMemoryDebugChildMod(GraphDebugMod var1) {
      super(false);
      this.method2(Framework.field16, Framework4.method3(var1));
   }

   public String getId() {
      return "DIRECT_MEMORY_DEBUG_CHILD_MOD";
   }

   public void method2(LightingExtension23 var1) {
      super.method2(var1);
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(RewindhandlersType.SETTINGS, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field19}));
   }

   protected Data method13() {
      return new Data(this, 0.0F, 72.0F, "Direct Memory");
   }

   protected List<Extension> method14() {
      return ImmutableList.of(this);
   }

   protected String method3(long var1) {
      return ((MemoryDebugChildMod.Type)this.field19.get()).getFormat().apply(var1);
   }

   public long method21() {
      return this.field20;
   }

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
