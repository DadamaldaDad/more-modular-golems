package net.dadamalda.more_modular_golems.compat.tconstruct;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.dadamalda.more_modular_golems.MMGGolemModifiers;
import net.dadamalda.more_modular_golems.compat.tconstruct.modifiers.TastyModifier;

public class TConstructCompatRegistry {
    public static final RegistryEntry<TastyModifier> TASTY;

    static {
        TASTY = MMGGolemModifiers.reg("tasty", TastyModifier::new, "Tasty", "You can bite your golem");
    }

    public static void register() {}
}
