package mainApplication;

import common.ModuleAbs;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<ModuleAbs> modules = new ArrayList<>();
        modules.add(new ui_Impl.ModuleImpl());
        modules.add(new entity_Impl.ModuleImpl());
        modules.add(new level_Impl.ModuleImpl());
        modules.add(new game_Impl.ModuleImpl());

        for (ModuleAbs module : modules) {
            module.initializeAgent();
        }

        for (ModuleAbs module : modules) {
            module.initializeServices();
        }

        for (ModuleAbs module : modules) {
            module.start();
        }
    }
}