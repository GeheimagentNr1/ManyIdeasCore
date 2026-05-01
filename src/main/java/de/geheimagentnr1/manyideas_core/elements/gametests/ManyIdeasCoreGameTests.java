package de.geheimagentnr1.manyideas_core.elements.gametests;

import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.neoforged.neoforge.gametest.GameTestHolder;


@GameTestHolder( "manyideas_core" )
public class ManyIdeasCoreGameTests {

    @GameTest( templateNamespace = "neoforge", template = "floor_3x3x3" )
    public static void modLoadsSuccessfully( GameTestHelper helper ) {

        helper.succeed();
    }
}
