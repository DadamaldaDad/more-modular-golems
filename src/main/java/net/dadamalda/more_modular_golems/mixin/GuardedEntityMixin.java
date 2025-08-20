package net.dadamalda.more_modular_golems.mixin;

import dev.xkmc.modulargolems.content.entity.common.GuardedEntity;
import net.dadamalda.more_modular_golems.ducks.IGuardedEntityMixinDuck;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(GuardedEntity.class)
public abstract class GuardedEntityMixin extends AbstractGolem implements IGuardedEntityMixinDuck {

    protected GuardedEntityMixin(EntityType<? extends AbstractGolem> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Unique
    @Override
    public void more_modular_golems$setHealthBypass(float health) {
        super.setHealth(health);
    }
}
