package com.moonsworth.lunar.client.mod.misc.debug;

import com.moonsworth.lunar.bridge.EntityRenderStateBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.client.chat.translation.SharedTranslations;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.ui.hud.HudConditionSet;
import com.moonsworth.lunar.client.cosmetics.gecko.EmoteDefinition;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.AnimationStateConfig;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.framework.Ref;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.TextComponent.Builder;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.joml.Vector3d;
import org.jspecify.annotations.Nullable;

public class CompanionDebug extends AbstractFeature {
   public final ToggleOption field8 = (ToggleOption)OptionFactory.method7("showCompanionPath").method31();
   public final ToggleOption field9 = (ToggleOption)OptionFactory.method7("showCompanionCollisionBox").method31();
   public final ToggleOption field10 = (ToggleOption)OptionFactory.method7("showCompanionCollisions").method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showCompanionState").method4(true))
      .method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showCompanionTargetPos").method4(true))
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showCompanionVelocity").method4(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showCompanionOnGround").method4(true))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showCompanionPos").method4(true))
      .method31();

   protected CompanionDebug(Framework7Extension framework7extension1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(framework7extension1));
      this.method2(ModTraits.field1, new CompanionDebug.Data());
   }

   public String getId() {
      return "COMPANION_DEBUG";
   }

   @OverridingMethodsMustInvokeSuper
   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method1(
         "companionDebugOptions",
         arg1x -> arg1x.method2(
            new OptionProvider[]{
               this.field8,
               this.field9,
               this.field10,
               this.field11,
               this.field15,
               this.field13,
               this.field14,
               this.field12,
               OptionFactory.method14("whereIsCompanion")
                  .method5(140.0F)
                  .method4(
                     () -> {
                        for (EntityRenderStateBridge bridge_612 : Ref.method4().method88().method24().keySet()) {
                           if (bridge_612 instanceof EmoteDefinition inactive33 && inactive33.method29().equals(Ref.method7())) {
                              Horsestats20Extension2 horsestats20extension26 = inactive33.method8();
                              String text4;
                              String text5;
                              if (horsestats20extension26 == null) {
                                 text5 = "/minecraft:tp " + inactive33.bridge$getPosX() + " " + inactive33.bridge$getPosY() + " " + inactive33.bridge$getPosZ();
                                 text4 = "[" + inactive33.bridge$getPosX() + ", " + inactive33.bridge$getPosY() + ", " + inactive33.bridge$getPosZ() + "]";
                              } else {
                                 text5 = "/minecraft:tp " + horsestats20extension26.bridge$getX() + " " + horsestats20extension26.bridge$getY() + " " + horsestats20extension26.bridge$getZ();
                                 text4 = "[" + horsestats20extension26.bridge$getX() + ", " + horsestats20extension26.bridge$getY() + ", " + horsestats20extension26.bridge$getZ() + "]";
                              }

                              Ref.method7()
                                 .HRICOROOOCCOCOROCRHHCRRIRCOICO(
                                    ((Builder)Component.text()
                                          .content(this.method14("companionIsAt", new Object[0]))
                                          .append(
                                             ((Builder)((Builder)Component.text().content(text4).color(NamedTextColor.GRAY))
                                                   .hoverEvent(
                                                      Component.text(
                                                         this.method14("clickToTeleport", new Object[0]), NamedTextColor.GREEN
                                                      )
                                                   ))
                                                .clickEvent(ClickEvent.runCommand(text5))
                                          ))
                                       .build()
                                 );
                              return;
                           }
                        }

                        Ref.method7().bridge$sendChatMessage(this.method14("companionDoesntExist", new Object[0]));
                     }
                  )
            }
         )
      );
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field7}).method3(new String[]{"FX"}).method11(this);
   }

   public List<Component> method3(EmoteDefinition inactive31, boolean flag2) {
      ArrayList list3 = new ArrayList();
      if (flag2) {
         list3.add(
            ((Builder)Component.text()
                  .content(this.method14("yourEquippedCosmetic", new Object[0]))
                  .append(Component.text(inactive31.method28().getName(), NamedTextColor.DARK_PURPLE)))
               .build()
         );
         list3.add(Component.empty());
      } else {
         Component component4 = inactive31.method29().bridge$getDisplayNameComponent();
         list3.add(Component.empty().append(new ComponentLike[]{component4, Component.text(this.method14("theirCompanion", new Object[0]))}));
      }

      if ((Boolean)this.field11.get()) {
         AnimationStateConfig gui36 = inactive31.method45();
         String text5;
         if (gui36 != null) {
            text5 = gui36.getId();
         } else {
            text5 = "-";
         }

         list3.add(
            ((Builder)((Builder)Component.text()
                     .content(this.method14("state", new Object[0]))
                     .append(Component.text(text5, NamedTextColor.GRAY)))
                  .append(Component.text(" (" + inactive31.method43() + ")")))
               .build()
         );
      }

      if ((Boolean)this.field15.get()) {
         list3.add(
            ((Builder)Component.text()
                  .content(this.method14("pos", new Object[0]))
                  .append(
                     Component.text(
                        String.format("[%.2f, %.2f, %.2f]", inactive31.bridge$getPosX(), inactive31.bridge$getPosY(), inactive31.bridge$getPosZ()), NamedTextColor.GRAY
                     )
                  ))
               .build()
         );
      }

      if ((Boolean)this.field12.get()) {
         Vector3d vector3d7 = inactive31.method38();
         list3.add(
            ((Builder)Component.text()
                  .content(this.method14("targetPos", new Object[0]))
                  .append(Component.text(vector3d7 != null ? "[" + vector3d7.x() + ", " + vector3d7.y() + ", " + vector3d7.z() + "]" : "[]", NamedTextColor.GRAY)))
               .build()
         );
      }

      if ((Boolean)this.field13.get()) {
         Vector3d vector3d8 = inactive31.method32();
         list3.add(
            ((Builder)Component.text()
                  .content(this.method14("velocity", new Object[0]))
                  .append(Component.text(String.format("[%.2f, %.2f, %.2f]", vector3d8.x(), vector3d8.y(), vector3d8.z()), NamedTextColor.GRAY)))
               .build()
         );
      }

      if ((Boolean)this.field14.get()) {
         list3.add(
            ((Builder)Component.text()
                  .content(this.method14("onGround", new Object[0]))
                  .append(
                     inactive31.isOnGround() ? Component.text(SharedTranslations.field4, NamedTextColor.GREEN) : Component.text(SharedTranslations.field5, NamedTextColor.DARK_RED)
                  ))
               .build()
         );
      }

      return list3;
   }

   private class Data extends TypedHudRenderer<List<Component>> {
      private @Nullable WeakReference<EmoteDefinition> field31;

      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_LEFT);
      }

      public HudConditionSet method5() {
         return HudConditionSet.method5().method1(false).method2(false).method8();
      }

      public HudSize method15() {
         return new HudSize(0, 50, 200, 0, 50, 200);
      }

      public @Nullable List<Component> method3(boolean flag1) {
         if (Ref.method7() == null) {
            this.field31 = null;
         } else {
            if (this.field31 == null || this.field31.get() == null) {
               for (EntityRenderStateBridge bridge_613 : Ref.method4().method88().method24().keySet()) {
                  if (bridge_613 instanceof EmoteDefinition inactive34 && inactive34.method29().equals(Ref.method7())) {
                     this.field31 = new WeakReference<>(inactive34);
                     break;
                  }
               }
            }

            if (this.field31 != null) {
               EmoteDefinition inactive35 = this.field31.get();
               if (inactive35 != null) {
                  return CompanionDebug.this.method3(inactive35, true);
               }
            }
         }

         return null;
      }
   }
}
