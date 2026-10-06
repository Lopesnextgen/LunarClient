package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.List;
import java.util.function.Consumer;
import net.kyori.adventure.text.Component;
import org.jspecify.annotations.Nullable;

public interface Bridge8_2 {
   @VersionGate(min = 17)
   int bridge$guiWidth();

   @VersionGate(min = 17)
   int bridge$guiHeight();

   @VersionGate(min = 26)
   void bridge$blitGuiSprite$v1_21_2(
      ResourceLocationBridge horsestats141, int number2, int number3, int number4, int number5, int number6, int number7, int number8, int number9, float value10, float value11, float value12, float value13
   );

   @VersionGate(min = 17)
   void bridge$blit$v1_20_0(ResourceLocationBridge horsestats141, int number2, int number3, int number4, int number5, int number6, int number7, int number8, int number9);

   @VersionGate(min = 17)
   void bridge$fill$v1_20_0(int number1, int number2, int number3, int number4, int number5);

   @VersionGate(min = 17)
   void bridge$drawString$v1_20_0(Bridge10_2 bridge10_21, Bridge2_42 bridge2_422, int number3, int number4, int number5, boolean flag6);

   @VersionGate(min = 17)
   default void method1(Bridge10_2 bridge10_21, Component component2, int number3, int number4, int number5, boolean flag6) {
      this.bridge$drawString$v1_20_0(bridge10_21, TextBridge.asBridge(component2), number3, number4, number5, flag6);
   }

   @VersionGate(min = 17)
   void bridge$drawStringNoBidi$v1_20_0(Bridge10_2 bridge10_21, String text2, int number3, int number4, int number5, boolean flag6);

   @VersionGate(min = 30)
   void bridge$blit$v1_21_6(RenderPipelineBridge bridge_451, ResourceLocationBridge horsestats142, int number3, int number4, float value5, float value6, int number7, int number8, int number9, int number10, int number11);

   @VersionGate(min = 17)
   Bridge5_16 bridge$getPoseStack();

   @VersionGate(min = 17)
   BatchMultiBufferSourceBridge bridge$getBufferSource();

   @VersionGate(min = 17)
   void bridge$fillGradient$v1_20_0(int number1, int number2, int number3, int number4, int number5, int number6);

   @VersionGate(min = 30)
   void bridge$submitGraph$v1_21_6(int number1, int number2, int[] items3, int number4, int number5, int number6);

   @VersionGate(min = 17)
   void bridge$drawItem$1_20_0(ItemStackBridge bridgeextension_41, int number2, int number3);

   @VersionGate(min = 24)
   void bridge$drawItemDecorations$v1_20_0(ItemStackBridge bridgeextension_41, int number2, int number3);

   @VersionGate(min = 30)
   void bridge$drawItemSharp$v1_21_6(ItemStackBridge bridgeextension_41, int number2, int number3);

   @VersionGate(min = 30)
   void bridge$setTooltipForNextFrame$1_21_6(Bridge10_2 bridge10_21, ItemStackBridge bridgeextension_42, int number3, int number4);

   @VersionGate(min = 30)
   void bridge$setTooltipForNextFrame$1_21_6(Bridge10_2 bridge10_21, List<Component> list2, int number3, int number4);

   @VersionGate(min = 30)
   void bridge$renderDeferredElements$1_21_6(int number1, int number2);

   @VersionGate(min = 17)
   void bridge$scissor$1_20_0(int number1, int number2, int number3, int number4);

   @VersionGate(min = 17)
   void bridge$stopScissor$1_20_0();

   @VersionGate(min = 30)
   int @Nullable [] bridge$peekScissor$v1_21_6();

   @VersionGate(min = 30)
   void bridge$scissorAbsolute$v1_21_6(int number1, int number2, int number3, int number4);

   @VersionGate(min = 30)
   void bridge$submitDynamic$v1_21_6(RenderPipelineBridge bridge_451, ResourceLocationBridge horsestats142, double value3, double value5, double value7, double value9, Consumer<DrawBufferBridge> consumer11);

   @VersionGate(min = 30)
   void bridge$submitEntity$v1_21_6(EntityLivingBridge bridgeextension2_51, int number2, int number3, int number4, int number5, int number6, float value7, float value8, float value9);

   @VersionGate(min = 30)
   void bridge$submitPictureInPicture$v1_21_6(Object obj1, int number2, int number3, int number4, int number5, Consumer<AbstractRenderContext> consumer6);
}
