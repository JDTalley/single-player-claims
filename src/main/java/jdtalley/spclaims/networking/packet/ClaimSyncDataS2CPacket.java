package jdtalley.spclaims.networking.packet;

import jdtalley.spclaims.util.IEntityDataSaver;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.ChunkPos;

public class ClaimSyncDataS2CPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        ChunkPos chunkPos = new ChunkPos(buf.readLong());
        ((IEntityDataSaver) client.player).getPersistentData().getCompound("claims")
                .putLong(String.valueOf(chunkPos.toLong()), chunkPos.toLong());
    }
}
