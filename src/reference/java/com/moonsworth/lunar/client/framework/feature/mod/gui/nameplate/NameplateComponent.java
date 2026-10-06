package com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate;

import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.ContainerOverlayPreviewMode;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.ui.GuiResolution;
import java.util.List;
import java.util.function.Supplier;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.NotNull;

public class NameplateComponent extends HudElementBase {
   private static final float field9 = 120.0F;
   private static final float field10 = 100.0F;
   private static final HudAnchor field11 = HudAnchor.MIDDLE_LEFT;
   private static final long field12 = 60000L;
   private static final float field13 = 20.0F;
   private static final int field14 = 176;
   private static final int field15 = 222;
   private final Framework7Extension field16;
   private final Supplier<List<TextComponent>> field17;
   private long field18 = -60000L;
   private boolean field19;

   public NameplateComponent(@NotNull Framework7Extension framework7extension1) {
      this(framework7extension1, null);
   }

   public NameplateComponent(@NotNull Framework7Extension framework7extension1, Supplier<List<TextComponent>> supplier2) {
      this(framework7extension1, 0.0F, 0.0F, field11, supplier2);
   }

   public NameplateComponent(@NotNull Framework7Extension framework7extension1, float value2, float value3, @NotNull HudAnchor gui2extension24) {
      this(framework7extension1, value2, value3, gui2extension24, null);
   }

   public NameplateComponent(@NotNull Framework7Extension framework7extension1, float value2, float value3, @NotNull HudAnchor gui2extension24, Supplier<List<TextComponent>> supplier5) {
      super(value2, value3, gui2extension24);
      this.field16 = framework7extension1;
      this.field17 = supplier5;
      this.method16(120.0F, 100.0F);
   }

   @Override
   public final void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
      if (flag4) {
         MixinHelper_4 mixinhelper_45 = highlightimpl1.method2();
         List list6 = this.field17 == null ? null : this.field17.get();
         if (list6 != null && !list6.isEmpty()) {
            this.method2(mixinhelper_45, value2, value3, list6);
         } else {
            LcuiScreen.method66(mixinhelper_45, value2, value3, value2 + this.getWidth(), value3 + this.getHeight(), 1862270976);
            ModDetails framework87 = (ModDetails)this.field16.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field13);
            TextComponent text8 = Component.text(framework87 == null ? "" : framework87.getName());
            float value9 = Ref.method10().method19();
            mixinhelper_45.method27(Ref.method10(), text8, (int)(value2 + this.getWidth() / 2.0F), (int)(value3 + (this.getHeight() - value9) / 2.0F), -1, true);
         }
      }
   }

   private void method2(MixinHelper_4 mixinhelper_41, float value2, float value3, List<TextComponent> list4) {
      float value5 = Ref.method10().method19();
      float value6 = 0.0F;

      for (TextComponent text8 : list4) {
         float value9 = Ref.method10().bridge$getStringWidth(text8);
         if (value9 > value6) {
            value6 = value9;
         }
      }

      float value12 = value6;
      float value13 = list4.size() * value5;
      this.method16(value12, value13);
      LcuiScreen.method66(mixinhelper_41, value2, value3, value2 + value12, value3 + value13, 1862270976);
      float value14 = value3;

      for (TextComponent text11 : list4) {
         mixinhelper_41.method11(Ref.method10(), text11, value2, value14, -1, true);
         value14 += value5;
      }
   }

   public void markRendered() {
      this.field18 = Ref.method3().bridge$getSystemTime();
   }

   @Override
   public boolean method4(boolean flag1) {
      if (!flag1) {
         this.field19 = false;
         return false;
      }

      if (this.method15()) {
         this.field19 = true;
      }

      return this.field19;
   }

   @Override
   public boolean method30() {
      return this.field19 || this.method15();
   }

   @Override
   public double method20(HudAnchor gui2extension21, double value2) {
      GuiResolution threadmoduledump714 = LcuiScreen.method151();
      double value5 = (this.method9(threadmoduledump714) - 20.0F) / this.method11(value2, threadmoduledump714.getScaledWidth()) - this.getWidth();
      return value5 + this.getX() / this.getScale();
   }

   @Override
   public double method22(HudAnchor gui2extension21, double value2) {
      GuiResolution threadmoduledump714 = LcuiScreen.method151();
      double value5 = this.method10(threadmoduledump714) / this.method11(value2, threadmoduledump714.getScaledHeight());
      return value5 + this.getY() / this.getScale();
   }

   @Override
   public double method23(double value1) {
      return (this.OCRORCCHCRIOOIRHOHRRHCRRHRCIHO(value1) + this.getWidth()) * this.getScale();
   }

   @Override
   public double method24(double value1) {
      return this.CORRCOHCRHOHHOIHOIOICORROHOOOO(value1) * this.getScale();
   }

   private float method9(GuiResolution threadmoduledump711) {
      return Ref.method3().bridge$getCurrentScreen() instanceof GuiContainerBridge bridge5extension_32
         ? bridge5extension_32.bridge$getGuiLeft()
         : (threadmoduledump711.getScaledWidth() - 176) / 2.0F;
   }

   private float method10(GuiResolution threadmoduledump711) {
      return Ref.method3().bridge$getCurrentScreen() instanceof GuiContainerBridge bridge5extension_32
         ? bridge5extension_32.bridge$getGuiTop()
         : (threadmoduledump711.getScaledHeight() - 222) / 2.0F;
   }

   private float method11(double value1, double value3) {
      float value5 = value1 <= 0.0 ? 1.0F : (float)(value3 / value1);
      return this.getScale() * value5;
   }

   private boolean method15() {
      return switch ((ContainerOverlayPreviewMode)Ref.method4().method40().method82().method34().get()) {
         case ALWAYS -> true;
         case RECENT -> Ref.method3().bridge$getSystemTime() - this.field18 <= 60000L;
         case NEVER -> false;
      };
   }
}
