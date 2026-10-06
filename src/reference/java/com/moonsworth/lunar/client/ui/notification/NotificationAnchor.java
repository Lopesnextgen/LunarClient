package com.moonsworth.lunar.client.ui.notification;

import lombok.Generated;
import com.moonsworth.lunar.client.gui.notification.AnchorFunction;

public enum NotificationAnchor implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   TOP_LEFT("topLeft", (arg0, arg1, arg2, arg3) -> arg3, (arg0, arg1, arg2, arg3) -> arg3 + arg2),
   BOTTOM_LEFT("bottomLeft", (arg0, arg1, arg2, arg3) -> arg3, (arg0, arg1, arg2, arg3) -> arg0 - arg3 - arg2 - arg1),
   TOP_RIGHT("topRight", (arg0, arg1, arg2, arg3) -> arg0 - arg3 - arg1, (arg0, arg1, arg2, arg3) -> arg3 + arg2),
   BOTTOM_RIGHT("bottomRight", (arg0, arg1, arg2, arg3) -> arg0 - 5.0F - arg1, (arg0, arg1, arg2, arg3) -> arg0 - arg3 - arg2 - arg1);

   private final String id;
   private final AnchorFunction horizontalPosition;
   private final AnchorFunction verticalPosition;

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id(), new Object[0]);
   }

   @Generated
   NotificationAnchor(String text3, AnchorFunction chest4, AnchorFunction chest5) {
      this.id = text3;
      this.horizontalPosition = chest4;
      this.verticalPosition = chest5;
   }

   @Generated
   public AnchorFunction getHorizontalPosition() {
      return this.horizontalPosition;
   }

   @Generated
   public AnchorFunction getVerticalPosition() {
      return this.verticalPosition;
   }
}
