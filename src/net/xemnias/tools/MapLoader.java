package net.xemnias.tools;

import java.awt.Dimension;
import java.io.File;
import java.util.Iterator;
import java.util.List;


import org.jdom2.DataConversionException;
import org.jdom2.Element;

import net.xemnias.lib.XMLMap;
import net.xemnias.lib.XMLMapLoader;

public class MapLoader 
{

	private XMLMap xmlMap;
	private XMLMapLoader mapLoader;
	private List<?> mapDataBlock;
	private List<?> mapDataItem;
	private List<?> mapDataEntity;
	private List<?> mapDataSize;
	private RightPanel act;
	private Case[][] cases = null;
	
	public MapLoader(Case[][] c, RightPanel actionListener, String path)
	{
		xmlMap = new XMLMap(new File(path));
		mapLoader = new XMLMapLoader(xmlMap);
		cases = c;
		act = actionListener;
	}

	public void buildMap(Case[][] cases) 
	{
		Iterator<?> sizeI = mapDataSize.iterator();
		while(sizeI.hasNext())
		{
			Element e = (Element)sizeI.next();
			
			try {
				int w =  e.getAttribute("witdh").getIntValue()/32;
				int h =  e.getAttribute("height").getIntValue()/32;
				
				act.parent.leftPanel.cases = new Case[w][h];
				act.parent.leftPanel.xSize = w;
				act.parent.leftPanel.ySize = h;
				
				for(int x = 0; x < act.parent.leftPanel.xSize; x++)
				{
					for(int y = 0; y < act.parent.leftPanel.ySize; y++)
					{
						act.parent.leftPanel.cases[x][y] = new Case(x*32, y*32);
					}
				}
				
			} catch (DataConversionException e1) {
				e1.printStackTrace();
			}
			act.parent.leftPanel.setPreferredSize(new Dimension(act.parent.leftPanel.xSize*32, act.parent.leftPanel.ySize*32));

			act.parent.leftPanel.updateUI();
		}
		
		Iterator<?> i = mapDataBlock.iterator();
		while(i.hasNext())
		{
			Element block = (Element)i.next();
			int x = readX(block);
			int y = readY(block);
			int id = readID(block);
			
			act.parent.leftPanel.cases[x/32][y/32] = new Case(x, y);
			act.parent.leftPanel.cases[x/32][y/32].id = id;
			act.parent.leftPanel.cases[x/32][y/32].type = "block";
			act.parent.leftPanel.cases[x/32][y/32].setSprite(act.parent.leftPanel.cases[x/32][y/32].getTexture(id));
		}
		
		Iterator<?> i2 = mapDataItem.iterator();
		while(i2.hasNext())
		{
			Element item = (Element)i2.next();
			int x = readX(item);
			int y = readY(item);
			int id = readID(item);
			
			act.parent.leftPanel.cases[x/32][y/32] = new Case(x, y);
			act.parent.leftPanel.cases[x/32][y/32].id = id;
			act.parent.leftPanel.cases[x/32][y/32].type = "item";
			act.parent.leftPanel.cases[x/32][y/32].setSprite(act.parent.leftPanel.cases[x/32][y/32].getTexture(id));
		}
		
		Iterator<?> i3 = mapDataEntity.iterator();
		while(i3.hasNext())
		{
			Element item = (Element)i3.next();
			int x = readX(item);
			int y = readY(item);
			int id = readID(item);
			
			act.parent.leftPanel.cases[x/32][y/32] = new Case(x, y);
			act.parent.leftPanel.cases[x/32][y/32].id = id;
			act.parent.leftPanel.cases[x/32][y/32].type = "entity";
			act.parent.leftPanel.cases[x/32][y/32].setSprite(act.parent.leftPanel.cases[x/32][y/32].getTexture(id));
		}
		updateMap();
	}

	private void updateMap()
	{
		act.parent.leftPanel.updateUI();
	}

	public void readMap() 
	{
		mapLoader.parseMap();
		mapDataBlock = mapLoader.getElementListByChildren("block");
		mapDataItem = mapLoader.getElementListByChildren("item");
		mapDataEntity = mapLoader.getElementListByChildren("entity");
		mapDataSize =mapLoader.getElementListByChildren("size");
	}
	
	private int readX(Element e)
	{
		try 
		{
			return e.getAttribute("x").getIntValue();
		} 
		catch (DataConversionException e1)
		{
			e1.printStackTrace();
		}
		return -1;
	}
	
	private int readY(Element e)
	{
		try 
		{
			return e.getAttribute("y").getIntValue();
		} 
		catch (DataConversionException e1)
		{
			e1.printStackTrace();
		}
		return -1;
	}
	
	private int readID(Element e)
	{
		return Integer.parseInt(e.getText());
	}

	public Case[][] getCases() 
	{
		return cases;
	}
}
