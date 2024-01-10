package jdtalley.spclaims.util;

import jdtalley.spclaims.networking.ModMessages;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

/*
* Holds data for player claims
* player ->
*   claim-count: number of chunks claimed
*   claims ->
*       ChunkPosLongToString -> ChunkPosLong
* */
public class ClaimData {
    public static long addClaim(IEntityDataSaver player, long claimedChunk) {
        NbtCompound nbt = player.getPersistentData();
        NbtCompound claims = nbt.getCompound("claims");
        int claimCount = nbt.getInt("claim-count");

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

    public static int getClaimCount(IEntityDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        return nbt.getInt("claim-count");
    }

    public static void syncClaims (long claimedChunk, ServerPlayerEntity player) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeLong(claimedChunk);
        ServerPlayNetworking.send(player, ModMessages.CLAIM_SYNC_ID, buffer);
    }
}
