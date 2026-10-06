package com.moonsworth.lunar.client.mod.skyblock.wishingcompass;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.particle.ParticleType;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.mod.render.WaypointStore;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockChat;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.EquippedItemListener;
import com.moonsworth.lunar.client.framework.feature.waypoints.Waypoint;
import com.moonsworth.lunar.client.event.input.InputAction;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSpawnParticle;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventKeybind;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseButton;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.List;
import toxi.geom.Line3D;
import toxi.geom.Vec3D;
import toxi.geom.Line3D.LineIntersection;

public class SkyblockWishingCompass extends AbstractFeature {
   EquippedItemListener field8 = (EquippedItemListener)this.method63(EquippedItemListener.class);
   private SkyblockWishingCompass.Type field9 = SkyblockWishingCompass.Type.USED_NONE;
   private final List<EventSpawnParticle> field10 = new ArrayList<>();
   private Line3D field11;
   private Line3D field12;
   private Vec3D field13;
   private long field14;
   private Waypoint field15;

   public SkyblockWishingCompass(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.MINING));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.method51(this::onDisable);
      this.handle(EventSpawnParticle.class, this::method5);
      this.handle(TypedChatMessage.class, this::method4);
      this.handle(EventWorldChange.class, this::method3);
      this.handle(EventTick.class, this::method1);
      this.handle(EventKeybind.class, arg1x -> {
         if (arg1x.method11() == InputAction.DOWN) {
            if (!arg1x.method12()) {
               this.method2(arg1x.method10(), arg1x);
            }
         }
      });
      this.handle(EventMouseButton.class, arg1x -> {
         if (arg1x.method2() >= 0 && arg1x.method2() <= 15) {
            if (arg1x.method4() == InputAction.DOWN) {
               this.method2(arg1x.method1(), arg1x);
            }
         }
      });
   }

   private void onDisable() {
      this.method3(null);
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_WISHING_COMPASS";
   }

   private void method1(EventTick highlightimpl21) {
      if (this.field15 != null && this.field15.getDistance() < 15.0) {
         Ref.method4().method48().method9(this.field15);
         this.field15 = null;
      }
   }

   private void method2(KeyCode bridgetype_81, com.moonsworth.lunar.client.event.CancellableEvent highlightimpl2) {
      if (bridgetype_81 == Ref.method3().bridge$getGameSettings().bridge$keyBindUseItem().bridge$getKey()) {
         SkyblockWishingCompass.WishingCompassVerdict type23 = this.method14();
         Skyblock skyblock4 = (Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1();
         switch (type23) {
            case ACCEPT_SUCCESS:
               if (this.field9 == SkyblockWishingCompass.Type.USED_NONE) {
                  SkyBlockChat.method1(skyblock4.method226("wishingCompassSuccess", new Object[0]));
               }

               this.field14 = Ref.method3().bridge$getSystemTime();
               break;
            case IN_NUCLEUS_REJECTION:
               SkyBlockChat.method1(skyblock4.method226("wishingCompassNucleus", new Object[0]));
               highlightimpl2.cancel();
               break;
            case TOO_CLOSE_REJECTION:
               SkyBlockChat.method1(skyblock4.method226("wishingCompassMove", new Object[0]));
               highlightimpl2.cancel();
               break;
            case SPAM_REJECTION:
               SkyBlockChat.method1(skyblock4.method226("wishingCompassWait", new Object[0]));
               highlightimpl2.cancel();
         }
      }
   }

   private void method3(EventWorldChange data31) {
      this.field9 = SkyblockWishingCompass.Type.USED_NONE;
      this.field11 = null;
      this.field12 = null;
      this.field13 = null;
      if (this.field15 != null) {
         Ref.method4().method48().method9(this.field15);
         this.field15 = null;
      }
   }

   private void method4(TypedChatMessage data1) {
      String text2 = TextBridge.getTextContent(data1.OHCICHOROROOORHCRICORHRRCRCCHO());
      Bridge5Extension_5 bridge5extension_53 = Ref.method7();
      if (bridge5extension_53 != null) {
         if (text2.equals("Your Wishing Compass shattered into pieces!")) {
            this.field10.clear();
            this.field14 = Ref.method3().bridge$getSystemTime();
            if (this.field9 == SkyblockWishingCompass.Type.USED_NONE) {
               this.field13 = new Vec3D((float)bridge5extension_53.bridge$getPosX(), (float)bridge5extension_53.bridge$getPosY(), (float)bridge5extension_53.bridge$getPosZ());
            }
         }
      }
   }

   private void method5(EventSpawnParticle highlightimpl151) {
      if (highlightimpl151.method2() == ParticleType.HAPPY_VILLAGER) {
         if (Ref.method3().bridge$getSystemTime() - this.field14 <= 5000L) {
            if (IslandUtils.getIsland() == SkyblockIsland.CRYSTAL_HOLLOWS) {
               if (this.field10.size() < 5) {
                  this.field10.add(highlightimpl151);
                  if (this.field10.size() == 5) {
                     EventSpawnParticle highlightimpl152 = this.field10.get(0);
                     EventSpawnParticle highlightimpl153 = this.field10.get(4);
                     Vec3D vec3d4 = new Vec3D((float)highlightimpl152.getPosX(), (float)highlightimpl152.getPosY(), (float)highlightimpl152.getPosZ());
                     Vec3D vec3d5 = new Vec3D((float)highlightimpl153.getPosX(), (float)highlightimpl153.getPosY(), (float)highlightimpl153.getPosZ());
                     Line3D line3d6 = new Line3D(vec3d4, vec3d5);
                     if (this.field9 == SkyblockWishingCompass.Type.USED_NONE) {
                        this.field11 = line3d6;
                        this.field9 = SkyblockWishingCompass.Type.USED_FIRST;
                     } else {
                        this.field12 = line3d6;
                        this.method13();
                        this.field9 = SkyblockWishingCompass.Type.USED_NONE;
                     }

                     this.field14 = 0L;
                     this.field10.clear();
                  }
               }
            }
         }
      }
   }

   private void method13() {
      Skyblock skyblock1 = (Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      LineIntersection lineintersection2 = this.field11.closestLineTo(this.field12);
      if (lineintersection2.getType() == toxi.geom.Line3D.LineIntersection.Type.NON_INTERSECTING) {
         SkyBlockChat.method1(skyblock1.method226("wishingCompassFail", new Object[0]));
      } else {
         Vec3D vec3d3 = lineintersection2.getLine().getMidPoint();
         WaypointStore holograms54 = Ref.method4().method48();
         if (this.field15 != null) {
            holograms54.method9(this.field15);
         }

         this.field15 = Waypoint.method18()
            .method2("Wishing Compass Estimate")
            .method3(Vec3Bridge.method2(vec3d3.x(), vec3d3.y(), vec3d3.z()))
            .method4(Client.method109().getWorld())
            .method5(Ref.method8().bridge$getDimensionId())
            .method12(WaypointStore.method19())
            .method13(true)
            .method19();
         SkyBlockChat.method1(skyblock1.method226("wishingCompassEstimateMade", new Object[0]));
         holograms54.method6(this.field15);
         this.field9 = SkyblockWishingCompass.Type.USED_NONE;
      }
   }

   private SkyblockWishingCompass.WishingCompassVerdict method14() {
      Bridge5Extension_5 bridge5extension_51 = Ref.method7();
      if (bridge5extension_51 == null) {
         return SkyblockWishingCompass.WishingCompassVerdict.ACCEPT_IRRELEVANT;
      }

      if (!this.field8.method9().equals("WISHING_COMPASS")) {
         return SkyblockWishingCompass.WishingCompassVerdict.ACCEPT_IRRELEVANT;
      }

      if (Ref.method3().bridge$getSystemTime() - this.field14 < 5000L) {
         return SkyblockWishingCompass.WishingCompassVerdict.SPAM_REJECTION;
      }

      if (bridge5extension_51.bridge$getPosY() >= 67.0
         && bridge5extension_51.bridge$getPosX() <= 560.0
         && bridge5extension_51.bridge$getPosZ() <= 560.0
         && bridge5extension_51.bridge$getPosX() >= 440.0
         && bridge5extension_51.bridge$getPosZ() >= 440.0) {
         return SkyblockWishingCompass.WishingCompassVerdict.IN_NUCLEUS_REJECTION;
      }

      if (this.field9 == SkyblockWishingCompass.Type.USED_NONE) {
         return SkyblockWishingCompass.WishingCompassVerdict.ACCEPT_SUCCESS;
      }

      Vec3D vec3d2 = new Vec3D((float)bridge5extension_51.bridge$getPosX(), (float)bridge5extension_51.bridge$getPosY(), (float)bridge5extension_51.bridge$getPosZ());
      return vec3d2.distanceToSquared(this.field13) <= 400.0F ? SkyblockWishingCompass.WishingCompassVerdict.TOO_CLOSE_REJECTION : SkyblockWishingCompass.WishingCompassVerdict.ACCEPT_SUCCESS;
   }

   private enum Type {
      USED_NONE,
      USED_FIRST;

      Type() {
      }
   }

   private enum WishingCompassVerdict {
      IN_NUCLEUS_REJECTION,
      TOO_CLOSE_REJECTION,
      ACCEPT_IRRELEVANT,
      ACCEPT_SUCCESS,
      SPAM_REJECTION;

      WishingCompassVerdict() {
      }
   }
}
