package net.jukoz.me.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.jukoz.me.MiddleEarth;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModNetworks {
    public static final Identifier ITEM_SYNC = Identifier.of(MiddleEarth.MOD_ID, "item_sync");

    public static void registerS2CPackets() { // Server to Client packets
        // ClientPlayNetworking.registerGlobalReceiver(ITEM_SYNC, ItemStackSyncS2CPacket::receive); // TODO fixme & ItemStackSyncS2CPacket::receive
    }
}
