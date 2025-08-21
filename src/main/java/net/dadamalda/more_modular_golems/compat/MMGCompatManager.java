package net.dadamalda.more_modular_golems.compat;

import com.mojang.logging.LogUtils;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import net.dadamalda.more_modular_golems.compat.tconstruct.TConstructDispatch;
import net.minecraftforge.fml.ModList;

import java.util.ArrayList;
import java.util.List;

public class MMGCompatManager {
    public static final List<MMGModDispatch> LIST = new ArrayList<>();

    public static void register() {
        if(ModList.get().isLoaded(TConstructDispatch.MODID)) LIST.add(new TConstructDispatch());
    }

    public static void commonSetup() {
        for (MMGModDispatch dispatch : LIST) {
            // LogUtils.getLogger().info("dispatch common setup");
            dispatch.commonSetup();
        }
    }

    public static void dispatchGenRecipe(RegistrateRecipeProvider pvd) {
        for (MMGModDispatch dispatch : LIST) {
            LogUtils.getLogger().info("dispatch gen recipe");
            dispatch.genRecipe(pvd);
        }
    }

    public static void dispatchLangGen(RegistrateLangProvider pvd) {
        for (MMGModDispatch dispatch : LIST) {
            dispatch.genLang(pvd);
        }
    }
}
