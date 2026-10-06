package com.moonsworth.lunar.client.framework.feature.debug;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.event.LunarEvent;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRenderTick.EventRenderTickStart;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.misc.debug.GraphDebugMod;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;
import it.unimi.dsi.fastutil.longs.Long2FloatLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import lombok.Generated;
import org.jetbrains.annotations.MustBeInvokedByOverriders;
import org.jetbrains.annotations.Nullable;

public abstract class Debug extends AbstractFeature {
   protected final ToggleOption field8 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showGraph").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showUsage").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ColorOption field10 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "graphColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-872349952))
      .method31();
   protected final EnumOption<Debug.Type> field11 = (EnumOption<Debug.Type>)OptionFactory.method10("updateRate", Debug.Type.EVERY_TICK)
      .method31();
   protected final IntegerOption field12 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "graphTimeframe"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(60))
         .OCRRICRIORICCCRHIOHORCICIHHICO(1, 1800))
      .method31();
   protected final Map<Debug.Extension, Long2FloatLinkedOpenHashMap> field13;
   protected String field14;
   protected boolean field15;
   @Nullable
   private Class<? extends LunarEvent> field16;
   @Nullable
   private Consumer<? extends LunarEvent> field17;

   public Debug(boolean flag1) {
      super(flag1);
      this.method13(ModTraits.field1, this.method13());
      List list2 = this.method14();
      this.field13 = new LinkedHashMap<>(list2.size());

      for (Debug.Extension extension4 : list2) {
         this.field13.put(extension4, new Long2FloatLinkedOpenHashMap());
         if (extension4.method21() != -1L) {
            this.field15 = true;
         }
      }

      this.field11.CICORRHIOIIOORRRICCORIOIOCIHII(arg1x -> {
         if (this.field16 != null && this.field17 != null) {
            LunarEventBus.method29().method6(this.field16, this.field17);
            this.field16 = null;
            this.field17 = null;
         }

         arg1x.getEventHandler().accept((arg1xx, arg2x) -> {
            this.field17 = arg2x;
            this.field16 = arg1xx;
            this.handle(arg1xx, arg2x);
         }, () -> {
            boolean flag1xx = true;
            boolean flag2x = !(Boolean)this.field8.get();

            for (Entry entry4x : this.field13.entrySet()) {
               Debug.Extension extension5 = (Debug.Extension)entry4x.getKey();
               long number6 = extension5.method21();
               if (number6 != -1L) {
                  long number8 = extension5.getValue();
                  float value10 = extension5.method2();
                  if (flag1xx) {
                     boolean flag11 = extension5.method3() != (Boolean)this.field9.get();
                     String text12 = this.method3(flag11 ? number6 - number8 : number8);
                     this.field14 = String.format("%s  %.2f%%", text12, 100.0F - value10 * 100.0F);
                     flag1xx = false;
                  }

                  if (flag2x) {
                     return;
                  }

                  long number16 = System.currentTimeMillis();
                  long number13 = number16 - (Integer)this.field12.get() * 1000;
                  Long2FloatLinkedOpenHashMap long2floatlinkedopenhashmap15 = (Long2FloatLinkedOpenHashMap)entry4x.getValue();
                  long2floatlinkedopenhashmap15.long2FloatEntrySet().removeIf(arg2xx -> arg2xx.getLongKey() < number13);
                  long2floatlinkedopenhashmap15.put(number16, value10);
               } else if (flag1xx) {
                  this.field14 = this.method3(extension5.getValue());
                  flag1xx = false;
                  if (flag2x) {
                     return;
                  }
               }
            }
         });
      });
      this.field8.CICORRHIOIIOORRRICCORIOIOCIHII(arg1x -> this.field13.values().forEach(Long2FloatLinkedOpenHashMap::clear));
   }

   protected abstract Debug.Data method13();

   protected abstract List<Debug.Extension> method14();

   protected abstract String method3(long number1);

   @MustBeInvokedByOverriders
   @Override
   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.SETTINGS,
         arg1x -> {
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field8, arg1xx -> arg1xx.method9(new ClientOption[]{this.field10}))
               .method3(() -> !this.field15 || !(Boolean)((TypedHudRenderer)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field1)).method27().get());
            arg1x.method9(new ClientOption[]{this.field9}).method3(() -> !this.field15);
            arg1x.method9(new ClientOption[]{this.field11, this.field12});
         }
      );
   }

   @Override
   protected ModDetails method20() {
      return ModDetails.method7().method1(ModCategory.field7).method11(this);
   }

   protected class Data extends TypedHudRenderer<String> {
      private static final HudSize field31 = new HudSize(10, 24, 80, 50, 100, 120);
      private final String field32;

      public Data(float value2, float value3, String text4) {
         super(value2, value3, HudAnchor.MIDDLE_LEFT);
         this.field32 = text4;
      }

      @Override
      public HudSize method15() {
         return field31;
      }

      @Nullable
      public String method2(boolean flag1) {
         return Debug.this.field14;
      }

      @Override
      protected void method18(MixinHelper_4 mixinhelper_41, float value2, float value3, boolean flag4, boolean flag5) {
         if (!flag5) {
            GraphDebugMod graphdebugmod9 = ((ChildModBinding)Debug.this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field16)).method1();
            if (graphdebugmod9.method13()) {
               mixinhelper_41.push();
               mixinhelper_41.scale(0.5F, 0.5F, 0.5F);
               mixinhelper_41.method19(Ref.method10(), this.field32, (value2 + 1.0F) * 2.0F, (value3 + 1.0F) * 2.0F, -1, false);
               mixinhelper_41.pop();
            }
         } else {
            if (Debug.this.field15 && (Boolean)Debug.this.field8.get()) {
               for (Entry entry7 : Debug.this.field13.entrySet()) {
                  Long2FloatLinkedOpenHashMap long2floatlinkedopenhashmap8 = (Long2FloatLinkedOpenHashMap)entry7.getValue();
                  if (long2floatlinkedopenhashmap8.size() > 1) {
                     this.method4(mixinhelper_41, value2, value3, this.getWidth(), this.getHeight(), long2floatlinkedopenhashmap8, ((Debug.Extension)entry7.getKey()).getColor());
                  }
               }
            }
         }
      }

      protected void method4(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4, float value5, Long2FloatLinkedOpenHashMap long2floatlinkedopenhashmap6, int number7) {
         float value8 = this.getScale();
         long number9 = long2floatlinkedopenhashmap6.firstLongKey();
         long number11 = long2floatlinkedopenhashmap6.lastLongKey();
         double value13 = number11 - number9;
         float value15 = value2 + value4;
         float value16 = value3 + value5;
         mixinhelper_41.method32(1.0F, number7 == -1 ? Debug.this.field10.method14(0.0F) : number7, arg10 -> {
            boolean flag11x = true;
            float value12 = 0.0F;
            float value13x = 0.0F;
            ObjectBidirectionalIterator objectbidirectionaliterator14 = long2floatlinkedopenhashmap6.long2FloatEntrySet().iterator();

            while (objectbidirectionaliterator14.hasNext()) {
               it.unimi.dsi.fastutil.longs.Long2FloatMap.Entry entry15x = (it.unimi.dsi.fastutil.longs.Long2FloatMap.Entry)objectbidirectionaliterator14.next();
               float value16x = MathUtils.lerp(value2, value15, (float)((entry15x.getLongKey() - number9) / value13)) * value8;
               float value17 = MathUtils.lerp(value3, value16, entry15x.getFloatValue()) * value8;
               if (!flag11x) {
                  arg10.accept(value12, value13x, value16x, value17);
               } else {
                  flag11x = false;
               }

               value12 = value16x;
               value13x = value17;
            }
         });
      }
   }

   public interface Extension {
      long method21();

      long getValue();

      default float method2() {
         return 2.0F - (float)((double)this.method21() / this.getValue());
      }

      default int getColor() {
         return -1;
      }

      default boolean method3() {
         return false;
      }
   }

   protected enum Type implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
      EVERY_FRAME((arg0, arg1) -> arg0.accept(EventRenderTickStart.class, arg1x -> arg1.run())),
      EVERY_OTHER_FRAME((arg0, arg1) -> {
         AtomicBoolean atomicboolean2 = new AtomicBoolean(true);
         arg0.accept(EventRenderTickStart.class, arg2x -> {
            if (atomicboolean2.get()) {
               arg1.run();
               atomicboolean2.set(false);
            } else {
               atomicboolean2.set(true);
            }
         });
      }),
      EVERY_TICK((arg0, arg1) -> arg0.accept(EventTick.class, arg1x -> arg1.run())),
      EVERY_OTHER_TICK((arg0, arg1) -> arg0.accept(EventTick.class, arg1x -> {
         if (EventTick.field1 % 2 == 0) {
            arg1.run();
         }
      })),
      EVERY_HALF_SECOND((arg0, arg1) -> arg0.accept(EventTick.class, arg1x -> {
         if (EventTick.field1 % 10 == 0) {
            arg1.run();
         }
      })),
      EVERY_SECOND((arg0, arg1) -> arg0.accept(EventSecond.class, arg1x -> arg1.run())),
      EVERY_15_SECONDS((arg0, arg1) -> {
         AtomicInteger number2 = new AtomicInteger();
         arg0.accept(EventSecond.class, arg2x -> {
            int number3 = number2.getAndIncrement();
            if (number3 == 0) {
               arg1.run();
            } else if (number3 >= 15) {
               number2.set(0);
            }
         });
      }),
      EVERY_30_SECONDS((arg0, arg1) -> {
         AtomicInteger number2 = new AtomicInteger();
         arg0.accept(EventSecond.class, arg2x -> {
            int number3 = number2.getAndIncrement();
            if (number3 == 0) {
               arg1.run();
            } else if (number3 >= 30) {
               number2.set(0);
            }
         });
      });

      private final BiConsumer<BiConsumer<Class<? extends LunarEvent>, Consumer<? extends LunarEvent>>, Runnable> eventHandler;

      public String id() {
         return this.name();
      }

      @Generated
      public BiConsumer<BiConsumer<Class<? extends LunarEvent>, Consumer<? extends LunarEvent>>, Runnable> getEventHandler() {
         return this.eventHandler;
      }

      @Generated
      Type(BiConsumer<BiConsumer<Class<? extends LunarEvent>, Consumer<? extends LunarEvent>>, Runnable> biconsumer3) {
         this.eventHandler = biconsumer3;
      }
   }
}
