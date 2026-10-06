package com.moonsworth.lunar.client.mod.misc.debug;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.feature.debug.Debug;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import java.util.List;
import java.util.function.LongFunction;
import lombok.Generated;
import com.moonsworth.lunar.client.mod.hud.memory.Memory;

public class MemoryDebug extends Debug {
   protected final EnumOption<MemoryDebug.Type> field18 = (EnumOption<MemoryDebug.Type>)OptionFactory.method10(
         "memoryFormat", MemoryDebug.Type.MB
      )
      .method31();

   public MemoryDebug(GraphDebugMod var1) {
      super(false);
      this.method2(Framework.field16, Framework4.method3(var1));
   }

   @Override
   public String getId() {
      return "MEMORY_DEBUG_CHILD_MOD";
   }

   @Override
   protected Debug.Data method13() {
      return new Debug.Data(0.0F, 24.0F, "Memory");
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      super.method2(var1);
      var1.method4(SettingsPage.SETTINGS, var1x -> var1x.method9(new ClientOption[]{this.field18}));
   }

   @Override
   protected List<Debug.Extension> method14() {
      return ImmutableList.of(new Debug.Extension() {
         @Override
         public long method21() {
            return Runtime.getRuntime().maxMemory();
         }

         @Override
         public long getValue() {
            Runtime var1 = Runtime.getRuntime();
            return var1.totalMemory() - var1.freeMemory();
         }

         @Override
         public float method2() {
            Runtime var1 = Runtime.getRuntime();
            long var2 = var1.totalMemory() - var1.freeMemory();
            return 1.0F - (int)(var2 * 100L / var1.maxMemory()) / 100.0F;
         }

         @Override
         public boolean method3() {
            return true;
         }
      }, new Debug.Extension() {
         @Override
         public long method21() {
            return Runtime.getRuntime().maxMemory();
         }

         @Override
         public long getValue() {
            return Runtime.getRuntime().totalMemory();
         }

         @Override
         public int getColor() {
            return -872349697;
         }
      });
   }

   @Override
   protected String method3(long var1) {
      return this.field18.get().getFormat().apply(var1);
   }

   public enum Type implements OptionEnumValue {
      KB("1234567kb", var0 -> var0 + "kb"),
      MB("1234.57mb", var0 -> String.format("%.2fmb", (float)var0 / 1048576.0F)),
      GB("1.234gb", var0 -> String.format("%.3fgb", (float)var0 / 1.0737418E9F));

      private final String display;
      private final LongFunction<String> format;

      @Override
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
