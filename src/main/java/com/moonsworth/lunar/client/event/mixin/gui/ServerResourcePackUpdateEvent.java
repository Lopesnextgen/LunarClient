package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.client.highlight.Highlight;
import java.io.File;
import java.util.List;
import lombok.Generated;

public class ServerResourcePackUpdateEvent extends Highlight {
   private final List<File> resourcePacks;

   @Generated
   public List<File> getResourcePacks() {
      return this.resourcePacks;
   }

   @Generated
   public ServerResourcePackUpdateEvent(List<File> list) {
      this.resourcePacks = list;
   }
}
