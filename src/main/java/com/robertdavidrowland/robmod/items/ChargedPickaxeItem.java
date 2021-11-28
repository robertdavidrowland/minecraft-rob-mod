package com.robertdavidrowland.robmod.items;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

public class ChargedPickaxeItem extends PickaxeItem {
    public ChargedPickaxeItem(Properties properties) {
        super(Tiers.IRON, 1, -2.8F, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        super.appendHoverText(stack, level, components, flag);
        components.add(new TranslatableComponent("message.charged_pickaxe.tooltip", Integer.toString(getDistance(stack))).withStyle(ChatFormatting.DARK_PURPLE));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        int distance = stack.getOrCreateTag().getInt("distance");
        distance = distance < 3 ? distance + 1 : 0;
        stack.getTag().putInt("distance", distance);
        if (level.isClientSide()) {
            player.sendMessage(new TranslatableComponent("message.charged_pickaxe.change", Integer.toString(distance)).withStyle(ChatFormatting.DARK_PURPLE), Util.NIL_UUID);
        }
        return InteractionResultHolder.success(stack);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        boolean result = super.mineBlock(stack, level, state, pos, entity);

        int distance = getDistance(stack);
        if (distance > 0) {
            CompoundTag tag = stack.getOrCreateTag();
            boolean mining = tag.getBoolean("mining");
            if (!mining) {
                BlockHitResult hit = trace(level, entity);
                if (hit.getType() == HitResult.Type.BLOCK) {
                    tag.putBoolean("mining", true);
                    for (int i = 0; i < distance; i++) {
                        BlockPos relative = pos.relative(hit.getDirection().getOpposite(), i + 1);
                        if (!tryHarvest(stack, entity, relative)) {
                            tag.putBoolean("mining", false);
                            return result;
                        }
                    }
                    tag.putBoolean("mining", false);
                }
            }
        }

        return result;
    }

    public int getDistance(ItemStack stack) {
        return stack.hasTag() ? stack.getTag().getInt("distance") : 0;
    }

    private boolean tryHarvest(ItemStack stack, LivingEntity entityLiving, BlockPos pos) {
        BlockState state = entityLiving.level.getBlockState(pos);
        if (stack.getItem().isCorrectToolForDrops(stack, state)) {
            if (entityLiving instanceof ServerPlayer) {
                ServerPlayer player = (ServerPlayer) entityLiving;
                return player.gameMode.destroyBlock(pos);
            }
        }
        return false;
    }

    private BlockHitResult trace(Level level, LivingEntity player) {
        double reach = player.getAttribute(net.minecraftforge.common.ForgeMod.REACH_DISTANCE.get()).getValue();
        Vec3 eye = player.getEyePosition(1.0f);
        Vec3 view = player.getViewVector(1.0f);
        Vec3 withReach = eye.add(view.x * reach, view.y * reach, view.z * reach);
        return level.clip(new ClipContext(eye, withReach, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player));
    }
}
