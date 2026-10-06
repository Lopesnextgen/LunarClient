package com.moonsworth.lunar.client.mod.misc;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.client.framework.Framework;
import com.moonsworth.lunar.client.framework.Framework4;
import com.moonsworth.lunar.client.framework.feature.debug.Debug;
import com.moonsworth.lunar.client.framework.feature.debug.Debug.Data;
import com.moonsworth.lunar.client.framework.feature.debug.Debug.Extension;
import com.moonsworth.lunar.client.lighting.Gui2Extension;
import com.moonsworth.lunar.client.lighting.Lighting;
import com.moonsworth.lunar.client.lighting.LightingExtension;
import com.moonsworth.lunar.client.lighting.LightingExtension23;
import com.moonsworth.lunar.client.lighting.LightingExtension497;
import com.moonsworth.lunar.client.lighting.rewindhandlers.RewindhandlersType;
import java.util.List;
import java.util.function.LongFunction;
import lombok.Generated;

public class MemoryDebugChildMod extends Debug {
   protected final LightingExtension497<MemoryDebugChildMod.Type> field18 = (LightingExtension497<MemoryDebugChildMod.Type>)Lighting.method10(
         "memoryFormat", MemoryDebugChildMod.Type.MB
      )
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();

   public MemoryDebugChildMod(GraphDebugMod var1) {
      super(false);
      this.method2(Framework.field16, Framework4.method3(var1));
   }

   public String getId() {
      return "MEMORY_DEBUG_CHILD_MOD";
   }

   protected Data method13() {
      return new Data(this, 0.0F, 24.0F, "Memory");
   }

   public void method2(LightingExtension23 var1) {
      super.method2(var1);
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(RewindhandlersType.SETTINGS, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field18}));
   }

   protected List<Extension> method14() {
      return ImmutableList.of(new Extension() {
         public long method21() {
            return Runtime.getRuntime().maxMemory();
         }

         public long getValue() {
            Runtime var1 = Runtime.getRuntime();
            return var1.totalMemory() - var1.freeMemory();
         }

         public float method2() {
            Runtime var1 = Runtime.getRuntime();
            long var2 = var1.totalMemory() - var1.freeMemory();
            return 1.0F - (int)(var2 * 100L / var1.maxMemory()) / 100.0F;
         }

         public boolean method3() {
            return true;
         }
      }, new Extension() {
         public long method21() {
            return Runtime.getRuntime().maxMemory();
         }

         public long getValue() {
            return Runtime.getRuntime().totalMemory();
         }

         public int getColor() {
            return -872349697;
         }
      });
   }

   protected String method3(long var1) {
      return ((MemoryDebugChildMod.Type)this.field18.get()).getFormat().apply(var1);
   }

   public enum Type implements Gui2Extension {
      KB("1234567kb", var0 -> var0 + "kb"),
      MB("1234.57mb", var0 -> String.format("%.2fmb", (float)var0 / 1048576.0F)),
      GB("1.234gb", var0 -> String.format("%.3fgb", (float)var0 / 1.0737418E9F));

      private final String display;
      private final LongFunction<String> format;

      public String id() {
         return this.name();
      }

      @Override
      public String toString() {
         return this.display;
      }

      @Generated
      public String getDisplay() {
         return this.display;
      }

      @Generated
      public LongFunction<String> getFormat() {
         return this.format;
      }

      @Generated
      Type(String var3, LongFunction<String> var4) {
         this.display = var3;
         this.format = var4;
      }
   }
}
