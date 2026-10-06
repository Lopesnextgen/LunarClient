package com.moonsworth.lunar.client.mod.misc.debug;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.mod.ModSearchIndex;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.TextHudComponent;
import com.moonsworth.lunar.client.ui.hud.ScrollableHudComponent;
import com.moonsworth.lunar.client.ui.hud.HudComponentGroup;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Base;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.command.StringArgumentParser;
import com.moonsworth.lunar.client.command.ClientCommand;
import com.moonsworth.lunar.client.command.LiteralCommandNode;
import com.moonsworth.lunar.client.command.ArgumentCommandNode;
import com.moonsworth.lunar.client.framework.feature.debug.optimizationdebugmod.OptimizationDebugOption;
import com.moonsworth.lunar.client.framework.feature.debug.optimizationdebugmod.OptimizationDebugCategory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WidgetFactory;
import com.moonsworth.lunar.client.framework.mod.UnlockableFeature;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRewindUpdate;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.MultiSelectOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.files.ValuePair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import lombok.Generated;
import com.moonsworth.lunar.client.mod.hud.fps.Fps;

public class OptimizationDebugMod extends AbstractFeature {
   private static boolean field8 = false;
   private static final EnumSet<OptimizationDebugOption> field9 = EnumSet.noneOf(OptimizationDebugOption.class);
   private static final EnumSet<OptimizationDebugCategory> field10 = EnumSet.noneOf(OptimizationDebugCategory.class);
   private final MultiSelectOption field11 = (MultiSelectOption)OptionFactory.method27("disabledOptimizations")
      .method3(Arrays.stream(OptimizationDebugOption.getToggleableEnabledOptis()).map(Enum::name).toList())
      .method31();
   private final MultiSelectOption field12 = (MultiSelectOption)OptionFactory.method27("forceEnabledOptimizations")
      .method3(Arrays.stream(OptimizationDebugOption.getToggleableDisabledOptis()).map(Enum::name).toList())
      .method31();
   private final ToggleOption field13 = (ToggleOption)OptionFactory.method7("showRunningOptiHud").method31();
   private final ToggleOption field14 = (ToggleOption)OptionFactory.method7("showResourceLoadTime").method31();
   private final HudComponentGroup field15 = new HudComponentGroup(true);
   private final AtomicInteger field16 = new AtomicInteger();
   private volatile ValuePair<OptimizationDebugOption, Boolean> field17;
   private boolean valid = false;

   public OptimizationDebugMod() {
      super(false);
      this.method30(this::onDisable);
      this.method29(this::onEnable);
      this.method25(EventSecond.class, this::method16);
      this.method25(EventRewindUpdate.class, this.field16::incrementAndGet);
      this.method25(
         new ClientCommand(
            LiteralCommandNode.method1("testopti")
               .method2(
                  ArgumentCommandNode.method1("opti", StringArgumentParser.field1)
                     .method6(OptimizationDebugOption.values())
                     .method8(arg1 -> this.method1(arg1.getString("opti")))
               )
         )
      );
      this.method25(new ClientCommand(LiteralCommandNode.method1("testfps").method3(arg1 -> this.method13())));
      this.method25(new ClientCommand(LiteralCommandNode.method1("testfpscategories").method3(arg1 -> this.method14())));
   }

   private void method1(String text1) {
      OptimizationDebugOption optimizationdebugmodtype2;
      try {
         optimizationdebugmodtype2 = OptimizationDebugOption.valueOf(text1);
      } catch (IllegalArgumentException illegalargumentexception4) {
         Ref.method17("Unknown optimization " + text1);
         return;
      }

      Ref.method17("Testing " + text1 + "... (this will take ~20 seconds)");
      new Thread(
            () -> {
               try {
                  OptimizationDebugMod.Data data2x = new OptimizationDebugMod.Data();
                  OptimizationDebugMod.Data data3 = new OptimizationDebugMod.Data();
                  this.method4(data2x, optimizationdebugmodtype2, false, 5000L);
                  this.method4(data3, optimizationdebugmodtype2, true, 5000L);
                  this.method4(data2x, optimizationdebugmodtype2, false, 5000L);
                  this.method4(data3, optimizationdebugmodtype2, true, 5000L);
                  BackgroundExecutor.method11(
                     () -> Ref.method17(
                        "Test for " + optimizationdebugmodtype2.name() + " finished, Enabled: ~" + data3.getFps() + " fps, Disabled: ~" + data2x.getFps() + " fps"
                     )
                  );
               } catch (InterruptedException interruptedexception4x) {
                  BackgroundExecutor.method11(() -> Ref.method17("Test failed for unknown reason!"));
                  throw new RuntimeException(interruptedexception4x);
               }
            }
         )
         .start();
   }

   private void method13() {
      Ref.method17("Testing fps... (this will take ~20 seconds)");
      new Thread(() -> {
         try {
            OptimizationDebugMod.Data data1 = this.method5(20000L);
            BackgroundExecutor.method11(() -> Ref.method17("Fps test finished, ~" + data1.getFps() + " fps"));
         } catch (InterruptedException interruptedexception2) {
            BackgroundExecutor.method11(() -> Ref.method17("Test failed for unknown reason!"));
            throw new RuntimeException(interruptedexception2);
         }
      }).start();
   }

   private void method14() {
      int number1 = (OptimizationDebugCategory.valuesOnVersion().size() + 2) * 5;
      Ref.method17("Testing fps categories... (this will take ~" + number1 + " seconds)");
      new Thread(() -> {
         ArrayList list1x = new ArrayList();
         list1x.add("Results:");
         EnumSet set2 = EnumSet.copyOf(field10);

         try {
            field10.clear();
            OptimizationDebugMod.Data data3 = this.method5(5000L);
            list1x.add("All: ~" + data3.getFps() + " fps");
            field10.addAll(OptimizationDebugCategory.valuesOnVersion());
            OptimizationDebugMod.Data data4 = this.method5(5000L);
            list1x.add("None: ~" + data4.getFps() + " fps");

            for (OptimizationDebugCategory optimizationdebugmodtype26 : OptimizationDebugCategory.valuesOnVersion()) {
               field10.addAll(OptimizationDebugCategory.valuesOnVersion());
               field10.remove(optimizationdebugmodtype26);
               OptimizationDebugMod.Data data7 = this.method5(5000L);
               list1x.add(optimizationdebugmodtype26 + ": ~" + data7.getFps() + " fps");
            }
         } catch (InterruptedException interruptedexception8) {
            BackgroundExecutor.method11(() -> Ref.method17("Test failed for unknown reason!"));
            throw new RuntimeException(interruptedexception8);
         }

         field10.clear();
         field10.addAll(set2);
         BackgroundExecutor.method11(() -> Ref.method17(String.join("\n", list1x)));
      }).start();
   }

   private void method4(OptimizationDebugMod.Data data1, OptimizationDebugOption optimizationdebugmodtype2, boolean flag3, long number4) {
      this.field17 = ValuePair.method1(optimizationdebugmodtype2, flag3);
      this.method16();
      data1.method1(this.method5(number4));
      this.field17 = null;
   }

   private OptimizationDebugMod.Data method5(long number1) {
      OptimizationDebugMod.Data data3 = new OptimizationDebugMod.Data();
      this.field16.set(0);
      long number4 = System.currentTimeMillis();
      Thread.sleep(number1);
      long number6 = System.currentTimeMillis();
      int number8 = this.field16.get();
      data3.frames += number8;
      data3.time += number6 - number4;
      return data3;
   }

   public MixinCore9Extension method15() {
      return new MixinCore9Base(0.0F, 0.0F, HudAnchor.TOP_LEFT) {
         {
            this.method12(WidgetFactory.withBackground(new ScrollableHudComponent(OptimizationDebugMod.this.field15).method1(300.0F).method3(true)));
         }

         public boolean method31() {
            return false;
         }

         public boolean method4(boolean flag1) {
            return (Boolean)OptimizationDebugMod.this.field13.get() && super.method4(flag1);
         }
      };
   }

   protected void method1(boolean flag1) {
      this.method12(ModTraits.field6, new UnlockableFeature(ModEnabledState.method6(flag1)));
      this.method12(ModTraits.field9, ModSearchIndex.method7());
   }

   private void onDisable() {
      field9.clear();
      field8 = false;
   }

   private void onEnable() {
      this.method16();
   }

   private void method16() {
      if (!this.isEnabled()) {
         this.onDisable();
      }

      Set set1 = Arrays.stream(OptimizationDebugOption.getToggleableEnabledOptis()).map(Enum::name).collect(Collectors.toSet());
      Set set2 = Arrays.stream(OptimizationDebugOption.getToggleableDisabledOptis()).map(Enum::name).collect(Collectors.toSet());
      ((Set)this.field11.get()).removeIf(arg1x -> !set1.contains(arg1x));
      ((Set)this.field12.get()).removeIf(arg1x -> !set2.contains(arg1x));
      field8 = !((Set)this.field11.get()).isEmpty() || !((Set)this.field12.get()).isEmpty();
      field9.clear();
      field9.addAll(Arrays.stream(OptimizationDebugOption.getEnabledOptis()).toList());
      ((Set)this.field11.get()).stream().<OptimizationDebugOption>map(OptimizationDebugOption::valueOf).forEach(field9::remove);
      field9.addAll(((Set)this.field12.get()).stream().map(OptimizationDebugOption::valueOf).toList());
      if (this.field17 != null) {
         field8 = true;
         if ((Boolean)this.field17.field2) {
            field9.add((OptimizationDebugOption)this.field17.field1);
         } else {
            field9.remove(this.field17.field1);
         }
      }

      if (this.method2(ModTraits.field1)) {
         this.field15.method8();

         for (OptimizationDebugOption optimizationdebugmodtype4 : field9) {
            this.field15.method5(new TextHudComponent(optimizationdebugmodtype4.name()));
         }
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field11, this.field12, this.field13, this.field14});

      for (OptimizationDebugCategory optimizationdebugmodtype23 : OptimizationDebugCategory.valuesOnVersion()) {
         lightingextension231.method9(
            new ClientOption[]{
               ((ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("Disable " + optimizationdebugmodtype23 + " rendering").OHICCHCORCORRRHCHRCCIROCCHCCRC())
                        .method4(field10.contains(optimizationdebugmodtype23)))
                     .method31())
                  .CICORRHIOIIOORRRICCORIOIOCIHII(arg2 -> this.method10(optimizationdebugmodtype23, arg2))
            }
         );
      }
   }

   private void method10(OptimizationDebugCategory optimizationdebugmodtype21, boolean flag2) {
      if (flag2) {
         field10.add(optimizationdebugmodtype21);
      } else {
         field10.remove(optimizationdebugmodtype21);
      }
   }

   public String getId() {
      return "OPTIMIZATION_DEBUG_MOD";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method8().method11(this);
   }

   public static boolean method12(OptimizationDebugOption optimizationdebugmodtype0) {
      return !field8 ? optimizationdebugmodtype0.isEnabledByDefault() : field9.contains(optimizationdebugmodtype0);
   }

   public static boolean method13(OptimizationDebugCategory optimizationdebugmodtype20) {
      return !field10.contains(optimizationdebugmodtype20);
   }

   public void method17() {
      this.valid = true;
   }

   @Generated
   public ToggleOption method19() {
      return this.field14;
   }

   @Generated
   public boolean isValid() {
      return this.valid;
   }

   private static class Data {
      private int frames;
      private long time;

      private Data() {
      }

      public int getFps() {
         return (int)(this.frames / ((float)this.time / 1000.0F));
      }

      public void method1(OptimizationDebugMod.Data data1) {
         this.frames = this.frames + data1.frames;
         this.time = this.time + data1.time;
      }
   }
}
