package jdtalley.spclaims.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.ChunkPos;

import java.util.Objects;

public class ClaimItem extends Item {
    public ClaimItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getWorld().isClient()) {
            ChunkPos positionClicked = context.getWorld().getChunk(context.getBlockPos()).getPos();
            PlayerEntity player = context.getPlayer();
            
            if (isOwnedChunk(positionClicked)) {
                player.sendMessage(Text.literal("Chunk " + positionClicked.toLong() + " Owned!"), false);
            } else {
                player.sendMessage(Text.literal("Chunk " + positionClicked.toLong() + " Not Owned!"), false);
            }
        }

        context.getStack().damage(0,context.getPlayer(),
                playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));

        return ActionResult.SUCCESS;
    }

    private boolean isOwnedChunk(ChunkPos positionClicked) {
        return Objects.equals(positionClicked, new ChunkPos(0, 0));
    }
}
