package com.moonsworth.lunar.altmanager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

/** Device-code Microsoft login: shows a code, polls, then logs in the account. */
public class GuiDeviceLogin extends GuiScreen {
    private final GuiAltManager parent;
    private final AltStore store;
    private String userCode = "";
    private String verificationUri = "";
    private String status = "Requesting device code...";
    private boolean started;

    public GuiDeviceLogin(GuiAltManager parent, AltStore store) {
        this.parent = parent;
        this.store = store;
    }

    @Override
    public void initGui() {
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 152, this.height - 52, 100, 20, "Copy code"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 50, this.height - 52, 100, 20, "Open link"));
        this.buttonList.add(new GuiButton(2, this.width / 2 + 52, this.height - 52, 100, 20, "Cancel"));
    }

    @Override
    public void updateScreen() {
        if (!this.started) {
            this.started = true;
            this.startLogin();
        }
    }

    private void startLogin() {
        Thread thread = new Thread(() -> {
            try {
                MicrosoftAuth.DeviceCode code = MicrosoftAuth.requestDeviceCode();
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    this.userCode = code.userCode;
                    this.verificationUri = code.verificationUri;
                    this.status = "Waiting for approval...";
                });
                AltAccount account = MicrosoftAuth.pollDeviceCode(code);
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    AltAccount saved = this.store.addPremium(account.getUsername(), account.getUuid(),
                            account.getAccessToken(), account.getRefreshToken());
                    SessionSwitcher.apply(saved);
                    this.store.select(saved);
                    this.parent.onExternalLogin(saved);
                    this.mc.displayGuiScreen(this.parent);
                });
            } catch (Exception exception) {
                Minecraft.getMinecraft().addScheduledTask(
                        () -> this.status = "Login failed: " + exception.getMessage());
            }
        }, "AltManager-DeviceLogin");
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Microsoft device login", this.width / 2, 30, 0xFFFFFF);
        this.drawCenteredString(this.fontRendererObj, this.status, this.width / 2, 60, 0xAAAAAA);
        if (!this.userCode.isEmpty()) {
            this.drawCenteredString(this.fontRendererObj, "Open " + this.verificationUri + " in a browser",
                    this.width / 2, 92, 0xFFFFFF);
            this.drawCenteredString(this.fontRendererObj, "and enter the code:", this.width / 2, 106, 0xFFFFFF);
            this.drawCenteredString(this.fontRendererObj, this.userCode, this.width / 2, 126, 0x55FF55);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0 -> {
                if (!this.userCode.isEmpty()) {
                    GuiScreen.setClipboardString(this.userCode);
                    this.status = "Code copied to clipboard";
                }
            }
            case 1 -> {
                if (!this.verificationUri.isEmpty() && Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(new URI(this.verificationUri));
                    } catch (Exception ignored) {
                    }
                }
            }
            case 2 -> this.mc.displayGuiScreen(this.parent);
            default -> {
            }
        }
    }
}
