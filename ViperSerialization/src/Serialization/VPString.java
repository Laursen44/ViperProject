package Serialization;

import static Serialization.SerializationWriter.*;


public class VPString 
{
	public static final byte CONTAINER_TYPE = ContainerType.STRING;
	public short nameLength;
	public byte[] name;
	public int size = 1 + 2 + 4 + 4;
	public int count = 0;
	public char[] characters;
	
	private VPString()
	{
		
	}
	
	public void setName(String name)
	{
		if (this.name != null)
			size -= this.name.length;
		
		nameLength = (short)name.length();
		this.name = name.getBytes();
		size += nameLength;
	}
	
	public String getName() 
	{
		return new String(name, 0, nameLength);
	}
	
	public String getString() 
	{
		return new String(characters);
	}
	
	private void updateSize()
	{
		size += getDataSize();
	}
	
	public int getBytes(byte[] dest, int pointer)
	{
		pointer = writeBytes(dest, pointer, CONTAINER_TYPE);
		pointer = writeBytes(dest, pointer, nameLength);
		pointer = writeBytes(dest, pointer, name);
		pointer = writeBytes(dest, pointer, size);
		pointer = writeBytes(dest, pointer, count);
		pointer = writeBytes(dest, pointer, characters);
		return pointer;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public int getDataSize()
	{
		return characters.length * Type.getSize(Type.CHAR);
	}
	
	public static VPString Create(String name, String data)
	{
		VPString string = new VPString();
		string.setName(name);
		string.count = data.length();
		string.characters = data.toCharArray();
		string.updateSize();
		return string;
	}
	
	public static VPString Deserialize(byte[] data, int pointer)
	{
		byte containerType = data[pointer++];
		assert(containerType == CONTAINER_TYPE);
		
		VPString result = new VPString();
		result.nameLength = readShort(data, pointer);
		pointer += 2;
		result.name = readString(data, pointer, result.nameLength).getBytes();
		pointer += result.nameLength;
		
		result.size = readInt(data, pointer);
		pointer += 4;
		
		result.count = readInt(data, pointer);
		pointer += 4;
		
		result.characters = new char[result.count];
		readChars(data, pointer, result.characters);
		
		pointer += result.count * Type.getSize(Type.CHAR);
		return result;
	}
}


