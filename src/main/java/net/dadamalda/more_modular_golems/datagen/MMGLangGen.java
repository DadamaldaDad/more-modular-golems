package net.dadamalda.more_modular_golems.datagen;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import net.dadamalda.more_modular_golems.compat.MMGCompatManager;

public class MMGLangGen {
    public static void genLang(RegistrateLangProvider pvd) {
        MMGCompatManager.dispatchLangGen(pvd);
    }
}
