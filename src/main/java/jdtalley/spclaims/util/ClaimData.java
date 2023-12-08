package jdtalley.spclaims.util;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.ChunkPos;

public class ClaimData {
    public static boolean addClaim(IEntityDataSaver player, ChunkPos chunkPos) {
        NbtCompound nbt = player.getPersistentData();
        NbtCompound claims = nbt.getCompound("claims");

        if (claims.contains(String.valueOf(chunkPos.toLong()))) {
            return false;
        }

        claims.putLong(String.valueOf(chunkPos.toLong()), chunkPos.toLong());

        nbt.put("claims", claims);

        return true;
    }

    public static String getClaims(IEntityDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        NbtCompound claims = nbt.getCompound("claims");
        StringBuffer chunks = new StringBuffer(claims.getSize());

        claims.getKeys().forEach(key -> {
            chunks.append(claims.getLong(key));
        });

        return chunks.toString();
    }
}
