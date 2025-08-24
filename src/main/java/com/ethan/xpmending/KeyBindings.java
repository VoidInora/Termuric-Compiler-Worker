package com.ethan.xpmending;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.InputUtil.Type;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {

    private static KeyBinding repairKey;

    public static void register() {
        repairKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.xpmending.repair", // Translation key
                Type.KEYSYM,
                GLFW.GLFW_KEY_R, // Default key
                "category.xpmending.keys" // Category
        ));

        // Tick event to check if key is pressed
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (repairKey.wasPressed()) {
                ClientPlayNetworking.send(XPMendingMod.REPAIR_PACKET_ID, net.minecraft.network.PacketByteBufs.empty());
            }
        });
    }
}
