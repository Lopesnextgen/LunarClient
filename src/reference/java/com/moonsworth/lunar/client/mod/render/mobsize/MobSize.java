package com.moonsworth.lunar.client.mod.render.mobsize;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityLivingStateBridge;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.client.render.turbo.TurboEngineManager;
import com.moonsworth.lunar.client.render.turbo.TurboEntityRecorder;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.event.entity.EventLivingEntityBase.EventEntityScale;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderNameTag;
import com.moonsworth.lunar.client.network.server.ServerBrandWatcher;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.game.NpcUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.text.WordUtils;

public class MobSize extends AbstractFeature {
   private final HashMap<String, Class<BridgeExtension>> entityClasses = new HashMap<>();
   private final HashMap<String, String> entityDisplayNames = new HashMap<>();
   private final FloatOption playerSize = (FloatOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2("playerSize")
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.5F, 1.0F))
      .method31();
   private final FloatOption otherPlayerSize = (FloatOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2("otherPlayerSize")
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.5F, 1.0F))
      .method31();
   private final ToggleOption changeNpcSize = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("changeNpcSize")
      .method31();
   private final ToggleOption skyblockOnly = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("playerSizeSkyblockOnly")
      .method31();
   private final HashMap<Class<BridgeExtension>, FloatOption> entitySizeOptions = new HashMap<>();

   public MobSize() {
      super(false);
      this.handle(EventEntityScale.class, this::onEntityScale);
      this.handle(EventRenderNameTag.class, this::adjustNameTagHeight);
      this.loadEntityClasses();
      this.entityDisplayNames.put("Entity Horse", "Horse");
      this.entityDisplayNames.put("Ozelot", "Ocelot");
      this.entityDisplayNames.put("Lava Slime", "Magma Cube");
      this.entityDisplayNames.put("Wither Boss", "Wither");
      this.entityDisplayNames.put("Villager Golem", "Iron Golem");
      this.entityDisplayNames.put("Pig Zombie", "Zombie Pigman");
      this.entityDisplayNames.put("Creaking Transient", "Creaking");
      this.entityDisplayNames.put("Mushroom Cow", "Mooshroom");
   }

   private void loadEntityClasses() {
      Map map1 = Bridge.method61().method5();

      for (Entry entry3 : map1.entrySet()) {
         String text4 = (String)entry3.getKey();
         Class clazz5 = (Class)entry3.getValue();
         if (!text4.equals("Mob") && !Bridge6_10.class.isAssignableFrom(clazz5) && EntityLivingBridge.class.isAssignableFrom(clazz5)) {
            this.entityClasses.put(text4, clazz5);
         }
      }
   }

   private void onEntityScale(EventEntityScale data71) {
      if (!(Boolean)this.skyblockOnly.get() || IslandUtils.isOnIsland()) {
         EntityLivingBridge bridgeextension2_52 = data71.COCIHRCIHOORIIRRCCRHCIIIHRICIR();
         if (bridgeextension2_52 instanceof Bridge6_10 bridge6_103) {
            if (bridge6_103.OIHOORHOCRCOCIORRHOROOOORROIIH()) {
               return;
            }

            if (!(Boolean)this.changeNpcSize.get() && NpcUtils.method2(bridge6_103, ServerBrandWatcher.method8(KeystrokesType.HYPIXEL))) {
               return;
            }

            float value4 = bridge6_103.bridge$isSelf() ? (Float)this.playerSize.get() : (Float)this.otherPlayerSize.get();
            data71.method1(value4);
         } else {
            Class clazz6 = bridgeextension2_52.getClass();
            FloatOption lightingextension4725 = this.entitySizeOptions.get(clazz6);
            if (lightingextension4725 == null) {
               return;
            }

            data71.method1((Float)lightingextension4725.get());
         }
      }
   }

   private void adjustNameTagHeight(EventRenderNameTag highlightimpl111) {
      if (!(Boolean)this.skyblockOnly.get() || IslandUtils.isOnIsland()) {
         EntityLivingStateBridge bridgeextension2_22 = highlightimpl111.method2();
         double value3 = highlightimpl111.getY();
         double value5 = bridgeextension2_22.bridge$getHeight();
         highlightimpl111.setY(value3 - value5 * (1.0F - bridgeextension2_22.bridge$getLunarScale()));
      }
   }

   public String getId() {
      return "MOB_SIZE";
   }

   public void onEntityScale(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL, arg1x -> arg1x.method9(new ClientOption[]{this.skyblockOnly}).RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(1)
      );
      lightingextension231.method1(
         "playerOptions", arg1x -> arg1x.method9(new ClientOption[]{this.playerSize, this.otherPlayerSize, this.changeNpcSize})
      );
      lightingextension231.method1(
         "mobOptions",
         arg1x -> {
            ArrayList list2 = new ArrayList();

            for (Entry entry4 : this.entityClasses.entrySet()) {
               String text5 = (String)entry4.getKey();
               Class clazz6 = (Class)entry4.getValue();
               FloatOption lightingextension4727 = (FloatOption)((Data)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(this.getEntitySizeLabel(text5))
                           .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
                        .method8(0.5F, 1.0F))
                     .OHICCHCORCORRRHCHRCCIROCCHCCRC())
                  .method31();
               lightingextension4727.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1xx -> {
                  if (Ref.MC_VERSION >= 8) {
                     WorldBridgeExtension itemcounter6extension2x = Ref.method8();
                     TurboEngineManager fogiterator_33 = Ref.method4().method89();
                     if (itemcounter6extension2x != null && fogiterator_33.method3()) {
                        TurboEntityRecorder highlight3iterator234x = fogiterator_33.method26();
                        itemcounter6extension2x.bridge$getEntities().forEach(arg2xx -> {
                           if (clazz6.isInstance(arg2xx)) {
                              highlight3iterator234x.method13(arg2xx.CCHCORHCRHOCHHHCRHRCIHHIROHRCO(), false);
                           }
                        });
                     }
                  }
               });
               this.entitySizeOptions.put(clazz6, lightingextension4727);
               list2.add(lightingextension4727);
            }

            list2.sort(Comparator.comparing(ClientOption::getId));
            arg1x.method9(list2.toArray(new ClientOption[0]));
         }
      );
   }

   private String getEntitySizeLabel(String text1) {
      String text2;
      if (Character.isUpperCase(text1.charAt(0))) {
         text2 = text1.replaceAll("(.)([A-Z])", "$1 $2");
      } else {
         text2 = WordUtils.capitalizeFully(text1.replace('_', ' '));
      }

      return this.entityDisplayNames.getOrDefault(text2, text2) + " Size";
   }
}
