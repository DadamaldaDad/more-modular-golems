package net.dadamalda.more_modular_golems.compat;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;

public abstract class MMGModDispatch {
    protected abstract void genLang(RegistrateLangProvider pvd);

    public abstract void genRecipe(RegistrateRecipeProvider pvd);

    public void commonSetup() {}
}
