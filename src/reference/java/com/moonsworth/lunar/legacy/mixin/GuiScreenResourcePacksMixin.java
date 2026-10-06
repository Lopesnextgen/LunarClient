package com.moonsworth.lunar.legacy.mixin;

import com.google.common.collect.Lists;
import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.moonsworth.lunar.bridge.Bridge5Extension10;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourcePackListBridge;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.gui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.ui.widget.HoverInfoOptionWidget;
import com.moonsworth.lunar.client.config.GeneralSettings;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.resourcepack.ResourcePackUpdateEvent;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump56;
import com.moonsworth.lunar.client.util.ThreadModuleDump61;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump76;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.legacy.wrapper.ResourcePackListEntryImpl;
import com.moonsworth.lunar.legacy.wrapper.ResourcePackListEntryIterator;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiResourcePackAvailable;
import net.minecraft.client.gui.GuiResourcePackSelected;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.ResourcePackListEntry;
import net.minecraft.client.resources.ResourcePackListEntryDefault;
import net.minecraft.client.resources.ResourcePackListEntryFound;
import net.minecraft.client.resources.ResourcePackListEntryServer;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.resources.ResourcePackRepository.Entry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiScreenResourcePacks.class)
public abstract class GuiScreenResourcePacksMixin extends GuiScreen implements Bridge5Extension10 {
   @Unique
   private final ResourceLocationBridge lunar$rpggIcon = ResourceLocationBridge.create("lunar", "logo/logo-rpgg.png");
   @Shadow
   public List<ResourcePackListEntry> availableResourcePacks;
   public List<ResourcePackListEntry> baseAvailableResourcePacks;
   @Shadow
   public List<ResourcePackListEntry> selectedResourcePacks;
   @Shadow
   public GuiResourcePackAvailable availableResourcePacksList;
   @Shadow
   public GuiResourcePackSelected selectedResourcePacksList;
   @Shadow
   public boolean changed;
   @Shadow
   public List field_146966_g$v1_7;
   @Shadow
   public List field_146969_h$v1_7;
   private GuiTextField searchField;
   @Annotation2(min = 1)
   private OptionWidget<?> showIncompatible;
   @Annotation2(max = 0)
   private GuiOptionButton doneButton;
   @Annotation2(max = 0)
   private boolean loaded;
   private final List<OptionWidget<?>> components = new ArrayList<>();
   private final Map<File, ResourcePackListEntryIterator> resourcePackFolderEntries = new HashMap<>();
   private boolean discoveredResourcePacks;

   @Shadow
   public abstract void markChanged();

   @Overwrite
   public void initGui() {
      List var1 = ThreadModuleDump63.MC_VERSION >= 1 ? this.buttonList : this.buttonList$v1_7;
      var1.add(new GuiOptionButton(2, this.width / 2 - 154, this.height - 48, I18n.format("resourcePack.openFolder", new Object[0])));
      if (ThreadModuleDump63.MC_VERSION <= 0) {
         var1.add(this.doneButton = new GuiOptionButton(1, this.width / 2 + 4, this.height - 48, I18n.format("gui.done", new Object[0])));
      } else {
         var1.add(new GuiOptionButton(1, this.width / 2 + 4, this.height - 48, I18n.format("gui.done", new Object[0])));
      }

      this.components.clear();
      GeneralSettings var2 = Client.method109().method41().method6();
      OverlayMod var3 = ThreadModuleDump63.method4().method40().method84();
      this.components.add(new HoverInfoOptionWidget(var3.getClearGlassOption(), null, var1x -> {
         if (var1x.get() && !var3.isEnabled()) {
            var3.method3(Framework.field6).ifPresent(var0x -> var0x.setEnabled(true));
         }
      }));
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.components.add(this.showIncompatible = Client.method109().method40().method57().method13().method25(null));
      }

      this.components.add(new HoverInfoOptionWidget(var3.getColoredStringOption(), null, var1x -> {
         if (var1x.get() && !var3.isEnabled()) {
            var3.method3(Framework.field6).ifPresent(var0x -> var0x.setEnabled(true));
         }
      }));
      this.components.add(var2.method28().method25(null));
      this.components.add(var2.method29().method25(null));
      if (ThreadModuleDump63.MC_VERSION <= 0) {
         this.doneButton.field_178665_b = false;
      }

      if (ThreadModuleDump63.MC_VERSION <= 0 || !this.changed) {
         if (ThreadModuleDump63.MC_VERSION <= 0) {
            this.field_146966_g$v1_7 = this.baseAvailableResourcePacks = Lists.newArrayList();
            this.field_146969_h$v1_7 = Lists.newArrayList();
         } else {
            this.availableResourcePacks = this.baseAvailableResourcePacks = Lists.newArrayList();
            this.selectedResourcePacks = Lists.newArrayList();
         }

         this.discoverResourcePacks();
      }

      if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.availableResourcePacksList = GuiResourcePackAvailable.newInstance(this.mc, 200, this.height - 20, this.availableResourcePacks);
         this.selectedResourcePacksList = GuiResourcePackSelected.newInstance(this.mc, 200, this.height - 76, this.selectedResourcePacks);
      } else {
         this.availableResourcePacksList = GuiResourcePackAvailable.newInstance(this.mc, 200, this.height - 20, this.field_146966_g$v1_7);
         this.selectedResourcePacksList = GuiResourcePackSelected.newInstance(this.mc, 200, this.height - 56, this.field_146969_h$v1_7);
      }

      this.availableResourcePacksList.setSlotXBoundsFromLeft(this.width / 2 - 4 - 200);
      this.availableResourcePacksList.registerScrollButtons(7, 8);
      this.selectedResourcePacksList.setSlotXBoundsFromLeft(this.width / 2 + 4);
      this.selectedResourcePacksList.registerScrollButtons(7, 8);
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.searchField = new GuiTextField(103, this.fontRenderer, this.width / 2 - 204, this.height - 70, 200, 18);
      } else {
         this.searchField = new GuiTextField(this.fontRenderer, this.width / 2 - 204, this.height - 70, 200, 18);
      }

      int var4 = this.width / 2 + 9;
      int var5 = this.height - (ThreadModuleDump63.MC_VERSION >= 1 ? 125 : 107);

      for (OptionWidget var7 : this.components) {
         var7.method1(var4, var5, 190.0F);
         var5 = (int)(var5 + var7.getHeight());
      }
   }

   private void discoverResourcePacks() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.availableResourcePacks.clear();
         this.selectedResourcePacks.clear();
      } else {
         this.field_146969_h$v1_7.clear();
         this.field_146966_g$v1_7.clear();
      }

      new Thread(
            () -> {
               this.discoveredResourcePacks = false;
               File var1 = this.mc.fileResourcepacks;
               if (Client.method109().method40().method57().isEnabled() && var1.exists() && var1.isDirectory()) {
                  String[] var2 = var1.list(ThreadModuleDump76.getDirectoryFilter());

                  for (String var6 : var2) {
                     File var7 = new File(var1, var6);
                     if (ThreadModuleDump63.MC_VERSION >= 1) {
                        this.availableResourcePacks
                           .add(
                              new ResourcePackListEntryIterator(
                                 (GuiScreenResourcePacks)this, var7, var1, null, this.availableResourcePacks, this.resourcePackFolderEntries
                              )
                           );
                     } else {
                        this.field_146966_g$v1_7
                           .add(
                              new ResourcePackListEntryIterator(
                                 (GuiScreenResourcePacks)this, var7, var1, null, this.field_146966_g$v1_7, this.resourcePackFolderEntries
                              )
                           );
                     }
                  }
               }

               ResourcePackRepository var8 = this.mc.getResourcePackRepository();
               var8.updateRepositoryEntriesAll();
               ArrayList var9;
               if (ThreadModuleDump63.MC_VERSION >= 1) {
                  var9 = Lists.newArrayList(var8.getRepositoryEntriesAll());
                  var9.removeAll(var8.getRepositoryEntries());
               } else {
                  var9 = Lists.newArrayList(var8.getRepositoryEntriesAll());
                  var9.removeAll(var8.getRepositoryEntries());
               }

               if (ThreadModuleDump63.MC_VERSION >= 1) {
                  int var10 = ThreadModuleDump63.MC_VERSION >= 5 ? 3 : 1;

                  for (Entry var14 : var9) {
                     int var15 = ThreadModuleDump63.MC_VERSION >= 5 ? var14.getPackFormat$v1_12() : var14.func_183027_f();
                     if (var15 == var10 || Client.method109().method40().method57().method13().get()) {
                        this.availableResourcePacks.add(new ResourcePackListEntryFound((GuiScreenResourcePacks)this, var14));
                     }
                  }
               } else {
                  for (Entry var13 : var9) {
                     this.field_146966_g$v1_7.add(new ResourcePackListEntryFound((GuiScreenResourcePacks)this, var13));
                  }
               }

               if (ThreadModuleDump63.MC_VERSION <= 0) {
                  this.doneButton.field_178665_b = true;
                  this.loaded = true;
               }

               if (ThreadModuleDump63.MC_VERSION >= 1) {
                  this.mc.addScheduledTask(() -> {
                     try {
                        if (ThreadModuleDump63.MC_VERSION >= 5) {
                           Entry var2x = var8.getResourcePackEntry$v1_12();
                           if (var2x != null) {
                              this.selectedResourcePacks.add(new ResourcePackListEntryServer((GuiScreenResourcePacks)this, var8.getServerResourcePack$v1_12()));
                           }
                        }

                        for (Entry var3 : Lists.reverse(var8.getRepositoryEntries())) {
                           this.selectedResourcePacks.add(new ResourcePackListEntryFound((GuiScreenResourcePacks)this, var3));
                        }

                        this.selectedResourcePacks.add(new ResourcePackListEntryDefault((GuiScreenResourcePacks)this));
                     } catch (Exception var4) {
                        var4.printStackTrace();
                     }
                  });
               } else {
                  this.mc.addScheduledTask(() -> {
                     try {
                        for (Object var3 : Lists.reverse(var8.getRepositoryEntries())) {
                           this.field_146969_h$v1_7.add(new ResourcePackListEntryFound((GuiScreenResourcePacks)this, (Entry)var3));
                        }

                        this.field_146969_h$v1_7.add(new ResourcePackListEntryDefault((GuiScreenResourcePacks)this));
                     } catch (Exception var4) {
                        var4.printStackTrace();
                     }
                  });
               }

               this.discoveredResourcePacks = true;
            }
         )
         .start();
   }

   public void keyTyped(char var1, int var2) {
      super.keyTyped(var1, var2);
      if (ThreadModuleDump63.MC_VERSION > 0 || this.loaded) {
         this.searchField.textboxKeyTyped(var1, var2);
         this.lunar$refreshAvailableList();
      }
   }

   @Unique
   private void lunar$refreshAvailableList() {
      if (!this.searchField.getText().isEmpty()) {
         LinkedHashMap var1 = new LinkedHashMap();

         for (Object var3 : ThreadModuleDump63.MC_VERSION >= 1 ? this.availableResourcePacks : this.field_146966_g$v1_7) {
            ResourcePackListEntry var4 = (ResourcePackListEntry)var3;
            String var5 = ThreadModuleDump63.MC_VERSION >= 5 ? var4.getResourcePackName() : var4.func_148312_b();
            if (var4 instanceof ResourcePackListEntryImpl) {
               var1.put(var5, var4);
            } else if (!var1.containsKey(var5)) {
               String var6 = AdventureChatFormatting.getTextWithoutFormattingCodes(var5);
               String var7 = this.searchField.getText();
               var7 = ThreadModuleDump56.method4(var7);
               String[] var8 = var6.split(" ");

               for (String var12 : var8) {
                  var12 = ThreadModuleDump56.method4(var12);
                  if (var12.startsWith(var7)) {
                     var1.put(var5, (ResourcePackListEntry)var3);
                  }
               }
            }
         }

         ((ResourcePackListBridge)this.availableResourcePacksList).setUnderlyingList(new ArrayList<>(var1.values()));
      } else {
         List var13 = ThreadModuleDump63.MC_VERSION >= 1 ? this.availableResourcePacks : this.field_146966_g$v1_7;
         ((ResourcePackListBridge)this.availableResourcePacksList).setUnderlyingList(var13);
      }
   }

   public void updateScreen() {
      this.searchField.updateCursorCounter();
   }

   @Inject(method = "mouseClicked", at = @At("RETURN"))
   private void lunar$mouseClicked(int var1, int var2, int var3, CallbackInfo var4) {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         this.searchField.mouseClicked(var1, var2, var3);
      } else {
         this.searchField.mouseClicked(var1, var2, var3);
      }

      MarkerModel.Data2 var5 = new MarkerModel.Data2(var1, var2);

      for (OptionWidget var7 : this.components) {
         if (var7.method5(var5)
            && var7.IHRHHRIHICHOOICIRIOOHOICHIRHOI(var5, var3)
            && ThreadModuleDump63.MC_VERSION >= 1
            && var7 == this.showIncompatible) {
            this.discoverResourcePacks();
         }
      }

      float var9 = this.width / 2.0F - 200.0F;
      float var10 = this.height - 42;
      boolean var8 = var1 >= var9 && var1 <= var9 + 30.0F && var2 >= var10 && var2 <= var10 + 20.0F;
      if (var3 == 0 && var8) {
         LcuiScreen.method15();
         ThreadModuleDump63.method4().method69().method7(NotificationType.INFO, NotificationManager.method15("urlOpened"));
         ThreadModuleDump61.method7(ThreadModuleDump48.field21, Initiator.INITIATOR_UNSPECIFIED);
      }
   }

   @Inject(method = "drawScreen", at = @At("RETURN"))
   private void lunar$drawScreen(int var1, int var2, float var3, CallbackInfo var4) {
      List var5 = ThreadModuleDump63.MC_VERSION >= 1 ? this.availableResourcePacksList.field_148204_l : this.availableResourcePacksList.field_148204_l$v1_7;
      if (var5.isEmpty()) {
         String var6;
         if (!this.discoveredResourcePacks) {
            var6 = "Discovering resource packs...";
         } else if (this.searchField.getText().isEmpty()) {
            var6 = "No resource packs found";
         } else {
            var6 = "";
         }

         this.drawCenteredString(this.fontRenderer, "" + AdventureChatFormatting.GRAY + AdventureChatFormatting.ITALIC + var6, this.width / 2 - 100, 60, 16777215);
      }

      this.searchField.drawTextBox();
      if (this.searchField.getText().isEmpty() && !this.searchField.isFocused()) {
         this.drawString(this.fontRenderer, "" + AdventureChatFormatting.GRAY + AdventureChatFormatting.ITALIC + "Search...", this.width / 2 - 200, this.height - 65, 16777215);
      }

      BridgeExtension3_5 var12 = AbstractRenderContext.method32();
      int var7 = this.width / 2 + 4;
      if (ThreadModuleDump63.method8() == null || !Client.method109().method41().method6().method28().get()) {
         LcuiScreen.method97(
            var12,
            var7,
            this.height - (ThreadModuleDump63.MC_VERSION >= 1 ? 127.0F : 107.0F),
            200.0F,
            ThreadModuleDump63.MC_VERSION >= 1 ? 76.0F : 56.0F,
            -1879048192
         );
      }

      MarkerModel.Data2 var8 = new MarkerModel.Data2(var1, var2);
      MixinHelper_4 var9 = var12.method42();

      for (OptionWidget var11 : this.components) {
         var11.method5(var9, var8, var11.method5(var8));
      }

      this.lunar$drawResourcePacksGGbutton(var12, var1, var2);
   }

   @Redirect(method = "drawScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreenResourcePacks;drawBackground(I)V"))
   private void lunar$drawBackground(GuiScreenResourcePacks var1, int var2) {
      if (this.mc.theWorld != null && ThreadModuleDump63.method4().method41().method6().method28().get()) {
         var1.drawDefaultBackground();
      } else {
         var1.drawBackground(var2);
      }
   }

   @Inject(method = "actionPerformed", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;refreshResources()V", shift = Shift.AFTER))
   private void lunar$onSwapPack(CallbackInfo var1) {
      ClientEventBus.method29().method12(ResourcePackUpdateEvent.class, () -> new ResourcePackUpdateEvent(ThreadModuleDump63.method3().bridge$getSelectedResourcePack()));
   }

   @Override
   public void bridge$handlePackSwapList() {
      if (Client.method109().method40().method57().isEnabled()) {
         List var1 = ThreadModuleDump63.MC_VERSION >= 1 ? this.availableResourcePacks : this.field_146966_g$v1_7;

         for (ResourcePackListEntry var3 : List.copyOf(var1)) {
            List var4 = null;
            if (var3 instanceof ResourcePackListEntryFound var5) {
               IResourcePack var6 = ThreadModuleDump63.MC_VERSION >= 5 ? var5.resourcePackEntry$v1_12.reResourcePack : var5.field_148319_c.reResourcePack;
               if (!(var6 instanceof AbstractResourcePack)) {
                  continue;
               }

               ResourcePackListEntryIterator var7 = this.resourcePackFolderEntries.get(((AbstractResourcePack)var6).resourcePackFile.getParentFile());
               if (var7 == null) {
                  var4 = this.baseAvailableResourcePacks;
               } else {
                  var4 = var7.method4();
               }
            }

            if (var4 != null && var4 != var1) {
               var1.remove(var3);
               var4.add(var3);
            }
         }

         for (ResourcePackListEntryIterator var9 : this.resourcePackFolderEntries.values()) {
            var9.method1();
         }
      }

      if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.markChanged();
      }
   }

   @Unique
   private void lunar$drawResourcePacksGGbutton(AbstractRenderContext var1, int var2, int var3) {
      float var4 = this.width / 2.0F - 200.0F;
      float var5 = this.height - 42;
      boolean var6 = var2 >= var4 && var2 <= var4 + 30.0F && var3 >= var5 && var3 <= var5 + 20.0F;
      LcuiScreen.method30(var1, this.lunar$rpggIcon, var4, var5, 30.0F, 20.0F, var6 ? -1 : -1426063361);
   }
}
