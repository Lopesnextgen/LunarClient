package com.moonsworth.lunar.client.replay.render;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ResourcePackBridge;
import com.moonsworth.lunar.client.ui.widget.ResourcePackListWidget;
import com.moonsworth.lunar.client.ui.external.ExternalLinkRegistry;
import com.moonsworth.lunar.client.fishing.Fishing2Extension;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.DynamicDropdownOption;
import com.moonsworth.lunar.client.config.option.ListOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.ListOption.Data;
import com.moonsworth.lunar.client.driver.DriverFieldType;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandler;
import com.moonsworth.lunar.client.util.collection.ValueHolder;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import org.apache.commons.lang3.mutable.MutableBoolean;

public class ResourcePackHandler extends RewindHandler {
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("packs").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("useServerPacks").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("useClientPacks").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ListOption<String> field12 = (ListOption<String>)((Data)((Data)OptionFactory.method31("overridePacks", Codec.STRING.listOf())
            .method7(Codec.STRING)
            .method11()
            .method7(ResourcePackListWidget::new))
         .method7(DriverFieldType.PACKS))
      .method6(arg0 -> {
         String text1x = new File(arg0).getName();
         String text2 = Ref.method3().bridge$getBuiltInPackName(text1x);
         if (text2 != null) {
            text1x = Ref.method4().method67().method2("rewind", text2, new Object[0]);
         }

         return text1x;
      })
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private List<Integer> field13 = new ArrayList<>();
   private List<String> field14 = new ArrayList<>();
   private String field15;
   private List<String> field16 = new ArrayList<>();
   private long field17 = 0L;
   private long field18 = 0L;
   private final ToggleOption field19 = (ToggleOption)OptionFactory.method7("shader").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final DynamicDropdownOption field20 = (DynamicDropdownOption)((com.moonsworth.lunar.client.config.option.DynamicDropdownOption.Data)OptionFactory.method19(
            "shaderPack"
         )
         .HIIIOHRRROCICIOIORRRIRCRCHHIII("OFF"))
      .method4(() -> {
         if (System.currentTimeMillis() - this.field17 < 10000L) {
            return this.field16;
         }

         this.field17 = System.currentTimeMillis();
         this.field16 = new ArrayList<>();
         this.field16.add("OFF");
         File file1x = new File(Ref.method3().bridge$getMcDataDir(), "shaderpacks");
         if (file1x.exists() && file1x.isDirectory()) {
            File[] items2 = file1x.listFiles(arg0 -> arg0.isDirectory() || arg0.getName().endsWith(".zip"));
            if (items2 != null) {
               for (File file6 : items2) {
                  this.field16.add(file6.getName());
               }
            }
         }

         return this.field16;
      })
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private boolean field21 = false;

   public ResourcePackHandler(ValueHolder<ReplayContext> threadmoduledump61) {
      super(threadmoduledump61);
      ExternalLinkRegistry.method2(Fishing2Extension.class).ifPresent(arg1x -> this.field15 = arg1x.lunar$getShaderPack());
      Bridge.method5().ifPresent(arg1x -> this.field15 = arg1x.getShaders().getShaderPack());
      this.field9.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.method15());
      this.field10.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> {
         if (!this.field13.isEmpty()) {
            this.field13 = List.of();
            this.method15();
         }
      });
      this.field11.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> {
         if (!this.field14.isEmpty()) {
            this.field14 = List.of();
            this.method15();
         }
      });
      this.field12.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.method15());
      this.field19.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> {
         if (arg1x) {
            this.method1((String)this.field20.get());
         } else {
            this.method1(this.field15);
         }
      });
      this.field20.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> {
         if ((Boolean)this.field19.get()) {
            this.method1(arg1x);
         }
      });
      Ref.method3().bridge$getAllBuiltInPacksFiles();
   }

   private void method1(String text1) {
      if (!(Boolean)this.field19.get()) {
         text1 = this.field15;
      }

      if (text1 == null) {
         text1 = "OFF";
      }

      ReplayTimeline highlight_32 = ((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method4();
      MutableBoolean mutableboolean3 = new MutableBoolean(false);
      String text4 = text1;
      this.field18 = System.currentTimeMillis();
      ExternalLinkRegistry.method2(Fishing2Extension.class).ifPresent(arg2x -> {
         try {
            mutableboolean3.setValue(mutableboolean3.getValue() || arg2x.lunar$setShaderPack(text4));
         } catch (Exception exception4x) {
            exception4x.printStackTrace();
         }
      });
      Bridge.method5().ifPresent(arg2x -> {
         try {
            mutableboolean3.setValue(mutableboolean3.getValue() || arg2x.getShaders().setShaderPack(text4));
         } catch (Exception exception4x) {
            exception4x.printStackTrace();
         }
      });
      if (mutableboolean3.getValue() && highlight_32 != null) {
         highlight_32.method6();
      }
   }

   public void apply() {
      ReplayContext nameplate41 = (ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get();
      if ((Boolean)this.field10.get() && !this.field13.equals(nameplate41.method12())) {
         this.field13 = nameplate41.method12();
         this.method15();
      }

      if ((Boolean)this.field11.get() && !this.field14.equals(nameplate41.method14())) {
         this.field14 = nameplate41.method14();
         this.method15();
      }
   }

   public void method14() {
      for (ResourcePackBridge bridge142 : ((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method11()) {
         bridge142.bridge$close();
      }

      for (ResourcePackBridge bridge144 : ((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method13()) {
         bridge144.bridge$close();
      }

      this.method1(this.field15);
      if (!((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method11().isEmpty()
         || !((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method13().isEmpty()) {
         Ref.method3().bridge$refreshResources();
      }
   }

   public void method15() {
      this.field21 = true;
   }

   public void method16() {
      if (((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method6().method41().method2()
         && Ref.method8() != null
         && Ref.method7() != null
         && this.field21) {
         Ref.method3().bridge$refreshResources();
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
