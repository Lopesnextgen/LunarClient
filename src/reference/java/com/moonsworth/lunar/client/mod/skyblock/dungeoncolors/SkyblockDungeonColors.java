package com.moonsworth.lunar.client.mod.skyblock.dungeoncolors;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.BlocksBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.mod.skyblock.debug.SkyblockDebugMod;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.function.Consumer;

public class SkyblockDungeonColors extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "highlightDungeonCrackedStoneBricks"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("highlightDungeonChests")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "highlightDungeonTrappedChests"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("highlightDungeonDispenser")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field12 = (ColorOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "dungeonCrackedStoneBricksColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16711681))
      .method15()
      .method31();
   private final ColorOption field13 = (ColorOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8("dungeonChestColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16711936))
      .method15()
      .method31();
   private final ColorOption field14 = (ColorOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8("dungeonTrappedChestColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-59392))
      .method15()
      .method31();
   private final ColorOption field15 = (ColorOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8("dungeonDispenserColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-59392))
      .method15()
      .method31();

   public SkyblockDungeonColors(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
   }

   public boolean method13() {
      return this.method14() || this.isEnabled();
   }

   public int method2(Bridge3_23 bridge3_231) {
      if (IslandUtils.getIsland() != SkyblockIsland.DUNGEON && !this.method14()) {
         return -1;
      }

      if (bridge3_231 == null) {
         return -1;
      }

      BlocksBridge bridge_562 = Bridge.method34();
      int number3 = -1;
      if ((Boolean)this.field8.get() && bridge3_231 == bridge_562.method50()) {
         number3 = this.field12.method14(0.0F);
      }

      if ((Boolean)this.field9.get() && bridge3_231 == bridge_562.method23()) {
         number3 = this.field13.method14(0.0F);
      } else if ((Boolean)this.field10.get() && bridge3_231 == bridge_562.method24()) {
         number3 = this.field14.method14(0.0F);
      } else if ((Boolean)this.field11.get() && bridge3_231 == bridge_562.method30()) {
         number3 = this.field15.method14(0.0F);
      }

      return 0xFF000000 | number3;
   }

   private boolean method14() {
      SkyblockDebugMod skyblockdebugmod1 = Ref.method4().method40().method77();
      return skyblockdebugmod1 == null ? false : skyblockdebugmod1.method14().method13();
   }

   public void method3(boolean flag1) {
      if (IslandUtils.getIsland() == SkyblockIsland.DUNGEON) {
         Ref.method3().bridge$getLevelRenderer().bridge$reloadChunks();
      }
   }

   public String getId() {
      return "SKYBLOCK_DUNGEON_COLORS";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> {
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field8, arg1xx -> arg1xx.method9(new ClientOption[]{this.field12}));
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field9, arg1xx -> arg1xx.method9(new ClientOption[]{this.field13}));
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field10, arg1xx -> arg1xx.method9(new ClientOption[]{this.field14}));
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field11, arg1xx -> arg1xx.method9(new ClientOption[]{this.field15}));
      });
      Consumer consumer2 = arg0 -> {
         if (IslandUtils.getIsland() == SkyblockIsland.DUNGEON) {
            Ref.method3().bridge$getLevelRenderer().bridge$reloadChunks();
         }
      };
      this.field8.CICORRHIOIIOORRRICCORIOIOCIHII(consumer2);
      this.field12.CICORRHIOIIOORRRICCORIOIOCIHII(consumer2);
      this.field9.CICORRHIOIIOORRRICCORIOIOCIHII(consumer2);
      this.field13.CICORRHIOIIOORRRICCORIOIOCIHII(consumer2);
      this.field10.CICORRHIOIIOORRRICCORIOIOCIHII(consumer2);
      this.field14.CICORRHIOIIOORRRICCORIOIOCIHII(consumer2);
      this.field11.CICORRHIOIIOORRRICCORIOIOCIHII(consumer2);
      this.field15.CICORRHIOIIOORRRICCORIOIOCIHII(consumer2);
   }
}
