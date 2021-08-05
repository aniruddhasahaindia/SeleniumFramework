package Pilot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import ReadAndWriteInputData.Configuration;
import TestLab.TestCases;
import databaseConnection.FetchDatabase;
import databaseConnection.StoreDatabase;

public class DriverScript {
	
	static JFrame frame;
	static JTable jTable;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		//TestCases.runTestCase();
		GUITest32_1();
	}
	
	 public static void GUITest32_1(){    
			try {
				     frame = new JFrame("Execution Grid");
				     frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					 frame.setDefaultCloseOperation(3);
				     JButton btn = new JButton("Execute!");
				     JPanel topPanel = new JPanel(new BorderLayout());
				     JPanel bottomPanel = new JPanel();
				    
				     HashMap data = FetchDatabase.getDBValues("automation_qa", "testcases");
				     String[][] data1 = formatHashMap(data);
//				     System.out.println(data1[0].length);
				     String column[]={"ID","NAME","DRIVER","RUN_STATUS"};  		    
				     jTable=new JTable(data1,column);   
					 //jTable.setBounds(30,40,200,300);          
					 JScrollPane srollPane=new JScrollPane(jTable);  
					 jTable.getTableHeader().setBackground(Color.orange);
					 jTable.getTableHeader().setForeground(Color.black);
					
					 Font font = new Font("Verdana", Font.PLAIN, 15);
					 jTable.setFont(font);
					 
					 topPanel.add(srollPane,BorderLayout.PAGE_START); 
					 
					 JPanel Panel_addRow = addButtonToPanel("Add Row", Color.black, Color.white, BorderLayout.LINE_END);
					 
					 //btn.setBounds(250, 150, 150, 30);
					 topPanel.add(btn,BorderLayout.WEST);
					 topPanel.add(Panel_addRow,BorderLayout.PAGE_END);
					 btn.addActionListener(new ActionListener(){  
					    	public void actionPerformed(ActionEvent e){  
					    		try {
//					    			 System.out.println("Welcome to Javatpoint 1");
					    			 //updateExecutionGrid();
					    			//insertRows(data1, column, jTable, frame);
					    			 //TestCases.runTestCase();
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
					    			
					    	 }

							 
					 }); 
					 
					 JSplitPane splitPane = new JSplitPane(SwingConstants.HORIZONTAL, topPanel, bottomPanel);	
					 //splitPane.setDividerLocation(frame.getHeight()*.8);
					 frame.add(splitPane);		     
					 frame.setVisible(true); 
					 //System.out.println(frame.getHeight());
					 
				    
			 }
			 catch(Exception e) {
				 System.out.println(e.getLocalizedMessage());
				 
			 }
		    
		}    

	 
	 private static void updateExecutionGrid() {
			
		int iRow = jTable.getRowCount();
		int iCol = jTable.getColumnCount();
		String[] recordSet = new String[iCol];
		StoreDatabase.truncateDBValues("automation_qa", "testcases");
		for(int i=0; i<iRow; i++) {
			for(int j=0;j<iCol; j++) {
				System.out.println(jTable.getValueAt(i, j));
				recordSet[j] = jTable.getValueAt(i, j).toString();
				
			}
			StoreDatabase.setDBValues("automation_qa", "testcases", recordSet);
		}
		 
		} 
	 
	 static String[][] formatHashMap(HashMap map)
	 	{
	 		
	 		int mapRow = map.size();
	 		int mapColumn = map.get(map.keySet().toArray()[0]).toString().split(Configuration.getDelimiter()).length+1;
	 		String[][] arr = new String[mapRow][mapColumn];
	 		for(int i = 0;i<mapRow;i++) {
	 			String key = null;
	 			int k=-1;
	 			for(int j = 0;j<mapColumn-1;j++) {
	 				k++;
	 				if(j==0) {
	 					key = map.keySet().toArray()[i].toString();
	 					arr[i][k++]=key;
	 					
	 				}
	 				arr[i][k]=map.get(key).toString().split(Configuration.getDelimiter())[j];
//	 				System.out.print("Key " + key);
//	 				System.out.println("  ... Item " + arr[i][k]);
	 			}
	 		}
	 		return arr;
	 	}
	 
	 
	 static private JPanel addButtonToPanel(String buttonTxt,Color background,Color foreground, String orientation) {
		 JButton addRowButton = new JButton(buttonTxt);
	     addRowButton.setBackground(background);
	     addRowButton.setForeground(foreground);
	     JPanel buttonPanel = new JPanel(new BorderLayout());
	     buttonPanel.add(addRowButton, orientation);
	     return buttonPanel;
	 }
	 
	 private static void insertRows(String[][] data1,String[] column,JTable table,JFrame frame){
	       // Insert the last row
	       DefaultTableModel model = new DefaultTableModel(data1,column);
	       model.insertRow(table.getRowCount(),new Object[]{"","","","",""});
	       table = new JTable(data1, column);
	       JScrollPane scrollPane = new JScrollPane( table );
	       frame.add( scrollPane );
	 }
}
