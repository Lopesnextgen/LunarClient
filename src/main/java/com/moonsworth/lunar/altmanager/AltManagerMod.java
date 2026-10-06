package com.moonsworth.lunar.altmanager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

import java.io.File;

/**
 * Built-in alt manager for the local Lunar Client runtime.
 *
 * Press RSHIFT while no GUI is open (or from the main menu) to open the
 * manager, add cracked accounts and switch the live Minecraft session.
 * Cracked accounts work on offline-mode servers; singleplayer always works.
 */
@Mod(modid = AltManagerMod.MODID, name = "Lunar Alt Manager", version = "1.0", clientSideOnly = true)
public class AltManagerMod {
    public static final String MODID = "lunaraltmanager";

    private AltStore store;
    private boolean keyDown;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    private AltStore store() {
        if (this.store == null) {
            Minecraft mc = Minecraft.getMinecraft();
            this.store = new AltStore(new File(mc.mcDataDir, "lunar/altmanager.json"));
        }
        return this.store;
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }
        Minecraft mc = Minecraft.getMinecraft();
        if (mc == null) {
            return;
        }
        boolean down = Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
        if (down && !this.keyDown) {
            GuiScreen screen = mc.currentScreen;
            if (screen instanceof GuiAltManager) {
                mc.displayGuiScreen(null);
            } else if (screen == null) {
                mc.displayGuiScreen(new GuiAltManager(null, this.store()));
            } else {
                mc.displayGuiScreen(new GuiAltManager(screen, this.store()));
            }
        }
        this.keyDown = down;
    }
}
