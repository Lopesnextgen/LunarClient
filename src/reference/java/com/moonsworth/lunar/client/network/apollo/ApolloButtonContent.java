package com.moonsworth.lunar.client.network.apollo;

import com.lunarclient.apollo.common.icon.Icon;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Ref;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

final class ApolloButtonContent {
   @Nullable
   private final Component field1;
   @Nullable
   private final ItemStackBridge field2;
   @Nullable
   private final ResourceLocationBridge field3;
   @Nullable
   private final Icon field4;
   private final float field5;
   private final float field6;
   private float field7 = -1.0F;

   private ApolloButtonContent(
      @Nullable Component component1, @Nullable ItemStackBridge bridgeextension_42, @Nullable ResourceLocationBridge horsestats143, @Nullable Icon icon4, float value, float value2
   ) {
      this.field1 = component1;
      this.field2 = bridgeextension_42;
      this.field3 = horsestats143;
      this.field4 = icon4;
      this.field5 = value;
      this.field6 = value2;
   }

   private static ApolloButtonContent method1(Component component0) {
      return new ApolloButtonContent(component0, null, null, null, 0.0F, 0.0F);
   }

   private static ApolloButtonContent method2(ItemStackBridge bridgeextension_40) {
      return new ApolloButtonContent(null, bridgeextension_40, null, null, 16.0F, 16.0F);
   }

   private static ApolloButtonContent method3(ResourceLocationBridge horsestats140, Icon icon1, float value, float value2) {
      return new ApolloButtonContent(null, null, horsestats140, icon1, value, value2);
   }

   private float method4() {
      if (this.field7 < 0.0F) {
         this.field7 = this.field1 != null ? Ref.method10().bridge$getStringWidth(this.field1) : this.field5;
      }

      return this.field7;
   }
}
