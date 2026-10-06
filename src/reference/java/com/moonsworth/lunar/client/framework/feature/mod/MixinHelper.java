package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.bridge.Bridge3_18;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.Bridge_7;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Locale;
import lombok.Generated;
import net.kyori.adventure.text.Component;

@Annotation2(min = 1)
public class MixinHelper {
   public static final MixinHelper field1 = new MixinHelper();
   private final Deque<MixinHelper3> stateStack = new ArrayDeque<>();
   private final MixinHelper2 widgetStateCache = new MixinHelper2(this);
   private final MixinHelper211 scissorStack = new MixinHelper211(this);
   private final MixinHelper29 tooltipRenderer = new MixinHelper29(this);
   private final MixinHelper27 textRenderer = new MixinHelper27(this);
   private final MixinHelper210 rectRenderer = new MixinHelper210(this);
   private final MixinHelper28 textFieldRenderer = new MixinHelper28(this);
   private final MixinHelper25 itemStackRenderer = new MixinHelper25(this);
   private final MixinHelper23 slotRenderer = new MixinHelper23(this);
   private final MixinHelper26 boxRenderer = new MixinHelper26(this);
   private final MixinHelper24 panelRenderer = new MixinHelper24(this);
   private final MixinHelper2_2 mouseInput = new MixinHelper2_2(this);
   private final MixinHelper22 keyboardInput = new MixinHelper22(this);
   private final MixinHelper22_2 scrollAnimator = new MixinHelper22_2(this);
   private final List<MixinHelper2_3> components = new ArrayList<>();
   private MixinHelperType theme = MixinHelperType.DARK_BLUE;
   private Bridge5Extension6 screen = null;
   private String guiId;
   private MixinHelper_4 drawContext;
   private boolean containerScreen;

   public MixinHelper() {
      this.components.add(this.widgetStateCache);
      this.components.add(this.scissorStack);
      this.components.add(this.tooltipRenderer);
      this.components.add(this.textRenderer);
      this.components.add(this.rectRenderer);
      this.components.add(this.textFieldRenderer);
      this.components.add(this.itemStackRenderer);
      this.components.add(this.slotRenderer);
      this.components.add(this.boxRenderer);
      this.components.add(this.panelRenderer);
      this.components.add(this.mouseInput);
      this.components.add(this.keyboardInput);
      this.components.add(this.scrollAnimator);
   }

   public void method1(String var1, Bridge5Extension6 var2, MixinHelper_4 var3, int var4, int var5, boolean var6) {
      if (this.screen != null) {
         throw new IllegalStateException("Called start twice before calling end!");
      }

      this.screen = var2;
      this.guiId = var1;
      this.drawContext = var3;
      this.containerScreen = var6;
      this.mouseInput.method1(var4, var5);
      this.stateStack.clear();
      this.stateStack.add(new MixinHelper3());

      for (MixinHelper2_3 var8 : this.components) {
         var8.start();
      }
   }

   public void end() {
      if (this.screen == null) {
         throw new IllegalStateException("Called end without calling start!");
      }

      for (MixinHelper2_3 var2 : this.components) {
         var2.end();
      }

      this.screen = null;
      this.guiId = null;
      this.drawContext = null;
      this.stateStack.clear();
   }

   MixinHelper3 currentState() {
      return this.stateStack.peek();
   }

   public void method3() {
      this.getRectRenderer().method17();
      this.stateStack.push(new MixinHelper3(this.currentState()));
   }

   public void method4() {
      this.getRectRenderer().method17();
      this.stateStack.pop();
      this.scissorStack.update();
   }

   public void method5(String var1) {
      this.textFieldRenderer.method6(var1);
   }

   public void method6(String var1) {
      this.widgetStateCache.clearGui(var1);
   }

   public void removeWidget(String var1, String var2) {
      this.widgetStateCache.removeWidget(var1, var2);
   }

   public boolean method8(String var1, String var2) {
      return this.textFieldRenderer.method2(var1, var2);
   }

   public void method9(String var1, String var2) {
      this.textFieldRenderer.method3(var1, var2);
   }

   public void method10(String var1, String var2, String var3) {
      this.textFieldRenderer.method4(var1, var2, var3);
   }

   public void method11(boolean var1) {
      this.currentState().field1 = var1;
   }

   public void method12(boolean var1) {
      this.currentState().field7 = var1;
   }

   public void method13(boolean var1) {
      this.currentState().field8 = var1;
   }

   public void method14(String var1) {
      if (var1 == null) {
         this.currentState().field5 = null;
      } else {
         this.currentState().field5 = var1.toLowerCase(Locale.ROOT);
      }
   }

   public void method15(boolean var1) {
      this.currentState().field6 = var1;
   }

   public void method16(boolean var1) {
      this.currentState().field2 = var1;
   }

   public void method17(boolean var1) {
      this.currentState().field3 = var1;
   }

   public void method18(int var1, int var2, int var3, int var4) {
      this.scissorStack.method1(var1, var2, var3, var4);
   }

   public void setScissor(int var1, int var2, int var3, int var4) {
      this.scissorStack.method2(var1, var2, var3, var4);
   }

   public void method20(double var1, double var3, double var5) {
      this.currentState().field9 *= var1;
      this.currentState().field10 *= var3;
      this.currentState().field11 *= var5;
   }

   public int method21(ItemStackBridge var1, int var2, int var3) {
      return this.itemStackRenderer.method2(var1, var2, var3, false, false);
   }

   public int method22(ItemStackBridge var1, int var2, int var3, boolean var4) {
      return this.itemStackRenderer.method2(var1, var2, var3, var4, false);
   }

   public void method23(String var1, int var2, int var3, boolean var4, float var5) {
      this.textRenderer.method1(var1, var2, var3, var4, var5);
   }

   public void method24(String var1, int var2, int var3, boolean var4) {
      this.textRenderer.method1(var1, var2, var3, var4, 1.0F);
   }

   public void method25(String var1, int var2, int var3, boolean var4, float var5) {
      this.textRenderer.method2(var1, var2, var3, var4, var5);
   }

   public void method26(String var1, int var2, int var3, boolean var4) {
      this.textRenderer.method2(var1, var2, var3, var4, 1.0F);
   }

   public int getStringWidth(String var1) {
      return this.textRenderer.getStringWidth(var1);
   }

   public boolean method27(String var1, int var2, int var3, int var4) {
      return this.mouseInput.method2(var1, var2, var3, var4);
   }

   public boolean method28(String var1, int var2, int var3, int var4) {
      return this.mouseInput.method3(var1, var2, var3, var4);
   }

   public boolean method29(String var1, Bridge_7 var2) {
      return this.keyboardInput.addKeyEvent(var1, var2);
   }

   public int drawSlotList(int var1, int var2, List<ItemStackBridge> var3) {
      return this.method31(null, var1, var2, var3);
   }

   public int method31(String var1, int var2, int var3, List<ItemStackBridge> var4) {
      return this.slotRenderer.method1(var1, var2, var3, var4);
   }

   public int drawSlotGridFlagged(String var1, int var2, int var3, List<ItemStackBridge> var4, boolean var5) {
      return this.slotRenderer.drawSlots(var1, var2, var3, var4, var5);
   }

   public void method33(String var1, int var2, int var3, List<Bridge3_18> var4, boolean var5, boolean var6) {
      this.slotRenderer.method3(var1, var2, var3, var4, var5, var6);
   }

   public int method34(int var1, int var2, int var3, int var4) {
      return this.panelRenderer.method1(null, var1, var2, var3, var4);
   }

   public int method35(String var1, int var2, int var3, int var4, int var5) {
      return this.panelRenderer.method1(var1, var2, var3, var4, var5);
   }

   public int drawPanelLeftAligned(String var1, int var2, int var3, int var4, int var5, int var6) {
      return this.panelRenderer.drawPanelLeftAligned(var1, var2, var3, var4, var5, var6);
   }

   public void method37(int var1, int var2, int var3, int var4) {
      this.boxRenderer.method1(var1, var2, var3, var4);
   }

   public void method38(int var1, int var2, int var3, int var4) {
      this.boxRenderer.method2(var1, var2, var3, var4);
   }

   public void drawSeparator(int var1, int var2, int var3) {
      this.boxRenderer.method4(var1, var2, var3);
   }

   public MixinHelper_2 method40(int var1, int var2, int var3, int var4) {
      return this.mouseInput.method4(var1, var2, var3, var4);
   }

   public int method41(int var1, int var2, int var3, int var4) {
      MixinHelper_2 var5 = this.method40(var1, var2, var3, var4);
      return var5 == null ? -1 : var5.method2();
   }

   public boolean method42(int var1, int var2, int var3, int var4) {
      return this.mouseInput.method5(var1, var2, var3, var4);
   }

   public void method43(int var1, int var2, int var3, int var4, int var5) {
      this.rectRenderer.method1(var1, var2, var3, var4, var5);
   }

   public Bridge5Extension6 method44() {
      return this.screen;
   }

   public String method45(String var1, int var2, int var3, int var4, int var5, String var6, boolean flag) {
      return this.textFieldRenderer.method7(var1, var2, var3, var4, var5, var6, flag);
   }

   public void translate(double var1, double var3) {
      this.rectRenderer.method17();
      this.drawContext.method39((float)var1, (float)var3);
   }

   public void translateScaled(float var1, float var2) {
      this.rectRenderer.method17();
      this.drawContext.method40(var1, var2);
   }

   public void method47(String var1, int var2, int var3, int var4, int var5) {
      this.widgetStateCache.beginWidget(var1, var2, var3, var4, var5);
   }

   public void method48(String var1) {
      this.widgetStateCache.endWidget(var1);
   }

   public void method49(String var1, String var2) {
      this.widgetStateCache.invalidateWidget(var1, var2);
   }

   public void method50(String var1) {
      this.widgetStateCache.invalidateWidget(var1);
   }

   public float method51(String var1, int var2, int var3) {
      return this.textRenderer.method3(var1, var2, var3);
   }

   public void method52(String var1) {
      this.method5(var1);
   }

   public void method53() {
      this.itemStackRenderer.drawCursorStack();
   }

   public void method54(String var1) {
      this.tooltipRenderer.method9(List.of(Component.text(var1)));
   }

   public void showTooltipComponents(List<Component> var1) {
      this.tooltipRenderer.method9(var1);
   }

   public void method56(ItemStackBridge var1) {
      this.tooltipRenderer.method10(var1);
   }

   String getCurrentGuiId() {
      if (this.guiId == null) {
         throw new IllegalStateException("Calling getCurrentGuiId when not in a render call");
      } else {
         return this.guiId;
      }
   }

   @Generated
   MixinHelper2 getWidgetStateCache() {
      return this.widgetStateCache;
   }

   @Generated
   MixinHelper211 getScissorStack() {
      return this.scissorStack;
   }

   @Generated
   MixinHelper29 getTooltipRenderer() {
      return this.tooltipRenderer;
   }

   @Generated
   MixinHelper27 getTextRenderer() {
      return this.textRenderer;
   }

   @Generated
   MixinHelper210 getRectRenderer() {
      return this.rectRenderer;
   }

   @Generated
   MixinHelper28 getTextFieldRenderer() {
      return this.textFieldRenderer;
   }

   @Generated
   MixinHelper25 getItemStackRenderer() {
      return this.itemStackRenderer;
   }

   @Generated
   MixinHelper23 getSlotRenderer() {
      return this.slotRenderer;
   }

   @Generated
   MixinHelper26 getBoxRenderer() {
      return this.boxRenderer;
   }

   @Generated
   MixinHelper24 getPanelRenderer() {
      return this.panelRenderer;
   }

   @Generated
   MixinHelper2_2 getMouseInput() {
      return this.mouseInput;
   }

   @Generated
   MixinHelper22 getKeyboardInput() {
      return this.keyboardInput;
   }

   @Generated
   MixinHelper22_2 getScrollAnimator() {
      return this.scrollAnimator;
   }

   @Generated
   public void setTheme(MixinHelperType var1) {
      this.theme = var1;
   }

   @Generated
   MixinHelperType getTheme() {
      return this.theme;
   }

   @Generated
   MixinHelper_4 getDrawContext() {
      return this.drawContext;
   }

   @Generated
   public boolean method73() {
      return this.containerScreen;
   }
}
