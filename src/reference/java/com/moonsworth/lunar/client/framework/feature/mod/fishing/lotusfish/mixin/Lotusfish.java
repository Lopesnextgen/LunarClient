package com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.mixin;

import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.ichor.util.KeepName;
import java.util.Set;
import org.jetbrains.annotations.Nullable;

@KeepName
public class Lotusfish {
   private final String id;
   private final String title;
   @Nullable
   private final Set<SkyblockIsland> exclusivelyOn;

   public Lotusfish(String text, String text2, @Nullable Set<SkyblockIsland> set3) {
      this.id = text;
      this.title = text2;
      this.exclusivelyOn = set3;
   }
}
