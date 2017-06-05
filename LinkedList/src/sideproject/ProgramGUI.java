package sideproject;

import java.util.ArrayList;
import java.util.Random;
import java.util.LinkedList;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ProgramGUI extends JFrame implements ActionListener{
	
	//CONSTANTS *******************************************************
	private static final String DEFAULT_PROGRAM_NAME = "BarGraphCreator";
	private static final int DEFAULT_PROGRAM_WIDTH = 500;
	private static final int DEFAULT_PROGRAM_HEIGHT = 500;
	private static final String DEFAULT_FILEMENU_NAME = "File";
	private static final String DEFAULT_FILE_IMPORTRAWDATA_NAME = "Import Raw Data (.txt)";
	private static final String DEFAULT_FILE_IMPORTTXT_NAME = "Import Text File (.txt)";
	private static final String DEFAULT_FILE_IMPORTBINARY_NAME = "Import Binary File (.txt)";
	private static final String DEFAULT_FILE_EXPORTTEXT_NAME = "Export to Text File";
	private static final String DEFAULT_FILE_EXPORTBINARY_NAME = "Export to Binary File";
	private static final String DEFAULT_FILE_EXIT_NAME = "Exit";
	private static final String DEFAULT_EDITMENU_NAME = "Edit";
	private static final String DEFAULT_EDIT_DRAWCHART_NAME = "Draw Chart";
	private static final String DEFAULT_EDIT_COLORS = "Change Colors";
	private static final String DEFAULT_EDIT_SORTBYNAME = "Sort by Name";
	private static final String DEFAULT_EDIT_SORTBYFREQUENCY = "Sort by Frequency";
	private static final String DEFAULT_VIEWMENU_NAME = "View";
	private static final String DEFAULT_VIEW_DATA_NAME = "View Data";
	private static final int DEFAULT_BAR_WIDTH = 200;
	
	//INSTANCE VARIABLES **********************************************
	DataList dataList;
		
	//CONSTRUCTORS ****************************************************
	public ProgramGUI()
	{
		//Intiialization
		super();
		dataList = new DataList();
		
		//Initial JFrame Setup
		this.setLayout(new BorderLayout());
		this.setSize(DEFAULT_PROGRAM_WIDTH,DEFAULT_PROGRAM_HEIGHT);
		this.setName(DEFAULT_PROGRAM_NAME);
		//Close operation
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent e)
			{
				int result = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to quit?",
							"Confirm Quit", JOptionPane.YES_NO_CANCEL_OPTION);
				if (result == JOptionPane.YES_OPTION)
				{	
					System.exit(0);
				}
			} 
		}); 
		
		//MENU BAR CREATION (creates JMenuItems - JMenu - JMenuBar) 
		//JMenuItems (File-Edit-View)**********
		//File Dropdown Items
		JMenuItem fileImportRawData = new JMenuItem(DEFAULT_FILE_IMPORTRAWDATA_NAME),
				  fileImportText = new JMenuItem(DEFAULT_FILE_IMPORTTXT_NAME),
				  fileImportBinary = new JMenuItem(DEFAULT_FILE_IMPORTBINARY_NAME),
				  fileExportText = new JMenuItem(DEFAULT_FILE_EXPORTTEXT_NAME),
				  fileExportBinary = new JMenuItem(DEFAULT_FILE_EXPORTBINARY_NAME),
				  fileExit = new JMenuItem(DEFAULT_FILE_EXIT_NAME);
		fileImportRawData.addActionListener(this);
		fileImportText.addActionListener(this); //uses JMenuItem name 
		fileImportBinary.addActionListener(this); //uses JMenuItem name
		fileExportText.addActionListener(this);
		fileExportBinary.addActionListener(this);
		fileExit.addActionListener(this);
		//Edit Dropdown Items
		JMenuItem drawChart = new JMenuItem(DEFAULT_EDIT_DRAWCHART_NAME),
				  editColors = new JMenuItem(DEFAULT_EDIT_COLORS),
				  editSortByName = new JMenuItem(DEFAULT_EDIT_SORTBYNAME),
				  editSortByFrequency = new JMenuItem(DEFAULT_EDIT_SORTBYFREQUENCY);
		drawChart.addActionListener(this);
		editColors.addActionListener(this);
		editSortByName.addActionListener(this);
		editSortByFrequency.addActionListener(this);
		//View Dropdown Items
		JMenuItem viewData = new JMenuItem(DEFAULT_VIEW_DATA_NAME);
		viewData.addActionListener(this);
		//JMenus(File-Edit-View**********
		JMenu fileMenu = new JMenu(DEFAULT_FILEMENU_NAME),
			  editMenu = new JMenu(DEFAULT_EDITMENU_NAME),
			  viewMenu = new JMenu(DEFAULT_VIEWMENU_NAME);
		//fileMenu
		fileMenu.add(fileImportRawData);
		fileMenu.add(fileImportText);
		fileMenu.add(fileImportBinary);
		fileMenu.add(fileExportText);
		fileMenu.add(fileExportBinary);
		fileMenu.add(fileExit);
		//editMenu
		editMenu.add(drawChart);
		editMenu.add(editColors);
		editMenu.add(editSortByName);
		editMenu.add(editSortByFrequency);
		//viewMenu
		viewMenu.add(viewData);
		
		//JMenuBar**********
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(viewMenu);
		this.add(menuBar, BorderLayout.NORTH);
	} 
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String action = e.getActionCommand();
		String fileName = null;
		boolean isValid = false;
		ArrayList<Data> temp = null;
		BarChart chart = new BarChart();
		
		//File Menu ************
		//import raw data
		if(action.equals(DEFAULT_FILE_IMPORTRAWDATA_NAME))
		{
			do
			{
				fileName = JOptionPane.showInputDialog("Name of file to load?");
				System.out.println(fileName);
				temp =  ProgramIO.fillList(fileName);
				isValid = fileName != null && temp != null;
				if(!isValid)
				{
					JOptionPane.showMessageDialog(this, "ERROR: file not found, try again.");
				}
			} while(!isValid); 
			this.dataList.setDataList(temp);	
		}
		//Import .txt
		if(action.equals(DEFAULT_FILE_IMPORTTXT_NAME))
		{
			do
			{
				fileName = JOptionPane.showInputDialog("Name of file to load?");
				System.out.println(fileName);
				temp =  ProgramIO.inputTXT(fileName);
				isValid = fileName != null && temp != null;
				if(!isValid)
				{
					JOptionPane.showMessageDialog(this, "ERROR: file not found, try again.");
				}
			} while(!isValid); 
			this.dataList.setDataList(temp);
		}
		//Import Binary
		if(action.equals(DEFAULT_FILE_IMPORTBINARY_NAME))
		{
			do
			{
				fileName = JOptionPane.showInputDialog("Name of file to load?");
				System.out.println(fileName);
				temp =  ProgramIO.inputObjectsBinary(fileName);
				isValid = fileName != null && temp != null;
				if(!isValid)
				{
					JOptionPane.showMessageDialog(this, "ERROR: file not found, try again.");
				}
			} while(!isValid); 
			this.dataList.setDataList(temp);
		}
		//Export text
		if(action.equals(DEFAULT_FILE_EXPORTTEXT_NAME))
		{
			JOptionPane pane = new JOptionPane();
			fileName = pane.showInputDialog("Name of file for Output?");	
			ProgramIO.outputTXT(fileName, this.dataList.getDataList());
			pane.setVisible(false);
			
		}
		//Exit
		if(action.equals(DEFAULT_FILE_EXIT_NAME))
		{
			int result = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to quit?",
							"Confirm Quit", JOptionPane.YES_NO_CANCEL_OPTION);
			if (result == JOptionPane.YES_OPTION)
			{	
				System.exit(0);
			}
		}
		//Export Binary
		if(action.equals(DEFAULT_FILE_EXPORTBINARY_NAME))
		{
			JOptionPane pane = new JOptionPane();
			fileName = pane.showInputDialog("Name of file for Output?");	
			ProgramIO.outputObjectsBinary(fileName, this.dataList.getDataList());
			this.add(pane);
		}
		//EditMenu **************
		//DrawChart
		if(action.equals(DEFAULT_EDIT_DRAWCHART_NAME))
		{
			JScrollPane scroll = new JScrollPane(new BarChart());
			this.add(scroll,BorderLayout.CENTER);
			
			//scroll.add(chart);
			//this.add(scroll, BorderLayout.CENTER); //should add and update in constructor
			
		}
		//EditColors
		if(action.equals(DEFAULT_EDIT_COLORS))
		{
			chart.randomizeColors();
			repaint();
		}
		//SortByName
		if(action.equals(DEFAULT_EDIT_SORTBYNAME))
		{
			this.dataList.sortByName();
		}
		
		if(action.equals(DEFAULT_EDIT_SORTBYFREQUENCY))
		{
			this.dataList.sortByFrequency();
		}
		
		
		//ViewMenu **************
		if(action.equals(DEFAULT_VIEW_DATA_NAME))
		{
			//JTextArea filled with instance variable toString()
			JTextArea data = new JTextArea("DATA : FREQUENCY\n\n" + this.dataList.toString());
			data.setEditable(false);
			//JScroll Pane (adds JTextArea)
			JScrollPane scroll = new JScrollPane(data);
			//Adding & Display
			JFrame frame = new JFrame();
			frame.setSize(DEFAULT_PROGRAM_WIDTH, DEFAULT_PROGRAM_HEIGHT);
			frame.add(scroll);
			frame.setVisible(true);
		}
		
	}
	
	private class BarChart extends JPanel
	{
		LinkedList<Color> colorsList; //necessary so colors dont change every repaint/size change
		
		public BarChart()
		{
			super();
			ArrayList<Data> list = dataList.getDataList();
			colorsList = new LinkedList();
			for(Data data : list)
			{
				colorsList.add(this.getRandomColor());
			}
		}
		
		private int calculateHeight(int frequency, int maxFrequency)
		{
			System.out.println(frequency + "   " + maxFrequency);
			int barHeight = (this.getHeight()*frequency/maxFrequency) - 30;
			return barHeight;
		}
		
		public Color getRandomColor()
		{
			//Declaration
			int red, green, blue;
			Random number;
			//Initializaion/Calculation
			number = new Random();
			red = number.nextInt(255);
			green = number.nextInt(255);
			blue = number.nextInt(255);
			
			return new Color(red,blue,green);
		}
		
		public void randomizeColors()
		{
			int size = colorsList.size();
			colorsList.clear();
			for(int i = 0; i < size; i++)
			{
				colorsList.add(this.getRandomColor());
			}
			this.repaint();
		}
		
		@Override
		public void paintComponent(Graphics g) 
		{
			String name;
			int i = 0;
			int frequency;
			int barWidth = DEFAULT_BAR_WIDTH;
			int barHeight;
			int xPosition = 80;
			int yPosition;
			Color color;  
			Font font = new Font(Font.SERIF ,Font.BOLD,12);
			ArrayList<Data> list = dataList.getDataList();
			int[] extrema = DataList.findFrequencyExtrema(list);
			
			for(Data data : list)
			{
				name = data.getName();
				frequency = data.getFrequency();
				barHeight = this.calculateHeight(frequency, extrema[0]);
				yPosition = this.getHeight() - barHeight;
				color = colorsList.get(i);
				g.setColor(color);
				g.fillRect(xPosition, yPosition, barWidth, barHeight-20); 
				g.setColor(Color.BLACK);
				g.setFont(font);
				g.drawString(name, xPosition,this.getHeight());
				g.drawString("Frequency: " + frequency, xPosition, yPosition);
				xPosition += barWidth;
				i++;
			}
			g.drawString("Name: ", 10, this.getHeight());
		}
	}

}
