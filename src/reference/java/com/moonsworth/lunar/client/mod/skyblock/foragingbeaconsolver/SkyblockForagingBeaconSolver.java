package com.moonsworth.lunar.client.mod.skyblock.foragingbeaconsolver;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.event.screen.EventScreenOpen;
import com.moonsworth.lunar.client.event.render.EventRenderHologram.EventRenderHologramText;
import com.moonsworth.lunar.client.event.mixin.fishing.EventPlaySound;
import com.moonsworth.lunar.client.event.mixin.gui.EventSlotUpdate;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.util.math.NumberUtils;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockForagingBeaconSolver extends AbstractFeature {
   private static final Pattern field8 = Pattern.compile("^Current color: (?<color>[\\w\\s]+)$");
   private static final Pattern field9 = Pattern.compile("^Current speed: (?<speed>\\d)$");
   private static final Pattern field10 = Pattern.compile("^Current pitch: (?<pitch>Low|Normal|High)$");
   private static final int field11 = 2;
   private static final int field12 = 6;
   private final HighlightTypeListener field13 = (HighlightTypeListener)this.method63(HighlightTypeListener.class);
   private final ColorOption field14 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "textColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private int field15;
   private int field16 = -1;
   private int field17;
   private final List<SkyblockForagingBeaconSolver.Data> field18 = new ArrayList<>();
   private final SkyblockForagingBeaconSolver.Data field19 = new SkyblockForagingBeaconSolver.Data();
   private final SkyblockForagingBeaconSolver.Data field20 = new SkyblockForagingBeaconSolver.Data();
   private final SkyblockForagingBeaconSolver.Data field21 = new SkyblockForagingBeaconSolver.Data();
   private final SkyblockForagingBeaconSolver.Data field22 = new SkyblockForagingBeaconSolver.Data();

   public SkyblockForagingBeaconSolver(Skyblock skyblock1) {
      super(true);
      this.method8(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method8(ModTraits.field17, ModCategories.method2(SettingsPage.FORAGING));
      this.method8(
         ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.GALATEA || IslandUtils.getIsland() == SkyblockIsland.TORRHUS_CANYON)
      );
      this.handle(EventScreenOpen.class, this::method1);
      this.handle(EventSlotUpdate.class, this::method2);
      this.handle(EventPlaySound.class, arg1x -> {
         this.method3(arg1x);
         this.method4(arg1x);
      });
      this.handle(EventRenderHologramText.class, this::method5);
      this.handle(com.moonsworth.lunar.client.event.mixin.gui.EventServerTick.class, this::method6);
   }

   private void method1(EventScreenOpen highlightimpl91) {
      this.field16 = -1;
      this.field17 = 0;
      this.field18.clear();
      this.field19.reset();
      this.field20.reset();
      this.field21.reset();
      this.field22.reset();
   }

   private void method2(EventSlotUpdate highlightimpl1) {
      if (this.method13()) {
         ItemStackBridge bridgeextension_42 = highlightimpl1.method3();
         if (bridgeextension_42 != null && !bridgeextension_42.bridge$isEmpty()) {
            SkyblockForagingBeaconSolver.Data data3;
            SkyblockForagingBeaconSolver.Data data4;
            if (bridgeextension_42.bridge$hasFoil()) {
               data3 = this.field20;
               data4 = this.field22;
            } else {
               data3 = this.field19;
               data4 = this.field21;
            }

            List list5 = SkyblockItemUtil.method15(bridgeextension_42);
            String text6 = ChatFormatting.getTextWithoutFormattingCodes(bridgeextension_42.bridge$getDisplayName());
            if (text6.equals("Match the Beat") || text6.equals("Match these Beats")) {
               com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil.DyeColor type215 = SkyblockItemUtil.method5(bridgeextension_42);
               if (type215 == null || type215 == com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil.DyeColor.GRAY) {
                  return;
               }

               SkyblockForagingBeaconSolver.Type type18 = SkyblockForagingBeaconSolver.Type.fromItemColor(type215);
               if (type18 != null) {
                  data3.method11(type18);
               }

               SkyblockForagingBeaconSolver.Type type21 = SkyblockForagingBeaconSolver.Type.fromItemColor(SkyblockItemUtil.method5(highlightimpl1.method2()));
               if (type21 != null && !type21.equals(data3.method6())) {
                  return;
               }

               int number10 = data3.method9();
               SkyblockForagingBeaconSolver.BeaconSpeed type311 = SkyblockForagingBeaconSolver.BeaconSpeed.fromTicks(this.field15 - number10);
               if (number10 != 0 && type311 != null) {
                  data3.method12(type311);
               }

               data3.method14(this.field15);
            } else if (text6.equals("Change the Beat") || text6.equals("Change the Beats")) {
               com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil.DyeColor type214 = SkyblockItemUtil.method5(bridgeextension_42);
               if (type214 == null || type214 == com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil.DyeColor.GRAY) {
                  return;
               }

               data4.method14(this.field15);
            } else if (text6.equals("Color")) {
               for (String text8 : list5) {
                  Matcher matcher9 = field8.matcher(text8);
                  if (matcher9.matches()) {
                     data4.method11(SkyblockForagingBeaconSolver.Type.fromText(matcher9.group("color")));
                     return;
                  }
               }
            } else if (text6.equals("Speed")) {
               for (String text16 : list5) {
                  Matcher matcher19 = field9.matcher(text16);
                  if (matcher19.matches()) {
                     data4.method12(SkyblockForagingBeaconSolver.BeaconSpeed.fromText(matcher19.group("speed")));
                     return;
                  }
               }
            } else if (text6.equals("Pitch")) {
               for (String text17 : list5) {
                  Matcher matcher20 = field10.matcher(text17);
                  if (matcher20.matches()) {
                     data4.method13(SkyblockForagingBeaconSolver.BeaconPitch.fromText(matcher20.group("pitch")));
                     return;
                  }
               }
            }
         }
      }
   }

   private void method3(EventPlaySound highlightimpl131) {
      if (this.field13.method7() == SkyblockMenuType.FORAGING_BEACON) {
         if (highlightimpl131.getPath().equals("block.note_block.bass")) {
            SkyblockForagingBeaconSolver.BeaconPitch type22 = SkyblockForagingBeaconSolver.BeaconPitch.fromPitch(highlightimpl131.getPitch());
            if (type22 != null) {
               if (type22 != this.field21.method8()) {
                  this.field19.method13(type22);
               }
            }
         }
      }
   }

   private void method4(EventPlaySound highlightimpl131) {
      if (this.field13.method7() == SkyblockMenuType.UPGRADE_SIGNAL_STRENGTH) {
         if (highlightimpl131.getPath().equals("block.note_block.bass")) {
            SkyblockForagingBeaconSolver.BeaconPitch type22 = SkyblockForagingBeaconSolver.BeaconPitch.fromPitch(highlightimpl131.getPitch());
            if (type22 != null) {
               if (this.field15 != this.field16) {
                  this.field16 = this.field15;
                  this.field17 = 0;
                  this.field18.clear();
                  if (this.method8(this.field19)) {
                     this.field18.add(this.field19);
                  }

                  if (this.method8(this.field20)) {
                     this.field18.add(this.field20);
                  }
               }

               if (this.field17 < this.field18.size()) {
                  this.field18.get(this.field17).method1(type22);
               }

               this.field17++;
            }
         }
      }
   }

   private void method5(EventRenderHologramText data61) {
      if (this.method13()) {
         int number2 = data61.method3().bridge$getIndex();
         int number3 = 0;
         if (this.field13.method7() == SkyblockMenuType.FORAGING_BEACON) {
            switch (number2) {
               case 37:
                  number3 = this.field19.method2(this.field21);
               case 38:
               case 40:
               default:
                  break;
               case 39:
                  number3 = this.field19.method3(this.field21);
                  break;
               case 41:
                  number3 = this.field19.method4(this.field21);
            }
         } else {
            switch (number2) {
               case 36:
                  number3 = this.field19.method2(this.field21);
               case 37:
               case 39:
               case 41:
               case 42:
               case 43:
               case 44:
               case 46:
               case 48:
               default:
                  break;
               case 38:
                  number3 = this.field19.method3(this.field21);
                  break;
               case 40:
                  number3 = this.field19.method4(this.field21);
                  break;
               case 45:
                  number3 = this.field20.method2(this.field22);
                  break;
               case 47:
                  number3 = this.field20.method3(this.field22);
                  break;
               case 49:
                  number3 = this.field20.method4(this.field22);
            }
         }

         if (number3 != 0) {
            data61.method1(Component.text(number3, TextColor.color(this.field14.method14(0.0F))));
         }
      }
   }

   private void method6(com.moonsworth.lunar.client.event.mixin.gui.EventServerTick highlightimpl91) {
      this.field15++;
   }

   private boolean method13() {
      SkyblockMenuType highlighttype1 = this.field13.method7();
      return highlighttype1 == SkyblockMenuType.FORAGING_BEACON || highlighttype1 == SkyblockMenuType.UPGRADE_SIGNAL_STRENGTH;
   }

   private boolean method8(SkyblockForagingBeaconSolver.Data data1) {
      int number2 = data1.method9();
      if (number2 == 0) {
         return false;
      }

      SkyblockForagingBeaconSolver.BeaconSpeed type33 = data1.method7();
      return type33 == null ? false : this.field15 - (number2 + type33.getTicks()) == 0;
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.COLOR, arg1x -> arg1x.method9(new ClientOption[]{this.field14}));
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_FORAGING_BEACON_SOLVER";
   }

   private static class Data {
      @Nullable
      private SkyblockForagingBeaconSolver.Type field1;
      @Nullable
      private SkyblockForagingBeaconSolver.BeaconSpeed field2;
      @Nullable
      private SkyblockForagingBeaconSolver.BeaconPitch field3;
      private int field4;
      private final Deque<SkyblockForagingBeaconSolver.BeaconPitch> field5 = new ArrayDeque<>();

      private Data() {
      }

      private void method1(SkyblockForagingBeaconSolver.BeaconPitch type21) {
         this.field5.add(type21);
         if (this.field5.size() > 6) {
            this.field5.removeFirst();
         }

         SkyblockForagingBeaconSolver.BeaconPitch[] items2 = SkyblockForagingBeaconSolver.BeaconPitch.values();
         int[] items3 = new int[items2.length];

         for (SkyblockForagingBeaconSolver.BeaconPitch type25 : this.field5) {
            items3[type25.ordinal()]++;
         }

         int index8 = 0;
         int number9 = 0;
         SkyblockForagingBeaconSolver.BeaconPitch type26 = null;

         for (int index7 = 0; index7 < items3.length; index7++) {
            if (items3[index7] != 0) {
               index8++;
               if (items3[index7] > number9) {
                  number9 = items3[index7];
                  type26 = items2[index7];
               }
            }
         }

         if (index8 > 2) {
            this.field5.clear();
         } else if (this.field5.size() >= 2 && number9 * 2 > this.field5.size()) {
            this.field3 = type26;
         }
      }

      public int method2(SkyblockForagingBeaconSolver.Data data1) {
         return this.method5(this.method6(), data1.method6());
      }

      public int method3(SkyblockForagingBeaconSolver.Data data1) {
         return this.method5(this.method7(), data1.method7());
      }

      public int method4(SkyblockForagingBeaconSolver.Data data1) {
         return this.method5(this.method8(), data1.method8());
      }

      private int method5(Enum<?> value1, Enum<?> value2) {
         if (value1 != null && value2 != null) {
            int number3 = ((Enum[])value1.getDeclaringClass().getEnumConstants()).length;
            int number4 = number3 / 2;
            int number5 = value1.ordinal() - value2.ordinal();
            if (number5 > number4) {
               number5 -= number3;
            } else if (number5 < -number4) {
               number5 += number3;
            }

            return number5;
         } else {
            return 0;
         }
      }

      private void reset() {
         this.field1 = null;
         this.field2 = null;
         this.field3 = null;
         this.field4 = 0;
         this.field5.clear();
      }

      @Nullable
      @Generated
      public SkyblockForagingBeaconSolver.Type method6() {
         return this.field1;
      }

      @Nullable
      @Generated
      public SkyblockForagingBeaconSolver.BeaconSpeed method7() {
         return this.field2;
      }

      @Nullable
      @Generated
      public SkyblockForagingBeaconSolver.BeaconPitch method8() {
         return this.field3;
      }

      @Generated
      public int method9() {
         return this.field4;
      }

      @Generated
      public Deque<SkyblockForagingBeaconSolver.BeaconPitch> method10() {
         return this.field5;
      }

      @Generated
      public void method11(@Nullable SkyblockForagingBeaconSolver.Type type1) {
         this.field1 = type1;
      }

      @Generated
      public void method12(@Nullable SkyblockForagingBeaconSolver.BeaconSpeed type31) {
         this.field2 = type31;
      }

      @Generated
      public void method13(@Nullable SkyblockForagingBeaconSolver.BeaconPitch type21) {
         this.field3 = type21;
      }

      @Generated
      public void method14(int number1) {
         this.field4 = number1;
      }
   }

   private enum Type {
      WHITE,
      ORANGE,
      MAGENTA,
      LIGHT_BLUE,
      YELLOW,
      LIME,
      PINK,
      CYAN,
      PURPLE,
      BLUE,
      BROWN,
      GREEN,
      RED;

      Type() {
      }

      @Nullable
      public static SkyblockForagingBeaconSolver.Type fromItemColor(@Nullable BeaconPitch type20) {
         if (type20 == null) {
            return null;
         }

         try {
            return valueOf(type20.name());
         } catch (IllegalArgumentException illegalargumentexception2) {
            return null;
         }
      }

      @Nullable
      public static SkyblockForagingBeaconSolver.Type fromText(String text0) {
         String text1 = text0.toUpperCase(Locale.ROOT).replaceAll(" ", "_");

         try {
            return valueOf(text1);
         } catch (IllegalArgumentException illegalargumentexception3) {
            return null;
         }
      }
   }

   private enum BeaconPitch {
      LOW(0.0952381F),
      NORMAL(0.7936508F),
      HIGH(1.4920635F);

      private final float pitch;

      @Nullable
      public static SkyblockForagingBeaconSolver.BeaconPitch fromPitch(float value0) {
         for (SkyblockForagingBeaconSolver.BeaconPitch type24 : values()) {
            if (Math.abs(type24.pitch - value0) < 0.001F) {
               return type24;
            }
         }

         return null;
      }

      public static SkyblockForagingBeaconSolver.BeaconPitch fromText(String text0) {
         return valueOf(text0.toUpperCase(Locale.ROOT));
      }

      @Generated
      BeaconPitch(float value3) {
         this.pitch = value3;
      }
   }

   private enum BeaconSpeed {
      ONE(55),
      TWO(45),
      THREE(35),
      FOUR(25),
      FIVE(15);

      private final int ticks;

      @Nullable
      public static SkyblockForagingBeaconSolver.BeaconSpeed fromTicks(int number0) {
         for (SkyblockForagingBeaconSolver.BeaconSpeed type34 : values()) {
            if (Math.abs(type34.getTicks() - number0) <= 1) {
               return type34;
            }
         }

         return null;
      }

      @Nullable
      public static SkyblockForagingBeaconSolver.BeaconSpeed fromText(String text0) {
         int index1 = NumberUtils.method3(text0);
         return index1 > 0 && index1 <= values().length ? values()[index1 - 1] : null;
      }

      @Generated
      BeaconSpeed(int number3) {
         this.ticks = number3;
      }

      @Generated
      public int getTicks() {
         return this.ticks;
      }
   }
}
