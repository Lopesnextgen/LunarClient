package com.moonsworth.lunar.client.framework.feature.animations.customhelditems;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.JsonPersistable;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.OptionSupplier;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.mod.render.itemcustomizer.CustomHeldItems;
import com.moonsworth.lunar.client.mod.render.itemcustomizer.ItemCustomizer;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import org.jetbrains.annotations.Nullable;

class Lighting3Loader2 implements JsonPersistable {
   private final CustomHeldItems field1;
   private final ToggleOption field2;
   @Annotation2(min = 5)
   private final ToggleOption field3 = (ToggleOption)OptionFactory.method7("splitHands").method31();
   private final boolean field4;
   private final Lighting3Loader field5 = new Lighting3Loader(this, CustomhelditemsType.RIGHT);
   @Annotation2(min = 5)
   private final Lighting3Loader field6 = new Lighting3Loader(this, CustomhelditemsType.LEFT);

   Lighting3Loader2(CustomHeldItems var1, String var2, boolean var3) {
      this.field1 = var1;
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         this.field3.method9(() -> ItemCustomizer.method5(this.field1, true));
      }

      this.field2 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("enabled").method4(true))
            .ROICHOCCIOCHCIHOIHIHICCORIROCC(var2))
         .method31();
      this.field4 = var3;
   }

   public void load(JsonObject var1) {
      this.field2.load(var1);
      this.field5.load(var1);
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         this.field3.load(var1);
         this.field6.load(var1);
      }
   }

   public void method1(JsonObject var1) {
      this.field2.load(var1);
      this.field5.method1(var1);
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         this.field3.load(var1);
         this.field6.method1(var1);
      }
   }

   public void method2(RootSettingsAssembler var1) {
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field2, var1x -> {
         this.field5.method3(var1x);
         if (ThreadModuleDump63.MC_VERSION >= 5) {
            this.field6.method3(var1x);
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field3});
         }

         if (this.field4) {
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new OptionSupplier[]{OptionFactory.method14("remove").method4(() -> {
               this.field1.method12(this);
               this.field2.method3(true);
            })});
         }
      });
      ((SettingsSectionImpl)var1.ROOOCICROROOHCIRRHHHCRCOROOHHH()).method2(this.field2::method9);
   }

   @Nullable
   public Lighting3Loader method3(CustomhelditemsType var1) {
      if (ThreadModuleDump63.MC_VERSION >= 5
         && !(Boolean)this.field3.get()
         && ThreadModuleDump63.method7() != null
         && ThreadModuleDump63.method7().bridge$isMainHandSwapped()) {
         var1 = var1.opposite();
      }

      Lighting3Loader var2 = this.method4(var1);
      return var2 != null && var2.isEnabled() ? var2 : null;
   }

   @Nullable
   public Lighting3Loader method4(CustomhelditemsType var1) {
      if (var1 == CustomhelditemsType.LEFT && ThreadModuleDump63.MC_VERSION >= 5) {
         return this.field6;
      } else {
         return var1 == CustomhelditemsType.RIGHT ? this.field5 : null;
      }
   }

   public boolean isEnabled() {
      return (Boolean)this.field2.get();
   }

   public boolean method5() {
      return ThreadModuleDump63.MC_VERSION >= 5 && (Boolean)this.field3.get();
   }
}
