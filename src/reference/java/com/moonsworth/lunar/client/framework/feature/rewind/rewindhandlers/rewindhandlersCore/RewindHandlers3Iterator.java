package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.IResourcePackBridge;
import com.moonsworth.lunar.client.ui.widget.ResourcePackListWidget;
import com.moonsworth.lunar.client.fishing.Fishing;
import com.moonsworth.lunar.client.fishing.Fishing2Extension;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.DynamicDropdownOption;
import com.moonsworth.lunar.client.config.option.ListOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.ListOption.Data;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers3;
import com.moonsworth.lunar.client.util.ThreadModuleDump6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import org.apache.commons.lang3.mutable.MutableBoolean;

public class RewindHandlers3Iterator extends RewindHandlers3 {
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("packs").method4(true))
      .method31();
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("useServerPacks").method4(true))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("useClientPacks").method4(true))
      .method31();
   private final ListOption<String> field12 = (ListOption<String>)((Data)((Data)OptionFactory.method31("overridePacks", Codec.STRING.listOf())
            .method7(Codec.STRING)
            .method11()
            .method7(ResourcePackListWidget::new))
         .method7(DriverFieldTypeLegacy.PACKS))
      .method6(var0 -> {
         String var1x = new File(var0).getName();
         String var2 = ThreadModuleDump63.method3().bridge$getBuiltInPackName(var1x);
         if (var2 != null) {
            var1x = ThreadModuleDump63.method4().method67().method2("rewind", var2, new Object[0]);
         }

         return var1x;
      })
      .method31();
   private List<Integer> field13 = new ArrayList<>();
   private List<String> field14 = new ArrayList<>();
   private String field15;
   private List<String> field16 = new ArrayList<>();
   private long field17 = 0L;
   private long field18 = 0L;
   private final ToggleOption field19 = (ToggleOption)OptionFactory.method7("shader").method31();
   private final DynamicDropdownOption field20 = (DynamicDropdownOption)((com.moonsworth.lunar.client.config.option.DynamicDropdownOption.Data)OptionFactory.method19(
            "shaderPack"
         )
         .method2("OFF"))
      .method4(() -> {
         if (System.currentTimeMillis() - this.field17 < 10000L) {
            return this.field16;
         }

         this.field17 = System.currentTimeMillis();
         this.field16 = new ArrayList<>();
         this.field16.add("OFF");
         File var1x = new File(ThreadModuleDump63.method3().bridge$getMcDataDir(), "shaderpacks");
         if (var1x.exists() && var1x.isDirectory()) {
            File[] var2 = var1x.listFiles(var0 -> var0.isDirectory() || var0.getName().endsWith(".zip"));
            if (var2 != null) {
               for (File var6 : var2) {
                  this.field16.add(var6.getName());
               }
            }
         }

         return this.field16;
      })
      .method31();
   private boolean field21 = false;

   public RewindHandlers3Iterator(ThreadModuleDump6<Nameplate4> var1) {
      super(var1);
      Fishing.method2(Fishing2Extension.class).ifPresent(var1x -> this.field15 = var1x.lunar$getShaderPack());
      Bridge.method5().ifPresent(var1x -> this.field15 = var1x.getShaders().getShaderPack());
      this.field9.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> this.method15());
      this.field10.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> {
         if (!this.field13.isEmpty()) {
            this.field13 = List.of();
            this.method15();
         }
      });
      this.field11.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> {
         if (!this.field14.isEmpty()) {
            this.field14 = List.of();
            this.method15();
         }
      });
      this.field12.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> this.method15());
      this.field19.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> {
         if (var1x) {
            this.method1((String)this.field20.get());
         } else {
            this.method1(this.field15);
         }
      });
      this.field20.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> {
         if ((Boolean)this.field19.get()) {
            this.method1(var1x);
         }
      });
      ThreadModuleDump63.method3().bridge$getAllBuiltInPacksFiles();
   }

   private void method1(String var1) {
      if (!(Boolean)this.field19.get()) {
         var1 = this.field15;
      }

      if (var1 == null) {
         var1 = "OFF";
      }

      Highlight_3 var2 = ((Nameplate4)this.field8.get()).method4();
      MutableBoolean var3 = new MutableBoolean(false);
      String var4 = var1;
      this.field18 = System.currentTimeMillis();
      Fishing.method2(Fishing2Extension.class).ifPresent(var2x -> {
         try {
            var3.setValue(var3.getValue() || var2x.lunar$setShaderPack(var4));
         } catch (Exception var4x) {
            var4x.printStackTrace();
         }
      });
      Bridge.method5().ifPresent(var2x -> {
         try {
            var3.setValue(var3.getValue() || var2x.getShaders().setShaderPack(var4));
         } catch (Exception var4x) {
            var4x.printStackTrace();
         }
      });
      if (var3.getValue() && var2 != null) {
         var2.method6();
      }
   }

   public void apply() {
      Nameplate4 var1 = (Nameplate4)this.field8.get();
      if ((Boolean)this.field10.get() && !this.field13.equals(var1.method12())) {
         this.field13 = var1.method12();
         this.method15();
      }

      if ((Boolean)this.field11.get() && !this.field14.equals(var1.method14())) {
         this.field14 = var1.method14();
         this.method15();
      }
   }

   public void method14() {
      for (IResourcePackBridge var2 : ((Nameplate4)this.field8.get()).method11()) {
         var2.bridge$close();
      }

      for (IResourcePackBridge var4 : ((Nameplate4)this.field8.get()).method13()) {
         var4.bridge$close();
      }

      this.method1(this.field15);
      if (!((Nameplate4)this.field8.get()).method11().isEmpty()
         || !((Nameplate4)this.field8.get()).method13().isEmpty()) {
         ThreadModuleDump63.method3().bridge$refreshResources();
      }
   }

   public void method15() {
      this.field21 = true;
   }

   public void method16() {
      if (((Nameplate4)this.field8.get()).method6().method41().method2()
         && ThreadModuleDump63.method8() != null
         && ThreadModuleDump63.method7() != null
         && this.field21) {
         ThreadModuleDump63.method3().bridge$refreshResources();
         this.field21 = false;
      }
   }

   @Generated
   public ToggleOption method17() {
      return this.field9;
   }

   @Generated
   public ToggleOption method19() {
      return this.field10;
   }

   @Generated
   public ToggleOption method21() {
      return this.field11;
   }

   @Generated
   public ListOption<String> method22() {
      return this.field12;
   }

   @Generated
   public List<Integer> method23() {
      return this.field13;
   }

   @Generated
   public List<String> method24() {
      return this.field14;
   }

   @Generated
   public String method25() {
      return this.field15;
   }

   @Generated
   public List<String> method26() {
      return this.field16;
   }

   @Generated
   public long method27() {
      return this.field17;
   }

   @Generated
   public ToggleOption method28() {
      return this.field19;
   }

   @Generated
   public DynamicDropdownOption method29() {
      return this.field20;
   }

   @Generated
   public long method30() {
      return this.field18;
   }

   @Generated
   public boolean method34() {
      return this.field21;
   }
}
