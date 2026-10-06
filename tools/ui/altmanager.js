/*
 * Local alt manager panel for Lunar's WebOSR UI.
 *
 * Injected by tools/patch_ui.py into a local copy of the UI bundle. It talks
 * to the patched "accounts" service (libs/lunar-localpatches.jar):
 *
 *   listAccounts()             -> JSON array of accounts
 *   addOfflineAccount(name)    -> creates a cracked account
 *   startPremiumAccount()      -> Microsoft device-code login
 *   getPremiumStatus()         -> JSON progress
 *   selectAccount(uuid)        -> switch session (patched to be local)
 *   removeAccount(uuid)        -> delete
 *
 * The stock "Add account" button (account chip and switcher) is redirected
 * here by patching the minified accounts module in patch_ui.py.
 */
(function () {
    "use strict";

    var panel = null;
    var premiumTimer = null;
    var launcherButton = null;
    var explicitOpenUntil = 0;

    function invoke(method) {
        var args = Array.prototype.slice.call(arguments, 1);
        if (!window.lunarInternalInvoke) {
            return Promise.reject(new Error("bridge not ready"));
        }
        return window.lunarInternalInvoke.apply(null, ["accounts", method].concat(args));
    }

    function el(tag, styles, text) {
        var node = document.createElement(tag);
        if (styles) {
            Object.keys(styles).forEach(function (key) {
                node.style[key] = styles[key];
            });
        }
        if (text != null) {
            node.textContent = text;
        }
        return node;
    }

    function button(label, onClick, background) {
        var b = el("button", {
            background: background || "#2b2f3a",
            color: "#fff",
            border: "1px solid rgba(255,255,255,0.15)",
            borderRadius: "6px",
            padding: "4px 8px",
            margin: "2px",
            cursor: "pointer",
            fontFamily: "inherit",
            fontSize: "12px"
        }, label);
        b.addEventListener("click", onClick);
        return b;
    }

    function row(text, extraStyle) {
        var r = el("div", Object.assign({
            color: "#e8e8e8",
            fontSize: "12px",
            padding: "3px 2px"
        }, extraStyle || {}), text);
        return r;
    }

    function ensurePanel() {
        if (panel || !document.body) {
            return panel;
        }
        panel = el("div", {
            position: "fixed",
            right: "12px",
            bottom: "64px",
            width: "300px",
            maxHeight: "70vh",
            overflowY: "auto",
            background: "rgba(18,20,26,0.96)",
            border: "1px solid rgba(255,255,255,0.18)",
            borderRadius: "10px",
            padding: "10px",
            zIndex: 2147483000,
            display: "none",
            boxShadow: "0 8px 24px rgba(0,0,0,0.5)",
            fontFamily: "system-ui, sans-serif"
        });

        var header = el("div", {
            display: "flex",
            alignItems: "center",
            justifyContent: "space-between"
        });
        header.appendChild(row("ALT MANAGER", {fontWeight: "700", fontSize: "13px", color: "#7fd7ff"}));
        var closeButton = button("✕", function () {
            togglePanel(false);
        });
        header.appendChild(closeButton);
        panel.appendChild(header);

        var nameInput = el("input", {
            width: "100%",
            boxSizing: "border-box",
            background: "#0f1116",
            color: "#fff",
            border: "1px solid rgba(255,255,255,0.2)",
            borderRadius: "6px",
            padding: "5px 7px",
            margin: "6px 0",
            fontSize: "12px"
        });
        nameInput.placeholder = "cracked username";
        panel.appendChild(nameInput);

        var actions = el("div", {display: "flex", flexWrap: "wrap"});
        actions.appendChild(button("Add cracked", function () {
            var name = (nameInput.value || "").trim();
            if (!name) {
                nameInput.focus();
                return;
            }
            invoke("addOfflineAccount", name).then(function () {
                nameInput.value = "";
                render();
            });
        }, "#1f6f43"));
        actions.appendChild(button("Add premium", function () {
            invoke("startPremiumAccount").then(function () {
                pollPremium();
            });
        }, "#2a4f7c"));
        actions.appendChild(button("Refresh", render));
        panel.appendChild(actions);

        var premiumBox = el("div", {marginTop: "4px"});
        premiumBox.id = "lunar-altmanager-premium";
        panel.appendChild(premiumBox);

        var listBox = el("div", {marginTop: "6px"});
        listBox.id = "lunar-altmanager-list";
        panel.appendChild(listBox);

        document.body.appendChild(panel);
        return panel;
    }

    function togglePanel(show) {
        ensurePanel();
        panel.style.display = show ? "block" : "none";
    }

    function render() {
        if (!panel) {
            return;
        }
        var list = panel.querySelector("#lunar-altmanager-list");
        list.textContent = "Loading...";
        invoke("listAccounts").then(function (raw) {
            var accounts;
            try {
                accounts = JSON.parse(raw);
            } catch (parseError) {
                accounts = [];
            }
            list.textContent = "";
            if (!accounts || !accounts.length) {
                list.appendChild(row("No accounts yet.", {color: "#999"}));
                return;
            }
            accounts.forEach(function (account) {
                var item = el("div", {
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "space-between",
                    borderTop: "1px solid rgba(255,255,255,0.08)",
                    padding: "4px 0"
                });
                var label = account.username + (account.invalid ? " (invalid)" : "");
                item.appendChild(row(label, {color: account.invalid ? "#ff8080" : "#e8e8e8"}));
                var buttons = el("div", {});
                buttons.appendChild(button("Use", function () {
                    invoke("selectAccount", account.uuid).then(render);
                }, "#1f6f43"));
                buttons.appendChild(button("✕", function () {
                    invoke("removeAccount", account.uuid).then(render);
                }, "#7c2a2a"));
                item.appendChild(buttons);
                list.appendChild(item);
            });
        }).catch(function (error) {
            list.textContent = "Failed to list accounts: " + error;
        });
    }

    function pollPremium() {
        if (premiumTimer) {
            clearInterval(premiumTimer);
        }
        var box = panel.querySelector("#lunar-altmanager-premium");
        var tick = function () {
            invoke("getPremiumStatus").then(function (raw) {
                var status;
                try {
                    status = JSON.parse(raw);
                } catch (parseError) {
                    status = {};
                }
                if (!status.state) {
                    box.textContent = "";
                    return;
                }
                if (status.state === "waiting" || status.state === "starting") {
                    box.innerHTML = "";
                    box.appendChild(row("Microsoft login", {fontWeight: "700", color: "#7fd7ff"}));
                    if (status.code) {
                        box.appendChild(row("Code: " + status.code, {fontSize: "16px", fontWeight: "700", color: "#ffd479"}));
                        box.appendChild(row(status.url, {color: "#9ecbff"}));
                    }
                    box.appendChild(row(status.message, {color: "#bbb"}));
                    return;
                }
                clearInterval(premiumTimer);
                premiumTimer = null;
                box.innerHTML = "";
                if (status.state === "success") {
                    box.appendChild(row("Added " + status.username, {color: "#8be28b"}));
                    render();
                } else if (status.state === "error") {
                    box.appendChild(row("Login failed: " + status.message, {color: "#ff8080"}));
                }
            });
        };
        tick();
        premiumTimer = setInterval(tick, 1000);
    }

    var api = {
        handleAdd: function () {
            explicitOpenUntil = Date.now() + 20000;
            togglePanel(true);
        },
        toggle: function () {
            ensurePanel();
            togglePanel(panel.style.display !== "block");
        },
        refresh: render,
        startWarmup: function () {
            // If the accounts payload changes, refresh the list when open.
            try {
                window.lunar.subscribe("data:accounts", function () {
                    if (panel && panel.style.display === "block") {
                        render();
                    }
                });
            } catch (subscribeError) {
                // ignore: bridge not ready yet
            }
        }
    };
    window.lunarAltManager = api;

    /**
     * The panel belongs to the main menu: hide the ALTS button everywhere
     * else (multiplayer list, in-game, ...). A panel opened explicitly (chip
     * click) stays for a short grace period, then auto-hides too.
     */
    function checkContext() {
        if (!window.lunarInternalInvoke || !launcherButton) {
            return;
        }
        invoke("getGameContext").then(function (raw) {
            var mainMenu = false;
            try {
                mainMenu = !!JSON.parse(raw).mainMenu;
            } catch (parseError) {
                // leave hidden on failure
            }
            launcherButton.style.display = mainMenu ? "block" : "none";
            if (!mainMenu && panel && panel.style.display === "block"
                    && Date.now() > explicitOpenUntil) {
                togglePanel(false);
            }
            if (!mainMenu && panel && Date.now() > explicitOpenUntil && premiumTimer) {
                clearInterval(premiumTimer);
                premiumTimer = null;
            }
        }).catch(function () {
            launcherButton.style.display = "none";
        });
    }

    function installLauncherButton() {
        if (!document.body || document.getElementById("lunar-altmanager-button")) {
            return;
        }
        var b = el("button", {
            position: "fixed",
            right: "12px",
            bottom: "12px",
            zIndex: 2147483000,
            background: "rgba(18,20,26,0.9)",
            color: "#7fd7ff",
            border: "1px solid rgba(255,255,255,0.2)",
            borderRadius: "8px",
            padding: "6px 10px",
            cursor: "pointer",
            fontSize: "12px",
            fontWeight: "700",
            fontFamily: "system-ui, sans-serif",
            display: "none"
        }, "ALTS");
        b.id = "lunar-altmanager-button";
        b.addEventListener("click", function () {
            api.toggle();
        });
        document.body.appendChild(b);
        launcherButton = b;
        setInterval(checkContext, 1500);
        checkContext();
    }

    var readyTimer = setInterval(function () {
        if (window.lunarInternalInvoke && document.body) {
            clearInterval(readyTimer);
            ensurePanel();
            installLauncherButton();
            api.startWarmup();
        }
    }, 250);
})();
