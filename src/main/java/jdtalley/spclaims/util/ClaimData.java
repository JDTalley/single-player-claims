package jdtalley.spclaims.util;

import jdtalley.spclaims.networking.ModMessages;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class ClaimData {
    public static long addClaim(IEntityDataSaver player, long claimedChunk) {
        NbtCompound nbt = player.getPersistentData();
        NbtCompound claims = nbt.getCompound("claims");

        claims.putLong(String.valueOf(claimedChunk), claimedChunk);

        nbt.put("claims", claims);

        syncClaims(claimedChunk, (ServerPlayerEntity) player);

        return claimedChunk;
    }

    public static boolean isChunkOwned(IEntityDataSaver player, long chunkPos) {
        NbtCompound nbt = player.getPersistentData();
        NbtCompound claims = nbt.getCompound("claims");

        return claims.contains(String.valueOf(chunkPos));
    }

    public static void syncClaims (long claimedChunk, ServerPlayerEntity player) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeLong(claimedChunk);
        ServerPlayNetworking.send(player, ModMessages.CLAIM_SYNC_ID, buffer);
    }
}
