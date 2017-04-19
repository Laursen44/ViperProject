package Serialization;


import java.util.Random;

public class TestClass 
{
	static Random random = new Random();
	
	static void printBytes(byte[] data)
	{
		for (int i = 0; i < data.length; i++)
			{
				System.out.printf("0x%x ", data[i]);
			}
	}
	
	public static void main(String[] args)
	{
		test1();
	}
	
	public static void test1()
	{
		int[] data = new int[50];
		for (int i = 0; i < data.length; i++)
		{
			data[i] = random.nextInt();
		}
		
		VPDatabase database = new VPDatabase("database");
		
		VPArray array = VPArray.Integer("numbers", data);
		VPField field = VPField.Integer("int", 8);
		
		VPObject object = new VPObject("entity");
		object.addArray(array);
		object.addField(field);
		
		database.addObject(object);
		database.addObject(object);
		
		byte[] data1 = new byte[database.getSize()];
		database.getBytes(data1, 0);
		//printBytes(data1);
		//System.out.println("\n");
		//System.out.println("Object count: " + database.objects.size());
		database.serializeToFile("test.vpdb");
		VPDatabase database1;
		database1 = VPDatabase.DeserializeFromFile("test.vpdb");
		
		System.out.println(database1.HEADER.length);
		System.out.println(database1.size);
		byte[] data2 = new byte[database.getSize()];
		database1.getBytes(data2, 0);
		//printBytes(data2);
		//System.out.println("Object count: " + database1.objects.size());
		
	}
	
	public void test2()
	{
		VPField field = VPField.Integer("hope", 10);
		byte[] data = new byte[field.getSize()];
		field.getBytes(data, 0);
		printBytes(data);
	}
}
