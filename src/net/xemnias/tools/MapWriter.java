package net.xemnias.tools;

import java.awt.Dimension;
import java.io.FileOutputStream;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class MapWriter 
{
	private Element root = new Element("MAP");
	private Document document = new Document(root);
	private Case[][] cases;
	private Dimension size;
	
	public MapWriter(Case[][] ca, Dimension d) 
	{
		cases = ca;
		size = d;
	}
	
	public void buildXMLData()
	{
		if(size != null)
		{
			Element e = new Element("size");

			e.setAttribute(new Attribute("witdh", Integer.toString(size.width)));
			e.setAttribute(new Attribute("height", Integer.toString(size.height)));
			
			root.addContent(e);
		}
		
		for(int x = 0; x < size.width/32; x++)
		{
			for(int y = 0; y < size.height/32; y++)
			{
				if(cases[x][y].type != null)
				{
					Element e = new Element(cases[x][y].type);
					e.setAttribute(new Attribute("x", cases[x][y].getXString()));
					e.setAttribute(new Attribute("y", cases[x][y].getYString()));
					e.setText(Integer.toString(cases[x][y].id));
	
					root.addContent(e);
				}
			}
		}
	}
	
	public int writeAs(String str)
	{
		try
	   {
	      XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	      sortie.output(document, new FileOutputStream(str));
	   }
	   catch (java.io.IOException e)
	   {
		   e.printStackTrace();
		   return 0;
	   }
		return 1;
	}

}
