package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.KeyEventBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Locale;
import lombok.Generated;
import net.kyori.adventure.text.Component;

@VersionGate(min = 1)
public class GuiRenderer {
   public static final GuiRenderer field1 = new GuiRenderer();
   private final Deque<GuiRenderState> stateStack = new ArrayDeque<>();
   private final WidgetStateCache widgetStateCache = new WidgetStateCache(this);
   private final ScissorStack scissorStack = new ScissorStack(this);
   private final MixinHelper29 tooltipRenderer = new MixinHelper29(this);
   private final ScaledTextRenderer textRenderer = new ScaledTextRenderer(this);
   private final RectRenderer rectRenderer = new RectRenderer(this);
   private final TextFieldRenderer textFieldRenderer = new TextFieldRenderer(this);
   private final ItemStackRenderer itemStackRenderer = new ItemStackRenderer(this);
   private final SlotRenderer slotRenderer = new SlotRenderer(this);
   private final BoxRenderer boxRenderer = new BoxRenderer(this);
   private final PanelRenderer panelRenderer = new PanelRenderer(this);
   private final MouseInput mouseInput = new MouseInput(this);
   private final KeyboardInput keyboardInput = new KeyboardInput(this);
   private final ScrollAnimator scrollAnimator = new ScrollAnimator(this);
   private final List<GuiComponent> components = new ArrayList<>();
   private GuiTheme theme = GuiTheme.DEFAULT_LIGHT;
   private GuiScreenBridge screen = null;
   private String guiId;
   private MixinHelper_4 drawContext;
   private boolean containerScreen;

   public GuiRenderer() {
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

   public void method1(String text1, GuiScreenBridge bridge5extension62, MixinHelper_4 mixinhelper_43, int number4, int number5, boolean flag6) {
      if (this.screen != null) {
         throw new IllegalStateException("Called start twice before calling end!");
      }

      this.screen = bridge5extension62;
      this.guiId = text1;
      this.drawContext = mixinhelper_43;
      this.containerScreen = flag6;
      this.mouseInput.method1(number4, number5);
      this.stateStack.clear();
      this.stateStack.add(new GuiRenderState());

      for (GuiComponent mixinhelper2_38 : this.components) {
         mixinhelper2_38.start();
      }
   }

   public void end() {
      if (this.screen == null) {
         throw new IllegalStateException("Called end without calling start!");
      }

      for (GuiComponent mixinhelper2_32 : this.components) {
         mixinhelper2_32.end();
      }

      this.screen = null;
      this.guiId = null;
      this.drawContext = null;
      this.stateStack.clear();
   }

   GuiRenderState currentState() {
      return this.stateStack.peek();
   }

   public void method3() {
      this.getRectRenderer().method17();
      this.stateStack.push(new GuiRenderState(this.currentState()));
   }

   public void method4() {
      this.getRectRenderer().method17();
      this.stateStack.pop();
      this.scissorStack.update();
   }

   public void method5(String text1) {
      this.textFieldRenderer.method6(text1);
   }

   public void clearGui(String text1) {
      this.widgetStateCache.method1(text1);
   }

   public void removeWidget(String text1, String text2) {
      this.widgetStateCache.method2(text1, text2);
   }

   public boolean isTextFieldSelected(String text1, String text2) {
      return this.textFieldRenderer.method2(text1, text2);
   }

   public void selectTextField(String text1, String text2) {
      this.textFieldRenderer.method3(text1, text2);
   }

   public void method10(String text1, String text2, String text3) {
      this.textFieldRenderer.method4(text1, text2, text3);
   }

   public void method11(boolean flag1) {
      this.currentState().field1 = flag1;
   }

   public void method12(boolean flag1) {
      this.currentState().field7 = flag1;
   }

   public void method13(boolean flag1) {
      this.currentState().field8 = flag1;
   }

   public void setSearchText(String text1) {
      if (text1 == null) {
         this.currentState().field5 = null;
      } else {
         this.currentState().field5 = text1.toLowerCase(Locale.ROOT);
      }
   }

   public void setSearchTooltips(boolean flag1) {
      this.currentState().field6 = flag1;
   }

   public void method16(boolean flag1) {
      this.currentState().field2 = flag1;
   }

   public void setHighlightHoveredSlots(boolean flag1) {
      this.currentState().field3 = flag1;
   }

   public void method18(int number1, int number2, int number3, int number4) {
      this.scissorStack.method1(number1, number2, number3, number4);
   }

   public void setScissor(int number1, int number2, int number3, int number4) {
      this.scissorStack.method2(number1, number2, number3, number4);
   }

   public void method20(double value1, double value3, double value5) {
      this.currentState().field9 *= value1;
      this.currentState().field10 *= value3;
      this.currentState().field11 *= value5;
   }

   public int drawItemStack(ItemStackBridge bridgeextension_41, int number2, int number3) {
      return this.itemStackRenderer.drawItemStack(bridgeextension_41, number2, number3, false, false);
   }

   public int method22(ItemStackBridge bridgeextension_41, int number2, int number3, boolean flag4) {
      return this.itemStackRenderer.drawItemStack(bridgeextension_41, number2, number3, flag4, false);
   }

   public void method23(String text1, int number2, int number3, boolean flag4, float value5) {
      this.textRenderer.method1(text1, number2, number3, flag4, value5);
   }

   public void drawCenteredText(String text1, int number2, int number3, boolean flag4) {
      this.textRenderer.method1(text1, number2, number3, flag4, 1.0F);
   }

   public void method25(String text1, int number2, int number3, boolean flag4, float value5) {
      this.textRenderer.method2(text1, number2, number3, flag4, value5);
   }

   public void method26(String text1, int number2, int number3, boolean flag4) {
      this.textRenderer.method2(text1, number2, number3, flag4, 1.0F);
   }

   public int getStringWidth(String text1) {
      return this.textRenderer.getStringWidth(text1);
   }

   public boolean method27(String text1, int number2, int number3, int number4) {
      return this.mouseInput.method2(text1, number2, number3, number4);
   }

   public boolean method28(String text1, int number2, int number3, int number4) {
      return this.mouseInput.method3(text1, number2, number3, number4);
   }

   public boolean method29(String text1, KeyEventBridge bridge_72) {
      return this.keyboardInput.addKeyEvent(text1, bridge_72);
   }

   public int drawSlotList(int number1, int number2, List<ItemStackBridge> list3) {
      return this.drawSlotGrid(null, number1, number2, list3);
   }

   public int drawSlotGrid(String text1, int number2, int number3, List<ItemStackBridge> list4) {
      return this.slotRenderer.method1(text1, number2, number3, list4);
   }

   public int drawSlotGridFlagged(String text1, int number2, int number3, List<ItemStackBridge> list4, boolean flag5) {
      return this.slotRenderer.method2(text1, number2, number3, list4, flag5);
   }

   public void method33(String text1, int number2, int number3, List<SlotBridge> list4, boolean flag5, boolean flag6) {
      this.slotRenderer.method3(text1, number2, number3, list4, flag5, flag6);
   }

   public int drawPanelNoTitle(int number1, int number2, int number3, int number4) {
      return this.panelRenderer.method1(null, number1, number2, number3, number4);
   }

   public int method35(String text1, int number2, int number3, int number4, int number5) {
      return this.panelRenderer.method1(text1, number2, number3, number4, number5);
   }

   public int drawPanelLeftAligned(String text1, int number2, int number3, int number4, int number5, int number6) {
      return this.panelRenderer.method2(text1, number2, number3, number4, number5, number6);
   }

   public void method37(int number1, int number2, int number3, int number4) {
      this.boxRenderer.method1(number1, number2, number3, number4);
   }

   public void method38(int number1, int number2, int number3, int number4) {
      this.boxRenderer.drawInsetBox(number1, number2, number3, number4);
   }

   public void drawSeparator(int number1, int number2, int number3) {
      this.boxRenderer.drawSeparator(number1, number2, number3);
   }

   public MouseClick method40(int number1, int number2, int number3, int number4) {
      return this.mouseInput.method4(number1, number2, number3, number4);
   }

   public int method41(int number1, int number2, int number3, int number4) {
      MouseClick mixinhelper_25 = this.method40(number1, number2, number3, number4);
      return mixinhelper_25 == null ? -1 : mixinhelper_25.method2();
   }

   public boolean method42(int number1, int number2, int number3, int number4) {
      return this.mouseInput.method5(number1, number2, number3, number4);
   }

   public void method43(int number1, int number2, int number3, int number4, int number5) {
      this.rectRenderer.method1(number1, number2, number3, number4, number5);
   }

   public GuiScreenBridge method44() {
      return this.screen;
   }

   public String method45(String text1, int number2, int number3, int number4, int number5, String text6, boolean flag7) {
      return this.textFieldRenderer.method7(text1, number2, number3, number4, number5, text6, flag7);
   }

   public void translate(double value1, double value3) {
      this.rectRenderer.method17();
      this.drawContext.method39((float)value1, (float)value3);
   }

   public void translateScaled(float value1, float value2) {
      this.rectRenderer.method17();
      this.drawContext.method40(value1, value2);
   }

   public void beginWidget(String text1, int number2, int number3, int number4, int number5) {
      this.widgetStateCache.method5(text1, number2, number3, number4, number5);
   }

   public void endWidget(String text1) {
      this.widgetStateCache.method6(text1);
   }

   public void method49(String text1, String text2) {
      this.widgetStateCache.method7(text1, text2);
   }

   public void invalidateWidget(String text1) {
      this.widgetStateCache.method8(text1);
   }

   public float method51(String text1, int number2, int number3) {
      return this.textRenderer.method3(text1, number2, number3);
   }

   public void method52(String text1) {
      this.method5(text1);
   }

   public void method53() {
      this.itemStackRenderer.drawCursorStack();
   }

   public void showTooltip(String text1) {
      this.tooltipRenderer.method9(List.of(Component.text(text1)));
   }

   public void showTooltipComponents(List<Component> list1) {
      this.tooltipRenderer.method9(list1);
   }

   public void method56(ItemStackBridge bridgeextension_41) {
      this.tooltipRenderer.method10(bridgeextension_41);
   }

   String getCurrentGuiId() {
      if (this.guiId == null) {
         throw new IllegalStateException("Calling getCurrentGuiId when not in a render call");
      } else {
         return this.guiId;
      }
   }

   @Generated
   WidgetStateCache getWidgetStateCache() {
      return this.widgetStateCache;
   }

   @Generated
   ScissorStack getScissorStack() {
      return this.scissorStack;
   }

   @Generated
   MixinHelper29 getTooltipRenderer() {
      return this.tooltipRenderer;
   }

   @Generated
   ScaledTextRenderer getTextRenderer() {
      return this.textRenderer;
   }

   @Generated
   RectRenderer getRectRenderer() {
      return this.rectRenderer;
   }

   @Generated
   TextFieldRenderer getTextFieldRenderer() {
      return this.textFieldRenderer;
   }

   @Generated
   ItemStackRenderer getItemStackRenderer() {
      return this.itemStackRenderer;
   }

   @Generated
   SlotRenderer getSlotRenderer() {
      return this.slotRenderer;
   }

   @Generated
   BoxRenderer getBoxRenderer() {
      return this.boxRenderer;
   }

   @Generated
   PanelRenderer getPanelRenderer() {
      return this.panelRenderer;
   }

   @Generated
   MouseInput getMouseInput() {
      return this.mouseInput;
   }

   @Generated
   KeyboardInput getKeyboardInput() {
      return this.keyboardInput;
   }

   @Generated
   ScrollAnimator getScrollAnimator() {
      return this.scrollAnimator;
   }

   @Generated
   public void setTheme(GuiTheme mixinhelpertype1) {
      this.theme = mixinhelpertype1;
   }

   @Generated
   GuiTheme getTheme() {
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
