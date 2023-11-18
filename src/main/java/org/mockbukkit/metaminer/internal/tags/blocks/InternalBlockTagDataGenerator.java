package org.mockbukkit.metaminer.internal.tags.blocks;

import com.google.gson.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.mockbukkit.metaminer.DataGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class InternalBlockTagDataGenerator implements DataGenerator
{

	private final File workDirectory;

	public InternalBlockTagDataGenerator(File dataFolder)
	{
		this.workDirectory = new File(dataFolder, "blocks");
	}

	@Override
	public void generateData() throws IOException
	{
		if(!this.workDirectory.exists() && !this.workDirectory.mkdirs()){
			throw new IOException("Could not create directory: " + this.workDirectory);
		}
		JsonArray isSolidValues = new JsonArray();
		JsonArray isNonSolidValues = new JsonArray();
		Block block = new Location(Bukkit.getWorlds().get(0), 10, 10, 10).getBlock();
		for (Material material : Material.values())
		{
			if (!material.isBlock())
			{
				continue;
			}
			block.setType(material);
			if (block.isSolid())
			{
				isSolidValues.add(material.getKey().toString());
			}
			else
			{
				isNonSolidValues.add(material.getKey().toString());
			}
		}
		JsonObject isSolid = new JsonObject();
		isSolid.add("values", isSolidValues);
		saveJSON(isSolid, new File(workDirectory, "solid_blocks.json"));
		JsonObject isNonSolid = new JsonObject();
		isNonSolid.add("values", isNonSolidValues);
		saveJSON(isNonSolid, new File(workDirectory, "non_solid_blocks.json"));
	}

	private void saveJSON(JsonElement jsonElement, File file) throws IOException
	{
		if (!file.exists() && !file.createNewFile())
		{
			throw new IOException("Could not create file: " + file);
		}
		try (Writer writer = new FileWriter(file))
		{
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(jsonElement, writer);
		}
	}

}
