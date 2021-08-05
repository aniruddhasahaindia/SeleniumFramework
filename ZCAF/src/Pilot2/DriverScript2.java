package Pilot2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ScrollPane;
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
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.apache.poi.util.SystemOutLogger;

import ReadAndWriteInputData.Configuration;
import TestLab.TestCases;
import databaseConnection.FetchDatabase;
import databaseConnection.StoreDatabase;

public class DriverScript2 {
	
	static JFrame frame;
	static JTable table;
	static int selectedRowIndex,selectedColumnIndex;

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
				     JButton btn_ExecuteTC = new JButton("Execute!");
				     JButton btn_AddRow = new JButton("Add Row");
				     JButton btn_DeleteRow = new JButton("Delete Row"); JButton btn_DeleteRow2 = new JButton("Delete Row2");
				     JButton btn_AddColumn = new JButton("Add Column");
				     JPanel topPanel = new JPanel();
				     JPanel midPanel = new JPanel();//(new FlowLayout());
				     JPanel bottomPanel = new JPanel();
				    
				     HashMap data = FetchDatabase.getDBValues("automation_qa", "testcases");
				     String[][] data1 = formatHashMap(data);
				     
				     DefaultTableModel tableModel = new DefaultTableModel();
				     table = new JTable(tableModel);
				     String column[]={"ID","NAME","DRIVER","RUN_STATUS"}; 
				     for(int i=0;i<column.length;i++) {
				    	 tableModel.addColumn(column[i]);
				     }
				     
				     for(int i=0;i<data1.length;i++) {
				    	 tableModel.addRow(data1[i]);
				    	 
				     }
				    				             
					 JScrollPane srollPane=new JScrollPane(table);  
					 table.getTableHeader().setBackground(Color.orange);
					 table.getTableHeader().setForeground(Color.black);
					
					 Font font = new Font("Verdana", Font.PLAIN, 15);
					 table.setFont(font);
					 
					 topPanel.add(srollPane);//,BorderLayout.PAGE_START); 
					 
//					 JPanel Panel_addRow = addButtonToPanel(btn_ExecuteTC, Color.ORANGE, Color.white, FlowLayout.RIGHT);
//					 JPanel Panel_executeTC = addButtonToPanel(btn_AddRow, Color.black, Color.white, FlowLayout.RIGHT);
//					 JPanel Panel_deleteRow = addButtonToPanel(btn_DeleteRow, Color.black, Color.white, FlowLayout.RIGHT);
					 
					 //btn.setBounds(250, 150, 150, 30);
					 midPanel.add(btn_ExecuteTC);//,FlowLayout.RIGHT);
					 midPanel.add(btn_AddRow);//,FlowLayout.RIGHT);
					 midPanel.add(btn_DeleteRow);//,BorderLayout.PAGE_END);
					 midPanel.add(btn_AddColumn);
					 btn_ExecuteTC.addActionListener(new ActionListener(){  
					    	public void actionPerformed(ActionEvent e){  
					    		try {
//					    			 System.out.println("Welcome to Javatpoint 1");
					    			 updateExecutionGrid();
					    			//insertRows(data1, column, jTable, frame);
					    			 TestCases.runTestCase();
					    			//tableModel.addRow(new Object[] {"new1","new2"});
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
					    			
					    	 }

							 
					 }); 
					 
					 btn_AddColumn.addActionListener(new ActionListener(){  
					    	public void actionPerformed(ActionEvent e){  
					    		try {
//					    			 
					    			//table.addColumn(new TableColumn(55));
					    			
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
					    			
					    	 }

							 
					 }); 
					 
					 table.addMouseListener(new java.awt.event.MouseAdapter() { // row is clicked
			                public void mouseClicked(java.awt.event.MouseEvent evt) {
			                    selectedRowIndex = table.getSelectedRow();
			                    selectedColumnIndex = table.getSelectedColumn();
			                    Object selectedObject = (Object) table.getModel().getValueAt(selectedRowIndex, selectedColumnIndex);
			                    System.out.println(selectedObject);
			                }

			            });
					 
					 btn_AddRow.addActionListener(new ActionListener(){  
					    	public void actionPerformed(ActionEvent e){  
					    		try {
//					    			 
					    			tableModel.addRow(new Object[] {"testcase id","testcase name","browser","run status"});
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
					    			
					    	 }

							 
					 }); 
					 
					 btn_DeleteRow.addActionListener(new ActionListener(){  
					    	public void actionPerformed(ActionEvent e){  
					    		try {
//					    			 System.out.println("Welcome to Javatpoint 1");
					    			 //updateExecutionGrid();
					    			//insertRows(data1, column, jTable, frame);
					    			 //TestCases.runTestCase();
					    			tableModel.removeRow(selectedRowIndex);
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
					    			
					    	 }

							 
					 }); 
					 
					 JSplitPane splitPane = new JSplitPane(SwingConstants.HORIZONTAL, topPanel, midPanel);	
					 //splitPane.setDividerLocation(frame.getHeight()*.8);
					 frame.setLayout(new FlowLayout(FlowLayout.CENTER)); 
					 bottomPanel.add(btn_DeleteRow2);
					 frame.add(splitPane);		
					 frame.add(bottomPanel);	
					 frame.setVisible(true); 
					 //System.out.println(frame.getHeight());
					 
				    
			 }
			 catch(Exception e) {
				 System.out.println(e.getLocalizedMessage());
				 
			 }
		    
		}    

	 
	 private static void updateExecutionGrid() {
			
		
		int iRow = table.getRowCount();
		int iCol = table.getColumnCount();
		String[] recordSet = new String[iCol];
		StoreDatabase.truncateDBValues("automation_qa", "testcases");
		for(int i=0; i<iRow; i++) {
			for(int j=0;j<iCol; j++) {
				System.out.println(table.getValueAt(i, j));
				recordSet[j] = table.getValueAt(i, j).toString();
				
			}
			System.out.println(recordSet);
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
	 
	 
	 static private JPanel addButtonToPanel(JButton myButton,Color background,Color foreground, int right) {
		 JButton addRowButton = myButton;
	     addRowButton.setBackground(background);
	     addRowButton.setForeground(foreground);
	     JPanel buttonPanel = new JPanel(new BorderLayout());
	     buttonPanel.add(addRowButton, right);
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
