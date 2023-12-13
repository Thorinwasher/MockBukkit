package be.seeseemelk.mockbukkit.enchantment;

import be.seeseemelk.mockbukkit.MockBukkit;
import org.bukkit.enchantments.Enchantment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EnchantmentMockTest
{

	@BeforeEach
	void setUp()
	{
		MockBukkit.mock();
	}

	@AfterEach
	void tearDown()
	{
		MockBukkit.unmock();
	}

	@Test
	void testEnchantmentValuesContainsEnchantment()
	{
		final Enchantment[] enchantments = Enchantment.values();
		assertTrue(enchantments.length > 0);
	}

	@Test
	void displayName_notNull()
	{
		assertNotNull(Enchantment.PROTECTION_ENVIRONMENTAL.displayName(1));
	}

	@Test
	void isTradeable()
	{
		assertTrue(Enchantment.PROTECTION_FALL.isTradeable());
	}

	@Test
	void isDiscoverable()
	{
		assertTrue(Enchantment.PROTECTION_FALL.isDiscoverable());
	}

	@Test
	void getMaxModifiedCost()
	{
		assertEquals(Enchantment.PROTECTION_FALL.getMaxModifiedCost(1), 11);
	}

	@Test
	void getMinModifiedCost()
	{
		assertEquals(Enchantment.PROTECTION_FALL.getMinModifiedCost(1), 5);
	}

	@Test
	void getRarity()
	{
		assertNotNull(Enchantment.ARROW_DAMAGE.getRarity());
	}

	@Test
	void getName()
	{
		assertNotNull(Enchantment.PROTECTION_ENVIRONMENTAL.getName());
	}

	@Test
	void getMaxLevel()
	{
		assertEquals(Enchantment.MENDING.getMaxLevel(), 1);
	}

	@Test
	void getStartLeve()
	{
		assertEquals(Enchantment.DURABILITY.getStartLevel(), 1);
	}

	@Test
	void getItemTarget()
	{
		assertNotNull(Enchantment.CHANNELING.getItemTarget());
	}

	@Test
	void isTreasure()
	{
		assertFalse(Enchantment.ARROW_FIRE.isTreasure());
	}

	@Test
	void isCursed(){
		assertFalse(Enchantment.ARROW_FIRE.isCursed());
	}

	@Test
	void conflictsWith()
	{
		assertTrue(Enchantment.PROTECTION_FIRE.conflictsWith(Enchantment.PROTECTION_ENVIRONMENTAL));
	}


	@Test
	void conflictsWith_nullInput()
	{
		assertThrows(NullPointerException.class, () -> Enchantment.PROTECTION_FIRE.conflictsWith(null));
	}
}
