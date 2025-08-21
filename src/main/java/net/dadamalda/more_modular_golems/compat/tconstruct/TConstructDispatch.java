package net.dadamalda.more_modular_golems.compat.tconstruct;

import com.mojang.logging.LogUtils;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import net.dadamalda.more_modular_golems.compat.MMGModDispatch;
import net.dadamalda.more_modular_golems.compat.tconstruct.automation.TConstructRecipeGen;

public class TConstructDispatch extends MMGModDispatch {
    public static final String MODID = "tconstruct";

    public void TCounstructDispatch() {
        TConstructCompatRegistry.register();
        LogUtils.getLogger().info("TConstructDispatch");
    }

    @Override
    protected void genLang(RegistrateLangProvider pvd) {
        pvd.add("golem_material."+MODID+".pig_iron", "Pig Iron");
    }

    @Override
    public void genRecipe(RegistrateRecipeProvider registrateRecipeProvider) {
        TConstructRecipeGen.genRecipe(registrateRecipeProvider);
    }
}
