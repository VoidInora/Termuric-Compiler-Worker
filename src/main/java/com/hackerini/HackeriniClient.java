package com.hackerini;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.lwjgl.glfw.GLFW;

public class HackeriniClient implements ClientModInitializer {
    public static boolean espEnabled = false;

    @Override
    public void onInitializeClient() {
        // Obsługa klawisza (np. "P" włącza/wyłącza ESP)
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (client.options.chatKey.wasPressed()) { // Przykład
                // Logika otwierania menu
            }
            
            // Prosty przełącznik pod klawiszem P (GLFW_KEY_P)
            if (GLFW.glfwGetKey(client.getWindow().getHandle(), GLFW.GLFW_KEY_P) == GLFW.GLFW_PRESS) {
                // Tu można dodać debouncing, żeby nie migało
                espEnabled = !espEnabled;
            }
        });
    }
}