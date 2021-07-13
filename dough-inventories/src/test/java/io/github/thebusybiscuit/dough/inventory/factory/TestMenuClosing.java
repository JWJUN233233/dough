package io.github.thebusybiscuit.dough.inventory.factory;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.github.thebusybiscuit.dough.inventory.Menu;
import io.github.thebusybiscuit.dough.inventory.MenuLayout;
import io.github.thebusybiscuit.dough.inventory.builders.MenuLayoutBuilder;
import io.github.thebusybiscuit.dough.inventory.builders.SlotGroupBuilder;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;

class TestMenuClosing {

    private static MenuFactory factory;
    private static ServerMock server;

    @BeforeAll
    static void setup() {
        server = MockBukkit.mock();
        factory = new MockMenuFactory();
    }

    @AfterAll
    static void teardown() {
        MockBukkit.unmock();
    }

    @Test
    void testCloseAll() {
        // @formatter:off
        MenuLayout layout = new MenuLayoutBuilder(9)
            .addSlotGroup(
                new SlotGroupBuilder('x', "test")
                    .withSlots(0, 1, 2, 3, 4, 5, 6, 7, 8)
                    .build()
            )
            .build();
        // @formatter:on

        Menu inv = factory.createInventory(layout);

        Player player = server.addPlayer();
        InventoryView view = inv.open(player);

        Player player2 = server.addPlayer();
        InventoryView view2 = inv.open(player2);

        assertSame(view, player.getOpenInventory());
        assertSame(view2, player2.getOpenInventory());

        inv.closeAllViews();

        assertNull(player.getOpenInventory());
        assertNull(player2.getOpenInventory());
    }

}