package internship;

import java.awt.FileDialog;
import java.awt.Font;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CodeEditorGUI {
	
	JFrame frame ;
	JTextArea textArea ;
	JScrollPane scrollPane ;
	
	JMenuBar menu ;
	
	JMenu fileMenu,editMenu,formatMenu,cmdMenu,languageMenu ;
	
	JMenuItem newItem,newWindowItem,openItem,saveAsItem,saveItem, exitItem ;
	
	JMenuItem wordWrapItem,fontItem,fontSizeItem ;
	
	JMenuItem javaItem,cItem,cppItem,pythonItem,htmlItem ;
	
	JMenuItem cmdItem ;
	
	JMenuItem timesNewRomanItem,arialItem,consolasItem ;
	
	JMenuItem size10,size15,size18,size25,size29 ;
	
	boolean wrap = false ;
	
	int fontSize = 13 ;
	String fontStyle = "Arial" ;
	
	String openFileName ;
	String openDirectory ;
	
	public CodeEditorGUI() {
		
		createFrame();
		createTextArea();
		
		createScrollBar();
		
		createMenuBar();
		
		createFileMenuItem();
		
		createFormatItem();
		
		createLanguageMenuItem();
		
		createCMDMenu();
	}
	
	public void createFrame()
	{
		frame = new JFrame("Notepad Code Editor") ;
		
		frame.setSize(700,500);
		
		frame.setVisible(true);
		
	}
	
	public void createTextArea()
	{
		textArea = new JTextArea() ;
		
		frame.add(textArea) ;
	}
	
	public void createScrollBar()
	{
		scrollPane = new JScrollPane(textArea,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED) ;
		
		frame.add(scrollPane);
	}
	
	public void createMenuBar()
	{
		 menu = new JMenuBar() ;
		
		frame.setJMenuBar(menu);
		
		 fileMenu = new JMenu("File") ;
		
		menu.add(fileMenu);
		
		 editMenu = new JMenu("Edit") ;
		
		menu.add(editMenu) ;
		
		 formatMenu = new JMenu("Format") ;
		
		menu.add(formatMenu);
		
		 cmdMenu = new JMenu("Command Prompt") ;
		
		menu.add(cmdMenu) ;
		
		 languageMenu = new JMenu("Languages") ;
		
		menu.add(languageMenu); 
	}
	
	
	public void createFileMenuItem()
	{
		newItem = new JMenuItem("New") ;
		
		newItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				textArea.setText("");
			}
		});
		
		fileMenu.add(newItem) ;
		
		newWindowItem = new JMenuItem("New Window");
		
		newWindowItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				CodeEditorGUI g2 = new CodeEditorGUI() ;
				
				g2.frame.setTitle("Untitled");
				
			}
		});
		
		fileMenu.add(newWindowItem) ;
		
		openItem = new JMenuItem("Open..");
		
		openItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				FileDialog fd = new FileDialog(frame,"Open",FileDialog.LOAD) ;
				
				fd.setVisible(true);
				
				 openDirectory = fd.getDirectory() ;
				
				 openFileName = fd.getFile();
				
				FileReader fr;
				try {
					fr = new FileReader(openDirectory+openFileName);
					BufferedReader br = new BufferedReader(fr) ;
					
					textArea.setText("") ;
					
					frame.setTitle(openFileName);
					
					String data  = br.readLine() ;
					
					while (data!=null) {
						
						textArea.append(data+"\n");
						
						data = br.readLine() ;
					}
					
					br.close();
					fr.close() ;
					
					
				} catch (FileNotFoundException e1) {
					
					System.out.println("file path issue");
				} catch (IOException e1) {
					
					System.out.println("Could not read the data");
				}
				
				
			}
		});
		
		fileMenu.add(openItem) ;
		
		saveItem = new JMenuItem("Save") ;
		
		fileMenu.add(saveItem) ;
		
		saveItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (frame.getTitle().equalsIgnoreCase("untitled")|| frame.getTitle().equalsIgnoreCase("Notepad Code Editor")) {
					
					saveAs() ;
				}
				else
				{
					String path = openDirectory ;
					
					
					
					String fileName = openFileName ;
					
					frame.setTitle(fileName);
					
					String data = textArea.getText() ;
					
					try {
						FileWriter fw = new FileWriter(path+fileName) ;
						BufferedWriter bw = new BufferedWriter(fw);
						
						bw.write(data);
						bw.close();
						fw.close();
					} catch (IOException e1) {
						
						System.out.println("Issue with path");
					}
				}
				
			}
		});
		
		saveAsItem = new JMenuItem("Save As") ;
		
		saveAsItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				saveAs() ;
			}
		});
		
		fileMenu.add(saveAsItem) ;
		
		exitItem = new JMenuItem("Exit") ;
		
		fileMenu.add(exitItem) ;
		
		exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				
			}
		});
	}
	
	public void createFormatItem()
	{
		wordWrapItem = new JMenuItem("Word Wrap Off") ;
		
		wordWrapItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (wrap==false) {
					
					wordWrapItem.setText("Word Wrap On");
					
					textArea.setLineWrap(true);
					wrap = true ;
				}
				else
				{
					wordWrapItem.setText("Word Wrap Off");
					
					textArea.setLineWrap(false);
					wrap = false ;
					
				}
			}
		});
		formatMenu.add(wordWrapItem) ;
		
		fontItem = new JMenu("Font") ;
		
		timesNewRomanItem = new JMenuItem("Times New Roman");
		
		fontItem.add(timesNewRomanItem) ;
		
		timesNewRomanItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setFont("Times New Roman",fontSize);
			}
		});
		
		arialItem = new JMenuItem("Arial");
		
		fontItem.add(arialItem) ;
		
		arialItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setFont("Arial",fontSize);
			}
		});
		
		consolasItem = new JMenuItem("Consolas");
		
		fontItem.add(consolasItem) ;
		
		consolasItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setFont("Consolas",fontSize);
				
			}
		});
		
		formatMenu.add(fontItem) ;
		
		fontSizeItem = new JMenu("Font Size") ;
		
		size10 = new JMenuItem("10") ;
		fontSizeItem.add(size10);
		
		size10.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setFontSize(10);
			}
		});
		
		size15 = new JMenuItem("15") ;
		fontSizeItem.add(size15);
		
		size15.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setFontSize(15);
				
			}
		});
		
		size18 = new JMenuItem("18") ;
		fontSizeItem.add(size18);
		
		size18.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setFontSize(18);
				
			}
		});
		
		size25 = new JMenuItem("25") ;
		fontSizeItem.add(size25);
		
		size25.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setFontSize(25);
				
			}
		});
		
		size29 = new JMenuItem("29") ;
		fontSizeItem.add(size29);
		
		size29.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setFontSize(29);
				
			}
		});
		
		formatMenu.add(fontSizeItem) ;
	}
	
	public void createLanguageMenuItem()
	{
		javaItem = new JMenuItem("Java");
		
		languageMenu.add(javaItem) ;
		
		javaItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setLanguage("Java");
				
			}
		});
		
		cItem = new JMenuItem("C") ;
		
		languageMenu.add(cItem) ;
		
		cItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setLanguage("C");
			}
		});
		
		cppItem = new JMenuItem("C++") ;
		
		languageMenu.add(cppItem) ;
		
		cppItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setLanguage("C++");
			}
			
		});
		
		pythonItem = new JMenuItem("Python") ;
		
		languageMenu.add(pythonItem) ;
		
		pythonItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setLanguage("Python");
			
			}
		});
		
		htmlItem = new JMenuItem("HTML") ;
		
		languageMenu.add(htmlItem) ;
		
		htmlItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setLanguage("HTML");
			}
		});
	}
	
	public void createCMDMenu()
	{
		cmdItem = new JMenuItem("Open CMD") ;
		
		cmdMenu.add(cmdItem);
		
		cmdItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					if (openDirectory!=null) {
						
						Runtime.getRuntime().exec(new String[] {"cmd","/K","start"},null,new File(openDirectory));
					}
					else
					{
						Runtime.getRuntime().exec(new String[] {"cmd","/K","start"},null,null);
					}
				} catch (IOException e1) {
					
					System.out.println("ISsue in cmd");
				}
				
			}
		});
 	}
	
	public void setFont(String fontName,int size)
	{
		switch (fontName) {
		case "Times New Roman":
			textArea.setFont(new Font("Times New Roman",Font.PLAIN,fontSize));
			fontStyle = "Times New Roman" ;
			
			break;
		case "Arial":
			textArea.setFont(new Font("Arial",Font.PLAIN,fontSize));
			fontStyle = "Arial" ;
			break ;
		case "Consolas":
			textArea.setFont(new Font("Consolas",Font.PLAIN,fontSize));
			fontStyle = "Consolas" ;
			break ;
		default:
			break;
		}
	}
	
	public void setFontSize(int size)
	{
		fontSize = size ;
		setFont(fontStyle,fontSize);
		
	}
	
	public void setLanguage(String lang)
	{
		String path = "" ;
		switch (lang) {
		case "Java":
		{
			path = "D:\\internship2\\javaformat.txt" ;
		}
			
			break;
		case "C":
		{
			path = "D:\\internship2\\cformat.txt" ;
		}
			break ;
		case "C++" :
		{
			path = "D:\\internship2\\cppformat.txt" ;
		}
			break ;
		case "HTML":
		{
			path = "D:\\internship2\\htmlformat.txt" ;
		}
			break ;
		case "Python":
		{
			path = "D:\\internship2\\pythonformat.txt" ;
		}
			break ;
		}

		try {
			
			textArea.setText("");
			BufferedReader bf = new BufferedReader(new FileReader(path));
			String line =bf.readLine() ;
			
			while (line!=null) {
				
				textArea.append(line+"\n");
				System.out.println();
				
				line = bf.readLine() ;
			}
		} catch (FileNotFoundException e) {
			
			System.out.println("Issue with path");
		} catch (IOException e) {
			System.out.println("Issue with format");
		}
		}
	public void saveAs()
	{
		FileDialog fd = new FileDialog(frame,"Save As",FileDialog.SAVE);
		
		fd.setVisible(true);
		
		String path = fd.getDirectory() ;
		
		openDirectory = path ;
		
		String fileName = fd.getFile() ;
		
		openFileName = fileName ;
		
		
		
		String data = textArea.getText() ;
		
		try {
			if (fileName!=null) {
				
				FileWriter fw = new FileWriter(path+fileName) ;
				BufferedWriter bw = new BufferedWriter(fw);
				
				bw.write(data);
				bw.close();
				fw.close();
				
				frame.setTitle(fileName);
			}
		} catch (IOException e1) {
			
			System.out.println("Issue with path");
		}
		
	}
	
	
	
}
