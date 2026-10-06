package com.moonsworth.lunar.client.mod.skyblock.hideexcoops;

import com.lunarclient.generated.skyblockprofileresponse.Profile;
import com.lunarclient.generated.skyblockprofileresponse.profile.Member;
import com.lunarclient.minecraft.MinecraftUsername;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SkyblockProfileEvents.SkyblockProfileLoadEvent;
import com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SkyblockHideExCoops extends AbstractFeature {
   private static final Pattern field8 = Pattern.compile("^(\\[.*] )?(?<name>\\w+): .*$");
   private final HighlightTypeListener field9 = (HighlightTypeListener)this.method63(HighlightTypeListener.class);
   private final com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockProfileCache field10 = (com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockProfileCache)this.method63(
      com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockProfileCache.class
   );
   private final Map<String, String> field11 = new HashMap<>();
   private final Set<String> field12 = Collections.newSetFromMap(new ConcurrentHashMap<>());

   public SkyblockHideExCoops(Skyblock skyblock1) {
      super(true);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method45(ModTraits.field17, ModCategories.method2(SettingsPage.INVENTORY));
      this.method45(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.method1(this::method13);
      this.method51(this::reset);
      this.handle(EventRenderTooltipPre.class, this::method1);
      this.handle(SkyblockProfileLoadEvent.class, arg1x -> this.method13());
   }

   private void method1(EventRenderTooltipPre data21) {
      SkyblockMenuType highlighttype2 = this.field9.method7();
      if (highlighttype2 != null && highlighttype2.isCollectionGui()) {
         if (!this.field12.isEmpty()) {
            ItemStackBridge bridgeextension_43 = (ItemStackBridge)data21.HHCOCHOIIIOOROCORRRRORORIOOHRC().orElse(null);
            if (bridgeextension_43 != null && !bridgeextension_43.bridge$isEmpty()) {
               List list4 = data21.method3();
               List list5 = SkyblockItemUtil.method15(bridgeextension_43);
               boolean flag6 = false;
               int index7 = 0;

               for (int index8 = 0; index8 < list5.size(); index8++) {
                  String text9 = (String)list5.get(index8);
                  if (!flag6) {
                     flag6 = text9.equals("Co-op Contributions:");
                  } else {
                     Matcher matcher10 = field8.matcher(text9);
                     if (!matcher10.matches()) {
                        return;
                     }

                     if (this.field12.contains(matcher10.group("name"))) {
                        list4.remove(index8 + 1 - index7++);
                     }
                  }
               }

               data21.method2(list4);
            }
         }
      }
   }

   private void method13() {
      BackgroundExecutor.method4(
         () -> {
            this.field12.clear();
            Profile profile1 = this.field10.method8();
            if (profile1 != null) {
               List list2 = profile1.members()
                  .entrySet()
                  .stream()
                  .filter(arg0 -> ((Member)arg0.getValue()).profile().deletionNotice().elm().exists())
                  .map(arg1x -> this.field11.getOrDefault(arg1x.getKey(), MinecraftUsername.getSync((String)arg1x.getKey())))
                  .toList();
               this.field12.addAll(list2);
            }
         }
      );
   }

   private void reset() {
      this.field12.clear();
      this.field11.clear();
   }

   public String getId() {
      return "SKYBLOCK_HIDE_EX_COOPS";
   }
}
