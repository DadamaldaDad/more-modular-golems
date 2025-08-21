package net.dadamalda.more_modular_golems.compat.tconstruct.modifiers;

import dev.xkmc.modulargolems.content.core.StatFilterType;
import dev.xkmc.modulargolems.content.entity.common.AbstractGolemEntity;
import dev.xkmc.modulargolems.content.modifier.base.GolemModifier;
import net.dadamalda.more_modular_golems.ducks.IGuardedEntityMixinDuck;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;

import java.util.Objects;

public class TastyModifier extends GolemModifier {

    public TastyModifier() {
        super(StatFilterType.HEALTH, 1);
    }

    @Override
    public InteractionResult interact(Player player, AbstractGolemEntity<?, ?> golem, InteractionHand hand, int value) {
        if(!(player instanceof ServerPlayer serverPlayer)) return InteractionResult.PASS;
        ServerLevel level = serverPlayer.serverLevel();

        float damageAmount = 10F * value;
        int restoredHunger = 2 * value;
        float restoredSaturation = 1.0F;
        boolean canAlwaysEat = false;

        if(!Objects.equals(golem.getOwnerUUID(), serverPlayer.getUUID())) return InteractionResult.PASS;
        if(serverPlayer.isCrouching()) return InteractionResult.PASS;
        if(golem.getHealth() <= damageAmount) return InteractionResult.PASS;
        if(!player.getMainHandItem().is(Items.AIR)) return InteractionResult.PASS;
        if(!canAlwaysEat && !serverPlayer.getFoodData().needsFood()) return InteractionResult.PASS;

        ((IGuardedEntityMixinDuck) golem).more_modular_golems$setHealthBypass(golem.getHealth() - damageAmount);

        serverPlayer.getFoodData().eat(restoredHunger, restoredSaturation);

        level.playSound(null,
                serverPlayer.getX(),
                serverPlayer.getY(),
                serverPlayer.getZ(),
                SoundEvents.GENERIC_EAT,
                SoundSource.PLAYERS,
                1.0F,
                1.0F + (level.random.nextFloat() - level.random.nextFloat()) * 0.4F);

        level.playSound(null,
                serverPlayer.getX(),
                serverPlayer.getY(),
                serverPlayer.getZ(),
                SoundEvents.PLAYER_BURP,
                SoundSource.PLAYERS,
                0.5F,
                1.0F + level.random.nextFloat() * 0.1F);

        return InteractionResult.SUCCESS;
        // return super.interact(player, golem, hand, value);
    }
}
