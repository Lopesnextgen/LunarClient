package com.moonsworth.lunar.client.mod.skyblock.kuudra;

import com.moonsworth.lunar.bridge.EntityMagmaCubeBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_4;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.client.config.option.NamedColorOption;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.KuudraTier;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.AlertDisplayListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.mixin.KuudraTierListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.mixin.KuudraBossHealth;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.framework.Ref;
import net.kyori.adventure.text.Component;

public class SkyblockKuudraAlerts extends AbstractFeature {
   private final KuudraTierListener field8 = (KuudraTierListener)this.method63(KuudraTierListener.class);
   private final KuudraBossHealth field9 = (KuudraBossHealth)this.method63(KuudraBossHealth.class);
   private final AlertDisplayListener field10 = (AlertDisplayListener)this.method63(AlertDisplayListener.class);
   private static final String field11 = "[NPC] Elle: A Dropship is approaching! Take it down before it's too late!";
   private static final String field12 = "[NPC] Elle: A fleet of Dropships are approaching! Take them down before it's too late!";
   private static final String field13 = "[NPC] Elle: That looks like it hurt! Quickly, while Kuudra is distracted, shoot him with the Ballista!";
   private static final String field14 = "[NPC] Elle: Phew! The Ballista is finally ready! It should be strong enough to tank Kuudra's blows now!";
   private static final String field15 = "recovered a Fuel Cell and charged the Ballista! (100%)";
   private static final double field16 = 55.0;
   private int field17;

   public SkyblockKuudraAlerts(SkyblockKuudra skyblockkuudra1, ToggleOption lightingextension4432) {
      super(true);
      this.method6(ModTraits.field16, ChildModBinding.method4(false, skyblockkuudra1));
      this.method6(ModTraits.field6, ModEnabledState.method7(lightingextension4432));
      this.handle(TypedChatMessage.class, this::method2);
      this.handle(EventTick.class, arg1x -> {
         this.method13();
         this.method14();
      });
      this.handle(EventWorldChange.class, this::method5);
   }

   public String getId() {
      return "SKYBLOCK_KUUDRA_ALERTS";
   }

   protected void method1(boolean flag1) {
   }

   private void method2(TypedChatMessage data1) {
      if (IslandUtils.getIsland() == SkyblockIsland.KUUDRA) {
         String text2 = data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
         SkyblockKuudra skyblockkuudra3 = (SkyblockKuudra)((ChildModBinding)this.method7(ModTraits.field16)).method1();
         if ((Boolean)skyblockkuudra3.method41().get()
            && text2.equals("[NPC] Elle: That looks like it hurt! Quickly, while Kuudra is distracted, shoot him with the Ballista!")) {
            this.method6(skyblockkuudra3, "stun", skyblockkuudra3.method42(), 2000L);
         } else if ((Boolean)skyblockkuudra3.method43().get()
            && text2.equals("[NPC] Elle: Phew! The Ballista is finally ready! It should be strong enough to tank Kuudra's blows now!")) {
            this.method6(skyblockkuudra3, "ballistaBuilt", skyblockkuudra3.method44(), 2000L);
         } else if ((Boolean)skyblockkuudra3.method39().get()
            && (
               text2.equals("[NPC] Elle: A Dropship is approaching! Take it down before it's too late!")
                  || text2.equals("[NPC] Elle: A fleet of Dropships are approaching! Take them down before it's too late!")
            )) {
            this.field17 = 900;
         }

         if (!text2.contains(":")) {
            if ((Boolean)skyblockkuudra3.method45().get() && text2.endsWith("recovered a Fuel Cell and charged the Ballista! (100%)")) {
               this.method6(skyblockkuudra3, "ballistaFueled", skyblockkuudra3.method46(), 2000L);
            }
         }
      }
   }

   private void method13() {
      if (IslandUtils.getIsland() == SkyblockIsland.KUUDRA) {
         if (this.field17 > 0) {
            this.field17--;
            WorldBridgeExtension itemcounter6extension1 = Ref.method8();
            if (itemcounter6extension1 != null) {
               if (this.field17 <= 0) {
                  for (BridgeExtension bridgeextension3 : itemcounter6extension1.bridge$getEntities()) {
                     if (bridgeextension3 instanceof Bridge5Extension_4) {
                        SkyblockKuudra skyblockkuudra4 = (SkyblockKuudra)((ChildModBinding)this.method7(ModTraits.field16)).method1();
                        this.method6(skyblockkuudra4, "dropship", skyblockkuudra4.method40(), 3000L);
                        return;
                     }
                  }
               }
            }
         }
      }
   }

   private void method14() {
      SkyblockKuudra skyblockkuudra1 = (SkyblockKuudra)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      if ((Boolean)skyblockkuudra1.method47().get()) {
         if (this.field8.method6() == KuudraTier.T5) {
            EntityMagmaCubeBridge bridge5extension32 = this.field9.method9();
            if (bridge5extension32 != null && this.field9.method10()) {
               if (!(Boolean)skyblockkuudra1.method50().get() || !(bridge5extension32.bridge$getUnboundedHealth() < 24900.0F)) {
                  if (!(bridge5extension32.bridge$getPosY() >= 55.0) && !(bridge5extension32.IHCIRIRCORRCOIHOHRRCOCOCOHIHCH() < 55.0)) {
                     String text3 = null;
                     double value4 = bridge5extension32.bridge$getPosX();
                     double value6 = bridge5extension32.bridge$getPosZ();
                     if (value6 > -70.0) {
                        text3 = "front";
                     } else if (value6 < -140.0) {
                        text3 = "back";
                     } else if (value4 > -70.0) {
                        text3 = "left";
                     } else if (value4 < -140.0) {
                        text3 = "right";
                     }

                     if (text3 != null) {
                        if ((Boolean)skyblockkuudra1.method49().get()) {
                           text3 = text3 + "Compass";
                        }

                        this.method6(skyblockkuudra1, text3, skyblockkuudra1.method48(), 1000L);
                     }
                  }
               }
            }
         }
      }
   }

   private void method5(EventWorldChange data31) {
      this.field17 = 0;
   }

   private void method6(SkyblockKuudra skyblockkuudra1, String text2, EnumOption<NamedColorOption> lightingextension4973, long number4) {
      this.field10
         .method2(
            ComparableImpl.method2()
               .method1("KUUDRA_ALERT")
               .method2(Component.text(skyblockkuudra1.method44(text2, new Object[0]), TextComponentFactory.styleOf(lightingextension4973)))
               .method3(number4)
               .method6()
         );
   }
}
