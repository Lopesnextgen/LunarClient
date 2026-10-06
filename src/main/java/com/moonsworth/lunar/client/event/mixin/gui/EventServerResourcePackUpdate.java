package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.client.event.LunarEvent;
import java.io.File;
import java.util.List;
import lombok.Generated;

public class EventServerResourcePackUpdate extends LunarEvent {
   private final List<File> resourcePacks;

   @Generated
   public List<File> getResourcePacks() {
      return this.resourcePacks;
   }

   @Generated
   public EventServerResourcePackUpdate(List<File> list) {
      this.resourcePacks = list;
   }
}
