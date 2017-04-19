package Serialization;

import static Serialization.SerializationUtil.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VPDatabase extends VPBase
{
	public static final byte[] HEADER = "VPDB".getBytes();
	public static final short VERSION = 0x0100;
	public static final byte CONTAINER_TYPE = ContainerType.DATABASE;
	private short objectCount;
	public ArrayList<VPObject> objects = new ArrayList<VPObject>();

	private VPDatabase()
	{
		
	}
	
	public VPDatabase(String name)
	{
		setName(name);
		size += HEADER.length + 2 + 1 + 2;
	}
	
	public void addObject(VPObject object) 
	{
		objects.add(object);
		size += object.getSize();
		
		objectCount = (short)objects.size();
	}
	
	public int getSize() 
	{
		return size;
	}

	public int getBytes(byte[] dest, int pointer) 
	{
		pointer = writeBytes(dest, pointer, HEADER);
		pointer = writeBytes(dest, pointer, VERSION);
		pointer = writeBytes(dest, pointer, CONTAINER_TYPE);
		pointer = writeBytes(dest, pointer, nameLength);
		pointer = writeBytes(dest, pointer, name);
		pointer = writeBytes(dest, pointer, size);
		
		pointer = writeBytes(dest, pointer, objectCount);
		for (VPObject object : objects)
			pointer = object.getBytes(dest, pointer);

		return pointer;
	}
	
	public static VPDatabase Deserialize(byte[] data)
	{
		int pointer = 0;
		assert(readString(data, pointer, HEADER.length).equals(HEADER));
		pointer += 4;
		
		if (readShort(data, pointer) != (VERSION))
		{
			System.out.println("Invalid version.");
			return null;
		}
		pointer += 2;
		
		byte containerType = readByte(data, pointer);
		assert(containerType == CONTAINER_TYPE);
		pointer++;
		
		VPDatabase result = new VPDatabase("");
		result.nameLength = readShort(data, pointer);
		pointer += 2;
		result.name = readString(data, pointer, result.nameLength).getBytes();
		pointer += 8;
		
		result.size = readInt(data, pointer);
		pointer += 4;
		
		result.objectCount = readShort(data, pointer);
		pointer += 2;
		
		for (int i = 0; i < result.objectCount; i++)
		{
			VPObject object = VPObject.Deserialize(data, pointer);
			result.objects.add(object);
			pointer += object.getSize();
		}
		
		return result;
	}
	
	public VPObject findObject(String name)
	{
		for (VPObject object : objects)
		{
			if (object.getName().equals(name))
				return object;
		}
		return null;
	}
	
	public static VPDatabase DeserializeFromFile(String path)
	{
		byte[] buffer = null;
		try {
			BufferedInputStream stream = new BufferedInputStream(new FileInputStream(path));
			buffer = new byte[stream.available()];
			stream.read(buffer);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Deserialize(buffer);
	}
	
	public void serializeToFile(String path)
	{
		byte[] data = new byte[getSize()];
		getBytes(data, 0);
		try {
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(path));
			stream.write(data);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
