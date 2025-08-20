package net.dadamalda.more_modular_golems;

import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import dev.xkmc.l2library.base.NamedEntry;
import dev.xkmc.modulargolems.content.modifier.base.GolemModifier;
import dev.xkmc.modulargolems.init.registrate.GolemTypes;
import net.dadamalda.more_modular_golems.compat.tconstruct.TConstructCompatRegistry;
import net.minecraftforge.fml.ModList;
import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableObject;
import org.jetbrains.annotations.Nullable;

import static net.dadamalda.more_modular_golems.More_modular_golems.REGISTRATE;

public class MMGGolemModifiers {
    public static <T extends GolemModifier> RegistryEntry<T> reg(String id, NonNullSupplier<T> sup, String name, @Nullable String def) {
        Mutable<RegistryEntry<T>> holder = new MutableObject<>();
        var ans = REGISTRATE.generic(GolemTypes.MODIFIERS, id, sup).defaultLang();
        ans.lang(NamedEntry::getDescriptionId, name);
        if (def != null) {
            ans.addMiscData(ProviderType.LANG, pvd -> pvd.add(holder.getValue().get().getDescriptionId() + ".desc", def));
        }
        var result = ans.register();
        holder.setValue(result);
        return result;
    }

    public static void register() {
        if(ModList.get().isLoaded("tconstruct")) {
            TConstructCompatRegistry.register();
        }
    }
}
